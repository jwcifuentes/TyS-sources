import {SessionHolderService, SessionAttribute} from '../../security/session/session-holder.service';
import {Component, OnInit, ViewChild, ElementRef} from '@angular/core';
import {AdminTramitesModel} from 'app/ui-components/admin-tramites/admin-tramites.model';
import {TramitesWcService} from 'app/services/tramites-wc.service';
import {Tramite} from 'app/domain/tramite';
import {Router, ActivatedRoute, Params} from '@angular/router';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {FlujoAdminTramite} from 'app/constants/flujo-admin-tramite.enum';
import {DomHandlerService} from 'app/util/dom-handler.service';
import {VALIDATION_MESSAGES} from '../../shared/validation-messages';

@Component({
  selector: 'app-admin-tramites',
  templateUrl: './admin-tramites.component.html'
})
export class AdminTramitesComponent implements OnInit {

  @ViewChild('spacer1')
  spacer1: ElementRef;
  model: AdminTramitesModel;

  constructor(private _tramitesService: TramitesWcService, private _router: Router,
              private fb: FormBuilder, private dom: DomHandlerService,
              private activatedRoute: ActivatedRoute, private _session: SessionHolderService) {

    this.model = new AdminTramitesModel();
    this.model.complexForm = fb.group({
      'tramite': [
        null,
        Validators.compose(
          [
            Validators.required,
            Validators.minLength(5),
            Validators.maxLength(255),
            this.model.noWhitespaceValidator
          ]
        )]
    });

    this.activatedRoute.params.subscribe((params: Params) => {
      const status = params['status'];
      if (status === 'error') {
        this.model.addError(_session.retrieve(SessionAttribute.INSTANT_MESSAGE));
      }
    });
  }

  ngOnInit() {

    this._tramitesService.listar().subscribe(
      data => this.model.tramites = data,
      error => this.model.addError(error.descripcion == null ? error.content : error.descripcion)
    );

    this.dom.addSpaces(this.spacer1, 4);
  }

  public verDetalles(tramite: Tramite): void {
    this._router.navigate(['/detalle_tramite', tramite.id]);
  }

  public crearTramite(): void {
    if (this.model.complexForm.controls['tramite'].valid) {
      const tramite = new Tramite();
      tramite.nombre = this.model.complexForm.controls['tramite'].value;
      this._tramitesService.crear(tramite, FlujoAdminTramite.CREACION_BASE).subscribe(
        data => this.manejarFlujoExito(),
        error => this.model.addError(error.descripcion == null ? error.content : error.descripcion)
      );
    } else {
      this.model.complexForm.controls['tramite'].markAsTouched();
      this.model.addError('El tramite es invalido');
    }
  }

  private manejarFlujoExito(): void {
    this.model.addInfo('El tramite se ha creado con exito');
    this.model.cleanInputs();
    this._tramitesService.listar().subscribe(
      data => this.model.tramites = data,
      error => this.model.addError(error.descripcion == null ? error.content : error.descripcion)
    );
  }

  getErrorMessage(control: string) {
    const error_keys = Object.keys(this.model.complexForm.get(control).errors);
    const last_error_key = error_keys[error_keys.length - 1];
    return VALIDATION_MESSAGES[last_error_key];
  }

  isInvalid(control: string) {
    const stringControl = control.toString().replace(/,/g, '_');
    const ac = this.model.complexForm.get(control);
    return (ac.touched || ac.dirty) && ac.invalid;
  }

}
