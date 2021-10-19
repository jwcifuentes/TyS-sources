import {Item} from '../item';
import {IConstante} from '../../../../domain/constante';

export interface DatosPracticaAdolecente {
  nombreCompleto: string;
  primerNombre: string;
  segundoNombre: string;
  primerApellido: string;
  segundoApellido: string;
  tipoDocumento: Item;
  nroDocumentoIdentidad: string;
  edad: Item;
  datosAfiliacionSalud: {
    tipoRegimen: Item;
    nombre: string;
  };
  datosResidencia: {
    direcciones: Array<any>;
    telefonoFijo: string;
    telefonoCelular: string;
    email: string;
  };
  datosRepresentanteLegal: {
    nombreCompleto: string;
    primerNombre: string;
    segundoNombre: string;
    primerApellido: string;
    segundoApellido: string;
    parentesco: IConstante;
    otroParentesco: string;
    tipoDocumento: Item;
    nroDocumentoIdentidad: string;
  };
  datosFormacionComplementaria: {
    nombreInstitucionEducativa: string;
    nit: string;
    tipoEducacion: Item;
    tipoInstitucion: Item;
    numeroTelefonico: string;
    primerNombreRepresentante: string;
    segundoNombreRepresentante: string;
    primerApellidoRepresentante: string;
    segundoApellidoRepresentante: string;
    nombreCompletoRepresentante: string;
    direcciones: Array<any>;
    nombreProgramaAcademico: string;
    grado: string;
    periodoAcademico: Item;
    anio: string;
    duracionPractica: {
      fechaInicio: string;
      fechaFin: string;
    };
    datosMonitor: {
      primerNombre: string;
      segundoNombre: string;
      primerApellido: string;
      segundoApellido: string;
      tipoDocumento: Item;
      nroDocumentoIdentidad: string;
      nombreCompletoMonitor: string;
    };
  };
  datosEscenarioPractica: {
    nombreEntidad: string;
    tipoEntidad: Item;
    tipoIdentificacion: Item;
    identificacion: string
    direcciones: Array<any>;
    correo: string;
    telefonoFijo: string;
    celular: string;
    primerNombreRL: string;
    segundoNombreRL: string;
    primerApellidoRL: string;
    segundoApellidoRL: string;
    tipoDocumentoRL: Item;
    numeroDocumentoRL: string;
    primerNombreTutor: string;
    segundoNombreTutor: string;
    primerApellidoTutor: string;
    segundoApellidoTutor: string;
    tipoDocumentoTutor: Item;
    numeroDocumentoTutor: string;
    nombreCompletoTutor: string;
    nombreCompletoRL: string;
  };
  datosCondicionVinculacion: {
    nombrePractica: string;
    practicaGratuita: boolean;
    objetoPractica: [
      {isSelected: boolean, objetoPractica: string, nombre: string, id: number}
    ];
    descripcionDetallada: string;
    tieneHorasTrabajoSemanal: boolean;
    horasTrabajoSemanal: number;
    horarioLaboral: [{dia: string, mi: string, mh: string, ti: string, th: string, total: string}],
    sumHours: string;
    nombreEntidadInstitucion: string
    datosAuxilio: {
      formaEntrega: IConstante;
      cual: string;
      lugar: string;
      periodicidadReconocimiento: Item;
    };
  };
}
