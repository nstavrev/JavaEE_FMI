package bg.uni_sofia.fmi.javaee.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: UserRole
 *
 */
@Entity
@Table(name = "roles")
@XmlRootElement
public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9169536956143781638L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;

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
