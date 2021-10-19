import {Message} from 'primeng/primeng';

export class CommonModel {

  msgs: Message[];

  constructor() {
  }

  public addInfo(detail: string): void {
    this.msgs = [{severity: 'info', summary: 'Mensaje del sistema', detail: detail}];
  }

  public addWarning(detail: string): void {
    this.msgs = [{severity: 'warn', summary: 'Mensaje del sistema', detail: detail}];
  }

  public addError(detail: string): void {
    if (detail == null) {
      detail = 'Ha ocurrido un error interno de sistema';
    }
    this.msgs = [{severity: 'error', summary: 'Mensaje del sistema', detail: detail}];
  }

  public addSucces(detail: string): void {
    this.msgs = [{severity: 'success', summary: 'Mensaje del sistema', detail: detail}];
  }

}
