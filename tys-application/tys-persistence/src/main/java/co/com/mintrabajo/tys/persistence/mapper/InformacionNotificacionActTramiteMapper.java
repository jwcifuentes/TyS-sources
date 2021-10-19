package co.com.mintrabajo.tys.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import co.com.mintrabajo.tys.commons.domain.InformacionNotificacionActTramite;

public class InformacionNotificacionActTramiteMapper implements RowMapper<InformacionNotificacionActTramite> {

	private InformacionNotificacionActTramiteMapper() {
	}

	public static InformacionNotificacionActTramiteMapper newInstance() {
		return new InformacionNotificacionActTramiteMapper();
	}
	@Override
	public InformacionNotificacionActTramite mapRow(ResultSet rs, int rowNum) throws SQLException {
		return InformacionNotificacionActTramite.newInstance()
				.numeroRadicado(rs.getString("NRO_RADICADO"))
				.codigoSeguridad(rs.getString("VAL_COD_SEGURIDAD"))
				.idTramite(rs.getLong("IDE_TRAMITE"))
				.tramite(rs.getString("NOMBRE_TRAMITE"))
				.idTerritorial(rs.getLong("ID_DIRTERRITORIAL"))
				.territorial(rs.getString("NOM_DIRTERRITORIAL"))
				.build();
	}

}
