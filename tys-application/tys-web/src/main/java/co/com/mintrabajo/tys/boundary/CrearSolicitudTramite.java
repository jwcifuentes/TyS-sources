package co.com.mintrabajo.tys.boundary;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.ObjectUtils.Null;
import co.com.mintrabajo.tys.commons.domain.*;
import co.com.mintrabajo.tys.persistence.dao.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.ldap.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.com.mintrabajo.tys.adapters.NotificacionesBroker;
import co.com.mintrabajo.tys.adapters.RadicacionesYTramitesBroker;
import co.com.mintrabajo.tys.adapters.TramitesBPMBroker;
import co.com.mintrabajo.tys.adapters.TramitesECMBroker;
import co.com.mintrabajo.tys.adapters.integration.client.ecm.AsignacionTySRequest;
import co.com.mintrabajo.tys.adapters.integration.client.ecm.AsignacionTySResponse;
import co.com.mintrabajo.tys.commons.domain.ActualizarDocumentosTramite;
import co.com.mintrabajo.tys.commons.domain.DatosDocumentoAdicional;
import co.com.mintrabajo.tys.commons.domain.DatosSolicitudTramite;
import co.com.mintrabajo.tys.commons.domain.DireccionTerritorial;
import co.com.mintrabajo.tys.commons.domain.DocumentoAdicional;
import co.com.mintrabajo.tys.commons.domain.DocumentoTramite;
import co.com.mintrabajo.tys.commons.domain.InformacionNotificacionActTramite;
import co.com.mintrabajo.tys.commons.domain.JustaCausa;
import co.com.mintrabajo.tys.commons.domain.ProcesoMensajeTyS;
import co.com.mintrabajo.tys.commons.domain.Tramite;
import co.com.mintrabajo.tys.commons.domain.messages.ActualizarProcesoRequest;
import co.com.mintrabajo.tys.commons.domain.messages.ActualizarProcesoResponse;
import co.com.mintrabajo.tys.commons.domain.messages.ObtenerDatosSeccionalRequest;
import co.com.mintrabajo.tys.commons.domain.messages.ObtenerDatosSeccionalResponse;
import co.com.mintrabajo.tys.commons.domain.messages.RadicacionesRequest;
import co.com.mintrabajo.tys.commons.domain.messages.RespuestaRadicacionTramite;
import co.com.mintrabajo.tys.commons.domain.messages.SubirDocumentoActualizarRequest;
import co.com.mintrabajo.tys.commons.domain.messages.SubirDocumentoActualizarResponse;
import co.com.mintrabajo.tys.commons.exceptions.BusinessException;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import co.com.mintrabajo.tys.commons.util.CheckUtil;
import co.com.mintrabajo.tys.commons.util.WebUtil;
import co.com.mintrabajo.tys.infrastructure.Transformer;
import co.com.mintrabajo.tys.json.domain.DatosGenerales;
import co.com.mintrabajo.tys.json.domain.DatosTramitesServicios;
import co.com.mintrabajo.tys.json.domain.RespuestaCorrespondenciaRadicacion;
import co.com.mintrabajo.tys.json.domain.RespuestaTramiteRadicacion;
import co.com.mintrabajo.tys.persistence.dao.DireccionesTerritorialesDAO;
import co.com.mintrabajo.tys.persistence.dao.DocumentosAdicionalesDAO;
import co.com.mintrabajo.tys.persistence.dao.JustaCausaDAO;
import co.com.mintrabajo.tys.persistence.dao.ProcesoMensajesDAO;
import co.com.mintrabajo.tys.persistence.dao.TramitesDAO;
import static co.com.mintrabajo.tys.commons.util.CheckUtil.checkTransFormUTF8;

@Component
public class CrearSolicitudTramite {

	private static final Logger LOGGER = LogManager.getLogger(CrearSolicitudTramite.class);
	private static final String COR_PLANTILLA = "OFICIO";
	private static final String ORIGEN_CREATE = "TyS_C";
	private static final String ORIGEN_UPLOAD = "TyS_UP";
	private static final String ESTADO_CREATE = "LD";
	private static final String ESTADO_UPLOAD = "UP";

	@Value("${correspondencia.tipo.comunicado.id}")
	private Long idTipoComunicacion;
	@Value("${correspondencia.medio.recepcion.id}")
	private Long idMedioRecepcion;
	@Value("${correspondencia.tipologia.documental.id}")
	private Long idTipologiaDocumental;
	@Value("${correspondencia.tiempo.respuesta}")
	private Long tiempoRespuesta;
	@Value("${solicitud.tramite.iniciar.proceso.bpm}")
	private String nombreInstanciaProceso;
	@Value("${correspondencia.tipologia.documental.id}")
	private Long idTipoDocumentalOficio;
	@Value("${system.services.params.usuario}")
	private String user;
	private final RestTemplate restTemplate;

	@Autowired
	private ComplementarInfSolicitud complementarInfSolicitud;

	@Autowired
	private RadicacionesYTramitesBroker radicacionesBroker;

	@Autowired
	@Qualifier("correspondenciaTransformer")
	private Transformer<DatosSolicitudTramite, DatosGenerales> correspondenciaTransformer;

	@Autowired
	@Qualifier("tramiteTransformer")
	private Transformer<DatosSolicitudTramite, DatosTramitesServicios> tramiteTransformer;

	@Autowired
	private TramitesBPMBroker tramitesBpmBroker;

	@Autowired
	private NotificacionesBroker notificacionesBroker;

	 @Autowired
	 private TramitesECMBroker tramitesECMBroker;

	@Autowired
	private DireccionesTerritorialesDAO direccionesTerritorialesDAO;

	@Autowired
	private TramitesDAO tramitesDAO;
	
	@Autowired
	private JustaCausaDAO justaCausaDAO;

	@Autowired
	private DocumentosAdicionalesDAO documentosAdicionalesDAO;

	@Autowired
	private ProcesoMensajesDAO procesoMensajesDAO;

	@Autowired
	private OrganizacionSindicalDAO organizacionSindicalDAO;
	@Autowired
	private PersonaDao personaDao;
	@Autowired
	private EmpleadorDao empleadorDao;
	@Autowired
	private EmpresasDAO empresasDAO;
	@Autowired
	private RemitenteDao remitenteDao;
	@Autowired
	private PracticasAdolecentesSP practicasAdolecentesSP;
	public CrearSolicitudTramite() {
		restTemplate = new RestTemplate();
	}

	public List<RespuestaRadicacionTramite>  procesar(final DatosSolicitudTramite solicitud)
			throws SystemException, BusinessException {

		LOGGER.info("Iniciando proceso de negocio de creacion de la solicitud");

		if (solicitud.getCorrespondencia() == null) {
			throw new BusinessException(
					"La informaci贸n suministrada no es v谩lida para realizar la radicaci贸n del tr谩mite, valide la informaci贸n e intente de nuevo.");
		}

		List<ObtenerDatosSeccionalResponse> dependencias = new ArrayList<ObtenerDatosSeccionalResponse>();

		for (DireccionTerritorial dt : solicitud.getCorrespondencia().getListaDireccionTerritoriales()) {

			ObtenerDatosSeccionalRequest request = ObtenerDatosSeccionalRequest.newInstance()
					.strCodigoTramiteIn(String.valueOf(solicitud.getSolicitudTramite().getIdTramite()))
					.strGradoAsociacionIn(String.valueOf(solicitud.getSolicitudTramite().getIdGradoAsociacion()))
					.strIdSubFondoIn(String.valueOf(dt.getId())).build();

			dependencias.add(tramitesBpmBroker.obtenerDatosSeccional(request));
		
		}
		
		

		Tramite tramite = complementarInfSolicitud.consultaInfTramite(solicitud.getSolicitudTramite().getIdTramite());

		if (tramite == null) {
			LOGGER.error("El tramite solicitado no ha sido encontrado ");
			throw new BusinessException("El tramite solicitado no ha sido encontrado");
		}

		solicitud.getCorrespondencia().setIdTipoComunicacion(idTipoComunicacion);
		solicitud.getCorrespondencia().setIdMedioRecepcion(idMedioRecepcion);
		solicitud.getCorrespondencia().setIdTipologiaDocumental(idTipologiaDocumental);
		solicitud.getCorrespondencia().setTiempoRespuesta(tiempoRespuesta);
		solicitud.getCorrespondencia().setAsunto(tramite.getNombre());
		solicitud.getCorrespondencia().setDescripcion(tramite.getNombre());

		return procesarDependencias(tramite, solicitud, dependencias);
	}

	private List<RespuestaRadicacionTramite> procesarDependencias(final Tramite tramite,
			final DatosSolicitudTramite solicitud, final List<ObtenerDatosSeccionalResponse> dependencias)
			throws BusinessException, SystemException {

		List<RespuestaRadicacionTramite> listaRadicadosTamite = new ArrayList<RespuestaRadicacionTramite>();

		for (ObtenerDatosSeccionalResponse dependencia : dependencias) {

			solicitud.setDependencias(dependencia);
			DatosGenerales dgJson = correspondenciaTransformer.transform(solicitud);
			RadicacionesRequest radicacionesRequest = RadicacionesRequest.newInstance().payload(dgJson)
					.storeProcedure(RadicacionesYTramitesBroker.OPERACION_CORRESPONDENCIA_RADICACION).build();

			RespuestaCorrespondenciaRadicacion rtaCorresp = radicacionesBroker.ejecutarOperacion(radicacionesRequest,
					RespuestaCorrespondenciaRadicacion.class);

			if (!StringUtils.isBlank(rtaCorresp.getNumRadicado())) {
				solicitud.getCorrespondencia().setFechaRadicacion(rtaCorresp.getFechaRadicacion());
				solicitud.getCorrespondencia().setNumeroRadicado(rtaCorresp.getNumRadicado());
			} else {
				LOGGER.error(
						"La informaci贸n suministrada no es v谩lida para realizar la radicaci贸n del tr谩mite, valide la informaci贸n e intente de nuevo.");
				throw new BusinessException(
						"La informaci贸n suministrada no es v谩lida para realizar la radicaci贸n del tr谩mite, valide la informaci贸n e intente de nuevo.");
			}

			DatosTramitesServicios tsJson = tramiteTransformer.transform(solicitud);

			RadicacionesRequest tramiteRequest = RadicacionesRequest.newInstance().payload(tsJson)
					.storeProcedure(RadicacionesYTramitesBroker.OPERACION_TRAMITE_RADICACION).build();
			RespuestaTramiteRadicacion rtaTramite = radicacionesBroker.ejecutarOperacion(tramiteRequest,
					RespuestaTramiteRadicacion.class);

			if (!StringUtils.isBlank(rtaCorresp.getNumRadicado())) {

				DireccionTerritorial direccionesTerritorial = direccionesTerritorialesDAO
						.getDireccionTerritoral(dependencia.getStrIdSubFondoOut());

				listaRadicadosTamite.add(RespuestaRadicacionTramite.newInstance()
						.nombreDireccion(direccionesTerritorial.getNombre()).numeroRadicado(rtaCorresp.getNumRadicado())
						.codigoSeguridad(rtaTramite.getStrCodigoSeguridad()).build());

			} else {
				LOGGER.error(
						"La informaci贸n suministrada no es v谩lida para realizar la radicaci贸n del tr谩mite, valide la informaci贸n e intente de nuevo.");
				throw new BusinessException(
						"La informaci贸n suministrada no es v谩lida para realizar la radicaci贸n del tr谩mite, valide la informaci贸n e intente de nuevo.");
			}
			
			
			String idsFilenet="";
			String idDocumentos="";

			if (solicitud.getListDocumentos() != null && solicitud.getListDocumentos().size()>0) {
				for (DocumentoTramite documento : solicitud.getListDocumentos()) {
					if (documento.getBase64() != null) {

						documento.setCodigoDependencia(dependencia.getStrCodigoSeccionOut());
						documento.setNroRadicado(rtaCorresp.getNumRadicado());
						//byte[] ptext = documento.getNombreDocumento().getBytes("ISO-8859-1"); 
						//String nombreDocumento = checkTransFormUTF8(documento.getNombreDocumento());
						String idFilenet=registrarDocumentoECM(documento,tramite,solicitud);
						idsFilenet+=idFilenet+",";
						idDocumentos=documento.getIdDocumento()+",";
								
						LOGGER.info("Cargar documentos table procesar_mensajes_tys creacion : {}", documento);
						procesoMensajesDAO.crear(ProcesoMensajeTyS.newInstance()
								.codDependencia(documento.getCodigoDependencia())
								.numeroRadicado(documento.getNroRadicado()).documento(documento.getBase64().getBytes())
								.idTipoDocumental(documento.getIdDocumento()).codPlantilla(COR_PLANTILLA)
								.nombreDocumento(documento.getNombreDocumento()).idDocumento(0)
								.idFilenet(idFilenet).origen(ORIGEN_CREATE)
								.estado(ESTADO_UPLOAD).fechaCreacion(new Date()).idUsuarioCrea(user).build());

						// Cargar documentos con servicios
						// tramitesECMBroker.subirDocumentoActualizar
						
						  /*SubirDocumentoActualizarRequest request =
						  SubirDocumentoActualizarRequest.newInstance()
						  .intTramiteTipologiaIn(documento.getIdDocumento())
						  .strBase64In(documento.getBase64())
						  .strCodigoDependenciaIn(documento.
						  getCodigoDependencia())
						  .strCorPlantillaIn(COR_PLANTILLA)
						  .strNombreDocumentoIn(documento.getNombreDocumento())
						  .strIdFilenetIn(documento.getIdFilenet())
						  .strNroRadicadoIn(documento.getNroRadicado())
						  .build();
						  tramitesECMBroker.subirDocumentoActualizar(request);*/
						 
					}
				}
				idsFilenet=idsFilenet.substring(0,idsFilenet.length()-1);
				idDocumentos=idDocumentos.substring(0,idDocumentos.length()-1);
			} else {
				DocumentoTramite documento = new DocumentoTramite();
				documento.setNroRadicado(solicitud.getCorrespondencia().getNumeroRadicado());
				documento.setCodigoDependencia(dependencia.getStrCodigoSeccionOut());
				LOGGER.info("Cargar documentos table procesar_mensajes_tys creacion : {}", documento);
				procesoMensajesDAO.crear(ProcesoMensajeTyS.newInstance()
						.codDependencia(documento.getCodigoDependencia())
						.numeroRadicado(documento.getNroRadicado()).documento(null)
						.idTipoDocumental(0).codPlantilla(COR_PLANTILLA)
						.nombreDocumento(null).idDocumento(0)
						.idFilenet(null).origen(ORIGEN_CREATE)
						.estado(ESTADO_CREATE).fechaCreacion(new Date()).idUsuarioCrea(user).build());
				
			}
			
			/*if (solicitud.getListaJustaCausa() != null) {
				for (JustaCausa justaCausa : solicitud.getListaJustaCausa()) {
					justaCausa.setIdRegistroTramite(Long.parseLong(rtaTramite.getStrIdRegistroTramite()));
				}
				
				justaCausaDAO.crearTodos(solicitud.getListaJustaCausa());
			}*/
			
			Long idRegistroTramite = Long.parseLong(rtaTramite.getStrIdRegistroTramite());
			//if(solicitud.getListaOrganizacionesSindicales()!=null){
				//crearOrganizacionesSindicales(rtaTramite, solicitud.getListaOrganizacionesSindicales());
			//}
			//Actualizar datos personas por cambios en requerimiento de tramite NNA y agregacion de nombres y apellidos
			//if(solicitud.getEmpresa()!=null){
				//personaDao.actualizarPersonas(idRegistroTramite,
					//	solicitud.getEmpresa().getListaPersonas());
				//personaDao.actualizarSocios(idRegistroTramite,solicitud.getEmpresa().getListaSocios());
				//if(solicitud.getEmpresa().getDatosEmpleador()!=null){
					//empleadorDao.crearEmpleador(idRegistroTramite,
						//	solicitud.getEmpresa().getDatosEmpleador());
				//}
			//}
			//Actualizacion de nombres y apellidos del remitente
			//remitenteDao.actualizarNombresApellidos(rtaCorresp.getNumRadicado(),solicitud.getCorrespondencia().getRemitente());
			// Tramite con 1 o 5 Empresa de servicios temporales modificacion
			//if(tramite.getId() == 1 || tramite.getId() == 5) {
				//empresasDAO.actualizarActividadEconomica(idRegistroTramite,solicitud.getEmpresa().getEmpresa());
			//}
			LOGGER.info("Respuesta servicio json generico - OPERACION_TRAMITE_RADICACION : {}", rtaTramite);
			//practicasAdolecentesSP.guardarDatosPracticasAdolecentes(idRegistroTramite,solicitud);
			
//			  DatosIniciarProcesoBPM inJson = DatosIniciarProcesoBPM.newInstance().numeroRadicado(solicitud.getCorrespondencia().getNumeroRadicado()).nomProceso(nombreInstanciaProceso).build();
//			 
//			  IniciarProcesoResponse respInitProcess = tramitesBpmBroker.iniciarInstanciaBPM(IniciarProcesoRequest.newInstance().payload(
//			  inJson).build()); 
//			  LOGGER.info("Respuesta inicio proceso: {}",respInitProcess.getCodigoRespuesta());
			 try {
				 String bpmUri="http://MINTRABGD08A:9080/BPMClient/Service/iniciarbpm";
				 URL url = new URL(bpmUri);
					URLConnection conn = null;
					conn = url.openConnection();
					System.out.println("Conectando a... "+bpmUri);
			        HttpURLConnection httpConn = (HttpURLConnection) conn;
			        httpConn.setRequestMethod("POST");
			        httpConn.addRequestProperty("Content-Type","text/plain");
			        conn.setDoInput(true);
			        conn.setDoOutput(true);
			        OutputStream os = conn.getOutputStream();
			        
			        BufferedWriter writer = new BufferedWriter(
			                new OutputStreamWriter(os, "UTF-8"));
			        String peticionBPM = "{"//\"tipoComunicacion\":[{\"value\":"+idTipoComunicacion+",\"name\":\"Comunicaci髇 Oficial Externa Recibida\"}],"
			        		+ "\"idDocumentoFilenet\":\""+idsFilenet+"\","
			        		+ "\"strReqActualizacion\":\"\","
			        		+ "\"idAgente\":\"\","
			        		+ "\"esReiterado\":\"\","
			        		+ "\"fechaRadicado\":\""+solicitud.getCorrespondencia().getFechaRadicacion()+"\","
	        				+ "\"distribucionFisica\":\"\","
	        				+ "\"loginUser\":\"\","
	        				+ "\"idDocumento\":\""+idDocumentos+"\","
	        				+ "\"responderAremitente\":\"true\","
	        				+ "\"idDocumentoRadicado\":\"\","
	        				+ "\"asunto\":\""+tramite.getNombre()+"\","
    						+ "\"id\":\"\","
			        		+ "\"numeroRadicado\": \""+rtaCorresp.getNumRadicado()+"\", "
	        				+ "\"observacion\":\"\","
			        		+ "\"nomProceso\": \"GTYSW\"}";
			        LOGGER.info("Peticon BPM... "+peticionBPM);
			        writer.write(peticionBPM);
			        writer.flush();
			        writer.close();
			        os.close();
			        httpConn.connect();
			        if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			        	BufferedReader in2 = new BufferedReader(new InputStreamReader(httpConn.getInputStream(),"UTF8"));
			            String inputLine;
			            String contenido = "";
			            while ((inputLine = in2.readLine()) != null) {
			                contenido += inputLine + "\n";
			            }
			            System.out.println("mensaje: "+contenido);
			            httpConn.disconnect();
			            in2.close();
			        }else {
			        	System.out.println("Error de conexi髇 "+httpConn.getResponseCode());
			        }
			 } catch (Exception e) {
					throw new SystemException("Error envio a BPM" + e.getMessage(), e);
				}
		}
		
		try {
			
			enviarNotificaciones(tramite, listaRadicadosTamite, solicitud);
		} catch (Exception e) {
			throw new SystemException("No se ha podido enviar la notificacion" + e.getMessage(), e);
		}

		return listaRadicadosTamite;
	}

	private void crearOrganizacionesSindicales(RespuestaTramiteRadicacion rtaTramite, List<OrganizacionSindical> listOrganizacionesSindicales) throws SystemException{
		for(OrganizacionSindical organizacionSindical: listOrganizacionesSindicales){
			organizacionSindical.setIdRegistroTramite(Long.parseLong(rtaTramite.getStrIdRegistroTramite()));
		}
		organizacionSindicalDAO.crearTodos(listOrganizacionesSindicales);
	}

	private void enviarNotificaciones(final Tramite tramite, final List<RespuestaRadicacionTramite> tramitados,
			final DatosSolicitudTramite solicitud) throws SystemException, BusinessException {

		String remitente = solicitud.getCorrespondencia().getRemitente().getCorreoElectronico();
		String tiempoGestionTramite = tramitesDAO.getTiempoGestionTramite(tramite.getId());

		final List<String> destinatarios = new ArrayList<String>();
		destinatarios.add(remitente);
		LOGGER.info("Preparando envio asincrono de notificaciones para tramite {} a cuenta {}", tramite.getNombre(),
				remitente, tiempoGestionTramite);
		if (tramite.getId() == 22) {
			notificacionesBroker.notificarCreacionTramitePracticas(tramite.getNombre(),
					tramitados,
					destinatarios,
					tiempoGestionTramite,
					solicitud.getStrJsonDatosBasicosPdf());
		} else {
			notificacionesBroker.notificarCreacionTramite(tramite.getNombre(), tramitados, destinatarios,
					tiempoGestionTramite);
		}
	}
	
	private String registrarDocumentoECM(DocumentoTramite documento,Tramite tramite,
			DatosSolicitudTramite solicitud){
		String uri="http://172.20.44.107:9080/appcmd/apiDocumentos/SubirDocumento";
		String idFilenet="";
		try {
			LOGGER.info("Inicio cargar documentos ECM");
			
//			String solicitud ="{\"CRP_fechaDocumento\":\"2021/10/17\","
//					+ "\"GCO_IdentificadorDocumento\":\""+documento.getIdDocumento()+"\","
//					+ "\"CRP_NroRadicado\":\""+documento.getNroRadicado()+"\","
//					+ "\"DocumentTitle\":\""+documento.getNombreDocumento()+"\","
//					+ "\"CRP_Doc64\":\""+documento.getBase64()+"\","
//					+ "\"CRP_Asunto\":\"""\","
//					+ "\"ActosAdministrativos\":[{\"nroActo\":\"-1\"}]},"
//					+ "\"GCO_CodDependencia\":\""+documento.getCodigoDependencia()+"\"}";
			
			String solicitudECM ="{\"peUser\":\"cpeadmin\","
					+ "\"pePassword\":\"Colombia2020**\","
					+ "\"cdUri\":\"http://mintrabgd01a:9080/wsi/FNCEWS40MTOM/\","
					+ "\"osName\":\"OS\",\"stanza\":\"FileNetP8WSI\","
					+ "\"document\":{\"CRP_fechaDocumento\":\""+solicitud.getCorrespondencia().getFechaRadicacion()+"\","
					+ "\"CRP_Asunto\":\""+tramite.getNombre()+"\","
					+ "\"CRP_Doc64\":\""+documento.getBase64()+"\","
					+ "\"GCO_IdentificadorDocumento\":\""+documento.getIdDocumento()+"\","
					+ "\"CRP_NroActoAdministrativo\":\"\","
					+ "\"CRP_DocumentoPrincipalAsociado\":\"\","
					+ "\"CRP_SnDocumentoPrincipal\":\"\","
					+ "\"CRP_Copia\":\"\","
					+ "\"CRP_NumeroFolios\":\"\","
					+ "\"CRP_TipoComunicacion\":\""+idTipoComunicacion+"\","
					+ "\"CRP_FechaRadicacion\":\""+solicitud.getCorrespondencia().getFechaRadicacion()+"\","
					+ "\"CRP_NroRadicado\":\""+documento.getNroRadicado()+"\","
					+ "\"DocumentTitle\":\""+documento.getNombreDocumento()+"\","
					+ "\"GCO_NomFolderAgrupador\":\"TemporalTramiteServicios\","
					+ "\"GCO_CodDependencia\":\""+documento.getCodigoDependencia()+"\","
					+ "\"ActosAdministrativos\":[{\"nroActo\":\"-1\"}]}}";
							
			
			URL url = new URL(uri);
			URLConnection conn = null;
			conn = url.openConnection();
			LOGGER.info("Conectando a... "+uri);
			LOGGER.info("######################### solicitud... "+solicitudECM);
	        HttpURLConnection httpConn = (HttpURLConnection) conn;
	        httpConn.setRequestMethod("POST");
	        httpConn.addRequestProperty("Content-Type","application/json");
	        String auth = "admin" + ":" + "admin";
	        byte[] encodedAuth = org.apache.commons.codec.binary.Base64.encodeBase64(auth.getBytes());
	        String authHeaderValue = "Basic " + new String(encodedAuth);
	        httpConn.setRequestProperty("Authorization", authHeaderValue);
	        conn.setDoInput(true);
	        conn.setDoOutput(true);
	        OutputStream os = conn.getOutputStream();
	        
	        BufferedWriter writer = new BufferedWriter(
	                new OutputStreamWriter(os, "UTF-8"));
	        LOGGER.info("Peticon ECM... "+solicitudECM);
	        writer.write(solicitudECM);
	        writer.flush();
	        writer.close();
	        os.close();
	        httpConn.connect();
	        if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
	        	BufferedReader in2 = new BufferedReader(new InputStreamReader(httpConn.getInputStream(),"UTF8"));
	            String inputLine;
	            String contenido = "";
	            while ((inputLine = in2.readLine()) != null) {
	                contenido += inputLine + "\n";
	            }
	            LOGGER.info("mensaje: "+contenido);
//	            JSONObject jsonObject =  (JSONObject) (Object)contenido;
	            JSONObject jsonObject = (JSONObject) JSONValue.parse(contenido);
	            LOGGER.info("ID Filenet Obtenido... "+(String)jsonObject.get("identDoc"));
	            idFilenet = (String)jsonObject.get("identDoc");
	            documento.setIdFilenet((String)jsonObject.get("identDoc"));
	            httpConn.disconnect();
	            in2.close();
	        }else {
	        	LOGGER.info("Error de conexi髇 "+httpConn.getResponseCode());
	        }
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return idFilenet;
	}

	public void actualizar(final ActualizarDocumentosTramite documentos) throws SystemException {
		List<String> documentosAdicionales = new ArrayList<String>();
		try {
			if (documentos.getListDocumentoTramite() != null) {
				for (DocumentoTramite documento : documentos.getListDocumentoTramite()) {
					if (documento.getBase64() != null) {

						LOGGER.info("Cargar documentos del tramite table procesar_mensajes_tys actualizacion : {}",
								documento);

						procesoMensajesDAO.crear(ProcesoMensajeTyS.newInstance()
								.codDependencia(documento.getCodigoDependencia())
								.numeroRadicado(documento.getNroRadicado()).documento(documento.getBase64().getBytes())
								.idTipoDocumental(documento.getTramiteTipologia()).codPlantilla(COR_PLANTILLA)
								.nombreDocumento(documento.getNombreDocumento()).idDocumento(0)
								.idFilenet(CheckUtil.checkNullString(documento.getIdFilenet())).origen(ORIGEN_UPLOAD)
								.estado(ESTADO_UPLOAD).fechaCreacion(new Date()).idUsuarioCrea(user).build());

						
						  SubirDocumentoActualizarRequest request =
						  SubirDocumentoActualizarRequest.newInstance()
						  .intTramiteTipologiaIn(documento.getTramiteTipologia(
						  )) .strBase64In(documento.getBase64())
						  .strCodigoDependenciaIn(documento.
						  getCodigoDependencia())
						  .strCorPlantillaIn(COR_PLANTILLA).
						  strNombreDocumentoIn(documento.getNombreDocumento())
						  .strIdFilenetIn(documento.getIdFilenet()).
						  strNroRadicadoIn(documento.getNroRadicado())
						  .intIdDocumentoIn(documento.getIdDocumento()).build()
						  ;
						  
						  // cargar doc tys -- table
						  SubirDocumentoActualizarResponse response =
						  tramitesECMBroker.subirDocumentoActualizar(request);
						  documentosAdicionales.add(documento.
						  getNombreDocumento()); if
						  (!response.getStrCodigoOut().equals("000")) {
						  LOGGER.error(
						  "Se produjo un error al actualizar los documentos del tr谩mite, intente de nuevo."
						  ); throw new BusinessException(
						  "Se produjo un error al actualizar los documentos del tr谩mite, intente de nuevo."
						  ); }
						 
					}
					if(documento.getIdDocumento()!=0 && documento.isOther()){
						procesoMensajesDAO.actualizarSubclasificaccionDocumentoNoCargado(documento);
					}
					else if(documento.getSubclasificacion()!=null && documento.getIdDocumento()!=0){
						procesoMensajesDAO.actualizarSubclasificaccionDocumento(documento);
					}
				}

				if (documentos.getListDocumentoAdicionales() != null) {
					for (DatosDocumentoAdicional documentoAdicional : documentos.getListDocumentoAdicionales()) {

						LOGGER.info("Cargar documentos del tramite table procesar_mensajes_tys actualizacion : {}",
								documentoAdicional);

						procesoMensajesDAO.crear(ProcesoMensajeTyS.newInstance()
								.codDependencia(documentoAdicional.getCodigoDependencia())
								.numeroRadicado(documentoAdicional.getNroRadicado())
								.documento(documentoAdicional.getBase64().getBytes())
								.idTipoDocumental(documentoAdicional.getTramiteTipologia()).codPlantilla(COR_PLANTILLA)
								.nombreDocumento(documentoAdicional.getNombreDocumento()).idDocumento(0)
								.idFilenet(CheckUtil.checkNullString(documentoAdicional.getIdFilenet()))
								.origen(ORIGEN_UPLOAD).estado(ESTADO_UPLOAD).fechaCreacion(new Date())
								.idUsuarioCrea(user).build());

						/*documentosAdicionalesDAO.crear(DocumentoAdicional.newInstance()
								.idResgistroTramite(documentoAdicional.getIdRegistraTramite())
								.idDocumentosProducido(1000L)
								// .idDocumentosProducido(Long.parseLong(response.getStrMensajeOut()))
								.fechaCreacion(new Date()).usuarioCrea(documentoAdicional.getUsuarioCreacion())
								.ip(WebUtil.getRemoteHost()).build());*/

						
						  SubirDocumentoActualizarRequest request =
						  SubirDocumentoActualizarRequest.newInstance()
						  .intTramiteTipologiaIn(documentoAdicional.
						  getTramiteTipologia())
						  .strBase64In(documentoAdicional.getBase64())
						  .strCodigoDependenciaIn(documentoAdicional.
						  getCodigoDependencia())
						  .strCorPlantillaIn(COR_PLANTILLA)
						  .strNombreDocumentoIn(documentoAdicional.
						  getNombreDocumento()) //
						  .strIdFilenetIn(documentoAdicional.getIdFilenet())
						  .strNroRadicadoIn(documentoAdicional.getNroRadicado()
						  )
						  .intIdDocumentoIn(documentoAdicional.getIdDocumento()
						  ).build(); SubirDocumentoActualizarResponse response
						  =
						  tramitesECMBroker.subirDocumentoActualizar(request);
						  if (response.getStrCodigoOut().equals("000")) {
						  
						  documentosAdicionalesDAO.crear(DocumentoAdicional.
						  newInstance() .idResgistroTramite(documentoAdicional.
						  getIdRegistraTramite())
						  .idDocumentosProducido(Long.parseLong(response.
						  getStrMensajeOut())) .fechaCreacion(new
						  Date()).usuarioCrea(documentoAdicional.
						  getUsuarioCreacion())
						  .ip(WebUtil.getRemoteHost()).build()); }
						 

						documentosAdicionales.add(documentoAdicional.getNombreDocumento());

					}
				}
				Long idRegistroTramite = null;
				String numeroRadicado = "";
				if (documentos.getListDocumentoTramite().size() != 0){
					idRegistroTramite = documentos.getListDocumentoTramite().get(0).getIdRegistraTramite();
					numeroRadicado = documentos.getListDocumentoTramite().get(0).getNroRadicado();
				}
				else{
					idRegistroTramite = documentos.getListDocumentoAdicionales().get(0).getIdRegistraTramite();
					numeroRadicado = documentos.getListDocumentoAdicionales().get(0).getNroRadicado();
				}

				//tramitesDAO.actualizarIndicadorReqActualizacionDoc(numeroRadicado);

				
				  ActualizarProcesoResponse actualizarProcesoResponse =
				  tramitesBpmBroker
				  .actuaizarProcesoBPM(ActualizarProcesoRequest.newInstance().
				  strId(numeroRadicado).build());
				  
				  tramitesDAO.actualizarIndicadorReqActualizacionDoc(numeroRadicado);
				  
				 /* tramitesDAO.actualizarIndicadorReqActualizacionDoc(
				 * numeroRadicado);*/
				  
				  if
				  (actualizarProcesoResponse.getCodigoRespuesta() != null && actualizarProcesoResponse.getCodigoRespuesta().equals("")) {
				  LOGGER.
				  error("Se produjo un error al actualizar los documentos del tr谩mite, intente de nuevo."
				  ); throw new BusinessException(
				  "Se produjo un error al actualizar los documentos del tr谩mite, intente de nuevo."
				  ); }
				 /* 
				 * else {
				 */

				//if(documentos.getListDocumentoAdicionales().size()>0 || documentos.getListDocumentoTramite().size()>0) {
					//Long idTramite = documentos.getListDocumentoAdicionales().size()>0 ? documentos.getListDocumentoAdicionales().get(0).getIdRegistraTramite() : documentos.getListDocumentoTramite().get(0).getIdRegistraTramite();
				InformacionNotificacionActTramite infNotificacion = tramitesDAO.getInfNotificarActualizacionTramite(
						//numeroRadicado, idTramite);
						numeroRadicado, idRegistroTramite);
				final List<String> destinatarios = new ArrayList<String>();
				destinatarios.add(infNotificacion.getCorreo());
				destinatarios.add(infNotificacion.getCorreoFuncinario());

				RespuestaRadicacionTramite tramitados = RespuestaRadicacionTramite.newInstance()
						.nombreDireccion(infNotificacion.getTerritorial())
						.numeroRadicado(infNotificacion.getNumeroRadicado())
						.codigoSeguridad(infNotificacion.getCodigoSeguridad())
						.documentosAdicionalesTramite(documentosAdicionales).build();

				enviarNotificacionActualizacion(infNotificacion.getTramite(), tramitados, destinatarios);
				//}

			}

		} catch (Exception e) {
			throw new SystemException("No se puedo actualizar los documentos del tramite." + e.getMessage(), e);
		}
	}

	private void enviarNotificacionActualizacion(final String tramite, final RespuestaRadicacionTramite tramitados,
			final List<String> destinatarios) throws SystemException {
		LOGGER.info("Preparando envio asincrono de notificaciones para actualizacion tramite {} a cuenta {}", tramite);
		notificacionesBroker.notificarActualizacionTramite(tramite, tramitados, destinatarios);
	}

}
