package co.com.mintrabajo.tys.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import co.com.mintrabajo.tys.commons.domain.TipoDocumentalTramite;

public class TipoDocumentalTramiteMapper implements RowMapper<TipoDocumentalTramite> {

	private TipoDocumentalTramiteMapper() {
	}

	public static TipoDocumentalTramiteMapper newInstance() {
		return new TipoDocumentalTramiteMapper();
	}

	@Override
	public TipoDocumentalTramite mapRow(ResultSet rs, int rowNum) throws SQLException {
		return TipoDocumentalTramite.newInstance()
				.id(rs.getLong("IDE_TRA_TPG"))
				.idTipoDocumental(rs.getLong("IDE_TPG_DOC"))
				.nombre(rs.getString("NOM_TPG_DOC"))
				.idTramite(rs.getLong("IDE_TRAMITE"))
				.esRequerido(rs.getBoolean("VAL_REQUERIDO"))
				.fechaCreacion(rs.getDate("FEC_CREACION"))
				.fechaCambio(rs.getDate("FEC_CAMBIO"))
				.usuarioCreacion(rs.getString("IDE_USUARIO_CREA"))
				.usuariaCambio(rs.getString("IDE_USUARIO_CAMBIO"))
				.estado(rs.getBoolean("ESTADO"))
				.descripcion(rs.getString("DESCRIPCION"))
				.build();
	}

}
