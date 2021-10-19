import {Item} from '../item';
import {IConstante} from 'app/domain/constante';
import {Validators} from '@angular/forms';
import {ActividadEconomica} from '../../../../domain/ActividadEconomica';
import {DatosPracticaAdolecente} from './datosPracticaAdolecente';

export interface ITramiteForm {

  tipoGestion?: number;
  tipoTramite: Item;
  numeroRadicado: string;
  fechaRadicado: string;
  justificacionSolicitud: Item;
  gradoAsociacion: Item;
  tipoSolicitud: Item;
  tienePactos: any;
  tieneReglamento: any;
  tieneOrganizacionesSindicales: Item;
  radicadaPor: Item;
  tipoSocietarioSas: Item;
  circunstanciasDescripcion: string;
  objetoSaludDescripcion: string;

  datosNotificacionTrabajador: {
    nombreTrabajador: string;
    emailTrabajador: string;
    tieneDirecciones: boolean;
    justaCausaDescripcion: string;
    justaCausaId: number;
    primerNombre: string;
    segundoNombre: string;
    primerApellido: string;
    segundoApellido: string;
  };

  tieneDireccionesTerritoriales: boolean;
  tieneNumerosRadicadosRelacionados: boolean;
  listaJustaCausa: Array<any>;

  autorizacionTrabajoAdolescente: {
    nombreCompleto: string;
    tipoDocumento: Item;
    nroDocumentoIdentidad: string;
    tipoTelefono: Item;
    indicador: string;
    telefono: string;
    ultimoGradoCursado: string;
    edad: Item;
    esUnMenor: boolean;
    fechaNacimiento: any;
    genero: any;
    cuantosHijos: number;
    condicionDiscapacidad: any;
    nombreInstitucionEducativa: string;
    jornadaEscolar: any;
    primerNombre: string;
    segundoNombre: string;
    primerApellido: string;
    segundoApellido: string;
    listaDirecciones: Array<any>;
    tieneHijos: any;

    datosResidencia: {
      direccion: string;
      tipoUbicacion: any;
      nombreUbicacion: string;
      tipoZona: any;
      departamento: any;
      municipio: any;
      tipoTelefono: any;
      indicador: string;
      telefono: string;
      correoElectronico: string;
    }

    representanteLegal: {
      nombreCompleto: string;
      parentesco: any;
      otroParentesco: string;
      tipoDocumento: Item;
      nroDocumentoIdentidad: string;
      nit: string;
      primerNombre: string;
      segundoNombre: string;
      primerApellido: string;
      segundoApellido: string;
    },

    tipoCondicionesVinculacion: {
      tipoVinculacion: Item;
      tipoActividad: Item; // Este campo es obligatorio cuando el niño; niña o adolescente es menor de 15 años
      remuneracion: number;
      auxilioTransporte: number;
      formaPago: Item; // Este campo es obligatorio cuando el niño; niña o adolescente es menor de 15 años
      cargo: string;
      cantidadHorasTrabajoSemanal: number;
      lugarTrabajoDescripcion: string;
      horarioLaboral: [{dia: string, mi: string, mh: string, ti: string, th: string, total: string}],
      sumHours: string;
    },

    datosEmpleador: {
      tipoEmpleador: any;
      nombreRazonSocial: string;
      nroDocumentoNit: string;
      nombreRepresentanteLegalDatosEmpleador: string;
      direccion: string;
      telefonoDatosEmpleador: string;
      celularDatosEmpleador: string,
      correoElectronicoDatosEmpleador: string;
      departamentoDatosEmpleador: any;
      municipioDatosEmpleador: any;
    }
  };

  autorizacionEmpresasTemporales: {

    informacionEscrituraPublica: {
      nroEscritura: string;
      fechaExpedicion: string;
      notaria: number;
      departamento: number;
      municipio: number;
      divisionActividadEconomica: ActividadEconomica,
      grupoActividadEconomica: ActividadEconomica
    }
    informacionPolizaGarantia: {
      nroPoliza: string;
      valorPoliza: number;
      nombreAseguradora: string;
      fechaInicioVigencia: string;
      fechaFinVigencia: string
    }
    informacionRepresentanteLegal: {
      celular: string;
      email: string;
      extension: string;
      indicativo: string;
      nombreCompleto: string;
      telefono: string;
      tipoDocumento: number;
      tipoSolicitante: number;
      nroDocumentoIdentidad: string;
      verificacionNroDocumento: string;
      primerNombre: string;
      segundoNombre: string;
      primerApellido: string;
      segundoApellido: string;
    };
  };

  listaDireccionesVinculacion: Array<any>;
  listaDireccionesSucursales: Array<any>;
  listaDireccionesTrabajador: Array<any>;
  listaDireccionesAdolescente: Array<any>;
  listaDireccionesTerritoriales: Array<IConstante>;
  listaOrganizacionesSindicales: Array<any>;
  listaRadicadosReferidos: Array<{ nombre: string }>;

  listaSocios?: Array<{
    nombreCompleto: string;
    nroDocumentoIdentidad: number;
    tipoDocumento: Item;
    verificacionNroDocumento: string;
    primerNombre: string;
    segundoNombre: string;
    primerApellido: string;
    segundoApellido: string;
  }>;

  // datos adolecente
  datosBasicosAdolecente: DatosPracticaAdolecente;
  strJsonDatosBasicosPdf: string;
}
