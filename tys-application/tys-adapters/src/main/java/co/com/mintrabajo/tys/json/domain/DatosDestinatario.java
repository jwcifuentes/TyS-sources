package co.com.mintrabajo.tys.json.domain;


import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "newInstance")
public class DatosDestinatario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Destinatario> tablaDestinatario;
	

}
