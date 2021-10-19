import {Component, Injector, Input, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {FormBaseComponent} from '../../../shared/utils/form-base-component';
import {Observable} from 'rxjs/Observable';
import {ConstanteWcService} from '../../../services/bussiness/constante-wc.service';
import {DatosDireccionComponent} from '../datos-direccion/datos-direccion.component';

@Component({
  selector: 'app-escenario-practica',
  templateUrl: './escenario-practica.component.html',
  styleUrls: ['./escenario-practica.component.css']
})
export class EscenarioPracticaComponent extends FormBaseComponent implements OnInit {
  @Input() parentForm: FormGroup;
  tipoDocumentoOptions$: Observable<any[]>;
  tipoEntidadOptions$: Observable<any[]>;
  @ViewChild('datosDireccionComponent') datosDireccionComponent: DatosDireccionComponent;
  @Input() validEvent: Observable<any>;
  constructor(injector: Injector,
              private formBuilder: FormBuilder,
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
      nombreEntidad: [null, Validators.required],
      tipoEntidad: [null, Validators.required],
      tipoIdentificacion: [null, Validators.required],
      identificacion: [null, Validators.required],
      direcciones: [null, Validators.required],
      correo: [null, [Validators.required, Validators.email]],
      telefonoFijo: [null, Validators.required],
      celular: [null, Validators.required],
      primerNombreRL: [null, Validators.required],
      segundoNombreRL: [null],
      primerApellidoRL: [null, Validators.required],
      segundoApellidoRL: [null],
      tipoDocumentoRL: [null, Validators.required],
      numeroDocumentoRL: [null, Validators.required],
      primerNombreTutor: [null, Validators.required],
      segundoNombreTutor: [null],
      primerApellidoTutor: [null, Validators.required],
      segundoApellidoTutor: [null],
      tipoDocumentoTutor: [null, Validators.required],
      numeroDocumentoTutor: [null, Validators.required],
    });
    this.parentForm.addControl('datosEscenarioPractica', this.form);
  }
  onDireccionesValid() {
    this.form.get('direcciones').setValue(this.datosDireccionComponent.contacts);
  }
  onDireccionesInValid() {
    this.form.get('direcciones').setValue(null);
  }

  private loadData() {
    this.tipoEntidadOptions$ = this.constanteWcService.listByParentCode('TIPO_ENTIDAD');
    this.tipoDocumentoOptions$ = this.constanteWcService.listByParentCode('NNA-REPRESENTANTE/TIP-IDENT');
  }

  private listenForErrors() {
    this.bindToValidationErrorsOf([ 'nombreEntidad']);
    this.bindToValidationErrorsOf([ 'tipoEntidad']);
    this.bindToValidationErrorsOf([ 'tipoIdentificacion']);
    this.bindToValidationErrorsOf([ 'identificacion']);
    this.bindToValidationErrorsOf([ 'direcciones']);
    this.bindToValidationErrorsOf([ 'correo']);
    this.bindToValidationErrorsOf([ 'telefonoFijo']);
    this.bindToValidationErrorsOf([ 'celular']);
    this.bindToValidationErrorsOf([ 'primerNombreRL']);
    this.bindToValidationErrorsOf([ 'primerApellidoRL']);
    this.bindToValidationErrorsOf([ 'tipoDocumentoRL']);
    this.bindToValidationErrorsOf([ 'numeroDocumentoRL']);
    this.bindToValidationErrorsOf([ 'primerNombreTutor']);
    this.bindToValidationErrorsOf([ 'primerApellidoTutor']);
    this.bindToValidationErrorsOf([ 'tipoDocumentoTutor']);
    this.bindToValidationErrorsOf([ 'numeroDocumentoTutor']);
  }

  private listenForChanges() {
    const telefonoControl = this.form.get('telefonoFijo');
    const celularControl = this.form.get('celular');
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
