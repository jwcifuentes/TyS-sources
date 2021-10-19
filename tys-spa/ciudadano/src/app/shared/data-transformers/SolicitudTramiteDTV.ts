import {IDatosTramite} from './interfaces/datos-tramite';
import {DatosSolicitudTramite} from 'app/domain/datos-solicitud-tramite';
import {CorrespondenciaTramite} from 'app/domain/correspondencia-tramite';
import {DatosEmpresa} from 'app/domain/datos-empresa';
import {RemitenteDTV} from './RemitenteDTV';
import {TramiteDTV} from './tramiteDTV';
import {DatosCondicionVinculacion} from 'app/domain/datos-condicion-vinculacion';
import {ITramiteForm} from './interfaces/form/tramite-form';

export class SolicitudTramiteDTV {

  private remitenteDTV: RemitenteDTV;
  private tramiteDTV: TramiteDTV;
  private documentosDTV: any;

  constructor(private source: IDatosTramite) {
    this.remitenteDTV = new RemitenteDTV(this.source.datosRemitente);
    this.tramiteDTV = new TramiteDTV(this.source.datosTramite);
  }

  getDatosSolicitudTramite(): DatosSolicitudTramite {
    const dtv = <DatosSolicitudTramite> {
      correspondencia: this.getCorrespondenciaTramite(),
      solicitudTramite: this.getDatosTramite(),
      empresa: this.getDatosEmpresa(),
      listDocumentos: this.getListaDocumentos(),
      listaJustaCausa: this.tramiteDTV.source.listaJustaCausa,
      listaOrganizacionesSindicales: this.tramiteDTV.getOrganizacionesSindicales(),
      datosFormacion: this.tramiteDTV.getDatosFormacion(),
      datosEscenarioPractica: this.tramiteDTV.getDatosEscenarioPractica(),
      datosVinculacion: this.tramiteDTV.getDatosVinculacionPracticas(),
      strJsonDatosBasicosPdf: this.tramiteDTV.source.strJsonDatosBasicosPdf
    };

    if (this.tramiteDTV.source.autorizacionTrabajoAdolescente)
      dtv.condicionVinculacion = this.getDatosCondicionVinculacion();

    return dtv;
  }
  getDatosAdolecente() {
    return {
      solicitudTramite: this.getDatosTramite(),
      empresa: this.getDatosEmpresa(),
      datosFormacion: this.tramiteDTV.getDatosFormacion(),
      datosEscenarioPractica: this.tramiteDTV.getDatosEscenarioPractica(),
      datosVinculacion: this.tramiteDTV.getDatosVinculacionPracticas()
    };
  }

  getCorrespondenciaTramite(): CorrespondenciaTramite {
    const result = this.remitenteDTV.getRemitente();
    result.listaDireccionTerritoriales = this.tramiteDTV.getDireccionesTerritoriales();
    result.referidos = this.tramiteDTV.getRadicadosReferidos();

    return result;
  }

  getDatosTramite() {
    return this.tramiteDTV.getTramite();
  }

  getDatosEmpresa(): DatosEmpresa {
    const datosEmpresa = this.tramiteDTV.getDatosEmpresa();
    const empresa = datosEmpresa.empresa;
    empresa.autorizacionNotificacionElectronica = this.source.datosRemitente.autorizaActosAdministrativosElectrinico;
    empresa.idNotificacionElectronica = this.source.datosRemitente.autorizaEnvioViaCorreoElectrinico;
    datosEmpresa.listaPersonas = [...datosEmpresa.listaPersonas, ...this.remitenteDTV.getRepresentanteLegal()];
    empresa.idTipoEmpleador = this.remitenteDTV.source.tipoEmpleador ? this.remitenteDTV.source.tipoEmpleador.id : null;
    empresa.idTipoEmpresa = this.remitenteDTV.source.idTipoEmpresa && this.remitenteDTV.source.idTipoEmpresa.id;
    empresa.idCIIU = this.remitenteDTV.source.codigoActividad && this.remitenteDTV.source.codigoActividad.id;
    empresa.nit = this.remitenteDTV.source.nit;
    empresa.tieneConveniosColectivos  = this.tramiteDTV.source.tienePactos ? this.tramiteDTV.source.tienePactos.value : false;
    empresa.tieneReglamentoTrabajo = this.tramiteDTV.source.tieneReglamento ? this.tramiteDTV.source.tieneReglamento.value : false;
    return datosEmpresa;
  }

  getDatosCondicionVinculacion(): DatosCondicionVinculacion {
    const direcciones = this.tramiteDTV.getDireccionesVinculacion();
    return {
      horarioLaboral: this.tramiteDTV.getHorarioLaboralNNA(),
      direccionVinculo: direcciones ? direcciones[0] : null,
      condicionVinculacion: this.tramiteDTV.getCondicionVinculacion()
    };
  }

  getListaDocumentos() {
    return this.source.documentosTramite;
  }
}
