package co.com.mintrabajo.tys.commons.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "newInstance")
public class InformacionRemitente {

	private String correo;
}
