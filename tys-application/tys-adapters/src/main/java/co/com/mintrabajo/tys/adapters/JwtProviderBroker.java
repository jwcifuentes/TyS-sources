package co.com.mintrabajo.tys.adapters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import co.com.mintrabajo.tys.commons.domain.GenericResponse;
import co.com.mintrabajo.tys.commons.domain.TokenWrapper;
import co.com.mintrabajo.tys.commons.domain.Usuario;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import co.com.mintrabajo.tys.commons.exceptions.WebSecurityException;
import co.com.mintrabajo.tys.commons.util.JSONUtil;

@Component
public class JwtProviderBroker {


	private static final Logger LOGGER = LogManager.getLogger(JwtProviderBroker.class);

	private final RestTemplate restTemplate;
	
	@Value("${providers.jwt.service.endpoint}")
	private String endpoint;

	public JwtProviderBroker() {
		restTemplate = new RestTemplate();
	}

	public Usuario checkAndParseToken(final String token) throws WebSecurityException, SystemException {

		try {
			
			LOGGER.info("Invocando servicio de verificacion de token jwt {}",token);
			TokenWrapper wrapper = TokenWrapper.newInstance().token(token).build();
			String payload = restTemplate.postForObject(endpoint + "/check", wrapper,
					String.class);
			return JSONUtil.unmarshal(payload, Usuario.class);

		} catch (HttpStatusCodeException e) {
			String errorpayload = e.getResponseBodyAsString();
			LOGGER.error("Error invocando servicio remoto: " + errorpayload, e);
			GenericResponse<?> response = JSONUtil.unmarshal(errorpayload, GenericResponse.class);
			throw new WebSecurityException(response == null ? e.getMessage() : response.getContent().toString());
		} catch (RestClientException e) {
			LOGGER.error("Error invocando servicio de check de jwt: " + e.getMessage(), e);
			throw new SystemException("Error invocando servicio remoto: ", e);
		}
	}

	public String generateToken(final Usuario usuario) throws SystemException {
		
		try {
			
			LOGGER.info("Invocando servicio de generacion de token para usuario {}", usuario.getLogin());
			TokenWrapper wrapper = restTemplate.postForObject(endpoint + "/", usuario, TokenWrapper.class);
			return wrapper.getToken();
			
		} catch (RestClientException e) {
			LOGGER.error("Error invocando servicio generacion de token jwt: " + e.getMessage(), e);
			throw new SystemException("Error invocando servicio remoto: ", e);
		}
	}
}
