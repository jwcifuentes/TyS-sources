package co.com.mintrabajo.tys.security.jwt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private static final Logger LOGGER = LogManager.getLogger(JwtAuthenticationSuccessHandler.class);
	
    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) {
        // We do not need to do anything extra on REST authentication success, because there is no page to redirect to
    	LOGGER.info("Autenticacion ejecutada con exito para ip {}", request.getRemoteAddr());
    }

}