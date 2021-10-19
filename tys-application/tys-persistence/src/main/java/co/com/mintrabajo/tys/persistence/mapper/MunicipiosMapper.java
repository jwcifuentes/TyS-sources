package co.com.mintrabajo.tys.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import co.com.mintrabajo.tys.commons.domain.Municipio;

public class MunicipiosMapper implements RowMapper<Municipio> {
	
	private MunicipiosMapper() {
	}

	public static MunicipiosMapper newInstance() {
		return new MunicipiosMapper();
	}

	@Override
	public Municipio mapRow(ResultSet rs, int rowNum) throws SQLException {
		return Municipio.newInstance().id(rs.getLong("IDE_MUNICIPIO"))
				.nombre(rs.getString("NOM_MUNICIPIO"))
				.codigo(rs.getString("COD_MUNICIPIO"))
				.idDepartamento(rs.getLong("IDE_DEPARTAMENTO"))
				.build();
	}

}
