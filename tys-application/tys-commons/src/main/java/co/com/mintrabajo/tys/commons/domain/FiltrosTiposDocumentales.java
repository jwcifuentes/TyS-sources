package co.com.mintrabajo.tys.commons.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class FiltrosTiposDocumentales {

	private Long idTramite;
	private boolean tienePactosColectivos;
	private boolean tieneReglamentoTrabajo;
	private boolean tieneAsociacionSAS;
	private Long idSolicitadoPor;
	private Long idJustSolicitud;
	private Long idGradoAsociacion;
	private Long idTipoGestion;
	private String numeroRadicado;
	private Long idTipoEntidad;
	private Long idTipoParentesco;
	public FiltrosTiposDocumentales() {

	}

}
