package co.com.mintrabajo.infrastructure.security.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class GenericResponse<T> {

	private T content;

	public GenericResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

}
