import {Injectable} from '@angular/core';
import {Http, RequestOptions, Headers} from '@angular/http';
import {Router} from '@angular/router';
import {SessionHolderService, SessionAttribute} from 'app/security/session/session-holder.service';
import {environment} from 'environments/environment';
import {CipherService} from 'app/security/session/cipher.service';

@Injectable()
export class CommonUtilService {

  private securityUser: string;
  private securityPass: string;

  constructor(private _session: SessionHolderService, private _cipher: CipherService, private _router: Router) {
    this.securityUser = _cipher.decrypt(environment.securityUser);
    this.securityPass = _cipher.decrypt(environment.securityPass);
  }

  public getHttpHeadersForAdminProfile(): RequestOptions {

    const options = new RequestOptions();
    options.headers = new Headers();
    options.headers.append('Authorization', 'Basic ' + btoa(this.securityUser + ':' + this.securityPass));
    options.headers.append('TokenAuthorization', 'Bearer ' + this._session.getToken());
    options.headers.append('Accept', 'application/json');
    options.headers.append('Content-Type', 'application/json');

    return options;
  }


  public getHttpHeadersForPublicProfile(): RequestOptions {

    const options = new RequestOptions();
    options.headers = new Headers();
    options.headers.append('Authorization', 'Basic ' + btoa(this.securityUser + ':' + this.securityPass));
    options.headers.append('Accept', 'application/json');
    options.headers.append('Content-Type', 'application/json');

    return options;
  }

  public verificarAccesoNoAutorizado(response: Response): any {

    if (response.status === 401) {
      this._session.logout();
      this._router.navigate(['/login', 'expired']);
    }

    return response.json();
  }

}
