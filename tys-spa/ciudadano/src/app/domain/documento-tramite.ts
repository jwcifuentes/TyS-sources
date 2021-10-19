import {Parametro} from './parametro';

export interface DocumentoTramite {
  codigoDependencia?: string;
  nroRadicado?: string;
  base64?: string;
  tramiteTipologia?: number;
  corPlantilla?: string;
  nombreDocumento: string;
  idDocumento?: number;
  idFilenet?: string;
  size?: string;
  subclasificacion?: string;
  estadoDoc?: Parametro;
  nombre?: string;
  other?: boolean;
}
