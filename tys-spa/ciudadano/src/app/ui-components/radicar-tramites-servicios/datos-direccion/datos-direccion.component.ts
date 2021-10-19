import { ChangeDetectorRef, Component, EventEmitter, HostBinding, Injector, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { tassign } from 'tassign';
import { VALIDATION_MESSAGES } from 'app/shared/validation-messages';
import { Subscription } from 'rxjs/Subscription';
import { IPais } from 'app/domain/pais';
import { IMunicipio } from 'app/domain/municipio';
import { IDepartamento } from 'app/domain/departamento';
import { IConstante } from 'app/domain/constante';
import { ConstanteWcService } from 'app/services/bussiness/constante-wc.service';
import { PaisWcService } from 'app/services/bussiness/pais-wc.service';
import { DepartamentoWcService } from 'app/services/bussiness/departamento-wc.service';
import { MunicipioWcService } from 'app/services/bussiness/municipio-wc.service';
import { IDireccionForm } from 'app/shared/data-transformers/interfaces/form/direccion-form';
import { distinctUntilChanged } from 'rxjs/operators';

import { FormBaseComponent } from '../../../shared/utils/form-base-component';

enum FormContextEnum {
  SAVE,
  CREATE
}

@Component({
  selector: 'app-datos-direccion',
  templateUrl: './datos-direccion.component.html',
})
export class DatosDireccionComponent extends FormBaseComponent implements OnInit, OnDestroy {

  form: FormGroup;
  display = false;
  @Input() editable = true;
  @Input() headerTitle = 'FORMS.RADICAR_TRAMITES_SERVICIOS.DATOS_DIRECCION.TITLE';
  validations: any = {};
  canAddMore = true;

  @HostBinding('class')
  clazz = 'ui-sm-12 ui-md-12 ui-g-12 ui-lg-12';

  @Input() single = false;
  @Input() paisOptions$: Observable<IPais[]>;
  @Input() departamentoOptions$: Observable<IDepartamento[]>;
  @Input() municipioOptions$: Observable<IMunicipio[]>;
  @Input() prefijoCuadranteOptions$: Observable<IConstante[]>;
  @Input() tipoViaOptions$: Observable<IConstante[]>;
  @Input() orientacionOptions$: Observable<IConstante[]>;
  @Input() bisOptions$: Observable<IConstante[]>;
  @Input() tipoComplementoOptions$: Observable<IConstante[]>;

  contacts: Array<IDireccionForm> = [];
  listaDireccionesSucursales: IDireccionForm[] = [];
  showDireccionForm = false;
  showContactForm = false;
  formContext: FormContextEnum;
  editIndexContext: number;
  esPaisColombia = false;

  subscribers: Array<Subscription> = [];

  @Output() onValid: EventEmitter<any> = new EventEmitter();
  @Output() onInvalid: EventEmitter<any> = new EventEmitter();

  get complementos(): FormArray {
    return <FormArray>this.form.get(['direccion', 'complementos']);
  }


  constructor(injector: Injector, private formBuilder: FormBuilder,
    private constantProvider: ConstanteWcService,
    private changeDetector: ChangeDetectorRef,
    private paisProvider: PaisWcService,
    private departamentoProvider: DepartamentoWcService,
    private municipioProvider: MunicipioWcService) {
    super(injector);
    this.initForm();
    this.listenForChanges();
    this.listenForErrors();
  }

  ngOnInit(): void {
    this.prefijoCuadranteOptions$ = this.constantProvider.listByParentCode('PRE_CUA_TS');
    this.tipoViaOptions$ = this.constantProvider.listByParentCode('TIP_VIA_TS');
    this.orientacionOptions$ = this.constantProvider.listByParentCode('ORI_TS');
    this.bisOptions$ = this.constantProvider.listByParentCode('BIS_TS');
    this.tipoComplementoOptions$ = this.constantProvider.listByParentCode('COM_TS');
    this.paisOptions$ = this.paisProvider
      .list()
      .map((paises) => {
        paises.forEach((pais, i) => {
          if (pais.codigo === 'CO') {
            const paisCol = pais;
            paises[i] = paises[0];
            paises[0] = paisCol;
            this.form.get('pais').setValue(paises[0]);
            return paises;
          }
        });
        return paises;
      });
  }

  initForm() {
    this.form = this.formBuilder.group({
      'pais': [null, Validators.required],
      'departamento': [null, Validators.required],
      'municipio': [null, Validators.required],
      'ciudad': [null, Validators.required],
      'codigoPostal': [null],
      'direccion': this.formBuilder.group({
        'tipoVia': [null],
        'nroViaPrincipal': [null],
        'prefijoCuadrante': [null],
        'bis': [null],
        'orientacion': [null],
        'nroVia': [null],
        'prefijoCuadrante_se': [null],
        'nroPlaca': [null],
        'orientacion_se': [null],
        'complementos': this.formBuilder.array([])
      })
    });
  }

  buildComplemento() {
    return this.formBuilder.group({
      'tipoComplemento': [null],
      'complementoAdicional': [null]
    });
  }

  addComplemento() {
    this.complementos.push(this.buildComplemento());
  }

  listenForErrors() {
    this.bindToValidationErrorsOf('pais');
    this.bindToValidationErrorsOf('departamento');
    this.bindToValidationErrorsOf('municipio');
    this.bindToValidationErrorsOf('ciudad');
    this.bindToValidationErrorsOf('codigoPostal');
  }

  removeComplemento(index) {
    this.complementos.removeAt(index);
  }

  listenForChanges() {
    const paisControl = this.form.get('pais');
    const departamentoControl = this.form.get('departamento');
    const municipioControl = this.form.get('municipio');
    const ciudad = this.form.get('ciudad');

    this.subscribers.push(paisControl.valueChanges.distinctUntilChanged().subscribe(value => {
      if (this.editable && value) {
        // verificar el pais es colombia para cambiar el label 'Departamento'
        this.changeDetector.markForCheck();
        this.esPaisColombia = this.checkForColombia(value);
        if (this.esPaisColombia) {
          departamentoControl.enable();
          municipioControl.enable();
          this.form.get('ciudad').disable();
          this.departamentoOptions$ = this.departamentoProvider.listDepartmentsByCountry(value.id);
        } else {
          departamentoControl.disable();
          municipioControl.disable();
          this.form.get('ciudad').enable();
        }
      }

    }));

    this.subscribers.push(departamentoControl.valueChanges.distinctUntilChanged().subscribe(value => {
      if (this.editable && value) {

        municipioControl.enable();
        this.municipioOptions$ = this.municipioProvider.listMunicipioByDepartement(value.id);
      }
    }));
  }

  checkForColombia(pais) {
    return pais.codigo === 'CO';
  }

  listenForBlurEvents(control: string) {
    const stringControl = control.toString().replace(/,/g, '_');
    const ac = this.form.get(control);
    ////debugger;
    try {
      if (ac.touched && ac.invalid) {
        const error_keys = Object.keys(ac.errors);
        const last_error_key = error_keys[error_keys.length - 1];
        this.validations[stringControl] = VALIDATION_MESSAGES[last_error_key];
      }
    } catch (error) {

    }

  }

  bindToValidationErrorsOf(control: string | Array<string>) {
    const ac = this.form.get(control);
    const stringControl = control.toString().replace(/,/g, '_');
    ac.valueChanges.subscribe(value => {
      ////debugger;
      try {
        if ((ac.touched || ac.dirty) && ac.errors) {
          const error_keys = Object.keys(ac.errors);
          const last_error_key = error_keys[error_keys.length - 1];
          this.validations[stringControl] = VALIDATION_MESSAGES[last_error_key];
        } else {
          delete this.validations[stringControl];
        }
      } catch (error) {

      }

    });
  }

  saveAndRetriveContact(): any {

    const pais = this.form.get('pais');
    console.log('-------------------------' + pais);
    const departamento = this.form.get('departamento');
    console.log('-------------------------' + departamento);
    const municipio = this.form.get('municipio');
    console.log('-------------------------' + municipio);
    const ciudad = this.form.get('ciudad');
    const codigoPostal = this.form.get('codigoPostal');
    let toSave = null;

    if (this.esPaisColombia) {
      toSave = tassign({
        pais: pais.value,
        departamento: departamento.value,
        municipio: municipio.value,
        codigoPostal: codigoPostal.value
      }, this.saveDireccionData());
    } else {
      toSave = tassign({
        pais: pais.value,
        ciudad: ciudad.value,
        codigoPostal: codigoPostal.value
      }, this.saveDireccionData());
    }

    this.resetForm();

    return toSave;
  }

  resetForm() {
    // this.form.get('pais').reset();
    // this.form.get('pais').enable();
    // this.form.get('departamento').reset();
    // this.form.get('departamento').enable();
    this.form.get('municipio').reset();
    this.form.get('ciudad').reset();
    this.form.get('codigoPostal').reset();

    this.showContactForm = false;
    this.showDireccionForm = false;
  }

  saveDireccionData() {

    let direccion = '';
    const tipoVia = this.form.get(['direccion', 'tipoVia']);
    const noViaPrincipal = this.form.get(['direccion', 'nroViaPrincipal']);
    const prefijoCuadrante = this.form.get(['direccion', 'prefijoCuadrante']);
    const bis = this.form.get(['direccion', 'bis']);
    const orientacion = this.form.get(['direccion', 'orientacion']);
    const noVia = this.form.get(['direccion', 'nroVia']);
    const prefijoCuadrante_se = this.form.get(['direccion', 'prefijoCuadrante_se']);
    const placa = this.form.get(['direccion', 'nroPlaca']);
    const orientacion_se = this.form.get(['direccion', 'orientacion_se']);
    const complementos = this.complementos.controls;

    const value = {};

    if (tipoVia.value) {
      direccion += tipoVia.value.nombre;
      value['tipoVia'] = tipoVia.value;
      tipoVia.reset();
    }
    if (noViaPrincipal.value) {
      direccion += ' ' + noViaPrincipal.value;
      value['nroViaPrincipal'] = noViaPrincipal.value;
      noViaPrincipal.reset();
    }
    if (prefijoCuadrante.value) {
      direccion += ' ' + prefijoCuadrante.value.nombre;
      value['prefijoCuadrante'] = prefijoCuadrante.value;
      prefijoCuadrante.reset();
    }
    if (bis.value) {
      direccion += ' ' + bis.value.nombre;
      value['bis'] = bis;
      bis.reset();
    }
    if (orientacion.value) {
      direccion += ' ' + orientacion.value.nombre;
      value['orientacion'] = orientacion.value;
      orientacion.reset();
    }
    if (noVia.value) {
      direccion += ' ' + noVia.value;
      value['nroVia'] = noVia.value;
      noVia.reset();
    }
    if (prefijoCuadrante_se.value) {
      direccion += ' ' + prefijoCuadrante_se.value.nombre;
      prefijoCuadrante_se.reset();
    }
    if (placa.value) {
      direccion += ' ' + placa.value;
      value['nroPlaca'] = placa.value;
      placa.reset();
    }
    if (orientacion_se.value) {
      direccion += ' ' + orientacion_se.value.nombre;
      orientacion_se.reset();
    }
    if (complementos.length > 0) {
      complementos.forEach((val, index) => {
        direccion += ' ' + val.get('tipoComplemento').value.nombre;
        direccion += ' ' + val.get('complementoAdicional').value;
      });
      this.complementos.reset();
    }

    value['direccion'] = direccion === '' ? null : direccion;

    return value;
  }

  onFilterPais(event) {
  }

  deleteContact(index) {
    const radref = [...this.contacts];
    radref.splice(index, 1);
    this.contacts = radref;
    this.checkForInvalid();
  }

  editContact(index) {
    const values = this.contacts[index];

    this.form.patchValue(values);
    this.showContactForm = true;
    this.showDireccionForm = !!values['tipoVia'];
    this.formContext = FormContextEnum.SAVE;
    this.editIndexContext = index;
  }

  addContact() {
    this.showContactForm = true;
    this.formContext = FormContextEnum.CREATE;
  }

  setContacts(contacts: IDireccionForm[]) {
    this.contacts = contacts;
    this.checkForValid();
  }

  setDefaultInfo(idPais, idDepartamento: number) {
    this.resetForm();
    if (idPais + idDepartamento === 0) return;

    const paisControl = this.form.get('pais');
    const dptoControl = this.form.get('departamento');

    const paisSubscription = this.paisOptions$
      .map(paises => paises.find(pais => pais.id === idPais))
      .do(pais => {
        paisControl.setValue(pais);
        //paisControl.disable();
      })
      .subscribe(() => setTimeout(() => paisSubscription.unsubscribe()));

    this.departamentoProvider.listDepartmentsByCountry(idPais)
      .map(dptos => dptos.find(dpto => dpto.id === idDepartamento))
      .do(dpto => {
        dptoControl.setValue(dpto);
        //dptoControl.disable();
      })
      .subscribe()
  }

  save() {
    this.execValidationErrorsOf(this.form);
    if (this.form.valid) {
      if (this.formContext === FormContextEnum.CREATE) {
        this.contacts = [this.saveAndRetriveContact(), ...this.contacts];
        this.checkForValid();
      } else {
        const temp = [...this.contacts];
        temp[this.editIndexContext] = this.saveAndRetriveContact();
        this.contacts = temp;
      }

      this.formContext = null;
      this.editIndexContext = null;
    }
  }

  checkForValid() {
    if (this.contacts.length === 1) {
      this.onValid.emit();
    }
    return this.single ? this.canAddMore = false : null;
  }

  checkForInvalid() {
    if (this.contacts.length === 0) {
      this.onInvalid.emit();
    }
    return this.single ? this.canAddMore = true : null;
  }

  toggleDireccionVisibility() {
    this.showDireccionForm = !this.showDireccionForm;
  }

  ngOnDestroy() {
    this.subscribers.forEach(subscriber => {
      subscriber.unsubscribe();
    });
  }

  execValidationErrorsOf(form: any, parent?: string) {
    Object.keys(form.controls).forEach(key => {
      const ac = form.get(key);
      if (ac instanceof FormControl) {
        ac.markAsTouched();
        if (ac.errors) {
          console.log(ac.errors, key);
          const error_keys = Object.keys(ac.errors);
          const last_error_key = error_keys[error_keys.length - 1];
          this.validations[(parent) ? parent + '_' + key : key] = VALIDATION_MESSAGES[last_error_key];
        } else {
          delete this.validations[(parent) ? parent + '_' + key : key];
        }
      } else {
        this.execValidationErrorsOf(ac, (parent) ? parent + '_' + key : key);
      }
    });
  }

}

const findElementById = (arr: any[], theId) => arr.find(item => item.id == theId || item.codigo == theId);
