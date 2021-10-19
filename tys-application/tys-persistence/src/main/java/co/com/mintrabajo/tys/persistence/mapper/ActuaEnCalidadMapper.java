package co.com.mintrabajo.tys.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import co.com.mintrabajo.tys.commons.domain.ActuaEnCalidad;

public class ActuaEnCalidadMapper implements RowMapper<ActuaEnCalidad> {

	private ActuaEnCalidadMapper() {
	}

	public static ActuaEnCalidadMapper newInstance() {
		return new ActuaEnCalidadMapper();
	}
	
	@Override
	public ActuaEnCalidad mapRow(ResultSet rs, int rowNum) throws SQLException {
		return ActuaEnCalidad.newInstance().id(rs.getLong("IDE_EN_CALIDAD"))
				.nombre(rs.getString("NOM_CALIDAD"))
				.build();
	}

}
