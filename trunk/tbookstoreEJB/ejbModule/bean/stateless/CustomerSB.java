/**
 * 
 */
package bean.stateless;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import bean.entity.Customer;
import bean.stateless.interf.CustomerSBI;

/**
 * @author mattia
 *
 */
@Remote(bean.stateless.interf.CustomerSBI.class)
@WebService
@Stateless(mappedName="CustomerWebService")
public class CustomerSB implements CustomerSBI {

	@PersistenceContext(type=PersistenceContextType.TRANSACTION, unitName="tbookstoredb")
	private EntityManager em;
	
	@Override
	public List<Customer> findAllCustomers() {
		System.out.println("dentro alla statefull in findallcustomer");
		Query query = em.createQuery("Select c from Customer c");
		return (List<Customer>) query.getResultList();
	}
	

	/**
	 * Crea un nuovo cliente nel database. Se esiste gia' un cliente
	 * registrato con quell'email allora ritorna false; altrimenti true.
	 * */
	@Override
	public boolean createCustomer(String name, String surname, String email, String password) {

		boolean flag = false;
		Customer newCustomer = null;
		if(!checkExistEmailAddressEmail(email)) {

			newCustomer = new Customer();
			newCustomer.setEmail(email);
			newCustomer.setName(name);
			newCustomer.setSurname(surname);
			newCustomer.setPassword(password);
			em.persist(newCustomer);
			flag = true;
		}

		return flag;
	}

	/**
	 * Verifica se esiste gia' un cliente iscritto con quella mail
	 * 
	 */
	private boolean checkExistEmailAddressEmail(String email) {

		boolean flag = true;

		Query query = em.createQuery("Select count(*) from Customer c where c.email = '" + email +"'");
		long usersFinded = ((Long)query.getSingleResult()).longValue();
		if(usersFinded == 0)
			flag = false;

		return flag;
	}

	@Override
	public Customer login(String email, String password) {

		return getCustomer(email, password);

	}

	@Override
	public Customer searchCustomer(String email) {
		
		return getCustomer(email, null);
	}
	
	/**
	 * 
	 * Ritorna un oggetto customer.
	 * 
	 * Se password e' null ritorna l'oggetto senza verificare la password
	 * */
	public Customer getCustomer(String email, String password) {

		Customer findedCustomer = null;
		try{
			Query query;
			
			if(password == null)
				query = em.createQuery("SELECT c  FROM Customer c WHERE c.email = '" + email + "'");
			else
				query = em.createQuery("SELECT c  FROM Customer c WHERE c.email = '" + email +
						"' AND c.password = '" + password +"'" );
			findedCustomer = (Customer)query.getSingleResult();
			
		}catch(Exception e){
			System.out.println("[Customer Session Bean:customerExist] Nessun risultato in query!");
		}
		return findedCustomer;
	}

	@Override
	public boolean removeCustomer(String email, String password) {
		boolean flag = false;

		Customer cust = getCustomer(email, password);
		if(cust != null) {
			em.remove(cust);
			flag = true;
		}
		return flag;
	}
}