package co.com.mintrabajo.tys.mvc;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import co.com.mintrabajo.tys.adapters.JavaMailBroker;
import co.com.mintrabajo.tys.adapters.TramitesBPMBroker;
import co.com.mintrabajo.tys.commons.domain.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import co.com.mintrabajo.tys.boundary.CrearSolicitudTramite;
import co.com.mintrabajo.tys.boundary.ObtenerDocumentoPorIdFilenet;
import co.com.mintrabajo.tys.boundary.ObtenerDocumentosTramites;
import co.com.mintrabajo.tys.boundary.SubirDocumentoTramites;
import co.com.mintrabajo.tys.commons.exceptions.BusinessException;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import co.com.mintrabajo.tys.mvc.util.URLSecuritySchema;
import co.com.mintrabajo.tys.persistence.dao.DocumentosAdicionalesDAO;
import co.com.mintrabajo.tys.persistence.dao.EmpresasDAO;
import co.com.mintrabajo.tys.persistence.dao.TramitesDAO;

@RestController
@RequestMapping(value = URLSecuritySchema.GESTIONAR_TRAMITES_SERVICIOS_PUBLIC, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=*/*")
public class GestionarTramitesServiciosWebController {
	private static final Logger LOGGER = LogManager.getLogger(GestionarTramitesServiciosWebController.class);

	@Autowired
	private CrearSolicitudTramite crearSolicitudTramite;

	@Autowired
	private SubirDocumentoTramites subirDocumentoTramites;

	@Autowired
	private TramitesDAO tramitesDAO;

	@Autowired
	private ObtenerDocumentosTramites obtenerDocumentosTramites;

	@Autowired
	private ObtenerDocumentoPorIdFilenet obtenerDocumentoPorIdFilenet;

	@Autowired
	private DocumentosAdicionalesDAO documentosAdicionales;

	@Autowired
	private EmpresasDAO empresas;

	@Autowired
	private TramitesBPMBroker tramitesBPMBroker;


	public GestionarTramitesServiciosWebController() {
		super();
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> crear(@RequestBody final DatosSolicitudTramite solicitud)
			throws SystemException, BusinessException, UnsupportedEncodingException {
		LOGGER.info("crear solicitud de tramites y servicios web  {solicitud} :", solicitud);
		return ResponseEntity.ok().body(crearSolicitudTramite.procesar(solicitud));
	}
	
	@RequestMapping(value = "/subirDocumentoActualizar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> subirDocumentoActualizar(@RequestBody final DocumentoTramite documento)
			throws SystemException, BusinessException {
		LOGGER.info("subir documento de tramites y servicios web  {documento} :", documento);
		subirDocumentoTramites.procesar(documento);
		return ResponseEntity.ok().build();
	}

	// Consulta de tramites por numero de radicado y codigo se seguridad
	@RequestMapping(value = "/{numero}/{codigo}", method = RequestMethod.GET)
	public ResponseEntity<?> getRadicadoTramite(@PathVariable("numero") final String numero,
			@PathVariable("codigo") final String codigo) throws SystemException, BusinessException {
		LOGGER.info("consultando radicado tramite por numero - codigo seguridad {}", numero + " --- " + codigo);
		return ResponseEntity.ok().body(tramitesDAO.getConsultarTramiteRadicado(numero, codigo));
	}

	// Consulta de tramites por numero de radicado a relacionar
	@RequestMapping(value = "nradicadoARelacionar/{numero}", method = RequestMethod.GET)
	public ResponseEntity<?> getRadicadoTramiteARelacionar(@PathVariable("numero") final String numero) throws SystemException, BusinessException {
		LOGGER.info("consultando radicado tramite por numero  {}", numero + " --- ");
		return ResponseEntity.ok().body(tramitesDAO.getConsultarTramiteRadicadoARelacionar(numero));
	}

	@RequestMapping(value = "/obtenerDatosDocumento/{nroRadicado}", method = RequestMethod.GET)
	public ResponseEntity<?> obtenerDatosDocumento(@PathVariable("nroRadicado") final String nroRadicado)
			throws SystemException, BusinessException {
		LOGGER.info("obtener documentos de tramites {nroRadicado} :", nroRadicado);
		List<DocumentoTramite> documentos = obtenerDocumentosTramites.procesar(nroRadicado);
		return ResponseEntity.ok().body(documentos);
	}

	@RequestMapping(value = "/documentosTramite", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> documentosPorTramite(@RequestBody final FiltrosTiposDocumentales filtros)
			throws SystemException, BusinessException {
		LOGGER.info("obtener documentos del tramites con filtros documentales :", filtros);
		List<DocumentosTramiteYServicio> documentos = obtenerDocumentosTramites.listarDocumentosPorTramite(filtros);
		return ResponseEntity.ok().body(documentos);
	}

	@RequestMapping(value = "/byteDocumentosTramite/{idFilenet}", method = RequestMethod.GET)
	public ResponseEntity<?> getByteDocumentosTramite(@PathVariable final String idFilenet)
			throws SystemException, BusinessException {
		LOGGER.info("obtener documentos del tramites con filtros documentales :", idFilenet);
		DocumentoTramite documentos = obtenerDocumentoPorIdFilenet.procesar(idFilenet);
		return ResponseEntity.ok().body(documentos);
	}

	@RequestMapping(value = "/actualizarDocumentos", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> actualizarDocumentosPorTramite(@RequestBody final ActualizarDocumentosTramite documentos)
			throws SystemException {
		LOGGER.info("Actualizando documentos tramite",documentos);
		boolean isOk = documentos!=null;
		if(isOk){
			if(documentos.getListDocumentoTramite()!=null && documentos.getListDocumentoAdicionales()!=null){
				isOk = documentos.getListDocumentoAdicionales().size() > 0 || documentos.getListDocumentoTramite().size() > 0;
			}
			else if(documentos.getListDocumentoTramite()!=null){
				isOk = documentos.getListDocumentoTramite().size() > 0;
			}
			else {
				isOk = documentos.getListDocumentoAdicionales().size() > 0;
			}
		}
		if(!isOk){
			LOGGER.info("No hay nada para actualizar");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		crearSolicitudTramite.actualizar(documentos);
		return ResponseEntity.ok().body(documentos);
	}

	@RequestMapping(value = "/obtenerDatosDocumentoProducido/{nroRadicado}", method = RequestMethod.GET)
	public ResponseEntity<?> obtenerDatosDocumentoProducido(@PathVariable("nroRadicado") final String nroRadicado)
			throws SystemException, BusinessException {
		LOGGER.info("obtener documento producido por el tramites {nroRadicado} :", nroRadicado);
		return ResponseEntity.ok().body(tramitesDAO.getDocumentoProducido(nroRadicado));
	}

	@RequestMapping(value = "/documentosAdicionales/{nroRadicado}", method = RequestMethod.GET)
	public ResponseEntity<?> listarDocumentosAdicionalesTramite(@PathVariable("nroRadicado") final String nroRadicado)
			throws SystemException, BusinessException {
		LOGGER.info("obtener documento producido por el tramites {nroRadicado} :", nroRadicado);
		return ResponseEntity.ok().body(documentosAdicionales.listarDocumentosAdicioanesTramite(nroRadicado));
	}

	@RequestMapping(value = "/empresa/{nit}", method = RequestMethod.GET)
	public ResponseEntity<?> consultarInfEmpresaPorNit(@PathVariable final String nit)
			throws BusinessException, SystemException {
		LOGGER.info(" consultar Informacion de la empresa por nit {nit} :", nit);
		return ResponseEntity.ok().body(empresas.consultarInformacionDeEmpresa(nit));
	}

	@RequestMapping(value = "/empresa/remitente/{nit}", method = RequestMethod.GET)
	public ResponseEntity<?> consultarInfRemitenteEmpresaPorNit(@PathVariable final String nit)
			throws BusinessException, SystemException {
		LOGGER.info(" consultar Informacion del remitente de la empresa por nit {nit} :", nit);
		return ResponseEntity.ok().body(empresas.consultarInformacionRemitenteEmpresa(nit));
	}
	@PostMapping(value = "/generate-pdf",consumes = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<InputStreamResource> generarPdfPracticasAdolecente(@RequestBody String jsonVal) throws IOException {
		jsonVal = jsonVal.replace("T05:00:00.000Z", "");
		byte[] ptext = jsonVal.getBytes("ISO-8859-1"); 
		String value = new String(ptext, "UTF-8");
		System.out.println("Json del reporte: "+value);
		Response response = tramitesBPMBroker.fetchPdfPracticasAdolecente(value);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-disposition",response.header("Content-disposition"));
		headers.add("Content-Type",response.header("Content-Type"));
		return ResponseEntity.ok().headers(headers)
				.contentLength(response.body().contentLength())
				.body(new InputStreamResource(response.body().byteStream()));
	}
}
