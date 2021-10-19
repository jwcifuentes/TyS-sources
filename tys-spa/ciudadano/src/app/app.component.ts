import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {SessionHolderService} from 'app/security/session/session-holder.service';
import {Observable} from 'rxjs/Observable';
import {LoadingService} from './services/infrastructure/loading.service';
import {MenuItem} from 'primeng/primeng';
import {BreadcrumbService} from './services/infrastructure/breadcrumb.service';
import {TranslateService} from '@ngx-translate/core';

const FONT_SIZE = 12;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {

  isLoading: boolean;
  loading$: Observable<boolean>;
  breadcrumb: any;

  fontSize = FONT_SIZE;

  constructor(private _session: SessionHolderService, private _router: Router,
              private loading: LoadingService,
              private translateService: TranslateService,
              private breadcrumbService: BreadcrumbService) {
    this.breadcrumbService.breadcrumbChanged.subscribe(bc => {
      console.log(bc);
      return this.breadcrumb = bc;
    });

  }

  resetFontSize(): number {
    this.fontSize = FONT_SIZE;

    return this.fontSize;
  }

  changeFontSize(cant: number): number {
    if (this.fontSize + cant >= 8 && this.fontSize + cant <= 24) {
      this.fontSize = this.fontSize + cant;
      return this.fontSize;
    }
    return 0;
  }

  ngOnInit(): void {
    this.loading$ = this.loading.getLoaderAsObservable();
    this.loading$.subscribe(value => {
      this.isLoading = value;
    });

    this.TranslateSettings();
  }

  public logout(): void {
    this._session.logout();
    this._router.navigate(['/login', 'logout']);
  }

  TranslateSettings() {
    this.translateService.addLangs(['es', 'en']);
    this.translateService.setDefaultLang('es');
    const browserLang = this.translateService.getBrowserLang();
    this.translateService.use(browserLang.match(/es|en/) ? browserLang : 'es');
  }

  ChangeLanguage(value: any) {
    const selectedLanguage  = value.toString();
    this.translateService.use(selectedLanguage);
  }



}
