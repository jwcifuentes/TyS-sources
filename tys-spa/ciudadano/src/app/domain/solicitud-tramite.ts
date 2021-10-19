export interface SolicitudTramite {
  id?: number;
  idTramite?: number;
  idRadicaTramite?: number;
  idJustificacionSolicitud?: number;
  idGradoAsociacion?: number;
  idTipoSolicitud?: number;
  valCircunstancias?: string;
  valObjetoSolicitud?: string;
  idJustaCausa?: number;
  idEstadoTramite?: number;
  indicaReqConceptoSDI?: string;
  valObservaciones?: string;
  valMotivoNiega?: string;
  idConceptoTramite?: number;
  indicaReqActualizacion?: string;
  valCodSeguridad?: string;
  fechaVenGestion?: string;
  fechaCreacion?: string;
  fechaModificacion?: string;
  usuarioCrea?: string;
  usuarioModifica?: string;
}
