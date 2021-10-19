package co.com.mintrabajo.tys.persistence.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import co.com.mintrabajo.tys.commons.domain.Notaria;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import co.com.mintrabajo.tys.persistence.Queries;
import co.com.mintrabajo.tys.persistence.mapper.NotariasMapper;

@Component
public class NotariasDAO {

	private static final Logger LOGGER = LogManager.getLogger(NotariasDAO.class);

	@Autowired
	private JdbcTemplate template;
	
	public List<Notaria> listarNotariasPorMunicipio(Long id) throws SystemException {
		try {
			LOGGER.info("----- consultar notarias por municipios:", id);
			Object[] arguments = { id };
			return template.query(Queries.LISTA_NOTARIAS, arguments, NotariasMapper.newInstance());
		} catch (DataAccessException e) {
			throw new SystemException("Excepcion consultar notarias por municipios: " + e.getMessage(), e);
		}
	}


}
