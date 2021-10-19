import {Component, Injector, Input, OnInit, ViewChild} from '@angular/core';
import {FormBaseComponent} from '../../../../../shared/utils/form-base-component';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {DatosDireccionComponent} from '../../../datos-direccion/datos-direccion.component';
import {log} from 'util';
import {Observable} from 'rxjs/Observable';

@Component({
  selector: 'app-datos-residencia-adolecente',
  templateUrl: './datos-residencia-adolecente.component.html',
  styleUrls: ['./datos-residencia-adolecente.component.css']
})
export class DatosResidenciaAdolecenteComponent extends FormBaseComponent implements OnInit {
  @Input() parentForm: FormGroup;
  @ViewChild('direccionComponent') direccionComponent: DatosDireccionComponent;
  @Input() validEvent: Observable<any>;
  constructor(injector: Injector, public formBuilder: FormBuilder) {
    super(injector);
  }

  ngOnInit() {
    this.initForm();
    this.listenForErrors();
    this.listenForChanges();
  }

  onDireccionAdolecenteValid() {
    this.form.get('direcciones').setValue(this.direccionComponent.contacts);
  }

  onDireccionAdolecenteInvalid() {
    this.form.get('direcciones').setValue(null);
  }

  private initForm() {
    this.form = this.formBuilder.group({
      direcciones: [null, Validators.required],
      email: [null, Validators.required],
      telefonoFijo: [null, Validators.required],
      telefonoCelular: [null, Validators.required]
    });
    this.parentForm.addControl('datosResidencia', this.form);
  }

  private listenForErrors() {
    this.bindToValidationErrorsOf('direcciones');
    this.bindToValidationErrorsOf('email');
    this.bindToValidationErrorsOf('telefonoFijo');
    this.bindToValidationErrorsOf('telefonoCelular');
  }
  private listenForChanges() {
    const telefonoControl = this.form.get('telefonoFijo');
    const celularControl = this.form.get('telefonoCelular');
    telefonoControl.valueChanges.subscribe((value) => {
      if (value) {
        telefonoControl.setValidators([Validators.required]);
        celularControl.setValidators(null);
        celularControl.setErrors(null);
      } else {
        celularControl.setValidators([Validators.required]);
      }
    });
    celularControl.valueChanges.subscribe((value) => {
      if (value) {
        celularControl.setValidators([Validators.required]);
        telefonoControl.setValidators(null);
        telefonoControl.setErrors(null);
      } else {
        telefonoControl.setValidators([Validators.required]);
      }
    });
    this.validEvent.subscribe((value => {
      this.execValidations();
      this.form.valid;
    }));
  }
}
