import {Injectable} from '@angular/core';
import {environment} from 'environments/environment';
import * as CryptoJS from 'crypto-js/crypto-js';

@Injectable()
export class CipherService {

  private securityKey: string = environment.securityKey;

  constructor() {
  }

  // --------------------

  public encrypt(payload: any): string {
    const cipher = CryptoJS.AES.encrypt(payload + '', this.securityKey);
    return cipher.toString();
  }

  // --------------------

  public decrypt(cipher: any): any {
    const bytes = CryptoJS.AES.decrypt(cipher.toString(), this.securityKey);
    return bytes.toString(CryptoJS.enc.Utf8);
  }

}

