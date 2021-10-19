import {Injectable} from '@angular/core';
import CryptoJS from 'crypto-js';
import {CipherService} from 'app/security/session/cipher.service';

@Injectable()
export class SessionHolderService {

  constructor(private _cipher: CipherService) {
  }

  // --------------------

  public save(key: SessionAttribute, payload: any): void {
    const cipherValue = this._cipher.encrypt(payload);
    sessionStorage.setItem(key.toString(), cipherValue.toString());
  }

  public saveToken(key: SessionAttribute, payload: any): void {
    sessionStorage.setItem(key.toString(), payload);
  }

  // --------------------

  public retrieve(key: SessionAttribute): any {
    const cipherValue = sessionStorage.getItem(key.toString())
    if (cipherValue == null) {
      return null;
    } else {
      return this._cipher.decrypt(cipherValue.toString());
    }
  }

  // --------------------

  public isLoggedIn(): boolean {
    return this.retrieve(SessionAttribute.LOGGED_IN);
  }

  // --------------------

  public getToken(): string {
    return sessionStorage.getItem(SessionAttribute.SECURITY_TOKEN.toString());
  }

  // --------------------

  public getUserFullName(): string {
    return this.retrieve(SessionAttribute.USER_FULL_NAME);
  }

  // --------------------

  public loggedIn(): void {
    this.save(SessionAttribute.LOGGED_IN, true);
  }

  // --------------------

  public logout(): void {
    sessionStorage.clear();
  }

}

export enum SessionAttribute {
  SECURITY_TOKEN, LOGGED_IN, USER_FULL_NAME, LOGIN, INSTANT_MESSAGE
}
