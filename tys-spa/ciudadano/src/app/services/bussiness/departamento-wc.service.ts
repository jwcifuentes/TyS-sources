import {Injectable} from '@angular/core';
import {Api} from '../infrastructure/api';
import {CoreEntity} from '../infrastructure/core-entity.service';
import {Observable} from 'rxjs/Observable';
import {IDepartamento} from '../../domain/departamento';

@Injectable()
export class DepartamentoWcService extends CoreEntity<IDepartamento> {

  constructor(protected readonly http: Api) {
    super(http, 'departamentos-wc/');
  }

  listDepartmentsByCountry(codigoPais: string): Observable<Array<IDepartamento>> {
    return this.http.get<Array<IDepartamento>>(`${this.uri}${codigoPais}`);
  }

  listDepartmentsOfColombia(): Observable<Array<IDepartamento>> {
    return this.http.get(`${this.uri}${1}`);
  }

}
