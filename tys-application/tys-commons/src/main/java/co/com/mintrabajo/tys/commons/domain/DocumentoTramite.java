package co.com.mintrabajo.tys.commons.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class DocumentoTramite {

	private String codigoDependencia;
	private String nroRadicado;
	private String base64;
	private int tramiteTipologia;
	private String corPlantilla;
	private String nombreDocumento;
	private int idDocumento;
	private String idFilenet;
	private String subclasificacion;
	private Long  idRegistraTramite;
	private boolean other;
	public DocumentoTramite() {

	}

	@Override
	public String toString() {
		return "DocumentoTramite [codigoDependencia=" + codigoDependencia + ", nroRadicado=" + nroRadicado
				+ ", tramiteTipologia=" + tramiteTipologia + ", corPlantilla=" + corPlantilla + ", nombreDocumento="
				+ nombreDocumento + ", idDocumento=" + idDocumento + ", idFilenet=" + idFilenet + "]";
	}

}
