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

import co.com.mintrabajo.tys.commons.domain.TratamientoCortesia;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import co.com.mintrabajo.tys.mvc.util.URLSecuritySchema;
import co.com.mintrabajo.tys.persistence.dao.TratamientosCortesiaDAO;

@RestController
@RequestMapping(value = URLSecuritySchema.TRATAMIENTO_CORTESIA_PUBLIC, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=*/*")
public class TratamientoCortesiaWebController {
	
	private static final Logger LOGGER = LogManager.getLogger(TratamientoCortesiaWebController.class);
	
	@Autowired
	private TratamientosCortesiaDAO tratamientosCortesiaDTO;
	
	public TratamientoCortesiaWebController(){
		super();
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> listarDepartamentosPorPais() throws SystemException {
		LOGGER.info("listar tratamientos de cortesia");
		List<TratamientoCortesia> tratamientosCortesias = tratamientosCortesiaDTO.listar();
		return ResponseEntity.ok().body(tratamientosCortesias);
	}	
}
