package co.com.mintrabajo.tys.persistence.mapper;

import co.com.mintrabajo.tys.commons.domain.ActividadEconomica;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActividadEconomicaMapper implements RowMapper<ActividadEconomica> {
    public static ActividadEconomicaMapper newInstance() {
        return new ActividadEconomicaMapper();
    }
    @Override
    public ActividadEconomica mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ActividadEconomica.newInstance()
                .idActividadEconomica(rs.getLong("ID_ACTIVIDAD_ECONOMICA"))
                .descripcion(rs.getString("DESCRIPCION"))
                .idActividadEconomicaPadre(rs.getLong("ID_ACTIVIDAD_ECONOMICA_P"))
                .seccion(rs.getNString("SECCION"))
                .divsionGrupo(rs.getLong("DIVISION_GRUPO"))
                .build();
    }
}
