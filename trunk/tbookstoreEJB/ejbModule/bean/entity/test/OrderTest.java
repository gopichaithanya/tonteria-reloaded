package bean.entity.test;

import bean.entity.Customer;
import bean.entity.Order;

import com.bm.datagen.annotations.GeneratorType;
import com.bm.datagen.relation.BeanCollectionGenerator;
import com.bm.testsuite.BaseEntityFixture;


public class OrderTest extends BaseEntityFixture<Order>{

	
	public OrderTest() {
		super(bean.entity.Order.class);
		// TODO Auto-generated constructor stub
	}
	
	@GeneratorType(className = bean.entity.Customer.class, field = "customer")
	private static final class CustomerCreator extends
			BeanCollectionGenerator<Customer> {
		private CustomerCreator() {
			super(bean.entity.Customer.class, 10);
		}
	}

}
