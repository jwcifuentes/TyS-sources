import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { SessionHolderService, SessionAttribute } from "app/security/session/session-holder.service";

@Injectable()
export class LoginGuardService implements CanActivate{

  constructor(private _router: Router, private _session: SessionHolderService ){ }

  canActivate() {

      if (this._session.isLoggedIn()) {
          return true;
      }

      console.log('user not logged');
      this._router.navigate(['/login']);
      return false;
  }

}
