package co.com.mintrabajo.tys.persistence.dao;

import co.com.mintrabajo.tys.commons.domain.ActividadEconomica;
import co.com.mintrabajo.tys.commons.domain.Empresa;
import co.com.mintrabajo.tys.persistence.mapper.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import co.com.mintrabajo.tys.commons.domain.DatosEmpresa;
import co.com.mintrabajo.tys.commons.domain.Remitente;
import co.com.mintrabajo.tys.commons.exceptions.BusinessException;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import co.com.mintrabajo.tys.persistence.Queries;

@Component
public class EmpresasDAO {

	private static final Logger LOGGER = LogManager.getLogger(EmpresasDAO.class);

	@Autowired
	private JdbcTemplate template;

	public DatosEmpresa consultarInformacionDeEmpresa(final String nit) throws BusinessException, SystemException {

		DatosEmpresa datosEmpresa = new DatosEmpresa();
		try {
			LOGGER.info("consultar informacion de la empresa por nit {}", nit);
			Object[] arguments = { nit };
			datosEmpresa.setEmpresa(
					template.queryForObject(Queries.INFORMACION_EMPRESA, arguments, EmpresasMapper.newInstance()));
			datosEmpresa.setListaPersonas(
					template.query(Queries.INFORMACION_REPRESENTANTE_EMPRESA, arguments, PersonasMapper.newInstance()));
			datosEmpresa.setListaSocios(
					template.query(Queries.INFORMACION_SOCIOS_EMPRESA, arguments, SociosEmpresaMapper.newInstance()));
			datosEmpresa.setListaDireccionesSucursales(
					template.query(Queries.DIRECCION_SUCURSALES_EMPRESA, arguments, DireccionesMapper.newInstance()));
			datosEmpresa.getEmpresa().setGrupoActividadEconomica(getActividadEcomica(datosEmpresa.getEmpresa()));
			return datosEmpresa;
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("La informaci&oacute;n de la empresa solicitada no ha sido encontrada.", e);
			throw new BusinessException("La informaci&oacute;n de la empresa solicitada no ha sido encontrada.");
		} catch (DataAccessException e) {
			throw new SystemException("Excepcion consultar informacion de la empresa por nit : " + e.getMessage(), e);
		}
	}
	private ActividadEconomica getActividadEcomica(Empresa empresa) throws SystemException {
		ActividadEconomica actividadEconomica;
		LOGGER.info(String.format("Consultando la actividad economica %d de la empres con nit %s",
				empresa.getGrupoActividadEconomica().getIdActividadEconomica(),
				empresa.getNit()));
		try{
			Object[] argsActividadEconomica = {
					empresa.getGrupoActividadEconomica().getIdActividadEconomica()
			};
			actividadEconomica  = template.queryForObject(Queries.GET_ACTIVIDAD_ECONOMICA_BY_ID_ACTIVIDAD_ECONOMICA,argsActividadEconomica,
							ActividadEconomicaMapper.newInstance());

		}
		catch (IncorrectResultSizeDataAccessException ie){
			actividadEconomica = null;
		}
		catch (DataAccessException e){
			throw new SystemException("Excepcion al consultar la actividad economica de la mpes de la empresa: " + e.getMessage(), e);
		}
		return actividadEconomica;
	}
	public Remitente consultarInformacionRemitenteEmpresa(final String nit) throws BusinessException, SystemException {

		try {
			LOGGER.info("consultar informacion del remitente de la empresa por nit {}", nit);
			Object[] arguments = { nit };

			Remitente remitente = template.queryForObject(Queries.INFORMACION_REMITENTE_EMPRESA, arguments,
					RemitentesMapper.newInstance());
			remitente.setDireccion(
					template.queryForObject(Queries.DIRECCION_EMPRESA, arguments, DireccionesMapper.newInstance()));
			return remitente;
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("La informaci&oacute;n de la empresa solicitada no ha sido encontrada.", e);
			throw new BusinessException("La informaci&oacute;n de la empresa solicitada no ha sido encontrada.");
		} catch (DataAccessException e) {
			throw new SystemException("Excepcion consultar informacion de la empresa por nit : " + e.getMessage(), e);
		}
	}

	public void actualizarActividadEconomica(Long idRegistroTramite, Empresa empresa) throws SystemException {
		try {
			LOGGER.info("Actualizando actividad economica empresa {}", empresa);
			Object []  args = {empresa.getGrupoActividadEconomica().getIdActividadEconomica(),idRegistroTramite};
			template.update(Queries.UPDATE_ACTIVIDAD_ECONOMICA_EMPRESA,args);
		}
		catch (DataAccessException e) {
			System.out.println("Error update, usando SP UPDATE_ACTIVIDAD_ECONOMICA_EMPRESA");
			throw new SystemException("Excepcion al actualizar actividad economica : " + e.getMessage(), e);
		}
	}
}
