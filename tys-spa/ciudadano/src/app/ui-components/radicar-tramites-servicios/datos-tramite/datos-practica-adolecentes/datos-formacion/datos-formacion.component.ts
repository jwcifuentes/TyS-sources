import {Component, EventEmitter, Injector, Input, OnInit, Output, ViewChild} from '@angular/core';
import {Form, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {FormBaseComponent} from '../../../../../shared/utils/form-base-component';
import {Observable} from 'rxjs/Observable';
import {ConstanteWcService} from '../../../../../services/bussiness/constante-wc.service';
import {DatosDireccionComponent} from '../../../datos-direccion/datos-direccion.component';

@Component({
  selector: 'app-datos-formacion',
  templateUrl: './datos-formacion.component.html',
  styleUrls: ['./datos-formacion.component.css']
})
export class DatosFormacionComponent extends FormBaseComponent implements OnInit {
  @Input() parentForm: FormGroup;
  @ViewChild('datosDireccionComponent') datosDireccionComponent: DatosDireccionComponent;
  @Input() validEvent: Observable<any>;
  tipoEducacionOptions$: Observable<any[]>;
  tipoInstitucionOptions$: Observable<any[]>;
  periodoAcademicoOptions$: Observable<any[]>;
  tipoDocumentoOptions$: Observable<any[]>;
  date1: Date;
  invalidDates: Array<Date>
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
      nombreInstitucionEducativa: [null, Validators.required],
      nit: [null, Validators.required],
      tipoEducacion: [null, Validators.required],
      tipoInstitucion: [null, Validators.required],
      numeroTelefonico: [null, Validators.required],
      primerNombreRepresentante: [null, Validators.required],
      segundoNombreRepresentante: [null],
      primerApellidoRepresentante: [null, Validators.required],
      segundoApellidoRepresentante: [null],
      direcciones: [null, Validators.required],
      nombreProgramaAcademico: [null, Validators.required],
      grado: [null, Validators.required],
      periodoAcademico: [null, Validators.required],
      anio: [null, Validators.required],
      duracionPractica: this.formBuilder.group({
        fechaInicio: [null, Validators.required],
        fechaFin: [null, Validators.required]
      }),
      datosMonitor: this.formBuilder.group({
        primerNombre: [null, Validators.required],
        segundoNombre: [null],
        primerApellido: [null, Validators.required],
        segundoApellido: [null],
        tipoDocumento: [null, Validators.required],
        nroDocumentoIdentidad: [null, Validators.required],
      })
    });
    this.parentForm.addControl('datosFormacionComplementaria', this.form);
  }

  private loadData() {
    this.tipoEducacionOptions$ = this.constanteWcService.listByParentCode('TIPO_EDUCACION');
    this.tipoInstitucionOptions$ = this.constanteWcService.listByParentCode('TIPO_INSTITUCION');
    this.periodoAcademicoOptions$ = this.constanteWcService.listByParentCode('PERIODO_ACADEMICO');
    this.tipoDocumentoOptions$ = this.constanteWcService.listByParentCode('NNA-REPRESENTANTE/TIP-IDENT');
  }

  private listenForErrors() {
    this.bindToValidationErrorsOf([ 'nombreInstitucionEducativa']);
    this.bindToValidationErrorsOf([ 'nit']);
    this.bindToValidationErrorsOf([ 'tipoEducacion']);
    this.bindToValidationErrorsOf([ 'tipoInstitucion']);
    this.bindToValidationErrorsOf([ 'numeroTelefonico']);
    this.bindToValidationErrorsOf([ 'primerNombreRepresentante']);
    this.bindToValidationErrorsOf([ 'primerApellidoRepresentante']);
    this.bindToValidationErrorsOf([ 'direcciones']);
    this.bindToValidationErrorsOf([ 'nombreProgramaAcademico']);
    this.bindToValidationErrorsOf([ 'grado']);
    this.bindToValidationErrorsOf([ 'periodoAcademico']);
    this.bindToValidationErrorsOf([ 'anio']);
    this.bindToValidationErrorsOf(['duracionPractica', 'fechaInicio']);
    this.bindToValidationErrorsOf(['duracionPractica', 'fechaFin']);
  }
  listenForChanges() {
    const fechaInicioControl = this.form.get(['duracionPractica', 'fechaInicio']);
    const fechaFinControl = this.form.get(['duracionPractica', 'fechaFin']);
    fechaFinControl.valueChanges.subscribe((value) => {
      if (value) {
        if (!((<Date>fechaFinControl.value) > (<Date>fechaInicioControl.value))) {
          this.validations['duracionPractica_fechaFin'] = 'La fecha final debe ser mayor';
        }
      }
    });
    this.validEvent.subscribe((value => {
      this.execValidations();
      this.form.valid;
    }));
  }

  onDireccionesValid() {
    this.form.get('direcciones').setValue(this.datosDireccionComponent.contacts);
  }

  onDireccionesInValid() {
    this.form.get('direcciones').setValue(null);
  }
  datosMonitorForm(): FormGroup {
    return <FormGroup> this.form.get('datosMonitor');
  }
}
