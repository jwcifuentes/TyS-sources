import {Observable} from 'rxjs/Observable';
import {Injectable, Injector} from '@angular/core';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {LoadingService} from '../infrastructure/loading.service';

@Injectable()
export class PendingRequestInterceptor implements HttpInterceptor {

  pendingRequests = 0;
  filteredUrlPatterns: RegExp[] = [];
  loadingService: LoadingService;


  constructor(private injector: Injector) {
    this.loadingService = this.injector.get(LoadingService);
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const shouldBypass = this.shouldBypass(req.url);

    if (!shouldBypass) {
      this.pendingRequests++;
      if (1 === this.pendingRequests) {
        this.loadingService.presentLoading();
      }
    }
    return next.handle(req).do(request => {
      if (!shouldBypass && request.type !== 0) {
        this.pendingRequests--;
        if (0 === this.pendingRequests) {
          this.loadingService.dismissLoading();
        }
      }
    }).catch((error) => {
      if (!shouldBypass) {
        this.pendingRequests = 0;
        this.loadingService.dismissLoading();
      }
      return Observable.throw(error);
    });
  }

  private shouldBypass(url: string): boolean {
    return this.filteredUrlPatterns.some(e => {
      return e.test(url);
    });
  }
}
