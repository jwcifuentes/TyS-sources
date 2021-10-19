import { Component, EventEmitter, HostBinding, Input, OnInit, Output } from '@angular/core';
import { IConstante } from '../../../../domain/constante';

@Component({
  selector: 'app-direcciones-territoriales',
  templateUrl: './direcciones-territoriales.component.html'
})
export class DireccionesTerritorialesComponent implements OnInit {

  @Input() options;
  @Output() onValid: EventEmitter<any> = new EventEmitter();
  @Output() onInvalid: EventEmitter<any> = new EventEmitter();
  @Output() infoChange: EventEmitter<any> = new EventEmitter();

  @Input()
  cantidadPermitidas: string;

  @HostBinding('class')
  clazz = 'ui-sm-12 ui-md-12 ui-g-12 ui-lg-12';

  collection: Array<IConstante> = [];
  currentValue: IConstante;

  constructor() {
  }

  ngOnInit() {
  }

  add() {
    if (this.collection.findIndex((item) => item.id === this.currentValue.id) < 0) {
      this.collection = [this.currentValue, ...this.collection];
      if (this.collection.length === 1) {
        this.onValid.emit();
        this.infoChange.emit(this.currentValue);
      }
    }
    this.currentValue = null;
  }

  remove(index) {
    const removeVal = [...this.collection];
    removeVal.splice(index, 1);
    if (removeVal.length === 0) {
      this.onInvalid.emit();
    }
    this.collection = removeVal;
  }

  clean() {
    this.collection = [];
  }
}
