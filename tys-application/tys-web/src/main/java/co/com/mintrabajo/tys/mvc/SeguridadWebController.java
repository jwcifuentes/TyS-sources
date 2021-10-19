package co.com.mintrabajo.tys.mvc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.mintrabajo.tys.adapters.JwtProviderBroker;
import co.com.mintrabajo.tys.boundary.LDAPSecurityAuthenticator;
import co.com.mintrabajo.tys.commons.autentication.AutenticationResponseContext;
import co.com.mintrabajo.tys.commons.domain.GenericResponse;
import co.com.mintrabajo.tys.commons.domain.TokenWrapper;
import co.com.mintrabajo.tys.commons.domain.Usuario;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import co.com.mintrabajo.tys.mvc.util.URLSecuritySchema;

@RestController
@RequestMapping(value = URLSecuritySchema.SEGURIDAD_PUBLIC, produces = MediaType.APPLICATION_JSON_VALUE)
public class SeguridadWebController {

	private static final Logger LOGGER = LogManager.getLogger(SeguridadWebController.class);

	@Autowired
	private JwtProviderBroker jwtProvider;

	@Autowired
	private LDAPSecurityAuthenticator security;

	public SeguridadWebController() {
		super();
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> login(@RequestBody final Usuario usuario) throws SystemException {

		// Authenticate the user using the credentials provided
		LOGGER.info("verificando credenciales de usuario");

			AutenticationResponseContext responseLogin = security.login(usuario.getLogin(), usuario.getPassword());

			if (responseLogin.isSuccessful()) {
				TokenWrapper tokenWrapper = TokenWrapper.newInstance().token(jwtProvider.generateToken(usuario))
						.build();
				Usuario ldapRecord = Usuario.newInstance().nombres(responseLogin.getPrincipalContext().getFirstName())
						.login(responseLogin.getPrincipalContext().getUsername()).role("admin").build();

				return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.AUTHORIZATION, tokenWrapper.getToken())
						.body(ldapRecord);
			} else {

				GenericResponse<?> response = GenericResponse.newInstance()
						.content("El usuario o password proveido es invalido").build();

				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
			}
	}

}
