package co.com.mintrabajo.tys.commons.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class Usuario {

	private String login;
	private String password;
	private String role;
	private String nombres;

	public Usuario() {
		super();
	}

}
