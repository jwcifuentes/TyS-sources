import {Direccion} from './direccion';

export interface Remitente {
  idtipoPersona: number;
  idtratamientoCortesia: number;
  idenCalidadDe: number;
  idtipoDocumentoIdentidad: number;
  idtipoTelefono: number;
  nit: number;
  razonSocial: string;
  numeroDocumento: string;
  nombre: string;
  telefono: string;
  extension: number;
  indicativo: number;
  correoElectronico: string;
  direccion: Direccion;
  codigoActividad: number;
  idTipoEmpresa: number;
  primerNombre: string;
  segundoNombre: string;
  primerApellido: string;
  segundoApellido: string;
}
