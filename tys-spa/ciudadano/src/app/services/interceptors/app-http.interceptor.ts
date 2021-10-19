import {Injectable, OnInit} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {SessionHolderService} from '../../security/session/session-holder.service';
import {Router} from '@angular/router';
import {CipherService} from '../../security/session/cipher.service';
import {environment} from '../../../environments/environment';
import {CommonUtilService} from '../common-util.service';

@Injectable()
export class AppHttpInterceptor implements HttpInterceptor {

  securityUser: string;
  securityPassword: string;

  constructor(private _session: SessionHolderService,
              private _cipher: CipherService,
              private _utilService: CommonUtilService,
              private _router: Router) {
    this.securityUser = this._cipher.decrypt(environment.securityUser);
    this.securityPassword = this._cipher.decrypt(environment.securityPass);
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const options = this._utilService.getHttpHeadersForPublicProfile();
    const headers = {
      setHeaders: {
        'Authorization': options.headers.get('Authorization'),
        'Content-Type': options.headers.get('Content-Type'),
        'Accept': options.headers.get('Accept')
      }
    };

    const _req = req.clone(headers);

    return next.handle(_req).do(success => '', err => {
      if (err.status === 401) {
        this._session.logout();
        this._router.navigate(['/login', 'expired']);
      }
    });
  }

}
