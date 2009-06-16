package bean.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sun.xml.bind.CycleRecoverable;

@Entity
@Table(name = "Customer")
public class Customer implements Serializable, CycleRecoverable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -988571761757900849L;
	private String name;
	private String surname;
	private String email;
	private String password;
	private Collection<Order> orders = new ArrayList<Order>();
	
	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "surname", nullable = false)
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	@Id
	@Column(name = "email", nullable = false)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "password", nullable = false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@OneToMany(mappedBy="customer", fetch=FetchType.EAGER)
	public Collection<Order> getOrders() {
		
		return orders;
	}
	public void setOrders(Collection<Order> orders) {
		this.orders = orders;
	}
	
	//workaround per evitare cicli
	@Override
	public Object onCycleDetected(Context context) {
		
		return null;
	}
	
}
