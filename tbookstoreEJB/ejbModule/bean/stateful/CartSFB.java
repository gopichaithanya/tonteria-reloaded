package bean.stateful;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.Init;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import bean.entity.Book;
import bean.entity.Customer;
import bean.entity.LineItem;
import bean.entity.Order;
import bean.stateful.interf.CartSFBI;

/**
 * Session Bean implementation class CartBean
 */
@Stateful(mappedName="CartSFSB")
@Remote(bean.stateful.interf.CartSFBI.class)
public class CartSFB implements CartSFBI, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7439849640925068454L;

	@PersistenceContext(type=PersistenceContextType.EXTENDED) EntityManager em;

	private Order order;
	
	private String paymentType;

	@Init
    public void create() {
    	order = null;
    }
    
    
    /**
     * Crea l'ordine
     * */
  //  @Override
    public void createOrder(Customer customer) {
    	order = new Order();
    //	this.customer = customer;
    	order.setCustomer(customer);
    }
    
    /** 
     * Salva l'ordine nel db 
     */
    public void shipOrder(String paymentType) {
		order.setPaymentType(paymentType);
		order.setDate(new Date());
		em.persist(order);
		// salvo i lineItem dell'ordine nel db
		for( Iterator<LineItem> i = order.getLineItems().iterator(); i.hasNext(); ) {
			LineItem tmpLI = i.next();
			em.persist(tmpLI);
		}
    }
    
    /**
     * 
     * */
   // @Override
	public String getPaymentType() {
		return paymentType;
	}

	/**
	 * Rimuove un libro al carrello (senza toccare il db)
	 * */
	public void removeBook(Book book) {

		boolean flag = false;
		// cerco il libro fra le lineItem
		for( Iterator<LineItem> i = order.getLineItems().iterator(); i.hasNext() && !flag; ) {
			LineItem currentLineItem = i.next();
			if(book.getIsbn().equals(currentLineItem.getPk().getBook().getIsbn())) {
				flag = true;
				long bookQuantity = currentLineItem.getQuantity();
				if(bookQuantity > 1) {
					// se si voleva comprare piu' di una copia, diminuisco il numero di copie
					currentLineItem.setQuantity(bookQuantity - 1);
				} else if(bookQuantity == 1) { 
					// se si era interessa ad una sola copia rimuovo la lineItem
					order.getLineItems().remove(currentLineItem);
				}
			}
		}	
	}

	/**
     * Aggiunge un libro al carrello (senza salvare nulla nel db)
     * */
	//@Override
	public void add(Book book) {
		System.out.println("---------------------------metodo addBook");
		
		boolean flag = false;
		// cerco se il libro e' gia' stato inserito in una lineItem
		// se lo e' incremento la quantita'
		for( Iterator<LineItem> i = order.getLineItems().iterator(); i.hasNext() && !flag; ) {
			LineItem currentLineItem = i.next();
			if(book.getIsbn().equals(currentLineItem.getPk().getBook().getIsbn())) {
				flag = true;
				System.out.println("---------------------------aumento la quantita' di un libro");
				currentLineItem.setQuantity( currentLineItem.getQuantity() + 1 );	
			}
		}
		// se non lo e' creo la lineItem e la aggiungo alla lista
		if(!flag) {
			LineItem lineItem = new LineItem();
			lineItem.setBook(book);
			lineItem.setQuantity(1);
			lineItem.getPk().setOrder(order);
			System.out.println("---------------------------aggiungo un nuovo libro");
			order.getLineItems().add(lineItem);	
		}
	}
	
	/**
	 * 
	 * */
	public Collection<LineItem> getLineItemList() {
		return order.getLineItems();
	}
	
	/**
	 * 
	 * */
	// @Override
	public @Remove void closeCart(){ System.out.println("Carrello chiuso. SFSB rimossa dal container."); } 

}
