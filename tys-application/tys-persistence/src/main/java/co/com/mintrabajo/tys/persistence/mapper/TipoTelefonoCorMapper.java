package co.com.mintrabajo.tys.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import co.com.mintrabajo.tys.commons.domain.TipoTelefonoCor;

public class TipoTelefonoCorMapper implements RowMapper<TipoTelefonoCor>{
	
	private TipoTelefonoCorMapper() {
	}

	public static TipoTelefonoCorMapper newInstance() {
		return new TipoTelefonoCorMapper();
	}

	@Override
	public TipoTelefonoCor mapRow(ResultSet rs, int rowNum) throws SQLException {
		return TipoTelefonoCor.newInstance().id(rs.getLong("IDE_TIPO_TELEFONO"))
				.nombre(rs.getString("NOM_TIPO_TELEFONO"))
				.build();
	}

}
