package co.com.mintrabajo.tys.mvc;

import java.util.List;

import co.com.mintrabajo.tys.commons.exceptions.BusinessException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.mintrabajo.tys.commons.domain.ActuaEnCalidad;
import co.com.mintrabajo.tys.commons.domain.CausalDespido;
import co.com.mintrabajo.tys.commons.domain.JustaCausa;
import co.com.mintrabajo.tys.commons.domain.Parametro;
import co.com.mintrabajo.tys.commons.domain.TipoIdentificacion;
import co.com.mintrabajo.tys.commons.domain.TipoPersona;
import co.com.mintrabajo.tys.commons.domain.TipoTelefonoCor;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import co.com.mintrabajo.tys.mvc.util.URLSecuritySchema;
import co.com.mintrabajo.tys.persistence.dao.ParametrosDAO;

/**
 * Created by jrodriguez on 07/11/2017.
 */

@RestController
@RequestMapping(value = URLSecuritySchema.PARAMETROS_PUBLIC, produces = MediaType.APPLICATION_JSON_VALUE)
public class ParametrosWebController {

	private static final Logger LOGGER = LogManager.getLogger(ParametrosWebController.class);

	@Autowired
	private ParametrosDAO constanteDAO;

	public ParametrosWebController() {
		super();
	}

	@RequestMapping(value = "/{codPadre}", method = RequestMethod.GET)
	public ResponseEntity<?> listarParametrosDelPadre(@PathVariable("codPadre") final String codPadre)
			throws SystemException {

		LOGGER.info("inicia procesamiento {}", codPadre);

		List<Parametro> constantes = constanteDAO
				.listarParametrosDelPadre(Parametro.newInstance().codigo(codPadre).estado(true).build());
		return ResponseEntity.ok().body(constantes);
	}

	@RequestMapping(value = "/TIP-IDENT", method = RequestMethod.GET)
	public ResponseEntity<?> listarTiposDocumentales() throws SystemException {

		LOGGER.info("inicia procesamiento {}", "TIP-IDENT");
		List<TipoIdentificacion> tiposIdentificaciones = constanteDAO.listarTiposIdentificacion();
		return ResponseEntity.ok().body(tiposIdentificaciones);
	}

	@RequestMapping(value = "NNA/TIP-IDENT", method = RequestMethod.GET)
	public ResponseEntity<?> listarTiposDocumentalesNNA() throws SystemException {

		LOGGER.info("inicia procesamiento {}", "TIP-IDENT");
		List<TipoIdentificacion> tiposIdentificaciones = constanteDAO.listarTiposIdentificacionNNA();
		return ResponseEntity.ok().body(tiposIdentificaciones);
	}


	@RequestMapping(value = "NNA-REPRESENTANTE/TIP-IDENT",method = RequestMethod.GET)
	public ResponseEntity<?> listarTiposDocumentoNNARepresentante() throws SystemException {
		LOGGER.info("inicia procesamiento {}", "NNA-REPRESENTANTE/TIP-IDENT");
		List<TipoIdentificacion> tiposIdentificaciones = constanteDAO.listarTiposIdentificacionNNARepresentante();
		return ResponseEntity.ok().body(tiposIdentificaciones);
	}
	
	@RequestMapping(value = "/TIP-PERS", method = RequestMethod.GET)
	public ResponseEntity<?> listarTiposPersonas() throws SystemException {

		LOGGER.info("inicia procesamiento {}", "TIP-PERS");
		List<TipoPersona> tiposPersonas = constanteDAO.listarTiposPersona();
		return ResponseEntity.ok().body(tiposPersonas);
	}

	@RequestMapping(value = "/TIP-ACTC", method = RequestMethod.GET)
	public ResponseEntity<?> listarActuaEnCalidad() throws SystemException {

		LOGGER.info("inicia procesamiento {}", "TIP-ACTC");
		List<ActuaEnCalidad> actuaEnCalidad = constanteDAO.listarActuaEnCalidad();
		return ResponseEntity.ok().body(actuaEnCalidad);
	}

	@RequestMapping(value = "/TIP-TELC", method = RequestMethod.GET)
	public ResponseEntity<?> listarTipoTelefonoCor() throws SystemException {

		LOGGER.info("inicia procesamiento {}", "TIP-TELC");
		List<TipoTelefonoCor> tipoTelefonoCors = constanteDAO.listarTipoTelefonoCor();
		return ResponseEntity.ok().body(tipoTelefonoCors);
	}

	@RequestMapping(value = "/justaCausa", method = RequestMethod.GET)
	public ResponseEntity<?> listarJustaCausa() throws SystemException {

		LOGGER.info("inicia procesamiento JustaCausa {}");

		List<JustaCausa> justasCausas = constanteDAO.listarCausas();
		return ResponseEntity.ok().body(justasCausas);
	}

	@RequestMapping(value = "/causaDespido", method = RequestMethod.GET)
	public ResponseEntity<?> listarCuasaDespido() throws SystemException {

		LOGGER.info("inicia procesamiento ListarDespido {}");

		List<CausalDespido> causaDespido = constanteDAO.listarCuasaDespido();
		return ResponseEntity.ok().body(causaDespido);
	}

	@RequestMapping(value = "/justaCausaCausalD/{idCausalDespido}", method = RequestMethod.GET)
	public ResponseEntity<?> listarJustaCausaCausalD(@PathVariable("idCausalDespido") int idCausalDespido) throws SystemException {

		LOGGER.info("inicia procesamiento ListarDespido {}");

		List<JustaCausa> justaCausaCausalD = constanteDAO.listarJustaCausaCausalD(idCausalDespido);	
		return ResponseEntity.ok().body(justaCausaCausalD);
	}

	@RequestMapping(value = "/actividad-economica/{divisionGrupo}",method = RequestMethod.GET)
	public ResponseEntity<?> listarActividadEconomica(@PathVariable String divisionGrupo) throws BusinessException, SystemException {
		LOGGER.info("inicia procesamiento actividad economica{}",divisionGrupo);
		return ResponseEntity.ok(constanteDAO.listaActividadEconomica(divisionGrupo));
	}

}
