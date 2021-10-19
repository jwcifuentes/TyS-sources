package co.com.mintrabajo.tys.boundary;

import co.com.mintrabajo.tys.adapters.TramitesECMBroker;
import co.com.mintrabajo.tys.commons.domain.DocumentoTramite;
import co.com.mintrabajo.tys.commons.domain.messages.ObtenerDocumentoPorIdFilenetRequest;
import co.com.mintrabajo.tys.commons.domain.messages.ObtenerDocumentoPorIdFilenetResponse;
import co.com.mintrabajo.tys.commons.exceptions.BusinessException;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ObtenerDocumentoPorIdFilenet {

    private static final Logger LOGGER = LogManager.getLogger(ObtenerDocumentoPorIdFilenet.class);

    @Autowired
    private TramitesECMBroker tramitesECMBroker;

    public ObtenerDocumentoPorIdFilenet() {
    }

	public DocumentoTramite procesar(final String idFilenet) throws SystemException, BusinessException {

        LOGGER.info("Iniciando obtencion de documento");

        ObtenerDocumentoPorIdFilenetRequest request = ObtenerDocumentoPorIdFilenetRequest.newInstance()
                .strIdFilenet(idFilenet).build();

        ObtenerDocumentoPorIdFilenetResponse rtaTramite = tramitesECMBroker.obtenerDocumentoPorIdFilenet(request);

        return DocumentoTramite.newInstance().base64(rtaTramite.getBase64()).nombreDocumento(rtaTramite.getFileName()).build();
    }
}
