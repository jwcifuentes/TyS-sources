import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/switchMap';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { environment } from '../../../environments/environment';

interface RequestOptions {
  headers?: HttpHeaders;
  observe?: 'body';
  params?: HttpParams;
  reportProgress?: boolean;
  responseType?: 'json';
  withCredentials?: boolean;
};

/**
 * Api is a generic REST Api handler. Set your API url first.
 */
@Injectable()
export class Api {

  url: string = environment.base_endpoint;

  constructor(public http: HttpClient) {
  }

  get<T>(endpoint: string, params?: any, options?: RequestOptions) {
    options = this.transformOptionParams(params, options);
    return this.http.get<T>(this.url + '/' + endpoint, options);
  }

  post<T>(endpoint: string, body: any, options?: RequestOptions, httpParams?: any) {
    options = this.transformOptionParams(httpParams, options);
    return this.http.post<T>(this.url + '/' + endpoint, body, options);
  }

  put<T>(endpoint: string, body: any, options?: RequestOptions, httpParams?: any) {
    options = this.transformOptionParams(options);
    return this.http.put<T>(this.url + '/' + endpoint, body, options);
  }

  delete(endpoint: string, options?: RequestOptions, httpParams?: any) {
    options = this.transformOptionParams(httpParams, options);
    return this.http.delete(this.url + '/' + endpoint, options);
  }

  patch(endpoint: string, body: any, options?: RequestOptions, httpParams?: any) {
    options = this.transformOptionParams(httpParams, options);
    return this.http.put(this.url + '/' + endpoint, body, options);
  }

  urlEncodeParams(obj) {
    const str = [];
    for (const p in obj) {
      str.push(encodeURIComponent(p) + '=' + encodeURIComponent(obj[p]));
    }
    return str.join('&');
  }

  transformOptionParams(params?: any, options?: RequestOptions) {
    if (!params) {
      return options;
    } else if (!options) {
      options = {};
    }
    // Support easy query params for GET requests
    if (params) {
      let p = new HttpParams();

      for (const k in params) {
        if (params[k] !== null) {
          p = p.set(k, <string>params[k]);
        }
      }
      // Set the search field if we have params and don't already have
      // a search field set in options.
      options.params = p;
    }
    return options;
  }

}
