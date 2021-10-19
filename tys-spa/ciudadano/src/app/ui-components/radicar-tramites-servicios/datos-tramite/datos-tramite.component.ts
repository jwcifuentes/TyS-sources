import {
  ChangeDetectorRef,
  Component,
  EventEmitter,
  Injector,
  Input,
  OnInit,
  Output,
  ViewChild,
  ViewEncapsulation
} from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
//import * as jsPDF from 'jspdf';
import { Observable } from 'rxjs/Observable';
import { TramiteWcService } from 'app/services/bussiness/tramite-wc.service';
import { ConstanteWcService } from 'app/services/bussiness/constante-wc.service';
import { DireccionTerritorialWcService } from 'app/services/bussiness/direccion-territorial-wc.service';
import { DepartamentoWcService } from 'app/services/bussiness/departamento-wc.service';
import { MunicipioWcService } from 'app/services/bussiness/municipio-wc.service';
import { NotariaWcService } from 'app/services/bussiness/notaria-wc.service';
import { IConstante } from 'app/domain/constante';
import {environment, TIPO_TRAMITE, TIPODOC_NIT} from 'environments/environment';
import { JustaCausaDescripcionComponent } from '../justa-causa-descripcion/justa-causa-descripcion.component';
import { DatosDireccionComponent } from '../datos-direccion/datos-direccion.component';
import { ITramiteForm } from 'app/shared/data-transformers/interfaces/form/tramite-form';
import { DatosSociosComponent } from './datos-socios/datos-socios.component';
import { RadicadosReferidosComponent } from './radicados-referidos/radicados-referidos.component';
import { DireccionesTerritorialesComponent } from './direcciones-territoriales/direcciones-territoriales.component';
import { RegistroHorarioComponent } from './registro-horario/registro-horario.component';
import { FormBaseComponent } from 'app/shared/utils/form-base-component';
import { Empresa } from 'app/domain/empresa';
import { Persona } from 'app/domain/persona';
import { SocioEmpresa } from 'app/domain/socio-empresa';
import { Direccion } from 'app/domain/direccion';
import { IDireccionForm } from 'app/shared/data-transformers/interfaces/form/direccion-form';
import { TipoGestion } from 'app/constants/tipo-gestion.enum';
import { CommonModel } from 'app/ui-components/radicar-tramites-servicios/util/common.model';
import { Remitente } from 'app/domain/remitente';
import { Subject } from 'rxjs/Subject';
import { combineLatest } from 'rxjs/observable/combineLatest';
import { flatMap } from 'rxjs/operators';
import { TranslateService } from '@ngx-translate/core';
import { element } from 'protractor';
import {OrganizacionesSindicalesComponent} from './organizaciones-sindicales/organizaciones-sindicales.component';
import {log} from 'util';
import {Http} from '@angular/http';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {ActividadEconomica} from '../../../domain/ActividadEconomica';
import {GenerarPdfService} from '../../../services/generar-pdf.service';
import {DatosSolicitudTramite} from '../../../domain/datos-solicitud-tramite';
import {LoadingService} from '../../../services/infrastructure/loading.service';


@Component({
  selector: 'app-datos-tramite',
  templateUrl: './datos-tramite.component.html',
  styleUrls: ['./datos-tramite.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class DatosTramiteComponent extends FormBaseComponent implements OnInit {

  commonModel: CommonModel;
  //AUTORIZACION EMPRESA
  GESTION_CREACION_EMPRESA = 11291;

  isCreacionEmpresa: boolean;
  isEmpresaSucursal: boolean;
  bloquearFlujo: boolean;
  razonBloqueo: string;
  isChildLessThan15 = false;
  ocultaDatoEmpresa = false;
  otroParentescoIsVisible = false;
  @Input() sharedData: Subject<any>;

  @ViewChild('justaCausaDescripcion')
  justaCausaDescripcion: JustaCausaDescripcionComponent;

  @ViewChild('direccionesTrabajador')
  direccionesTrabajador: DatosDireccionComponent;

  @ViewChild('direccionesVinculacion')
  direccionesVinculacion: DatosDireccionComponent;

  @ViewChild('direccionesAdolescente')
  direccionesAdolescentes: DatosDireccionComponent;

  @ViewChild('direccionesSucursales')
  direccionesSucursales: DatosDireccionComponent;

  @ViewChild('radicadosReferidos')
  radicadosReferidos: RadicadosReferidosComponent;

  @ViewChild('direccionesTerritoriales')
  direccionesTerritoriales: DireccionesTerritorialesComponent;

  @ViewChild('organizacionesSindicales')
  organizacionesSindicales: OrganizacionesSindicalesComponent;

  @ViewChild('datosSocios')
  datosSocios: DatosSociosComponent;

  @ViewChild('registroHorario')
  registroHorario: RegistroHorarioComponent;

  @Output()
  onChangeTipoTramite: EventEmitter<IConstante> = new EventEmitter();
  @Output() onInvalid: EventEmitter<any> = new EventEmitter();

  tipoEmpleadorOptions$: Observable<Array<IConstante>>;
  tipoEmpleadorOptionsNNA$: Observable<Array<any>>;

  nmDireccionesPermitidas: number;
  tipoGestionOptions$: Observable<any[]>;
  tipoTramiteOptions$: Observable<any[]>;
  justificacionSolicitudOptions$: Observable<any[]>;
  gradoAsociacionOptions$: Observable<any[]>;
  tipoSolicitudOptions$: Observable<any[]>;
  generoOptions$: Observable<any[]>;
  tienePactosOptions$: Observable<any[]>;
  tieneReglamentoOptions$: Observable<any[]>;
  tieneOrganizacionesSindicalesOptions$: Observable<any[]>;
  radicadaPorOptions$: Observable<any[]>;
  tipoSocietarioSasOptions$: Observable<any[]>;
  direccionTerritorialOptions$: Observable<any[]>;
  tipoDocumentoOptions$: Observable<any[]>;
  tipoDocumentoOptionsNNAReprsentante$: Observable<any[]>;
  tipoDocumentoNNAOptions$: Observable<any[]>;
  tipoTelefonoOptions$: Observable<any[]>;
  parentescoOptions$: Observable<any[]>;
  tipoVinculacionOptions$: Observable<any[]>;
  tipoActividadOptions$: Observable<any[]>;
  formaPagoOptions$: Observable<any[]>;
  departamentoColombiaOptions$: Observable<any[]>;
  municipioOptions$: Observable<any[]>;
  municipioOptionsNNADatosDireccion$: Observable<any[]>;
  municipioOptionsNNADatosEmpleador$: Observable<any[]>;
  notariaOptions$: Observable<any[]>;
  tipoSolicitanteOptions$: Observable<any[]>;
  edadOptions$: Observable<any[]>;
  listseleccionados: Array<any> = [];
  causalDespidos$: Observable<any[]>;
  capturaListaCausalD: any;
  collection: Array<any> = [];
  datosTramite: DatosSolicitudTramite;

  tieneHijosOptions$: Observable<any[]>;
  tipoUbicacionOptions$: Observable<any[]>;
  tipoZonaOptions$: Observable<any[]>;
  jornadaEscolar$: Observable<any[]>;
  condicionDiscapacidadOptions$: Observable<any[]>;
  divisionActividadEconomica$: Observable<Array<ActividadEconomica>>;
  @Input() departamentoOptions: any[];
  arrDireccionTerritorial: any[];
  es: any;
  tramiteDescription: string;
  tramiteUrls: any[];
  isNinioaRepresentateNit = false;
  isTxtNumeric = false;
  isTxtNumeric_2 = false;
  cuantosHijosVisibility = true;
  grupoActividadEconomica$: Observable<Array<ActividadEconomica>>;
  private isReadOnly: boolean = false;
  private isHiddenDatosSocios: boolean = false;
  private isVisibleShowLoading: boolean = false;
  private validationSubject: Subject<any> = new Subject();
  private isPopUpDownloadDocx: boolean = false;
  doc_carta_intencion: string = environment.doc_carta_intencion;
  doc_carta_autorizacion: string = environment.doc_carta_autorizacion;


  constructor(injector: Injector,
    private formBuilder: FormBuilder,
    private changeDetector: ChangeDetectorRef,
    public tramiteService: TramiteWcService,
    public direccionTerritorialServie: DireccionTerritorialWcService,
    public departamentoService: DepartamentoWcService,
    public municipioService: MunicipioWcService,
    public notariaService: NotariaWcService,
    public constanteWcService: ConstanteWcService,
    public pdf: GenerarPdfService,
    // public JustaCausaDescripcionComponent: JustaCausaDescripcionComponent,
    protected _translator: TranslateService,
             public http: HttpClient
  ) {
    super(injector);
    this.commonModel = new CommonModel(_translator);
  }


  ngOnInit() {
    this.ShowListCausalDespido();
    this.isCreacionEmpresa = true;
    this.initForm();
    this.listenForChanges();
    this.listenForErrors();
    this.condicionDiscapacidadOptions$ = this.http.get(`${environment.base_endpoint}/parametros-wc/DISCAPACIDAD`);
    this.tipoEmpleadorOptionsNNA$ = this.constanteWcService.listByParentCode('TIPO_EMPLEADOR');
    this.jornadaEscolar$ = this.constanteWcService.listByParentCode('JORNADA_ESCOLAR');
    this.tipoZonaOptions$ = this.constanteWcService.listByParentCode('TIPO_ZONA');
    this.tipoUbicacionOptions$ = this.constanteWcService.listByParentCode('TIPO_UBICACION');
    this.tipoEmpleadorOptions$ = this.constanteWcService.listByParentCode('TIP_EMP_TS');
    this.tipoTramiteOptions$ = this.tramiteService.list().map((response: any) => response);
    this.generoOptions$ = this.constanteWcService.listByParentCode('SEXO_TS');
    this.tipoGestionOptions$ = this.constanteWcService.listByParentCode('TIP_GES_EMP_TS');
    this.justificacionSolicitudOptions$ = this.constanteWcService.listByParentCode('JUS_SOLI_TS');
    this.gradoAsociacionOptions$ = this.constanteWcService.listByParentCode('GRA_ASO_TS');
    this.tipoSolicitudOptions$ = this.constanteWcService.listByParentCode('TIP_SOLI_TS');
    this.tienePactosOptions$ = this.constanteWcService.listYesNoConstants();
    this.tieneReglamentoOptions$ = this.constanteWcService.listYesNoConstants();
    this.tieneOrganizacionesSindicalesOptions$ = this.constanteWcService.listYesNoConstants();
    this.radicadaPorOptions$ = this.constanteWcService.listByParentCode('RAD_POR_TS');
    this.tipoSocietarioSasOptions$ = this.constanteWcService.listYesNoConstants();
    this.direccionTerritorialOptions$ = this.direccionTerritorialServie.list().map((response: any) => response);
    this.tipoDocumentoOptions$ = this.constanteWcService.listByParentCode('TIP-IDENT');
    this.tipoDocumentoNNAOptions$ = this.constanteWcService.listByParentCode('NNA/TIP-IDENT');
    this.tipoDocumentoOptionsNNAReprsentante$ = this.constanteWcService.listByParentCode('NNA-REPRESENTANTE/TIP-IDENT');
    this.tipoTelefonoOptions$ = this.constanteWcService.listByParentCode('TIP-TELC');
    this.tipoVinculacionOptions$ = this.constanteWcService.listByParentCode('TIP_VIN_TS');
    this.tipoActividadOptions$ = this.constanteWcService.listByParentCode('ACT_EXC_TS');
    this.formaPagoOptions$ = this.constanteWcService.listByParentCode('FOR_PAG_TS');
    this.tipoSolicitanteOptions$ = this.constanteWcService.listByParentCode('TIP_SOLT_TS');
    this.edadOptions$ = this.constanteWcService.listByParentCode('EDAD_TS').map((edades) => {
      return edades.sort((edad1, edad2) => (parseInt(edad1.nombre) - parseInt(edad2.nombre)) );
    });
    this.parentescoOptions$ = this.constanteWcService.listByParentCode('PARENTESCO');
    this.departamentoColombiaOptions$ = this.departamentoService.listDepartmentsOfColombia();
    this.arrDireccionTerritorial = [{ id: 1, direccion: 'Direccion territorial 1' }];
    this.tieneHijosOptions$ = this.constanteWcService.listYesNoConstants();
    this.divisionActividadEconomica$ = this.constanteWcService.listActividadesEconomicas('ACTIVIDAD_DIVISION');
    this.es = {
      firstDayOfWeek: 1,
      dayNames: ['domingo', 'lunes', 'martes', 'miércoles', 'jueves', 'viernes', 'sábado'],
      dayNamesShort: ['dom', 'lun', 'mar', 'mié', 'jue', 'vie', 'sáb'],
      dayNamesMin: ['D', 'L', 'M', 'X', 'J', 'V', 'S'],
      monthNames: ['enero', 'febrero', 'marzo', 'abril', 'mayo', 'junio', 'julio', 'agosto', 'septiembre',
        'octubre', 'noviembre', 'diciembre'],
      monthNamesShort: ['ene', 'feb', 'mar', 'abr', 'may', 'jun', 'jul', 'ago', 'sep', 'oct', 'nov', 'dic'],
      today: 'Hoy',
      clear: 'Borrar'
    };

    this.refreshVisibility(null);
  }

  initForm() {
    this.form = this.formBuilder.group({
      tipoGestion: [this.GESTION_CREACION_EMPRESA, Validators.required],
      tipoTramite: [null, Validators.required],
      justificacionSolicitud: [null, Validators.required],
      gradoAsociacion: [null, Validators.required],
      tipoSolicitud: [null, Validators.required],
      tienePactos: [null, Validators.required],
      tieneReglamento: [null, Validators.required],
      tieneOrganizacionesSindicales: [null, Validators.required],
      radicadaPor: [null, Validators.required],
      tipoSocietarioSas: [null, Validators.required],
      circunstanciasDescripcion: [null, Validators.required],
      objetoSaludDescripcion: [null, Validators.required],

      datosNotificacionTrabajador: this.formBuilder.group({
        nombreTrabajador: [null],
        primerNombre: [null, Validators.required],
        segundoNombre: [null],
        primerApellido: [null, Validators.required],
        segundoApellido: [null],
        emailTrabajador: [null, Validators.email],
        tieneDirecciones: [null, Validators.required],
        // justaCausaDescripcion: [null, Validators.required],
        // justaCausaId: [null, Validators.required],
        causalDespidoLista: [null, Validators.required],
      }),

      tieneDireccionesTerritoriales: [null, Validators.required],
      //tieneJustasCausas: [null, Validators.required],

      autorizacionTrabajoAdolescente: this.formBuilder.group({
        //nombreCompleto: [null, Validators.compose([Validators.required, Validators.pattern(/^([a-z]+\s?){1,4}/i)])],
        nombreCompleto: [null],
        primerNombre: [null, Validators.required],
        segundoNombre: [null],
        primerApellido: [null, Validators.required],
        segundoApellido: [null],
        tipoDocumento: [null, Validators.required],
        nroDocumentoIdentidad: [null, Validators.required],
        ultimoGradoCursado: [null, Validators.compose([Validators.required, Validators.min(0), Validators.max(12)])],
        edad: [null, Validators.required],
        esUnMenor: [null],
        fechaNacimiento: [null, Validators.required],
        genero: [null, Validators.required],
        tieneHijos: [null, Validators.required],
        cuantosHijos: [null],
        condicionDiscapacidad: [null, Validators.required],
        nombreInstitucionEducativa: [null, Validators.required],
        jornadaEscolar: [null, Validators.required],
        datosResidencia: this.formBuilder.group({
          direccion: [null, Validators.required],
          tipoUbicacion: [null, Validators.required],
          nombreUbicacion: [null, Validators.required],
          tipoZona: [null, Validators.required],
          departamento: [null, Validators.required],
          municipio: [null, Validators.required],
          tipoTelefono: [null, Validators.required],
          indicador: [null],
          telefono: [null, Validators.required],
          correoElectronico: [null, Validators.required]
        }),
        representanteLegal: this.formBuilder.group({
          //nombreCompleto: [null, Validators.compose([Validators.required, Validators.pattern(/^([a-z]+\s?){1,4}/i)])],
          nombreCompleto: [null],
          primerNombre: [null, Validators.required],
          segundoNombre: [null],
          primerApellido: [null, Validators.required],
          segundoApellido: [null],
          parentesco: [null, Validators.required],
          otroParentesco: [null],
          tipoDocumento: [null, Validators.required],
          nroDocumentoIdentidad: [null, Validators.required],
        }),

        tipoCondicionesVinculacion: this.formBuilder.group({
          tipoVinculacion: [null, Validators.required],
          tipoActividad: [null, Validators.required], // Este campo es obligatorio cuando el niño, niña o adolescente es menor de 15 años
          remuneracion: [null, Validators.required],
          auxilioTransporte: [null],
          formaPago: [null, Validators.required], // Este campo es obligatorio cuando el niño, niña o adolescente es menor de 15 años
          cargo: [null, Validators.required],
          lugarTrabajoDescripcion: [null, Validators.required],
          tieneHorasTrabajoSemanal: [null, Validators.required],
          horasTrabajoSemanal: [null, [
            Validators.required,
            Validators.min(0.25)
          ]],
          tieneDirecciones: [null, Validators.required] // lista de direcciones
        }),
        datosEmpleador: this.formBuilder.group({
          tipoEmpleador: [null, Validators.required],
          nombreRazonSocial: [null, Validators.required],
          nroDocumentoNit: [null, Validators.required],
          nombreRepresentanteLegalDatosEmpleador: [null, Validators.required],
          direccion: [null, Validators.required],
          telefonoDatosEmpleador: [null, Validators.required],
          celularDatosEmpleador: [null, Validators.required],
          correoElectronicoDatosEmpleador: [null, Validators.required],
          departamentoDatosEmpleador: [null, Validators.required],
          municipioDatosEmpleador: [null, Validators.required],
        })

      }),
      busquedaEmpresa: this.formBuilder.group({
        nit: ['', Validators.pattern('^[0-9]+$')]
      }),
      autorizacionEmpresasTemporales: this.formBuilder.group({
        informacionEscrituraPublica: this.formBuilder.group({
          nroEscritura: [null, Validators.required],
          fechaExpedicion: [null, Validators.required],
          departamento: [null, Validators.required],
          municipio: [null, Validators.required],
          notaria: [null, Validators.required],
          divisionActividadEconomica: [null, Validators.required],
          grupoActividadEconomica: [null, Validators.required]
        }),

        informacionPolizaGarantia: this.formBuilder.group({
          nroPoliza: [null, Validators.required],
          valorPoliza: [null, Validators.required],
          nombreAseguradora: [null, Validators.compose([Validators.required, Validators.pattern(/^([a-z]+\s?){1,4}/i)])],
          fechaInicioVigencia: [null, Validators.required],
          fechaFinVigencia: [null, Validators.required],
        }),

        informacionRepresentanteLegal: this.formBuilder.group({
          tipoSolicitante: [null, Validators.required],
          tipoDocumento: [null, Validators.required],
          nroDocumentoIdentidad: [null, Validators.required],
          verificacionNroDocumento: [null, Validators.required],
          //nombreCompleto: [null, Validators.compose([Validators.required, Validators.pattern(/^([a-z]+\s?){1,4}/i)])],
          nombreCompleto: [null],
          primerNombre: [null, Validators.required], //TOOO: Agregar validaciones
          segundoNombre: [null],
          primerApellido: [null, Validators.required],  //TOOO: Agregar validaciones
          segundoApellido: [null],
          indicativo: [null],
          telefono: [null],
          extension: [null],
          celular: [null],
          email: [null, Validators.email]
        }),

        tieneSocios: [null, Validators.requiredTrue],

        informacionSucursal: this.formBuilder.group({
          tieneSucursales: [null, Validators.required]
        })
      })
    });
  }

  listenForErrors() {
    this.bindToValidationErrorsOf('tipoGestion');
    this.bindToValidationErrorsOf('tipoTramite');
    this.bindToValidationErrorsOf('justificacionSolicitud');
    this.bindToValidationErrorsOf('gradoAsociacion');
    this.bindToValidationErrorsOf('tipoSolicitud');
    this.bindToValidationErrorsOf('tienePactos');
    this.bindToValidationErrorsOf('tieneReglamento');
    this.bindToValidationErrorsOf('tieneOrganizacionesSindicales');
    this.bindToValidationErrorsOf('isValidOrganizaciones');
    this.bindToValidationErrorsOf('radicadaPor');
    this.bindToValidationErrorsOf('tipoSocietarioSas');
    this.bindToValidationErrorsOf('circunstanciasDescripcion');
    this.bindToValidationErrorsOf('objetoSaludDescripcion');
    this.bindToValidationErrorsOf('objetoSaludDescripcion');
    // Trabajador
    this.bindToValidationErrorsOf(['datosNotificacionTrabajador', 'nombreTrabajador']);
    this.bindToValidationErrorsOf(['datosNotificacionTrabajador', 'emailTrabajador']);
    this.bindToValidationErrorsOf(['datosNotificacionTrabajador', 'justaCausaDescripcion']);
    this.bindToValidationErrorsOf(['datosNotificacionTrabajador', 'justaCausaId']);
    this.bindToValidationErrorsOf(['datosNotificacionTrabajador', 'tieneDirecciones']);
    this.bindToValidationErrorsOf(['datosNotificacionTrabajador', 'primerNombre']);
    this.bindToValidationErrorsOf(['datosNotificacionTrabajador', 'segundoNombre']);
    this.bindToValidationErrorsOf(['datosNotificacionTrabajador', 'primerApellido']);
    this.bindToValidationErrorsOf(['datosNotificacionTrabajador', 'segundoApellido']);

    this.bindToValidationErrorsOf('tieneDireccionesTerritoriales');
    // Adolescentes
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'nombreCompleto']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'primerNombre']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'segundoNombre']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'primerApellido']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'segundoApellido']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'tipoDocumento']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'nroDocumentoIdentidad']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'ultimoGradoCursado']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'edad']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'fechaNacimiento']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'genero']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'tieneHijos']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'cuantosHijos']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'condicionDiscapacidad']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'nombreInstitucionEducativa']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'jornadaEscolar']);

    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'datosResidencia', 'direccion']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'datosResidencia', 'tipoUbicacion']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'datosResidencia', 'nombreUbicacion']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'datosResidencia', 'tipoZona']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'datosResidencia', 'departamento']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'datosResidencia', 'municipio']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'datosResidencia', 'correoElectronico']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'datosResidencia', 'tipoTelefono']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'datosResidencia',  'indicativo']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'datosResidencia', 'telefono']);

    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'representanteLegal', 'nombreCompleto']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'representanteLegal', 'primerNombre']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'representanteLegal', 'segundoNombre']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'representanteLegal', 'primerApellido']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'representanteLegal', 'segundoApellido']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'representanteLegal', 'parentesco']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'representanteLegal', 'otroParentesco']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'representanteLegal', 'tipoDocumento']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'representanteLegal', 'nroDocumentoIdentidad']);

    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'datosEmpleador', 'tipoEmpleador']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'datosEmpleador', 'nombreRazonSocial']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'datosEmpleador', 'nroDocumentoNit']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'datosEmpleador', 'nombreRepresentanteLegalDatosEmpleador']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'datosEmpleador', 'direccion']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'datosEmpleador', 'telefonoDatosEmpleador']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'datosEmpleador', 'celularDatosEmpleador']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'datosEmpleador', 'departamentoDatosEmpleador']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'datosEmpleador', 'municipioDatosEmpleador']);


    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'tieneDirecciones']);

    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'tipoCondicionesVinculacion', 'tipoVinculacion']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'tipoCondicionesVinculacion', 'tipoActividad']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'tipoCondicionesVinculacion', 'formaPago']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'tipoCondicionesVinculacion', 'remuneracion']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'tipoCondicionesVinculacion', 'cargo']);

    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'tipoCondicionesVinculacion', 'lugarTrabajoDescripcion']);
    this.bindToValidationErrorsOf(['autorizacionTrabajoAdolescente', 'tipoCondicionesVinculacion', 'tieneDirecciones']);

    // Empresas
    this.bindToValidationErrorsOf(['autorizacionEmpresasTemporales', 'informacionEscrituraPublica', 'nroEscritura']);
    this.bindToValidationErrorsOf(['autorizacionEmpresasTemporales', 'informacionEscrituraPublica', 'fechaExpedicion']);
    this.bindToValidationErrorsOf(['autorizacionEmpresasTemporales', 'informacionEscrituraPublica', 'departamento']);
    this.bindToValidationErrorsOf(['autorizacionEmpresasTemporales', 'informacionEscrituraPublica', 'municipio']);
    this.bindToValidationErrorsOf(['autorizacionEmpresasTemporales', 'informacionEscrituraPublica', 'notaria']);
    this.bindToValidationErrorsOf(['autorizacionEmpresasTemporales', 'informacionEscrituraPublica', 'divisionActividadEconomica']);
    this.bindToValidationErrorsOf(['autorizacionEmpresasTemporales', 'informacionEscrituraPublica', 'grupoActividadEconomica']);

    this.bindToValidationErrorsOf(['autorizacionEmpresasTemporales', 'informacionPolizaGarantia', 'nroPoliza']);
    this.bindToValidationErrorsOf(['autorizacionEmpresasTemporales', 'informacionPolizaGarantia', 'valorPoliza']);
    this.bindToValidationErrorsOf(['autorizacionEmpresasTemporales', 'informacionPolizaGarantia', 'nombreAseguradora']);
    this.bindToValidationErrorsOf(['autorizacionEmpresasTemporales', 'informacionPolizaGarantia', 'fechaInicioVigencia']);
    this.bindToValidationErrorsOf(['autorizacionEmpresasTemporales', 'informacionPolizaGarantia', 'fechaFinVigencia']);

    this.bindToValidationErrorsOf(['autorizacionEmpresasTemporales', 'informacionRepresentanteLegal', 'tipoSolicitante']);
    this.bindToValidationErrorsOf(['autorizacionEmpresasTemporales', 'informacionRepresentanteLegal', 'tipoDocumento']);
    this.bindToValidationErrorsOf(['autorizacionEmpresasTemporales', 'informacionRepresentanteLegal', 'nroDocumentoIdentidad']);
    this.bindToValidationErrorsOf(['autorizacionEmpresasTemporales', 'informacionRepresentanteLegal', 'verificacionNroDocumento']);
    this.bindToValidationErrorsOf(['autorizacionEmpresasTemporales', 'informacionRepresentanteLegal', 'email']);
    this.bindToValidationErrorsOf(['autorizacionEmpresasTemporales', 'informacionRepresentanteLegal', 'primerNombre']);
    this.bindToValidationErrorsOf(['autorizacionEmpresasTemporales', 'informacionRepresentanteLegal', 'segundoNombre']);
    this.bindToValidationErrorsOf(['autorizacionEmpresasTemporales', 'informacionRepresentanteLegal', 'primerApellido']);
    this.bindToValidationErrorsOf(['autorizacionEmpresasTemporales', 'informacionRepresentanteLegal', 'segundoApellido']);

    this.bindToValidationErrorsOf(['autorizacionEmpresasTemporales', 'informacionSucursal', 'tieneSucursales']);

    this.bindToValidationErrorsOf(['busquedaEmpresa', 'nit']);

  }

  onTipoDocumentoChange(tipoDocumento) {
    this.form.get(['autorizacionTrabajoAdolescente', 'representanteLegal', 'nroDocumentoIdentidad']).setValue('');
    this.isTxtNumeric = false;
    if (tipoDocumento.id == 1 || tipoDocumento.id == 5 || tipoDocumento.id == 6 || tipoDocumento.id == 8) {
      this.isTxtNumeric = true;
    } else {
      this.isTxtNumeric = false;
    }

  }
  onTipoPersonaChange(tipoPersonaE) {
    //console.log(tipoPersonaE);
    this.form.get('tipoDocumento').setValue('');
    this.form.get('numeroDocumentoIdentidad').setValue('');
    this.form.get('nit').setValue('');
    this.form.get('numeroDocumentoIdentidadTarReg').setValue('');
  }

  onChangeTipoSolicitante(tipoSolicitante) {
    //debugger;
    console.log(tipoSolicitante);
    //this.form.get(['autorizacionEmpresasTemporales', 'informacionRepresentanteLegal', 'tipoSolicitante']).setValue('');
    this.form.get(['autorizacionEmpresasTemporales', 'informacionRepresentanteLegal', 'tipoDocumento']).setValue('');
    this.form.get(['autorizacionEmpresasTemporales', 'informacionRepresentanteLegal', 'nroDocumentoIdentidad']).setValue('');
    this.form.get(['autorizacionEmpresasTemporales', 'informacionRepresentanteLegal', 'verificacionNroDocumento']).setValue('');
    //this.form.get('autorizacionEmpresasTemporales').setValue('');
    //this.form.get('nroDocumentoIdentidad').setValue('');
    //this.form.get('verificacionNroDocumento').setValue('');
  }

  onChangeTipoDocumento2(tipoDocumento) {
    //debugger;
    console.log(tipoDocumento);
    this.form.get(['autorizacionEmpresasTemporales', 'informacionRepresentanteLegal', 'nroDocumentoIdentidad']).setValue('');
    this.form.get(['autorizacionEmpresasTemporales', 'informacionRepresentanteLegal', 'verificacionNroDocumento']).setValue('');
    this.isTxtNumeric_2 = false;
    if (tipoDocumento == 1 || tipoDocumento == 5 || tipoDocumento == 6 || tipoDocumento == 8) {
      this.isTxtNumeric_2 = true;
    } else {
      this.isTxtNumeric_2 = false;
    }
  }

  selectTipoGestion(id) {
    this.bloquearFlujo = false;
    this.isCreacionEmpresa = id == this.GESTION_CREACION_EMPRESA;
    this.isEmpresaSucursal = !this.isCreacionEmpresa;

    if (this.isCreacionEmpresa) {
      this.ocultaDatoEmpresa = false;
      this.form.get(['autorizacionEmpresasTemporales', 'informacionSucursal', 'tieneSucursales']).setValue(false);
    } else
      this.form.get(['autorizacionEmpresasTemporales', 'informacionSucursal', 'tieneSucursales']).setValue(null);

    return false;
  }

  saveAndShowDescription(event) {

    this.direccionesTerritoriales.clean();
    this.isReadOnly = false;
    if (event.value) {
      this.tramiteDescription = event.value.descripcion;
      this.tramiteUrls = event.value.listURL;
      this.onDireccionTerritorialChange(-1);
    }
  }
  isVisibleDescripcionTramite(): boolean {
    return this.tramiteUrls != null && this.tramiteUrls.length > 0;
  }
  setTieneDirecciones(control: string, tiene: boolean) {
    this.form.get(control).setValue(tiene);
  };

  onDireccionTerritorialChange(idDireccionTerritorial) {
    const validTramites = [1, 5];
    const tipoTramiteId = this.form.get('tipoTramite').value.id;
    const direccionTramiteId = validTramites.indexOf(tipoTramiteId) >= 0
      ? idDireccionTerritorial : -1;

    this.sharedData.next({ direccionTramiteId: direccionTramiteId });

    if (idDireccionTerritorial > 0 && this.direccionesAdolescentes !== undefined) {
      this.direccionTerritorialServie.getPaisAndDptoBy(idDireccionTerritorial).subscribe(
        data => this.direccionesVinculacion.setDefaultInfo(data.idPais, data.idDepartamento)
      );
    }
  }
  onClickPrevisualizarPdf() {
    this.execValidations();
    if (this.isValidForm()) {
      this.isVisibleShowLoading = true;
      this.pdf.getPdf(this.form.value).then((blobData) => {
        const pdf = URL.createObjectURL(blobData);
        window.open(pdf);
        this.isVisibleShowLoading = false;
      });
    } else {
      this.commonModel.addWarning('valide los campos obligatorios');
    }
  }
  onDireccionTerritorialValid() {
    this.form.get('tieneDireccionesTerritoriales').setValue(true);
  }

  onDireccionTerritorialInvalid() {
    this.form.get('tieneDireccionesTerritoriales').setValue(null);
  }

  onDireccionesAdolescenteValid() {
    this.form.get(['autorizacionTrabajoAdolescente', 'tieneDirecciones']).setValue(true);
  }

  onDireccionesAdolescenteInvalid() {
    this.form.get(['autorizacionTrabajoAdolescente', 'tieneDirecciones']).setValue(null);
  }

  onDireccionesTipoVinculacionValid() {
    this.form.get(['autorizacionTrabajoAdolescente', 'tipoCondicionesVinculacion', 'tieneDirecciones']).setValue(true);
  }

  onDireccionesTipoVinculacionInvalid() {
    this.form.get(['autorizacionTrabajoAdolescente', 'tipoCondicionesVinculacion', 'tieneDirecciones']).setValue(null);
  }

  onDireccionesSucursalesValid() {
    this.form.get(['autorizacionEmpresasTemporales', 'informacionSucursal', 'tieneSucursales']).setValue(true);
  }

  onDireccionesSucursalesInvalid() {
    this.form.get(['autorizacionEmpresasTemporales', 'informacionSucursal', 'tieneSucursales']).setValue(null);
  }

  onRegistroHorasSemanalesValid() {
    this.tieneHorasTrabajoSemanal.setValue(true);
  }

  onRegistroHorasSemanalesInvalid() {
    this.tieneHorasTrabajoSemanal.setValue(null);
  }

  onSocioListChange() {
    const tieneSocios = this.datosSocios.collection.length !== 0;
    this.form.get(['autorizacionEmpresasTemporales', 'tieneSocios']).setValue(tieneSocios);
  }

  listenForChanges() {
    const municipio = this.form.get(['autorizacionEmpresasTemporales', 'informacionEscrituraPublica', 'municipio']);
    const notaria = this.form.get(['autorizacionEmpresasTemporales', 'informacionEscrituraPublica', 'notaria']);

    this.form.get(['autorizacionEmpresasTemporales', 'informacionEscrituraPublica', 'departamento']).valueChanges.subscribe((value) => {
      if (value) {
        municipio.reset();
        notaria.reset();
        municipio.enable();
        notaria.disable();
        this.form.get(['autorizacionEmpresasTemporales', 'informacionEscrituraPublica', 'notaria']).reset();
        this.municipioOptions$ = this.municipioService.listMunicipioByDepartement(value);
      }
    });
    this.form.get(['autorizacionTrabajoAdolescente', 'datosResidencia', 'departamento']).valueChanges.subscribe((value) => {
        this.form.get(['autorizacionTrabajoAdolescente', 'datosResidencia', 'municipio']).disable();
        if (value) {
          this.form.get(['autorizacionTrabajoAdolescente', 'datosResidencia', 'municipio']).reset();
          this.form.get(['autorizacionTrabajoAdolescente', 'datosResidencia', 'municipio']).enable();
          this.municipioOptionsNNADatosDireccion$ = this.municipioService.listMunicipioByDepartement(value.id);
        }
      }
    );
    const departamentoEmpleador = this.form.get(['autorizacionTrabajoAdolescente', 'datosEmpleador', 'departamentoDatosEmpleador']);
    const municipioEmpleador = this.form.get(['autorizacionTrabajoAdolescente', 'datosEmpleador', 'municipioDatosEmpleador']);
    departamentoEmpleador.valueChanges.subscribe((value) => {
      municipioEmpleador.disable();
      if (value) {
        municipioEmpleador.reset();
        municipioEmpleador.enable();
        this.municipioOptionsNNADatosEmpleador$ = this.municipioService.listMunicipioByDepartement(value.id);
      }
    });
    this.form.get(['autorizacionEmpresasTemporales', 'informacionEscrituraPublica', 'municipio']).valueChanges.subscribe((value) => {
      if (value) {
        notaria.reset();
        notaria.enable();
        this.notariaOptions$ = this.notariaService.listByMunicipio(value);
      }
    });

    this.form.get('tipoTramite').valueChanges.subscribe((selectItem) => {
      this.onChangeTipoTramite.next(selectItem);
      if (selectItem) {
        this.nmDireccionesPermitidas = selectItem.nmDireccionesPermitidas;
      }
      this.refreshVisibility(selectItem);
    });

    this.form.get(['autorizacionTrabajoAdolescente', 'esUnMenor']).valueChanges.subscribe((val) => {
      if (this.form.get('autorizacionTrabajoAdolescente').status === 'DISABLED')
        return;
      if (val) {
        this.form.get(['autorizacionTrabajoAdolescente', 'tipoCondicionesVinculacion', 'tipoActividad']).setValidators(Validators.required);
        this.form.get(['autorizacionTrabajoAdolescente', 'tipoCondicionesVinculacion', 'formaPago']).setValidators(Validators.required);
      } else {
        this.form.get(['autorizacionTrabajoAdolescente', 'tipoCondicionesVinculacion', 'tipoActividad']).clearValidators();
        this.form.get(['autorizacionTrabajoAdolescente', 'tipoCondicionesVinculacion', 'formaPago']).clearValidators();
        delete this.validations['autorizacionTrabajoAdolescente_tipoCondicionesVinculacion_tipoActividad'];
        delete this.validations['autorizacionTrabajoAdolescente_tipoCondicionesVinculacion_formaPago'];
        this.changeDetector.detectChanges();
      }

      this.form.get(['autorizacionTrabajoAdolescente', 'tipoCondicionesVinculacion', 'tipoActividad']).updateValueAndValidity();
      this.form.get(['autorizacionTrabajoAdolescente', 'tipoCondicionesVinculacion', 'formaPago']).updateValueAndValidity();

    });
    this.form.get(['autorizacionTrabajoAdolescente', 'tieneHijos']).valueChanges.subscribe( (value) => {
      if (this.form.get('autorizacionTrabajoAdolescente').status === 'DISABLED')
        return;
      if (value ) {
        if (value.value) {
          this.form.get(['autorizacionTrabajoAdolescente', 'cuantosHijos']).setValue(1);
          this.form.get(['autorizacionTrabajoAdolescente', 'cuantosHijos']).setValidators([Validators.required, Validators.min(1)]);
          this.cuantosHijosVisibility = false;
        }
        else {
          this.cuantosHijosVisibility = true;
          this.form.get(['autorizacionTrabajoAdolescente', 'cuantosHijos']).clearValidators();
          this.form.get(['autorizacionTrabajoAdolescente', 'cuantosHijos']).setValue(0);
        }
      }
    });
    this.form.get(['autorizacionTrabajoAdolescente', 'representanteLegal', 'tipoDocumento']).valueChanges.subscribe((v) => {

      if (this.form.get('autorizacionTrabajoAdolescente').status === 'DISABLED')
        return;

      this.isNinioaRepresentateNit = v && v.id === TIPODOC_NIT;
    });

    this.form.get(['autorizacionTrabajoAdolescente', 'edad']).valueChanges.subscribe(v => {
      if (this.form.get('autorizacionTrabajoAdolescente').status === 'DISABLED')
        return;

      if (v && parseInt(v.nombre) < 15) {
        this.form.get(['autorizacionTrabajoAdolescente', 'esUnMenor']).setValue(true);
      } else {
        this.form.get(['autorizacionTrabajoAdolescente', 'esUnMenor']).reset();
      }
    });
    const divisionActividadEconomica = this.form.get(['autorizacionEmpresasTemporales', 'informacionEscrituraPublica', 'divisionActividadEconomica']);
    const grupoActividadEconomica = this.form.get(['autorizacionEmpresasTemporales', 'informacionEscrituraPublica', 'grupoActividadEconomica']);
    divisionActividadEconomica.valueChanges.subscribe((value) => {
      if (divisionActividadEconomica.status === 'DISABLED' || !value ) return;
      grupoActividadEconomica.reset();
      this.grupoActividadEconomica$ = this.constanteWcService.listActividadesEconomicas(value.idActividadEconomica);
    });
    const parentescoControl = this.form.get(['autorizacionTrabajoAdolescente', 'representanteLegal', 'parentesco']);
    const otroParentescoControl = this.form.get(['autorizacionTrabajoAdolescente', 'representanteLegal', 'otroParentesco']);
      parentescoControl.valueChanges.subscribe((value => {
        if (value) {
          if (value.id === 11333) {
            otroParentescoControl.setValidators([Validators.required]);
            otroParentescoControl.enable();
            this.otroParentescoIsVisible = true;
          } else {
            otroParentescoControl.setValue(null);
            otroParentescoControl.disable();
            this.otroParentescoIsVisible = false;
          }
        }
      }));
      this.form.get('tieneOrganizacionesSindicales').valueChanges.subscribe((value => {
        if (value) {
          if (!value.value) {
            if (this.form.get('isValidOrganizaciones')) {
              this.form.get('isValidOrganizaciones').disable();
            }
            if (this.organizacionesSindicales) {
              this.organizacionesSindicales.collection = null;
            }
          } else {
            if (this.form.get('isValidOrganizaciones')) {
              this.form.get('isValidOrganizaciones').enable();
              this.form.get('isValidOrganizaciones').setValue(null);
            }
          }
        }
      }));
  }

  refreshVisibility(selectItem) {

    this.visibility = {};
    this.disableAll();
    if (selectItem) {

      switch (selectItem.id) {

        case TIPO_TRAMITE['1']: // APROBACIÓN DEL REGLAMENTO DE TRABAJO DE LAS EMPRESAS DE SERVICIOS TEMPORALES.
          this.visibility = {
            circunstanciasDescripcion: true,
            tieneDireccionesTerritoriales: true,
            tieneRadicadosReferidos: true,
            autorizacionEmpresasTemporales: true
          };

          this.isEmpresaSucursal = false;
          break;

        case TIPO_TRAMITE['2']: // COMPROBACIÓN DE TRABAJO SIN SOLUCIÓN DE CONTINUIDAD.
          this.visibility = {
            circunstanciasDescripcion: true,
            tieneDireccionesTerritoriales: true,
            tieneRadicadosReferidos: true

          };
          break;

        case TIPO_TRAMITE['3']: // AUTORIZACIÓN PARA LABORAR HORAS EXTRAS.
          this.visibility = {
            circunstanciasDescripcion: true,
            tienePactos: true,
            tieneReglamento: true,
            tieneDireccionesTerritoriales: true,
            tieneRadicadosReferidos: true,
            tieneOrganizacionesSindicales: true,
          };
          break;

        case TIPO_TRAMITE['4']: // CERTIFICACIÓN DE TRABAJADORES EN SITUACIÓN DE DISCAPACIDAD CONTRATADOS POR UN EMPLEADOR.
          this.visibility = {
            circunstanciasDescripcion: true,
            tieneDireccionesTerritoriales: true,
            tieneRadicadosReferidos: true
          };
          break;

        case TIPO_TRAMITE['5']: // AUTORIZACIÓN PARA EL FUNCIONAMIENTO DE EMPRESAS DE SERVICIOS TEMPORALES (EST) Y DE SUS SUCURSALES.
          this.visibility = {
            tipoGestion: true,
            tipoSocietarioSas: true,
            circunstanciasDescripcion: true,
            tieneDireccionesTerritoriales: true,
            tieneRadicadosReferidos: true,
            autorizacionEmpresasTemporales: true
          };

          this.isCreacionEmpresa = true;
          break;

        case TIPO_TRAMITE['6']: // AUTORIZACIÓN PARA TERMINACIÓN DE CONTRATOS DE TRABAJADORAS EN ESTADO DE EMBARAZO O LACTANCIA
          this.visibility = {
            circunstanciasDescripcion: true,
            datosNotificacionTrabajador: true,
            tieneDireccionesTerritoriales: true,
            tieneRadicadosReferidos: true
          };
          break;
        // AUTORIZACIÓN PARA LA TERMINACIÓN DEL VÍNCULO LABORAL O DE TRABAJO ASOCIATIVO A TRABAJADORES EN SITUACIÓN DE DISCAPACIDAD.
        case TIPO_TRAMITE['7']:
          this.visibility = {
            circunstanciasDescripcion: true,
            datosNotificacionTrabajador: true,
            tieneDireccionesTerritoriales: true,
            tieneRadicadosReferidos: true
          };
          break;
        // AUTORIZACIÓN AL EMPLEADOR PARA DESPIDO COLECTIVO DE TRABAJADORES POR CLAUSURA
        // DE LABORES TOTAL O PARCIAL EN FORMA DEFINITIVA O TEMPORAL.
        case TIPO_TRAMITE['8']:
          this.visibility = {
            justificacionSolicitud: true,
            circunstanciasDescripcion: true,
            tieneDireccionesTerritoriales: true,
            tieneRadicadosReferidos: true
          };
          break;

        case TIPO_TRAMITE['9']: // AUTORIZACIÓN PARA TRABAJAR A NIÑOS, NIÑAS O ADOLESCENTES.

          this.visibility = {
            circunstanciasDescripcion: true,
            tieneDireccionesTerritoriales: true,
            tieneRadicadosReferidos: true,
            autorizacionTrabajoAdolescente: true
          };
          break;

        case TIPO_TRAMITE['10']: // DECLARATORIA DE UNIDAD DE EMPRESA.

          this.visibility = {
            circunstanciasDescripcion: true,
            tieneDireccionesTerritoriales: true,
            tieneRadicadosReferidos: true,
          };
          break;

        case TIPO_TRAMITE['11']: // AUTORIZACIÓN A EMPRESA PARA DISMINUCIÓN DE CAPITAL.

          this.visibility = {
            circunstanciasDescripcion: true,
            tieneDireccionesTerritoriales: true,
            tieneRadicadosReferidos: true
          };
          break;
        // AUTORIZACIÓN AL EMPLEADOR PARA LA SUSPENSIÓN TEMPORAL DE ACTIVIDADES HASTA POR 120 DÍAS.
        case TIPO_TRAMITE['12']:

          this.visibility = {
            justificacionSolicitud: true,
            tieneDireccionesTerritoriales: true,
            tieneRadicadosReferidos: true,
            circunstanciasDescripcion: true
          };
          break;
        // CONSTATACIÓN DE CESE DE ACTIVIDADES.
        case TIPO_TRAMITE['13']:

          this.visibility = {
            circunstanciasDescripcion: true,
            tieneDireccionesTerritoriales: true,
            tieneRadicadosReferidos: true
          };
          break;
        // AUTORIZACIÓN PARA EL PAGO PARCIAL DE CESANTIAS, PARA LA REALIZACIÓN DE PLANES DE VIVIENDA.
        case TIPO_TRAMITE['14']:

          this.visibility = {
            circunstanciasDescripcion: true,
            tieneDireccionesTerritoriales: true,
            tieneRadicadosReferidos: true
          };
          break;
        // CONVOCATORIA DE INTEGRACIÓN DEL TRIBUNAL DE ARBITRAMENTO PARA LA SOLUCIÓN DE CONFLICTOS COLECTIVOS LABORALES.
        case TIPO_TRAMITE['15']:
          this.visibility = {
            radicadaPor: true,
            circunstanciasDescripcion: true,
            tieneDireccionesTerritoriales: true,
            tieneRadicadosReferidos: true,
          };
          break;
        // EXPEDICIÓN DEL CERTIFICADO DE EXISTENCIA Y REPRESENTACIÃ“N` LEGAL DE LAS ASOCIACIONES DE PENSIONADOS.
        case TIPO_TRAMITE['16']:

          this.visibility = {
            tipoSolicitud: true,
            gradoAsociacion: true,
            objetoSaludDescripcion: true,
            tieneDireccionesTerritoriales: true,
            tieneRadicadosReferidos: true,
          };
          break;
        // CERTIFICACIONES Y/O COPIAS DE LOS REGISTROS DE LAS ORGANIZACIONES SINDICALES ANTE EL MINISTERIO DEL TRABAJO.
        case TIPO_TRAMITE['17']:
          this.visibility = {
            tipoSolicitud: true,
            objetoSaludDescripcion: true,
            tieneDireccionesTerritoriales: true,
            tieneRadicadosReferidos: true,
          };
          break;
        case TIPO_TRAMITE['18']: // APROBACIÓN DE LOS ESTATUTOS O REFORMAS DE UNA ASOCIACIÓN DE PENSIONADOS.

          this.visibility = {
            gradoAsociacion: true,
            circunstanciasDescripcion: true,
            tieneDireccionesTerritoriales: true,
            tieneRadicadosReferidos: true,
          };
          break;

        case TIPO_TRAMITE['19']: // RECONOCIMIENTO DE LA PERSONERÍA JURÍDICA DE LAS ASOCIACIONES DE PENSIONADOS.

          this.visibility = {
            gradoAsociacion: true,
            circunstanciasDescripcion: true,
            tieneDireccionesTerritoriales: true,
            tieneRadicadosReferidos: true,
          };
          break;

        case TIPO_TRAMITE['20']: // CANCELACIÓN DE LA PERSONERÍA JURÍDICA DE ASOCIACIONES DE PENSIONADOS.

          this.visibility = {
            gradoAsociacion: true,
            circunstanciasDescripcion: true,
            tieneDireccionesTerritoriales: true,
            tieneRadicadosReferidos: true,
          };
          break;

        case TIPO_TRAMITE['21']: // OTRO
          this.visibility = {
            circunstanciasDescripcion: true,
            tieneDireccionesTerritoriales: true,
            tieneRadicadosReferidos: true,
          };
          break;
        case TIPO_TRAMITE['22']: // Autorización de prácticas laborales para adolescentes
          this.visibility = {
            circunstanciasDescripcion: true,
            tieneDireccionesTerritoriales: true,
            tieneRadicadosReferidos: true,
            datosPracticaAdolecente: true,
          };
          this.isPopUpDownloadDocx = true;
          break;
        default:
          this.visibility = {
            circunstanciasDescripcion: true,
            tieneDireccionesTerritoriales: true,
            tieneRadicadosReferidos: true,
          };
          break;
      }

      this.enableGroupByObject(this.visibility);
    }

    this.changeDetector.detectChanges();
  }

  public isValidForm(): boolean {
    //Emitir validaciones
    this.validationSubject.next(null);
    return this.form.valid;
  }

  disableAll() {

    this.isCreacionEmpresa = false;
    this.isEmpresaSucursal = false;
    this.bloquearFlujo = false;

    this.resetFormForEmpresaSearch();

    this.form.get('tipoGestion').disable();
    this.form.get('justificacionSolicitud').disable();
    this.form.get('gradoAsociacion').disable();
    this.form.get('tipoSolicitud').disable();
    this.form.get('tienePactos').disable();
    this.form.get('tieneReglamento').disable();
    this.form.get('tieneOrganizacionesSindicales').disable();
    if ( this.form.get('isValidOrganizaciones')) {
      this.form.get('isValidOrganizaciones').disable();
    }
    this.form.get('radicadaPor').disable();
    this.form.get('tipoSocietarioSas').disable();
    this.form.get('circunstanciasDescripcion').disable();
    this.form.get('objetoSaludDescripcion').disable();
    this.form.get('datosNotificacionTrabajador').disable();
    this.form.get('tieneDireccionesTerritoriales').disable();
    // this.form.get('tieneRadicadosReferidos').disable();
    this.form.get('autorizacionTrabajoAdolescente').disable({ onlySelf: false });
    this.form.get('autorizacionEmpresasTemporales').disable({ onlySelf: false });
    if (this.form.get('datosPracticaAdolecente')) {
      this.form.get('datosPracticaAdolecente').disable();
    }
  }

  enableGroupByObject(toEnable) {
    for (const val of Object.keys(toEnable)) {
      const c = this.form.get(val);
      if (c) {
        c.enable();
      }
    }
  }

  showJustaCausaDescripcionDialog() {
    this.justaCausaDescripcion.showDialog(this.listseleccionados);
  }

  onSelectJustaCausa(justasCausas) {
    // this.form.get(['datosNotificacionTrabajador', 'justaCausaDescripcion']).setValue(justaCausa.nombre);
    // this.form.get(['datosNotificacionTrabajador', 'justaCausaId']).setValue(justaCausa.id);
   /* const filtros = [
      justaCausa
    ];*/
   justasCausas = justasCausas.filter(value => value.check_description);
    this.listseleccionados = [...justasCausas];
      /*if (justaCausa.check_description) {
        for(let i = 0; i < this.listseleccionados.length; i++) {
          if (this.listseleccionados[i].idJustasCausas === justaCausa.idJustasCausas) {
            // console.log("La justa causa esta repetida " + elementList.description_name + ", por favor eliminarla.");
              this.Alert("La justa causa: " + this.listseleccionados[i].description_name + ", ya ha sido Seleccionada.");
            return;
          }

        }
        this.listseleccionados = [...filtros, ...this.listseleccionados];

      } else {
        const indexAlreadySelected = this.listseleccionados.findIndex(value => value.idJustasCausas === justaCausa.idJustasCausas);
        if (indexAlreadySelected !== -1) {
          this.listseleccionados.splice(indexAlreadySelected, 1);
          this.listseleccionados = [...this.listseleccionados];
        }
      }*/
      console.log(this.listseleccionados);
  }

  getDatosTramiteForm(): ITramiteForm {

    const result = this.form.value;
    if (this.visibility.datosNotificacionTrabajador) {
      result.listaDireccionesTrabajador = this.direccionesTrabajador.contacts;
    }

    if (this.datosSocios) {
      result.listaSocios = this.datosSocios.collection;
    }

    if (this.direccionesSucursales) {
      result.listaDireccionesSucursales = this.direccionesSucursales.contacts;
    }

    if (this.visibility.autorizacionTrabajoAdolescente) {
      result.listaDireccionesAdolescente = [<IDireccionForm>{direccion: result.autorizacionTrabajoAdolescente.datosResidencia.direccion,
      municipio: result.autorizacionTrabajoAdolescente.datosResidencia.municipio, pais: {id: 1, codigo: 'CO'},
        departamento: result.autorizacionTrabajoAdolescente.datosResidencia.departamento}];
      result.listaDireccionesVinculacion = this.direccionesVinculacion.contacts;

      result.autorizacionTrabajoAdolescente.tipoCondicionesVinculacion.horarioLaboral = this.registroHorario.getRegistroHorario();
      result.autorizacionTrabajoAdolescente.tipoCondicionesVinculacion.sumHours = this.registroHorario.totalRegistroHorario;
    }

    if (this.direccionesTerritoriales) {
      result.listaDireccionesTerritoriales = this.direccionesTerritoriales.collection;
    }

    if (this.radicadosReferidos) {
      result.listaRadicadosReferidos = this.radicadosReferidos.collection;
    }

    if (this.listseleccionados) {
      if (this.listseleccionados.length > 0){
        result.listaJustaCausa = this.listseleccionados;
      }
    }

    if (this.organizacionesSindicales) {
      result.listaOrganizacionesSindicales = this.organizacionesSindicales.collection;
    }

    if (result.datosPracticaAdolecente) {
      result.strJsonDatosBasicosPdf = this.pdf.getDataAsJsonStr(this.form.value);
      result.datosBasicosAdolecente = result.datosPracticaAdolecente.datosBasicosAdolecente;
      result.datosPracticaAdolecente = null;
    }
    return result;
  }

  validarDireccionesSucursales() {
    const formPath = ['autorizacionEmpresasTemporales', 'informacionSucursal'];
    const formSucursales = this.form.get(formPath);
    if (formSucursales.enabled) {
      if (this.isEmpresaSucursal) {
        if (this.direccionesSucursales.contacts.length == 0) {
          this.execValidationErrorsOf(formSucursales, formPath.join('_'));
          this.bloquearFlujoWith('se debe registrar una sucursal');
        } else {
          this.bloquearFlujo = false;
        }
      } else {
        formSucursales.get('tieneSucursales').setValue(false);
        this.direccionesSucursales.listaDireccionesSucursales = [];
      }
    }
  }

  searchByNit(nit) {

    const {
      formTieneSocios,
      formEscrituraPublica,
      formPolizaGarantia,
      formInformacionSucursal,
      formRepresentanteLegal
    } = this.resetFormForEmpresaSearch();

    let tramite = this.form.get('tipoTramite').value.id;

    this.tramiteService.buscarEmpresa(nit)

      .flatMap((empresaInfo: EmpresaInfo) => this.resolveDependencies(empresaInfo))

      .do(empresaInfo => {
        const {
          empresa, representante, listaSocios,
          direccionSucursal, listaDireccionesSucursales,
          tiposDocumento
        } = empresaInfo;

        formEscrituraPublica.get('fechaExpedicion').setValue(fixFecha(empresa.fechaExpedicionEscritura));
        formEscrituraPublica.get('nroEscritura').setValue(empresa.numeroEscritura);
        formEscrituraPublica.get('departamento').setValue(empresa.idDepartamentoExpedicion);
        formEscrituraPublica.get('municipio').setValue(empresa.idMunicipioExpedicion);
        formEscrituraPublica.get('notaria').setValue(empresa.idNotaria);
        if (empresa.grupoActividadEconomica) {
          this.divisionActividadEconomica$.do((value) => {
            for (let i = 0 ; i < value.length; i++) {
              if (value[i].idActividadEconomica === empresa.grupoActividadEconomica.idActividadEconomicaPadre) {
                formEscrituraPublica.get('divisionActividadEconomica').setValue(value[i]);
                this.grupoActividadEconomica$ = this.constanteWcService.listActividadesEconomicas(value[i].idActividadEconomica);
                this.grupoActividadEconomica$.do((grupos) => {}, (e) => {} , () => {
                  formEscrituraPublica.get('grupoActividadEconomica').setValue(empresa.grupoActividadEconomica);
                }).subscribe();
              }
            }
          }).subscribe();
        }
        formPolizaGarantia.get('nroPoliza').setValue(empresa.numeroPoliza);
        formPolizaGarantia.get('valorPoliza').setValue(empresa.valorPoliza);
        formPolizaGarantia.get('nombreAseguradora').setValue(empresa.nombreAseguradora);
        formPolizaGarantia.get('fechaInicioVigencia').setValue(fixFecha(empresa.fechaInicialPoliza));
        formPolizaGarantia.get('fechaFinVigencia').setValue(fixFecha(empresa.fechaFinPoliza));
        const nombresApellidos = this.getNombreApellidosRepresentante(representante);
        formRepresentanteLegal.get('tipoSolicitante').setValue(representante.idTipoPersona);
        formRepresentanteLegal.get('tipoDocumento').setValue(representante.idTipoIdentificacion);
        formRepresentanteLegal.get('nroDocumentoIdentidad').setValue(representante.numeroIdentificacion);
        formRepresentanteLegal.get('verificacionNroDocumento').setValue(representante.numeroIdentificacion);
        formRepresentanteLegal.get('nombreCompleto').setValue(representante.nombreCompleto);
        formRepresentanteLegal.get('primerNombre').setValue(nombresApellidos[0]);
        formRepresentanteLegal.get('segundoNombre').setValue(nombresApellidos[1]);
        formRepresentanteLegal.get('primerApellido').setValue(nombresApellidos[2]);
        formRepresentanteLegal.get('segundoApellido').setValue(nombresApellidos[3]);
        formRepresentanteLegal.get('indicativo').setValue(representante.valIndicativo);
        formRepresentanteLegal.get('telefono').setValue(representante.valTelefono);
        formRepresentanteLegal.get('extension').setValue(representante.valExtension);
        formRepresentanteLegal.get('celular').setValue(representante.valCelular);
        formRepresentanteLegal.get('email').setValue(representante.correoElectronico);
        this.ocultaDatoEmpresa = false;
        

        const socios
          = this.form.value.listaSocios
          = this.datosSocios.collection
          = listaSocios.map(socio => trasformToSocioForm(socio, tiposDocumento));

        formTieneSocios.setValue(socios.length != 0);

        this.direccionesSucursales.listaDireccionesSucursales = listaDireccionesSucursales
          .map(trasformToDireccionForm);
        if (this.isCreacionEmpresa) {
          this.ocultaDatoEmpresa = true;
          if (this.form.get('tipoTramite').value.id === 5) {
            this.getclean (empresaInfo);
            formRepresentanteLegal.reset();
            this.form.get(['autorizacionEmpresasTemporales', 'informacionRepresentanteLegal']).reset();
          }
          this.bloquearFlujoWithOnlyMessage('No es posible continuar con el trámite, pues la empresa ya existe y tiene una autorización previamente gestionada');
        } else {
          /*const aTramitesOcultar = [5];
          const tipoTramite = this.form.get('tipoTramite').value;
          this.ocultaDatoEmpresa = aTramitesOcultar.indexOf(tipoTramite.id) !== -1;*/
          if (this.form.get('tipoTramite').value.id === 1 || this.form.get('tipoTramite').value.id === 5) {
            /*formTieneSocios.disable();
            formEscrituraPublica.disable();
            formPolizaGarantia.disable();
            formRepresentanteLegal.disable();*/
            formEscrituraPublica.get('divisionActividadEconomica').enable();
            formEscrituraPublica.get('grupoActividadEconomica').enable();
            this.isReadOnly = true;
          }
          if (this.form.get('tipoTramite').value.id === 5) {
            this.isHiddenDatosSocios = true;
            this.isEmpresaSucursal = true;
          }
        }
      })

      .do((empresaInfo) => this.sharedData.next({ nit, empresaInfo, tramite }))

      .catch(error => {
        if (this.isCreacionEmpresa) {
          this.ocultaDatoEmpresa = false;
          this.commonModel.addWarning(error.error.descripcion);
        } else {
          if (this.form.get('tipoTramite').value.id === 1) {
            this.bloquearFlujoWithOnlyMessage('No es posible continuar con el trámite, pues la empresa principal debe estar aprobada' +
              ' antes de solicitar la aprobación del reglamento de trabajo.');
          } else {
            this.bloquearFlujoWithOnlyMessage('No es posible continuar con el trámite, pues la empresa principal debe estar aprobada antes de solicitar la creación de una sucursal.');
            this.isEmpresaSucursal = false;
          }
          this.ocultaDatoEmpresa = true;
        }
        return Observable.of(error);
      })

      .subscribe();
  }

  // reseta los formularios de empresa y componentes hijos
  isHideenSucursales: boolean = true;

  resetFormForEmpresaSearch() {

    this.bloquearFlujo = false;

    const formTieneSocios = this.form.get(['autorizacionEmpresasTemporales', 'tieneSocios']);
    const formEscrituraPublica = this.form.get(['autorizacionEmpresasTemporales', 'informacionEscrituraPublica']);
    const formPolizaGarantia = this.form.get(['autorizacionEmpresasTemporales', 'informacionPolizaGarantia']);
    const formInformacionSucursal = this.form.get(['autorizacionEmpresasTemporales', 'informacionSucursal']);
    const formRepresentanteLegal = this.form.get(['autorizacionEmpresasTemporales', 'informacionRepresentanteLegal']);

    [formTieneSocios, formEscrituraPublica, formPolizaGarantia, formInformacionSucursal, formRepresentanteLegal]
      .forEach(form => form.reset());

    this.direccionesSucursales.listaDireccionesSucursales
      = this.form.value.listaSocios
      = this.datosSocios.collection
      = [];

    return { formTieneSocios, formEscrituraPublica, formPolizaGarantia, formInformacionSucursal, formRepresentanteLegal };
  }

  bloquearFlujoWith(message) {
    this.bloquearFlujo = true;
    this.commonModel.addWarning(`No es posible realizar la solicitud, ${message}`);
    this.razonBloqueo = `No es posible realizar la solicitud, ${message}`;
  }
  bloquearFlujoWithOnlyMessage(message) {
    this.bloquearFlujo = true;
    this.commonModel.addWarning(message);
    this.razonBloqueo = `No es posible realizar la solicitud, ${message}`;
  }
  Alert(message) {
    this.commonModel.addWarning(`Los datos ya fueron ingresados, ${message}`);
  }

  public renderPanelSucursales(): boolean {
    return this.isEmpresaSucursal;
  }


  private resolveDependencies(empresaInfo) {
    return this.tipoDocumentoOptions$
      .map(tiposDocumento => {
        empresaInfo.listaPersonas[0].correoElectronico = empresaInfo.listaPersonas[0].correoElectronico.toLowerCase();
        return { ...empresaInfo, tiposDocumento, representante: empresaInfo.listaPersonas[0] || {} as Persona };
      });
  }

  ageChange() {
    const fAuthWorkChild = this.form.get('autorizacionTrabajoAdolescente');
    const oEdad = fAuthWorkChild.get('edad').value;
    this.isChildLessThan15 = parseInt(oEdad.nombre) < 15;
    console.log(this.isChildLessThan15);
  }

  get tieneDireccionesTerritoriales() {
    return this.form.get('tieneDireccionesTerritoriales');
  }

  get tieneHorasTrabajoSemanal() {
    return this.form.get([
      'autorizacionTrabajoAdolescente',
      'tipoCondicionesVinculacion',
      'tieneHorasTrabajoSemanal'
    ]);
  }

  get horasTrabajoSemanal() {
    return this.form.get([
      'autorizacionTrabajoAdolescente',
      'tipoCondicionesVinculacion',
      'horasTrabajoSemanal'
    ]);
  }

  onRegistroHorasSemanalesTotal(total: any) {
    this.horasTrabajoSemanal.setValue(total);
  }

  get tipoTramite() {
    return this.form.get('tipoTramite');
  }

  setCausalDespido(causalDespido: Observable<any[]>) {
    if (!this.causalDespidos$) {
      this.causalDespidos$ = causalDespido;
    }
  }

  ShowListCausalDespido() {
    this.setCausalDespido(this.constanteWcService.listCausaDespido());
  }

  remove(index) {
    const removeVal = [...this.listseleccionados];
    removeVal.splice(index, 1);
    if (removeVal.length === 0) {
      this.onInvalid.emit();
    }
    this.listseleccionados = removeVal;
  }

  seleccion(){
    const ngModel = this.capturaListaCausalD;
    if (this.listseleccionados.length > 0) {
      const idCausal = this.listseleccionados[0].idCausalDespido;
      if (idCausal !== this.capturaListaCausalD.idCausalDespido) {
        this.listseleccionados = [];
      }
    }

}

  getMessageErrorOrganizaciones(): string {
    if (this.validations['isValidOrganizaciones']) {
      return 'El nombre, numero de personería y dirección son obligatorios';
    }
    return null;
  }

  private getNombreApellidosRepresentante(representante) {
    const nombresApellidos = [null, null, null, null];
    if (representante.primerNombre && representante.primerApellido) {
      nombresApellidos[0] = representante.primerNombre;
      nombresApellidos[1] = representante.segundoNombre;
      nombresApellidos[2] = representante.primerApellido;
      nombresApellidos[3] = representante.segundoApellido;
      return nombresApellidos;
    } else {
      representante.nombreCompleto = representante.nombreCompleto.replace('  ', ' ');
      const parts = representante.nombreCompleto.split(' ');
      if (parts.length === 1) {
        nombresApellidos[0] = parts[0];
      } else if (parts.length === 2 ) {
        nombresApellidos[0] = parts[0];
        nombresApellidos[2] = parts[1];
      } else if (parts.length === 4) {
        parts.forEach((nombre, i) => {
          nombresApellidos[i] = nombre;
        });
      }
      return nombresApellidos;
    }
  }

  test() {
    return true;
  }

  hide() {
    this.isPopUpDownloadDocx = false;
  }

  getclean (empresaInfo){
    empresaInfo.representante.idTipoPersona = null;
    empresaInfo.representante.nombreCompleto = null;
    empresaInfo.representante.idTipoIdentificacion = null;
    empresaInfo.representante.numeroIdentificacion = null;
    empresaInfo.representante.valTelefono = null;
    empresaInfo.representante.valCelular = null;
    empresaInfo.representante.correoElectronico = null;
  }
}

declare type EmpresaInfo = {
  empresa: Empresa,
  listaPersonas: Persona[],
  listaSocios: SocioEmpresa[],
  direccionSucursal: Direccion,
  listaDireccionesSucursales: Direccion[]
};

const findElementById = (arr: any[], theId) => arr.find(item => item.id == theId || item.codigo == theId);

const trasformToSocioForm = (socio, tiposDocumento) => {
  return {
    nombreCompleto: socio.nombreCompleto,
    tipoDocumento: findElementById(tiposDocumento, socio.idTipoIdentificacion),
    nroDocumentoIdentidad: socio.numeroIdentificacion
  };
};

const trasformToDireccionForm = (direccion: Direccion): IDireccionForm => {
  return {
    pais: { id: direccion.idPais, nombre: direccion.nombrePais },
    departamento: { id: direccion.idDepartamento, nombre: direccion.nombreDepartamento },
    municipio: { id: direccion.idMunicipio, nombre: direccion.nombreMunicipio },
    ciudad: direccion.valCiudad,
    direccion: direccion.valDireccion
  };
};

const fixFecha = fechaWrongFromat => fechaWrongFromat && new Date(fechaWrongFromat + 'T12:00:00Z');
