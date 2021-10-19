package co.com.mintrabajo.tys.boundary;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.mintrabajo.tys.commons.constants.FlujoAdminTramite;
import co.com.mintrabajo.tys.commons.domain.Tramite;
import co.com.mintrabajo.tys.commons.exceptions.BusinessException;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import co.com.mintrabajo.tys.persistence.dao.TramitesDAO;

@Component
public class CreadorTramites {

	private static final Logger LOGGER = LogManager.getLogger(CreadorTramites.class);

	@Autowired
	private TramitesDAO tramitesDAO;
	
	public CreadorTramites(){
	}

	public void procesar(final Tramite tramite, final FlujoAdminTramite flujo)
			throws SystemException, BusinessException {

		LOGGER.info("procesando tramite con flujo {} : {}", flujo, tramite);

		switch (flujo) {

		case CREACION_BASE:
			LOGGER.info("crear objeto tramite {}", tramite);
			tramite.setFechaCreacion(new Date());
			tramite.setPermiteActualizacion(true);
			tramitesDAO.crear(tramite);
			break;

		case AJUSTE_DETALLES:
			LOGGER.info("actualizando objeto tramite {}", tramite);
			tramite.setFechaCambio(new Date());
			tramite.setPermiteActualizacion(true);
			tramitesDAO.actualizar(tramite);
			break;
		}
	}

}
