import {Routes, RouterModule} from '@angular/router';
import {AdminTramitesComponent} from 'app/ui-components/admin-tramites/admin-tramites.component';
import {DetalleTramiteComponent} from 'app/ui-components/detalle-tramite/detalle-tramite.component';
import {LoginComponent} from 'app/ui-components/login/login.component';
import {LoginGuardService} from 'app/security/guards/login-guard.service';

const routes: Routes = [
  {path: '', redirectTo: 'admin_tramites', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'login/:status', component: LoginComponent},
  {path: 'admin_tramites', component: AdminTramitesComponent, canActivate: [LoginGuardService]},
  {path: 'admin_tramites/:status', component: AdminTramitesComponent, canActivate: [LoginGuardService]},
  {path: 'detalle_tramite/:id', component: DetalleTramiteComponent, canActivate: [LoginGuardService]}
];

export const routing = RouterModule.forRoot(routes, {useHash: true});
