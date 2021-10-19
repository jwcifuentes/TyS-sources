package co.com.mintrabajo.tys.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import co.com.mintrabajo.tys.commons.domain.TipoDocumentalTramite;

public class TipoDocumentalTramiteReglasMapper implements RowMapper<TipoDocumentalTramite> {

	private TipoDocumentalTramiteReglasMapper() {
	}

	public static TipoDocumentalTramiteReglasMapper newInstance() {
		return new TipoDocumentalTramiteReglasMapper();
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
				.indTienePactosColectivos(rs.getBoolean("IND_TIENE_PACT_COV_COLEC"))
				.indTieneReglamentoTrabajo(rs.getBoolean("IND_TIENE_REGL_TRAB"))
				.indTieneAsociacionSAS(rs.getBoolean("IND_TIENE_SOC_SAS"))
				.idSolicitadoPor(rs.getLong("IDE_SOLIC_POR"))
				.nombreSolicitadoPor(rs.getString("SOLICITADO_POR"))
				.idJustSolicitud(rs.getLong("IDE_JUST_SOLICITUD"))
				.nombreJustSolicitud(rs.getString("JUST_SOLICITUD"))
				.idGradoAsociacion(rs.getLong("IDE_GRADO_ASOCI"))
				.nombreGradoAsociacion(rs.getString("GRADO_ASOC"))
				.idtipoGestion(rs.getLong("IDE_TIP_GESTION"))
				.nombreTipoGestion(rs.getString("TIPO_GESTION"))
				.idTipoEntidad(rs.getLong("IDE_TIP_ENTIDAD"))
				.idTipoParentesco(rs.getLong("IDE_TIP_PARENTESCO"))
				.build();
	}

}
