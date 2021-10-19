package co.com.mintrabajo.tys.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import co.com.mintrabajo.tys.commons.domain.Parametro;

/**
 * Created by jrodriguez on 07/11/2017.
 */

public class ParametrosMapper implements RowMapper<Parametro> {

    private ParametrosMapper(){}

    public static ParametrosMapper newInstance() {
        return new ParametrosMapper();
    }

    public Parametro mapRow(final ResultSet rs, final int rowNum) throws SQLException{
        return Parametro.newInstance()
                .id(rs.getLong("IDE_CON_CONSTANTE"))
                .nombre(rs.getString("NOMBRE"))
                .estado(rs.getBoolean("IDE_ESTADO_REG"))
                .build();
    }

}
