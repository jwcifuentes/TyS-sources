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

import co.com.mintrabajo.tys.commons.domain.Notaria;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import co.com.mintrabajo.tys.mvc.util.URLSecuritySchema;
import co.com.mintrabajo.tys.persistence.dao.NotariasDAO;

@RestController
@RequestMapping(value = URLSecuritySchema.NOTARIAS_PUBLIC, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=*/*")

public class NotariasWebController {
	
	private static final Logger LOGGER = LogManager.getLogger(NotariasWebController.class);

	@Autowired
	private NotariasDAO notariasDTO;
	
	public NotariasWebController(){
		super();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> listarNotariasPorMunicipio(@PathVariable("id") final Long id) throws SystemException {
		LOGGER.info("listar notarias por municipio");
		List<Notaria> notarias =notariasDTO.listarNotariasPorMunicipio(id);
		return ResponseEntity.ok().body(notarias);
	}	
	
	
	
}
