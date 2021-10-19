package co.com.mintrabajo.tys.security.cors;

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

public class CORSFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		httpResponse.addHeader("Access-Control-Allow-Origin", "*");
		httpResponse.addHeader("Access-Control-Allow-Methods","GET, OPTIONS, HEAD, PUT, POST");
		httpResponse.addHeader("Access-Control-Expose-Headers","Authorization");
		
		
		String reqHeader = httpRequest.getHeader("Access-Control-Request-Headers");
        if (!StringUtils.isEmpty(reqHeader)) {
        	httpResponse.addHeader("Access-Control-Allow-Headers", reqHeader);
        }else {
        	httpResponse.addHeader("Access-Control-Allow-Headers","*");
        }
		
        if (httpRequest.getMethod().equals("OPTIONS")) {
        	httpResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
            return;
        }
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

}
