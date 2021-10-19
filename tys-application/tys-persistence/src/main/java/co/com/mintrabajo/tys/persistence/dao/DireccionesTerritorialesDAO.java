package co.com.mintrabajo.tys.persistence.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import co.com.mintrabajo.tys.commons.domain.DireccionTerritorial;
import co.com.mintrabajo.tys.commons.domain.InformacionDireccionTerritorial;
import co.com.mintrabajo.tys.commons.exceptions.BusinessException;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import co.com.mintrabajo.tys.persistence.Queries;
import co.com.mintrabajo.tys.persistence.mapper.DireccionesTerritorialesMapper;
import co.com.mintrabajo.tys.persistence.mapper.InformacionDireccionTerritorialMapper;

@Component
public class DireccionesTerritorialesDAO {

	private static final Logger LOGGER = LogManager.getLogger(DireccionesTerritorialesDAO.class);

	@Autowired
	private JdbcTemplate template;

	public List<DireccionTerritorial> listar() throws SystemException {
		try {
			LOGGER.info("listar direcciones territoriales");
			return template.query(Queries.LISTA_DIRECCIONES_TERRITORIALES,
					DireccionesTerritorialesMapper.newInstance());
		} catch (DataAccessException e) {
			throw new SystemException("Excepcion listando direcciones territoriales: " + e.getMessage(), e);
		}
	}

	public DireccionTerritorial getDireccionTerritoral(String id) throws BusinessException, SystemException {
		try {
			LOGGER.info("consultando direcciones territoriales por ID {} :", id);
			Object[] arguments = { id };
			return template.queryForObject(Queries.DIRECCION_TERRITORIAL_X_ID, arguments,
					DireccionesTerritorialesMapper.newInstance());
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("La direccion territoral solicitada no ha sido encontrado ", e);
			throw new BusinessException("La direccion territoral solicitada no ha sido encontrado");
		} catch (DataAccessException e) {
			throw new SystemException("Excepcion dereccion territorial por id: " + e.getMessage(), e);
		}

	}
	
	public InformacionDireccionTerritorial getDireccionTerritoralPaisDepar(Long idDireccionTerritoral) throws BusinessException, SystemException {
		try {
			LOGGER.info("consultando pais y departamento direcciones territoriales por ID {} :", idDireccionTerritoral);
			Object[] arguments = { idDireccionTerritoral };
			return template.queryForObject(Queries.DIRECCION_TERRITORIAL_PAIS_DEPART, arguments, 
					InformacionDireccionTerritorialMapper.newInstance());
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("El pais y departamento direccion territoral solicitada no ha sido encontrado ", e);
			throw new BusinessException("El pais y departamento direccion territoral solicitada no ha sido encontrado");
		} catch (DataAccessException e) {
			throw new SystemException("Excepcion el pais y departamento direccion territorial por id: " + e.getMessage(), e);
		}

	}

}
