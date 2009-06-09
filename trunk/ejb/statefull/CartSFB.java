package bean.statefull;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import bean.entity.Customer;
import bean.entity.LineItem;
import bean.entity.Order;
import bean.stateless.OrderSB;
import bean.stateless.OrderSBIL;

/**
 * Session Bean implementation class CartBean
 */
@Stateful
@Remote(bean.statefull.CartSFBI.class)
public class CartSFB implements CartSFBI {

	@PersistenceContext(type=PersistenceContextType.EXTENDED) EntityManager em;

	@EJB OrderSBIL orderSB;

	private Customer customer;
	
	private Order order;


    /**
     * Default constructor. 
     */
    public CartSFB(String paymentType, Date date) {
        
    	createOrder(paymentType, date);
    	// TODO: inizializzare l'oggetto customer con il cliente corrente pescato dal db
    	// anche facendo passare l'id via costruttore volendo
    	
    }
    
    private void createOrder(String paymentType, Date date) {
		
    	order = orderSB.createOrder(customer, paymentType, date);
		
	}

	public void addLineItem(LineItem lineItem) {
    	
    	orderSB.addLineItem(lineItem, order);
    	
    }
    

}
