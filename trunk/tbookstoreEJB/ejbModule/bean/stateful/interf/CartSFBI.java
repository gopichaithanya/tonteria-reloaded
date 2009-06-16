package bean.stateful.interf;

import java.util.Collection;

import javax.ejb.Remote;

import bean.entity.Book;
import bean.entity.Customer;
import bean.entity.LineItem;

@Remote
public interface CartSFBI {

	public void add(Book book);
	
	public void removeBook(Book book);

	public void shipOrder(String paymentType);
	
	public String getPaymentType();

	public void createOrder(Customer customer);
	
	public Collection<LineItem> getLineItemList();

	public void closeCart();

}
