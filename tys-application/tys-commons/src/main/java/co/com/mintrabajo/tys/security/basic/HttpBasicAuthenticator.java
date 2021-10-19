package co.com.mintrabajo.tys.security.basic;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import co.com.mintrabajo.tys.commons.constants.SecurityConstants;
import co.com.mintrabajo.tys.commons.constants.TipoExcepcion;
import co.com.mintrabajo.tys.commons.domain.Fault;
import co.com.mintrabajo.tys.commons.util.JSONUtil;

@Component
public class HttpBasicAuthenticator {

	private static final Logger LOGGER = LogManager.getLogger(HttpBasicAuthenticator.class);
	
	@Value( "${basic.authentication.user}" )
	private String applicationUser;
	
	@Value( "${basic.authentication.pass}" )
	private String applicationPass;
	
	public HttpBasicAuthenticator() {
		super();
	}

	public void markError(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
		
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		Fault fault = Fault.newInstance().tipo(TipoExcepcion.SYSTEM)
				.descripcion("Error de autenticacion de gateway, ip asociada " + request.getRemoteHost()).build();
		response.getWriter().println(JSONUtil.marshal(fault));
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
	}
	
	
	public boolean check(final String authorization,final HttpServletRequest request, final HttpServletResponse response) throws IOException {
		
		String credentials = new String(Base64.decodeBase64(authorization.substring(6)));
		if( !credentials.contains(SecurityConstants.SEPARATOR) ) {
			LOGGER.error("El header de seguridad no es consistente, ip asociada {}", request.getRemoteHost());
			return false;
		}
		
		String credentialParts[] = credentials.split(SecurityConstants.SEPARATOR);
		return credentialParts[SecurityConstants.USER_POS].equals(applicationUser) && credentialParts[SecurityConstants.PASS_POS].equals(applicationPass);
	}

}
