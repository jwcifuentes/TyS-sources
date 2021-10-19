import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {ActivatedRoute, Router} from '@angular/router';
import {TramiteWcService} from '../../services/bussiness/tramite-wc.service';
import { DocumentoTramite } from '../../domain/documento-tramite';
import {DocumentoTramiteActualizar} from '../../domain/DocumentoTramiteActualizar';
import {assign} from 'rxjs/util/assign';
import {CommonModel} from '../radicar-tramites-servicios/util/common.model';
import {LoadingService} from '../../services/infrastructure/loading.service';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-actualizar-tramite',
  templateUrl: './actualizar-tramite.component.html'
})
export class ActualizarTramiteComponent extends CommonModel implements OnInit {

  documentos$: Observable<Array<DocumentoTramite>>;
  documentos: Array<DocumentoTramite>;
  documentosAdicionales: Array<DocumentoTramite> = [];
  nuevosDocumentosAdicionales: Array<DocumentoTramite> = [];

  data: {
    numeroRadicado: string;
    codigoVerificacion: string;
  } = {
    numeroRadicado: null,
    codigoVerificacion: null
  };

  tramite: any;
  documentNames: Array<string> = [];
  files: Array<any> = [];
  aditionalFiles: Array<any> = [];
  showSuccessUpdateDialog = false;

  constructor(public router: Router,
              private changeDetector: ChangeDetectorRef,
              private tramiteWcService: TramiteWcService,
              private loadingService: LoadingService,
              private route: ActivatedRoute,
              protected _translator: TranslateService) {
    super(_translator);
  }

  ngOnInit() {
    assign(this.data, this.route.snapshot.params);
    this.loadData();
  }

  backToConsultarTramites() {
    this.router.navigate(['consultar_tramites'], {queryParams: this.data});
  }

  loadData(showSuccesDialog = false) {
    this.tramiteWcService.consultarTramitesRadicados(this.data).subscribe(value => {
        this.tramite = value;
        this.documentos$ = this.tramiteWcService.obtenerDocumentosTramite(this.tramite);
        this.documentos$.subscribe(values => {
          this.documentos = values;
          this.showSuccessUpdateDialog = showSuccesDialog;
          console.log(values);
        });
      }
    );

    this.tramiteWcService.consultarDocumentosAdicionales(this.data.numeroRadicado).take(1).subscribe(values => {
      console.log(values);
      this.documentosAdicionales = values;
    });
  }

  showDocuemento(documento, i) {
    if (this.documentNames[i]) {
      const doc = {
        base64: this.files[i],
        nombreDocumento: this.documentNames[i]
      };
      this.visualizarDocumentos(doc);
    } else {
      this.tramiteWcService.obtenerByteDocumentosTramite(documento.idFilenet).subscribe(value => {
        this.visualizarDocumentos(value);
      });
    }
  }

  showDocumentoAdicional(documento, i) {
    if (documento.base64) {
      this.visualizarDocumentos(documento);
    } else {
      this.tramiteWcService.obtenerByteDocumentosTramite(documento.idFilenet).subscribe(value => {
        this.visualizarDocumentos(value);
      });
    }
  }

  removeDocumentoAdicional(index) {
    this.nuevosDocumentosAdicionales.splice(index, 1);
  }

  visualizarDocumentos(file) {
    if (file === undefined || file === null) {
      alert('Failed to open PDF.');
    } else { // Directly use base 64 encoded data for rest browsers (not IE)

      const downloadLink = document.createElement('a');
      downloadLink.target = '_blank';
      downloadLink.download = file.nombreDocumento;

      // convert downloaded data to a Blob
      // const blob = new Blob([file], {type: 'application/pdf'});

      // create an object URL from the Blob
      console.log('archivo entrante' +file.base64)
      const downloadUrl = file.base64;

      // set object URL as the anchor's href
      downloadLink.href = downloadUrl;

      // append the anchor to document body
      document.body.appendChild(downloadLink);

      // fire a click event on the anchor
      downloadLink.click();

      // cleanup: remove element and revoke object URL
      document.body.removeChild(downloadLink);
      URL.revokeObjectURL(downloadUrl);
    }
  }

  getBase64(file) {
    return new Promise((resolve, reject) => {
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => resolve(reader.result);
      reader.onerror = error => reject(error);
    });
  }

  onSelect(event, index, uploadInput) {
    if (!event.files[0]) return;

    const file = event.files[0];
    if (file.size > uploadInput.maxFileSize) {
      this.addWarning(`No es posible subir el archivo ${file.name}, ${uploadInput.invalidFileSizeMessageDetail}`);
      return;
    }

    if (file.type !== 'application/pdf') {
      this.addWarning(`No es posible subir el archivo ${file.name}, el formato es inválido.`);
      return;
    }

    this.changeDetector.detectChanges();
    this.documentNames[index] = event.files[0].name;
    this.getBase64(event.files[0]).then((binary: any) => {
      this.files[index] = binary;
    });
  }

  onAditionalDocumentSelected(event, uploadInput) {
    if (!event.files[0]) return;
    const file = event.files[0];
    if (file.size > uploadInput.maxFileSize) {
      this.addWarning(`No es posible subir el archivo ${file.name}, ${uploadInput.invalidFileSizeMessageDetail}`);
      return;
    }

    if (file.type !== 'application/pdf') {
      this.addWarning(`No es posible subir el archivo ${file.name}, el formato es inválido.`);
      return;
    }

    this.getBase64(event.files[0]).then((binary: any) => {

      this.nuevosDocumentosAdicionales.push({
        nombreDocumento: file.name,
        size: this.formatSize(file.size),
        base64: binary
      });

    });
  }

  onClear(event, index) {
    this.changeDetector.detectChanges();
  }

  customUploader(event, index) {
    return;
  }

  onUpload(event, index) {
    return;
  }

  getDataForUpdate(): Array<DocumentoTramiteActualizar> {
    const result: Array<DocumentoTramiteActualizar> = [];
    this.documentos.forEach((doc, index) => {
      if (this.documentNames[index]) {
        const res = new DocumentoTramiteActualizar();
        res.base64 = this.files[index];
        res.codigoDependencia = this.tramite.codDependencia;
        res.corPlantilla = doc.corPlantilla;
        res.idFilenet = doc.idFilenet;
        res.nombreDocumento = this.documentNames[index];
        res.nroRadicado = this.data.numeroRadicado;
        res.tramiteTipologia = doc.tramiteTipologia;
        res.idDocumento = doc.idDocumento;
        res.subclasificacion = doc.subclasificacion;
        res.idRegistraTramite = this.tramite.idTramiteRadicado;
        res.other = doc.other;
        result.push(res);
      }
    });
    return result;
  }
  onChangeSubclasificacion(documento, i) {
    this.documentNames[i] = documento.nombre;
    this.files[i] = undefined;
  }
  getAditionalDocumentDataUpdate(): Array<DocumentoTramiteActualizar> {
    const result: Array<DocumentoTramiteActualizar> = [];
    this.nuevosDocumentosAdicionales.forEach((doc, index) => {
      const res = new DocumentoTramiteActualizar();
      res.base64 = doc.base64;
      res.nombreDocumento = doc.nombreDocumento;
      res.nroRadicado = this.data.numeroRadicado;
      res.codigoDependencia = this.tramite.codDependencia;
      res.tramiteTipologia = 1;
      res.idRegistraTramite = this.tramite.idTramiteRadicado;
      res.idDocumento = -1;
      if (res.base64 != null) {
        result.push(res);
      }
    });
    return result;
  }

  update() {
    const data = {
      listDocumentoTramite: this.getDataForUpdate(),
      listDocumentoAdicionales: this.getAditionalDocumentDataUpdate()
    };
    if (data.listDocumentoAdicionales.length === 0 && data.listDocumentoTramite.length === 0) {
      this.addInfo('No hay nada para actualizar');
      return;
    }
    let message = '';
    this.documentos.forEach((doc, i) => {
      if (doc.other && !doc.subclasificacion) {
        message += '-Debe ingresar la subclasificacion del documento con nombre Otro.\n';
      }
      else if (this.shouldUpdateDocument(doc) && !this.files[i]) {
        message += `-Debe actualizar el documento con nombre ${doc.nombre}\n`;
      }
    });
    if (message) {
      this.addWarning(message);
      return;
    }
    console.log(data);
    this.loadingService.presentLoading();
    this.tramiteWcService.actualizarDocumentosTramite(data).catch(error => {
      console.error(error);
      this.loadingService.dismissLoading();
      return Observable.throw(error);
    }).subscribe(response => {
      this.loadingService.dismissLoading();
      this.addSucces('Los documentos se actualizaron con éxito');
      this.loadData(true);
    });
  }

  goToModuleSelection() {
    this.showSuccessUpdateDialog = false;
    this.router.navigate(['seleccion_modulos']);
  }
  shouldUpdateDocument(document): boolean {
    if(!document.estadoDoc) return false;
    const estados = [11337, 11336];
    const res = estados.indexOf(document.estadoDoc.id);
    return res !== -1;
  }
  formatSize(bytes): string {
    if (bytes === 0) {
      return '0 B';
    }
    const k = 1000, dm = 3, sizes = ['B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'],
      i = Math.floor(Math.log(bytes) / Math.log(k));
    return parseFloat((bytes / Math.pow(k, i)).toFixed(dm)) + ' ' + sizes[i];
  };


}
