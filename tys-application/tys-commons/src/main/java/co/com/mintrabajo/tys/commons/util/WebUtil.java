package co.com.mintrabajo.tys.commons.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class WebUtil {

	public static HttpServletRequest getHttpServletRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	public static String getRemoteHost() {
		return getHttpServletRequest().getRemoteHost();
	}
	
	public static String getRemoteAddr(){
		return getHttpServletRequest().getRemoteAddr();
	}

}
