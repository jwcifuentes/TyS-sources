package co.com.mintrabajo.tys.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import co.com.mintrabajo.tys.commons.domain.InformacionDireccionTerritorial;

public class InformacionDireccionTerritorialMapper implements RowMapper<InformacionDireccionTerritorial>{

	private InformacionDireccionTerritorialMapper() {
	}

	public static InformacionDireccionTerritorialMapper newInstance() {
		return new InformacionDireccionTerritorialMapper();
	}

	
	@Override
	public InformacionDireccionTerritorial mapRow(ResultSet rs, int rowNum) throws SQLException {
		return InformacionDireccionTerritorial.newInstance()
				.idPais(rs.getLong("IDE_PAIS"))
				.idDepartamento(rs.getLong("IDE_DEPARTAMENTO"))
				.build();
	}

}
