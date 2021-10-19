import {Component, EventEmitter, HostBinding, OnInit, Output, ChangeDetectorRef, Injector, ViewChild, Input,} from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { CommonModel } from 'app/ui-components/radicar-tramites-servicios/util/common.model';
import {FormBaseComponent} from '../../../../shared/utils/form-base-component';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {DatosDireccionComponent} from '../../datos-direccion/datos-direccion.component';

@Component({
  selector: 'app-organizaciones-sindicales',
  templateUrl: './organizaciones-sindicales.component.html'
})
export class OrganizacionesSindicalesComponent extends FormBaseComponent implements OnInit {
  commonModel: CommonModel;
  @Input() messageError: string;
  @Input() parentForm: FormGroup;
  @Output() onValid: EventEmitter<any> = new EventEmitter();
  @Output() onInvalid: EventEmitter<any> = new EventEmitter();
  @ViewChild('direccion') direccion: DatosDireccionComponent;
  @HostBinding('class')
  showContactForm = false;
  clazz = 'ui-sm-12 ui-md-12 ui-g-12 ui-lg-12';
  collection: Array<any> = [];
  display = false;
  constructor(
    injector: Injector,
    private formBuilder: FormBuilder,
    protected _translator: TranslateService,
    private changeDetector: ChangeDetectorRef
    ) {
      super(injector);
      this.commonModel = new CommonModel(_translator);
  }

  ngOnInit() {
    this.initForm();
    this.listenForErrors();
    this.form.get('isValidOrganizaciones').valueChanges.subscribe((value => {
      if (value) {
        if (value === true) {
          this.messageError = null;
        } else {
          this.messageError = 'El nombre, numero de personería y dirección son obligatorios';
        }
      }
    }));
  }
  showDialog() {
    this.collection = [...[{
      nombreOrganizacion: '',
      sigla: '',
      numeroPersoneria: ''
    }], ...this.collection];
    this.onChangeInput();
  }
  add() {
    const insertVal = [
        {
          nombreOrganizacion: this.form.get('nombreOrganizacion').value,
          sigla: this.form.get('sigla') ? this.form.get('sigla').value : '',
          numeroPersoneria: this.form.get('numeroPersoneria').value,
          direccion: this.form.get('direccion').value,
          address: this.direccion.contacts[0]
        }
      ];
    this.direccion.contacts = [];
    this.collection = [...insertVal, ...this.collection.filter(value => value.nombreOrganizacion !== insertVal[0].nombreOrganizacion)];
    this.onChangeInput();
  }
  remove(index) {
    const removeVal = [...this.collection];
    removeVal.splice(index, 1);
    this.collection = removeVal;
    if (this.collection.length === 0) {
      this.onInvalid.emit();
    }
  }
  setTieneDirecciones(control: string, tiene: boolean) {
    this.form.get(control).setValue(tiene);
  };
  private initForm() {
    this.form = this.formBuilder.group({
      tieneDirecciones: [null, Validators.required],
      isValidOrganizaciones: [null, Validators.required]
    });
    this.parentForm.addControl('isValidOrganizaciones', this.form.get('isValidOrganizaciones') );
  }
  isValidOrgs(): boolean {
    for (let i = 0; i < this.collection.length ; i++) {
      const currentOrg = this.collection[i];
      if (!currentOrg.nombreOrganizacion || !currentOrg.numeroPersoneria || !currentOrg.direccion) {
        this.form.get('isValidOrganizaciones').setValue(null);
        this.form.get('isValidOrganizaciones').setErrors({required: true});
        this.parentForm.get('isValidOrganizaciones').setValue(null);
        this.parentForm.get('isValidOrganizaciones').setErrors({required: true});
        return false;
      }
    }
    this.form.get('isValidOrganizaciones').setValue(true);
    this.form.get('isValidOrganizaciones').setErrors(null);
    this.parentForm.get('isValidOrganizaciones').setValue(true);
    this.parentForm.get('isValidOrganizaciones').setErrors(null);
    return true;
  }
  private listenForErrors() {
    this.bindToValidationErrorsOf('tieneDirecciones');
    this.bindToValidationErrorsOf('isValidOrganizaciones');
  }

  onSaveDireccion(direccion) {
    if (direccion.contacts[0].direccion) {
      direccion.contacts[0].direccion = <string>direccion.contacts[0].direccion.replace(/NULL|null/, '');
    }
    this.collection[direccion.index].direccion = direccion.contacts[0];
    this.onChangeInput();
  }
  getDireccionString(direccion): string {
    if (!direccion) return ;
    let cad = '';
    cad += (direccion.pais.nombre + ', ');
    cad += (direccion.departamento ? direccion.departamento.nombre : '') ;
    cad += (direccion.municipio ? (', ' + direccion.municipio.nombre) : (''));
    cad += (direccion.ciudad ? direccion.ciudad : '');
    cad += (direccion.direccion ? (', ' + direccion.direccion) : (''));
    return cad;
  }
  onChangeInput() {
    this.isValidOrgs();
    this.execValidations();
  }
}
