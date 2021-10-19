package co.com.mintrabajo.tys.commons.domain.messages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class SubirDocumentoActualizarRequest {

	private String strCodigoDependenciaIn;
	private String strNroRadicadoIn;
	private String strBase64In;
	private int intTramiteTipologiaIn;
	private String strCorPlantillaIn;
	private String strNombreDocumentoIn;
	private int intIdDocumentoIn;
	private String strIdFilenetIn;

	public SubirDocumentoActualizarRequest() {
		super();
	}

}
