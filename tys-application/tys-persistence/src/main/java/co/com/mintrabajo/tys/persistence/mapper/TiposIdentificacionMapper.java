package co.com.mintrabajo.tys.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import co.com.mintrabajo.tys.commons.domain.TipoIdentificacion;

public class TiposIdentificacionMapper implements RowMapper<TipoIdentificacion> {
	
	private TiposIdentificacionMapper() {
	}

	public static TiposIdentificacionMapper newInstance() {
		return new TiposIdentificacionMapper();
	}

	@Override
	public TipoIdentificacion mapRow(ResultSet rs, int rowNum) throws SQLException {
		return TipoIdentificacion.newInstance().id(rs.getLong("IDE_TIP_DOC_IDENT"))
				.nombre(rs.getString("DES_TIP_DOC_IDENT"))
				.codigo(rs.getString("COD_TIP_DOC_IDENT"))
				.build();
	}

}
