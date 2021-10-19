import {Direccion} from './direccion';

export interface DatosEscenarioPractica {
  nombreEntidad: string;
  idTipoEntidad: number;
  idTipoIdentificacion: number;
  numeroIdentificacion: string;
  direccion: Direccion;
  correo: string;
  telefonoFijo: string;
  telefonoCelular: string;
}
