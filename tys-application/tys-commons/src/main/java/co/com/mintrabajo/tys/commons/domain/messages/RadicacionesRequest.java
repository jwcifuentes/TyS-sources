package co.com.mintrabajo.tys.commons.domain.messages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class RadicacionesRequest {

	private Object payload;
	private String storeProcedure;

	public RadicacionesRequest() {
		super();
	}

}
