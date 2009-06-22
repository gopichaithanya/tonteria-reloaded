package bean.entity.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.entity.Book;
import bean.entity.Customer;
import bean.entity.LineItem;
import bean.entity.LineItemPk;
import bean.entity.Order;

import com.bm.testsuite.BaseEntityFixture;
import com.bm.testsuite.PoJoFixture;

public class LineItemTest extends PoJoFixture {

	private final static Class []USED_ENTITY = {bean.entity.Book.class, bean.entity.Order.class, bean.entity.LineItem.class };
	
	public LineItemTest() {
		super(USED_ENTITY);
	}

	public void testGetEntityManager(){
		
		assertNotNull(this.getEntityManager());
	}
	
	public void testWriteLineItem() {
		List<LineItem> lineItems = generateTestLineItems();

		// persist the graph and load it again
		List<LineItem> persisted = persist(lineItems);
		List<LineItem> allFromDB = findAll(LineItem.class);

		// assert the persisted graph and the loaded are equal
		assertCollectionsEqual(persisted, allFromDB);


	}

	public List<LineItem> generateTestLineItems() {
		final List<LineItem> lineItemList = new ArrayList<LineItem>();
		Order o = new Order();
		Book b = new Book();
		Customer c = new Customer();
		c.setEmail("deefgs");
		c.setName("lopikjhu");
		c.setPassword("fefw");
		c.setSurname("effwrs");
		o.setCustomer(c);
		o.setDate(new Date());
		o.setLineItems(lineItemList);
		o.setPaymentType("pagamento");
		b.setAuthor("efe");
		b.setDescription("ewgw");
		b.setEditor("regerber");
		b.setImage("on/fefwe.jpg");
		b.setIsbn("wfefw34de12");
		b.setTitle("wefwe");
		b.setPrice(23);
		b.setUploadDate(new Date());
		
		List<Book> lb = new ArrayList<Book>();
		lb.add(b);
		persist(lb);
		
		List<Customer> lc = new ArrayList<Customer>();
		lc.add(c);
		persist(lc);
		
		List<Order> lo = new ArrayList<Order>();
		lo.add(o);
		persist(lo);
		
		LineItem li = new LineItem();
		LineItemPk lipk = new LineItemPk();
		lipk.setOrder(o);
		lipk.setBook(b);
		li.setPk(lipk);
		lineItemList.add(li);

		return lineItemList;
	}

}
