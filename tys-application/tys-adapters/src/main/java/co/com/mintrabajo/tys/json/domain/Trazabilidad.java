package co.com.mintrabajo.tys.json.domain;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "newInstance")
public class Trazabilidad implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String strUsuarioModifica;
	private String strInstruccionRealizada;
	private String strDireccionIP;
	private String strUsuarioCrea;

}
