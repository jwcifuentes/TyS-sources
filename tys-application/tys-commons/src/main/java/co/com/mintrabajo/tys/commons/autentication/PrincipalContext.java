package co.com.mintrabajo.tys.commons.autentication;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class PrincipalContext {
	
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private ArrayList<String> roles;
    
    public PrincipalContext() {
    	super();
    }
}
