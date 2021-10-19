import { Injectable } from '@angular/core';
import { Api } from '../infrastructure/api';
import { CoreEntity } from '../infrastructure/core-entity.service';
import { IConstante } from '../../domain/constante';
import { YesNo } from '../../domain/yes-no';
import { Observable } from 'rxjs/Observable';
import { LocalDatabase } from '../infrastructure/local-database';
import 'rxjs/add/operator/do';
import 'rxjs/add/observable/from';
import 'rxjs/add/observable/of';
import 'rxjs/operators/exhaustMap';
import { IConst } from 'app/domain/const';
import {ActividadEconomica} from '../../domain/ActividadEconomica';

@Injectable()
export class ConstanteWcService extends CoreEntity<IConst> {
  gestionarJustaCausa = 'justaCausaCausalD';

  constructor(protected readonly http: Api, protected cacheDB: LocalDatabase) {
    super(http, 'parametros-wc');
  }

  listByParentCode(codigoPadre: string): Observable<IConst[]> {

    return Observable.from(this.getCachedData(codigoPadre)).exhaustMap(record => {
      if (record) {
        return Observable.of(record.data);
      } else {
        return this.http.get<IConst[]>(`${this.uri}/${codigoPadre}`).do(response => {
          this.cacheDB.cache.add({ codigo: codigoPadre, data: response });
        });
      }
    });
  }

  async getCachedData(codigo) {
    return this.cacheDB.cache.where('codigo').equals(codigo).first();
  }
  listActividadesEconomicas(divisionGrupo): Observable<Array<any>> {
    return this.http.get<ActividadEconomica>(`${this.uri}/actividad-economica/${divisionGrupo}`);
  }
  listYesNoConstants(): Observable<Array<YesNo>> {
    return Observable.of([{
      nombre: 'Seleccione',
      value: true
    },
      {
      nombre: 'Si',
      value: true
    }, {
      nombre: 'No',
      value: false
    }]);
  }

  // listJustaCausa(): Observable<any[]> {
  //   console.log("entra para la prueba");
  //   this.http.get(`${this.uri}/justaCausa`).subscribe(Response =>
  //     console.log(Response));
  //   return this.http.get(`${this.uri}/justaCausa`);
  // }

  listCausaDespido(): Observable<any[]> {
    console.log("Entrada prueba Causa despido");
    this.http.get(`${this.uri}/causaDespido`).subscribe(Response =>
      console.log(Response));
    return this.http.get(`${this.uri}/causaDespido`);
  }

  //listJustaCausa(idCausalDespido: number): Observable<any[]>{
    //  let codigoPadre: string = idCausalDespido.toFixed();
    // return Observable.from(this.getCachedDataJus(codigoPadre)).exhaustMap(record => {
    //   if (record) {
    //     return Observable.of(record.data);
    //   } else {
    //     return this.http.get<any[]>(`${this.uri}/${this.gestionarJustaCausa}/${codigoPadre}`).do(response => {
    //       this.cacheDB.cache.add({ codigo: codigoPadre, data: response });
    //     });
    //   }
    // });

    //     return this.http.get(`${this.uri}/${this.gestionarJustaCausa}`,idCausalDespido).catch((error) => {
    //       return Observable.throw(error);
    // });

    // console.log("Entrada al servicio List Justa Causal Despido");
    // this.http.get(`${this.uri}/${this.gestionarJustaCausa}/${idCausalDespido}`).subscribe(Response =>
    //   console.log(Response));
    // return this.http.get(`${this.uri}/${this.gestionarJustaCausa}`,idCausalDespido);
  //}

  listJustaCausa2(idCausalDespido){
    return this.http.get(`${this.uri}/${this.gestionarJustaCausa}/${idCausalDespido}`).catch((error) => {
      return Observable.throw(error);
  });
}

// consultarDocumentosAdicionales(numeroRadicado) {
//   return this.http.get(`${this.gestionarTramiteUri}/${this.documentosAdicionalesTramiteUrl}/${numeroRadicado}`).catch((error) => {
//     return Observable.throw(error);
//   });
// }


}
