import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-translate-options',
  templateUrl: './translate-options.component.html',
  styleUrls: ['./translate-options.component.css']
})
export class TranslateOptionsComponent implements OnInit {

  @Output() onChange: EventEmitter<string> = new EventEmitter();

  constructor(private translateService: TranslateService) { }

  ngOnInit(
  ) {
    this.TranslateSettings();
  }

  TranslateSettings() {
    this.translateService.addLangs(['es', 'en']);
    this.translateService.setDefaultLang('es');
    const browserLang = this.translateService.getBrowserLang();
    this.translateService.use(browserLang.match(/es|en/) ? browserLang : 'es');
  }

  ChangeLanguage(value: any) {
    const selectedLanguage  = value.toString();
    this.onChange.emit(selectedLanguage);
  }

}
