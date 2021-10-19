import { Injectable } from '@angular/core';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs/Observable';

import { Http } from '@angular/http';
import { Usuario } from 'app/domain/usuario';
import { SessionAttribute, SessionHolderService } from 'app/security/session/session-holder.service';
import { CommonUtilService } from 'app/services/common-util.service';

@Injectable()
export class SeguridadWcService {

  private baseApiPath: string = environment.base_endpoint;

  constructor(private _common: CommonUtilService, private _session: SessionHolderService, private _http: Http) {
  }

  public login(usuario: Usuario): Observable<Usuario> {

    return this._http.post(this.baseApiPath + '/seguridad-wc/login', usuario, this._common.getHttpHeadersForPublicProfile())
      .map(response => {
        const token = response.headers.get('Authorization');
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
