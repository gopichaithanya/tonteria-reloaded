package bean.stateless;

import java.util.Date;
import java.util.List;

import bean.entity.Customer;
import bean.entity.LineItem;
import bean.entity.Order;

public interface OrderSBI {

	List<Order> findAllOrders();

	Order createOrder(Customer customer, String paymentType, Date date);

	void addLineItem(LineItem lineItem, Order order);

	void shipOrder(Order order);

}
