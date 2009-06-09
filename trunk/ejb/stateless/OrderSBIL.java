package bean.stateless;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import bean.entity.Customer;
import bean.entity.LineItem;
import bean.entity.Order;

@Local 
public interface OrderSBIL {

	List<Order> findAllOrders();

	Order createOrder(Customer customer, String paymentType, Date date);

	void addLineItem(LineItem lineItem, Order order);

	void shipOrder(Order order);
	
}
