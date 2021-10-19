package co.com.mintrabajo.tys.commons.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by jrodriguez on 07/11/2017.
 */
@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class Tramite implements Serializable {

	private static final long serialVersionUID = 1L;

	//IDE_TRAMITE
	private Long id;
	//NOMBRE_TRAMITE
	private String nombre;
	//IND_SUTANCIADORES
	private boolean tieneSustanciadores;
	//REQ_CONCEPTO_SUB_INSP
	private boolean requiereConceptoSubInsp;
	//IDE_REGLA_ASIGNACION
	private Long idReglaAsignacion;
	//IND_DT_PERMITIDAS
	private String nmDireccionesPermitidas;
	//TIEMPO_GEST_TRA
	private Integer tiempoGestionTramite;
	//IDE_UNIDAD_TIEMPO
	private Long idUnidadTiempo;
	//IDE_DOC_RPTA_FINAL
	private Long idTipoDocEmitido;
	//IND_SOLO_RECEP
	private boolean esTramiteSoloRecepcion;
	//ESTADO_REG
	private boolean estado;
	//FEC_CREACION
	private Date fechaCreacion;
	//FEC_CAMBIO
	private Date fechaCambio;
	//USUARIO_CAMBIO
	private String usuariaCambio;
	//USUARIO_CREA
	private String usuarioCreacion;
	//IND_PER_ACTUALIZA
	private boolean permiteActualizacion;
	//DESCRIPCION
	private String descripcion;
	//URL
	private List<String> listURL;

	private List<UrlTramite> urlTramites;
	public Tramite() {
		super();
	}

}
