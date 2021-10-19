package co.com.mintrabajo.tys.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import co.com.mintrabajo.tys.commons.domain.Tramite;

/**
 * Created by jrodriguez on 08/11/2017.
 */
public class TramitesMapper implements RowMapper<Tramite> {


    private TramitesMapper() {
    }

    public static TramitesMapper newInstance() {
        return new TramitesMapper();
    }

    @Override
    public Tramite mapRow(final ResultSet rs, final int rowNum) throws SQLException {

        return Tramite.newInstance().id(rs.getLong("IDE_TRAMITE"))
                .nombre(rs.getString("NOMBRE_TRAMITE"))
                .tieneSustanciadores(rs.getBoolean("IND_SUTANCIADORES"))
                .requiereConceptoSubInsp(rs.getBoolean("REQ_CONCEPTO_SUB_INSP"))
                .idReglaAsignacion(rs.getLong("IDE_REGLA_ASIGNACION"))
                .nmDireccionesPermitidas(rs.getString("IND_DT_PERMITIDAS"))
                .tiempoGestionTramite(rs.getInt("TIEMPO_GEST_TRA"))
                .idUnidadTiempo(rs.getLong("IDE_UNIDAD_TIEMPO"))
                .idTipoDocEmitido(rs.getLong("IDE_DOC_RPTA_FINAL"))
                .esTramiteSoloRecepcion(rs.getBoolean("IND_SOLO_RECEP"))
                .estado(rs.getBoolean("ESTADO_REG"))
                .fechaCreacion(rs.getTimestamp("FEC_CREACION"))
                .fechaCambio(rs.getTimestamp("FEC_CAMBIO"))
                .usuarioCreacion(rs.getString("USUARIO_CREA"))
                .usuariaCambio(rs.getString("USUARIO_CAMBIO"))
                .permiteActualizacion(rs.getBoolean("IND_PER_ACTUALIZA"))
                .descripcion(rs.getString("DESCRIPCION"))
                .build();
    }

}
