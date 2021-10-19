package co.com.mintrabajo.tys.boundary;

import java.util.ArrayList;
import java.util.List;

import co.com.mintrabajo.tys.commons.domain.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.mintrabajo.tys.adapters.TramitesECMBroker;
import co.com.mintrabajo.tys.commons.domain.messages.ObtenerDatosDocumentoRequest;
import co.com.mintrabajo.tys.commons.domain.messages.ObtenerDatosDocumentoResponse;
import co.com.mintrabajo.tys.commons.exceptions.BusinessException;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import co.com.mintrabajo.tys.persistence.dao.TipoDocumentalDAO;

@Component
public class ObtenerDocumentosTramites {

	private static final Logger LOGGER = LogManager.getLogger(ObtenerDocumentosTramites.class);

	@Autowired
	private TipoDocumentalDAO tipoDocumentalDAO;

	@Autowired
	private TramitesECMBroker tramitesECMBroker;

	public ObtenerDocumentosTramites() {
	}

	public List<DocumentoTramite> procesar(final String nroRadicado) throws SystemException, BusinessException {

		LOGGER.info("Iniciando obtencion de documentos");

		ObtenerDatosDocumentoRequest request = ObtenerDatosDocumentoRequest.newInstance()
				.strNroRadicado(nroRadicado)
				.build();

		ObtenerDatosDocumentoResponse rtaTramite = tramitesECMBroker.obtenerDatosDocumento(request);

		List<DocumentoTramite> documentosOut = rtaTramite.getDocumentosOut();

		for (DocumentoTramite documentoTramite : documentosOut) {
			TipoDocumental tipoDocumental = tipoDocumentalDAO.getTipoDocumentalById(Long.valueOf(String.valueOf(documentoTramite.getTramiteTipologia())));
			if(tipoDocumental  != null ){
				documentoTramite.setNombreDocumento(tipoDocumental.getNombre());
			}
		}

		return documentosOut;

	}


	public List<DocumentosTramiteYServicio> listarDocumentosPorTramite(final FiltrosTiposDocumentales filtros) throws SystemException{

		LOGGER.info("Consultar documentos relacionados con el tramite");

		List<DocumentosTramiteYServicio> listDocumentosTramiteYServicios =new ArrayList<DocumentosTramiteYServicio>();
		List<TipoDocumentalTramite> listTipoDocumentalTramites =new ArrayList<TipoDocumentalTramite>();
		List<DocumentoTramite>  documentoTramites =new ArrayList<DocumentoTramite>();


		try {
			LOGGER.info("Consultar documentos  cargados al tramite");
			listTipoDocumentalTramites.addAll(tipoDocumentalDAO.getListTipoDocumentalTramitesByFilter(filtros));
			documentoTramites.addAll(procesar(filtros.getNumeroRadicado()));

			for (TipoDocumentalTramite tipoDocumentalTramite : listTipoDocumentalTramites) {
				DocumentosTramiteYServicio documentosTramiteYServicio=	DocumentosTramiteYServicio.newInstance()
						.obligatorio(tipoDocumentalTramite.getEsRequerido())
				.nombre(tipoDocumentalTramite.getNombre())
				.descripcion(tipoDocumentalTramite.getDescripcion())
				.tramiteTipologia(tipoDocumentalTramite.getId())
				.idTipologia(tipoDocumentalTramite.getIdTipoDocumental())
				.nroRadicado(filtros.getNumeroRadicado())
				.build();
				for (DocumentoTramite documentoTramite : documentoTramites) {
					if(Long.valueOf(documentoTramite.getTramiteTipologia()).equals(documentosTramiteYServicio.getTramiteTipologia())){
						documentosTramiteYServicio.setIdFilenet(documentoTramite.getIdFilenet());
						documentosTramiteYServicio.setIdDocumento((long) documentoTramite.getIdDocumento());
						//CONSULTAR EL ESTADO DEL DOCUMENTO Y AGREGARLO A documentosTramiteYServicio
						tipoDocumentalDAO.setEstadoDocAndSubclasificacionPpdDocumento(documentosTramiteYServicio);
					}
				}
				listDocumentosTramiteYServicios.add(documentosTramiteYServicio);
			}
			//Consultar y añadir los nuevos documentos de tipo otro (PPD_DOCUMENTO_NO_CARGADO)
			listDocumentosTramiteYServicios.addAll(tipoDocumentalDAO.getDocumentosOtros(filtros.getNumeroRadicado()));

		return listDocumentosTramiteYServicios;

		} catch (BusinessException e) {
			LOGGER.info("Error consultar documentos relacionados con el tramite",e.getMessage());
			throw new SystemException("Error consultar documentos relacionados con el tramite");
		}
	}


	public List<DatosDocumentoAdicional> listaDocumentosAdicionalesPorTramite() throws BusinessException, SystemException{



		return null;
	}










}
