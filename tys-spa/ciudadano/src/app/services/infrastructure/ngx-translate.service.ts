import {Injectable} from '@angular/core';
import {TranslateService} from '@ngx-translate/core';

@Injectable()
export class NgxTranslateService {

  constructor(public _translate: TranslateService) {
    _translate.setDefaultLang('es');
  }

  switchLanguage(language: string) {
    this._translate.use(language);
  }
}
