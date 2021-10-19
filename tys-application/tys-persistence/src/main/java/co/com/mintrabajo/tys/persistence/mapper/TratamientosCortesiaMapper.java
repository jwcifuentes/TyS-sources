package co.com.mintrabajo.tys.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import co.com.mintrabajo.tys.commons.domain.TratamientoCortesia;

public class TratamientosCortesiaMapper implements RowMapper<TratamientoCortesia> {
	
	private TratamientosCortesiaMapper() {
	}

	public static TratamientosCortesiaMapper newInstance() {
		return new TratamientosCortesiaMapper();
	}

	@Override
	public TratamientoCortesia mapRow(ResultSet rs, int rowNum) throws SQLException {
		return TratamientoCortesia.newInstance().id(rs.getLong("IDE_TRA_CORTESIA"))
				.nombre(rs.getString("DES_TRA_CORTESIA"))
				.build();
	}
	

}
