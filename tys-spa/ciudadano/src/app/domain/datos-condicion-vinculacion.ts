import {CondicionVinculacion} from './condicion-vinculacion';
import {HorarioLaboral} from './horario-laboral';
import {Direccion} from './direccion';

export interface DatosCondicionVinculacion {
  condicionVinculacion: CondicionVinculacion;
  direccionVinculo: Direccion;
  horarioLaboral: HorarioLaboral;
}
