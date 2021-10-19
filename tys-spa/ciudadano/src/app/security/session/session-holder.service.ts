import { Injectable } from '@angular/core';
import CryptoJS from 'crypto-js';

@Injectable()
export class SessionHolderService {

  private securityKey: string = 'secret';

  constructor() { }

  //--------------------

  public save(key: SessionAttribute, payload: any): void
  {
    let cipher = CryptoJS.AES.encrypt(payload + '', this.securityKey);
    sessionStorage.setItem(key.toString(), cipher.toString());
  }

  public saveToken(key: SessionAttribute, payload: any): void {
    sessionStorage.setItem(key.toString(), payload);
  }

  //--------------------

  public retrieve(key: SessionAttribute): any {
    let cipher = sessionStorage.getItem(key.toString())
    if( cipher == null ){
      return null;
    }else{
      var bytes  = CryptoJS.AES.decrypt(cipher.toString(), this.securityKey);
      return bytes.toString(CryptoJS.enc.Utf8);
    }
  }

  //--------------------

  public isLoggedIn(): boolean {
    return this.retrieve(SessionAttribute.LOGGED_IN) ;
  }

  //--------------------

  public getToken(): string {
    return sessionStorage.getItem(SessionAttribute.SECURITY_TOKEN.toString());
  }

  //--------------------

  public getUserFullName(): string {
    return this.retrieve(SessionAttribute.USER_FULL_NAME);
  }

  //--------------------

  public loggedIn(): void {
    this.save(SessionAttribute.LOGGED_IN,true);
  }

  //--------------------

  public logout(): void {
    sessionStorage.clear();
  }

}

export enum SessionAttribute {
   SECURITY_TOKEN, LOGGED_IN, USER_FULL_NAME, LOGIN
}
