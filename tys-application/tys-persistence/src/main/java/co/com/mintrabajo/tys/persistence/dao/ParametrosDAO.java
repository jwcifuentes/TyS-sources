package co.com.mintrabajo.tys.persistence.dao;

import java.util.List;

import co.com.mintrabajo.tys.commons.domain.*;
import co.com.mintrabajo.tys.commons.exceptions.BusinessException;
import co.com.mintrabajo.tys.persistence.mapper.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import co.com.mintrabajo.tys.commons.domain.ActuaEnCalidad;
import co.com.mintrabajo.tys.commons.domain.CausalDespido;
import co.com.mintrabajo.tys.commons.domain.JustaCausa;
import co.com.mintrabajo.tys.commons.domain.Parametro;
import co.com.mintrabajo.tys.commons.domain.TipoIdentificacion;
import co.com.mintrabajo.tys.commons.domain.TipoPersona;
import co.com.mintrabajo.tys.commons.domain.TipoTelefonoCor;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import co.com.mintrabajo.tys.persistence.Queries;
import co.com.mintrabajo.tys.persistence.mapper.ActuaEnCalidadMapper;
import co.com.mintrabajo.tys.persistence.mapper.CausalDespidoMapper;
import co.com.mintrabajo.tys.persistence.mapper.JustaCausaMapper;
import co.com.mintrabajo.tys.persistence.mapper.ParametrosMapper;
import co.com.mintrabajo.tys.persistence.mapper.TipoPersonaMapper;
import co.com.mintrabajo.tys.persistence.mapper.TipoTelefonoCorMapper;
import co.com.mintrabajo.tys.persistence.mapper.TiposIdentificacionMapper;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import co.com.mintrabajo.tys.persistence.Queries;

/**
 * Created by jrodriguez on 07/11/2017.
 */

@Component
public class ParametrosDAO {

	private static final Logger LOGGER = LogManager.getLogger(ParametrosDAO.class);

	@Autowired
	private JdbcTemplate template;

	public List<Parametro> listarParametrosDelPadre(final Parametro padre) throws SystemException {
		try {
			Object[] arguments = { padre.getCodigo(), padre.isEstado() };
			return template.query(Queries.LISTA_CONSTANTES_X_CODPADRE_ESTADO, arguments,
					ParametrosMapper.newInstance());
		} catch (DataAccessException e) {
			throw new SystemException("Excepcion consultado parametros: " + e.getMessage(), e);
		}
	}

	public List<TipoIdentificacion> listarTiposIdentificacion() throws SystemException {
		try {
			LOGGER.info(" ----- consultar tipos de identificacion");
			return template.query(Queries.LISTA_TIPOS_IDENTIFICACION, TiposIdentificacionMapper.newInstance());
		} catch (DataAccessException e) {
			throw new SystemException("Excepcion consultado lista tipos de identificacion: " + e.getMessage(), e);
		}
	}

	public List<TipoIdentificacion> listarTiposIdentificacionNNA() throws SystemException {
		try {
			LOGGER.info(" ----- consultar tipos de identificacion");
			return template.query(Queries.LISTA_TIPOS_IDENTIFICACION_NNA, TiposIdentificacionMapper.newInstance());
		} catch (DataAccessException e) {
			throw new SystemException("Excepcion consultado lista tipos de identificacion: " + e.getMessage(), e);
		}
	}

	public List<TipoPersona> listarTiposPersona() throws SystemException {

		try {
			LOGGER.info(" ----- consultar tipos de persona correspondencia");
			return template.query(Queries.LISTA_TIPO_PERSONA, TipoPersonaMapper.newInstance());
		} catch (DataAccessException e) {
			throw new SystemException("Excepcion tipos de persona correspondencia : " + e.getMessage(), e);
		}

	}

	public List<ActuaEnCalidad> listarActuaEnCalidad() throws SystemException {
		try {
			LOGGER.info(" ----- consultar actua en calidad ");
			return template.query(Queries.LISTA_ACTUA_CALIDAD, ActuaEnCalidadMapper.newInstance());
		} catch (DataAccessException e) {
			throw new SystemException("Excepcion consultado actua en calidad : " + e.getMessage(), e);
		}

	}

	public List<TipoTelefonoCor> listarTipoTelefonoCor() throws SystemException {
		try {
			LOGGER.info(" ----- consultar tipos de telefono correspondencia  ");
			return template.query(Queries.LISTA_TIPO_TELEFONO, TipoTelefonoCorMapper.newInstance());
		} catch (DataAccessException e) {
			throw new SystemException("Excepcion consultar  tipos de telefono correspondencia : " + e.getMessage(), e);
		}
	}

	public List<JustaCausa> listarCausas() throws SystemException {
		try {
			// Object[] arguments = { padre.getCodigo(), padre.isEstado() };
			return template.query(Queries.LISTA_JUSTA_CAUSA, JustaCausaMapper.newInstance());
		} catch (DataAccessException e) {
			throw new SystemException("Excepcion consultado parametros: " + e.getMessage(), e);
		}
	}

	public List<CausalDespido> listarCuasaDespido() throws SystemException {
		try {
			return template.query(Queries.LISTA_CAUSAL_DESPIDO, CausalDespidoMapper.newInstance());
		} catch (DataAccessException e) {
			throw new SystemException("Excepcion consultado parametros: " + e.getMessage(), e);
		}
	}
	
	public List<JustaCausa> listarJustaCausaCausalD(int idCausalDespido) throws SystemException {
		try {
			Object[] arguments = { idCausalDespido };
			return template.query(Queries.LISTA_ID_JUSTA_CAUSA, arguments,JustaCausaMapper.newInstance());
		} catch (DataAccessException e) {
			throw new SystemException("Excepcion consultado parametros: " + e.getMessage(), e);
		}
	}
	public List<TipoIdentificacion> listarTiposIdentificacionNNARepresentante() throws SystemException {
		try {
			LOGGER.info(" ----- consultar tipos de identificacion");
			return template.query(Queries.LISTA_TIPOS_IDENTIFICACION_NNA_REPRESENTANTE, TiposIdentificacionMapper.newInstance());
		} catch (DataAccessException e) {
			throw new SystemException("Excepcion consultado lista tipos de identificacion: " + e.getMessage(), e);
		}
	}

    public List<ActividadEconomica> listaActividadEconomica(String divisionGrupo) throws SystemException, BusinessException {
		try {
			LOGGER.info(" ----- consultando actividades economicas");
			if(divisionGrupo.equals("ACTIVIDAD_DIVISION")){
				return template.query(Queries.LIST_ACTIVIDAD_ECONOMICA_DIVISION,ActividadEconomicaMapper.newInstance());
			}
			Object [] args = {Long.parseLong(divisionGrupo)};
			return template.query(Queries.LIST_ACTIVIDAD_ECONOMICA_GRUPOS_DIVISION,args, ActividadEconomicaMapper.newInstance());
		}
		catch (NumberFormatException n){
			throw new BusinessException("Se esperaba el numero de grupo (Long)" + n.getMessage(),n.getCause());
		}
		catch (DataAccessException e) {
			throw new SystemException("Excepcion consultado actividades economicas: " + e.getMessage(), e);
		}
    }

	public List<TipoIdentificacion> listarTiposIdentificacionIn(List<String> codigos) {
		return null;
	}
}
