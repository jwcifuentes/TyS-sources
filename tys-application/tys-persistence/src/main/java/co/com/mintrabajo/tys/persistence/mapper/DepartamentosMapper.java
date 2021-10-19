package co.com.mintrabajo.tys.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import co.com.mintrabajo.tys.commons.domain.Departamento;

public class DepartamentosMapper implements RowMapper<Departamento>  {
	
	private DepartamentosMapper() {
	}

	public static DepartamentosMapper newInstance() {
		return new DepartamentosMapper();
	}

	@Override
	public Departamento mapRow(ResultSet rs, int rowNum) throws SQLException {
		return Departamento.newInstance().id(rs.getLong("IDE_DEPARTAMENTO"))
				.nombre(rs.getString("NOM_DEPARTAMENTO"))
				.codigo(rs.getString("COD_DEPARTAMENTO"))
				.idPais(rs.getLong("IDE_PAIS"))
				.build();
	}
	

}
