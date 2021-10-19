package co.com.mintrabajo.tys.commons.domain;

import co.com.mintrabajo.tys.commons.constants.TipoExcepcion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class Fault {

	private String codigo;
	private TipoExcepcion tipo;
	private String descripcion;

	public Fault() {
		super();
	}
	
}
