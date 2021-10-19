export class TipoDocumentalTramite {
  id: number;
  idTipoDocumental: number;
  idTramite: number;
  esRequerido: boolean;
  fechaCreacion: Date;
  fechaCambio: Date;
  usuariaCambio: string;
  usuarioCreacion: string;
  estado: boolean;
  descripcion: string;
  nombre: string;
  indTienePactosColectivos: boolean;
  indTieneReglamentoTrabajo: boolean;
  indTieneAsociacionSAS: boolean;
  idSolicitadoPor: number;
  nombreSolicitadoPor: string;
  idJustSolicitud: number;
  nombreJustSolicitud: string;
  idGradoAsociacion: number;
  nombreGradoAsociacion: string;
  idtipoGestion: number;
  nombreTipoGestion: string;
  idTipoEntidad: number;
  idTipoParentesco: number;
  constructor() {
  }
}
