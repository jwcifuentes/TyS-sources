import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {Tramite} from '../../domain/tramite';
import {TramiteWcService} from '../../services/bussiness/tramite-wc.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {VALIDATION_MESSAGES} from '../../shared/validation-messages';
import {ActivatedRoute, Router} from '@angular/router';
import {CommonModel} from '../radicar-tramites-servicios/util/common.model';
import { EstadosNinosNinas } from 'app/constants/estados-ninos-ninas.enum';
import { TranslateService } from '@ngx-translate/core';
import {DocumentoTramiteActualizar} from '../../domain/DocumentoTramiteActualizar';

@Component({
  selector: 'app-consultar-tramites',
  templateUrl: './consultar-tramites.component.html',
  styleUrls: ['./consultar-tramites.component.css']
})
export class ConsultarTramitesComponent extends CommonModel implements OnInit {

  tramite: Tramite;

  form: FormGroup;

  validations: any = {};


  estadosNinosNinas = EstadosNinosNinas;
  comunicacionesIntermedias :  Array<DocumentoTramiteActualizar>;
  documento:   DocumentoTramiteActualizar;
  showPopupComunicaciones:boolean=false;

  constructor(public tramiteWcService: TramiteWcService,
              public router: Router,
              private route: ActivatedRoute,
              public formBuilder: FormBuilder,
              protected _translator: TranslateService) {
    super(_translator);
    this.initForm();
  }

  ngOnInit() {
    this.listenForErrors();
    this.route
      .queryParams
      .subscribe(params => { // escucha la respuesta de un servicio.
        if (Object.keys(params).length > 0) {
          this.form.patchValue(params);
          this.reloadTramites();
        }
      });
  }

  loadTramite() {
    this.tramiteWcService.consultarTramitesRadicados(this.form.value).subscribe(
      data => this.tramite = data,
      error => {
        if (error.status === 400) {
          this.addWarning('No se encontraron resultados');
        }
      }
  );
  }

  // 13EE2017410600000000495
  // SGDVACMK
  initForm() {
    this.form = this.formBuilder.group({
      numeroRadicado: [null, Validators.required],
      codigoVerificacion: [null, Validators.required]
    });
  }

  listardocumentos() {
    let datos;
    this.tramiteWcService.obtenerDatosDocumento(this.form.controls['numeroRadicado'].value).subscribe(
      (data: DocumentoTramiteActualizar) => {
         datos = data;
        this.showcomunicaciones(datos);
      },
      error => {
        if (error.status === 400) {
          this.addWarning('No se encontraron resultados');
        }
      }
    );
  }

  showDocuemento(documento, i) {
      this.tramiteWcService.obtenerByteDocumentosTramite(documento.idFilenet).subscribe(value => {
        this.visualizarDocumentos(value);
      });
  }

  showcomunicaciones(datos){
    this.comunicacionesIntermedias = datos;
    this.showPopupComunicaciones = true;
  }



  reloadTramites(){
    if (this.form.valid) {
      this.loadTramite();
    } else {
      this.form.get('numeroRadicado').markAsTouched();
      this.form.get('codigoVerificacion').markAsTouched();
      this.listenForBlurEvents('numeroRadicado');
      this.listenForBlurEvents('codigoVerificacion');
      this.addWarning('Los campos del formulario son obligatorios');
    }
  }

  listenForBlurEvents(control: string | Array<string>) {
    const stringControl = control.toString().replace(/,/g, '_');
    const ac = this.form.get(control);
    ac.setValue(ac.value && ac.value.trim());
    if (ac.touched && ac.invalid) {
      const error_keys = Object.keys(ac.errors);
      const last_error_key = error_keys[error_keys.length - 1];
      this.validations[stringControl] = VALIDATION_MESSAGES[last_error_key];
    }
  }

  bindToValidationErrorsOf(control: string | Array<string>) {
    const ac = this.form.get(control);
    const stringControl = control.toString().replace(/,/g, '_');
    ac.valueChanges.subscribe(value => {
      if ((ac.touched || ac.dirty) && ac.errors) {
        const error_keys = Object.keys(ac.errors);
        const last_error_key = error_keys[error_keys.length - 1];
        this.validations[stringControl] = VALIDATION_MESSAGES[last_error_key];
      } else {
        delete this.validations[stringControl];
      }
    });
  }

  listenForErrors() {
    this.bindToValidationErrorsOf('numeroRadicado');
    this.bindToValidationErrorsOf('codigoVerificacion');
  }

  backToModuleSelection() {
    this.router.navigate(['seleccion_modulos']);
  }

  goActualizarTramite(tramite) {
    this.router.navigate(['actualizar_tramite', tramite.numeroRadicado, this.form.get('codigoVerificacion').value]);
  }

  documentoEsVisible(): boolean {
    const aEstadosVer = [10579, 10580, 10581, 10582];
    return this.tramite.idFilenetDocProducido !== null
      && aEstadosVer.indexOf(this.tramite.idEstadoTramite) > -1;
  }

  tramiteEsActualizable(): boolean {
    const aEstadosActualizar = [10575, 10576, 10577];
    return this.tramite.indRequiereActualizacion === true
      && aEstadosActualizar.indexOf(this.tramite.idEstadoTramite) > -1;
  }

  showProducedDocument(fileNetId: string) {
    this.tramiteWcService.obtenerByteDocumentosTramite(fileNetId).subscribe(value => {
      this.visualizarDocumentos(value);
    });
  }


  visualizarDocumentos(file) {
    if (file === undefined || file === null) {
      alert('Failed to open PDF.');
    } else { // Directly use base 64 encoded data for rest browsers (not IE)
      //let repair = "data:application/pdf;base64,"+file.base64 SI EL BASE64 VIENE SIN FORMATO PDF
      const downloadLink = document.createElement('a');
      downloadLink.target = '_blank';
      downloadLink.download = file.nombreDocumento;

      // convert downloaded data to a Blob
      // const blob = new Blob([file], {type: 'application/pdf'});

      // create an object URL from the Blob
      const downloadUrl = file.base64;

      // set object URL as the anchor's href
      downloadLink.href = downloadUrl;

      // append the anchor to document body
      document.body.appendChild(downloadLink);

      // fire a click event on the anchor
      downloadLink.click();

      // cleanup: remove element and revoke object URL
      document.body.removeChild(downloadLink);
      URL.revokeObjectURL(downloadUrl);
    }
  }

  onBlur(target) {
    const control = this.form.get(target);
    control.setValue(control.value && control.value.trim());
  }

  soporteEsVisible() {
    const e = this.tramite.indEstadoEntrevista;
    const v = this.tramite.indEstadoVisita;
    const esVisible = (e === 2 && !v) || (e === 1 && !v)
    || (e === 1 && v === 1);
    return esVisible;
  }
}
