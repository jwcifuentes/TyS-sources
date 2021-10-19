package co.com.mintrabajo.tys.commons.autentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class AutenticationResponseContext {
	
    private boolean successful;
    private PrincipalContext principalContext;
    
    public AutenticationResponseContext() {
    	super();    	
    }

    
}
