import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {SessionHolderService} from 'app/security/session/session-holder.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {

  constructor(public _session: SessionHolderService, private _router: Router) {
  }

  ngOnInit(): void {
  }

  public logout(): void {
    this._session.logout();
    this._router.navigate(['/login', 'logout']);
  }

  isInTramiteDetail(): boolean {
    return this._router.isActive('detalle_tramite', false);
  }

  goToAdminTramites() {
    this._router.navigate(['admin_tramites']);
  }

}
