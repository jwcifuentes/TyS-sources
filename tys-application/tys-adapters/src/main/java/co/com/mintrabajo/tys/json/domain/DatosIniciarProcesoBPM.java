package co.com.mintrabajo.tys.json.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class DatosIniciarProcesoBPM {
	
	private String numeroRadicado;
	private String nomProceso;
	
	public DatosIniciarProcesoBPM() {
		super();
	}


}
