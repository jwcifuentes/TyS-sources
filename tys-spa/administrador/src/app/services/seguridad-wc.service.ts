import { Injectable } from "@angular/core";
import { environment } from "environments/environment";
import { Observable } from "rxjs/Observable";
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import { Parametro } from "app/domain/parametro";
import { Http, RequestOptions, Headers } from "@angular/http";
import { Usuario } from "app/domain/usuario";
import { SessionHolderService, SessionAttribute } from "app/security/session/session-holder.service";
import { CommonUtilService } from "app/services/common-util.service";

@Injectable()
export class SeguridadWcService {

    private baseApiPath: string = environment.mvcEndpoint;

    constructor(private _common: CommonUtilService, private _session: SessionHolderService, private _http: Http) { }

    public login(usuario: Usuario): Observable<Usuario> {

        return this._http.post(this.baseApiPath + '/public/seguridad-wc/login', usuario, this._common.getHttpHeadersForPublicProfile())
            .map(response => {
                let token = response.headers.get('Authorization');
                if (token != null) {
                    this._session.saveToken(SessionAttribute.SECURITY_TOKEN, token);
                }
                return response.json();
            })
            .catch((err: Response) => {
                return Observable.throw(err.json());
            });
    }

}
