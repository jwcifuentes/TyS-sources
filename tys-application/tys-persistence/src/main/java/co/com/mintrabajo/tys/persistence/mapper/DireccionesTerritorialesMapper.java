package co.com.mintrabajo.tys.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import co.com.mintrabajo.tys.commons.domain.DireccionTerritorial;

public class DireccionesTerritorialesMapper implements RowMapper<DireccionTerritorial> {

	private DireccionesTerritorialesMapper() {
	}

	public static DireccionesTerritorialesMapper newInstance() {
		return new DireccionesTerritorialesMapper();
	}

	@Override
	public DireccionTerritorial mapRow(ResultSet rs, int rowNum) throws SQLException {
		return DireccionTerritorial.newInstance().id(rs.getLong("IDE_SUBFONDO"))
				.nombre(rs.getString("NOM_SUBFONDO"))
				.codigo(rs.getString("COD_SUBFONDO"))
				.estado(rs.getBoolean("IDE_FONDO"))
				.build();
	}

}
