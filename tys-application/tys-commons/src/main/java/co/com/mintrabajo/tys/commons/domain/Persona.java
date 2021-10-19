package co.com.mintrabajo.tys.commons.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class Persona   {

	//IDE_PERSONA		
	private 	Long 	id;
	//IDE_TIP_PERSONA
	private 	String 	idTipoPersona;
	//IDE_REG_TRAMITE
	private 	Long 	idRegistoTramite;
	//NOMBRE_COMPLETO
	private 	String 	nombreCompleto;
	//IDE_TIP_IDENTIFICACION
	private 	Long 	idTipoIdentificacion;
	//NUM_IDENTIDAD
	private 	String 	numeroIdentificacion;
	//IDE_DIRECCION
	private 	Long 	idDireccion;
	//VAL_TELEFONO
	private 	String 	valTelefono;
	//IDE_TIP_TELEFONO
	private 	Long 	idTipoTelefono;
	//VAL_EXTENSION
	private 	String 	valExtension;
	//VAL_INDICATIVO
	private 	String 	valIndicativo;
	//VAL_EDAD
	private 	Long 	valEdad;
	//ULT_GRADO_CURSADO
	private 	String 	ultimoGradoCursado;
	//IDE_REP_LEGAL
	private 	Long 	idRepresentanteLegal;
	//PARENTESCO
	private 	String 	parentesco;
	//VAL_MAIL
	private 	String 	valMail;
	//VAL_CELULAR
	private 	String 	valCelular;
	//IDE_EDAD_NNA
	private 	String 	idEdadNNAdolecente;
	//EMAIL
	private String correoElectronico;
	
	private Direccion direccionPersona;

	//FECHA_NACIMIENTO
	private Date fechaNacimiento;
	//ID_GENERO
	private Long genero;
	//NRO_HIJOS
	private Integer cuantosHijos;
	//ID_DISCAPACIDAD
	private List<Parametro> condicionDiscapacidad;
	//NOMBRE_COLEGIO
	private String nombreInstitucionEducativa;
	//JORNADA_ESCOLAR
	private String jornadaEscolar;

	//Nuevos datos direccion NNA
	//CON_CONSTANTE.ID_TIPOUBICACION
	private Long tipoUbicacion;
	//CON_CONSTANTE.NOMBREUBICACION
	private String nombreUbicacion;
	//CON_CONSTANTE.ID_TIPOZONA
	private Long tipoZona;


	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;

	private Long idTipoRegimen;
	private String nombreRegimen;
	private Long idParentesco;
	private boolean tieneHijos;
	private Long idJornadaEscolar;
	public Persona(){
		
	}

}
