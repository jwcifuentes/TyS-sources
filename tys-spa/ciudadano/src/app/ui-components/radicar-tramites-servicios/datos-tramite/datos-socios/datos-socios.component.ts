import { Component, EventEmitter, Injector, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { VALIDATION_MESSAGES } from '../../../../shared/validation-messages';
import { Observable } from 'rxjs/Observable';
import { FormBaseComponent } from '../../../../shared/utils/form-base-component';
import { CommonModel } from 'app/ui-components/radicar-tramites-servicios/util/common.model';
import { TranslateService } from '@ngx-translate/core';


@Component({
  selector: 'app-datos-socios',
  templateUrl: './datos-socios.component.html'
})
export class DatosSociosComponent extends FormBaseComponent implements OnInit {

  commonModel: CommonModel;

  isEditing = false;
  isTxtNumeric = false;

  @Input() headerTitle = 'Datos socios';
  @Input() tipoDocumentoOptions;
  @Output() onRemove: EventEmitter<any> = new EventEmitter();
  @Output() onAdd: EventEmitter<any> = new EventEmitter();
  collection: Array<any> = [];

  constructor(injector: Injector,
    private formBuilder: FormBuilder,
    protected _translator: TranslateService) {
    super(injector);
    this.commonModel = new CommonModel(_translator);
  }

  ngOnInit() {
    this.initForm();
    this.listenForErrors();

    this.tipoDocumentoOptions = this.tipoDocumentoOptions ? this.tipoDocumentoOptions : [
      { id: 1, nombre: 'Tipo DOcumento #1' },
      { id: 2, nombre: 'Tipo DOcumento #2' },
    ];

  }

  initForm() {
    this.form = this.formBuilder.group({
      //nombreCompleto: [null, Validators.compose([Validators.required, Validators.pattern(/^([a-z]+\s?){1,4}/i)])],
      nombreCompleto: [null],
      primerNombre: [null, Validators.required],
      segundoNombre: [null],
      primerApellido: [null, Validators.required],
      segundoApellido: [null],
      tipoDocumento: [null, Validators.required],
      nroDocumentoIdentidad: [null, Validators.required],
      verificacionNroDocumento: [null, Validators.required]
    });
  }

  listenForErrors() {
    // this.bindToValidationErrorsOf('nombreCompleto');
    this.bindToValidationErrorsOf('primerNombre');
    this.bindToValidationErrorsOf('segundoNombre');
    this.bindToValidationErrorsOf('primerApellido');
    this.bindToValidationErrorsOf('segundoApellido');
    this.bindToValidationErrorsOf('tipoDocumento');
    this.bindToValidationErrorsOf('nroDocumentoIdentidad');
    this.bindToValidationErrorsOf('verificacionNroDocumento');
  }

  addSocio() {
    this.isEditing = true;
  }

  hideDialog() {
    this.isEditing = false;
  }



  createSocio() {

    if (this.form.valid) {
      // const nombre = this.form.get('nombreCompleto');
      const primerNombre = this.form.get('primerNombre');
      const segundoNombre = this.form.get('segundoNombre');
      const primerApellido = this.form.get('primerApellido');
      const segundoApellido = this.form.get('segundoApellido');
      const tipoDocumento = this.form.get('tipoDocumento');
      const nroDocumento = this.form.get('nroDocumentoIdentidad');
      const verificar = this.form.get('verificacionNroDocumento');

      const insertVal = [
        {
          nombreCompleto: concatNombreCompleto(primerNombre.value, segundoNombre.value, primerApellido.value, segundoApellido.value),
          tipoDocumento: tipoDocumento.value,
          nroDocumentoIdentidad: nroDocumento.value,
          primerNombre: primerNombre.value,
          segundoNombre: segundoNombre.value,
          primerApellido: primerApellido.value,
          segundoApellido: segundoApellido.value
        }
      ];

      this.collection = [
        ...insertVal,
        ...this.collection
      ];

      this.onAdd.emit(this.collection);

      //nombre.reset();
      primerNombre.reset();
      segundoNombre.reset();
      primerApellido.reset();
      segundoApellido.reset();
      tipoDocumento.reset();
      nroDocumento.reset();
      verificar.reset();
      this.hideDialog();
    } else {
      this.commonModel.addWarning("La información no es correta.");
    }

  }

  onTipoDocumentoChange(tipoDocumento) {
    console.log(tipoDocumento);
    this.isTxtNumeric = false;
    //this.bindToValidationErrorsOf('nroDocumentoIdentidad');
    this.bindToValidationErrorsOf('verificacionNroDocumento');
    this.form.get('nroDocumentoIdentidad').setValue('');
    this.form.get('verificacionNroDocumento').setValue('');
    if (tipoDocumento.id == 1 || tipoDocumento.id == 5 || tipoDocumento.id == 6 || tipoDocumento.id == 8) {
      this.isTxtNumeric = true;
    } else {
      this.isTxtNumeric = false;
    }
  }

  remove(index) {
    const removeVal = [...this.collection];
    removeVal.splice(index, 1);
    this.collection = removeVal;
    this.onRemove.emit(this.collection);
  }
}

function concatNombreCompleto(primerNombre: string, segundoNombre: string, primerApellido: string, segundoApellido: string): string {

  let nombreCompleto: string = primerNombre.concat(" ");

  if (segundoNombre != null)
    nombreCompleto = nombreCompleto.concat(segundoNombre).concat(" ");

  nombreCompleto = nombreCompleto.concat(primerApellido);

  if (segundoApellido != null)
    nombreCompleto = nombreCompleto.concat(" ").concat(segundoApellido);

  return nombreCompleto;

}
