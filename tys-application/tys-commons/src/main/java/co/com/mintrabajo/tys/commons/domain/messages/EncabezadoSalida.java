package co.com.mintrabajo.tys.commons.domain.messages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class EncabezadoSalida {

	protected String peticionId;
	protected String rtaCodigo;
	protected String rtaDescripcion;
	protected String criticidad;
	protected String rtaCodigoHost;
	protected String rtaDescripcionHost;

	public EncabezadoSalida() {
		super();
	}

}
