import {Item} from '../item';
import {IDireccionForm} from './direccion-form';
import {Validators} from '@angular/forms';

export interface ITramiteRemitenteForm {

  tipoPersona: Item;
  tipoDocumento: Item;
  numeroDocumentoIdentidad: string;
  nit: number;
  razonSocial: string;
  tratamientoCortesia: Item;
  actuaCalidad: Item;
  nombreApellidos: string;
  tipoTelefono: Item;
  indicativo: number;
  numeroTelefono: string;
  extencion: number;
  correoElectronico: string;
  tipoEmpleador: Item;
  codigoActividad: Item;
  descripcionCodigoActividad: string;
  idTipoEmpresa: Item;
  autorizaEnvioViaCorreoElectrinico: boolean;
  autorizaActosAdministrativosElectrinico: boolean;
  primerNombre: string;
  segundoNombre: string;
  primerApellido: string;
  segundoApellido: string;
  datosRepresentanteLegal: {
    primerNombreRl: string,
    segundoNombreRl: string,
    primerApellidoRl: string,
    segundoApellidoRl: string,
    tipoDocumento: Item;
    numeroDocumentoIdentidad: string;
    nit: string
  };

  listaDirecciones: Array<IDireccionForm>;

}
