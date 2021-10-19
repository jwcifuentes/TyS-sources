package co.com.mintrabajo.tys.json.domain;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "newInstance")
public class SolicitudTramite implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long strIndicaEstaAdicionada;
	private String strNomProceso;
	private Long strIdRegistroTramite;
	private Long strIdTramite;
	private String strNumRadicado;
	private Long strIdJustificacion;
	private Long strIdGradoAsociacion;
	private Long strIdTipoSolicitud;
	private Long strIdRadicaTramite;
	private String strValCircunstancias;
	private String strValObjetoSolicitud;
	private Long strIdJustaCausa;
	private String datFechaVencimientoGestion;
	private Long strIdEstadoTramite;
	private Long strIndicaReqConceptoSDI;
	private String strValObservaciones;
	private String strValMotivoNiega;
	private Long strIdConceptoTramite;
	private Long strIndicaReqActualizacion;
	private String datFechaCrea;
	
}
