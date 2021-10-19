import {Injectable} from '@angular/core';
import Dexie from 'dexie';
import {IndexedIConstantes} from '../../shared/data-transformers/interfaces/indexed-iConstantes';

@Injectable()
export class LocalDatabase extends Dexie {

  cache: Dexie.Table<IndexedIConstantes, number>;

  constructor () {
    super('mintrabajo');
    this.version(1).stores({
      cache: '++id, codigo',
    });
  }
}
