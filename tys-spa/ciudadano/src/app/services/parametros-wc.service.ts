import {Injectable} from '@angular/core';
import {environment} from 'environments/environment';
import {Observable} from 'rxjs/Observable';
import {Parametro} from 'app/domain/parametro';
import {Http} from '@angular/http';
import {CommonUtilService} from 'app/services/common-util.service';

@Injectable()
export class ParametrosWcService {
  private baseApiPath: string = environment.base_endpoint;

  constructor(private _common: CommonUtilService, private _http: Http) {
  }

  public listParametros(codPadre: string): Observable<Array<Parametro>> {
    return this._http
      .get(
        this.baseApiPath + '/parametros-wc/' + codPadre,
        this._common.getHttpHeadersForPublicProfile()
      )
      .map(response => response.json())
      .catch((err: Response) => {
        return Observable.throw(this._common.verificarAccesoNoAutorizado(err));
      });
  }
}
