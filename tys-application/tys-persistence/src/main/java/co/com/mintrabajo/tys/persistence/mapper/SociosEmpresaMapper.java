package co.com.mintrabajo.tys.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import co.com.mintrabajo.tys.commons.domain.SocioEmpresa;

public class SociosEmpresaMapper implements RowMapper<SocioEmpresa> {
	
	private SociosEmpresaMapper() {
	}

	public static SociosEmpresaMapper newInstance() {
		return new SociosEmpresaMapper();
	}

	@Override
	public SocioEmpresa mapRow(ResultSet rs, int rowNum) throws SQLException {
		return SocioEmpresa.newInstance().nombreCompleto(rs.getString("NOMBRE"))
				.idTipoIdentificacion(rs.getLong("IDE_TIP_IDENT"))
				.numeroIdentificacion(rs.getString("NUM_IDENTIDAD"))
				.idTipoPersona("SOCIO")
				.build();
	}

}
