package co.com.mintrabajo.tys.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import co.com.mintrabajo.tys.commons.domain.Direccion;

public class DireccionesMapper implements RowMapper<Direccion> {
	
	private DireccionesMapper() {
	}

	public static DireccionesMapper newInstance() {
		return new DireccionesMapper();
	}

	@Override
	public Direccion mapRow(ResultSet rs, int rowNum) throws SQLException {
		return Direccion.newInstance().idPais(rs.getLong("IDE_PAIS"))
				.nombrePais(rs.getString("NOM_PAIS"))
				.idDepartamento(rs.getLong("IDE_DEPARTAMENTO"))
				.nombreDepartamento(rs.getString("NOM_DEPARTAMENTO"))
				.idMunicipio(rs.getLong("IDE_MUNICIPIO"))
				.nombreMunicipio(rs.getString("NOM_MUNICIPIO"))
				.codigoPostal(rs.getString("COD_POSTAL"))
				.valDireccion(rs.getString("VAL_DIRECCION"))
				.valCiudad(rs.getString("VAL_CIUDAD"))
				.build();
	}

}
