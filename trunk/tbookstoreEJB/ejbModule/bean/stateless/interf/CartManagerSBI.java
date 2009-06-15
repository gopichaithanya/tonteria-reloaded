package bean.stateless.interf;

import java.util.Collection;

import javax.ejb.Remote;
import javax.jws.soap.SOAPBinding;

import bean.entity.Book;
import bean.entity.Customer;
import bean.entity.LineItem;


@Remote
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public interface CartManagerSBI {

	public byte[] openSession(Customer customer);

	public void closeSession(byte[] handleBytes);
	
	public void addBook(byte[] handleBytes, Book book);
	
	public void removeBookFromCart(byte[] handleBytes, Book book);
	
	public void shipOrder(byte[] handleBytes, String paymentType);

	// public void setPaymentType(byte[] handleBytes, String paymentType);
	
	public String getPaymentType(byte[] handleBytes);
	
	public Collection<LineItem> getLineItemList(byte[] handleBytes);

}
