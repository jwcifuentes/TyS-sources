package co.com.mintrabajo.tys.commons.domain.messages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class ObtenerDatosSeccionalRequest {

	private String strCodigoTramiteIn;
	private String strGradoAsociacionIn;
	private String strIdSubFondoIn;

	public ObtenerDatosSeccionalRequest() {
		super();
	}

}
