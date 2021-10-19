import { BrowserModule } from "@angular/platform-browser";
import { LOCALE_ID, NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { HttpModule } from "@angular/http";
import { AppComponent } from "./app.component";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import {
    BlockUIModule,
    BreadcrumbModule,
    CheckboxModule,
    DataTableModule,
    DropdownModule,
    GrowlModule,
    InputSwitchModule,
    InputTextareaModule,
    MenubarModule,
    MessageModule,
    MessagesModule,
    PanelModule,
    RadioButtonModule,
    SelectButtonModule,
    SharedModule,
    SpinnerModule,
    StepsModule,
    TabViewModule,
    ToggleButtonModule,
    FileUploadModule,
    FieldsetModule,
    CalendarModule,
    AutoCompleteModule,
    TooltipModule,
    DialogModule,
    ProgressSpinnerModule,
    OverlayPanelModule, MultiSelectModule
} from 'primeng/primeng';
import { LoginComponent } from "./ui-components/login/login.component";
import { routing } from "./routing-conf/app-routes";
import { TramitesWcService } from "app/services/tramites-wc.service";
import { ButtonModule } from "primeng/components/button/button";
import { ParametrosWcService } from "app/services/parametros-wc.service";
import { TiposDocumentalesWcService } from "app/services/tipos-documentales-wc.service";
import { SeguridadWcService } from "app/services/seguridad-wc.service";
import { SessionHolderService } from "app/security/session/session-holder.service";
import { LoginGuardService } from "app/security/guards/login-guard.service";
import { CommonUtilService } from "app/services/common-util.service";
import { RadicarTramitesServiciosComponent } from "./ui-components/radicar-tramites-servicios/radicar-tramites-servicios.component";
import { DatosTramiteComponent } from "./ui-components/radicar-tramites-servicios/datos-tramite/datos-tramite.component";
import { DatosRemitenteComponent } from "./ui-components/radicar-tramites-servicios/datos-remitente/datos-remitente.component";
import { DocumentosTramiteComponent } from "./ui-components/radicar-tramites-servicios/documentos-tramite/documentos-tramite.component";
import { OrganizacionesSindicalesComponent } from "./ui-components/radicar-tramites-servicios/datos-tramite/organizaciones-sindicales/organizaciones-sindicales.component";
import { DIRECTIVES, PIPES, PIPES_AS_PROVIDERS } from "./shared";
import {
  HTTP_INTERCEPTORS,
  HttpClient,
  HttpClientModule
} from "@angular/common/http";
import { AppHttpInterceptor } from "./services/interceptors/app-http.interceptor";
import { TipoDocumentalWcService } from "./services/bussiness/tipoDocumental-wc.service";
import { Api } from "./services/infrastructure/api";
import { CommonModule } from "@angular/common";
import "./rxjs-imports";
import { ConstanteWcService } from "./services/bussiness/constante-wc.service";
import { DatosDireccionComponent } from "./ui-components/radicar-tramites-servicios/datos-direccion/datos-direccion.component";
import { TratamientoCortesiaWcService } from "./services/bussiness/tratamiento-cortesia-wc.service";
import { TramiteWcService } from "./services/bussiness/tramite-wc.service";
import { DireccionTerritorialWcService } from "./services/bussiness/direccion-territorial-wc.service";
import { DepartamentoWcService } from "./services/bussiness/departamento-wc.service";
import { MunicipioWcService } from "./services/bussiness/municipio-wc.service";
import { NotariaWcService } from "./services/bussiness/notaria-wc.service";
import { PaisWcService } from "./services/bussiness/pais-wc.service";
import { CipherService } from "./security/session/cipher.service";
import { DireccionesTerritorialesComponent } from "./ui-components/radicar-tramites-servicios/datos-tramite/direcciones-territoriales/direcciones-territoriales.component";
import { RadicadosReferidosComponent } from "./ui-components/radicar-tramites-servicios/datos-tramite/radicados-referidos/radicados-referidos.component";
import { JustaCausaDescripcionComponent } from "./ui-components/radicar-tramites-servicios/justa-causa-descripcion/justa-causa-descripcion.component";
import { RadicarTramiteAcuerdoComponent } from "./ui-components/radicar-tramite-acuerdo/radicar-tramite-acuerdo.component";
import { SeleccionModulosComponent } from "./ui-components/seleccion-modulos/seleccion-modulos.component";
import { ConsultarTramitesComponent } from "./ui-components/consultar-tramites/consultar-tramites.component";
import { DatosSociosComponent } from "./ui-components/radicar-tramites-servicios/datos-tramite/datos-socios/datos-socios.component";
import { MessageBridgeService } from "./ui-components/radicar-tramites-servicios/util/message-bridge.service";
import { DomHandlerService } from "./ui-components/radicar-tramites-servicios/util/dom-handler.service";
import { PendingRequestInterceptor } from "./services/interceptors/pending-request.interceptor";
import { LoadingService } from "./services/infrastructure/loading.service";
import { RECAPTCHA_SETTINGS, RecaptchaModule } from "ng-recaptcha";
import { RecaptchaFormsModule } from "ng-recaptcha/forms";
import { RegistroHorarioComponent } from "./ui-components/radicar-tramites-servicios/datos-tramite/registro-horario/registro-horario.component";
import { ActualizarTramiteComponent } from "./ui-components/actualizar-tramite/actualizar-tramite.component";
import { LocalDatabase } from "./services/infrastructure/local-database";
import { BreadcrumbService } from "./services/infrastructure/breadcrumb.service";
import { TranslateModule, TranslateLoader } from "@ngx-translate/core";
import { TranslateHttpLoader } from "@ngx-translate/http-loader";
import { NgxTranslateService } from "./services/infrastructure/ngx-translate.service";
import { Http } from "@angular/http";
import { TranslateOptionsComponent } from "./ui-components/translate-options/translate-options.component";
import {DatosDireccionCustomComponent} from './ui-components/radicar-tramites-servicios/datos-direccion-custom/datos-direccion.component';
import { DatosPracticaAdolecentesComponent } from './ui-components/radicar-tramites-servicios/datos-tramite/datos-practica-adolecentes/datos-practica-adolecentes.component';
import { DatosResidenciaAdolecenteComponent } from './ui-components/radicar-tramites-servicios/datos-tramite/datos-practica-adolecentes/datos-residencia-adolecente/datos-residencia-adolecente.component';
import { DatosFormacionComponent } from './ui-components/radicar-tramites-servicios/datos-tramite/datos-practica-adolecentes/datos-formacion/datos-formacion.component';
import { DatosRepresentanteLegalComponent } from './ui-components/radicar-tramites-servicios/datos-tramite/datos-representante-legal/datos-representante-legal.component';
import { EscenarioPracticaComponent } from './ui-components/radicar-tramites-servicios/escenario-practica/escenario-practica.component';
import { DatosBasicosPersonaComponent } from './ui-components/radicar-tramites-servicios/datos-basicos-persona/datos-basicos-persona.component';
import { CondicionesVinculacionComponent } from './ui-components/radicar-tramites-servicios/datos-tramite/datos-practica-adolecentes/condiciones-vinculacion/condiciones-vinculacion.component';
import { AuxilioPracticaComponent } from './ui-components/radicar-tramites-servicios/datos-tramite/datos-practica-adolecentes/condiciones-vinculacion/auxilio-practica/auxilio-practica.component';
import {GenerarPdfService} from './services/generar-pdf.service';
//import {RemitenteDTV} from './shared/data-transformers/RemitenteDTV';

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http, "./assets/i18n/", ".json");
}

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RadicarTramitesServiciosComponent,
    DatosTramiteComponent,
    DatosRemitenteComponent,
    DocumentosTramiteComponent,
    DatosDireccionComponent,
    ...PIPES,
    ...DIRECTIVES,
    DireccionesTerritorialesComponent,
    RadicadosReferidosComponent,
    SeleccionModulosComponent,
    JustaCausaDescripcionComponent,
    RadicarTramiteAcuerdoComponent,
    SeleccionModulosComponent,
    ConsultarTramitesComponent,
    ActualizarTramiteComponent,
    DatosSociosComponent,
    RegistroHorarioComponent,
    TranslateOptionsComponent,
    OrganizacionesSindicalesComponent,
    DatosDireccionCustomComponent,
    DatosPracticaAdolecentesComponent,
    DatosResidenciaAdolecenteComponent,
    DatosFormacionComponent,
    DatosRepresentanteLegalComponent,
    EscenarioPracticaComponent,
    DatosBasicosPersonaComponent,
    CondicionesVinculacionComponent,
    AuxilioPracticaComponent,
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
        SelectButtonModule,
        RadioButtonModule,
        ToggleButtonModule,
        BreadcrumbModule,
        // BreadCrumbManagerModule,
        InputTextareaModule,
        MenubarModule,
        BlockUIModule,
        StepsModule,
        FileUploadModule,
        FieldsetModule,
        CommonModule,
        HttpClientModule,
        CalendarModule,
        AutoCompleteModule,
        DialogModule,
        OverlayPanelModule,
        routing,
        ProgressSpinnerModule,
        RecaptchaModule.forRoot(),
        RecaptchaFormsModule,
        TranslateModule.forRoot({
            loader: {
                provide: TranslateLoader,
                useFactory: HttpLoaderFactory,
                deps: [HttpClient]
            }
        }),
        MultiSelectModule
    ],

  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AppHttpInterceptor, multi: true },
    CipherService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: PendingRequestInterceptor,
      multi: true
    },
    LoadingService,
    { provide: LOCALE_ID, useValue: "es-CO" },
    // {
    //   provide: RECAPTCHA_SETTINGS,
    //   useValue: {
    //     siteKey: '6LcaVz4UAAAAAEP9IqWmzUDeOj1BTjp4zdXCJdmR',
    //   }
    // },
    ...PIPES_AS_PROVIDERS,
    TramitesWcService,
    ParametrosWcService,
    DomHandlerService,
    SessionHolderService,
    LoginGuardService,
    SeguridadWcService,
    CommonUtilService,
    MessageBridgeService,
    TiposDocumentalesWcService,
    Api,
    LocalDatabase,
    TipoDocumentalWcService,
    ConstanteWcService,
    TratamientoCortesiaWcService,
    TramiteWcService,
    DireccionTerritorialWcService,
    PaisWcService,
    DepartamentoWcService,
    MunicipioWcService,
    NotariaWcService,
    BreadcrumbService,
    NgxTranslateService,
    GenerarPdfService,
    //RemitenteDTV,
  ],

  bootstrap: [AppComponent]
})
export class AppModule {}
