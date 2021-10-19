export class Tramite {

  static readonly idEstadosTramiteAprobado: number[] = [10575, 10576, 10577];
  static readonly idEstadosTramiteCerrado: number[] = [10579, 10580, 10581, 10582];

    id: number;
    nombre: string;
    tieneSustanciadores: boolean;
    requiereConceptoSubInsp: boolean;
    idReglaAsignacion: number;
    nmDireccionesPermitidas: string;
    tiempoGestionTramite: number;
    idUnidadTiempo: number;
    idTipoDocEmitido: number;
    esTramiteSoloRecepcion: boolean;
    estado: boolean;
    fechaCreacion: Date;
    fechaCambio: Date;
    usuariaCambio: string;
    usuarioCreacion: string;
    subclasificacion?:string;

    idFilenetDocProducido: string;
    idEstadoTramite: number;
    indRequiereActualizacion: boolean;

    indEstadoEntrevista?: number;
    indEstadoVisita?: number;

  /**
   * 10575	EST_TRA_TS1 ABIERTO
   * 10576	EST_TRA_TS2 ABIERTO - A TIEMPO
   * 10577	EST_TRA_TS3 ABIERTO - VENCIDO
   * 10578	EST_TRA_TS4 CANCELADO SIN DATOS
   * 10579	EST_TRA_TS5 CERRADO
   * 10580	EST_TRA_TS6 CERRADO - A TIEMPO
   * 10581	EST_TRA_TS7 CERRADO - DESISTIMIENTO POR SOLICITANTE
   * 10582	EST_TRA_TS8 CERRADO - VENCIDO
   */
  getStateDescription(): string {
    let description: string;
    if (Tramite.idEstadosTramiteAprobado.indexOf(this.idEstadoTramite)) {
      description = 'ABIERTO';
    } else if (Tramite.idEstadosTramiteCerrado.indexOf(this.idEstadoTramite)) {
      description = 'CERRADO';
    } else {
      description = 'CANCELADO';
    }
    return description;
  }
}
