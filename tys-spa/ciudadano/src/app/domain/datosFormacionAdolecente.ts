import {Direccion} from './direccion';

export interface DatosFormacionAdolecente {
  nombreInstitucion: string;
  nit: string;
  idTipoEducacion: number;
  idTipoInstitucion: number;
  numeroTelefonico: string;
  direccion: Direccion;
  nombreProgramaAcademico: string;
  grado: string;
  idPeriodoAcademico: number;
  anio: string;
  fechaInicio: any;
  fechaFin: any;
}
