package co.com.mintrabajo.tys.mvc;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.mintrabajo.tys.commons.domain.Pais;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import co.com.mintrabajo.tys.mvc.util.URLSecuritySchema;
import co.com.mintrabajo.tys.persistence.dao.PaisDAO;

@RestController
@RequestMapping(value = URLSecuritySchema.PAISES_PUBLIC, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=*/*")
public class PaisWebController {
	
	private static final Logger LOGGER = LogManager.getLogger(PaisWebController.class);
	
	@Autowired
	private PaisDAO paisDAO;
	
	public PaisWebController(){
		super();
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> listarPaises() throws SystemException {
		LOGGER.info("listar paises");
		List<Pais> paises =paisDAO.listarPaises();
		return ResponseEntity.ok().body(paises);
	}	

}
