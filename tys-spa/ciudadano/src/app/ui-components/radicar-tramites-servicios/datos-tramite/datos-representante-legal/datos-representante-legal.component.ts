import {Component, Injector, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {FormBaseComponent} from '../../../../shared/utils/form-base-component';
import {Observable} from 'rxjs/Observable';
import {ConstanteWcService} from '../../../../services/bussiness/constante-wc.service';

@Component({
  selector: 'app-datos-representante-legal',
  templateUrl: './datos-representante-legal.component.html',
  styleUrls: ['./datos-representante-legal.component.css']
})
export class DatosRepresentanteLegalComponent extends FormBaseComponent implements OnInit {
  @Input()  parentForm: FormGroup;
  parentescoOptions$: Observable<any[]>;
  private tipoDocumentoOptions$: Observable<any[]>;
  @Input() validEvent: Observable<any>;
  private isHiddenOtroParentesco = true;
  constructor(injector: Injector,
              public formBuilder: FormBuilder,
              public constanteWcService: ConstanteWcService) {
    super(injector);
  }

  ngOnInit() {
    this.initForm();
    this.loadData();
    this.listenForErrors();
    this.listenForChanges();
  }

  private initForm() {
    this.form = this.formBuilder.group({
      nombreCompleto: [null],
      primerNombre: [null, Validators.required],
      segundoNombre: [null],
      primerApellido: [null, Validators.required],
      segundoApellido: [null],
      parentesco: [null, Validators.required],
      otroParentesco: [null],
      tipoDocumento: [null, Validators.required],
      nroDocumentoIdentidad: [null, Validators.required],
    });
    this.parentForm.addControl('datosRepresentanteLegal', this.form);
  }

  private loadData() {
    this.parentescoOptions$ =  this.parentescoOptions$ = this.constanteWcService.listByParentCode('PARENTESCO');
    this.tipoDocumentoOptions$ = this.constanteWcService.listByParentCode('NNA-REPRESENTANTE/TIP-IDENT');
  }

  private listenForErrors() {
    this.bindToValidationErrorsOf(['primerNombre']);
    this.bindToValidationErrorsOf(['primerApellido']);
    this.bindToValidationErrorsOf(['tipoDocumento']);
    this.bindToValidationErrorsOf(['nroDocumentoIdentidad']);
    this.bindToValidationErrorsOf(['parentesco']);
    this.bindToValidationErrorsOf(['otroParentesco']);
  }

  private listenForChanges() {
    const parentescoControl = this.form.get('parentesco');
    const otroParentescoControl = this.form.get('otroParentesco');
    parentescoControl.valueChanges.subscribe((value => {
      if (value) {
        if (<string>value.nombre === 'OTRO') {
          this.isHiddenOtroParentesco = false;
          otroParentescoControl.setValidators([Validators.required]);
        } else {
          this.isHiddenOtroParentesco = true;
          otroParentescoControl.clearValidators();
          otroParentescoControl.setValue(null);
        }
      }
    }));
    this.validEvent.subscribe((value => {
      this.execValidations();
      this.form.valid;
    }));
  }
}
