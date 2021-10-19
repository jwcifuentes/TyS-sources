package co.com.mintrabajo.tys.boundary;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.mintrabajo.tys.commons.constants.FlujoAdminTramite;
import co.com.mintrabajo.tys.commons.domain.TipoDocumentalTramite;
import co.com.mintrabajo.tys.commons.exceptions.BusinessException;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import co.com.mintrabajo.tys.persistence.dao.TipoDocumentalDAO;

@Component
public class CrearTiposDocumentalesTramites {

	private static final Logger LOGGER = LogManager.getLogger(CrearTiposDocumentalesTramites.class);

	@Autowired
	private TipoDocumentalDAO tipoDocumentalDAO;

	public CrearTiposDocumentalesTramites() {

	};

	public void procesar(final TipoDocumentalTramite tipoDocumentalTramite, final FlujoAdminTramite flujo)
			throws SystemException, BusinessException {

		LOGGER.info("procesando tipo documental tramite  con flujo {} : {}", flujo, tipoDocumentalTramite);

		switch (flujo) {

		case CREACION_BASE:
			LOGGER.info("crear objeto tipo documental tramite {}", tipoDocumentalTramite);
			tipoDocumentalTramite.setFechaCreacion(new Date());
			tipoDocumentalDAO.crear(tipoDocumentalTramite);
			break;

		case AJUSTE_DETALLES:
			LOGGER.info("actualizando objeto tipo documental tramite {}", tipoDocumentalTramite);
			tipoDocumentalTramite.setFechaCambio(new Date());
			tipoDocumentalDAO.actualizar(tipoDocumentalTramite);
			break;
		}
	}

}
