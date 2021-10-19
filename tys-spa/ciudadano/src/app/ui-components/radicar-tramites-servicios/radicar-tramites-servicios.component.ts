import {AfterViewInit, Component, OnInit, ViewChild, ViewEncapsulation} from '@angular/core';
import {MenuItem} from 'primeng/components/common/menuitem';
import {Message} from 'primeng/primeng';
import {DatosTramiteComponent} from './datos-tramite/datos-tramite.component';
import {DatosRemitenteComponent} from './datos-remitente/datos-remitente.component';
import {DocumentosTramiteComponent} from './documentos-tramite/documentos-tramite.component';
import {TipoDocumentalWcService} from '../../services/bussiness/tipoDocumental-wc.service';
import {SolicitudTramiteDTV} from '../../shared/data-transformers/SolicitudTramiteDTV';
import {TramiteWcService} from '../../services/bussiness/tramite-wc.service';
import {CommonModel} from './util/common.model';
import {Observable} from 'rxjs/Observable';
import {Router} from '@angular/router';
import {TipoDocumentalTramite} from '../../domain/tipo-documental-tramite';
import {Subject} from "rxjs/Subject";
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-radicar-tramites-servicios',
  templateUrl: './radicar-tramites-servicios.component.html',
  styleUrls: ['./radicar-tramites-servicios.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class RadicarTramitesServiciosComponent extends CommonModel implements OnInit, AfterViewInit {

  items: MenuItem[];

  msgs: Message[] = [];

  activeIndex = 0;

  stateValid: boolean;

  showRecaptcha: boolean;

  imNotRobot: boolean;

  showSuccessUpdateDialog: boolean = true;

  showRadicarConfirm: boolean = false;

  tramites: any[];

  nombreTramite: string;

  sharedData: Subject<any> = new Subject();

  @ViewChild('datosTramite')
  datosTramite: DatosTramiteComponent;

  @ViewChild('datosRemitente')
  datosRemitente: DatosRemitenteComponent;

  @ViewChild('datosDocumentosTramite')
  datosDocumentosTramite: DocumentosTramiteComponent;

  constructor(private tipoDocumentalService: TipoDocumentalWcService,
              public router: Router,
              private tramiteService: TramiteWcService,
              protected _translator: TranslateService) {
    super(_translator);
    // if (event) {
    //   this.msgs.length = 0;
    //   this.msgs.push({severity: 'info', summary: 'Seat Selection', detail: event.item.label});
    // }
  }

  ngOnInit() {
    this._translator.get(['GENERALES.STEPS.DATOS_TRAMITE', 'GENERALES.STEPS.DATOS_REMITENTE', 'GENERALES.STEPS.DOCUMENTOS_TRAMITE'])
                    .subscribe( (steps: [string])  => {
      this.items = [{
          label: steps['GENERALES.STEPS.DATOS_TRAMITE'],
          command: (event: any) => {
            this.activeIndex = 0;
          }
        },
        {
          label: steps['GENERALES.STEPS.DATOS_REMITENTE'],
          command: (event: any) => {
            this.activeIndex = 1;
          },
          disabled: this.activeIndex < 1
        },
        {
          label: steps['GENERALES.STEPS.DOCUMENTOS_TRAMITE'],
          command: (event: any) => {
            this.activeIndex = 2;
          },
          disabled: this.activeIndex < 2
        }
      ];

     } );

  }

  ngAfterViewInit() {
    this.listenForChanges();
  }

  previousStep() {
    if (this.activeIndex > 0) {
      this.items[this.activeIndex - 1].command();
    }
  }

  nextStep() {

    this.datosTramite.validarDireccionesSucursales();

    if( this.datosTramite.bloquearFlujo ){
      this.addWarning( this.datosTramite.razonBloqueo );
      return;
    }

    if (this.items.length > (this.activeIndex + 1)) {
      if (this.activeIndex === 0 && !this.isValidDatosTramiteEmitEvent()) {
        this._translator.get('SYSTEM.MESSAGE.WARNING.INVALID_FIELDS') .subscribe( message => {
          this.addWarning(message);
        });
        this.datosTramite.execValidations();
        return;
      } else if (this.activeIndex === 1 && !this.isValidDatosRemitente()) {
        this._translator.get('SYSTEM.MESSAGE.WARNING.INVALID_FIELDS') .subscribe( message => {
          this.addWarning(message);
        });
        this.datosRemitente.execValidations();
        return;
      }

      if (this.activeIndex === 1) {
        this.loadDocuments();
      }

      this.items[this.activeIndex + 1].command();
    }
  }

  loadDocuments() {
    const idTramite = this.datosTramite.form.get('tipoTramite').value;
    const tienePactosColectivos = this.datosTramite.form.get('tienePactos').value;
    const tieneReglamentoTrabajo = this.datosTramite.form.get('tieneReglamento').value;
    const tieneOrganizacionesSindicales = this.datosTramite.form.get('tieneOrganizacionesSindicales').value;
    const tieneSocietarioSas = this.datosTramite.form.get('tipoSocietarioSas').value;
    const idSolicitadoPor = this.datosTramite.form.get('radicadaPor').value;
    const idJustSolicitud = this.datosTramite.form.get('justificacionSolicitud').value;
    const idGradoAsociacion = this.datosTramite.form.get('gradoAsociacion').value;
    const tipoGestion = this.datosTramite.form.get('tipoGestion').value;
    const idTipoEntidadItem = this.datosTramite
      .form.get(['datosPracticaAdolecente', 'datosBasicosAdolecente', 'datosEscenarioPractica', 'tipoEntidad']).value;
    const idTipoParentescoItem = this.datosTramite
      .form.get(['datosPracticaAdolecente', 'datosBasicosAdolecente', 'datosRepresentanteLegal', 'parentesco']).value;
    const idTipoEntidad = idTipoEntidadItem ? idTipoEntidadItem.id : null;
    const idTipoParentesco = idTipoParentescoItem ? idTipoParentescoItem.id : null;
    const payload = {
      idTramite: idTramite ? idTramite.id : null,
      tienePactosColectivos: tienePactosColectivos ? tienePactosColectivos.value : false,
      tieneReglamentoTrabajo: tieneReglamentoTrabajo ? tieneReglamentoTrabajo.value : false,
      tieneOrganizacionesSindicales: tieneOrganizacionesSindicales ? tieneOrganizacionesSindicales.value : false,
      tieneSocietarioSas: tieneSocietarioSas ? tieneSocietarioSas.value : false,
      idSolicitadoPor: idSolicitadoPor ? idSolicitadoPor.id : null,
      idJustSolicitud: idJustSolicitud ? idJustSolicitud.id : null,
      idGradoAsociacion: idGradoAsociacion ? idGradoAsociacion.id : null,
      idTipoGestion: idTramite.id == 5 && tipoGestion ? tipoGestion : null,
      idTipoEntidad: idTipoEntidad,
      idTipoParentesco: idTipoParentesco
    };
    this.tipoDocumentalService.listPost(payload).subscribe((documentos: Array<TipoDocumentalTramite>) => {
      this.datosDocumentosTramite.setDocumentos(documentos);
    });

  }

  listenForChanges() {
  }

  checkComponentState() {
    const currentState = this.datosTramite.form.valid && this.datosRemitente.form.valid && this.datosDocumentosTramite.stateValid;
    if (this.stateValid !== currentState) {
      this.stateValid = currentState;
    }
  }

  isValidDatosTramiteEmitEvent() {
    // console.log(this.datosTramite.form, this.datosTramite.form.value);
    return this.datosTramite.isValidForm();
  }

  isValidDatosTramite() {
    // console.log(this.datosTramite.form, this.datosTramite.form.value);
    return this.datosTramite.form.valid;
  }

  isValidDatosRemitente() {
    // console.log(this.datosRemitente.form.value);
    return this.datosRemitente.form.valid;
  }

  isValidDatosDocumentosTramite() {
    return this.datosDocumentosTramite.stateValid;
  }

  recaptchaSolicitudRadicar() {
    if (this.activeIndex === 2 && !this.datosDocumentosTramite.stateValid) {
      this.addWarning('No se han Adjuntado todos los documentos obligatorios');
      return;
    }

    const solicitudTramite = new SolicitudTramiteDTV({
      datosTramite: this.datosTramite.getDatosTramiteForm(),
      datosRemitente: this.datosRemitente.getDatosRemitenteForm(),
      documentosTramite: this.datosDocumentosTramite.getDocumentosTramiteForm()
    });

    this.showRecaptcha = true;
    this.imNotRobot = false;
    this.radicarTramite();
  }

  radicarTramite() {

    this.imNotRobot = true;
    this.showRecaptcha = false;

    const solicitudTramite = new SolicitudTramiteDTV({
      datosTramite: this.datosTramite.getDatosTramiteForm(),
      datosRemitente: this.datosRemitente.getDatosRemitenteForm(),
      documentosTramite: this.datosDocumentosTramite.getDocumentosTramiteForm(),
    });
    const tramitar = solicitudTramite.getDatosSolicitudTramite();
    this.tramiteService.salvarTramitesRadicados(tramitar).subscribe((value) => {
      this.addSucces('El trámite se ha radicado con éxito consulte su correo para mayor información.');
      this.tramites = value;
      this.nombreTramite = this.datosTramite.form.get('tipoTramite').value.nombre;
      if (this.datosTramite) {
        this.datosTramite.form.reset();
      }
      if (this.datosRemitente) {
        this.datosRemitente.form.reset();
        this.datosRemitente.ubicaciones = [];
      }
      if (this.datosDocumentosTramite) {
        this.datosDocumentosTramite.setDocumentos([]);
      }
      this.activeIndex = 0;
      this.showSuccessUpdateDialog = true;

    }, (error) => {
      this.addError('Ocurrio un error');
      return Observable.throw(error);
    });
    // }
  }

  goToModuleSelection() {
    this.showSuccessUpdateDialog = false;
    this.router.navigate(['seleccion_modulos']);
  }

}
