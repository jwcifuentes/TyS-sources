package co.com.mintrabajo.tys.commons.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class DatosDocumentoAdicional {

	private String codigoDependencia;
	private String nroRadicado;
	private String base64;
	private int tramiteTipologia;
	private String corPlantilla;
	private String nombreDocumento;
	private int idDocumento;
	private String idFilenet;
	private Long  idRegistraTramite;
	private String usuarioCreacion;

	public DatosDocumentoAdicional() {}

	@Override
	public String toString() {
		return "DatosDocumentoAdicional [codigoDependencia=" + codigoDependencia + ", nroRadicado=" + nroRadicado
				+ ", tramiteTipologia=" + tramiteTipologia + ", corPlantilla=" + corPlantilla + ", nombreDocumento="
				+ nombreDocumento + ", idDocumento=" + idDocumento + ", idFilenet=" + idFilenet + ", idRegistraTramite="
				+ idRegistraTramite + ", usuarioCreacion=" + usuarioCreacion + "]";
	}
	
	
}
