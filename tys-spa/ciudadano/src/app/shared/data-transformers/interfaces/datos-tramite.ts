import {ITramiteRemitenteForm} from './form/tramite-remitente-form';
import {ITramiteForm} from './form/tramite-form';
import {DocumentoTramite} from '../../../domain/documento-tramite';

export interface IDatosTramite {
  datosTramite?: ITramiteForm;
  datosRemitente?: ITramiteRemitenteForm;
  documentosTramite?: Array<DocumentoTramite>;
}
