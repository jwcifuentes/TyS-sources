import {Injectable} from '@angular/core';
import {Api} from '../infrastructure/api';
import {CoreEntity} from '../infrastructure/core-entity.service';
import {IConstante} from '../../domain/constante';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class TratamientoCortesiaWcService extends CoreEntity<IConstante> {

  constructor(protected readonly http: Api) {
    super(http, 'tratamiento-cortesia-wc/');
  }

}
