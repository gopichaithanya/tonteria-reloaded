package bean.stateless;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bean.entity.Book;
import bean.entity.Customer;
import bean.entity.LineItem;
import bean.entity.Order;

/**
 * Session Bean implementation class OrderSB
 */
@Local(bean.stateless.OrderSBIL.class)
@Remote(bean.stateless.OrderSBI.class)
@WebService
@Stateless
public class OrderSB implements OrderSBI, OrderSBIL {

    /**
     * Default constructor. 
     */
    public OrderSB() {
        // TODO Auto-generated constructor stub
    }
    
    @PersistenceContext(unitName="tbookstoredb")
	private EntityManager em;
	
	@Override
	public List<Order> findAllOrders() {
		  Query query = em.createQuery("Select o from Order o");
		  return (List<Order>) query.getResultList();
		 }
	
	@Override
	public Order createOrder(Customer customer, String paymentType, Date date) {
		
		Order order = new Order();
		order.setCustomer(customer);
		order.setData(date);
		order.setPaymentType(paymentType);
		
		return order;
	}

	@Override
	public void shipOrder(Order order) {

		em.persist(order);
	}

	@Override
	public void addLineItem(LineItem lineItem, Order order) {
		
		order.getLineItems().add(lineItem);
		
	}
	
	


}
