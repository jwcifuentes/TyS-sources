package co.com.mintrabajo.infrastructure.security.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class TokenWrapper {

	private String token;

	public TokenWrapper() {
		super();
	}

}
