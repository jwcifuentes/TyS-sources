import {ChangeDetectorRef, Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {TipoDocumentalTramite} from '../../../domain/tipo-documental-tramite';
import {Http} from '@angular/http';
import {FormControl} from '@angular/forms';
import {Subject} from 'rxjs/Subject';
import { CommonModel } from 'app/ui-components/radicar-tramites-servicios/util/common.model';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-documentos-tramite',
  templateUrl: './documentos-tramite.component.html',
  styleUrls: ['./documentos-tramite.component.css']
})
export class DocumentosTramiteComponent implements OnInit {
  commonModel: CommonModel;

  documentos: Array<TipoDocumentalTramite> = [];
  files: Array<any> = [];
  documentNames: Array<string> = [];
  requiredDocumentsCount: number;
  stateValid: boolean;

  @Input() sharedData: Subject<any>;

  @Input()
  tipoTramiteControl: FormControl;

  @Input() editable = true;
  @Input() tipoDocumentos: any;

  @Output() onSelectDocument: EventEmitter<any> = new EventEmitter();
  @Output() onStateChange: EventEmitter<any> = new EventEmitter();

  constructor(private http: Http,
              private changeDetector: ChangeDetectorRef,
              protected _translator: TranslateService) {
      this.commonModel = new CommonModel(_translator);
  }

  ngOnInit() {

  }

  addDocumentos(ruleDocs?) {
    alert('En implementación activa');
  }

  visualizarDocumentos(index) {
    const file = this.files[index];
    if (file === undefined || file === null) {
      alert('Failed to open PDF.');
    } else { // Directly use base 64 encoded data for rest browsers (not IE)

      const downloadLink = document.createElement('a');
      downloadLink.target = '_blank';
      downloadLink.download = this.documentos[index].nombre + '.pdf';

      // convert downloaded data to a Blob
      // const blob = new Blob([file], {type: 'application/pdf'});

      // create an object URL from the Blob
      const downloadUrl = file;

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
    //debugger;
    return new Promise((resolve, reject) => {
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => resolve(reader.result);
      reader.onerror = error => reject(error);
    });
  }
//eliminar archivo con click
  customUploader(event, index,form) {
    this.files.splice(index,1);
    form.clear();
    return;
  }

  onUpload(event, index) {
    return;
  }

  onClear(event, index) {
    this.changeDetector.detectChanges();
    // this.status = UploadStatus.CLEAN;
  }

  onSelect(event, index, uploadInput) {
    this.changeDetector.detectChanges();
    if(!event.files[0]) return;

    const file = event.files[0];
    if(file.size > uploadInput.maxFileSize) {
      this.commonModel.addWarning(`No es posible subir el archivo ${file.name}, ${uploadInput.invalidFileSizeMessageDetail}`);
      return;
    }
    //console.log(file.type);
    if(file.type != 'application/pdf'){
      this.commonModel.addWarning(`No se puede cargar el archivo ${file.name}, debe ser un archivo PDF.`);
      return;
    }

    this.changeDetector.detectChanges();
    this.documentNames[index] = file.name;
    this.getBase64(file).then((binary: any) => {
      this.files[index] = binary;
      let countRequired = 0;
      for (const key in this.files) {
        if (this.documentos[key].esRequerido) {
          countRequired++;
        }
      }
      this.stateValid = countRequired === this.requiredDocumentsCount;
    });
  }

  setDocumentos(documentos: Array<TipoDocumentalTramite>) {
    this.documentos = documentos;
    this.documentos = documentos.filter((doc: TipoDocumentalTramite) => doc.estado == true);
    this.requiredDocumentsCount = this.documentos.filter((doc: TipoDocumentalTramite) => doc.esRequerido).length;
    this.stateValid = documentos.length === 0;
  }

  getDocumentosTramiteForm() {
    const docs = [];
    this.documentos.forEach((doc, index) => {
      docs.push({
        base64: this.files[index],
        tramiteTipologia: doc.idTipoDocumental,
        nombreDocumento: this.documentNames[index],
        idDocumento: doc.id
      });
    });
    return docs;
  }
}
