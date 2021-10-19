package co.com.mintrabajo.tys.json.domain;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "newInstance")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	private String idUsuario;

}
