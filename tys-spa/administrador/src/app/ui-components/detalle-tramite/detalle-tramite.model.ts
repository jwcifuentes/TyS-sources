import {Parametro} from 'app/domain/parametro';
import {Tramite} from 'app/domain/tramite';
import {MenuItem, SelectItem} from 'primeng/primeng';
import {CommonModel} from 'app/util/common.model';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {TipoDocumental} from 'app/domain/tipo-documental';
import {TipoDocumentalTramite} from 'app/domain/tipo-documental-tramite';

export class DetalleTramiteModel extends CommonModel {

  complexForm: FormGroup;
  listNumDirecciones: SelectItem[];
  listReglaAsignacionItem: SelectItem[] = [];
  listUnidadTiempoItem: SelectItem[];
  listTipoDocEmiteItem: SelectItem[];
  listTipoDocumental: SelectItem[];
  home: MenuItem;
  tramite: Tramite = new Tramite();
  tipoDocumentalTramite: TipoDocumentalTramite = new TipoDocumentalTramite();
  tiposDocumentalesTramite: Array<TipoDocumentalTramite>;

  constructor() {
    super();
    this.listNumDirecciones = [];
    this.listNumDirecciones.push({label: '1', value: '1'});
    this.listNumDirecciones.push({label: 'N*', value: 'N'});
  }

  public cleanInputs(): void {
    this.complexForm.reset();
  }

  public initReglaAsignaciones(parametros: Array<Parametro>): void {
    this.listReglaAsignacionItem = [];
    parametros.forEach(parametro =>
      this.listReglaAsignacionItem.push({
        label: parametro.nombre,
        value: parametro.id
      })
    );
  }

  public initUnidadTiempo(parametros: Array<Parametro>): void {
    this.listUnidadTiempoItem = [];
    parametros.forEach(parametro =>
      this.listUnidadTiempoItem.push({
        label: parametro.nombre,
        value: parametro.id
      })
    );
  }

  public initTipoDocEmite(parametros: Array<Parametro>): void {
    this.listTipoDocEmiteItem = [];
    parametros.forEach(parametro =>
      this.listTipoDocEmiteItem.push({
        label: parametro.nombre,
        value: parametro.id
      })
    );
  }

  public initTiposDocumentales(tiposDocumentales: Array<TipoDocumental>): void {
    this.listTipoDocumental = [];
    tiposDocumentales.forEach((tipoDocumental) =>
      this.listTipoDocumental.push({
        label: tipoDocumental.nombre,
        value: tipoDocumental.id
      })
    );
  }

  // ----------------

  public setupForm(fb: FormBuilder): void {
    const tramite: Tramite = this.tramite;
    const tipoDocumentalTremite = this.tipoDocumentalTramite;

    this.complexForm = fb.group({
      'tipoDocumento': [
        tramite.idTipoDocEmitido,
        Validators.compose([Validators.required, Validators.nullValidator])
      ],
      reglaAsignacion: [
        tramite.idReglaAsignacion,
        Validators.compose([Validators.required, Validators.nullValidator])
      ],
      numeroDirecciones: [
        tramite.nmDireccionesPermitidas,
        Validators.compose([Validators.required, Validators.nullValidator])
      ],
      unidadTiempo: [
        tramite.idUnidadTiempo,
        Validators.compose([
          Validators.required
        ])
      ],
      tiempoGestion: [
        tramite.tiempoGestionTramite,
        Validators.compose([
          Validators.required,
          Validators.minLength(1),
          Validators.maxLength(3)
        ])
      ],
      tieneSustanciadores: [tramite.tieneSustanciadores],
      subdireccionInspeccion: [tramite.requiereConceptoSubInsp],
      soloRecepcion: [tramite.esTramiteSoloRecepcion],
      estado: [tramite.estado],
      tpgDocumento: [
        tipoDocumentalTremite.idTipoDocumental,
        Validators.compose([
          Validators.required,
          Validators.minLength(1),
          Validators.maxLength(3)
        ])
      ],
      esRequerido: [tipoDocumentalTremite.esRequerido],
      descripcionTramite: [
        tramite.descripcion,
        Validators.compose([
          Validators.required,
          this.noWhitespaceValidator
        ])
      ],
      descripcion: [
        tipoDocumentalTremite.descripcion,
        Validators.compose([
          Validators.required,
          this.noWhitespaceValidator,
          Validators.minLength(1),
          Validators.maxLength(900)
        ])
      ],
      estadoTpDoc: [tipoDocumentalTremite.estado],
      tienePactos: [null],
      tieneReglamento: [null],
      tipoSocietarioSas: [null],
      radicadaPor: [null],
      justificacionSolicitud: [null],
      gradoAsociacion: [null],
      tipoGestion: [null],
      urlTramites: fb.array(this.getUrlsTramiteAsControls(fb)),
      tipoEntidad: [null],
      tipoParentesco: [null]
    });

    this.onChangeSoloRecepcion(tramite.esTramiteSoloRecepcion);
  }

  private getUrlsTramiteAsControls(fb: FormBuilder): any[] {
    const tramite: Tramite = this.tramite;
    if (!tramite) {
      return [];
    }
    if (!tramite.urlTramites) {
      tramite.urlTramites = [{idTramite: tramite.id}, {idTramite: tramite.id}];
    }else if (tramite.urlTramites.length === 0) {
      tramite.urlTramites.push({idTramite: tramite.id});
      tramite.urlTramites.push({idTramite: tramite.id});
    } else if (tramite.urlTramites.length === 1) {
      tramite.urlTramites.push({idTramite: tramite.id});
    }
    return [
      fb.group({
        idUrl: [tramite.urlTramites[0].idUrl],
        idTramite: [tramite.urlTramites[0].idTramite],
        esVisible: [tramite.urlTramites[0].esVisible ? tramite.urlTramites[0].esVisible : false],
        url: [tramite.urlTramites[0].url]
      }),
      fb.group({
        idUrl: [tramite.urlTramites[1].idUrl],
        idTramite: [tramite.urlTramites[1].idTramite],
        esVisible: [tramite.urlTramites[1].esVisible ? tramite.urlTramites[1].esVisible : false],
        url: [tramite.urlTramites[1].url]
      })
    ];
  }

  onChangeSoloRecepcion(value) {
    if (!value) {
      this.complexForm.get('tiempoGestion').setValidators(Validators.compose([
        Validators.required,
        Validators.minLength(1),
        Validators.maxLength(3)
      ]));
      this.complexForm.get('unidadTiempo').setValidators(Validators.compose([
        Validators.required
      ]));
    } else {
      this.complexForm.get('tiempoGestion').setValidators(Validators.compose([
        Validators.minLength(1),
        Validators.maxLength(3)
      ]));
      this.complexForm.get('unidadTiempo').setValidators(null);
    }
    this.complexForm.get('unidadTiempo').updateValueAndValidity();
    this.complexForm.get('tiempoGestion').updateValueAndValidity();
  }

  public getFormValue(key: string): any {
    return this.complexForm.controls[key].value;
  }

  public noWhitespaceValidator(control: FormControl) {
    const isWhitespace = (control.value && control.value.length > 0) && (control.value || '').trim().length === 0;
    const isValid = !isWhitespace;
    return isValid ? null : {'whitespace': true};
  }
}
