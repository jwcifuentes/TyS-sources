import {Injectable} from '@angular/core';
import {Subject} from 'rxjs/Subject';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class MessageBridgeService {

  private subject = new Subject<any>();

  constructor() {
  }

  public sendMessage(message: any): void {
    this.subject.next(message);
  }

  public clearMessage(): void {
    this.subject.next();
  }

  public getMessage(): Observable<any> {
    return this.subject.asObservable();
  }

}

export enum MessageType {
  TOKEN_EXPIRED
}
