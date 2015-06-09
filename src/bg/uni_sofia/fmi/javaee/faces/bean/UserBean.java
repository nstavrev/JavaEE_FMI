package bg.uni_sofia.fmi.javaee.faces.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean {
	
	public boolean isAdmin() {
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		return request.isUserInRole("Administrator");
	}
	
	
}
