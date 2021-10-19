package co.com.mintrabajo.tys.json.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder(builderMethodName = "newInstance")
@AllArgsConstructor
public class RespuestaCorrespondenciaRadicacion {

	private String mensaje;
	private String codigo;
	private String numRadicado;
	private String fechaRadicacion;

	public RespuestaCorrespondenciaRadicacion() {
		super();
	}

}
