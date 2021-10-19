package co.com.mintrabajo.tys.persistence.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import co.com.mintrabajo.tys.commons.domain.JustaCausa;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import co.com.mintrabajo.tys.persistence.Queries;

@Component

public class JustaCausaDAO {

	@Autowired
	private JdbcTemplate template;

	public void crearTodos(List<JustaCausa> listado) throws SystemException {
		try {
		for (JustaCausa justaCausa : listado) {
			Object[] arguments = { justaCausa.getIdRegistroTramite(), justaCausa.getIdJustasCausas(),
					justaCausa.getIdCausalDespido(), justaCausa.getObservacion() };
			template.update(Queries.INSERT_TYS_CAUSALES, arguments);
		}
		} catch (DataAccessException e) {
			System.out.println("Error insertando, usando SP INSERT_TYS_CAUSALES");
			throw new SystemException("Excepcion creando registo INSERT_TYS_CAUSALES: " + e.getMessage(), e);
		}
	}

}
