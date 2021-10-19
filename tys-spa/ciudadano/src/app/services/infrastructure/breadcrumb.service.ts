import {EventEmitter, Injectable} from '@angular/core';

import {MenuModule, MenuItem} from 'primeng/primeng';
import {Router, ActivatedRouteSnapshot, RoutesRecognized} from '@angular/router';
import { TranslateService } from '@ngx-translate/core';

/**
 * A service provides functions to get and set the breadcrumb of the app
 */
@Injectable()
export class BreadcrumbService {
  items: MenuItem[] = [];

  public breadcrumbChanged: EventEmitter<MenuItem[]> = new EventEmitter<MenuItem[]>();

  getBreadcrumb(): MenuItem[] {
    return this.items;
  }

  setBreadcrumb(breadcrumb: MenuItem[]): void {
    this.items = breadcrumb;
    this.breadcrumbChanged.next(this.items);
  }

  // this.breadcrumbItems.push({label:'Products'});
  // this.breadcrumbItems.push({label:'Category-C', routerLink: ['/products/category-c']});
  // this.breadcrumbItems.push({label: 'My-Product'});
  // this.breadcrumb.setBreadcrumb(this.breadcrumbItems);

  constructor(private _router: Router, private _translator: TranslateService) {

    this._router.events.subscribe(eventData => {
      if (eventData instanceof RoutesRecognized) {
        const event: any = eventData;
        let currentUrlPart = event.state._root;
        // let currUrl = ''; // for HashLocationStrategy

        this.items = [];
        if (currentUrlPart.children.length > 0) {
          currentUrlPart = currentUrlPart.children[0];
          const routeSnaphot = <ActivatedRouteSnapshot>currentUrlPart.value;

          const data = (<any>routeSnaphot.data).breadcrumb;

          data.forEach((v) => {
            this._translator.get(v.displayName).subscribe(result => {
              this.items.push({
                label: result,
                routerLink: v.url,
                // params: routeSnaphot.params
              });
            });

          });

          /*
          data.forEach((v) => {
            this.items.push({
              label: v.displayName,
              routerLink: v.url,
              // params: routeSnaphot.params
            });
          });
          */

          console.log(this.items, routeSnaphot);
        }
        this.setBreadcrumb(this.items);
      }
    });
  }

}
