package bg.uni_sofia.fmi.javaee.filters;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bg.uni_sofia.fmi.javaee.services.UserContext;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/*")
public class LoginFilter implements Filter {

	@Inject
	private UserContext context;
	
	public void init(FilterConfig fConfig) throws ServletException {
	}
	

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		String requestURI = httpServletRequest.getRequestURI();
		String contextPath = httpServletRequest.getContextPath();
		
		boolean isResource = requestURI.startsWith(contextPath + "/js") || requestURI.startsWith(contextPath + "/css") ||
				requestURI.startsWith(contextPath + "/fonts") ||
				requestURI.startsWith(contextPath + "/font-awesome-4.1.0");
		
		if(isResource) {
			chain.doFilter(request, response);
			return;
		}
		
		boolean isLoginPage = requestURI.equals(contextPath + "/login.html");
		boolean isPostRequestForLogin = requestURI.equals(contextPath + "/rest/auth/login");

		if(isLoginPage || isPostRequestForLogin) {
			chain.doFilter(request, response);
			return;
		}

		if(context.getCurrentUser() == null) {
			String loginUrl = httpServletRequest.getContextPath()
                    + "/login.html";
            httpServletResponse.sendRedirect(loginUrl);
			return;
		}
		chain.doFilter(request, response);
	}

	public void destroy() {
	
	}

}
