package co.com.mintrabajo.tys.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import co.com.mintrabajo.tys.commons.domain.Remitente;

public class RemitentesMapper implements RowMapper<Remitente> {
	
	private RemitentesMapper() {
	}

	public static RemitentesMapper newInstance() {
		return new RemitentesMapper();
	}

	@Override
	public Remitente mapRow(ResultSet rs, int rowNum) throws SQLException {
		return Remitente.newInstance().idtipoPersona(2l)
				.idtipoDocumentoIdentidad(8l)
				.nit(rs.getString("NIT"))
				.razonSocial(rs.getString("NOM_RAZON_SOCIAL"))
				.idenCalidadDe(3l)
				.nombre(rs.getString("NOMBRE_REP"))
				.idtipoTelefono(2l)
				.indicativo(rs.getLong("VAL_INDICATIVO_REP"))
				.telefono(rs.getString("VAL_TELEFONO_REP"))
				.extension(rs.getLong("VAL_EXTENSION_REP"))
				.correoElectronico(rs.getString("VAL_MAIL_REP"))
				.build();
	}

}
