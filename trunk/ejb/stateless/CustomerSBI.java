package bean.stateless;

import java.util.List;

import javax.ejb.Remote;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import bean.entity.Customer;

@Remote
@SOAPBinding(style = Style.RPC)
public interface CustomerSBI {

	public boolean createCustomer(String name, String surname, String email, String password);

	public boolean removeCustomer(String email, String password);

	public Customer login(String email, String password);

	public List<Customer> findAllCustomers();

}
