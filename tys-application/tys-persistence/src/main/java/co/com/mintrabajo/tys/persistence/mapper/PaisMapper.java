package co.com.mintrabajo.tys.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import co.com.mintrabajo.tys.commons.domain.Pais;

public class PaisMapper implements RowMapper<Pais> {

	private PaisMapper() {
	}

	public static PaisMapper newInstance() {
		return new PaisMapper();
	}

	@Override
	public Pais mapRow(ResultSet rs, int rowNum) throws SQLException {
		return Pais.newInstance().id(rs.getLong("IDE_PAIS"))
		.nombre(rs.getString("NOM_PAIS"))
		.codigo(rs.getString("COD_PAIS"))
		.build();
	}

}
