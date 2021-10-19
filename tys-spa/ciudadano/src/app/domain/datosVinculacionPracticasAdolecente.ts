import {HorarioLaboral} from './horario-laboral';

export interface DatosVinculacionPracticasAdolecente {
  nombrePractica: string;
  practicaGratuita: boolean;
  objectoPractica: Array<any>;
  descripcionDetallada: string;
  horarioLaboral: HorarioLaboral;
  nombreEntidadInstitucion: string;
  formaEntrega: string;
  lugar: string;
  idPeriodicidadReconocimiento: number;
  idFormaEntrega: number;
}
