package co.com.mintrabajo.tys.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import co.com.mintrabajo.tys.commons.domain.InformacionDestinatario;

public class InformacionDestinatarioMapper implements RowMapper<InformacionDestinatario> {

	private InformacionDestinatarioMapper() {
	}

	public static InformacionDestinatarioMapper newInstance() {
		return new InformacionDestinatarioMapper();
	}

	@Override
	public InformacionDestinatario mapRow(ResultSet rs, int rowNum) throws SQLException {
		return InformacionDestinatario.newInstance()
				.nombre(rs.getString("NOM_FUNCIONARIO"))
				.correo(rs.getString("VAL_CORR_ELECTRONICO")).build();
	}

}
