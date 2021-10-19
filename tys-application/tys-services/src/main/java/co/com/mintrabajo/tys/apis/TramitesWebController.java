package co.com.mintrabajo.tys.apis;

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

import co.com.mintrabajo.tys.commons.domain.FiltrosTiposDocumentales;
import co.com.mintrabajo.tys.commons.domain.Tramite;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import co.com.mintrabajo.tys.persistence.dao.TipoDocumentalDAO;
import co.com.mintrabajo.tys.persistence.dao.TramitesDAO;

@RestController
@RequestMapping(value = "/tramites-api", produces = MediaType.APPLICATION_JSON_VALUE)
public class TramitesWebController {

	private static final Logger LOGGER = LogManager.getLogger(TramitesWebController.class);

	@Autowired
	private TramitesDAO tramitesDAO;

	@Autowired
	private TipoDocumentalDAO tipoDocumentalDAO;

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
	public ResponseEntity<?> getListTiposDocumentalesTramite(@PathVariable("id") final Long id) throws SystemException {
		LOGGER.info("consultando tipos documentales tramite por id {}", id);
		return ResponseEntity.ok().body(tipoDocumentalDAO.getListTipoDocumentalTramite(id));
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getListTipoDocumentalTramitesByFilter(@RequestBody final FiltrosTiposDocumentales filtros)
			throws SystemException {
		LOGGER.info("consultar tipo documental tramite con  filtros : {}", filtros);
		return ResponseEntity.ok(tipoDocumentalDAO.getListTipoDocumentalTramitesByFilter(filtros));
	}

}
