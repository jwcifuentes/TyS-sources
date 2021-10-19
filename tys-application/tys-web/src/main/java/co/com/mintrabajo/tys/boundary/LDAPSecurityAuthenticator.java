package co.com.mintrabajo.tys.boundary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.stereotype.Component;

import co.com.mintrabajo.tys.commons.autentication.AutenticationResponseContext;
import co.com.mintrabajo.tys.commons.autentication.PrincipalContext;
import co.com.mintrabajo.tys.commons.exceptions.SystemException;
import co.com.mintrabajo.tys.mvc.util.PrincipalMapper;
@Component
public class LDAPSecurityAuthenticator {

	@Resource(name = "configurationData")
	private Map<String, String> parameters;
	private final LdapTemplate template;

	private static final Logger LOGGER = LogManager.getLogger(LDAPSecurityAuthenticator.class);

	@Autowired
	public LDAPSecurityAuthenticator(@Qualifier("ldap_source") LdapContextSource ldapSource) {
		ldapSource.afterPropertiesSet();
		template = new LdapTemplate(ldapSource);
	}

	public AutenticationResponseContext login(String login, String password) throws SystemException {
		boolean success = true;
		PrincipalContext context = null;

		try {
			LOGGER.info("LDAP Security Authenticator | User: "+login);
			String loginExpression = parameters.get("user.unique.identifier").concat("=").concat(login);
			boolean auth = template.authenticate(parameters.get("user.base.dn"), loginExpression, password);
			if (auth) {
				context = queryUserDetails(login, password);
			} else {
				success = false;
				context = new PrincipalContext();
			}
			LOGGER.info("LDAP Security Authenticator | Response: "+success);
		} catch (Exception e) {
			LOGGER.error("Security - an error has occurred during logging", e);
			success = false;
		}

		return new AutenticationResponseContext(success, context);
	}

	private PrincipalContext queryUserDetails(String login, String password) throws javax.naming.NamingException {
		PrincipalContext context = null;
		String loginExpression = parameters.get("user.unique.identifier").concat("=").concat(login);

		PrincipalMapper mapper = PrincipalMapper.newInstance(parameters, password);
		List<PrincipalContext> result = template.search(parameters.get("user.base.dn"), loginExpression, mapper);

		if (!result.isEmpty()) {
			context = result.get(0);
			String roleFinder = mapper.getValue(parameters.get("mapper.roles.finder"));
			context.setRoles(queryUserRoles(roleFinder));
		}

		return context;
	}

	private ArrayList<String> queryUserRoles(String roleFinder) {
		AndFilter filter = new AndFilter();
		filter.and(new EqualsFilter(parameters.get("roles.group.criteria.name"),
				parameters.get("roles.group.criteria.value")));
		String memberExpression = parameters.get("mapper.roles.finder").concat("=").concat(roleFinder).concat(",")
				.concat(parameters.get("user.base.dn"));

		filter.and(new EqualsFilter(parameters.get("roles.group.member.name"), memberExpression));

		ArrayList<String> roles = new ArrayList<String>();

		return roles;
	}
}
