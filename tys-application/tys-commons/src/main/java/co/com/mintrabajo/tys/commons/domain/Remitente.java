package co.com.mintrabajo.tys.commons.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class Remitente implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long idtipoPersona;
	private Long idtratamientoCortesia;
	private Long idenCalidadDe;
	private Long idtipoDocumentoIdentidad;
	private Long idtipoTelefono;
	private String nit;
	private String razonSocial;
	private String numeroDocumento;
	private String nombre;
	private Long indicativo;
	private String telefono;
	private Long extension;
	private String correoElectronico;
	private Direccion direccion;

	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;

	public Remitente(){
		
	}
	
}
