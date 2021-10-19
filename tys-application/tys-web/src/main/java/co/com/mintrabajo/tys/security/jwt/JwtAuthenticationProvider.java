package co.com.mintrabajo.tys.security.jwt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import co.com.mintrabajo.tys.adapters.JwtProviderBroker;
import co.com.mintrabajo.tys.commons.domain.Usuario;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import co.com.mintrabajo.tys.commons.exceptions.WebSecurityException;
import co.com.mintrabajo.tys.security.model.JwtAuthenticationToken;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

	private static final Logger LOGGER = LogManager.getLogger(JwtAuthenticationProvider.class);
	
	@Autowired
	private JwtProviderBroker jwtProvider;

	@Override
	public boolean supports(Class<?> authentication) {
		return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
	}

	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
		try {

			if (authentication instanceof JwtAuthenticationToken) {
				
				JwtAuthenticationToken jwtAuth = (JwtAuthenticationToken) authentication;
				String token = jwtAuth.getToken();

				Usuario usuario = jwtProvider.checkAndParseToken(token);
				return new UsernamePasswordAuthenticationToken(usuario.getLogin(), usuario);
				
			} else {
				throw new InsufficientAuthenticationException("El objeto authentication no es del tipo esperado: " + authentication);
			}

		} catch (WebSecurityException e) {
			LOGGER.error("Error interno verificando token jwt: " + e.getMessage(),e);
			throw new AuthenticationServiceException(e.getMessage());
		} catch (SystemException e) {
			LOGGER.error("Error interno del sistema: " + e.getMessage(),e);
			throw new AuthenticationServiceException("Error interno del sistema",e);
		}
	}

}
