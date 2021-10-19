package co.com.mintrabajo.tys.commons.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "newInstance")
public class TipoPersona {

	private Long id;
	private String nombre;
	private String codigo;
}
