package co.com.mintrabajo.tys.json.domain;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "newInstance")
public class Socio implements Serializable {

	private static final long serialVersionUID = 1L;

	private String strIdTipoPersona;
	private Long strIdTipoIdentificacion;
	private String strNumeroIdentificacion;
	private String strNombreCompleto;

	private String strNombre1;
	private String strNombre2;
	private String strApellido1;
	private String strApellido2;
}
