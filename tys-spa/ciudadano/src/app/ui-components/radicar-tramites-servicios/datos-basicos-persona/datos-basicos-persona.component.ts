import {Component, Injector, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {FormBaseComponent} from '../../../shared/utils/form-base-component';
import {Observable} from 'rxjs/Observable';

@Component({
  selector: 'app-datos-basicos-persona',
  templateUrl: './datos-basicos-persona.component.html',
  styleUrls: ['./datos-basicos-persona.component.css']
})
export class DatosBasicosPersonaComponent extends FormBaseComponent implements OnInit {
  @Input() parentForm: FormGroup;
  @Input() tipoDocumentoOptions$: Observable<any[]>;
  @Input() validEvent: Observable<any>;
  constructor(injector: Injector,
              formBuilder: FormBuilder) {
    super(injector);
  }

  ngOnInit() {
    this.initForm();
    this.loadData();
    this.listenForErrors();
    this.listenForChanges();
  }

  private loadData() {

  }

  private listenForErrors() {
    this.bindToValidationErrorsOf([ 'primerNombre']);
    this.bindToValidationErrorsOf([ 'segundoNombre']);
    this.bindToValidationErrorsOf([ 'primerApellido']);
    this.bindToValidationErrorsOf([ 'segundoApellido']);
    this.bindToValidationErrorsOf([ 'tipoDocumento']);
    this.bindToValidationErrorsOf([ 'nroDocumentoIdentidad']);
  }

  private listenForChanges() {
    this.validEvent.subscribe((value => {
      this.execValidations();
      this.form.valid;
    }));
  }

  private initForm() {
    this.form = this.parentForm;
  }
}
