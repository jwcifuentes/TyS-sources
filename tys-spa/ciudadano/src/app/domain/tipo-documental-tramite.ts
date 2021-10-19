export interface TipoDocumentalTramite {
  id: number;
  nombre: string;
  idTipoDocumental?: number;
  idTramite?: number;
  esRequerido?: boolean;
  fechaCreacion?: Date;
  fechaCambio?: Date;
  usuariaCambio?: string;
  usuarioCreacion?: string;
  estado?: boolean;
  descripcion?: string;


}
