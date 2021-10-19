import {Injectable} from '@angular/core';
import {Api} from '../infrastructure/api';
import {CoreEntity} from '../infrastructure/core-entity.service';
import {IConstante} from '../../domain/constante';
import {Observable} from 'rxjs/Observable';
import { Tramite } from '../../domain/tramite';
import 'rxjs/add/operator/catch';

@Injectable()
export class TramiteWcService extends CoreEntity<IConstante> {

  gestionarTramiteUri = 'gestionar-tramites-wc';
  obenterDatosDocumentoUrl = 'obtenerDatosDocumento';
  documentosTramiteUrl = 'documentosTramite';
  documentosAdicionalesTramiteUrl = 'documentosAdicionales';
  byteDocumentosTramiteUrl = 'byteDocumentosTramite';
  actualizarDocsTramiteUrl = 'actualizarDocumentos';
  buscarEmpresaUrl = 'empresa';
  buscarRemitenteUrl = 'remitente';
  consultarNradicadoARelacionar = 'nradicadoARelacionar';
  listarDocumentosTramitesUrl= 'listarDocumentosTramites';

  constructor(protected readonly http: Api) {
    super(http, 'tramites-servicios-wc/');
  }

  consultarTramitesRadicados(payload: { numeroRadicado: string, codigoVerificacion: string }) {
    const url = `${this.gestionarTramiteUri}/${payload.numeroRadicado}/${payload.codigoVerificacion}`;
    return this.http.get<Tramite>(url).catch((error) => {
      return Observable.throw(error);
    });
  }

  consultarTramitesRadicadosARelacionar( numeroRadicado: string) {
    const url = `${this.gestionarTramiteUri}/${this.consultarNradicadoARelacionar}/${numeroRadicado}`;
    /*return this.http.get<Tramite>(url).catch((error) => {
      return Observable.throw(error);
    });*/

    return this.http.get(url).catch((error) => {
      return Observable.throw(error);
    });
  }

  consultarDocumentosAdicionales(numeroRadicado) {
    return this.http.get(`${this.gestionarTramiteUri}/${this.documentosAdicionalesTramiteUrl}/${numeroRadicado}`).catch((error) => {
      return Observable.throw(error);
    });
  }

  obtenerDatosDocumento(numeroRadicado) {
    return this.http.get(`${this.gestionarTramiteUri}/${this.obenterDatosDocumentoUrl}/${numeroRadicado}`).catch((error) => {
      return Observable.throw(error);
    });
  }

  obtenerDocumentosTramite(payload: any) {
    return this.http.post(`${this.gestionarTramiteUri}/${this.documentosTramiteUrl}`, payload).catch((error) => {
      return Observable.throw(error);
    });
  }

  actualizarDocumentosTramite(payload: any) {
    return this.http.post(`${this.gestionarTramiteUri}/${this.actualizarDocsTramiteUrl}`, payload).catch((error) => {
      return Observable.throw(error);
    });
  }

  obtenerByteDocumentosTramite(idFileNet: string) {
    const url = `${this.gestionarTramiteUri}/${this.byteDocumentosTramiteUrl}/${idFileNet}`;
    // TODO: Quitar el mime type cuando se ajuste el servicio del tercero
    return this.http.get(url).map((value: any) => {
      value.base64 = 'data:application/pdf;base64,' + <string>value.base64;
      return value;
    }).catch((error) => {
      return Observable.throw(error);
    });
  }

  salvarTramitesRadicados(payload: any) {
    console.log(payload);
    return this.http.post(`${this.gestionarTramiteUri}/`, payload).catch((error) => {
      if (error.status === 400) {
        return [];
      }
      return Observable.throw(error);
    });
  }

  buscarEmpresa(nit: string) {
    return this.http.get(`${this.gestionarTramiteUri}/${this.buscarEmpresaUrl}/${nit}`).catch((error) => {
      return Observable.throw(error);
    });
  }

  buscarRemitente(nit: string) {
    return this.http.get(`${this.gestionarTramiteUri}/${this.buscarEmpresaUrl}/${this.buscarRemitenteUrl}/${nit}`).catch((error) => {
      return Observable.throw(error);
    });
  }
}
