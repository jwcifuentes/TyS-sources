import {Injectable} from '@angular/core';
import {environment} from 'environments/environment';
import {Http} from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import {Tramite} from 'app/domain/tramite';
import {FlujoAdminTramite} from 'app/constants/flujo-admin-tramite.enum';
import {Observable} from 'rxjs/Observable';
import {CommonUtilService} from 'app/services/common-util.service';
import {LocalDatabase} from './infrastructure/local-database';

@Injectable()
export class TramitesWcService {

  private baseApiPath: string = environment.base_endpoint;

  constructor(private _common: CommonUtilService, private _http: Http, protected cacheDB: LocalDatabase) {
  }

  async getCachedData(codigo) {
    return this.cacheDB.cache.where('codigo').equals(codigo).first();
  }

  public listar(): Observable<Array<Tramite>> {

    return Observable.from(this.getCachedData('tiposTramite')).exhaustMap(record => {
      if (record) {
        return Observable.of(record.data);
      } else {
        return this._http.get(this.baseApiPath + '/tramites-wc/', this._common.getHttpHeadersForPublicProfile())
          .map(response => response.json())
          .catch((err: Response) => {
            return Observable.throw(this._common.verificarAccesoNoAutorizado(err));
          }).do(response => {
            this.cacheDB.cache.add({codigo: 'tiposTramite', data: response});
          });
      }
    });
  }

  public getTramite(id: number): Observable<Tramite> {

    return Observable.from(this.getCachedData('tramite-' + id)).exhaustMap(record => {
      if (record) {
        return Observable.of(record.data);
      } else {
        return this._http.get(this.baseApiPath + '/tramites-wc/' + id, this._common.getHttpHeadersForPublicProfile())
          .map(response => response.json())
          .catch((err: Response) => {
            return Observable.throw(this._common.verificarAccesoNoAutorizado(err));
          }).do(response => {
            this.cacheDB.cache.add({codigo: 'tramite-' + id, data: response});
          });
      }
    });
  }

  public crear(tramite: Tramite, flujo: FlujoAdminTramite): Observable<any> {

    return this._http.post(this.baseApiPath + '/tramites-wc/' + FlujoAdminTramite[flujo], tramite, this._common.getHttpHeadersForPublicProfile())
      .catch((err: Response) => {
        return Observable.throw(this._common.verificarAccesoNoAutorizado(err));
      });
  }

}
