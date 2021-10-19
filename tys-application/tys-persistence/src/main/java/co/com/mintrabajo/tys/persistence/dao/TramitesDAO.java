package co.com.mintrabajo.tys.persistence.dao;

import java.util.List;

import co.com.mintrabajo.tys.commons.domain.*;
import co.com.mintrabajo.tys.persistence.mapper.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import co.com.mintrabajo.tys.commons.exceptions.BusinessException;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import co.com.mintrabajo.tys.persistence.Queries;

/**
 * Created by jrodriguez on 06/11/2017.
 */

@Component
public class TramitesDAO {

	private static final Logger LOGGER = LogManager.getLogger(TramitesDAO.class);

	@Autowired
	private JdbcTemplate template;

	@Value("${solicitud.tramite.funcionario.responsable.cod}")
	private Long codFuncionarioResponsable;

	public List<Tramite> listar() throws SystemException {
		try {
			return template.query(Queries.LIST_TRAMITES, TramitesMapper.newInstance());
		} catch (DataAccessException e) {
			throw new SystemException("Excepcion listando tramites: " + e.getMessage(), e);
		}
	}

	public List<Tramite> listar(int estado) throws SystemException {
		try {
			Object[] arguments = {estado};
			List<Tramite> tramites = template.query(Queries.LIST_TRAMITES_ACTIVOS, arguments, TramitesMapper.newInstance());
			for (Tramite tramite : tramites){
				Object[] arg = {tramite.getId()};
				List<String> urls = template.queryForList(Queries.LIST_TRAMITES_URL,arg,String.class);
				tramite.setListURL(urls);
			}
			return tramites;
		} catch (DataAccessException e) {
			throw new SystemException("Excepcion listando tramites activos: " + e.getMessage(), e);
		}
	}

	public Tramite getTramite(final Long id) throws BusinessException, SystemException {
		try {
			Object[] arguments = { id };
			Tramite tramite = template.queryForObject(Queries.TRAMITE_SERVICIO_X_ID, arguments, TramitesMapper.newInstance());
			tramite.setUrlTramites(template.query(Queries.GET_LINKS_TRAMITE_BY_ID,arguments, UrlTramiteMapper.newInstance()));
			return tramite;
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("El tramite solicitado no ha sido encontrado ", e);
			throw new BusinessException("El tramite solicitado no ha sido encontrado");
		} catch (DataAccessException e) {
			throw new SystemException("Excepcion listando tramite por id: " + e.getMessage(), e);
		}
	}

	public String getTiempoGestionTramite(final Long id) throws BusinessException, SystemException {
		try {

			Object[] arguments = { id };
			return template.queryForObject(Queries.TIEMPO_GESTION_TRAMITE, arguments, String.class);

		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("El tramite solicitado no ha sido encontrado ", e);
			throw new BusinessException("El tramite solicitado no ha sido encontrado");
		} catch (DataAccessException e) {
			throw new SystemException("Excepcion listando tramite por id: " + e.getMessage(), e);
		}
	}

	public void crear(final Tramite tramite) throws SystemException {
		try {
			tramite.setPermiteActualizacion(true);
			Object[] arguments = { tramite.getNombre().toUpperCase(), false, tramite.getUsuarioCreacion(),
					tramite.getFechaCreacion(), tramite.isPermiteActualizacion() ? 1 : 0 };
			template.update(Queries.INSERT_TRAMITE, arguments);
		} catch (DataAccessException e) {
			throw new SystemException("Excepcion creando tramites: " + e.getMessage(), e);
		}
	}

	public void actualizar(final Tramite tramite) throws SystemException {
		try {
			Object[] arguments = { tramite.getNombre(), tramite.isTieneSustanciadores() ? 1 : 0,
					tramite.isRequiereConceptoSubInsp() ? 1 : 0, tramite.getIdReglaAsignacion(),
					tramite.getNmDireccionesPermitidas(), tramite.getTiempoGestionTramite(),
					tramite.getIdUnidadTiempo(), tramite.getIdTipoDocEmitido(),
					tramite.isEsTramiteSoloRecepcion() ? 1 : 0, tramite.isEstado() ? 1 : 0, tramite.getUsuariaCambio(),
					tramite.getFechaCambio(), tramite.isPermiteActualizacion() ? 1 : 0, tramite.getDescripcion(),
					tramite.getId() };
			//LOGGER.info(" ---- arguments --- : {}", arguments);
			template.update(Queries.UPDATE_TRAMITE, arguments);
			actualizarUrls(tramite);
		} catch (DataAccessException e) {
			throw new SystemException("Excepcion creando tramites: " + e.getMessage(), e);
		}

	}

	private void actualizarUrls(Tramite tramite) {
		if (tramite.getUrlTramites() == null) return;
		for (UrlTramite urlTramite: tramite.getUrlTramites()) {
			String query = "";
			Object [] args = null;
			if (urlTramite.getIdUrl() != null){
				//actualizar
				query = Queries.UPDATE_URL_TRAMITE;
				args = new Object[] {urlTramite.getUrl(), urlTramite.isEsVisible()? 1 : 0,urlTramite.getIdUrl(), urlTramite.getIdTramite()};
			} else {
				//crear
				args = new Object[] { urlTramite.getIdTramite(), urlTramite.getUrl(), urlTramite.isEsVisible()? 1 : 0};
				query = Queries.CREATE_URL_TRAMITE;
			}
			template.update(query,args);
		}
	}

	public void cambiarParametrizacionTramite(final Long idTramite) throws SystemException, BusinessException {
		try {
			Tramite tramite = getTramite(idTramite);
			Object[] arguments = { tramite.isPermiteActualizacion() ? 0 : 1, tramite.getId() };
			//LOGGER.info(" ---- arguments --- : {}", arguments);
			template.update(Queries.UPDATE_PARAMETRIZACION_TRAMITE, arguments);
		} catch (DataAccessException e) {
			throw new SystemException("Excepcion cambio de parametrizacion del tramite: " + e.getMessage(), e);
		}

	}

	public TramiteRadicado getConsultarTramiteRadicado(String numRadicado, String codSeguridad)
			throws BusinessException, SystemException {
		try {
			Object[] arguments = { numRadicado, codSeguridad };
			return template.queryForObject(Queries.CONSULTAR_TRAMITE_X_NUMERORAD_X_CODSEG, arguments,
					TramiteRadicadoMapper.newInstance());
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("El numero de radicado tramite solicitado no ha sido encontrado ", e);
			throw new BusinessException("El numero de radicado tramite solicitado no ha sido encontrado");
		} catch (DataAccessException e) {
			throw new SystemException("Excepcion listando tramite por id: " + e.getMessage(), e);
		}
	}

	// CONSULTAR tramite para relacionarlo
	public int getConsultarTramiteRadicadoARelacionar(String numRadicado) throws BusinessException, SystemException {
		try {
			Object[] arguments = { numRadicado };
			/*
			 * TramiteRadicado tramite = template.queryForObject(Queries.
			 * CONSULTAR_NUMERO_RADICADO_A_RELACIONAR, arguments,
			 * TramiteRadicadoMapper.newInstance());
			 * LOGGER.error("El valor del tramite " + tramite); if (tramite ==
			 * null) { return null; } else { return tramite; }
			 */

			int tramite = template.queryForObject(Queries.CONSULTAR_NUMERO_RADICADO_A_RELACIONAR, arguments,
					Integer.class);
			LOGGER.error("El valor del tramite " + tramite);
			// return (tramite == 0) ? "N" : "S";
			return tramite;
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("El numero de radicado tramite solicitado no ha sido encontrado ", e);
			throw new BusinessException("El numero de radicado tramite solicitado no ha sido encontrado");
		} catch (DataAccessException e) {
			throw new SystemException("Excepcion listando tramite por id: " + e.getMessage(), e);
		}
	}

	public InformacionNotificacionActTramite getInfNotificarActualizacionTramite(String numRadicado,
			Long numeroRegistro) throws BusinessException, SystemException {
		try {
			InformacionNotificacionActTramite informacion = InformacionNotificacionActTramite.newInstance().build();
			InformacionRemitente remitente = InformacionRemitente.newInstance().build();
			InformacionDestinatario destinatario = InformacionDestinatario.newInstance().build();

			Object[] arguments = { numRadicado };
			Object[] argumentsDestinatario = { numeroRegistro, codFuncionarioResponsable };

			informacion = template.queryForObject(Queries.INF_NOTIFICACION_ACTUALIZACION_TRAMITE, arguments,
					InformacionNotificacionActTramiteMapper.newInstance());
			remitente = template.queryForObject(Queries.CORREO_NOTIFICACION_ACTUALIZACION, arguments,
					InformacionRemitenteMapper.newInstance());
			destinatario = template.queryForObject(Queries.CORREO_NOTIFICACION_ACTUALIZACION_FUNCIONARIO,
					argumentsDestinatario, InformacionDestinatarioMapper.newInstance());
			informacion.setCorreo(remitente.getCorreo());
			informacion.setCorreoFuncinario(destinatario.getCorreo());

			return informacion;
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("La informacion del remitente para la notificacion no ha sido encontrada ", e);
			throw new BusinessException("La informacion del remitente para la notificacion no ha sido encontrada");
		} catch (DataAccessException e) {
			throw new SystemException(
					"Excepcion consultando informacion para notificacion por numero radicado: " + e.getMessage(), e);
		}
	}

	public DocumentoProducido getDocumentoProducido(final String numRadicado)
			throws BusinessException, SystemException {
		try {
			Object[] arguments = { numRadicado };
			return template.queryForObject(Queries.DOCUMENTO_PRODUCIDO, arguments,
					DocumentoProducidoMapper.newInstance());
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("No se han encontrado documentos producidos  para el tramite.", e);
			throw new BusinessException("No se han encontrado documentos producidos  para el tramite.");
		} catch (DataAccessException e) {
			throw new SystemException(
					"Excepcion consultado documentos producidos del tramite por numero radicado: " + e.getMessage(), e);
		}
	}

	public void actualizarIndicadorReqActualizacionDoc(String numeroRadicado)
			throws BusinessException, SystemException {
		try {
			Object[] arguments = { numeroRadicado };
			template.update(Queries.ACTUALIZAR_IND_REQ_ACTUALIZACION_DOC, arguments);
		} catch (DataAccessException e) {
			throw new SystemException(
					"Excepcion actializando ind require actualizacion doc tramites: " + e.getMessage(), e);
		}
	}

}
