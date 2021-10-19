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
export class MunicipioWcService extends CoreEntity<IMunicipio> {

  constructor(protected readonly http: Api) {
    super(http, 'municipios-wc/');
  }

  listMunicipioByDepartement(idDeparment: string): Observable<Array<IMunicipio>> {
    return this.http.get(`${this.uri}${idDeparment}`);
  }

}
