import {Component, Injector, Input, OnInit, ViewChild} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ConstanteWcService} from '../../../../../services/bussiness/constante-wc.service';
import {FormBaseComponent} from '../../../../../shared/utils/form-base-component';
import {RegistroHorarioComponent} from '../../registro-horario/registro-horario.component';
import {Observable} from 'rxjs/Observable';

@Component({
  selector: 'app-condiciones-vinculacion',
  templateUrl: './condiciones-vinculacion.component.html',
  styleUrls: ['./condiciones-vinculacion.component.css']
})
export class CondicionesVinculacionComponent extends FormBaseComponent implements OnInit {
  diasLaborales: any [] = [
    'Lunes', 'Martes', 'Miércoles',
    'Jueves', 'Viernes', 'Sábado'
  ];

  @ViewChild('registroHorario') registroHorario: RegistroHorarioComponent;
  objetoPracticaOptions$: Observable<Array<any>>;
  @Input() parentForm: FormGroup;
  @Input() validEvent: Observable<any>;
  constructor(injector: Injector,
              private formBuilder: FormBuilder,
              private constanteWcService: ConstanteWcService) {
    super(injector);
  }

  ngOnInit() {
    this.listenForChanges();
    this.initForm();
    this.objetoPracticaOptions$ = this.constanteWcService.listByParentCode('OBJETO_PRACTICA');
    this.objetoPracticaOptions$ = this.objetoPracticaOptions$.map(value => {
      const arrayControl = this.form.get('objetoPractica') as FormArray;
      value.forEach((objectoPractica) => {
        arrayControl.push(this.createObjectoPractica(objectoPractica));
      });
      return arrayControl.controls;
    });
    this.listenForErros();
  }
  private createObjectoPractica( objetoPractica): FormGroup {
    return this.formBuilder.group({
      isSelected: [false, Validators.required],
      objetoPractica: [objetoPractica.nombre, Validators.required],
      nombre: [objetoPractica.nombre],
      id: [objetoPractica.id]
    });
  }
  private initForm() {
    this.form = this.formBuilder.group({
      nombrePractica: [null, Validators.required],
      practicaGratuita: [false, Validators.required],
      objetoPractica: this.formBuilder.array([]),
      descripcionDetallada: [null, Validators.required],
      tieneHorasTrabajoSemanal: [null, Validators.required],
      horasTrabajoSemanal: [null, [Validators.required, Validators.min(0.25)]],
      horarioLaboral: [null],
      sumHours: [null],
      nombreEntidadInstitucion: [null, Validators.required],
    });
    this.parentForm.addControl('datosCondicionVinculacion', this.form);
  }
  getObjectoPracticaControl(): FormArray {
    return this.form.get('objetoPractica') as FormArray;
  }

  private listenForErros() {
    this.bindToValidationErrorsOf('nombrePractica');
    this.bindToValidationErrorsOf('practicaGratuita');
    this.bindToValidationErrorsOf('objetoPractica');
    this.bindToValidationErrorsOf('descripcionDetallada');
    this.bindToValidationErrorsOf('nombreEntidadInstitucion');
  }

  onRegistroHorasSemanalesValid() {
    this.tieneHorasTrabajoSemanal.setValue(true);
  }

  onRegistroHorasSemanalesInvalid() {
    this.tieneHorasTrabajoSemanal.setValue(null);
  }
  get tieneHorasTrabajoSemanal() {
    return this.form.get([
      'tieneHorasTrabajoSemanal'
    ]);
  }
  onRegistroHorasSemanalesTotal(total: any) {
    this.horasTrabajoSemanal.setValue(total);
    this.form.get('horarioLaboral').setValue(this.registroHorario.getRegistroHorario());
    this.form.get('sumHours').setValue(this.registroHorario.totalRegistroHorario);
  }
  get horasTrabajoSemanal() {
    return this.form.get([
      'horasTrabajoSemanal'
    ]);
  }

  private listenForChanges() {
    this.validEvent.subscribe((value => {
      this.execValidations();
      this.form.valid;
    }));
  }
}
