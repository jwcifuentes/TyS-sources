package co.com.mintrabajo.tys.commons.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class SocioEmpresa  {

	private String idTipoPersona;
	private Long idTipoIdentificacion;
	private String numeroIdentificacion;
	private String nombreCompleto;

	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	public SocioEmpresa(){
		
	}
}
