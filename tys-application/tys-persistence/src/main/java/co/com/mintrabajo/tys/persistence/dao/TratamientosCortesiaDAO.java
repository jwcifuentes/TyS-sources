package co.com.mintrabajo.tys.persistence.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import co.com.mintrabajo.tys.commons.domain.TratamientoCortesia;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import co.com.mintrabajo.tys.persistence.Queries;
import co.com.mintrabajo.tys.persistence.mapper.TratamientosCortesiaMapper;

@Component
public class TratamientosCortesiaDAO {
	
	private static final Logger LOGGER = LogManager.getLogger(TratamientosCortesiaDAO.class);

	@Autowired
	private JdbcTemplate template;
	
	public List<TratamientoCortesia> listar() throws SystemException {
		try {
			LOGGER.info("--------- listar tratamientos de cortesia");
			return template.query(Queries.LISTA_TRATAMIENTOS_CORTESIA, TratamientosCortesiaMapper.newInstance());
		} catch (DataAccessException e) {
			throw new SystemException("Excepcion listando de tratamientos de cortesia: " + e.getMessage(), e);
		}
	}

}
