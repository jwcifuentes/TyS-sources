import { Component, Injector, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ConstanteWcService } from '../../../../../../services/bussiness/constante-wc.service';
import { FormBaseComponent } from '../../../../../../shared/utils/form-base-component';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-auxilio-practica',
  templateUrl: './auxilio-practica.component.html',
  styleUrls: ['./auxilio-practica.component.css']
})
export class AuxilioPracticaComponent extends FormBaseComponent implements OnInit {
  @Input() parentForm: FormGroup;
  private formaEntregaOptions$: Observable<any[]>;
  private periodicidadReconocimientoOptions$: Observable<any[]>;
  private showOtro = false;
  constructor(injector: Injector,
    private formBuilder: FormBuilder,
    private constanteWcService: ConstanteWcService) {
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
      formaEntrega: [null, Validators.required],
      cual: [null],
      lugar: [null, Validators.required],
      periodicidadReconocimiento: [null, Validators.required]
    });
    this.parentForm.addControl('datosAuxilio', this.form);
  }

  private listenForErrors() {
    this.bindToValidationErrorsOf('formaEntrega');
    this.bindToValidationErrorsOf('cual');
    this.bindToValidationErrorsOf('lugar');
    this.bindToValidationErrorsOf('periodicidadReconocimiento');
  }

  private loadData() {
    this.formaEntregaOptions$ = this.constanteWcService.listByParentCode('FORMA_ENTREGA');
    this.periodicidadReconocimientoOptions$ = this.constanteWcService.listByParentCode('PERIODICIDAD_RECONO');
  }

  private listenForChanges() {
    const formaEntregaControl = this.form.get('formaEntrega');
    const otroControl = this.form.get('cual');
    formaEntregaControl.valueChanges.subscribe((value) => {
      if (value) {
        if (<string>value.nombre === 'OTRO') {
          this.showOtro = true;
          otroControl.setValidators([Validators.required]);
        } else {
          this.showOtro = false;
          otroControl.setValue(null);
          otroControl.setErrors(null);
          otroControl.clearValidators();
        }
      }
    });
  }
}
