package co.com.mintrabajo.tys.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import co.com.mintrabajo.tys.commons.domain.TramiteRadicado;
import co.com.mintrabajo.tys.commons.util.DateUtil;

public class TramiteRadicadoMapper implements RowMapper<TramiteRadicado> {

	private TramiteRadicadoMapper() {
	}

	public static TramiteRadicadoMapper newInstance() {
		return new TramiteRadicadoMapper();
	}

	@Override
	public TramiteRadicado mapRow(ResultSet rs, int rowNum) throws SQLException {
		return TramiteRadicado.newInstance().idTramiteRadicado(rs.getLong("IDE_REG_TRAMITE"))
				.numeroRadicado(rs.getString("NRO_RADICADO"))
				.idTramite(rs.getLong("IDE_TRAMITE"))
				.nombreTramite(rs.getString("NOMBRE_TRAMITE"))
				.fechaRadiacionTramite(rs.getTimestamp("FEC_CREACION"))
				.idDireccionTerritorial(rs.getLong("ID_DIRTERRITORIAL"))
				.direccionTerritorial(rs.getString("NOM_DIRTERRITORIAL"))
				.idDependencia(rs.getLong("ID_DEPENDENCIA"))
				.dependencia(rs.getString("NOM_DEPENDENCIA"))
				.idEstadoTramite(rs.getLong("IDE_ESTADO_TRAMITE"))
				.nombreEstado(rs.getString("ESTADO"))
				.fechaRadicacion(DateUtil.getDateToString(rs.getTimestamp("FEC_CREACION")))
				.indTienePactosColectivos(rs.getBoolean("IND_PACT_CONV_COL"))
				.indTieneReglamentoTrabajo(rs.getBoolean("IND_OBLI_REGL_TRAB"))
				.indTieneAsociacionSAS(rs.getBoolean("IND_EMP_SAS"))
				.idSolicitadoPor(rs.getLong("SOLICITADO_POR"))
				.idJustSolicitud(rs.getLong("JUST_SOLICITUD"))
				.idGradoAsociacion(rs.getLong("GRADO_ASOC"))
				.codDependencia(rs.getString("COD_SECCION"))
				.idFilenetDocProducido(rs.getString("VAL_FILENET"))
				.indRequiereActualizacion(rs.getBoolean("IND_REQ_ACTUALIZACION"))
				.indEstadoEntrevista(rs.getLong("IND_APROBACION_ENTR"))
				.indEstadoVisita(rs.getLong("IND_APROBACION_VISI"))
				.build();
				
	}

}
