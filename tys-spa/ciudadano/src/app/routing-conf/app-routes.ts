import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from 'app/ui-components/login/login.component';
import {RadicarTramitesServiciosComponent} from '../ui-components/radicar-tramites-servicios/radicar-tramites-servicios.component';
import {RadicarTramiteAcuerdoComponent} from '../ui-components/radicar-tramite-acuerdo/radicar-tramite-acuerdo.component';
import {SeleccionModulosComponent} from '../ui-components/seleccion-modulos/seleccion-modulos.component';
import {ConsultarTramitesComponent} from '../ui-components/consultar-tramites/consultar-tramites.component';
import {ActualizarTramiteComponent} from '../ui-components/actualizar-tramite/actualizar-tramite.component';

const selModulesCrumb = [{ displayName: 'GENERALES.MENU.SELECCION_MODULOS', url: 'Selección de módulos'}];
const radicarTramiteCrumb = [...selModulesCrumb, { displayName: 'GENERALES.MENU.RADICAR_TRAMITE_ACUERDO', url: 'radicar_tramite_acuerdo'}];
const consultarTramitesCrumb = [...selModulesCrumb, { displayName: 'GENERALES.MENU.CONSULTAR_TRAMITES', url: 'consultar_tramites'}];
const radicarServiciosCrumb = [...radicarTramiteCrumb, { displayName: 'GENERALES.MENU.RADICAR_TRAMITE_SERVICIOS',
                                                          url: 'radicar_tramites_servicios'}];
const actualizarTramiteCrumb = [...radicarTramiteCrumb, { displayName: 'GENERALES.MENU.ACTUALIZAR_TRAMITE',
                                                          url: 'actualizar_tramite/:numeroRadicado/:codigoVerificacion'}];

const routes: Routes = [
  {path: '', redirectTo: 'seleccion_modulos', pathMatch: 'full'},
  {path: 'login/:status', component: LoginComponent},
  {
    path: 'seleccion_modulos', component: SeleccionModulosComponent, data: {breadcrumb: selModulesCrumb}
  },
  {
    path: 'radicar_tramite_acuerdo',
    component: RadicarTramiteAcuerdoComponent,
    data: {breadcrumb: radicarTramiteCrumb}
  },
  {
    path: 'consultar_tramites', component: ConsultarTramitesComponent, data: {breadcrumb: consultarTramitesCrumb}
  },
  {
    path: 'radicar_tramites_servicios',
    component: RadicarTramitesServiciosComponent,
    data: {breadcrumb: radicarServiciosCrumb}
  },
  {
    path: 'actualizar_tramite/:numeroRadicado/:codigoVerificacion',
    component: ActualizarTramiteComponent,
    data: {breadcrumb: actualizarTramiteCrumb}
  },
  {path: '**', redirectTo: 'seleccion_modulos', pathMatch: 'full'},

];

export const routing = RouterModule.forRoot(routes, {useHash: true});
