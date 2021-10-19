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

import co.com.mintrabajo.tys.commons.domain.Municipio;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import co.com.mintrabajo.tys.mvc.util.URLSecuritySchema;
import co.com.mintrabajo.tys.persistence.dao.MunicipiosDAO;

@RestController
@RequestMapping(value = URLSecuritySchema.MUNICIPIOS_PUBLIC, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=*/*")
public class MunicipiosWebController {
	
	private static final Logger LOGGER = LogManager.getLogger(MunicipiosWebController.class);
	
	@Autowired
	private MunicipiosDAO municipiosDTO;

	public MunicipiosWebController(){
		super();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> listarMunicipiosPorDepart(@PathVariable("id") final Long id) throws SystemException {
		LOGGER.info("listar municipios por departamento");
		List<Municipio> municipios = municipiosDTO.listarMunicipiosPorDepart(id);
		return ResponseEntity.ok().body(municipios);
	}	
	
}
