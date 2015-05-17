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

import bg.uni_sofia.fmi.javaee.model.Role;
import bg.uni_sofia.fmi.javaee.services.UserContext;

/**
 * Checks if logged user has an admin rights
 */
@WebFilter(urlPatterns = { "/rest/admin/*", "/users.jsp", "/admin/*", "/register.html" })
public class AdminFilter implements Filter {
	
	@Inject
	private UserContext userContext;
	
    public AdminFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Role userRole = userContext.getCurrentUser().getRole();
		if(userRole != null && userRole.getName().equals("Administrator")){
			chain.doFilter(request, response);
			return;
		}
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		String loginUrl = httpServletRequest.getContextPath()
                + "/login.html";
        httpServletResponse.sendRedirect(loginUrl);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
