package co.com.mintrabajo.tys.commons.domain.messages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class ObtenerDocumentoPorIdFilenetRequest {

	private String strIdFilenet;

	public ObtenerDocumentoPorIdFilenetRequest() {
		super();
	}

}
