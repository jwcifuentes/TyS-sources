import {Empresa} from './empresa';
import {Persona} from './persona';
import {SocioEmpresa} from './socio-empresa';
import {Direccion} from './direccion';

export interface DatosEmpresa {
  empresa: Empresa;
  listaPersonas?: Array<Persona>;
  listaSocios?: Array<SocioEmpresa>;
  direccionSucursal?: Direccion;
  datosEmpleador?: any;
}
