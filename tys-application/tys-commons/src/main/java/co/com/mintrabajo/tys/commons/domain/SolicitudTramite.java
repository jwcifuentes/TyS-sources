package co.com.mintrabajo.tys.commons.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class SolicitudTramite  {

	//IDE_REG_TRAMITE
	private Long id;
	//IDE_TRAMITE
	private Long idTramite;
	//IDE_RADICA_TRAMITE
	private Long idRadicaTramite;
	//IDE_JUSTIFICACION
	private Long idJustificacionSolicitud;
	//IDE_GRADO_ASOCI
	private Long idGradoAsociacion;
	//IDE_TIPO_SOLICITUD
	private Long idTipoSolicitud;
	//VAL_CIRCUNSTANCIAS
	private String valCircunstancias;
	//VAL_OBJETO_SOL
	private String valObjetoSolicitud;
	//IDE_JUSTA_CAUSA
	private Long idJustaCausa;
	//FEC_VEN_GESTION
	private Date fechaVencimientoGestion;
	//IDE_ESTADO_TRAMITE
	private Long idEstadoTramite;
	//IND_REQ_CONCEP_SDI
	private String  indicaReqConceptoSDI;
	//VAL_OBSERVACION
	private String valObservaciones;
	//VAL_MOTIVO_NIEGA
	private String valMotivoNiega;
	//VAL_COD_SEGURIDAD
	private String valCodSeguridad;
	//IDE_CONCEPTO_TRAMITE
	private  Long idConceptoTramite;
	//IND_REQ_ACTUALIZACION
	private String  indicaReqActualizacion;
	//IND_TIENE_CONCEPTO_SDI
	private String indicaTieneConceptoSDI;
	//FEC_VEN_GESTION
	private Date fechaVenGestion;
	//FEC_CREACION
	private Date FechaCreacion;
	//FEC_MODIFICACION
	private Date fechaModificacion;
	//USUARIO_CREA
	private String usuarioCrea;
	//USUARIO_MODIFICA
	private String usuarioModifica;
	//IND_ESTA_ADICIONADA
	private String indicaSiEstaAdicionada;
	
	public SolicitudTramite(){
		
	}

}
