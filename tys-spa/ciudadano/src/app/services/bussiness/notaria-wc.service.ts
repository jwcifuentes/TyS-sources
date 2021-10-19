import {Injectable} from '@angular/core';
import {Api} from '../infrastructure/api';
import {environment} from '../../../environments/environment';
import {CoreEntity} from '../infrastructure/core-entity.service';
import {TipoDocumental} from '../../domain/tipo-documental';
import {IConstante} from '../../domain/constante';
import {Observable} from 'rxjs/Observable';
import {Response} from '@angular/http';
import {IDepartamento} from '../../domain/departamento';
import {IMunicipio} from '../../domain/municipio';

@Injectable()
export class NotariaWcService extends CoreEntity<IConstante> {

  constructor(protected readonly http: Api) {
    super(http, 'notarias-wc/');
  }

  listByMunicipio(idMunicipio: string): Observable<Array<IConstante>> {
    return this.http.get(`${this.uri}${idMunicipio}`);
  }

}
