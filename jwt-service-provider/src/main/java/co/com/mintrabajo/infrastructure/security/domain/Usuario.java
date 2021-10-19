package co.com.mintrabajo.infrastructure.security.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class Usuario {

	private String login;
	private String password;
	private String role;

	public Usuario() {
		super();
	}

}
