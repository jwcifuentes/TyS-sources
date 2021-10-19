package co.com.mintrabajo.tys.persistence.mapper;

import co.com.mintrabajo.tys.commons.domain.UrlTramite;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UrlTramiteMapper implements RowMapper<UrlTramite> {
    public static UrlTramiteMapper newInstance() {
        return new UrlTramiteMapper();
    }

    @Override
    public UrlTramite mapRow(ResultSet rs, int rowNum) throws SQLException {
        return UrlTramite.newInstance()
                .idUrl(rs.getLong("ID_TRAMITE_URL"))
                .idTramite(rs.getLong("COD_TIPO_TRAMITE"))
                .url(rs.getString("URL"))
                .esVisible(rs.getBoolean("ES_VISIBLE"))
                .build();
    }
}
