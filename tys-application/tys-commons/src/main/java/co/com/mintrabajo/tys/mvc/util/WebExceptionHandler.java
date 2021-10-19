package co.com.mintrabajo.tys.mvc.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import co.com.mintrabajo.tys.commons.constants.TipoExcepcion;
import co.com.mintrabajo.tys.commons.domain.Fault;
import co.com.mintrabajo.tys.commons.exceptions.BusinessException;
import co.com.mintrabajo.tys.commons.exceptions.NFRException;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;

@ControllerAdvice("co.com.mintrabajo.tys.mvc")
public class WebExceptionHandler {
	
	private static final Logger LOGGER = LogManager.getLogger(WebExceptionHandler.class);

	@ExceptionHandler({ BusinessException.class, SystemException.class, Exception.class })
	protected ResponseEntity<?> handle(Exception ex, WebRequest request) {
		LOGGER.error("procesando mensaje de error para canal http: {}", ex.getMessage());

		if (ex instanceof BusinessException) {
			Fault fault = Fault.newInstance().tipo(TipoExcepcion.BUSINESS).descripcion(ex.getMessage()).build();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(fault);
		}

		if (ex instanceof SystemException || checkFrameworkExceptions(ex)) {
			Fault fault = Fault.newInstance().tipo(TipoExcepcion.SYSTEM)
					.descripcion("Un error interno en el sistema ha ocurrido").build();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(fault);
		}
		
		if (ex instanceof NFRException) {
			Fault fault = Fault.newInstance().tipo(TipoExcepcion.SYSTEM)
					.descripcion("Un error interno en el sistema ha ocurrido").build();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(fault);
		}
		

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
				.body(Fault.newInstance().tipo(TipoExcepcion.SYSTEM)
						.descripcion("Un error interno no manejado en el sistema ha ocurrido").build());
	}
	
	//check spring runtime exceptions if it's needed
	private boolean checkFrameworkExceptions(final Exception exception) {
		return exception instanceof MethodArgumentTypeMismatchException;
	}

}
 