package co.com.mintrabajo.tys.security.basic;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import co.com.mintrabajo.tys.commons.constants.SecurityConstants;

public class HTTPBasicAuthFilter implements Filter {

	@Autowired
	private HttpBasicAuthenticator authenticator;
	
	private static final Logger LOGGER = LogManager.getLogger(HTTPBasicAuthFilter.class);
	
	public HTTPBasicAuthFilter() {
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, filterConfig.getServletContext());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		String authorization = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
		
		if( !StringUtils.contains(authorization,  SecurityConstants.BASIC_HEADER) ) {
			LOGGER.error("La header de seguridad {} no ha sido enviada, ip asociada {}",HttpHeaders.AUTHORIZATION, httpRequest.getRemoteAddr() );
			authenticator.markError(httpRequest, httpResponse);
			return;
		}
		
		if( !authenticator.check(authorization, httpRequest, httpResponse) ) {
			LOGGER.error("La header de seguridad {} no es valida, ip asociada {}",HttpHeaders.AUTHORIZATION, httpRequest.getRemoteAddr() );
			authenticator.markError(httpRequest, httpResponse);
			return;
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
