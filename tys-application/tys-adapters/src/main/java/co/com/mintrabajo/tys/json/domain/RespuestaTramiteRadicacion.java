package co.com.mintrabajo.tys.json.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder(builderMethodName = "newInstance")
@AllArgsConstructor
public class RespuestaTramiteRadicacion {

	private String strMensaje;
	private String strCodigo;
	private String strIdRegistroTramite;
	private String strCodigoSeguridad;

	public RespuestaTramiteRadicacion() {
		super();
	}

}
