package co.com.mintrabajo.tys.boundary;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import co.com.mintrabajo.tys.commons.domain.Tramite;
import co.com.mintrabajo.tys.commons.exceptions.BusinessException;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import co.com.mintrabajo.tys.persistence.dao.TramitesDAO;

@Component
public class ComplementarInfSolicitud {

	private static final Logger LOGGER = LogManager.getLogger(ComplementarInfSolicitud.class);

	@Autowired
	private TramitesDAO tramitesDAO;

	private ComplementarInfSolicitud() {

	}

	public Tramite consultaInfTramite(Long id) throws BusinessException, SystemException {
		try {
			Tramite tramite = tramitesDAO.getTramite(id);
			if (tramite == null) {
				LOGGER.error("El tramite solicitado no ha sido encontrado ");
				throw new BusinessException("El tramite solicitado no ha sido encontrado");
			}
			return tramite;

		} catch (DataAccessException e) {
			throw new SystemException("Excepcion consultando tramite por id: " + e.getMessage(), e);
		}
	}

}
