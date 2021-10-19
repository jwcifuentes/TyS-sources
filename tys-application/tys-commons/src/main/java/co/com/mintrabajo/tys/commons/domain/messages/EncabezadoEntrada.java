package co.com.mintrabajo.tys.commons.domain.messages;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class EncabezadoEntrada {

	protected String peticionId;
	protected String canal;
	protected Date peticionFecha;
	protected String usuario;

	public EncabezadoEntrada() {
		super();
	}

}
