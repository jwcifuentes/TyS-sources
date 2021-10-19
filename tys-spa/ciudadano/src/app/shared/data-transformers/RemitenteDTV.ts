import {CorrespondenciaTramite} from '../../domain/correspondencia-tramite';
import {Remitente} from '../../domain/remitente';
import {IDireccionForm} from './interfaces/form/direccion-form';
import {Direccion} from '../../domain/direccion';
import {ITramiteRemitenteForm} from './interfaces/form/tramite-remitente-form';
import {Persona} from '../../domain/persona';

export class RemitenteDTV {

  private date: Date;

  constructor(public source: ITramiteRemitenteForm) {
    this.date = new Date();
  }

  getDirecciones(listaDirecciones) {
    const direcciones = [];
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
          ciudad: direccion.ciudad,
        });
      }

    });
    return direcciones;
  }

  getRemitente(): CorrespondenciaTramite {
    return {
      remitente: <Remitente> {
        idtipoPersona: this.source.tipoPersona ? this.source.tipoPersona.id : null,
        idtratamientoCortesia: this.source.tratamientoCortesia ? this.source.tratamientoCortesia.id : null,
        idenCalidadDe: this.source.actuaCalidad ? this.source.actuaCalidad.id : null,
        idtipoDocumentoIdentidad: this.source.tipoDocumento ? this.source.tipoDocumento.id : null,
        idtipoTelefono: this.source.tipoTelefono ? this.source.tipoTelefono.id : null,
        nit: this.source.nit,
        nombre: this.source.nombreApellidos,
        extension: this.source.extencion,
        telefono: this.source.numeroTelefono,
        indicativo: this.source.indicativo,
        razonSocial: this.source.razonSocial,
        correoElectronico: this.source.correoElectronico,
        numeroDocumento: this.source.numeroDocumentoIdentidad,
        direccion:  this.getDirecciones(this.source.listaDirecciones)[0],
        primerNombre: this.source.primerNombre,
        segundoNombre: this.source.segundoNombre,
        primerApellido: this.source.primerApellido,
        segundoApellido: this.source.segundoApellido
      }
    };
  }

  getRepresentanteLegal() {
    if (this.source.datosRepresentanteLegal) {
      const datosRepresentanteLegal = this.source.datosRepresentanteLegal;
      return [<Persona> {
        idTipoPersona: 'RLEMP',
        nombreCompleto: this.getNombreCompleto([datosRepresentanteLegal.primerNombreRl, datosRepresentanteLegal.segundoNombreRl,
          datosRepresentanteLegal.primerApellidoRl, datosRepresentanteLegal.segundoApellidoRl]),
        primerNombre: datosRepresentanteLegal.primerNombreRl,
        segundoNombre: datosRepresentanteLegal.segundoNombreRl,
        primerApellido: datosRepresentanteLegal.primerApellidoRl,
        segundoApellido: datosRepresentanteLegal.segundoApellidoRl,
        idTipoIdentificacion: datosRepresentanteLegal.tipoDocumento.id,
        numeroIdentificacion: datosRepresentanteLegal.numeroDocumentoIdentidad && datosRepresentanteLegal.numeroDocumentoIdentidad != null ? datosRepresentanteLegal.numeroDocumentoIdentidad : datosRepresentanteLegal.nit,
        nit: datosRepresentanteLegal.nit
      }];
    }
    return [];
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
