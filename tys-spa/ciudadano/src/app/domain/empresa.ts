import {ActividadEconomica} from './ActividadEconomica';

export interface Empresa {
  id?: number;
  numeroEscritura?: string;
  fechaExpedicionEscritura?: string;
  idDepartamentoExpedicion?: number;
  idMunicipioExpedicion?: number;
  idNotaria?: number;
  numeroPoliza?: string;
  valorPoliza?: number;
  nombreAseguradora?: string;
  fechaInicialPoliza?: string;
  fechaFinPoliza?: string;
  idSolicitante?: number;
  idDireccion?: number;
  idCIIU?: number;
  idRegistroTramite?: number;
  idTipoEmpresa?: number;
  idNotificacionElectronica?: boolean;
  autorizacionNotificacionElectronica?: boolean;
  tieneConveniosColectivos?: string;
  tieneReglamentoTrabajo?: string;
  esSAS?: string;
  idRepresentanteLegal?: number;
  idDireccionSucuarsal?: number;
  idTipoEmpleador?: number;
  idTipoGestion?: number;
  nit?: number;
  grupoActividadEconomica?: ActividadEconomica;
}
