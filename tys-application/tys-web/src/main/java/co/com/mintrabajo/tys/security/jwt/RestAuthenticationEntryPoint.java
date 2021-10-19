package co.com.mintrabajo.tys.security.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint{

	private static final Logger LOGGER = LogManager.getLogger(RestAuthenticationEntryPoint.class);
	
	@Override
	public void commence(final HttpServletRequest request, final HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		LOGGER.info("Entry point ejecutado para ip {}", request.getRemoteAddr());
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	}

}
