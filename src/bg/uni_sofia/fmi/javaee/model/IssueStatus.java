package bg.uni_sofia.fmi.javaee.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: IssueStatus
 *
 */
@Entity
@Table(name = "issuestatuses")
@XmlRootElement
public class IssueStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2743109814444562147L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@OneToMany(mappedBy = "status", cascade = CascadeType.REMOVE)
	public List<Issue> issues;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
