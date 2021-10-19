package co.com.mintrabajo.tys.commons.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class TipoDocumentalTramite implements Serializable {

	private static final long serialVersionUID = 1L;

	// IDE_TRA_TPG
	private Long id;
	// IDE_TPG_DOC
	private Long idTipoDocumental;
	// IDE_TRAMITE
	private Long idTramite;
	// VAL_REQUERIDO
	private Boolean esRequerido;
	// FEC_CREACION
	private Date fechaCreacion;
	// FEC_CAMBIO
	private Date fechaCambio;
	// USUARIO_CAMBIO
	private String usuariaCambio;
	// USUARIO_CREA
	private String usuarioCreacion;
	// ESTADO
	private boolean estado;
	// DESCRIPCION
	private String descripcion;
	// NOM_TPG_DOC
	private String nombre;
	//IND_TIENE_PACT_COV_COLEC
	private boolean indTienePactosColectivos;
	//IND_TIENE_REGL_TRAB
	private boolean indTieneReglamentoTrabajo;
	//IND_TIENE_SOC_SAS
	private boolean indTieneAsociacionSAS;
	//IDE_SOLIC_POR
	private Long  idSolicitadoPor;
	//SOLICITADO_POR
	private String nombreSolicitadoPor;
	//IDE_JUST_SOLICITUD
	private Long idJustSolicitud;
	//JUST_SOLICITUD
	private String nombreJustSolicitud;
	//IDE_GRADO_ASOCI
	private Long idGradoAsociacion;
	//GRADO_ASOC
	private String nombreGradoAsociacion;
	//IDE_TIP_GESTION
	private Long idtipoGestion;
	//TIP_GESTION
	private String nombreTipoGestion;
	//SUBCLASIFICACION
	private String subclasificacion;
	//IDE_TIP_ENTIDAD
	private Long idTipoEntidad;
	private  Long idTipoParentesco;
	public TipoDocumentalTramite() {
		super();
	}

}
