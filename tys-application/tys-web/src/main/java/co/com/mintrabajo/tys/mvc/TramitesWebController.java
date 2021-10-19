package co.com.mintrabajo.tys.mvc;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.mintrabajo.tys.boundary.CreadorTramites;
import co.com.mintrabajo.tys.commons.constants.FlujoAdminTramite;
import co.com.mintrabajo.tys.commons.domain.Tramite;
import co.com.mintrabajo.tys.commons.exceptions.BusinessException;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import co.com.mintrabajo.tys.mvc.util.URLSecuritySchema;
import co.com.mintrabajo.tys.persistence.dao.TramitesDAO;

/**
 * Created by jrodriguez on 06/11/2017.
 */

@RestController
@RequestMapping(value = URLSecuritySchema.TRAMITES_ADMIN, produces = MediaType.APPLICATION_JSON_VALUE)
public class TramitesWebController {

	private static final Logger LOGGER = LogManager.getLogger(TramitesWebController.class);

	@Autowired
	private TramitesDAO tramitesDAO;

	@Autowired
	private CreadorTramites creadorTramites;

	public TramitesWebController() {
		super();
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> listar() throws SystemException {

		LOGGER.info("listar tramites");
		List<Tramite> tramites = tramitesDAO.listar();
		return ResponseEntity.ok().body(tramites);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getTramite(@PathVariable("id") final Long id) throws SystemException, BusinessException {

		LOGGER.info("consultando tramite por id {}", id);
		return ResponseEntity.ok().body(tramitesDAO.getTramite(id));
	}

	@RequestMapping(value = "/{flujo}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> crear(@PathVariable("flujo") final FlujoAdminTramite flujo,
			@RequestBody final Tramite tramite) throws SystemException, BusinessException {

		LOGGER.info("crear tramite con flujo {} : {}", flujo, tramite);
		creadorTramites.procesar(tramite, flujo);
		return ResponseEntity.ok().build();
	}

	@RequestMapping(value = "/parametrizacion/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> cambiarParametrizacionTramite(@PathVariable("id") final Long id)
			throws SystemException, BusinessException {
		LOGGER.info("cambiar parametrizacion tramite por id {}", id);
		tramitesDAO.cambiarParametrizacionTramite(id);
		return ResponseEntity.ok().build();
	}

}
