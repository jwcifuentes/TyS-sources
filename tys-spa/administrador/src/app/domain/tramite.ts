export class Tramite {

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
  permiteActualizacion: boolean;
  descripcion: string;
  urlTramites: [{
    idUrl?: number,
    idTramite?: number,
    url?: string,
    esVisible?: boolean
  }];
  constructor() {
  }
}
