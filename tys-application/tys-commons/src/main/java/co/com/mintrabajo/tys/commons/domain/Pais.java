package co.com.mintrabajo.tys.commons.domain;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "newInstance")
public class Pais implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nombre;
	private String codigo;

}
