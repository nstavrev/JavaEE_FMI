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

import bg.uni_sofia.fmi.javaee.model.User;
import bg.uni_sofia.fmi.javaee.services.UserContext;

/**
 * Servlet Filter implementation class AdminIndexPageFilter
 */
@WebFilter(urlPatterns = "/index.jsp")
public class AdminIndexPageFilter implements Filter {
	
	@Inject
	private UserContext userContext;
	
    public AdminIndexPageFilter() {
    }

	
	public void destroy() {
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		User user = userContext.getCurrentUser();
		if(user != null && user.getRole() != null && user.getRole().getName().equals("Administrator")){
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;
			String indexPageUrl = httpServletRequest.getContextPath() + "/admin.jsp";
			httpServletResponse.sendRedirect(indexPageUrl); 
			return; 
		}
		chain.doFilter(request, response);
	} 

	
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
