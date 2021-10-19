package co.com.mintrabajo.tys.commons.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class DireccionTerritorial implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nombre;
	private String codigo;
	private Long idFondo;
	private boolean estado;
	
	public DireccionTerritorial(){
		
	}

}
