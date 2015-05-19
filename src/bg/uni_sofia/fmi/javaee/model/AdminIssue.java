package bg.uni_sofia.fmi.javaee.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: AdminIssue
 *
 */
@Entity
@Table(name = "adminsIssues")
@Inheritance(strategy = InheritanceType.JOINED)
public class AdminIssue extends Issue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3333691336223442563L;
	
	private boolean important;

	public boolean isImportant() {
		return important;
	}

	public void setImportant(boolean important) {
		this.important = important;
	}
	
}
