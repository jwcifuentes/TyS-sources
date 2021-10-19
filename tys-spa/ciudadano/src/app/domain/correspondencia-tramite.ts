import {Remitente} from './remitente';
import {DireccionTerritorial} from './direccion-territorial';

export interface CorrespondenciaTramite {
  idTipoComunicacion?: number;
  idMedioRecepcion?: number;
  idTipologiaDocumental?: number;
  tiempoRespuesta?: number;
  fechaRadicacion?: string;
  numeroRadicado?: string;
  asunto?: string;
  descripcion?: string;
  referidos?: Array<string>;
  remitente?: Remitente;
  listaDireccionTerritoriales?: Array<DireccionTerritorial>;
  idUsuario?: string;
}
