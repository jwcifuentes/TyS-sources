package co.com.mintrabajo.tys.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import co.com.mintrabajo.tys.commons.domain.Persona;

public class PersonasMapper implements RowMapper<Persona> {

	private PersonasMapper() {
	}

	public static PersonasMapper newInstance() {
		return new PersonasMapper();
	}

	@Override
	public Persona mapRow(ResultSet rs, int rowNum) throws SQLException {
		return Persona.newInstance().idTipoIdentificacion(rs.getLong("IDE_TIP_IDENT_REP"))
				.numeroIdentificacion(rs.getString("NUM_IDENTIDAD_REP"))
				.nombreCompleto(rs.getString("NOMBRE_REP"))
				.correoElectronico(rs.getString("VAL_MAIL_REP"))
				.valIndicativo(rs.getString("VAL_INDICATIVO_REP"))
				.valTelefono(rs.getString("VAL_TELEFONO_REP"))
				.valExtension(rs.getString("VAL_EXTENSION_REP"))
				.valCelular(rs.getString("VAL_CELULAR_REP"))
				.idTipoPersona("10566")
			.build();
	}

}
