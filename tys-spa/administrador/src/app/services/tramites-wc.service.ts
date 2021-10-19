import { Injectable } from "@angular/core";
import { environment } from "environments/environment";
import { Http } from "@angular/http";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import { Tramite } from "app/domain/tramite";
import { FlujoAdminTramite } from "app/constants/flujo-admin-tramite.enum";
import { Observable } from "rxjs/Observable";
import { CommonUtilService } from "app/services/common-util.service";

@Injectable()
export class TramitesWcService {

    private baseApiPath: string = environment.mvcEndpoint;

    constructor(private _common: CommonUtilService, private _http: Http) { }

    public listar(): Observable<Array<Tramite>> {

        return this._http.get(this.baseApiPath + '/admin/tramites-wc/', this._common.getHttpHeadersForAdminProfile())
        .map(response => response.json())
        .catch((err: Response) => {
          return Observable.throw( this._common.verificarAccesoNoAutorizado(err) );
        });
    }

    public getTramite(id: number): Observable<Tramite> {

        return this._http.get(this.baseApiPath + '/admin/tramites-wc/' + id, this._common.getHttpHeadersForAdminProfile())
        .map(response => response.json())
        .catch((err: Response) => {
          return Observable.throw( this._common.verificarAccesoNoAutorizado(err) );
        });
    }

    public crear(tramite: Tramite, flujo: FlujoAdminTramite ): Observable<any> {

        return this._http.post(this.baseApiPath + '/admin/tramites-wc/' + FlujoAdminTramite[flujo], tramite, this._common.getHttpHeadersForAdminProfile())
        .catch((err: Response) => {
          return Observable.throw( this._common.verificarAccesoNoAutorizado(err) );
        });
    }

    public cambiarParametrizacionTramite(id: number): Observable<Tramite> {
        const url = `${this.baseApiPath}/admin/tramites-wc/parametrizacion/${id}`;
        const headers = this._common.getHttpHeadersForAdminProfile();
        return this._http.put(url, null, headers)
        .catch((err: Response) => Observable.throw( this._common.verificarAccesoNoAutorizado(err) ));
    }

}
