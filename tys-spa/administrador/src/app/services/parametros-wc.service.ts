import {Injectable} from '@angular/core';
import {environment} from 'environments/environment';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import 'rxjs/add/observable/of';
import {Parametro} from 'app/domain/parametro';
import {Http} from '@angular/http';
import {CommonUtilService} from 'app/services/common-util.service';
import {YesNo} from '../domain/yes-no';

@Injectable()
export class ParametrosWcService {
  private baseApiPath: string = environment.mvcEndpoint;

  constructor(private _common: CommonUtilService, private _http: Http) {
  }

  public listParametros(codPadre: string): Observable<Array<Parametro>> {
    return this._http
      .get(
        this.baseApiPath + '/public/parametros-wc/' + codPadre,
        this._common.getHttpHeadersForPublicProfile()
      )
      .map(response => response.json())
      .catch((err: Response) => {
        return Observable.throw(this._common.verificarAccesoNoAutorizado(err));
      });
  }

  listYesNoConstants(): Observable<Array<YesNo>> {
    return Observable.of([{
      nombre: 'Si',
      value: true
    }, {
      nombre: 'No',
      value: false
    }]);
  }
}
