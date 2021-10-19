package co.com.mintrabajo.tys.commons.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class DatosRemitente {
	
	private Remitente remitente; 

}
