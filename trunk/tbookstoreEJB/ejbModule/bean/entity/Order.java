package bean.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "TOrder")
public class Order implements Serializable/*, CycleRecoverable*/ {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6084322691851200736L;
	private int orderId;
	private Date date;
	private String paymentType;
	private Customer customer;
	private Collection<LineItem> lineItems = new ArrayList<LineItem>();
	
	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Column(name = "order_id", nullable = false)
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	@Column(name = "date")
	@Temporal(value = TemporalType.TIMESTAMP)
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Column(name = "payment_type")
	public String getPaymentType() {
		return paymentType;
	}
	
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	
	
	@ManyToOne
	@JoinColumn(name="customer")
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
//	@Override
//	public Object onCycleDetected(Context context) {

//		return null;
//	}
	
	@Transient
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.order")
	public Collection<LineItem> getLineItems() {
		return lineItems;
	}
	
	public void setLineItems(Collection<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

}
