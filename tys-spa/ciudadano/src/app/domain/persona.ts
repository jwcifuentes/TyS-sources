import {Direccion} from './direccion';

export interface Persona {
  id?: number;
  idTipoPersona?: string;
  idRegistoTramite?: number;
  nombreCompleto: string;
  idTipoIdentificacion?: number;
  numeroIdentificacion?: string;
  idDireccion?: number;
  valTelefono?: string;
  idTipoTelefono?: number;
  valExtension?: string;
  valIndicativo?: string;
  valEdad?: number;
  ultimoGradoCursado?: string;
  idRepresentanteLegal?: number;
  parentesco?: string;
  valMail?: string;
  valCelular?: string;
  idEdadNNAdolecente?: string;
  correoElectronico?: string;
  direccionPersona?: Direccion;
  genero?: string;
  fechaNacimiento?: any;
  cuantosHijos?: number;
  condicionDiscapacidad?: any;
  nombreInstitucionEducativa?: string;
  jornadaEscolar?: any;
  tipoUbicacion?: any;
  nombreUbicacion?: string;
  tipoZona?: any;
  primerNombre?: string;
  segundoNombre?: string;
  primerApellido?: string;
  segundoApellido?: string;

  idTipoRegimen?: number;
  nombreRegimen?: string;
  idParentesco?: number;
  tieneHijos?: string;
  idJornadaEscolar?: number;
}
