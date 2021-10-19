import { Component, EventEmitter, HostBinding, OnInit, Output, ChangeDetectorRef } from '@angular/core';

import { TramiteWcService } from '../../../../services/bussiness/tramite-wc.service';
import { Tramite } from '../../../../domain/tramite';
//import { CommonModel } from '../../../radicar-tramites-servicios/util/common.model';
import { TranslateService } from '@ngx-translate/core';


import { CommonModel } from 'app/ui-components/radicar-tramites-servicios/util/common.model';

@Component({
  selector: 'app-radicados-referidos',
  templateUrl: './radicados-referidos.component.html'
})
export class RadicadosReferidosComponent implements OnInit {
  commonModel: CommonModel;
  tramite: Tramite = null;
  isTramite: number = 0;
  mostrarBtnAgregar: boolean = false;

  @Output() onValid: EventEmitter<any> = new EventEmitter();
  @Output() onInvalid: EventEmitter<any> = new EventEmitter();

  @HostBinding('class')
  clazz = 'ui-sm-12 ui-md-12 ui-g-12 ui-lg-12';
  collection: Array<{ nombre: string }> = [];
  currentValue: any;

  constructor(public tramiteWcService: TramiteWcService
    , protected _translator: TranslateService
    , private changeDetector:ChangeDetectorRef
    ) {
      this.commonModel = new CommonModel(_translator);
  }

  ngOnInit() {
  }

  add() {
    this.verTramite(this.currentValue.toUpperCase());
  }

  verTramite(numeroRadicado: string) {
   
    this.tramiteWcService.consultarTramitesRadicadosARelacionar(numeroRadicado)
      .subscribe(
        (data: number) => {
          this.isTramite = data;
          //debugger;
          if (this.isTramite > 0) {
            const insertVal = [{ nombre: this.currentValue }];
            this.collection = [...insertVal, ...this.collection.filter(value => value.nombre !== this.currentValue)];
            this.currentValue = null;
            if (this.collection.length === 1) {
              this.onValid.emit();
            }
          }else{
            this.commonModel.addWarning(`N. Radicado Referido no existe.`);
            this.changeDetector.detectChanges();

            return;
          }

        },
        error => {
          if (error.status === 400) {
            this.commonModel.addWarning(`N. Radicado Referido no existe.`);
            return;
          }
        },
        () => {
          console.log('Validación realizada - referidos');
        }
      );
  }

  /*verTramite(numeroRadicado: string): Tramite {

    this.tramiteWcService.consultarTramitesRadicadosARelacionar(numeroRadicado)
      .subscribe(
        (data: Tramite) => {
          this.tramite = data;
          const insertVal = [{ nombre: this.currentValue }];
          this.collection = [...insertVal, ...this.collection.filter(value => value.nombre !== this.currentValue)];
          this.currentValue = null;
          if (this.collection.length === 1) {
            this.onValid.emit();
          }
        },
        error => {
          if (error.status === 400) {
            this.addWarning('No se encontraron resultados');
          }
        },
        () => {
          console.log('Final');
        }
      );
    return this.tramite;
  }*/

  remove(index) {
    const removeVal = [...this.collection];
    removeVal.splice(index, 1);
    this.collection = removeVal;
    if (this.collection.length === 0) {
      this.onInvalid.emit();
    }
  }

  getRadicadosReferidosCollection() {
    return this.collection;
  }

  restrictAlphaNum(event) {
    if (event.key === 'Backspace' || event.key === 'Delete' || event.key === 'ArrowLeft' || event.key === 'ArrowRight')
      return true;
    return String.fromCharCode(event.charCode).match(/[a-z0-9ñÑ&_\u00f1\u00d1\x81-\xFF]/gi) !== null;
  }

}
