import {ITramiteForm} from './interfaces/form/tramite-form'
import {Empresa} from 'app/domain/empresa';
import {Persona} from 'app/domain/persona';
import {Direccion} from 'app/domain/direccion';
import {IDireccionForm} from './interfaces/form/direccion-form';
import {CondicionVinculacion} from 'app/domain/condicion-vinculacion';
import {HorarioLaboral} from 'app/domain/horario-laboral';
import {SolicitudTramite} from 'app/domain/solicitud-tramite';
import {DatosEmpresa} from 'app/domain/datos-empresa';
import {DireccionTerritorial} from 'app/domain/direccion-territorial';
import {IConstante} from 'app/domain/constante';
import {DatosFormacionAdolecente} from '../../domain/datosFormacionAdolecente';
import {DatosEscenarioPractica} from '../../domain/datosEscenarioPractica';
import {DatosVinculacionPracticasAdolecente} from '../../domain/datosVinculacionPracticasAdolecente';

export class TramiteDTV {

  private date: Date;

  constructor(public source: ITramiteForm) {
    this.date = new Date();
  }

  getTramite(): SolicitudTramite {
    return {
      idTramite: this.source.tipoTramite.id,
      idJustificacionSolicitud: this.source.justificacionSolicitud
      ? this.source.justificacionSolicitud.id : null,
      idGradoAsociacion: this.source.gradoAsociacion
      ? this.source.gradoAsociacion.id : null,
      idTipoSolicitud: this.source.tipoSolicitud
      ? this.source.tipoSolicitud.id : null,
      valCircunstancias: this.source.circunstanciasDescripcion,
      valObjetoSolicitud: this.source.objetoSaludDescripcion,
      idJustaCausa: this.source.datosNotificacionTrabajador
      ? this.source.datosNotificacionTrabajador.justaCausaId : null
    };
  }

  getEmpresa() {
    if (this.source.autorizacionEmpresasTemporales
      && this.source.autorizacionEmpresasTemporales.informacionEscrituraPublica
      && this.source.autorizacionEmpresasTemporales.informacionPolizaGarantia) {
      return <Empresa> {
        numeroEscritura: this.source.autorizacionEmpresasTemporales.informacionEscrituraPublica.nroEscritura,
        fechaExpedicionEscritura: this.source.autorizacionEmpresasTemporales.informacionEscrituraPublica.fechaExpedicion,
        idDepartamentoExpedicion: this.source.autorizacionEmpresasTemporales.informacionEscrituraPublica.departamento,
        idMunicipioExpedicion: this.source.autorizacionEmpresasTemporales.informacionEscrituraPublica.municipio,
        idNotaria: this.source.autorizacionEmpresasTemporales.informacionEscrituraPublica.notaria,
        numeroPoliza: this.source.autorizacionEmpresasTemporales.informacionPolizaGarantia.nroPoliza,
        valorPoliza: this.source.autorizacionEmpresasTemporales.informacionPolizaGarantia.valorPoliza,
        nombreAseguradora: this.source.autorizacionEmpresasTemporales.informacionPolizaGarantia.nombreAseguradora,
        fechaInicialPoliza: this.source.autorizacionEmpresasTemporales.informacionPolizaGarantia.fechaInicioVigencia,
        fechaFinPoliza: this.source.autorizacionEmpresasTemporales.informacionPolizaGarantia.fechaFinVigencia,
        idTipoGestion: this.source.tipoGestion,
        grupoActividadEconomica: this.source.autorizacionEmpresasTemporales.informacionEscrituraPublica.grupoActividadEconomica
      };
    }

    return null;
  }

  getRepresentanteLegalEmpresa() {
    if (this.source.autorizacionEmpresasTemporales) {
      return <Persona> {
        idTipoPersona: 'RLEMP',
        nombreCompleto: this.source.autorizacionEmpresasTemporales.informacionRepresentanteLegal.nombreCompleto,
        idTipoIdentificacion: this.source.autorizacionEmpresasTemporales.informacionRepresentanteLegal.tipoDocumento,
        numeroIdentificacion: this.source.autorizacionEmpresasTemporales.informacionRepresentanteLegal.nroDocumentoIdentidad,
        valTelefono: this.source.autorizacionEmpresasTemporales.informacionRepresentanteLegal.telefono,
        valIndicativo: this.source.autorizacionEmpresasTemporales.informacionRepresentanteLegal.indicativo,
        valExtension: this.source.autorizacionEmpresasTemporales.informacionRepresentanteLegal.extension,
        valCelular: this.source.autorizacionEmpresasTemporales.informacionRepresentanteLegal.celular,
        valMail: this.source.autorizacionEmpresasTemporales.informacionRepresentanteLegal.email,
        primerNombre: this.source.autorizacionEmpresasTemporales.informacionRepresentanteLegal.primerNombre,
        segundoNombre: this.source.autorizacionEmpresasTemporales.informacionRepresentanteLegal.segundoNombre,
        primerApellido: this.source.autorizacionEmpresasTemporales.informacionRepresentanteLegal.primerApellido,
        segundoApellido: this.source.autorizacionEmpresasTemporales.informacionRepresentanteLegal.segundoApellido,
      };
    }

    return null;
  }

  getSocios() {
    if (this.source.autorizacionEmpresasTemporales) {
      const socios = [];

      this.source.listaSocios.forEach((socio: any) => {
        socios.push(<Persona> {
          idTipoPersona: 'SOCIO',
          idTipoIdentificacion: socio.tipoDocumento.id,
          numeroIdentificacion: socio.nroDocumentoIdentidad,
          nombreCompleto: socio.nombreCompleto,
          primerNombre: socio.primerNombre,
          segundoNombre: socio.segundoNombre,
          primerApellido: socio.primerApellido,
          segundoApellido: socio.segundoApellido
        });
      });

      return socios;
    }

    return null;
  }

  getRepresentanteAdolescente() {
    const authTrabAdolesence = this.source.autorizacionTrabajoAdolescente;
    if (authTrabAdolesence) {
      const representanteLegal = authTrabAdolesence.representanteLegal;
      return <Persona> {
        idTipoPersona: 'RLNNA',
        nombreCompleto: representanteLegal.nombreCompleto,
        idTipoIdentificacion: representanteLegal.tipoDocumento.id,
        numeroIdentificacion: representanteLegal.nroDocumentoIdentidad,
        parentesco: representanteLegal.otroParentesco ? representanteLegal.otroParentesco : representanteLegal.parentesco.nombre,
        primerNombre: representanteLegal.primerNombre,
        segundoNombre: representanteLegal.segundoNombre,
        primerApellido: representanteLegal.primerApellido,
        segundoApellido: representanteLegal.segundoApellido,
        idParentesco: representanteLegal.parentesco.id
      };
    }

    return null;
  }

  getDatosAdolescente() {
    const authTrabAdolesence = this.source.autorizacionTrabajoAdolescente;
    if (authTrabAdolesence) {
      return <Persona> {
        idTipoPersona: 'NNA',
        nombreCompleto: authTrabAdolesence.nombreCompleto,
        idTipoIdentificacion: authTrabAdolesence.tipoDocumento.id,
        numeroIdentificacion: authTrabAdolesence.nroDocumentoIdentidad,
        valCelular: authTrabAdolesence.datosResidencia.telefono,
        idTipoTelefono: authTrabAdolesence.datosResidencia.tipoTelefono.id,
        valIndicativo: authTrabAdolesence.datosResidencia.indicador,
        valEdad: authTrabAdolesence.edad.id,
        ultimoGradoCursado: authTrabAdolesence.ultimoGradoCursado,
        direccionPersona: this.getDirecciones([this.source.listaDireccionesAdolescente[0]])[0],
        fechaNacimiento: authTrabAdolesence.fechaNacimiento,
        genero: authTrabAdolesence.genero.id,
        cuantosHijos: authTrabAdolesence.cuantosHijos,
        condicionDiscapacidad: authTrabAdolesence.condicionDiscapacidad,
        nombreInstitucionEducativa: authTrabAdolesence.nombreInstitucionEducativa,
        jornadaEscolar: authTrabAdolesence.jornadaEscolar.nombre,
        correoElectronico: authTrabAdolesence.datosResidencia.correoElectronico,
        valMail: authTrabAdolesence.datosResidencia.correoElectronico,
        tipoUbicacion: authTrabAdolesence.datosResidencia.tipoUbicacion.id,
        nombreUbicacion: authTrabAdolesence.datosResidencia.nombreUbicacion,
        tipoZona: authTrabAdolesence.datosResidencia.tipoZona.id,
        primerNombre: authTrabAdolesence.primerNombre,
        segundoNombre: authTrabAdolesence.segundoNombre,
        primerApellido: authTrabAdolesence.primerApellido,
        segundoApellido: authTrabAdolesence.segundoApellido,
        tieneHijos : authTrabAdolesence.tieneHijos ? authTrabAdolesence.tieneHijos.value : false,
        idJornadaEscolar : authTrabAdolesence.jornadaEscolar.id
      };
    }

    return null;
  }

  private getDatosNotificacionTrabajador() {
    const datosNotifTrab = this.source.datosNotificacionTrabajador;
    if (datosNotifTrab) {
      return <Persona> {
        idTipoPersona: 'EMP',
        nombreCompleto: datosNotifTrab.nombreTrabajador,
        valMail: datosNotifTrab.emailTrabajador,
        direccionPersona: this.getDirecciones(this.source.listaDireccionesTrabajador)[0],
        primerNombre: datosNotifTrab.primerNombre,
        segundoNombre: datosNotifTrab.segundoNombre,
        primerApellido: datosNotifTrab.primerApellido,
        segundoApellido: datosNotifTrab.segundoApellido,
      };
    }
    return null;
  }
  getOrganizacionesSindicales() {
    if ( !this.source.listaOrganizacionesSindicales ) return;
    const direcciones = this.getDirecciones(this.source.listaOrganizacionesSindicales.map((org) => org.direccion));
    this.source.listaOrganizacionesSindicales.forEach((org, index, arr) => {
      org.direccion = '';
      org.address = direcciones[index];
    });
    return this.source.listaOrganizacionesSindicales;
  }
  getDirecciones(listaDirecciones) {
    const direcciones: Array<Direccion> = [];
    listaDirecciones.forEach((direccion: IDireccionForm) => {
      if (direccion.pais.codigo === 'CO') {
        direcciones.push(<Direccion> {
          codigoPostal: direccion.codigoPostal,
          valDireccion: direccion.direccion,
          idPais: direccion.pais.id,
          idDepartamento: direccion.departamento.id,
          idMunicipio: direccion.municipio.id,
        });
      } else {
        direcciones.push(<Direccion> {
          codigoPostal: direccion.codigoPostal,
          valDireccion: direccion.direccion,
          idPais: direccion.pais.id,
          valCiudad: direccion.ciudad,
        });
      }
    });

    return direcciones;
  }

  getDireccionesTrabajador() {
    return this.source.datosNotificacionTrabajador
    ? this.getDirecciones(this.source.listaDireccionesTrabajador)
    : null;
  }

  getDireccionesVinculacion() {
    return this.source.autorizacionTrabajoAdolescente
    ? this.getDirecciones(this.source.listaDireccionesVinculacion)
    : null;
  }

  getDireccionesAdolescente() {
    return this.source.autorizacionTrabajoAdolescente
    ? this.getDirecciones(this.source.listaDireccionesAdolescente)
    : null;
  }

  getDireccionesSucursales(): Array<Direccion> {
    return this.source.autorizacionEmpresasTemporales
    ? this.getDirecciones(this.source.listaDireccionesSucursales)
    : null;
  }

  getDireccionesTerritoriales(): Array<DireccionTerritorial> {
    const arr: Array<DireccionTerritorial> = [];
    this.source.listaDireccionesTerritoriales.forEach((dir: IConstante) => {
      arr.push(<DireccionTerritorial> {
        id: dir.id,
        nombre: dir.nombre,
        codigo: dir.codigo
      });
    });
    return arr;
  }

  getRadicadosReferidos() {
    const arr: Array<string> = [];
    this.source.listaRadicadosReferidos.forEach((val: any) => {
      arr.push(val.nombre);
    });
    return arr;
  }

  getCondicionVinculacion(): CondicionVinculacion {
    const authTrabAdolesence = this.source.autorizacionTrabajoAdolescente;
    const tipoCondsVinculacion = authTrabAdolesence.tipoCondicionesVinculacion;
    const tipoActividad = tipoCondsVinculacion.tipoActividad;
    const formaPago = tipoCondsVinculacion.formaPago;
    if (this.source.autorizacionTrabajoAdolescente) {
      return {
        valRemuneracion: tipoCondsVinculacion.remuneracion,
        idTipoVinculo: tipoCondsVinculacion.tipoVinculacion.id,
        idTipoActividad: tipoActividad ? tipoActividad.id : null,
        idTipoPago: formaPago ? formaPago.id : null,
        valAuxTransp: tipoCondsVinculacion.auxilioTransporte,
        valDescripcion: tipoCondsVinculacion.lugarTrabajoDescripcion,
        valCargo: tipoCondsVinculacion.cargo,
      };
    }

    return null;
  }

  getHorarioLaboralNNA(): HorarioLaboral {
    if (this.source.autorizacionTrabajoAdolescente) {
      return {
        lunesInicioAM: this.source.autorizacionTrabajoAdolescente.tipoCondicionesVinculacion.horarioLaboral[0].mi,
        lunesFinAM: this.source.autorizacionTrabajoAdolescente.tipoCondicionesVinculacion.horarioLaboral[0].mh,
        lunesInicioPM: this.source.autorizacionTrabajoAdolescente.tipoCondicionesVinculacion.horarioLaboral[0].ti,
        lunesFinPM: this.source.autorizacionTrabajoAdolescente.tipoCondicionesVinculacion.horarioLaboral[0].th,
        lunesTotal: this.source.autorizacionTrabajoAdolescente.tipoCondicionesVinculacion.horarioLaboral[0].total,
        martesInicioAM: this.source.autorizacionTrabajoAdolescente.tipoCondicionesVinculacion.horarioLaboral[1].mi,
        martesFinAM: this.source.autorizacionTrabajoAdolescente.tipoCondicionesVinculacion.horarioLaboral[1].mh,
        martesInicioPM: this.source.autorizacionTrabajoAdolescente.tipoCondicionesVinculacion.horarioLaboral[1].ti,
        martesFinPM: this.source.autorizacionTrabajoAdolescente.tipoCondicionesVinculacion.horarioLaboral[1].th,
        martesTotal: this.source.autorizacionTrabajoAdolescente.tipoCondicionesVinculacion.horarioLaboral[1].total,
        miercolesInicioAM: this.source.autorizacionTrabajoAdolescente.tipoCondicionesVinculacion.horarioLaboral[2].mi,
        miercolesFinAM: this.source.autorizacionTrabajoAdolescente.tipoCondicionesVinculacion.horarioLaboral[2].mh,
        miercolesInicioPM: this.source.autorizacionTrabajoAdolescente.tipoCondicionesVinculacion.horarioLaboral[2].ti,
        miercolesFinPM: this.source.autorizacionTrabajoAdolescente.tipoCondicionesVinculacion.horarioLaboral[2].th,
        miercolesTotal: this.source.autorizacionTrabajoAdolescente.tipoCondicionesVinculacion.horarioLaboral[2].total,
        juevesInicioAM: this.source.autorizacionTrabajoAdolescente.tipoCondicionesVinculacion.horarioLaboral[3].mi,
        juevesFinAM: this.source.autorizacionTrabajoAdolescente.tipoCondicionesVinculacion.horarioLaboral[3].mh,
        juevesInicioPM: this.source.autorizacionTrabajoAdolescente.tipoCondicionesVinculacion.horarioLaboral[3].ti,
        juevesFinPM: this.source.autorizacionTrabajoAdolescente.tipoCondicionesVinculacion.horarioLaboral[3].th,
        juevesTotal: this.source.autorizacionTrabajoAdolescente.tipoCondicionesVinculacion.horarioLaboral[3].total,
        viernesInicioAM: this.source.autorizacionTrabajoAdolescente.tipoCondicionesVinculacion.horarioLaboral[4].mi,
        viernesFinAM: this.source.autorizacionTrabajoAdolescente.tipoCondicionesVinculacion.horarioLaboral[4].mh,
        viernesInicioPM: this.source.autorizacionTrabajoAdolescente.tipoCondicionesVinculacion.horarioLaboral[4].ti,
        viernesFinPM: this.source.autorizacionTrabajoAdolescente.tipoCondicionesVinculacion.horarioLaboral[4].th,
        viernesTotal: this.source.autorizacionTrabajoAdolescente.tipoCondicionesVinculacion.horarioLaboral[4].total,
        sabadoInicioAM: this.source.autorizacionTrabajoAdolescente.tipoCondicionesVinculacion.horarioLaboral[5].mi,
        sabadoFinAM: this.source.autorizacionTrabajoAdolescente.tipoCondicionesVinculacion.horarioLaboral[5].mh,
        sabadoInicioPM: this.source.autorizacionTrabajoAdolescente.tipoCondicionesVinculacion.horarioLaboral[5].ti,
        sabadoFinPM: this.source.autorizacionTrabajoAdolescente.tipoCondicionesVinculacion.horarioLaboral[5].th,
        sabadoTotal: this.source.autorizacionTrabajoAdolescente.tipoCondicionesVinculacion.horarioLaboral[5].total,
        domingoInicioAM: this.source.autorizacionTrabajoAdolescente.tipoCondicionesVinculacion.horarioLaboral[6].mi,
        domingoFinAM: this.source.autorizacionTrabajoAdolescente.tipoCondicionesVinculacion.horarioLaboral[6].mh,
        domingoInicioPM: this.source.autorizacionTrabajoAdolescente.tipoCondicionesVinculacion.horarioLaboral[6].ti,
        domingoFinPM: this.source.autorizacionTrabajoAdolescente.tipoCondicionesVinculacion.horarioLaboral[6].th,
        domingoTotal: this.source.autorizacionTrabajoAdolescente.tipoCondicionesVinculacion.horarioLaboral[6].total,
        totalSemanal: this.source.autorizacionTrabajoAdolescente.tipoCondicionesVinculacion.sumHours
      };
    }

    return null;
  }

  getPersonas(): Array<Persona> {
    const result: Array<Persona> = [];
    const datosAdolescente = this.getDatosAdolescente();
    const representanteLegal = this.getRepresentanteLegalEmpresa();
    const representanteAdolescente = this.getRepresentanteAdolescente();
    const datosNotificacionTrabajador = this.getDatosNotificacionTrabajador();

    if (this.source.autorizacionTrabajoAdolescente) {
      result.push(datosAdolescente);
    }

    if (representanteLegal) {
      result.push(representanteLegal);
    }

    if (representanteAdolescente) {
      result.push(representanteAdolescente);
    }

    if (datosNotificacionTrabajador) {
      result.push(datosNotificacionTrabajador);
    }

    return result;
  }

  getDatosEmpresa(): DatosEmpresa {
    if (this.source.autorizacionEmpresasTemporales) {
      return {
        empresa: this.getEmpresa(),
        listaPersonas: this.getPersonas(),
        listaSocios: this.getSocios(),
        direccionSucursal: this.getDireccionesSucursales()[0],
      };
    } else if (this.source.datosBasicosAdolecente) {
      return {
        empresa: {},
        listaPersonas: this.getPersonasTramitePracticasAdolecente()
      };
    }
    return {
      empresa: {},
      listaPersonas: this.getPersonas(),
      datosEmpleador: this.getDatosEmpleador()
    };
  }

  private getDatosEmpleador(): any {
    if (!this.source.autorizacionTrabajoAdolescente) return null;
    return {
      tipoEmpleador: this.source.autorizacionTrabajoAdolescente.datosEmpleador.tipoEmpleador.id,
      nombreRazonSocial: this.source.autorizacionTrabajoAdolescente.datosEmpleador.nombreRazonSocial,
      nroNit : this.source.autorizacionTrabajoAdolescente.datosEmpleador.nroDocumentoNit,
      nombreRepresentanteLegat: this.source.autorizacionTrabajoAdolescente.datosEmpleador.nombreRepresentanteLegalDatosEmpleador,
      direccion : this.source.autorizacionTrabajoAdolescente.datosEmpleador.direccion,
      telefono: this.source.autorizacionTrabajoAdolescente.datosEmpleador.telefonoDatosEmpleador,
      celular: this.source.autorizacionTrabajoAdolescente.datosEmpleador.celularDatosEmpleador,
      email : this.source.autorizacionTrabajoAdolescente.datosEmpleador.correoElectronicoDatosEmpleador,
      departamento: this.source.autorizacionTrabajoAdolescente.datosEmpleador.departamentoDatosEmpleador.id,
      municipio: this.source.autorizacionTrabajoAdolescente.datosEmpleador.municipioDatosEmpleador.id
    };
  }

  private getPersonasTramitePracticasAdolecente(): Array<Persona> {
    const result: Array<Persona> = [];
    const adolecente: Persona = this.getPersonaAdolecente();
    const representateAdolecente = this.getRepresentanteAdolecente();
    const getRepresentanteLegalInstitucion = this.getRepresentanteLegalInstitucion();
    const monitor = this.getDatosMonitor();
    const getDatosRLEscenarioPractica = this.getDatosRLEscenarioPractica();
    const getDatostutorEscenarioPractica = this.getDatostutorEscenarioPractica();

    result.push(adolecente);
    result.push(representateAdolecente);
    result.push(getRepresentanteLegalInstitucion);
    result.push(monitor);
    result.push(getDatosRLEscenarioPractica);
    result.push(getDatostutorEscenarioPractica);
    return result;
  }

  private getPersonaAdolecente(): Persona {
    const datosAdolecente = this.source.datosBasicosAdolecente;
    return <Persona>{
      idTipoPersona: 'ADOL',
      nombreCompleto: datosAdolecente.nombreCompleto,
      primerNombre: datosAdolecente.primerNombre,
      segundoNombre: datosAdolecente.segundoNombre,
      primerApellido: datosAdolecente.primerApellido,
      segundoApellido: datosAdolecente.segundoApellido,
      idTipoIdentificacion: datosAdolecente.tipoDocumento.id,
      numeroIdentificacion: datosAdolecente.nroDocumentoIdentidad,
      valEdad: datosAdolecente.edad.id,
      idTipoRegimen: datosAdolecente.datosAfiliacionSalud.tipoRegimen.id,
      nombreRegimen: datosAdolecente.datosAfiliacionSalud.nombre,
      direccionPersona: this.getDirecciones(datosAdolecente.datosResidencia.direcciones)[0],
      valTelefono: datosAdolecente.datosResidencia.telefonoFijo,
      valCelular: datosAdolecente.datosResidencia.telefonoCelular,
      valMail: datosAdolecente.datosResidencia.email,
    };
  }
  private getRepresentanteAdolecente(): Persona {
    const datosRepresentante = this.source.datosBasicosAdolecente.datosRepresentanteLegal;
    return <Persona> {
      idTipoPersona: 'RLADOL',
      nombreCompleto: datosRepresentante.nombreCompleto,
      primerNombre: datosRepresentante.primerNombre,
      segundoNombre: datosRepresentante.segundoNombre,
      primerApellido: datosRepresentante.primerApellido,
      segundoApellido: datosRepresentante.segundoApellido,
      parentesco: datosRepresentante.otroParentesco ? datosRepresentante.otroParentesco : datosRepresentante.parentesco.nombre,
      idParentesco: datosRepresentante.parentesco.id,
      idTipoIdentificacion: datosRepresentante.tipoDocumento.id,
      numeroIdentificacion: datosRepresentante.nroDocumentoIdentidad
    };
  }
  private getRepresentanteLegalInstitucion(): Persona {
    const datosRLInstitucion = this.source.datosBasicosAdolecente.datosFormacionComplementaria;
    return <Persona>{
      idTipoPersona: 'RLINST',
      nombreCompleto: this.getNombreCompleto([datosRLInstitucion.primerNombreRepresentante,
        datosRLInstitucion.segundoNombreRepresentante,
        datosRLInstitucion.primerApellidoRepresentante,
        datosRLInstitucion.segundoApellidoRepresentante
      ]),
      primerNombre: datosRLInstitucion.primerNombreRepresentante,
      segundoNombre: datosRLInstitucion.segundoNombreRepresentante,
      primerApellido: datosRLInstitucion.primerApellidoRepresentante,
      segundoApellido: datosRLInstitucion.segundoApellidoRepresentante
    };
  }
  private getDatosMonitor(): Persona {
    const datosMonitor = this.source.datosBasicosAdolecente.datosFormacionComplementaria.datosMonitor;
    return <Persona>{
      idTipoPersona: 'MONITOR',
      nombreCompleto: this.getNombreCompleto([datosMonitor.primerNombre, datosMonitor.segundoNombre,
      datosMonitor.primerApellido, datosMonitor.segundoApellido]),
      primerNombre: datosMonitor.primerNombre,
      segundoNombre: datosMonitor.segundoNombre,
      primerApellido: datosMonitor.primerApellido,
      segundoApellido: datosMonitor.segundoApellido,
      idTipoIdentificacion: datosMonitor.tipoDocumento.id,
      numeroIdentificacion: datosMonitor.nroDocumentoIdentidad
    };
  }
  private getDatosRLEscenarioPractica(): Persona {
    const datosRLEscenario = this.source.datosBasicosAdolecente.datosEscenarioPractica;
    return <Persona>{
      idTipoPersona: 'RLESC',
      nombreCompleto: this.getNombreCompleto([datosRLEscenario.primerNombreRL, datosRLEscenario.segundoNombreRL,
        datosRLEscenario.primerApellidoRL, datosRLEscenario.segundoApellidoRL]),
      primerNombre: datosRLEscenario.primerNombreRL,
      segundoNombre: datosRLEscenario.segundoNombreRL,
      primerApellido: datosRLEscenario.primerApellidoRL,
      segundoApellido: datosRLEscenario.segundoApellidoRL,
      idTipoIdentificacion: datosRLEscenario.tipoDocumentoRL.id,
      numeroIdentificacion: datosRLEscenario.numeroDocumentoRL
    };
  }
  private getDatostutorEscenarioPractica(): Persona {
    const datosTutor = this.source.datosBasicosAdolecente.datosEscenarioPractica;
    return <Persona>{
      idTipoPersona: 'TUTORESC',
      nombreCompleto: this.getNombreCompleto([datosTutor.primerNombreTutor, datosTutor.segundoNombreTutor,
      datosTutor.primerApellidoTutor, datosTutor.segundoApellidoRL]),
      primerNombre: datosTutor.primerNombreTutor,
      segundoNombre: datosTutor.segundoNombreTutor,
      primerApellido: datosTutor.primerApellidoTutor,
      segundoApellido: datosTutor.segundoApellidoTutor,
      idTipoIdentificacion: datosTutor.tipoDocumentoTutor.id,
      numeroIdentificacion: datosTutor.numeroDocumentoTutor
    };
  }

  getDatosFormacion(): DatosFormacionAdolecente {
    if (!this.source.datosBasicosAdolecente) return null;
    const datosFromacion = this.source.datosBasicosAdolecente.datosFormacionComplementaria;
    return <DatosFormacionAdolecente>{
      nombreInstitucion: datosFromacion.nombreInstitucionEducativa,
      nit: datosFromacion.nit,
      idTipoEducacion: datosFromacion.tipoEducacion.id,
      idTipoInstitucion: datosFromacion.tipoInstitucion.id,
      numeroTelefonico: datosFromacion.numeroTelefonico,
      direccion: this.getDirecciones(datosFromacion.direcciones)[0],
      nombreProgramaAcademico: datosFromacion.nombreProgramaAcademico,
      grado: datosFromacion.grado,
      idPeriodoAcademico: datosFromacion.periodoAcademico.id,
      anio: datosFromacion.anio,
      fechaInicio: datosFromacion.duracionPractica.fechaInicio,
      fechaFin: datosFromacion.duracionPractica.fechaFin
    };
  }

  getDatosEscenarioPractica(): DatosEscenarioPractica {
    if (!this.source.datosBasicosAdolecente) return null;
    const datosEscenarioPractica = this.source.datosBasicosAdolecente.datosEscenarioPractica;
    return <DatosEscenarioPractica> {
      nombreEntidad: datosEscenarioPractica.nombreEntidad,
      idTipoEntidad: datosEscenarioPractica.tipoEntidad.id,
      idTipoIdentificacion: datosEscenarioPractica.tipoIdentificacion.id,
      numeroIdentificacion: datosEscenarioPractica.identificacion,
      direccion: this.getDirecciones(datosEscenarioPractica.direcciones)[0],
      correo: datosEscenarioPractica.correo,
      telefonoFijo: datosEscenarioPractica.telefonoFijo,
      telefonoCelular: datosEscenarioPractica.celular
    };
  }

  getDatosVinculacionPracticas(): DatosVinculacionPracticasAdolecente {
    if (!this.source.datosBasicosAdolecente) return null;
    const datosVinculacion = this.source.datosBasicosAdolecente.datosCondicionVinculacion;
    return <DatosVinculacionPracticasAdolecente>{
      nombrePractica: datosVinculacion.nombrePractica,
      practicaGratuita: datosVinculacion.practicaGratuita,
      descripcionDetallada: datosVinculacion.descripcionDetallada,
      nombreEntidadInstitucion : datosVinculacion.nombreEntidadInstitucion,
      formaEntrega: datosVinculacion.datosAuxilio.cual ?
        datosVinculacion.datosAuxilio.cual : datosVinculacion.datosAuxilio.formaEntrega.nombre,
      lugar: datosVinculacion.datosAuxilio.lugar,
      idPeriodicidadReconocimiento: datosVinculacion.datosAuxilio.periodicidadReconocimiento.id,
      objectoPractica: datosVinculacion.objetoPractica,
      horarioLaboral: this.getHorarioLaboral(datosVinculacion.horarioLaboral, datosVinculacion.sumHours),
      idFormaEntrega: datosVinculacion.datosAuxilio.formaEntrega.id
    };
  }
  getHorarioLaboral(horarioLaboral: Array<any>, sumHours: string): HorarioLaboral {
    return {
      lunesInicioAM: horarioLaboral[0].mi,
      lunesFinAM: horarioLaboral[0].mh,
      lunesInicioPM: horarioLaboral[0].ti,
      lunesFinPM: horarioLaboral[0].th,
      lunesTotal: horarioLaboral[0].total,
      martesInicioAM: horarioLaboral[1].mi,
      martesFinAM: horarioLaboral[1].mh,
      martesInicioPM: horarioLaboral[1].ti,
      martesFinPM: horarioLaboral[1].th,
      martesTotal: horarioLaboral[1].total,
      miercolesInicioAM: horarioLaboral[2].mi,
      miercolesFinAM: horarioLaboral[2].mh,
      miercolesInicioPM: horarioLaboral[2].ti,
      miercolesFinPM: horarioLaboral[2].th,
      miercolesTotal: horarioLaboral[2].total,
      juevesInicioAM: horarioLaboral[3].mi,
      juevesFinAM: horarioLaboral[3].mh,
      juevesInicioPM: horarioLaboral[3].ti,
      juevesFinPM: horarioLaboral[3].th,
      juevesTotal: horarioLaboral[3].total,
      viernesInicioAM: horarioLaboral[4].mi,
      viernesFinAM: horarioLaboral[4].mh,
      viernesInicioPM: horarioLaboral[4].ti,
      viernesFinPM: horarioLaboral[4].th,
      viernesTotal: horarioLaboral[4].total,
      sabadoInicioAM: horarioLaboral[5].mi,
      sabadoFinAM: horarioLaboral[5].mh,
      sabadoInicioPM: horarioLaboral[5].ti,
      sabadoFinPM: horarioLaboral[5].th,
      sabadoTotal: horarioLaboral[5].total,
      totalSemanal: sumHours
    };
  }

  public getNombreCompleto(nombres: string[]): string {
    let nombreCompleto = '';
    nombres.forEach((nombre) => {
      if (nombre) {
        nombreCompleto += ' ' + nombre;
      }
    });
    return nombreCompleto.trim();
  }
}
