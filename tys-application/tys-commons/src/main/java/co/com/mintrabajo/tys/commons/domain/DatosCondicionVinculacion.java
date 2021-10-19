package co.com.mintrabajo.tys.commons.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class DatosCondicionVinculacion {
	
	private CondicionVinculacion condicionVinculacion;
	private Direccion direccionVinculo;
	private HorarioLaboral horarioLaboral;
	
	public DatosCondicionVinculacion(){
		
	}
}
