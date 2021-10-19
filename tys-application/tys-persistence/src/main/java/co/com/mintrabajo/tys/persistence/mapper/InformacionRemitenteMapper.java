package co.com.mintrabajo.tys.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import co.com.mintrabajo.tys.commons.domain.InformacionRemitente;

public class InformacionRemitenteMapper implements RowMapper<InformacionRemitente>  {
	
	private InformacionRemitenteMapper() {
	}

	public static InformacionRemitenteMapper newInstance() {
		return new InformacionRemitenteMapper();
	}

	@Override
	public InformacionRemitente mapRow(ResultSet rs, int rowNum) throws SQLException {
		return InformacionRemitente.newInstance().correo(rs.getString("CORREO")).build();
	}

}
