import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';

import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {
  PanelModule,
  GrowlModule,
  CheckboxModule,
  MessagesModule,
  MessageModule,
  SharedModule,
  DataTableModule,
  InputSwitchModule,
  TabViewModule,
  DropdownModule,
  SpinnerModule,
  SelectButtonModule,
  ToggleButtonModule,
  RadioButtonModule,
  MenubarModule,
  FieldsetModule,
  BlockUIModule
} from 'primeng/primeng';
import {BreadcrumbModule, MenuItem, InputTextareaModule} from 'primeng/primeng';
import {AdminTramitesComponent} from './ui-components/admin-tramites/admin-tramites.component';
import {DetalleTramiteComponent} from './ui-components/detalle-tramite/detalle-tramite.component';
import {LoginComponent} from './ui-components/login/login.component';
import {routing} from './routing-conf/app-routes';
import {TramitesWcService} from 'app/services/tramites-wc.service';
import {ButtonModule} from 'primeng/components/button/button';
import {ParametrosWcService} from 'app/services/parametros-wc.service';
import {TiposDocumentalesWcService} from 'app/services/tipos-documentales-wc.service';
import {SeguridadWcService} from 'app/services/seguridad-wc.service';
import {DomHandlerService} from 'app/util/dom-handler.service';
import {SessionHolderService} from 'app/security/session/session-holder.service';
import {LoginGuardService} from 'app/security/guards/login-guard.service';
import {CommonUtilService} from 'app/services/common-util.service';
import {MessageBridgeService} from 'app/util/message-bridge.service';
import {CipherService} from 'app/security/session/cipher.service';
import {DynamicDisableDirective} from './shared';
import {ExistTipoDocumentalValidatorDirective} from './shared/directives/existTipoDocumentalValidator.directive';
import {DropdownItemPipe, DropdownSingleItemPipe} from './pipes/dropdown-single-item';

@NgModule({
  declarations: [
    AppComponent,
    AdminTramitesComponent,
    DetalleTramiteComponent,
    LoginComponent,
    DynamicDisableDirective,
    ExistTipoDocumentalValidatorDirective,
    DropdownSingleItemPipe,
    DropdownItemPipe
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    PanelModule,
    DataTableModule,
    SharedModule,
    ButtonModule,
    InputSwitchModule,
    TabViewModule,
    DropdownModule,
    SpinnerModule,
    MessagesModule,
    MessageModule,
    GrowlModule,
    CheckboxModule,
    FieldsetModule,
    SelectButtonModule,
    RadioButtonModule,
    ToggleButtonModule,
    BreadcrumbModule,
    InputTextareaModule,
    MenubarModule, BlockUIModule,
    routing
  ],

  providers: [TramitesWcService, ParametrosWcService, DomHandlerService, SessionHolderService, CipherService,
    LoginGuardService, SeguridadWcService, CommonUtilService, MessageBridgeService, TiposDocumentalesWcService

  ],

  bootstrap: [AppComponent]
})
export class AppModule {
}
