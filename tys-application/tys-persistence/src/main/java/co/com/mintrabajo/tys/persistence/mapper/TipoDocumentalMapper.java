package co.com.mintrabajo.tys.persistence.mapper;

import co.com.mintrabajo.tys.commons.domain.TipoDocumental;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jrodriguez on 08/11/2017.
 */
public class TipoDocumentalMapper implements RowMapper<TipoDocumental> {

    private TipoDocumentalMapper() {
    }

    public static TipoDocumentalMapper newInstance() {
        return new TipoDocumentalMapper();
    }

    @Override
    public TipoDocumental mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        return TipoDocumental.newInstance().id(rs.getLong("IDE_TPG_DOC"))
                .nombre(rs.getString("NOM_TPG_DOC"))
                .estado(rs.getBoolean("EST_TPG_DOC"))
                .build();
    }
}
