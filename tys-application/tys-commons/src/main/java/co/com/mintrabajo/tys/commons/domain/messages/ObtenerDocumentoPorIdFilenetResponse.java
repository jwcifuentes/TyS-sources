package co.com.mintrabajo.tys.commons.domain.messages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class ObtenerDocumentoPorIdFilenetResponse {

	private EncabezadoSalida encabezadoSalida;
	private String base64;
	private String fileName;

	public ObtenerDocumentoPorIdFilenetResponse() {
		super();
	}

}
