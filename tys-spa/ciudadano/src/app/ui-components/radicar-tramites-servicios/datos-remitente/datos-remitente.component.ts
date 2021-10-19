import {ChangeDetectorRef, Component, Injector, Input, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {VALIDATION_MESSAGES} from '../../../shared/validation-messages';
import {ConstanteWcService} from '../../../services/bussiness/constante-wc.service';
import {IConstante} from '../../../domain/constante';
import {TratamientoCortesiaWcService} from '../../../services/bussiness/tratamiento-cortesia-wc.service';
import {assign} from 'rxjs/util/assign';
import {DatosDireccionComponent} from '../datos-direccion/datos-direccion.component';
import {ITramiteRemitenteForm} from '../../../shared/data-transformers/interfaces/form/tramite-remitente-form';
import {TIPO_PERSONA, TIPO_TRAMITE, TIPODOC_NIT} from '../../../../environments/environment';
import {FormBaseComponent} from '../../../shared/utils/form-base-component';
import {Subject} from 'rxjs/Subject';
import {TramiteWcService} from '../../../services/bussiness/tramite-wc.service';
import 'rxjs/add/operator/filter';
import 'rxjs/add/operator/do';
import {Observable} from 'rxjs/Observable';
import {Direccion} from '../../../domain/direccion';
import {IDireccionForm} from '../../../shared/data-transformers/interfaces/form/direccion-form';
import {DireccionTerritorialWcService} from '../../../services/bussiness/direccion-territorial-wc.service';
import {Subscription} from 'rxjs/Subscription';
import {COMMON_DEPRECATED_DIRECTIVES} from '@angular/common/src/directives';
import {concat} from 'rxjs/operators';

@Component({
  selector: 'app-datos-remitente',
  templateUrl: './datos-remitente.component.html'
})
export class DatosRemitenteComponent extends FormBaseComponent implements OnInit {

  @Input() sharedData: Subject<any>;

  @Input()
  tipoTramiteControl: FormControl;

  @ViewChild('direcciones')
  direcciones: DatosDireccionComponent;

  showEmpresaSAS: boolean;

  subscriptions: Subscription = new Subscription();

  tipoPersonaOptions$: Observable<any[]>;
  tipoDocumentoOptions$: Observable<any[]>;
  tratamientoCortesiaOptions$: Observable<Array<IConstante>>;
  actuaCalidadOptions$: Observable<Array<IConstante>>;
  tipoTelefonoOptions$: Observable<Array<IConstante>>;
  tipoEmpleadorOptions$: Observable<Array<IConstante>>;
  idTipoEmpresaOptions$: Observable<Array<IConstante>>;
  codigoActividadOptions$: Observable<Array<IConstante>>;
  @Input() ubicaciones: any[];

  constructor(injector: Injector,
              private formBuilder: FormBuilder,
              private tramiteService: TramiteWcService,
              private changeDetector: ChangeDetectorRef,
              private tratamientoCortesiaService: TratamientoCortesiaWcService,
              private direccionTerritorialService: DireccionTerritorialWcService,
              private constanteWcService: ConstanteWcService) {
    super(injector);
  }

  ngOnInit() {

    this.initForm();
    this.listenForErrors();
    this.listenForChanges();
    this.tipoDocumentoOptions$ = this.constanteWcService.listByParentCode('TIP-IDENT');
    this.actuaCalidadOptions$ = this.constanteWcService.listByParentCode('TIP-ACTC');
    this.tipoTelefonoOptions$ = this.constanteWcService.listByParentCode('TIP-TELC');
    this.tipoEmpleadorOptions$ = this.constanteWcService.listByParentCode('TIP_EMP_TS');
    this.tratamientoCortesiaOptions$ = this.tratamientoCortesiaService.list();
    this.tipoPersonaOptions$ = this.constanteWcService.listByParentCode('TIP-PERS');
    this.idTipoEmpresaOptions$ = this.constanteWcService.listByParentCode('EMP_SAS_TS');
    this.codigoActividadOptions$ = this.constanteWcService.listByParentCode('COD_CII_TS');
    this.initSharedData();
  }

  ngOnDestroy() {
    this.subscriptions.unsubscribe()
  }

  initForm() {
    this.form = this.formBuilder.group({
      tipoPersona: [null, Validators.required],
      tipoDocumento: [null, Validators.required],
      numeroDocumentoIdentidad: [null, Validators.required],
      numeroDocumentoIdentidadTarReg: [null, Validators.required],
      nit: [null, Validators.required],
      razonSocial: [null, Validators.required],
      tratamientoCortesia: [null, Validators.required],
      actuaCalidad: [null, Validators.required],
      nombreApellidos: '',
      primerNombre: [null, Validators.compose([Validators.required, Validators.pattern(/^([a-z]+\s?){1,4}/i)])],
      segundoNombre: [null, Validators.compose([Validators.pattern(/^([a-z]+\s?){1,4}/i)])],
      primerApellido: [null, Validators.compose([Validators.required, Validators.pattern(/^([a-z]+\s?){1,4}/i)])],
      segundoApellido: [null, Validators.compose([Validators.pattern(/^([a-z]+\s?){1,4}/i)])],
      tipoTelefono: [null, Validators.required],
      indicativo: [null],
      numeroTelefono: [null, Validators.compose([Validators.required, Validators.maxLength(14)])],
      extencion: [null, Validators.maxLength(6)],
      correoElectronico: [null, Validators.email],
      verificarCorreoElectronico: [null, Validators.email],
      tipoEmpleador: [null],
      codigoActividad: [null, Validators.required],
      descripcionCodigoActividad: [null],
      idTipoEmpresa: [null, Validators.required],
      autorizaEnvioViaCorreoElectrinico: [null],
      autorizaActosAdministrativosElectrinico: [null],
      datosRepresentanteLegal: this.formBuilder.group({
        //nombreCompleto: [null, Validators.compose([Validators.required, Validators.pattern(/^([a-z]+\s?){1,4}/i)])],
        primerNombreRl: [null, Validators.required],
        segundoNombreRl: [null],
        primerApellidoRl: [null, Validators.required],
        segundoApellidoRl: [null],
        tipoDocumento: [null, Validators.required],
        numeroDocumentoIdentidad: [null],
        nit: [null],
        numeroDocumentoIdentidadTarReg: [null]
      }),
      tieneDirecciones: [null, Validators.required]
    });

    this.form.get('autorizaEnvioViaCorreoElectrinico').setValue(true);
    this.form.get('autorizaActosAdministrativosElectrinico').setValue(true);

  }

  listenForErrors() {
    this.bindToValidationErrorsOf('tipoPersona');
    this.bindToValidationErrorsOf('tipoDocumento');
    this.bindToValidationErrorsOf('numeroDocumentoIdentidad');
    this.bindToValidationErrorsOf('numeroDocumentoIdentidadTarReg');
    this.bindToValidationErrorsOf('nit');
    this.bindToValidationErrorsOf('razonSocial');
    this.bindToValidationErrorsOf('tratamientoCortesia');
    this.bindToValidationErrorsOf('actuaCalidad');
    //this.bindToValidationErrorsOf('nombreApellidos');
    this.bindToValidationErrorsOf('primerNombre');
    this.bindToValidationErrorsOf('segundoNombre');
    this.bindToValidationErrorsOf('primerApellido');
    this.bindToValidationErrorsOf('segundoApellido');
    this.bindToValidationErrorsOf('tipoTelefono');
    this.bindToValidationErrorsOf('numeroTelefono');
    this.bindToValidationErrorsOf('extencion');
    this.bindToValidationErrorsOf('correoElectronico');
    this.bindToValidationErrorsOf('verificarCorreoElectronico');
    this.bindToValidationErrorsOf('tipoEmpleador');
    this.bindToValidationErrorsOf('codigoActividad');
    this.bindToValidationErrorsOf('descripcionCodigoActividad');
    this.bindToValidationErrorsOf('idTipoEmpresa');
    this.bindToValidationErrorsOf('autorizaEnvioViaCorreoElectrinico');
    this.bindToValidationErrorsOf('autorizaActosAdministrativosElectrinico');
    this.bindToValidationErrorsOf(['datosRepresentanteLegal', 'tipoDocumento']);
    this.bindToValidationErrorsOf(['datosRepresentanteLegal', 'primerNombreRl']);
    this.bindToValidationErrorsOf(['datosRepresentanteLegal', 'segundoNombreRl']);
    this.bindToValidationErrorsOf(['datosRepresentanteLegal', 'primerApellidoRl']);
    this.bindToValidationErrorsOf(['datosRepresentanteLegal', 'segundoApellidoRl']);
    this.bindToValidationErrorsOf(['datosRepresentanteLegal', 'numeroDocumentoIdentidad']);
    this.bindToValidationErrorsOf(['datosRepresentanteLegal', 'numeroDocumentoIdentidadTarReg']);
    this.bindToValidationErrorsOf(['datosRepresentanteLegal', 'nit']);
    this.bindToValidationErrorsOf('tieneDirecciones');
  }

  initSharedData() {

    let idTramite = null;
    const direccionSubscription = this.sharedData

      .do(data => {
        idTramite = data.tramite;
        if (data.direccionTramiteId < 0)
          this.direcciones.setDefaultInfo(0, 0);
      })

      .filter(data => data.direccionTramiteId > 0)

      .switchMap(data => this.direccionTerritorialService.getPaisAndDptoBy(data.direccionTramiteId))

      .do(info => this.direcciones.setDefaultInfo(info.idPais, info.idDepartamento))

      .subscribe();

    this.subscriptions.add(direccionSubscription);

    const isEmpresaSASSubscription = this.sharedData

      .filter(data => data.isEmpresaSas)

      .do(data => this.showEmpresaSAS = data.isEmpresaSas.value)

      .subscribe();

    this.subscriptions.add(direccionSubscription);

    const remitenteSubscription = this.sharedData

      .filter(data => data.nit)

      .switchMap(data => this.tramiteService.buscarRemitente(data.nit).map(remitente => {
        return {remitente, empresa: data.empresaInfo.empresa}
      }))

      .switchMap(data => Observable.combineLatest(
        Observable.of(data),
        this.actuaCalidadOptions$,
        this.tipoDocumentoOptions$,
        this.tipoTelefonoOptions$,
        this.tipoEmpleadorOptions$,
        this.tipoPersonaOptions$,
        this.idTipoEmpresaOptions$,
        this.tratamientoCortesiaOptions$,
        this.codigoActividadOptions$
        )
      )

      .do(([{empresa, remitente}, tiposActuaCalidad, tiposDocumento, tiposTelefono,
             tiposEmpleador, tiposPersona, tiposEmpresa,
             tratamientoCortesia, codigosActividad]) => {

        
        if(idTramite==! 5){
        this.form.get('nit').setValue(remitente.nit);
        this.form.get('extencion').setValue(remitente.extension);
        this.form.get('indicativo').setValue(remitente.indicativo);
        this.form.get('razonSocial').setValue(remitente.razonSocial);

        //this.form.get('nombreApellidos').setValue(remitente.nombre);
        this.form.get('primerNombre').setValue(remitente.nombre);

        this.form.get('numeroTelefono').setValue(remitente.telefono);
        this.form.get('correoElectronico').setValue(remitente.correoElectronico);
        this.form.get('verificarCorreoElectronico').setValue(remitente.correoElectronico);
        this.form.get('numeroDocumentoIdentidad').setValue(remitente.numeroDocumento);

        if (empresa.idTipoEmpresa) this.form.get('idTipoEmpresa').setValue(findElementById(tiposEmpresa, empresa.idTipoEmpresa));
        if (empresa.idCIIU) this.form.get('codigoActividad').setValue(findElementById(codigosActividad, empresa.idCIIU));
        if (remitente.idtipoPersona) this.form.get('tipoPersona').setValue(findElementById(tiposPersona, remitente.idtipoPersona));
        if (remitente.idtipoDocumentoIdentidad) this.form.get('tipoDocumento').setValue(findElementById(tiposDocumento, remitente.idtipoDocumentoIdentidad));
        if (remitente.idtratamientoCortesia) this.form.get('tratamientoCortesia').setValue(findElementById(tratamientoCortesia, remitente.idtratamientoCortesia));
        if (remitente.idenCalidadDe) this.form.get('actuaCalidad').setValue(findElementById(tiposActuaCalidad, remitente.idenCalidadDe));
        if (remitente.idtipoTelefono) this.form.get('tipoTelefono').setValue(findElementById(tiposTelefono, remitente.idtipoTelefono));


        this.form.get('nit').disable();
        this.form.get('tipoDocumento').disable();

        this.direcciones.setContacts([trasformToDireccionForm(remitente.direccion)])
        }
      })

      .subscribe();

    this.subscriptions.add(remitenteSubscription);
  }

  onTipoPersonaChange(tipoPersonaE) {
    //console.log(tipoPersonaE);
    this.form.get('tipoDocumento').setValue('');
    this.form.get('numeroDocumentoIdentidad').setValue('');
    this.form.get('nit').setValue('');
    this.form.get('numeroDocumentoIdentidadTarReg').setValue('');
  }

  onTipoDocumentoChange(tipoDocumento) {

    var showReCTar = false, showNit = false, showNumId = false;
    this.form.get('numeroDocumentoIdentidad').setValue('');
    this.form.get('nit').setValue('');
    this.form.get('numeroDocumentoIdentidadTarReg').setValue('');
    if (tipoDocumento.id == 5 || tipoDocumento.id == 6 || tipoDocumento.id == 1
    ) {
      this.visibility.numeroDocumentoIdentidadTarReg = true;

      this.visibility.numeroDocumentoIdentidad = false;
      this.visibility.nit = false;
      showReCTar = true;
    } else if (tipoDocumento.id == 8) {
      this.visibility.nit = true;

      this.visibility.numeroDocumentoIdentidadTarReg = false;
      this.visibility.numeroDocumentoIdentidad = false;
      showNit = true;
    } else {
      this.visibility.numeroDocumentoIdentidad = true;
      this.visibility.nit = false;
      this.visibility.numeroDocumentoIdentidadTarReg = false;
      showNumId = true;
    }

    //const showNit = tipoDocumento.id == 8;
    // this.visibility.nit = showNit;
    //this.visibility.numeroDocumentoIdentidad = !showNit;

    const nitForm = this.form.get('nit');
    const nDocForm = this.form.get('numeroDocumentoIdentidad');
    const nDocFormTRc = this.form.get('numeroDocumentoIdentidadTarReg');


    const nitAvailability = showNit ? nitForm.enable : nitForm.disable;
    const nDocAvailability = showNumId ? nDocForm.enable : nDocForm.disable;
    const nDocRecTarAvailability = showReCTar ? nDocFormTRc.enable : nDocFormTRc.disable;

    nitAvailability.apply(nitForm);
    nDocAvailability.apply(nDocForm);
    nDocRecTarAvailability.apply(nDocFormTRc);

    this.changeDetector.detectChanges();
  }

  onDireccionesRemitenteValid() {
    this.form.get('tieneDirecciones').setValue(true);
  }

  onDireccionesRemitenteInvalid() {
    this.form.get('tieneDirecciones').setValue(null);
  }

  execVisivility() {
    if (this.form.get('tipoPersona').value && this.tipoTramiteControl.value) {

      this.disableAll();

      this.visibility = {
        autorizaEnvioViaCorreoElectrinico: true,
        autorizaActosAdministrativosElectrinico: true,
        tieneDirecciones: true,
      };

      this.visibility.tipoEmpleador = this.tipoTramiteControl.value.id === TIPO_TRAMITE['9'];
      this.visibility.datosRepresentanteLegal = this.tipoTramiteControl.value.id === TIPO_TRAMITE['9'];
      this.visibility.codigoActividad = this.tipoTramiteControl.value.id === TIPO_TRAMITE['5'];
      this.visibility.descripcionCodigoActividad = this.tipoTramiteControl.value.id === TIPO_TRAMITE['5'];
      this.visibility.idTipoEmpresa = this.tipoTramiteControl.value.id === TIPO_TRAMITE['5'] && this.showEmpresaSAS;

      switch (this.form.get('tipoPersona').value.id) {
        case TIPO_PERSONA.PERSONA_JURIDICA: {
          this.visibility = assign({
            'tipoDocumento': true,
            'nit': true,
            'razonSocial': true,
            'actuaCalidad': this.tipoTramiteControl.value.id !== 16 && this.tipoTramiteControl.value.id !== 17,
            // 'nombreApellidos': true,
            'primerNombre': true,
            'segundoNombre': true,
            'primerApellido': true,
            'segundoApellido': true,
            'tipoTelefono': true,
            'numeroTelefono': true,
            'indicativo': true,
            'extencion': true,
            'correoElectronico': true,
            'verificarCorreoElectronico': true
          }, this.visibility);

          break;
        }
        case TIPO_PERSONA.PERSONA_NATURAL: {
          this.visibility = assign({
            'tipoDocumento': true,
            'numeroDocumentoIdentidad': true,
            // 'nombreApellidos': true,
            'primerNombre': true,
            'segundoNombre': true,
            'primerApellido': true,
            'segundoApellido': true,
            'numeroTelefono': true,
            'indicativo': true,
            'extencion': true,
            'correoElectronico': true,
            'verificarCorreoElectronico': true,
            'tipoTelefono': true,
            'tratamientoCortesia': this.tipoTramiteControl.value.id !== 16 && this.tipoTramiteControl.value.id !== 17
          }, this.visibility);
          break;
        }
        case TIPO_PERSONA.ENTIDAD_OFICIAL: {
          this.visibility = assign({
            'nit': true,
            'razonSocial': true,
            'tipoDocumento': true,
            'numeroDocumentoIdentidad': true,
            // 'nombreApellidos': true,
            'primerNombre': true,
            'segundoNombre': true,
            'primerApellido': true,
            'segundoApellido': true,
            'numeroTelefono': true,
            'indicativo': true,
            'extencion': true,
            'correoElectronico': true,
            'verificarCorreoElectronico': true,
            'tipoTelefono': true,
            'tratamientoCortesia': this.tipoTramiteControl.value.id !== 16 && this.tipoTramiteControl.value.id !== 17
          }, this.visibility);
          break;
        }
        case TIPO_PERSONA.ESTABLECIMIENTO_COMERCIAL: {
          this.visibility = assign({
            'nit': true,
            'razonSocial': true,
            'tipoDocumento': true,
            'numeroDocumentoIdentidad': true,
            //  'nombreApellidos': true,
            'primerNombre': true,
            'segundoNombre': true,
            'primerApellido': true,
            'segundoApellido': true,
            'numeroTelefono': true,
            'indicativo': true,
            'extencion': true,
            'correoElectronico': true,
            'verificarCorreoElectronico': true,
            'tipoTelefono': true,
            'tratamientoCortesia': this.tipoTramiteControl.value.id !== 16 && this.tipoTramiteControl.value.id !== 17
          }, this.visibility);
          break;
        }
      }

      this.enableGroupByObject(this.visibility);
    }
  }

  disableAll() {
    this.form.get('tipoDocumento').disable();
    this.form.get('numeroDocumentoIdentidad').disable();
    this.form.get('numeroDocumentoIdentidadTarReg').disable();

    this.form.get('nit').disable();
    this.form.get('razonSocial').disable();
    this.form.get('tratamientoCortesia').disable();
    this.form.get('actuaCalidad').disable();
    //this.form.get('nombreApellidos').disable();

    this.form.get('primerNombre').disable();
    this.form.get('segundoNombre').disable();
    this.form.get('primerApellido').disable();
    this.form.get('segundoApellido').disable();

    this.form.get('tipoTelefono').disable();
    this.form.get('indicativo').disable();
    this.form.get('numeroTelefono').disable();
    this.form.get('extencion').disable();
    this.form.get('correoElectronico').disable();
    this.form.get('verificarCorreoElectronico').disable();
    this.form.get('tipoEmpleador').disable();
    this.form.get('codigoActividad').disable();
    this.form.get('descripcionCodigoActividad').disable();
    this.form.get('idTipoEmpresa').disable();
    this.form.get('autorizaEnvioViaCorreoElectrinico').disable();
    this.form.get('autorizaActosAdministrativosElectrinico').disable();
    this.form.get('datosRepresentanteLegal').disable({onlySelf: false});
    this.form.get('tieneDirecciones').disable();
  }

  enableGroupByObject(toEnable) {
    for (const val in toEnable) {
      if (toEnable[val]) {
        this.form.get(val).enable();
      }

    }
  }

  listenForChanges() {
    this.form.get('tipoPersona').valueChanges.subscribe((value) => {
      this.execVisivility();
      if (value) {
        this.tipoDocumentoOptions$ = this.constanteWcService.listByParentCode('TIP-IDENT');
        if (value.id === 2) {
          this.tipoDocumentoOptions$ = this.tipoDocumentoOptions$.map((tipoDocumento) => {
            tipoDocumento = tipoDocumento.filter((documento) => documento.id === 8);
            return tipoDocumento;
          });
        }
      }
    });

    this.tipoTramiteControl.valueChanges.subscribe((value) => {
      this.execVisivility();
    });

    this.form.get('codigoActividad').valueChanges.subscribe((v) => {
      if (v) {
        this.form.get('descripcionCodigoActividad').setValue(v.nombre);
      }
    });

    if(this.tipoTramiteControl){
      this.tipoTramiteControl.valueChanges.subscribe((v) => {
        if (v) {
          if(v.id==5){
            this.tipoPersonaOptions$ = this.constanteWcService.listByParentCode('TIP-PERS');
            this.tipoPersonaOptions$=this.tipoPersonaOptions$.map((value)=>{
              value=value.filter((item)=>item.id==2);
              return value;
            })
          }else{
            this.tipoPersonaOptions$ = this.constanteWcService.listByParentCode('TIP-PERS');
          }

        }
      });
    }

    this.form.get(['datosRepresentanteLegal', 'tipoDocumento']).valueChanges.subscribe((s) => {
      if (this.form.get('datosRepresentanteLegal').status === 'DISABLED')
        return;

      if (s && s.id === TIPODOC_NIT) {
        delete this.visibility['datosRepresentanteLegal_numeroDocumentoIdentidad'];
        this.visibility['datosRepresentanteLegal_nit'] = true;
        this.form.get(['datosRepresentanteLegal', 'nit']).setValidators(Validators.required);
        this.form.get(['datosRepresentanteLegal', 'numeroDocumentoIdentidad']).clearValidators();
      } else if (s) {
        delete this.visibility['datosRepresentanteLegal_nit'];
        this.visibility['datosRepresentanteLegal_numeroDocumentoIdentidad'] = true;
        this.form.get(['datosRepresentanteLegal', 'nit']).clearValidators()
        this.form.get(['datosRepresentanteLegal', 'numeroDocumentoIdentidad']).setValidators(Validators.required);
      } else {
        delete this.visibility['datosRepresentanteLegal_nit'];
        delete this.visibility['datosRepresentanteLegal_numeroDocumentoIdentidad'];
        this.form.get(['datosRepresentanteLegal', 'nit']).clearValidators();
        this.form.get(['datosRepresentanteLegal', 'numeroDocumentoIdentidad']).clearValidators();
      }

      this.form.get(['datosRepresentanteLegal', 'nit']).updateValueAndValidity();
      this.form.get(['datosRepresentanteLegal', 'numeroDocumentoIdentidad']).updateValueAndValidity();
    });
  }

  getDatosRemitenteForm(): ITramiteRemitenteForm {
    this.form.get(['nit']).enable();
    this.form.get(['tipoDocumento']).enable();
    const result = this.form.value;
    if (!result.numeroDocumentoIdentidad) {
      result.numeroDocumentoIdentidad = result.numeroDocumentoIdentidadTarReg;
    }
    result['nombreApellidos'] = concatNombreCompleto(result['primerNombre'], result['segundoNombre'], result['primerApellido'], result['segundoApellido']);
    //console.log( "------- " + JSON.stringify(result) );

    result.listaDirecciones = this.direcciones.contacts;
    return result;
  }
}

function concatNombreCompleto(primerNombre: string, segundoNombre: string, primerApellido: string, segundoApellido: string): string {

  let nombreCompleto: string = primerNombre ? primerNombre.concat(' ') : '';

  if (segundoNombre != null)
    nombreCompleto = nombreCompleto.concat(segundoNombre).concat(' ');

  nombreCompleto = nombreCompleto.concat(primerApellido);

  if (segundoApellido != null)
    nombreCompleto = nombreCompleto.concat(' ').concat(segundoApellido);

  return nombreCompleto;

}


const findElementById = (arr: any[], theId) => arr.find(item => item.id == theId || item.codigo == theId);

const trasformToDireccionForm = (direccion: Direccion): IDireccionForm => {
  return {
    isExternalInfo: true,
    pais: {id: direccion.idPais, nombre: direccion.nombrePais},
    departamento: {id: direccion.idDepartamento, nombre: direccion.nombreDepartamento},
    municipio: {id: direccion.idMunicipio, nombre: direccion.nombreMunicipio},
    ciudad: direccion.valCiudad,
    codigoPostal: direccion.codigoPostal,
    direccion: direccion.valDireccion
  }
};
