import {Component, OnInit, AfterContentInit} from '@angular/core';
import {Router, ActivatedRoute, Params} from '@angular/router';
import {DomHandlerService} from 'app/util/dom-handler.service';
import {SessionHolderService, SessionAttribute} from 'app/security/session/session-holder.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {CommonModel} from 'app/util/common.model'
import {SeguridadWcService} from 'app/services/seguridad-wc.service';
import {Usuario} from 'app/domain/usuario'
import {Response} from '@angular/http';
import {Subscription} from 'rxjs/Subscription';
import {MessageBridgeService, MessageType} from 'app/util/message-bridge.service';
import {VALIDATION_MESSAGES} from '../../shared/validation-messages';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent extends CommonModel implements OnInit {

  form: FormGroup;
  private subscription: Subscription;

  constructor(private dom: DomHandlerService, private _router: Router, private _session: SessionHolderService,
              private fb: FormBuilder, private _seguridad: SeguridadWcService, private activatedRoute: ActivatedRoute) {
    super();
  }

  ngOnInit() {

    this.form = this.fb.group({
      'login': [null, Validators.compose([Validators.required])],
      'password': [null, Validators.compose([Validators.required])]
    });

    this.activatedRoute.params.subscribe((params: Params) => {
      const status = params['status'];
      if (status === 'expired') {
        this.addWarning('El token de la sesion de usuario ha caducado, ingrese sus credenciales de nuevo');
      }
      if (status === 'logout') {
        this.addInfo('La sesion ha sido cerrada de forma segura');
      }
    });
  }

  public login(): void {
    if (this.form.valid) {
      const usuario = new Usuario();
      usuario.login = this.form.controls['login'].value;
      usuario.password = this.form.controls['password'].value;
      this._seguridad.login(usuario).subscribe(
        data => this.handleLoginOk(data),
        error => {
          this.form.reset();
          this.addError(error.descripcion == null ? error.content : error.descripcion);
        }
      );
    } else {
      this.form.markAsTouched();
      this.addWarning('Los campos del formulario son obligatorios');
    }
  }

  private handleLoginOk(usuario: Usuario) {
    this._session.loggedIn();
    this._session.save(SessionAttribute.USER_FULL_NAME, usuario.nombres);
    this._session.save(SessionAttribute.LOGIN, usuario.login);
    this._router.navigate(['/admin_tramites']);
  }

  getErrorMessage(control: string) {
    const error_keys = Object.keys(this.form.get(control).errors);
    const last_error_key = error_keys[error_keys.length - 1];
    return VALIDATION_MESSAGES[last_error_key];
  }

  isInvalid(control: string) {
    const stringControl = control.toString().replace(/,/g, '_');
    const ac = this.form.get(control);
    return (ac.touched || ac.dirty) && ac.invalid;
  }

}
