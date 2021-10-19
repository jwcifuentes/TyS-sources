package co.com.mintrabajo.tys.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import co.com.mintrabajo.tys.commons.domain.Notaria;

public class NotariasMapper implements RowMapper<Notaria> {

	private NotariasMapper() {
	}

	public static NotariasMapper newInstance() {
		return new NotariasMapper();
	}

	@Override
	public Notaria mapRow(ResultSet rs, int rowNum) throws SQLException {
		return Notaria.newInstance().id(rs.getLong("IDE_NOTARIA"))
				.nombre(rs.getString("NOMBRE"))
				.idMunicipio(rs.getLong("IDE_MUNICIPIO"))
				.build();
	}

}
