package co.com.mintrabajo.tys.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.com.mintrabajo.tys.commons.constants.SecurityConstants;
import co.com.mintrabajo.tys.commons.constants.TipoExcepcion;
import co.com.mintrabajo.tys.commons.domain.Fault;
import co.com.mintrabajo.tys.commons.util.JSONUtil;
import co.com.mintrabajo.tys.security.model.JwtAuthenticationToken;

public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
	
	private static final Logger LOGGER = LogManager.getLogger(JwtAuthenticationFilter.class);
	private static final String JWT_TOKEN_HEADER = "Token"+HttpHeaders.AUTHORIZATION;
	
	public JwtAuthenticationFilter(final String url) {
		super(url);
	}

	@Override
	protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
		return true;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException {

		String header = request.getHeader(JWT_TOKEN_HEADER);

		if ( !StringUtils.contains(header,  SecurityConstants.BEARER_HEADER) ) 
		{
			LOGGER.warn("No ha sido encontrado el JWT token en las headers, ip asociada {}", request.getRemoteAddr());
			handleUnAuthorizedResponse(response, "No ha sido encontrado el JWT token en las headers");
			return null;
		}

		String authToken = header.substring(7);
		JwtAuthenticationToken authRequest = new JwtAuthenticationToken(authToken);
		
		try {
			return getAuthenticationManager().authenticate(authRequest);
		} catch (AuthenticationException e) {
			LOGGER.warn("Error validando integridad del token: {}, ip asociada {}",e.getMessage(), request.getRemoteAddr());
			handleUnAuthorizedResponse(response, "Error validando integridad del token: " + e.getMessage());
			return null;
		}
	}
	
	private void handleUnAuthorizedResponse(final HttpServletResponse response, String message) throws JsonProcessingException, IOException {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		Fault fault = Fault.newInstance().tipo(TipoExcepcion.SYSTEM)
				.descripcion(message).build();
		response.getWriter().println(JSONUtil.marshal(fault));
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		super.successfulAuthentication(request, response, chain, authResult);
		chain.doFilter(request, response);
	}

}
