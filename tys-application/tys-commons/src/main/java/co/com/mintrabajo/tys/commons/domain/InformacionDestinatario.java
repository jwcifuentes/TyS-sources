package co.com.mintrabajo.tys.commons.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "newInstance")
public class InformacionDestinatario {
	
	private String nombre;
	private String correo;
	

}
