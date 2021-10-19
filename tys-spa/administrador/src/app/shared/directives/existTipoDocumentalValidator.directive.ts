import {NgControl} from '@angular/forms';
import {Directive, Input} from '@angular/core';
import {TiposDocumentalesWcService} from '../../services/tipos-documentales-wc.service';

@Directive({
  selector: '[existTipoDocumental][formControlName],[existTipoDocumental][formControl],[existTipoDocumental][ngModel]',
  exportAs: 'existTipoDocumental',
  host: {
    '(onChange)': 'onBlur($event)'
  }
})
export class ExistTipoDocumentalValidatorDirective {

  private idTramite: any;

  constructor(private servForValidation: TiposDocumentalesWcService, private formControl: NgControl) {

  }

  @Input()
  set existTipoDocumental(idTramite) {
    this.idTramite = idTramite;
  }

  onBlur($event) {
    if ($event.value) {
      this.servForValidation.validarTipoDocumental($event.value, this.idTramite).subscribe((response: any) => {
        this.formControl.control.updateValueAndValidity();
      }, (error) => {
        this.formControl.control.setErrors({'existTipoDocumental': true});
      });
    }
  }

}
