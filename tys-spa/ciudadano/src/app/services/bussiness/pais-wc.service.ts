import {Injectable} from '@angular/core';
import {Api} from '../infrastructure/api';
import {CoreEntity} from '../infrastructure/core-entity.service';
import {IPais} from '../../domain/pais';
import {Observable} from 'rxjs/Observable';
import {LocalDatabase} from '../infrastructure/local-database';
import 'rxjs/add/observable/from';
import 'rxjs/add/observable/of';

@Injectable()
export class PaisWcService extends CoreEntity<IPais> {

  constructor(protected readonly http: Api, protected cacheDB: LocalDatabase) {
    super(http, 'paises-wc/');
  }

  list(): Observable<IPais[]> {
    const codigoPadre = 'paises';
    return Observable.from(this.getCachedData(codigoPadre)).exhaustMap(record => {
      if (record) {
        return Observable.of(record.data);
      } else {
        return this.http.get<IPais[]>(`${this.uri}`).do(response => {
          this.cacheDB.cache.add({codigo: codigoPadre, data: response});
        });
      }
    });
  }

  async getCachedData(codigo) {
    return this.cacheDB.cache.where('codigo').equals(codigo).first();
  }
}
