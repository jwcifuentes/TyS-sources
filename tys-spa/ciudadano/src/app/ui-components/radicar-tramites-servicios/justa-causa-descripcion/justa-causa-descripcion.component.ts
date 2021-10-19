import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { IConstante } from '../../../domain/constante';
import { ConstanteWcService } from '../../../services/bussiness/constante-wc.service';
import { IConst } from 'app/domain/const';
import { CommonModel } from '../util/common.model';

@Component({
  selector: 'app-justa-causa-descripcion',
  templateUrl: './justa-causa-descripcion.component.html',
  styleUrls: ['./justa-causa-descripcion.component.css']
})
export class JustaCausaDescripcionComponent implements OnInit {

  commonModel: CommonModel;
  bloquearFlujo: boolean;
  razonBloqueo: string;

  showJustaCausaDescripcion = false;
  showDescription: boolean = true;
  listselect: any[];

  justaCausaDespido$: Observable<any[]>;
  justasCausas$: Observable<any[]>;
  public justaCausaAny: any = [];
  public justaCausalSelect: any = [];

  @Output()
  onSelectJustaCausa: EventEmitter<IConst> = new EventEmitter<IConst>();

  @Output() ShowListCausalDespido: EventEmitter<any> = new EventEmitter();
  @Output() listseleccionados: EventEmitter<any> = new EventEmitter();

  @Input() causalDespido: any;

  constructor(private constanteWcService: ConstanteWcService) { }

  ngOnInit() {
   }

   listJustaCausaL(justasCausas: Observable<any[]>) {
    if (!this.justasCausas$) {
      this.justasCausas$ = justasCausas;
    }
  }
  // setJustasCausas(justasCausas: Observable<any[]>) {
  //   if (!this.justasCausas$) {
  //     this.justasCausas$ = justasCausas;
  //     //this.justaCausaAny = justasCausas.map;
  //     ///this.justasCausas$.subscribe(justaCausaAny => this.justaCausaAny = justaCausaAny);
  //     let observab : boolean = false;
  //     this.justasCausas$.subscribe((value => {
  //       if(value){
  //         this.justaCausaAny = value;
  //         this.lstJustaCausa(this.justaCausaAny);
  //         this.showJustaCausaDescripcion = true;
  //       }
  //     }))

  //     console.log("Traida de datos",this.justaCausaAny);
  //     // this.justasCausas$.forEach(element => {
  //     //   this.justaCausaAny = element;
  //     // });
  //   }
  // }

  /*showDialog() {
    this.setJustasCausas(this.constanteWcService.listByParentCode('JUS_CAU_TS'));
    this.showJustaCausaDescripcion = true;
  }*/

  showDialog(selectedItems: Array<any>) {
    console.log(this.causalDespido);
    if (!this.causalDespido) {
      return;
    }
    this.traerJustaCasua(this.causalDespido.idCausalDespido, selectedItems);
    this.showJustaCausaDescripcion = true;
  }

  hideDialog() {

    // this.listseleccionados.forEach(elementList => {
    //     if(elementList.idCausalDespido){
    //          this.bloquearFlujoWith('se Encuentran repetidos los datos ingresados');
    //     }
    // })
    this.justaCausaAny.forEach(value => {
      value.idTipo = this.causalDespido.idCausalDespido;
    });
    this.onSelectJustaCausa.emit(this.justaCausaAny);
    this.showJustaCausaDescripcion = false;
  }

  // bloquearFlujoWith(message) {
  //   this.bloquearFlujo = true;
  //   this.commonModel.addWarning(`No es posible realizar la solicitud, ${message}`);
  //   this.razonBloqueo = `No es posible realizar la solicitud, ${message}`;
  // }


  selectJustaCausa() {

    // // this.listseleccionados = this.justasCausas$.toArray().filter((item, index, array) => item.check_description);
    // let save: any = this.justasCausas$.toArray()

    // for (let item of save) {
    //   if (item.check_description == true) {
    //     this.listseleccionados.push(item)
    //   }
    // }

    // // this.justasCausas$.forEach(item => {
    // //   if (item) {
    // //     this.listseleccionados.push(item)
    // //   }
    // // });

    // this.hideDialog();
    // this.onSelectJustaCausa.next();
  }

  tsonclick(item) {
    //item.check_description = !item.check_description;
    item.idTipo = this.causalDespido.idCausalDespido;
    if (!this.listselect) {
      this.listselect = [];
    }
    this.onSelectJustaCausa.emit(this.justaCausaAny);
  }

  // lstJustaCausa(any : any[]){
  //   any.forEach(resp => {
  //     if (resp.idCausalDespido == 1) {
  //       this.justaCausalSelect.push(resp);
  //     }
  //   });
  // }
  // ShowJustaCausaCausalDespido() {
  //   this.listJustaCausaL(this.constanteWcService.listJustaCausa(1));
  // }

  traerJustaCasua(idCausal, selectedItems: Array<any>) {
    this.constanteWcService.listJustaCausa2(idCausal).take(1).subscribe(values => {
      if (selectedItems) {
        values.forEach(value => {
          const selectedIndex = selectedItems.findIndex((item) => item.idJustasCausas === value.idJustasCausas);
          if (selectedIndex !== -1) {
            value.check_description = selectedItems[selectedIndex].check_description;
            value.observacion = selectedItems[selectedIndex].observacion;
          }
        });
        this.justaCausaAny = [...values];
      }
    });
  }

  onChange(item) {
    item.check_description = !item.check_description;
  }
}
