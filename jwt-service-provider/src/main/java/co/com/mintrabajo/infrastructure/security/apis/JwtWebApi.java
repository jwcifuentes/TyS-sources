package co.com.mintrabajo.infrastructure.security.apis;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.mintrabajo.infrastructure.security.domain.GenericResponse;
import co.com.mintrabajo.infrastructure.security.domain.TokenWrapper;
import co.com.mintrabajo.infrastructure.security.domain.Usuario;
import co.com.mintrabajo.infrastructure.security.exception.WebSecurityException;
import co.com.mintrabajo.infrastructure.security.jwt.JwtProvider;
import co.com.mintrabajo.infrastructure.security.jwt.KeyManager;

@RestController
@RequestMapping(value = "/jwt-api", produces = MediaType.APPLICATION_JSON_VALUE)
public class JwtWebApi {

	private static final Logger LOGGER = LogManager.getLogger(JwtWebApi.class);

	@Autowired
	private JwtProvider jwtProvider;
	@Autowired
	private KeyManager keyManager;

	public JwtWebApi() {
		super();
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> generateToken(@RequestBody final Usuario usuario) {

		LOGGER.info("Recibiendo peticion para generar token a usuario: {}", usuario.getLogin());
		String token = jwtProvider.generateToken(usuario);
		return ResponseEntity.ok(TokenWrapper.newInstance().token(token).build());
	}

	@RequestMapping(value = "/check", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> check(@RequestBody final TokenWrapper wrapper) {

		try {

			LOGGER.info("Recibiendo peticion para validar token");
			Usuario usuario = jwtProvider.checkAndParseToken(wrapper.getToken());
			LOGGER.info("El token es valido: {}", usuario);
			return ResponseEntity.ok(usuario);

		} catch (WebSecurityException e) {
			LOGGER.info("El token es invalido {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(GenericResponse.newInstance()
							.content(e.getMessage())
							.build());
		}
	}

	@RequestMapping(value = "/keys", method = RequestMethod.PUT)
	public ResponseEntity<?> generateKeys() {
		try {
			LOGGER.info("Recibiendo solicitud para generar llaves : {}", LocalDateTime.now());
			keyManager.generateKeys();
			return ResponseEntity.ok(GenericResponse.newInstance().content("Llaves generadas con exito").build());
		} catch (NoSuchAlgorithmException | IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(GenericResponse.newInstance().content("Error interno generando llaves: " + e.getMessage()).build());
		}
	}

}
