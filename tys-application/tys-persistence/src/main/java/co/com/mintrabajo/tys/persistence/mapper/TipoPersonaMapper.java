package co.com.mintrabajo.tys.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import co.com.mintrabajo.tys.commons.domain.TipoPersona;

public class TipoPersonaMapper implements RowMapper<TipoPersona> {

	private TipoPersonaMapper() {
	}

	public static TipoPersonaMapper newInstance() {
		return new TipoPersonaMapper();
	}

	@Override
	public TipoPersona mapRow(ResultSet rs, int rowNum) throws SQLException {
		return TipoPersona.newInstance()
				.id(rs.getLong("IDE_PER_REMITENTE"))
				.nombre(rs.getString("DES_PERSONA_REMITENT"))
				.build();
	}

}
