package co.com.mintrabajo.tys.commons.autentication;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class LoginContext {

	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private List<String> roles;
	private String username;
	private boolean logged;

	public LoginContext() {
		super();
	}

}
