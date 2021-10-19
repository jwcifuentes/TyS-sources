import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {CommonUtilService} from './common-util.service';
import {RemitenteDTV} from '../shared/data-transformers/RemitenteDTV';

@Injectable()
export class GenerarPdfService {
  headers = new HttpHeaders({
    'Content-Type': 'text/plain',
    'Accept': 'application/pdf',
  });
  gestionarTramiteUri = 'gestionar-tramites-wc';
  url: string = environment.base_endpoint;
  private remitenteDTV: RemitenteDTV
  

  constructor(private http: HttpClient,
              private commonUtilService: CommonUtilService,
              ) {
                this.remitenteDTV = new RemitenteDTV(null);
  }

  getPdf(request): Promise<any> {
    const json = this.getDataAsJsonStr(request);
    console.log(json);
    const myHeaders = new Headers();
    myHeaders.append('Content-Type', 'text/plain');
    myHeaders.append('Authorization', this.commonUtilService.getAuthorization().Authorization);
    const requestOptions = {
      method: 'POST',
      headers: myHeaders,
      body: json,
      redirect: 'follow'
    };
    return fetch(`${this.url}/${this.gestionarTramiteUri}/generate-pdf`, requestOptions)
      .then(response => response.blob());
  }

  getDataAsJsonStr(datosPractica): string {
    this.getConcatName(datosPractica);    
    const datosBasicosAdolecente = datosPractica.datosPracticaAdolecente.datosBasicosAdolecente;
    datosBasicosAdolecente.datosResidencia.direcciones = this.getDirecciones(datosBasicosAdolecente.datosResidencia.direcciones);
    datosBasicosAdolecente.datosEscenarioPractica.direcciones = this.getDirecciones(datosBasicosAdolecente.datosEscenarioPractica.direcciones);
    datosBasicosAdolecente.datosFormacionComplementaria.direcciones = this.getDirecciones(datosBasicosAdolecente.datosFormacionComplementaria.direcciones);
    return JSON.stringify(datosPractica);
  }

  getDirecciones(listaDirecciones): Array<any> {
    const direcciones: Array<any> = [];
    if (!listaDirecciones) return direcciones;
    listaDirecciones.forEach((direccion: any) => {
      direcciones.push({
        pais: direccion.pais,
        departamento: direccion.departamento,
        municipio: direccion.municipio,
        codigoPostal: direccion.codigoPostal,
        direccion: direccion.direccion,
        ciudad: direccion.ciudad
      });
    });
    return direcciones;
  }

  getConcatName(datosPractica){
    let nombreCompletoRepresentant = this.remitenteDTV.getNombreCompleto([datosPractica.datosPracticaAdolecente.datosBasicosAdolecente.datosFormacionComplementaria.primerNombreRepresentante,datosPractica.datosPracticaAdolecente.datosBasicosAdolecente.datosFormacionComplementaria.segundoNombreRepresentante,
      datosPractica.datosPracticaAdolecente.datosBasicosAdolecente.datosFormacionComplementaria.primerApellidoRepresentante,datosPractica.datosPracticaAdolecente.datosBasicosAdolecente.datosFormacionComplementaria.segundoApellidoRepresentante]);      
      datosPractica.datosPracticaAdolecente.datosBasicosAdolecente.datosFormacionComplementaria.nombreCompletoRepresentante = nombreCompletoRepresentant;
    let nombreCompletoMonito = this.remitenteDTV.getNombreCompleto([datosPractica.datosPracticaAdolecente.datosBasicosAdolecente.datosFormacionComplementaria.datosMonitor.primerNombre,datosPractica.datosPracticaAdolecente.datosBasicosAdolecente.datosFormacionComplementaria.datosMonitor.segundoNombre,
      datosPractica.datosPracticaAdolecente.datosBasicosAdolecente.datosFormacionComplementaria.datosMonitor.primerApellido,datosPractica.datosPracticaAdolecente.datosBasicosAdolecente.datosFormacionComplementaria.datosMonitor.segundoApellido]);
      datosPractica.datosPracticaAdolecente.datosBasicosAdolecente.datosFormacionComplementaria.datosMonitor.nombreCompletoMonitor = nombreCompletoMonito;
    let nombreCompletoRRL = this.remitenteDTV.getNombreCompleto([datosPractica.datosPracticaAdolecente.datosBasicosAdolecente.datosEscenarioPractica.primerNombreRL,datosPractica.datosPracticaAdolecente.datosBasicosAdolecente.datosEscenarioPractica.segundoNombreRL,
      datosPractica.datosPracticaAdolecente.datosBasicosAdolecente.datosEscenarioPractica.primerApellidoRL,datosPractica.datosPracticaAdolecente.datosBasicosAdolecente.datosEscenarioPractica.segundoApellidoRL]);
      datosPractica.datosPracticaAdolecente.datosBasicosAdolecente.datosEscenarioPractica.nombreCompletoRL = nombreCompletoRRL;      
    let nombreCompletoTut = this.remitenteDTV.getNombreCompleto([datosPractica.datosPracticaAdolecente.datosBasicosAdolecente.datosEscenarioPractica.primerNombreTutor,datosPractica.datosPracticaAdolecente.datosBasicosAdolecente.datosEscenarioPractica.segundoNombreTutor,
        datosPractica.datosPracticaAdolecente.datosBasicosAdolecente.datosEscenarioPractica.primerApellidoTutor,datosPractica.datosPracticaAdolecente.datosBasicosAdolecente.datosEscenarioPractica.segundoApellidoTutor]);
        datosPractica.datosPracticaAdolecente.datosBasicosAdolecente.datosEscenarioPractica.nombreCompletoTutor = nombreCompletoTut;
  }
}
