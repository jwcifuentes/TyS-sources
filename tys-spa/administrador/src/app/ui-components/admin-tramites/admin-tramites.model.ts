import {Tramite} from 'app/domain/tramite';
import {CommonModel} from 'app/util/common.model';
import {FormControl, FormGroup} from '@angular/forms';

export class AdminTramitesModel extends CommonModel {

  complexForm: FormGroup;
  tramites: Array<Tramite>;

  constructor() {
    super();
  }

  public cleanInputs(): void {
    this.complexForm.reset();
  }


  public noWhitespaceValidator(control: FormControl) {
    const isWhitespace = (control.value && control.value.length > 0) && (control.value || '').trim().length === 0;
    const isValid = !isWhitespace;
    return isValid ? null : {'whitespace': true};
  }

}
