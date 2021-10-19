import {Message} from 'primeng/primeng';
import { TranslateService } from '@ngx-translate/core';

export class CommonModel {

  msgs: Message[];

  constructor(protected _translator: TranslateService) {
  }

  public addInfo(detail: string): void {
    this._translator.get('SYSTEM.MESSAGE.SUMMARY') .subscribe( summary => {
      this.msgs = [{severity: 'info', summary: summary, detail: detail}];
    });
  }

  public addWarning(detail: string): void {
    this._translator.get('SYSTEM.MESSAGE.SUMMARY') .subscribe( summary => {
      //debugger;
      this.msgs = [{severity: 'warn', summary: summary, detail: detail}];
    });
  }

  public addError(detail: string): void {
    this._translator.get(['SYSTEM.MESSAGE.SUMMARY', 'SYSTEM.MESSAGE.DEFAULT_ERROR']) .subscribe( (summary: [string]) => {
      if (detail == null) {
        detail = summary['SYSTEM.MESSAGE.DEFAULT_ERROR'];
      }
      this.msgs = [{severity: 'error', summary: summary['SYSTEM.MESSAGE.SUMMARY'], detail: detail}];
    });
  }

  public addSucces(detail: string): void {
    this._translator.get('SYSTEM.MESSAGE.SUMMARY') .subscribe( summary => {
      this.msgs = [{severity: 'success', summary: summary, detail: detail}];
    });
  }



}
