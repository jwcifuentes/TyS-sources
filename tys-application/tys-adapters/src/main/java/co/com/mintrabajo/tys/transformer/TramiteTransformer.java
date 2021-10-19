package co.com.mintrabajo.tys.transformer;

import static co.com.mintrabajo.tys.commons.util.CheckUtil.checkNullDate;
import static co.com.mintrabajo.tys.commons.util.CheckUtil.checkNullLong;
import static co.com.mintrabajo.tys.commons.util.CheckUtil.checkNullLongToString;
import static co.com.mintrabajo.tys.commons.util.CheckUtil.checkNullString;
import static co.com.mintrabajo.tys.commons.util.CheckUtil.checkTransFormUTF8;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.mintrabajo.tys.commons.domain.DatosSolicitudTramite;
import co.com.mintrabajo.tys.commons.domain.JustaCausa;
import co.com.mintrabajo.tys.commons.domain.OrganizacionSindical;
import co.com.mintrabajo.tys.commons.domain.Parametro;
import co.com.mintrabajo.tys.commons.domain.Persona;
import co.com.mintrabajo.tys.commons.domain.SocioEmpresa;
import co.com.mintrabajo.tys.commons.util.DateUtil;
import co.com.mintrabajo.tys.commons.util.JSONUtil;
import co.com.mintrabajo.tys.commons.util.WebUtil;
import co.com.mintrabajo.tys.infrastructure.Transformer;
import co.com.mintrabajo.tys.json.domain.CondicionVinculacionTramite;
import co.com.mintrabajo.tys.json.domain.DatosTramitesServicios;
import co.com.mintrabajo.tys.json.domain.DireccionSucursal;
import co.com.mintrabajo.tys.json.domain.DireccionVinculacionTramite;
import co.com.mintrabajo.tys.json.domain.Empresa;
import co.com.mintrabajo.tys.json.domain.HorarioLaboralTramite;
import co.com.mintrabajo.tys.json.domain.OrganizacionSindicalTramite;
import co.com.mintrabajo.tys.json.domain.PersonaTramite;
import co.com.mintrabajo.tys.json.domain.Socio;
import co.com.mintrabajo.tys.json.domain.SolicitudTramite;
import co.com.mintrabajo.tys.json.domain.Trazabilidad;

@Component
public class TramiteTransformer implements Transformer<DatosSolicitudTramite, DatosTramitesServicios> {

	@Value("${solicitud.tramite.estado.id}")
	protected Long estadoTramite;
	@Value("${system.services.params.usuario_correspondencia}")
	protected String usuario;
	@Value("${solicitud.tramite.operacion}")
	protected String operacion;
	@Value("${solicitud.tramite.iniciar.proceso.bpm}")
	protected String nombreProceso;

	public DatosTramitesServicios transform(DatosSolicitudTramite data) {

		DatosTramitesServicios tramite = DatosTramitesServicios.newInstance().build();
	    List<Parametro> lstCondicionDiscapacidad = new ArrayList<Parametro>();

		SolicitudTramite objDatosTramite = SolicitudTramite.newInstance()
				.strIndicaEstaAdicionada(checkNullLong(data.getSolicitudTramite().getIndicaSiEstaAdicionada()))
				.strIdTramite(data.getSolicitudTramite().getIdTramite())
				.strNomProceso(nombreProceso)
				.strNumRadicado(data.getCorrespondencia().getNumeroRadicado())
				.strIdJustificacion(checkNullLong(data.getSolicitudTramite().getIdJustificacionSolicitud()))
				.strIdGradoAsociacion(checkNullLong(data.getSolicitudTramite().getIdGradoAsociacion()))
				.strIdTipoSolicitud(data.getSolicitudTramite().getIdTipoSolicitud())
				.strIdRadicaTramite(checkNullLong(data.getSolicitudTramite().getIdRadicaTramite()))
				.strValCircunstancias(checkNullString(data.getSolicitudTramite().getValCircunstancias()))
				.strValObjetoSolicitud(checkNullString(data.getSolicitudTramite().getValObjetoSolicitud()))
				.strIdJustaCausa(checkNullLong(data.getSolicitudTramite().getIdJustaCausa()))
				.datFechaVencimientoGestion(checkNullDate(data.getSolicitudTramite().getFechaVencimientoGestion()))
				.strIdEstadoTramite(estadoTramite)
				.strIndicaReqConceptoSDI(checkNullLong(data.getSolicitudTramite().getIndicaReqConceptoSDI()))
				.strValObservaciones(checkNullString(data.getSolicitudTramite().getValObservaciones()))
				.strValMotivoNiega(checkNullString(data.getSolicitudTramite().getValMotivoNiega()))
				.strIdConceptoTramite(checkNullLong(data.getSolicitudTramite().getIdConceptoTramite()))
				.strIndicaReqActualizacion(checkNullLong(data.getSolicitudTramite().getIndicaReqActualizacion()))
				.datFechaCrea(DateUtil.getDateToString(new Date())).build();

		if (data.getCondicionVinculacion() != null
				&& data.getCondicionVinculacion().getCondicionVinculacion() != null) {

			HorarioLaboralTramite objHorarioLaboral = HorarioLaboralTramite.newInstance().strTotalSemana(
					checkNullString(data.getCondicionVinculacion().getHorarioLaboral().getTotalSemanal()))

					.strDomingoInicioAM(
							checkNullString(data.getCondicionVinculacion().getHorarioLaboral().getDomingoInicioAM()))
					.strDomingoFinAM(
							checkNullString(data.getCondicionVinculacion().getHorarioLaboral().getDomingoFinAM()))
					.strDomingoInicioPM(
							checkNullString(data.getCondicionVinculacion().getHorarioLaboral().getDomingoInicioPM()))
					.strDomingoFinPM(
							checkNullString(data.getCondicionVinculacion().getHorarioLaboral().getDomingoFinPM()))
					.strDomingoTotal(
							checkNullString(data.getCondicionVinculacion().getHorarioLaboral().getDomingoTotal()))

					.strJuevesInicioAM(
							checkNullString(data.getCondicionVinculacion().getHorarioLaboral().getJuevesInicioAM()))
					.strJuevesFinAM(
							checkNullString(data.getCondicionVinculacion().getHorarioLaboral().getJuevesFinAM()))
					.strJuevesInicioPM(
							checkNullString(data.getCondicionVinculacion().getHorarioLaboral().getJuevesInicioPM()))
					.strJuevesFinPM(
							checkNullString(data.getCondicionVinculacion().getHorarioLaboral().getJuevesFinPM()))
					.strJuevesTotal(
							checkNullString(data.getCondicionVinculacion().getHorarioLaboral().getJuevesTotal()))

					.strViernesInicioAM(
							checkNullString(data.getCondicionVinculacion().getHorarioLaboral().getViernesInicioAM()))
					.strViernesFinAM(
							checkNullString(data.getCondicionVinculacion().getHorarioLaboral().getViernesFinAM()))
					.strViernesInicioPM(
							checkNullString(data.getCondicionVinculacion().getHorarioLaboral().getViernesInicioPM()))
					.strViernesFinPM(
							checkNullString(data.getCondicionVinculacion().getHorarioLaboral().getViernesFinPM()))
					.strViernesTotal(
							checkNullString(data.getCondicionVinculacion().getHorarioLaboral().getViernesTotal()))

					.strSabadoInicioAM(
							checkNullString(data.getCondicionVinculacion().getHorarioLaboral().getSabadoInicioAM()))
					.strSabadoFinAM(
							checkNullString(data.getCondicionVinculacion().getHorarioLaboral().getSabadoFinAM()))
					.strSabadoInicioPM(
							checkNullString(data.getCondicionVinculacion().getHorarioLaboral().getSabadoInicioPM()))
					.strSabadoFinPM(
							checkNullString(data.getCondicionVinculacion().getHorarioLaboral().getSabadoFinPM()))
					.strSabadoTotal(
							checkNullString(data.getCondicionVinculacion().getHorarioLaboral().getSabadoTotal()))

					.strLunesInicioAM(
							checkNullString(data.getCondicionVinculacion().getHorarioLaboral().getLunesInicioAM()))
					.strLunesFinAM(checkNullString(data.getCondicionVinculacion().getHorarioLaboral().getLunesFinAM()))
					.strLunesInicioPM(
							checkNullString(data.getCondicionVinculacion().getHorarioLaboral().getLunesInicioPM()))
					.strLunesFinPM(checkNullString(data.getCondicionVinculacion().getHorarioLaboral().getLunesFinPM()))
					.strLunesTotal(checkNullString(data.getCondicionVinculacion().getHorarioLaboral().getLunesTotal()))

					.strMartesInicioAM(
							checkNullString(data.getCondicionVinculacion().getHorarioLaboral().getMartesInicioAM()))
					.strMartesFinAM(
							checkNullString(data.getCondicionVinculacion().getHorarioLaboral().getMartesFinAM()))
					.strMartesInicioPM(
							checkNullString(data.getCondicionVinculacion().getHorarioLaboral().getMartesInicioPM()))
					.strMartesFinPM(
							checkNullString(data.getCondicionVinculacion().getHorarioLaboral().getMartesFinPM()))
					.strMartesTotal(
							checkNullString(data.getCondicionVinculacion().getHorarioLaboral().getMartesTotal()))

					.strMiercolesInicioAM(
							checkNullString(data.getCondicionVinculacion().getHorarioLaboral().getMiercolesInicioAM()))
					.strMiercolesFinAM(
							checkNullString(data.getCondicionVinculacion().getHorarioLaboral().getMiercolesFinAM()))
					.strMiercolesInicioPM(
							checkNullString(data.getCondicionVinculacion().getHorarioLaboral().getMiercolesInicioPM()))
					.strMiercolesFinPM(
							checkNullString(data.getCondicionVinculacion().getHorarioLaboral().getMiercolesFinPM()))
					.strMiercolesTotal(
							checkNullString(data.getCondicionVinculacion().getHorarioLaboral().getMiercolesTotal()))
					.build();

			List<DireccionVinculacionTramite> lstDireccionVinculacion = new ArrayList<DireccionVinculacionTramite>();
			lstDireccionVinculacion.add(DireccionVinculacionTramite.newInstance()
					.strCodigoPostal(
							checkNullString(data.getCondicionVinculacion().getDireccionVinculo().getCodigoPostal()))
					.strValDireccion(data.getCondicionVinculacion().getDireccionVinculo().getValDireccion())
					.strIdPais(data.getCondicionVinculacion().getDireccionVinculo().getIdPais())
					.strIdDepartamento(
							checkNullLong(data.getCondicionVinculacion().getDireccionVinculo().getIdDepartamento()))
					.strIdMunicipio(
							checkNullLong(data.getCondicionVinculacion().getDireccionVinculo().getIdMunicipio()))
					.strValCiudad(checkNullString(data.getCondicionVinculacion().getDireccionVinculo().getValCiudad()))
					.build());

			CondicionVinculacionTramite objCondVinculacion = CondicionVinculacionTramite.newInstance()
					.strIdCondicionVinculo(
							checkNullLong(data.getCondicionVinculacion().getCondicionVinculacion().getId()))
					.strIdTipoVinculo(data.getCondicionVinculacion().getCondicionVinculacion().getIdTipoVinculo())
					.strIdTipoActividad(data.getCondicionVinculacion().getCondicionVinculacion().getIdTipoActividad())
					.strTipoPago(data.getCondicionVinculacion().getCondicionVinculacion().getIdTipoPago())
					.decValRemuneracion(data.getCondicionVinculacion().getCondicionVinculacion().getValRemuneracion())
					.decValAuxilioTransporte(data.getCondicionVinculacion().getCondicionVinculacion().getValAuxTransp())
					.strValDescripcion(data.getCondicionVinculacion().getCondicionVinculacion().getValDescripcion())
					.strValCargo(data.getCondicionVinculacion().getCondicionVinculacion().getValCargo())
					.lstDireccionVinculacion(lstDireccionVinculacion).objHorarioLaboral(objHorarioLaboral).build();

			if (data.getCondicionVinculacion().getCondicionVinculacion() != null) {
				tramite.setObjCondVinculacion(objCondVinculacion);
			}

		}

		List<PersonaTramite> lstPersonas = new ArrayList<PersonaTramite>();

		if (data.getEmpresa().getListaPersonas() != null) {
			for (Persona p : data.getEmpresa().getListaPersonas()) {
				
				PersonaTramite persona	= PersonaTramite.newInstance().strIdTipoPersona(p.getIdTipoPersona())
						.strNombreCompleto(p.getNombreCompleto())
						.strIdTipoIdentificacion(p.getIdTipoIdentificacion())
						.strNumeroIdentificacion(p.getNumeroIdentificacion())
						.strIdTipoTelefono(checkNullLong(p.getIdTipoTelefono()))
						.strValTelefono(checkNullString(p.getValTelefono()))
						.strValExtension(checkNullString(p.getValExtension()))
						.strValIndicativo(checkNullString(p.getValIndicativo()))
						.strValMail(checkNullString(p.getValMail()))
						.strValCelular(checkNullString(p.getValCelular()))
						.strIdEdadNNA(checkNullLong(p.getValEdad()))
						.strUltimoGradoCursado(checkNullString(p.getUltimoGradoCursado()))
						.strParentesco(checkNullString(p.getParentesco()))
						.strNombre1(p.getPrimerNombre())
						.strNombre2(p.getSegundoNombre())
						.strApellido1(p.getPrimerApellido())
						.strApellido2(p.getSegundoApellido())
						.strFechaNacimiento(p.getFechaNacimiento() != null ? new java.sql.Date(p.getFechaNacimiento().getTime()) : null)
					    .strNombreInstitucionEducativa(p.getNombreInstitucionEducativa())
					    .strJornadaEscolar(p.getJornadaEscolar())
					    .strNumHijos(p.getCuantosHijos())
					    .strIdTipoGenero(p.getGenero())
						.strIdParentesco(p.getIdParentesco())
					    //.strCorreoElectronico(p.getCorreoElectronico())
					    .strIdTipoRegimen(p.getIdTipoRegimen())
					    .strNomRegimen(p.getNombreRegimen())	
					    .strTieneHijos(p.isTieneHijos()==true ? "1" : "0")
					    .strIdJornadaEscolar(p.getIdJornadaEscolar())
						.build();
				if(p.getIdTipoPersona().equals("NNA") && p.getCondicionDiscapacidad()!=null && p.getCondicionDiscapacidad().size()>0) {
					lstCondicionDiscapacidad = p.getCondicionDiscapacidad();
				}
				
				if(p.getDireccionPersona() != null){
					DireccionSucursal objDireccion = DireccionSucursal.newInstance()
							.strCodigoPostal(checkNullString(p.getDireccionPersona().getCodigoPostal()))
							.strValDireccion(p.getDireccionPersona().getValDireccion())
							.strIdPais(p.getDireccionPersona().getIdPais())
							.strIdDepartamento(checkNullLong(p.getDireccionPersona().getIdDepartamento()))
							.strIdMunicipio(checkNullLong(p.getDireccionPersona().getIdMunicipio()))
							.strValCiudad(checkNullString(p.getDireccionPersona().getValCiudad()))
							.strIdTipoUbicacion(p.getTipoUbicacion())
							.strNombreUbicacion(p.getNombreUbicacion())
							.strIdTipoZona(p.getTipoZona())
							.strNumDireccion(p.getDireccionPersona().getNumeroDireccion())
							.strNumVia(p.getDireccionPersona().getNumeroViaGeneradora())
							.strNumPlaca(p.getDireccionPersona().getNumeroPlaca())
							.strIdTipoVia(p.getDireccionPersona().getIdTipoVia())
							.strIdPrefijoCuadrante(p.getDireccionPersona().getIdPrejifoCuadrante())
							.build();
					persona.setObjDireccion(objDireccion);
				}
				lstPersonas.add(persona);
			}
			tramite.setLstPersonas(lstPersonas);
		}

		Empresa objEmpresa = Empresa.newInstance()
				.strIdSolicitante(checkNullLong(data.getEmpresa().getEmpresa().getIdSolicitante()))
				.strIdCiiu(checkNullLong(data.getEmpresa().getEmpresa().getIdCIIU()))
				.strNumeroEscritura(checkNullString(data.getEmpresa().getEmpresa().getNumeroEscritura()))
				.datFechaExpedicionEscritura(checkNullDate(data.getEmpresa().getEmpresa().getFechaExpedicionEscritura()))
				.strIdDepartamentoExpedicion(checkNullLongToString(data.getEmpresa().getEmpresa().getIdDepartamentoExpedicion()))
				.strIdMunicipioExpedicion(checkNullLongToString(data.getEmpresa().getEmpresa().getIdMunicipioExpedicion()))
				.strIdNotaria(checkNullLongToString(data.getEmpresa().getEmpresa().getIdNotaria()))
				.strNumeroPoliza(checkNullString(data.getEmpresa().getEmpresa().getNumeroPoliza()))
				.strValPoliza(checkNullLongToString(data.getEmpresa().getEmpresa().getValorPoliza()))
				.strNombreAseguradora(checkNullString(data.getEmpresa().getEmpresa().getNombreAseguradora()))
				.datFechaInicioPoliza(checkNullDate(data.getEmpresa().getEmpresa().getFechaInicialPoliza()))
				.datFechaFinPoliza(checkNullDate(data.getEmpresa().getEmpresa().getFechaFinPoliza()))
				.strIndicaNotificacionElec(data.getEmpresa().getEmpresa().isAutorizacionNotificacionElectronica() == true ? 1 : 0)
				.strIndicaCOFElectronica(data.getEmpresa().getEmpresa().isIdNotificacionElectronica() == true ? 1 : 0)
				.strIndicaConvPactosColectivos(data.getEmpresa().getEmpresa().isTieneConveniosColectivos() == true ? "1" :"0")
				.strIndicaObligReglaTrabajo(data.getEmpresa().getEmpresa().isTieneReglamentoTrabajo() == true ? "1" :"0")
				.strIndicaEmpresaSAS(data.getEmpresa().getEmpresa().isEsSAS() == true ? "1" :"0")
				.strIdTipoEmpresa(checkNullLong(data.getEmpresa().getEmpresa().getIdTipoEmpresa()))
				.strIdTipoEmpleador(checkNullLong(data.getEmpresa().getEmpresa().getIdTipoEmpleador()))
				.strIdTipoGestion(checkNullLong(data.getEmpresa().getEmpresa().getIdTipoGestion()))
				.strIdActEconomica(data.getEmpresa().getEmpresa().getGrupoActividadEconomica() != null ? data.getEmpresa().getEmpresa().getGrupoActividadEconomica().getIdActividadEconomica() : null)
				
				.strRazonSocial(data.getEmpresa().getDatosEmpleador()!=null ? data.getEmpresa().getDatosEmpleador().getNombreRazonSocial() : null)
				.strIdPersoneria(data.getEmpresa().getDatosEmpleador()!=null ? Long.parseLong(data.getEmpresa().getDatosEmpleador().getTipoEmpleador()) : null)
				.strNumeroIdentificacion(data.getEmpresa().getDatosEmpleador()!=null ? data.getEmpresa().getDatosEmpleador().getNroNit() : null)
				.strValCelular(data.getEmpresa().getDatosEmpleador()!=null ? data.getEmpresa().getDatosEmpleador().getCelular() : null)
				.strValTelefono(data.getEmpresa().getDatosEmpleador()!=null ? data.getEmpresa().getDatosEmpleador().getTelefono() : null)
				.strValRepresentanteLegal(data.getEmpresa().getDatosEmpleador()!=null ? data.getEmpresa().getDatosEmpleador().getNombreRepresentanteLegat() : null)
				.build();
		
		List<Socio> lstSocio = new ArrayList<Socio>();
		if (data.getEmpresa().getListaSocios() != null) {
			for (SocioEmpresa s : data.getEmpresa().getListaSocios()) {
				lstSocio.add(Socio.newInstance().strIdTipoPersona(s.getIdTipoPersona())
						.strIdTipoIdentificacion(s.getIdTipoIdentificacion())
						.strNumeroIdentificacion(s.getNumeroIdentificacion()).strNombreCompleto(s.getNombreCompleto())
						.strNombre1(s.getPrimerNombre())
						.strNombre2(s.getSegundoNombre())
						.strApellido1(s.getPrimerApellido())
						.strApellido2(s.getSegundoApellido())
						.build());
			}
			objEmpresa.setLstSocio(lstSocio);
		}
		
		if(data.getEmpresa().getDireccionSucursal() != null){
			DireccionSucursal objDireccionSucursal = DireccionSucursal.newInstance()
					.strCodigoPostal(checkNullString(data.getEmpresa().getDireccionSucursal().getCodigoPostal()))
					.strValDireccion(data.getEmpresa().getDireccionSucursal().getValDireccion())
					.strIdPais(data.getEmpresa().getDireccionSucursal().getIdPais())
					.strIdDepartamento(checkNullLong(data.getEmpresa().getDireccionSucursal().getIdDepartamento()))
					.strIdMunicipio(checkNullLong(data.getEmpresa().getDireccionSucursal().getIdMunicipio()))
					.strValCiudad(checkNullString(data.getEmpresa().getDireccionSucursal().getValCiudad()))					
					.strIdTipoUbicacion(null)
					.strNombreUbicacion(null)
					.strIdTipoZona(null)
					.strNumDireccion(data.getEmpresa().getDireccionSucursal().getNumeroDireccion())
					.strNumVia(data.getEmpresa().getDireccionSucursal().getNumeroViaGeneradora())
					.strNumPlaca(data.getEmpresa().getDireccionSucursal().getNumeroPlaca())
					.strIdTipoVia(data.getEmpresa().getDireccionSucursal().getIdTipoVia())
					.strIdPrefijoCuadrante(data.getEmpresa().getDireccionSucursal().getIdPrejifoCuadrante())					
					.build();
			objEmpresa.setObjDireccionSucursal(objDireccionSucursal);
		} else {
			if(data.getEmpresa().getDatosEmpleador()!=null) {				
				DireccionSucursal objDireccionEmpelador = DireccionSucursal.newInstance()
						.strCodigoPostal(null)
						.strValDireccion(data.getEmpresa().getDatosEmpleador().getDireccion())
						.strIdPais(1L)
						.strIdDepartamento(data.getEmpresa().getDatosEmpleador().getDepartamento())
						.strIdMunicipio(data.getEmpresa().getDatosEmpleador().getMunicipio())
						.strValCiudad(data.getEmpresa().getDatosEmpleador().getDireccion())					
						.strIdTipoUbicacion(null)
						.strNombreUbicacion(null)
						.strIdTipoZona(null)
						.strNumDireccion(null)
						.strNumVia(null)
						.strNumPlaca(null)
						.strIdTipoVia(null)
						.strIdPrefijoCuadrante(null)
						.strValCorreoElectronico(data.getEmpresa().getDatosEmpleador().getEmail())
						.build();				
				objEmpresa.setObjDireccionSucursal(objDireccionEmpelador);				
			}
		}

		Trazabilidad objTrazabilidad = Trazabilidad.newInstance().strUsuarioCrea(usuario)
				.strInstruccionRealizada(operacion).strDireccionIP(WebUtil.getRemoteAddr()).build();
	    
	    String strJsonPracticas = null;
	    if(data.getDatosEscenarioPractica() != null && data.getDatosVinculacion() != null && data.getDatosFormacion() != null) {
	    	Map<String,Object> jsonMap = new HashMap<>();
	        jsonMap.put("idRegTramite",null);
	        jsonMap.put("datosFormacion",data.getDatosFormacion());
	        jsonMap.put("datosEscenarioPractica",data.getDatosEscenarioPractica());
	        jsonMap.put("datosVinculacion",data.getDatosVinculacion());
	        try {
	            
	        	//strJsonPracticas = new ObjectMapper().writeValueAsString(jsonMap);
	        	strJsonPracticas = JSONUtil.marshal(jsonMap);
	        	strJsonPracticas = strJsonPracticas.replace("T05:00:00.000Z", "");
	        	
	        } catch (Exception e) {
				// TODO: handle exception
	        	strJsonPracticas = null;
			}
	    }
	    
	    List<OrganizacionSindicalTramite> lstOrganizaciones = new ArrayList<OrganizacionSindicalTramite>();
	    if(data.getListaOrganizacionesSindicales() != null && data.getListaOrganizacionesSindicales().size()>0) {
		    for(OrganizacionSindical org : data.getListaOrganizacionesSindicales()) {
		    	
		    	OrganizacionSindicalTramite organizacion	= OrganizacionSindicalTramite.newInstance().id(org.getId())
		    	.idRegistroTramite(org.getIdRegistroTramite())
		    	.nombreOrganizacion(org.getNombreOrganizacion())
		    	.sigla(org.getSigla())
		    	.numeroPersoneria(org.getNumeroPersoneria())
		    	.build();
		    	
		    	if(org.getAddress()!= null) {
		    		DireccionSucursal objDireccion = DireccionSucursal.newInstance()
							.strCodigoPostal(checkNullString(org.getAddress().getCodigoPostal()))
							.strValDireccion(org.getAddress().getValDireccion())
							.strIdPais(org.getAddress().getIdPais())
							.strIdDepartamento(checkNullLong(org.getAddress().getIdDepartamento()))
							.strIdMunicipio(checkNullLong(org.getAddress().getIdMunicipio()))
							.strValCiudad(checkNullString(org.getAddress().getValCiudad()))
							.strIdTipoUbicacion(null)
							.strNombreUbicacion(null)
							.strIdTipoZona(null)
							.strNumDireccion(org.getAddress().getNumeroDireccion())
							.strNumVia(org.getAddress().getNumeroViaGeneradora())
							.strNumPlaca(org.getAddress().getNumeroPlaca())
							.strIdTipoVia(org.getAddress().getIdTipoVia())
							.strIdPrefijoCuadrante(org.getAddress().getIdPrejifoCuadrante())
							.build();
		    		organizacion.setObjDireccion(objDireccion);		    		
		    	}
		    	lstOrganizaciones.add(organizacion);		    	
		    }
	    }

		tramite.setObjDatosTramite(objDatosTramite);
		tramite.setObjEmpresa(objEmpresa);
		tramite.setObjTrazabilidad(objTrazabilidad);
		tramite.setLstOrgSindicales(lstOrganizaciones);
		tramite.setLstJustasCausas(data.getListaJustaCausa()!=null && data.getListaJustaCausa().size()>0 ? data.getListaJustaCausa() : new ArrayList<JustaCausa>());
		tramite.setStrJsonPracticas(strJsonPracticas);
		tramite.setLstCondicionDiscapacidad(lstCondicionDiscapacidad);

		return tramite;
	}

}
