package co.com.mintrabajo.tys.mvc;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.mintrabajo.tys.commons.constants.EstadoTramite;
import co.com.mintrabajo.tys.commons.domain.Tramite;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import co.com.mintrabajo.tys.mvc.util.URLSecuritySchema;
import co.com.mintrabajo.tys.persistence.dao.TipoDocumentalDAO;
import co.com.mintrabajo.tys.persistence.dao.TramitesDAO;

@RestController
@RequestMapping(value =URLSecuritySchema.TRAMITES_SERVICIOS_PUBLIC, produces = MediaType.APPLICATION_JSON_VALUE)
public class TramitesServiciosWebController {
	
	private static final Logger LOGGER = LogManager.getLogger(TramitesServiciosWebController.class);

	@Autowired
	private TramitesDAO tramitesDAO;
	
	@Autowired
	private TipoDocumentalDAO tipoDocumentalDAO;
	
	public TramitesServiciosWebController(){
		super();
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> listarTramitesActivos() throws SystemException {		
		LOGGER.info("listar tramites activos ");
		List<Tramite> tramites = tramitesDAO.listar(EstadoTramite.ACTIVO.getId());
		return ResponseEntity.ok().body(tramites);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getListTiposDocumentalesTramite(@PathVariable("id") final Long id) throws SystemException {
		LOGGER.info("consultando tipos documentales activos tramite por id {}", id);
		return ResponseEntity.ok().body(tipoDocumentalDAO.getListTipoDocumentalTramiteActivos(id,EstadoTramite.ACTIVO.getId()));
	}
	

}
