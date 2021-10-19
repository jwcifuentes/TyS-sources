package co.com.mintrabajo.tys.boundary;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.mintrabajo.tys.adapters.TramitesECMBroker;
import co.com.mintrabajo.tys.commons.domain.DocumentoTramite;
import co.com.mintrabajo.tys.commons.domain.messages.SubirDocumentoActualizarRequest;
import co.com.mintrabajo.tys.commons.exceptions.BusinessException;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;

@Component
public class SubirDocumentoTramites {

	private static final Logger LOGGER = LogManager.getLogger(SubirDocumentoTramites.class);

	@Autowired
	private TramitesECMBroker tramitesECMBroker;

	public SubirDocumentoTramites() {
	}

	public void procesar(final DocumentoTramite documento) throws SystemException, BusinessException {

		LOGGER.info("Iniciando subida de documento");

		SubirDocumentoActualizarRequest request = SubirDocumentoActualizarRequest.newInstance()
				.intIdDocumentoIn(documento.getIdDocumento()).intTramiteTipologiaIn(documento.getTramiteTipologia())
				.strBase64In(documento.getBase64()).strCodigoDependenciaIn(documento.getCodigoDependencia())
				.strCorPlantillaIn(documento.getCorPlantilla()).strNombreDocumentoIn(documento.getNombreDocumento())
				.strIdFilenetIn(documento.getIdFilenet()).strNroRadicadoIn(documento.getNroRadicado()).build();

		tramitesECMBroker.subirDocumentoActualizar(request);

	}
}
