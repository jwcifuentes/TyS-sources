import { Injector } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { VALIDATION_MESSAGES } from '../validation-messages';
import { Observable } from 'rxjs/Observable';
import { combineLatest, distinctUntilChanged } from 'rxjs/operators';

export abstract class FormBaseComponent {

  form: FormGroup;
  visibility: any = {};
  validations: any = {};

  constructor(injector: Injector) {
  }

  get names() {
    return /[a-zñÑ&\u00f1\u00d1\x81-\xFF\s]/gi;
  }

  get numbers() {
    return /[0-9]/g;
  }

  get alphanum() {
    return /([a-zA-Z0-9_\u00f1\u00d1\x81-\xFF])/gi;
  }

  get alpha() {
    return /[a-zñÑ&_\u00f1\u00d1\x81-\xFF]/gi;
  }

  isEspecialKey(event) {
    return event.key === 'Backspace' || event.key === 'Delete' || event.key === 'ArrowLeft' ||
      event.key === 'ArrowRight' || event.key === 'Enter' || event.key === 'Tab';
  }

  restrictAlphaNames(event) {
    return this.isEspecialKey(event) || String.fromCharCode(event.charCode).match(this.names) !== null;
  }

  restrictAlpha(event) {
    return this.isEspecialKey(event) || String.fromCharCode(event.charCode).match(this.alpha) !== null;
  }

  restrictAlphaNum(event) {
    return this.isEspecialKey(event) || String.fromCharCode(event.charCode).match(this.alphanum) !== null;
  }

  restrictNumbers(event) {
    return this.isEspecialKey(event) || String.fromCharCode(event.charCode).match(this.numbers) !== null;
  }

  listenForBlurEvents(control: string | Array<string>) {
    const stringControl = control.toString().replace(/,/g, '_');
    const ac = this.form.get(control);
    try {
      if (ac.touched && ac.invalid) {
        const error_keys = Object.keys(ac.errors);
        const last_error_key = error_keys[error_keys.length - 1];
        this.validations[stringControl] = VALIDATION_MESSAGES[last_error_key];
      }
    } catch (error) {

    }

  }

  bindToValidationErrorsOf(control: string | Array<string>) {
    const ac = this.form.get(control);
    const stringControl = control.toString().replace(/,/g, '_');
    try {
      Observable.combineLatest(
        ac.statusChanges.distinctUntilChanged(),
        ac.valueChanges
      ).subscribe(([s, v]) => {
        if ((ac.touched || ac.dirty) && ac.errors) {
          const error_keys = Object.keys(ac.errors);
          const last_error_key = error_keys[error_keys.length - 1];
          this.validations[stringControl] = VALIDATION_MESSAGES[last_error_key];
        } else {
          delete this.validations[stringControl];
        }
      });
    } catch (e) {
      console.log(stringControl);
    }
  }

  execValidations() {
    this.execValidationErrorsOf(this.form);
  }

  execValidationErrorsOf(form: any, parent?: string) {
    Object.keys(form.controls).forEach(key => {
      const ac = form.get(key);
      if (ac instanceof FormControl) {
        ac.markAsTouched();
        if (ac.errors) {
          console.log(ac.errors, key);
          const error_keys = Object.keys(ac.errors);
          const last_error_key = error_keys[error_keys.length - 1];
          this.validations[(parent) ? parent + '_' + key : key] = VALIDATION_MESSAGES[last_error_key];
        } else {
          delete this.validations[(parent) ? parent + '_' + key : key];
        }
      } else {
        this.execValidationErrorsOf(ac, (parent) ? parent + '_' + key : key);
      }
    });
  }
  onNameChange(field: string, varName?: string): void {
    const fullname = this.buildFullname(
      this.form.get(field + 'primerNombre').value,
      this.form.get(field + 'segundoNombre').value,
      this.form.get(field + 'primerApellido').value,
      this.form.get(field + 'segundoApellido').value
    );

    if (varName) {
      this.form.get(field + varName).setValue(fullname);
    } else {
      this.form.get(field + 'nombreCompleto').setValue(fullname);
    }
  }
  buildFullname(primerNombre: string, segundoNombre: string, primerApellido: string, segundoApellido: string): string {
    let fullname = '';

    if (primerNombre != null && primerNombre.length > 0) {
      fullname = primerNombre.replace(' ', '');
    }

    if (segundoNombre != null && segundoNombre.length > 0) {
      fullname = fullname + ' ' + segundoNombre.replace(' ', '');
    }

    if (primerApellido != null && primerApellido.length > 0) {
      fullname = fullname + ' ' + primerApellido.replace(' ', '');
    }

    if (segundoApellido != null && segundoApellido.length > 0) {
      fullname = fullname + ' ' + segundoApellido.replace(' ', '');
    }

    return fullname.trim();
  }
  getLocaleEs(): any {
    return {
      firstDayOfWeek: 1,
      dayNames: ['domingo', 'lunes', 'martes', 'miércoles', 'jueves', 'viernes', 'sábado'],
      dayNamesShort: ['dom', 'lun', 'mar', 'mié', 'jue', 'vie', 'sáb'],
      dayNamesMin: ['D', 'L', 'M', 'X', 'J', 'V', 'S'],
      monthNames: ['enero', 'febrero', 'marzo', 'abril', 'mayo', 'junio', 'julio', 'agosto', 'septiembre',
        'octubre', 'noviembre', 'diciembre'],
      monthNamesShort: ['ene', 'feb', 'mar', 'abr', 'may', 'jun', 'jul', 'ago', 'sep', 'oct', 'nov', 'dic'],
      today: 'Hoy',
     // dateFormat: 'yy-mm-dd',
      clear: 'Borrar'
    };
  }
}
