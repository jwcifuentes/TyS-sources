import {Directive, forwardRef, Attribute} from '@angular/core';
import {Validator, AbstractControl, NG_VALIDATORS} from '@angular/forms';

@Directive({
  selector: '[validateEqual][formControlName],[validateEqual][formControl],[validateEqual][ngModel]',
  providers: [
    {provide: NG_VALIDATORS, useExisting: forwardRef(() => EqualValidator), multi: true}
  ]
})
export class EqualValidator implements Validator {
  constructor(@Attribute('validateEqual') public validateEqual: string) {

  }

  validate(c: AbstractControl): { [key: string]: any } {
    // self value
    let v = c.value;

    // control vlaue
    let e = c.parent.get(this.validateEqual);

    if (e.pristine || c.pristine) {
      return null;
    }

    // value not equal
    if (e && v.toLowerCase() !== e.value.toLowerCase()) {
      return {
        validateEqual: false
      };

    }

    // value equal
    if (e && v === e.value) {
      if (e.errors) {
        e.setErrors(null);
      }
    }

    return null;
  }
}
