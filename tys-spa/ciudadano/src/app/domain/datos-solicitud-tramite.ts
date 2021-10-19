import {CorrespondenciaTramite} from './correspondencia-tramite';
import {SolicitudTramite} from './solicitud-tramite';
import {DatosEmpresa} from './datos-empresa';
import {DatosCondicionVinculacion} from './datos-condicion-vinculacion';
import {DocumentoTramite} from './documento-tramite';
import {DatosFormacionAdolecente} from './datosFormacionAdolecente';
import {DatosEscenarioPractica} from './datosEscenarioPractica';
import {DatosVinculacionPracticasAdolecente} from './datosVinculacionPracticasAdolecente';

export interface DatosSolicitudTramite {
  correspondencia: CorrespondenciaTramite;
  solicitudTramite: SolicitudTramite;
  empresa: DatosEmpresa;
  condicionVinculacion?: DatosCondicionVinculacion;
  listDocumentos: Array<DocumentoTramite>;
  listaJustaCausa: Array<any>;
  datosFormacion: DatosFormacionAdolecente;
  datosEscenarioPractica: DatosEscenarioPractica;
  datosVinculacion: DatosVinculacionPracticasAdolecente;
  listaOrganizacionesSindicales: Array<any>;
  strJsonDatosBasicosPdf: string;
}
