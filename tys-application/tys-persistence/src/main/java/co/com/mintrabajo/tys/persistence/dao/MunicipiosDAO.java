package co.com.mintrabajo.tys.persistence.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import co.com.mintrabajo.tys.commons.domain.Municipio;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import co.com.mintrabajo.tys.persistence.Queries;
import co.com.mintrabajo.tys.persistence.mapper.MunicipiosMapper;

@Component
public class MunicipiosDAO {

	private static final Logger LOGGER = LogManager.getLogger(MunicipiosDAO.class);

	@Autowired
	private JdbcTemplate template;

	public List<Municipio> listarMunicipiosPorDepart(Long id) throws SystemException {
		try {
			LOGGER.info("----- consultar municipios por departamento -----");
			Object[] arguments = { id };
			return template.query(Queries.LISTA_MUNICIPIOS_POR_DEPART, arguments, MunicipiosMapper.newInstance());
		} catch (DataAccessException e) {
			throw new SystemException("Excepcion consultar municipios por departamento: " + e.getMessage(), e);
		}
	}


}
