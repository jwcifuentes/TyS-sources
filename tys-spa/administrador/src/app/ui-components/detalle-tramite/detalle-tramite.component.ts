import {Component, OnInit} from '@angular/core';
import {DetalleTramiteModel} from 'app/ui-components/detalle-tramite/detalle-tramite.model';
import {ParametrosWcService} from 'app/services/parametros-wc.service';
import {TramitesWcService} from 'app/services/tramites-wc.service';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {AbstractControl, FormArray, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {FlujoAdminTramite} from 'app/constants/flujo-admin-tramite.enum';
import {Tramite} from 'app/domain/tramite';
import {TiposDocumentalesWcService} from 'app/services/tipos-documentales-wc.service';
import {SessionAttribute, SessionHolderService} from 'app/security/session/session-holder.service';
import {TipoDocumentalTramite} from 'app/domain/tipo-documental-tramite';
import {VALIDATION_MESSAGES} from '../../shared/validation-messages';
import {YesNo} from '../../domain/yes-no';
import {Observable} from 'rxjs/Observable';
import {el} from '@angular/platform-browser/testing/src/browser_util';

@Component({
  selector: 'app-detalle-tramite',
  templateUrl: './detalle-tramite.component.html'
})
export class DetalleTramiteComponent implements OnInit {

  model: DetalleTramiteModel;
  tienePactosOptions$: Observable<Array<YesNo>>;
  tieneReglamentoOptions$: Observable<Array<YesNo>>;
  tipoSocietarioSasOptions$: Observable<Array<YesNo>>;
  radicadaPorOptions$: Observable<any[]>;
  justificacionSolicitudOptions$: Observable<any[]>;
  gradoAsociacionOptions$: Observable<any[]>;
  tipoGestionOptions$: Observable<any[]>;
  tipoEntidadOptions$: Observable<any[]>;
  tipoParentescoOptions$: Observable<any[]>;

  tienePactosOptions: Array<YesNo>;
  tieneReglamentoOptions: Array<YesNo>;
  tipoSocietarioSasOptions: Array<YesNo>;
  justificacionSolicitudOptions: Array<any>;
  gradoAsociacionOptions: Array<any>;
  radicadaPorOptions: Array<any>;
  tipoGestionOptions: Array<any>;
  tipoEntidadOptions: Array<any>;
  tipoParentescoOptions: Array<any>;

  get form(): FormGroup {
    return this.model.complexForm;
  };

  validations: any = {};

  constructor(private _constanteService: ParametrosWcService,
              private _tiposDocService: TiposDocumentalesWcService,
              private _router: Router,
              private activatedRoute: ActivatedRoute,
              private fb: FormBuilder,
              private _tramitesService: TramitesWcService,
              public constanteWcService: ParametrosWcService,
              private _session: SessionHolderService) {

    this.model = new DetalleTramiteModel();

    this.model.setupForm(this.fb);

    this.activatedRoute.params.subscribe((params: Params) => {
      const tramiteId = params['id'];

      this.loadTramite(tramiteId);

      this._tiposDocService
        .listarNew(tramiteId)
        .subscribe(
          data => (this.model.tiposDocumentalesTramite = data),
          error =>
            this.model.addError(
              error.descripcion == null ? error.content : error.descripcion
            )
        );
    });
  }

  loadTramite(tramiteId) {
    this._tramitesService
      .getTramite(tramiteId)
      .subscribe(
        data => this.iniciarFormulario(data),
        error => {
          this._session.save(SessionAttribute.INSTANT_MESSAGE, (error.descripcion == null ? error.content : error.descripcion));
          this._router.navigate(['/admin_tramites/error']);
        }
      );
  }

  ngOnInit() {
    this._constanteService
      .listParametros('REG_ASI_TS')
      .subscribe(
        data => {
          this.model.initReglaAsignaciones(data);
          this.iniciarListaReglas();
        },
        error =>
          this.model.addError(
            error.descripcion == null ? error.content : error.descripcion
          )
      );

    this._constanteService
      .listParametros('UNI_TIM_TS')
      .subscribe(
        data => this.model.initUnidadTiempo(data),
        error =>
          this.model.addError(
            error.descripcion == null ? error.content : error.descripcion
          )
      );

    this._constanteService
      .listParametros('DOC_EMI_TS')
      .subscribe(
        data => this.model.initTipoDocEmite(data),
        error =>
          this.model.addError(
            error.descripcion == null ? error.content : error.descripcion
          )
      );

    this._tiposDocService
      .listTiposDocumentales()
      .subscribe(
        data => this.model.initTiposDocumentales(data),
        error =>
          this.model.addError(
            error.descripcion == null ? error.content : error.descripcion
          )
      );
    this.tipoGestionOptions$ = this.constanteWcService.listParametros('TIP_GES_EMP_TS');
    this.tienePactosOptions$ = this.constanteWcService.listYesNoConstants();
    this.tieneReglamentoOptions$ = this.constanteWcService.listYesNoConstants();
    this.tipoSocietarioSasOptions$ = this.constanteWcService.listYesNoConstants();
    this.radicadaPorOptions$ = this.constanteWcService.listParametros('RAD_POR_TS');
    this.justificacionSolicitudOptions$ = this.constanteWcService.listParametros('JUS_SOLI_TS');
    this.gradoAsociacionOptions$ = this.constanteWcService.listParametros('GRA_ASO_TS');
    this.tipoEntidadOptions$ = this.constanteWcService.listParametros('TIPO_ENTIDAD');
    this.tipoParentescoOptions$ = this.constanteWcService.listParametros('PARENTESCO');
    this.initLists();
  }

  initLists() {
    this.tienePactosOptions$.subscribe(values => {
      this.tienePactosOptions = values;
    });
    this.tieneReglamentoOptions$.subscribe(values => {
      this.tieneReglamentoOptions = values;
    });
    this.tipoSocietarioSasOptions$.subscribe(values => {
      this.tipoSocietarioSasOptions = values;
    });
    this.radicadaPorOptions$.subscribe(values => {
      this.radicadaPorOptions = values;
    });
    this.justificacionSolicitudOptions$.subscribe(values => {
      this.justificacionSolicitudOptions = values;
    });
    this.gradoAsociacionOptions$.subscribe(values => {
      this.gradoAsociacionOptions = values;
    });
    this.tipoGestionOptions$.subscribe(values => {
      this.tipoGestionOptions = values;
    });
    this.tipoEntidadOptions$.subscribe(values => {
      this.tipoEntidadOptions = values;
    });
    this.tipoParentescoOptions$.subscribe(values => {
      this.tipoParentescoOptions = values;
    });
  }

  public iniciarListaReglas() {
    if (this.model.tramite.id) {
      this.model.listReglaAsignacionItem = this.model.listReglaAsignacionItem.filter(element => {
        return this.model.tramite.fechaCambio ? true : element.value === 10654;
      });
      if (!this.model.tramite.fechaCambio) {
        this.model.tramite.idReglaAsignacion = 10654;
      }
    }
  }

  public iniciarFormulario(tramite: Tramite): void {
    this.model.tramite = {
      id: tramite.id,
      idTipoDocEmitido: (tramite.idTipoDocEmitido) ? tramite.idTipoDocEmitido : null,
      idReglaAsignacion: (tramite.idReglaAsignacion) ? tramite.idReglaAsignacion : null,
      fechaCambio: (tramite.fechaCambio) ? tramite.fechaCambio : null,
      estado: (tramite.estado) ? tramite.estado : null,
      esTramiteSoloRecepcion: (tramite.esTramiteSoloRecepcion) ? tramite.esTramiteSoloRecepcion : null,
      fechaCreacion: (tramite.fechaCreacion) ? tramite.fechaCreacion : null,
      idUnidadTiempo: (tramite.idUnidadTiempo) ? tramite.idUnidadTiempo : null,
      nmDireccionesPermitidas: (tramite.nmDireccionesPermitidas) ? tramite.nmDireccionesPermitidas : null,
      nombre: (tramite.nombre) ? tramite.nombre : null,
      requiereConceptoSubInsp: (tramite.requiereConceptoSubInsp) ? tramite.requiereConceptoSubInsp : null,
      tiempoGestionTramite: (tramite.tiempoGestionTramite) ? tramite.tiempoGestionTramite : null,
      tieneSustanciadores: (tramite.tieneSustanciadores) ? tramite.tieneSustanciadores : null,
      usuariaCambio: (tramite.usuariaCambio) ? tramite.usuariaCambio : null,
      usuarioCreacion: (tramite.usuarioCreacion) ? tramite.usuarioCreacion : null,
      permiteActualizacion: (tramite.permiteActualizacion) ? tramite.permiteActualizacion : null,
      descripcion: (tramite.descripcion) ? tramite.descripcion : null,
      urlTramites: tramite.urlTramites
    };
    this.iniciarListaReglas();
    this.model.setupForm(this.fb);
  }

  cleanTramite() {
    this.model.tramite.idTipoDocEmitido = (this.model.tramite.idTipoDocEmitido !== 0) ? this.model.tramite.idTipoDocEmitido : null;
  }

  public cancelar(): void {
    this._router.navigate(['/admin_tramites']);
  }

  public enviar(): void {
    if (this.validarFormulario()) {
      this.model.tramite.idTipoDocEmitido = this.model.getFormValue('tipoDocumento');
      this.model.tramite.idReglaAsignacion = this.model.getFormValue('reglaAsignacion');
      this.model.tramite.nmDireccionesPermitidas = this.model.getFormValue('numeroDirecciones');
      this.model.tramite.idUnidadTiempo = this.model.getFormValue('unidadTiempo');
      this.model.tramite.tiempoGestionTramite = this.model.getFormValue('tiempoGestion');
      this.model.tramite.tieneSustanciadores = this.model.getFormValue('tieneSustanciadores');
      this.model.tramite.requiereConceptoSubInsp = this.model.getFormValue('subdireccionInspeccion');
      this.model.tramite.esTramiteSoloRecepcion = this.model.getFormValue('soloRecepcion');
      this.model.tramite.estado = this.model.getFormValue('estado');
      this.model.tramite.descripcion = this.model.getFormValue('descripcionTramite');
      this.model.tramite.usuariaCambio = this._session.retrieve(SessionAttribute.LOGIN);
      this.model.tramite.urlTramites = this.model.getFormValue('urlTramites');
      this._tramitesService
        .crear(this.model.tramite, FlujoAdminTramite.AJUSTE_DETALLES)
        .subscribe(
          data => {
            this.loadTramite(this.model.tramite.id);
            this.model.addInfo('El trámite se ha actualizado con éxito.');
          },
          error =>
            this.model.addError(
              error.descripcion == null ? error.content : error.descripcion
            )
        );
    } else {
      this.form.get('descripcionTramite').markAsTouched();
      this.form.get('tipoDocumento').markAsTouched();
      this.form.get('reglaAsignacion').markAsTouched();
      this.form.get('numeroDirecciones').markAsTouched();
      this.form.get('unidadTiempo').markAsTouched();
      this.form.get('tiempoGestion').markAsTouched();
    }
  }

  public validarFormulario(): boolean {
    let valido = true;
    let mensaje = '';

    if (!this.model.complexForm.controls['descripcionTramite'].valid) {
      mensaje = 'La \'DESCRIPCIÓN DEL TRÁMITE QUE SE EMITE\' es inválida';
      valido = false;
    }

    if (!this.model.complexForm.controls['tipoDocumento'].valid) {
      mensaje = 'El \'TIPO DOCUMENTO QUE SE EMITE\' es inválido';
      valido = false;
    }

    if (!this.model.complexForm.controls['reglaAsignacion'].valid) {
      mensaje = mensaje + '<br/>La \'REGLA DE ASIGNACION - ASIGNADOR\' es inválida';
      valido = false;
    }

    if (!this.model.complexForm.controls['numeroDirecciones'].valid) {
      mensaje = mensaje + '<br/>El \'NUMERO DIRECCIONES PERMITIDAS\' es inválido';
      valido = false;
    }

    if (!this.model.complexForm.controls['tiempoGestion'].valid) {
      mensaje = mensaje + '<br/>El \'TIEMPO GESTION DEL TRAMITEO\' es inválido';
      valido = false;
    }

    if (!this.model.complexForm.controls['unidadTiempo'].valid) {
      mensaje = mensaje + '<br/>La \'UNIDAD DE TIEMPO\' no es inválida';
      valido = false;
    }
    const urlsValid = this.validateUrlTramites();
    if (urlsValid) {
      mensaje = mensaje + '<br/>' + urlsValid;
      valido = false;
    }

    if (!valido) {
      this.model.addError(mensaje);
    }

    return valido;
  }

  private validateUrlTramites() {
    let mensage = '';
    const urlsTramites: any[] = this.model.complexForm.get('urlTramites').value;
    if (urlsTramites[0].esVisible && urlsTramites[1].esVisible) {
      mensage = 'Solo una Url puede ser visible';
    } else if (!urlsTramites[0].esVisible && !urlsTramites[1].esVisible) {
      mensage = 'Una Url debe ser visible';
    } else {
      urlsTramites.forEach((urlTramite, index) => {
        if (!urlTramite.url && urlTramite.esVisible) {
          mensage = mensage + 'La url' + (index + 1) + ' es visible pero no tiene un link asignado\n';
        }
      });
    }
    return mensage;
  }

  public verDetalle(tipo: TipoDocumentalTramite): void {
    console.log(tipo);
    this.model.complexForm.patchValue({
      descripcion: tipo.descripcion,
      tpgDocumento: tipo.idTipoDocumental,
      esRequerido: tipo.esRequerido,
      estadoTpDoc: tipo.estado,
      tienePactos: this.tienePactosOptions.find((value) => value.value === tipo.indTienePactosColectivos),
      tieneReglamento: this.tieneReglamentoOptions.find((value) => value.value === tipo.indTieneReglamentoTrabajo),
      tipoSocietarioSas: this.tipoSocietarioSasOptions.find((value) => value.value === tipo.indTieneAsociacionSAS),
      radicadaPor: this.radicadaPorOptions.find((value) => value.id === tipo.idSolicitadoPor),
      justificacionSolicitud: this.justificacionSolicitudOptions.find((value) => value.id === tipo.idJustSolicitud),
      gradoAsociacion: this.gradoAsociacionOptions.find((value) => value.id === tipo.idGradoAsociacion),
      tipoGestion: this.tipoGestionOptions.find((value) => value.id === tipo.idtipoGestion),
      tipoEntidad: this.tipoEntidadOptions.find((value) => value.id === tipo.idTipoEntidad),
      tipoParentesco: this.tipoParentescoOptions.find((value) => value.id === tipo.idTipoParentesco)
    });

    this.model.tipoDocumentalTramite.id = tipo.id;
  }

  isDocumentFormValid(): boolean {
    return this.model.complexForm.get('tpgDocumento').valid && this.model.complexForm.get('descripcion').valid &&
      this.model.complexForm.get('gradoAsociacion').valid && this.model.complexForm.get('tienePactos').valid &&
      this.model.complexForm.get('tieneReglamento').valid && this.model.complexForm.get('tipoSocietarioSas').valid &&
      this.model.complexForm.get('radicadaPor').valid && this.model.complexForm.get('justificacionSolicitud').valid;
  }

  public agregar(): void {
    if (this.isDocumentFormValid()) {
      console.log(this.model.complexForm.get('justificacionSolicitud'));
      this.model.tipoDocumentalTramite.idTipoDocumental = this.model.complexForm.controls[
        'tpgDocumento'
        ].value;
      this.model.tipoDocumentalTramite.esRequerido = this.model.complexForm.controls['esRequerido'].value || false;
      this.model.tipoDocumentalTramite.descripcion = this.model.complexForm.controls['descripcion'].value;
      this.model.tipoDocumentalTramite.estado = this.model.complexForm.controls['estadoTpDoc'].value || false;
      this.model.tipoDocumentalTramite.idTramite = this.model.tramite.id;
      this.model.tipoDocumentalTramite.idGradoAsociacion = this.model.complexForm.controls['gradoAsociacion'].value
        ? this.model.complexForm.controls['gradoAsociacion'].value.id : null;
      this.model.tipoDocumentalTramite.indTienePactosColectivos = this.model.complexForm.controls['tienePactos'].value
        ? this.model.complexForm.controls['tienePactos'].value.value : null;
      this.model.tipoDocumentalTramite.indTieneReglamentoTrabajo = this.model.complexForm.controls['tieneReglamento'].value
        ? this.model.complexForm.controls['tieneReglamento'].value.value : null;
      this.model.tipoDocumentalTramite.indTieneAsociacionSAS = this.model.complexForm.controls['tipoSocietarioSas'].value
        ? this.model.complexForm.controls['tipoSocietarioSas'].value.value : null;
      this.model.tipoDocumentalTramite.idSolicitadoPor = this.model.complexForm.controls['radicadaPor'].value
        ? this.model.complexForm.controls['radicadaPor'].value.id : null;
      this.model.tipoDocumentalTramite.idJustSolicitud = this.model.complexForm.controls['justificacionSolicitud'].value
        ? this.model.complexForm.controls['justificacionSolicitud'].value.id : null;

      this.model.tipoDocumentalTramite.idTipoEntidad = this.model.complexForm.controls['tipoEntidad'].value
        ? this.model.complexForm.controls['tipoEntidad'].value.id : null;

      this.model.tipoDocumentalTramite.idTipoParentesco = this.model.complexForm.controls['tipoParentesco'].value
        ? this.model.complexForm.controls['tipoParentesco'].value.id : null;

      const tipoGestion = this.model.complexForm.controls['tipoGestion'].value;
      this.model.tipoDocumentalTramite.idtipoGestion = tipoGestion && tipoGestion.id;
      this.model.tipoDocumentalTramite.nombreTipoGestion = tipoGestion && tipoGestion.nombre;

      this.model.complexForm.controls['tipoGestion'].reset();
      this.model.complexForm.controls['tpgDocumento'].reset();
      this.model.complexForm.controls['descripcion'].reset();
      this.model.complexForm.controls['esRequerido'].reset();
      this.model.complexForm.controls['estadoTpDoc'].reset();
      this.model.complexForm.controls['gradoAsociacion'].reset();
      this.model.complexForm.controls['tienePactos'].reset();
      this.model.complexForm.controls['tieneReglamento'].reset();
      this.model.complexForm.controls['tipoSocietarioSas'].reset();
      this.model.complexForm.controls['radicadaPor'].reset();
      this.model.complexForm.controls['justificacionSolicitud'].reset();
      this.model.complexForm.controls['tipoEntidad'].reset();
      this.model.complexForm.controls['tipoParentesco'].reset();
      console.log(this.model.tipoDocumentalTramite);
      if (this.model.tipoDocumentalTramite.id == null) {
        console.log('CREATE !!!');
        this.model.tipoDocumentalTramite.usuarioCreacion = this._session.retrieve(
          SessionAttribute.LOGIN
        );
        this._tiposDocService
          .crear(
            this.model.tipoDocumentalTramite,
            FlujoAdminTramite.CREACION_BASE
          )
          .subscribe(
            data => this.manejarFlujoExito(),
            error =>
              this.model.addError(
                error.descripcion == null ? error.content : error.descripcion
              )
          );
      } else {
        console.log('UPDATE !!!');
        this.model.tipoDocumentalTramite.usuariaCambio = this._session.retrieve(
          SessionAttribute.LOGIN
        );
        this._tiposDocService
          .crear(
            this.model.tipoDocumentalTramite,
            FlujoAdminTramite.AJUSTE_DETALLES
          )
          .subscribe(
            data => this.manejarFlujoExito(),
            error =>
              this.model.addError(
                error.descripcion == null ? error.content : error.descripcion
              )
          );
      }

      this.model.tipoDocumentalTramite.id = null;
    } else {
      this.model.complexForm.get('tpgDocumento').markAsTouched();
      this.model.complexForm.get('descripcion').markAsTouched();
      this.model.addError('Campos requeridos en el formulario');
    }
  }

  public cancelarTipoDocumentalTramite() {
    this.model.tipoDocumentalTramite.id = null;
    this.model.complexForm.controls['tpgDocumento'].reset();
    this.model.complexForm.controls['descripcion'].reset();
    this.model.complexForm.controls['esRequerido'].reset();
    this.model.complexForm.controls['estadoTpDoc'].reset();
  }

  private manejarFlujoExito(): void {
    this.model.addInfo(
      'La tipología documental se adicionado al trámite con éxito'
    );
    this._tiposDocService
      .listarNew(this.model.tramite.id)
      .subscribe(
        data => (this.model.tiposDocumentalesTramite = data),
        error => this.model.addError(error.descripcion)
      );
  }

  listenForBlurEvents(control: string | Array<string>) {
    const stringControl = control.toString().replace(/,/g, '_');
    const ac = this.model.complexForm.get(control);

    if (ac.touched && ac.invalid) {
      const error_keys = Object.keys(ac.errors);
      const last_error_key = error_keys[error_keys.length - 1];
      this.validations[stringControl] = VALIDATION_MESSAGES[last_error_key];
    }
  }

  getErrorMessage(control: string) {
    const error_keys = Object.keys(this.model.complexForm.get(control).errors);
    const last_error_key = error_keys[error_keys.length - 1];
    return VALIDATION_MESSAGES[last_error_key];
  }

  getErrorMessageAbsControl(control: AbstractControl) {
    const error_keys = Object.keys(control.errors);
    const last_error_key = error_keys[error_keys.length - 1];
    return VALIDATION_MESSAGES[last_error_key];
  }

  isInvalid(control: string) {
    const ac = this.model.complexForm.get(control);
    return (ac.touched || ac.dirty) && ac.invalid;
  }

  isInvalidAbsControl(control: AbstractControl) {
    return (control.touched || control.dirty) && control.invalid;
  }

  getControlAsArrayForm(control: string): FormArray {
    return this.model.complexForm.get(control) as FormArray;
  }

  cambiarEstado() {
    this._tramitesService
      .cambiarParametrizacionTramite(this.model.tramite.id)
      .subscribe(
        data => this.model.tramite.permiteActualizacion = !this.model.tramite.permiteActualizacion
      );
    console.log('Estado cambiado');
  }
}
