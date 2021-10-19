package co.com.mintrabajo.tys.commons.domain.messages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;


@Data
@AllArgsConstructor
@ToString
@Builder(builderMethodName = "newInstance")
public class IniciarProcesoResponse {
	
	private EncabezadoSalida encabezadoSalida;
	private String codigoRespuesta;
	
	public IniciarProcesoResponse() {
		super();
	}
	
}
