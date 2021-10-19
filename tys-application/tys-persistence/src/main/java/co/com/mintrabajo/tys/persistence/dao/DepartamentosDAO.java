package co.com.mintrabajo.tys.persistence.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import co.com.mintrabajo.tys.commons.domain.Departamento;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import co.com.mintrabajo.tys.persistence.Queries;
import co.com.mintrabajo.tys.persistence.mapper.DepartamentosMapper;

@Component
public class DepartamentosDAO {

	private static final Logger LOGGER = LogManager.getLogger(DepartamentosDAO.class);
	
	@Autowired
	private JdbcTemplate template;

	public List<Departamento> listarDepartaentossPorPais(Long id) throws SystemException {
		try {
			LOGGER.info("----- consultar departamentos por pais -----");
			Object[] arguments = { id };
			return template.query(Queries.LISTA_DEPARTAMENTOS_POR_PAIS, arguments, DepartamentosMapper.newInstance());
		} catch (DataAccessException e) {
			throw new SystemException("Excepcion consultado lista de departamentos: " + e.getMessage(), e);
		}
	}

}
