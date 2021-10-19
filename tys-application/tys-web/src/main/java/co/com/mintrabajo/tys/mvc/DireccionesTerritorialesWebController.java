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

import co.com.mintrabajo.tys.commons.domain.DireccionTerritorial;
import co.com.mintrabajo.tys.commons.exceptions.BusinessException;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import co.com.mintrabajo.tys.mvc.util.URLSecuritySchema;
import co.com.mintrabajo.tys.persistence.dao.DireccionesTerritorialesDAO;

@RestController
@RequestMapping(value = URLSecuritySchema.DIRECCIONES_TERRITORIALES_PUBLIC, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=*/*")

public class DireccionesTerritorialesWebController {
	
	private static final Logger LOGGER = LogManager.getLogger(DireccionesTerritorialesWebController.class);
	
	@Autowired
	private DireccionesTerritorialesDAO direccionesTerritorialesDTO;
	
	public DireccionesTerritorialesWebController(){
		super();
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> listar() throws SystemException {
		LOGGER.info("listar direcciones territoriales");
		List<DireccionTerritorial> direccionesTerritoriales =direccionesTerritorialesDTO.listar();
		return ResponseEntity.ok().body(direccionesTerritoriales);
	}
	
	@RequestMapping(value = "/{idDireccionTerritoral}", method = RequestMethod.GET)
	public ResponseEntity<?> getDireccionTerritoralPaisDepar(@PathVariable("idDireccionTerritoral") Long idDireccionTerritoral) throws BusinessException, SystemException {
		LOGGER.info("pais y departamento direcciones territoriales");
		return ResponseEntity.ok().body(direccionesTerritorialesDTO.getDireccionTerritoralPaisDepar(idDireccionTerritoral));
	}

}
