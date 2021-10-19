package co.com.mintrabajo.tys.persistence.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import co.com.mintrabajo.tys.commons.domain.Pais;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import co.com.mintrabajo.tys.persistence.Queries;
import co.com.mintrabajo.tys.persistence.mapper.PaisMapper;

@Component
public class PaisDAO {
	
	private static final Logger LOGGER = LogManager.getLogger(PaisDAO.class);
	
	@Autowired
	private JdbcTemplate template;
	
	public List<Pais> listarPaises() throws SystemException{
		try {
			LOGGER.info(" ----- consultar  paises -------");
			return template.query(Queries.LISTA_PAISES, PaisMapper.newInstance());
		} catch (DataAccessException e) {
			throw new SystemException("Excepcion consultado lista de paises: " + e.getMessage(), e);
		}
	}

}
