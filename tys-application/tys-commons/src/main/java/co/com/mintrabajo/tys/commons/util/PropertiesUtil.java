package co.com.mintrabajo.tys.commons.util;

import java.util.ResourceBundle;

public class PropertiesUtil {

	public static final String APPLICATION_WEB = "application-web";
	
	private static ResourceBundle bundle;
	
	private static void init( final String bundleName ) {
		if( bundle == null ) {
			bundle = ResourceBundle.getBundle("spring-config." + bundleName);
		}
	}
	
	public static String get( final String bundleName, final String key ) {
		init(bundleName);
		return bundle.getString(key);
	}
	
}
