package co.com.mintrabajo.tys.commons.domain.messages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class SubirDocumentoActualizarResponse {

	private EncabezadoSalida encabezadoSalida;
	private String strCodigoOut;
	private String strMensajeOut;

	public SubirDocumentoActualizarResponse() {
		super();
	}

}
