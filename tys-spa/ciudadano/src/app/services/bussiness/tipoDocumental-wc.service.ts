import {Injectable} from '@angular/core';
import {Api} from '../infrastructure/api';
import {CoreEntity} from '../infrastructure/core-entity.service';
import {Observable} from 'rxjs/Observable';
import {IConstante} from '../../domain/constante';
import {TipoDocumentalTramite} from '../../domain/tipo-documental-tramite';

@Injectable()
export class TipoDocumentalWcService extends CoreEntity<TipoDocumentalTramite> {

  constructor(protected readonly http: Api) {
    super(http, 'tiposDocumentales-wc/');
  }

  listByTramite(idTramite: number): Observable<Array<IConstante>> {
    return this.http.get(`${this.uri}/${idTramite}`);
  }

}
