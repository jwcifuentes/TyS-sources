import { Injectable } from '@angular/core';
import { environment } from 'environments/environment';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { TipoDocumental } from 'app/domain/tipo-documental';
import { TipoDocumentalTramite } from 'app/domain/tipo-documental-tramite';
import { FlujoAdminTramite } from 'app/constants/flujo-admin-tramite.enum';
import { CommonUtilService } from 'app/services/common-util.service';

@Injectable()
export class TiposDocumentalesWcService {
  private baseApiPath: string = environment.base_endpoint;

  constructor(private _common: CommonUtilService, private _http: Http) {
  }

  public listTiposDocumentales(): Observable<Array<TipoDocumental>> {
    return this._http.get(this.baseApiPath + '/tiposDocumentales-wc/', this._common.getHttpHeadersForPublicProfile())
      .map(response => response.json())
      .catch((err: Response) => {
        return Observable.throw(this._common.verificarAccesoNoAutorizado(err));
      });
  }


  public crear(tipoDocumentalTramite: TipoDocumentalTramite, flujo: FlujoAdminTramite): Observable<any> {
    return this._http.post(this.baseApiPath + '/tiposDocumentales-wc/' + FlujoAdminTramite[flujo], tipoDocumentalTramite,
      this._common.getHttpHeadersForPublicProfile())
      .catch((err: Response) => {
        return Observable.throw(this._common.verificarAccesoNoAutorizado(err));
      });
  }

  public listar(id: number): Observable<Array<TipoDocumentalTramite>> {
    return this._http.get(this.baseApiPath + '/tiposDocumentales-wc/' + id, this._common.getHttpHeadersForPublicProfile())
      .map(response => response.json())
      .catch((err: Response) => {
        return Observable.throw(this._common.verificarAccesoNoAutorizado(err));
      });
  }

}
