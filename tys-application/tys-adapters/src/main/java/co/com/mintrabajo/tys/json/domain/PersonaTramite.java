package co.com.mintrabajo.tys.json.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import co.com.mintrabajo.tys.commons.domain.Parametro;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "newInstance")
public class PersonaTramite implements Serializable {

	private static final long serialVersionUID = 1L;

	private String strIdTipoPersona;
	private Long strIdPersona;
	private String strNombreCompleto;
	private Long strIdTipoIdentificacion;
	private String strNumeroIdentificacion;
	private String strValTelefono;
	private String strValExtension;
	private String strValIndicativo;
	private Long strIdEdadNNA;
	private Long strIdTipoTelefono;
	private String strUltimoGradoCursado;
	private String strParentesco;
	private String strValMail;
	private String strValCelular;
	private DireccionSucursal objDireccion;

	private String strNombre1;
	private String strNombre2;
	private String strApellido1;
	private String strApellido2;
	
	private Date strFechaNacimiento;
	private String strNombreInstitucionEducativa;
	private String strJornadaEscolar;
	private Integer strNumHijos;
    private Long strIdTipoGenero;
    private String strCorreoElectronico;
    private Long strIdTipoRegimen;
    private String strNomRegimen;
    private Long strIdParentesco;
    private String strTieneHijos;
    private Long strIdJornadaEscolar;
}
