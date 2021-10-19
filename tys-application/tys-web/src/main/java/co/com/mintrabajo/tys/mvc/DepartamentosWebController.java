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

import co.com.mintrabajo.tys.commons.domain.Departamento;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import co.com.mintrabajo.tys.mvc.util.URLSecuritySchema;
import co.com.mintrabajo.tys.persistence.dao.DepartamentosDAO;

@RestController
@RequestMapping(value = URLSecuritySchema.DEPATAMENTOS_PUBLIC, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=*/*")
public class DepartamentosWebController {
	
	private static final Logger LOGGER = LogManager.getLogger(DepartamentosWebController.class);
	
	@Autowired
	private  DepartamentosDAO departamentosDTO;
	
	public DepartamentosWebController(){
		super();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> listarDepartamentosPorPais(@PathVariable("id") final Long id) throws SystemException {
		LOGGER.info("listar departamentos por  paises");
		List<Departamento> departamentos =departamentosDTO.listarDepartaentossPorPais(id);
		return ResponseEntity.ok().body(departamentos);
	}	
	
}
