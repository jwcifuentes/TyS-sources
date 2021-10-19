package co.com.mintrabajo.tys.commons.autentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class PortalModel {

	private LoginContext context;

	public PortalModel() {
		super();
		context = new LoginContext();
	}
}
