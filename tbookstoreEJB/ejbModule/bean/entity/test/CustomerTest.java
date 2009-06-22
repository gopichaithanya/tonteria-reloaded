package bean.entity.test;

import java.util.ArrayList;
import java.util.List;

import bean.entity.Customer;

import com.bm.testsuite.PoJoFixture;

public class CustomerTest extends PoJoFixture {

	private final static Class []USED_ENTITY = {bean.entity.Customer.class, bean.entity.Order.class};
	
	public CustomerTest() {
		super(USED_ENTITY);
	}

	public void testGetEntityManager(){
		
		assertNotNull(this.getEntityManager());
	}
	
	public void testWriteCustomer() {
		List<Customer> customers = generateTestCustomers();

		// persist the graph and load it again
		List<Customer> persisted = persist(customers);
		List<Customer> allFromDB = findAll(Customer.class);

		// assert the persisted graph and the loaded are equal
		assertCollectionsEqual(persisted, allFromDB);


	}

	public List<Customer> generateTestCustomers() {
		final List<Customer> customerList = new ArrayList<Customer>();
		Customer customer = new Customer();
		customer.setEmail("mailina@iyu.com");
		customer.setName("franz");
		customer.setPassword("passed");
		customer.setSurname("senzi");
		customerList.add(customer);

		return customerList;
	}

}
