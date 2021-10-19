package co.com.mintrabajo.tys.mvc.util;

import java.util.ArrayList;
import java.util.Map;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

import org.springframework.ldap.core.AttributesMapper;

import co.com.mintrabajo.tys.commons.autentication.PrincipalContext;

public class PrincipalMapper implements AttributesMapper<PrincipalContext> {
	
	private Map<String, String> parameters;
	private String password;
	private Attributes attributes;

	public static PrincipalMapper newInstance(final Map<String, String> parameters, String password) {
		return new PrincipalMapper(parameters, password);
	}

	private PrincipalMapper(final Map<String, String> parameters, String password) {
		this.parameters = parameters;
		this.password = password;
	}

	public PrincipalContext mapFromAttributes(Attributes attributes) throws NamingException {
		this.attributes = attributes;
		PrincipalContext builder = new PrincipalContext(getValue(parameters.get("mapper.user")), password,
				getValue("cn"), getValue("sn"), getValue(parameters.get("mapper.email")), new ArrayList<String>());
		return builder;
	}

	public String getValue(String name) throws NamingException {

		Attribute attribute = attributes.get(name);

		if (attribute !=null)
			return attribute.get().toString();
		return "";
	}
}
