package co.com.mintrabajo.infrastructure.security.jwt;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import co.com.mintrabajo.infrastructure.security.domain.Usuario;
import co.com.mintrabajo.infrastructure.security.exception.WebSecurityException;

@Component
public class JwtProvider {

	private static final Logger LOGGER = LogManager.getLogger(JwtProvider.class);

	@Autowired
	private KeyManager keyManager;

	private static final String ISSUER = "MINISTERIO DEL TRABAJO";
	private Algorithm algorithm;

	public JwtProvider() throws WebSecurityException {
		super();
	}

	@PostConstruct
	public void initKeys() throws WebSecurityException {
		try {
			keyManager.loadKeys();
			RSAPublicKey publicKey = (RSAPublicKey) keyManager.getPubKey();
			RSAPrivateKey privateKey = (RSAPrivateKey) keyManager.getPrivKey();
			algorithm = Algorithm.RSA256(publicKey, privateKey);
		} catch (Exception e) {
			throw new WebSecurityException("Error generando algoritmo: " + e.getMessage(), e);
		}
	}

	public Usuario checkAndParseToken(final String token) throws WebSecurityException {

		try {

			LOGGER.info("Verificando token {} con algoritmo {}", token, algorithm);
			JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
			DecodedJWT jwt = verifier.verify(token);
			return Usuario.newInstance().login(jwt.getSubject()).role(jwt.getClaim("role").asString()).build();

		} catch (JWTVerificationException e) {
			throw new WebSecurityException("Token invalido: " + e.getMessage(), e);
		}
	}

	public String generateToken(final Usuario usuario) {
		return issueToken(usuario.getLogin(), usuario.getRole());
	}

	private String issueToken(String login, String role) {

		LOGGER.info("Emitiendo token con algoritmo {} para el usuario {}", algorithm, login);
		String jwtToken = JWT.create().withIssuer(ISSUER).withExpiresAt(generateExpirationForKey())
				.withIssuedAt(new Date()).withSubject(login).withClaim("role", role).sign(algorithm);

		return jwtToken;
	}

	private Date generateExpirationForKey() {
		Calendar today = Calendar.getInstance();
		today.add(Calendar.MINUTE, 20);
		return today.getTime();
	}

}
