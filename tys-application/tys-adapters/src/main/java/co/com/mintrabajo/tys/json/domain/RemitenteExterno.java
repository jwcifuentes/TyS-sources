package co.com.mintrabajo.tys.json.domain;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "newInstance")
public class RemitenteExterno implements Serializable {

	private static final long serialVersionUID = 1L;

	private TipoPersona tipoPersona;
	private TratamientoCortesia tratamientoCortesia;
	private ActuaCalidad enCalidadDe;
	private TipoDocumentoIdentidad tipoDocumentoIdentidad;
	private TipoTelefono tipoTelefono;
	private Pais pais;
	private Departamento departamento;
	private Ciudad ciudad;
	private String nit;
	private String numeroDocIdentidad;
	private String razonSocial;
	private String nombre;
	private Long indicativo;
	private String telefono;
	private Long extension;
	private String correoElectronico;
	private String direccion;
	private String codigoPostal;

	private String strNombre1;
	private String strNombre2;
	private String strApellido1;
	private String strApellido2;
}
