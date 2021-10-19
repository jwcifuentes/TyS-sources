import {Api} from '../infrastructure/api';
import {Observable} from 'rxjs/Observable';
import {Response} from '@angular/http';
import {environment} from '../../../environments/environment';

export class CoreEntity<T> {

  protected uri: string;

  constructor(protected http: Api, protected url?: string) {
    this.uri = url || environment.base_endpoint;
  }

  list(params?: any): Observable<T[]> {
    return this.http.get<T[]>(`${this.uri}`, params);
  }

  listPost(payload?: any): Observable<T[]> {
    return this.http.post<T[]>(`${this.uri}`, payload || {});
  }

  get(id: number): Observable<T> {
    return this.http.get<T>(`${this.uri}/${id}`);
  }

  create(body: T): Observable<any> {
    return this.http.post<T>(`${this.uri}`, body);
  }

  remove(id: number): Observable<any> {
    return this.http.delete(`${this.uri}/${id}`);
  }

  put(id: number, body: T): Observable<any> {
    return this.http.put(`${this.uri}/${id}`, body);
  }

}
