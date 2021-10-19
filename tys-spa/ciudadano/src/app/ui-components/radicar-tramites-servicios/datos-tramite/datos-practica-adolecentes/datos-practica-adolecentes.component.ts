import {Component, EventEmitter, Injector, Input, OnInit, Output} from '@angular/core';
import { FormBuilder, FormGroup, Validators} from '@angular/forms';
import {FormBaseComponent} from '../../../../shared/utils/form-base-component';
import {Observable} from 'rxjs/Observable';
import {ConstanteWcService} from '../../../../services/bussiness/constante-wc.service';
import {el} from '@angular/platform-browser/testing/src/browser_util';

@Component({
  selector: 'app-datos-practica-adolecentes',
  templateUrl: './datos-practica-adolecentes.component.html',
})
export class DatosPracticaAdolecentesComponent extends FormBaseComponent implements OnInit {
  private edades: Array<number> = [15, 16, 17];
  private tiposRegimen: Array<string> = ['CONTRIBUTIVO', 'SUBSIDIADO'];
  @Input()  parentForm: FormGroup;
  @Input()  tipoDocumentoOptions$: Observable<any[]>;
  @Input() edadOptions$: Observable<any[]>;
  @Input() validEvent: Observable<any>;
  tipoRegimenOptions$: Observable<any[]>;
  showNombreMandatory = false;
  @Output() onClickVisualizarPdf = new EventEmitter();
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
      datosBasicosAdolecente: this.formBuilder.group({
        nombreCompleto: [null],
        primerNombre: [null, Validators.required],
        segundoNombre: [null],
        primerApellido: [null, Validators.required],
        segundoApellido: [null],
        tipoDocumento: [null, Validators.required],
        nroDocumentoIdentidad: [null, Validators.required],
        edad: [null, Validators.required],
        datosAfiliacionSalud: this.formBuilder.group({
          tipoRegimen: [null, Validators.required],
          nombre: [null, Validators.required]
        })
      }),
    });
    this.parentForm.addControl('datosPracticaAdolecente', this.form);
  }

  private loadData() {
    if (!this.tipoDocumentoOptions$) {
      this.tipoDocumentoOptions$ = this.constanteWcService.listByParentCode('TIP-IDENT');
    }
    this.tipoDocumentoOptions$ = this.tipoDocumentoOptions$.map((tiposDocumento) => {
      return tiposDocumento.filter((tipoDocumento) => <string>tipoDocumento.codigo.match('TI|PA'));
    });
    if (!this.edadOptions$) {
      this.edadOptions$ = this.constanteWcService.listByParentCode('EDAD_TS');
    }
    this.edadOptions$ = this.edadOptions$.map((edades) => {
      return edades.filter((edad) => this.edades.indexOf(parseInt(edad.nombre)) !== -1);
    });
    this.tipoRegimenOptions$ = this.constanteWcService.listByParentCode('TIPO_REGIMEN_SALUD');
  }

  private listenForErrors() {
    this.bindToValidationErrorsOf([ 'datosBasicosAdolecente', 'primerNombre']);
    this.bindToValidationErrorsOf([ 'datosBasicosAdolecente', 'segundoNombre']);
    this.bindToValidationErrorsOf([ 'datosBasicosAdolecente', 'primerNombre']);
    this.bindToValidationErrorsOf([ 'datosBasicosAdolecente', 'primerApellido']);
    this.bindToValidationErrorsOf([ 'datosBasicosAdolecente', 'segundoApellido']);
    this.bindToValidationErrorsOf([ 'datosBasicosAdolecente', 'tipoDocumento']);
    this.bindToValidationErrorsOf([ 'datosBasicosAdolecente', 'nroDocumentoIdentidad']);
    this.bindToValidationErrorsOf([ 'datosBasicosAdolecente', 'edad']);
    this.bindToValidationErrorsOf([ 'datosBasicosAdolecente', 'datosAfiliacionSalud', 'tipoRegimen']);
    this.bindToValidationErrorsOf([ 'datosBasicosAdolecente', 'datosAfiliacionSalud', 'nombre']);
  }

  private listenForChanges() {
    const tipoRegimenControl = this.form.get(['datosBasicosAdolecente', 'datosAfiliacionSalud', 'tipoRegimen']);
    const nombreRegimenControl = this.form.get(['datosBasicosAdolecente', 'datosAfiliacionSalud', 'nombre']);
    tipoRegimenControl.valueChanges.subscribe((value) => {
      if (value) {
        if (this.tiposRegimen.indexOf(value.nombre) !== -1) {
          nombreRegimenControl.setValidators([Validators.required]);
          this.showNombreMandatory = true;
        } else {
          nombreRegimenControl.clearValidators();
          nombreRegimenControl.setValue(null);
          this.showNombreMandatory = false;
        }
      }
    });
    this.validEvent.subscribe((value => {
      this.execValidations();
      this.form.valid;
    }));
  }
  getParentForm(): FormGroup {
    return <FormGroup> this.form.get('datosBasicosAdolecente');
  }
}
