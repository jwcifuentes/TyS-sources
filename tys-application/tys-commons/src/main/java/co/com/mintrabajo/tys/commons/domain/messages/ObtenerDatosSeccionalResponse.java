package co.com.mintrabajo.tys.commons.domain.messages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class ObtenerDatosSeccionalResponse {

	private EncabezadoSalida encabezadoSalida;
	private String strIdSeccionOut;
	private String strCodigoSeccionOut;
	private String strIdSubFondoOut;

	public ObtenerDatosSeccionalResponse() {
		super();
	}

}
