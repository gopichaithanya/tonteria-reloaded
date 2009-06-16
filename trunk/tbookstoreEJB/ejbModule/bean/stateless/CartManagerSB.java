package bean.stateless;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import bean.entity.Book;
import bean.entity.Customer;
import bean.entity.LineItem;
import bean.stateful.interf.CartSFBI;
import bean.stateless.interf.CartManagerSBI;

@Remote(bean.stateless.interf.CartManagerSBI.class)
@WebService
public @Stateless(mappedName="CartWebService") class CartManagerSB implements CartManagerSBI {

	/**
	 * Crea la sessione del carrello. Si occupa di creare (ctx.lookup) la SFSB
	 * per l'utente.
	 * 
	 *  @return byte[] handle della SFSB
	 * */
	@Override
	public byte[] openSession(Customer customer){

		InitialContext ctx;
		byte[] handleBytes = {};
		ObjectOutput out = null;
		ByteArrayOutputStream bos = null;
		try {
			ctx = new InitialContext();

			CartSFBI cart = (CartSFBI)ctx.lookup("CartSFSB");
			cart.createOrder(customer);
			bos = new ByteArrayOutputStream();
			out  = new ObjectOutputStream(bos) ;
			out.writeObject(cart);
			handleBytes = bos.toByteArray();
			

		} catch (NamingException e) {
			System.err.println("ERRORE in openSession");
			e.printStackTrace();
		} catch (RemoteException e) {
			System.err.println("ERRORE in openSession");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("ERRORE in openSession, nella serializzazione");
			e.printStackTrace();
		} finally {
			
			try { out.close(); } catch (Exception e) { System.err.println("WARNING: errore nella chiusura di ObjectOutputStream"); }
			try { bos.close(); } catch (Exception e) { System.err.println("WARNING: errore nella chiusura di ByteArrayOutputStream"); }
		}
		return handleBytes;
	}

	/**
	 * Chiude la sessione del carrello. Si occupa di rimuove la SFSB dal container.
	 * */
	@Override
	public void closeSession(byte[] handleBytes) {
		try {
			getRef(handleBytes).closeCart();
		} catch (RemoteException e) {
			System.err.println("ERRORE in closeSession");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("ERRORE in closeSession");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("ERRORE in closeSession");
			e.printStackTrace();
		}
	}

	/**
	 * Aggiunge un libro al carrello
	 * */
	@Override
	public void addBook(byte[] handleBytes, Book book) {
		
		try {
			getRef(handleBytes).add(book);
		} catch (IOException e) {
			System.err.println("ERRORE in addBook");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("ERRORE in addBook");
			e.printStackTrace();
		}		
	}

	/**
	 * Rimuove un libro dal carrello.
	 * */
	@Override
	public void removeBookFromCart(byte[] handleBytes, Book book) {
		
		try {
			getRef(handleBytes).removeBook(book);
		} catch (IOException e) {
			System.err.println("ERRORE in addBook");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("ERRORE in addBook");
			e.printStackTrace();
		}	
	}
	
	/**
	 * Ritorna il tipo di pagamento sotto forma di stringa
	 * */
	@Override
	public String getPaymentType(byte[] handleBytes) {
		
		String paymentType = "";
		try {
			paymentType = getRef(handleBytes).getPaymentType();
		} catch (IOException e) {
			System.err.println("ERRORE in getPaymentType");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("ERRORE in getPaymentType");
			e.printStackTrace();
		}
		
		return paymentType;
	}

	/**
	 * Conclude l'ordine. (Commit)
	 * */
	@Override
	public void shipOrder(byte[] handleBytes, String paymentType) {
		
		try {
			getRef(handleBytes).shipOrder(paymentType);
		} catch (IOException e) {
			System.err.println("ERRORE in shipOrder");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("ERRORE in shipOrder");
			e.printStackTrace();
		}
		
	}

	/**
	 * Ritorna la lista delle righe che compongono il carrello.
	 * */
	@Override
	public Collection<LineItem> getLineItemList(byte[] handleBytes) {
		Collection<LineItem> lineItemList = null;
		try {
			lineItemList = getRef(handleBytes).getLineItemList();
		} catch (IOException e) {
			System.err.println("ERRORE in getLineItemList");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("ERRORE in getLineItemList");
			e.printStackTrace();
		}
		
		return lineItemList;
	}
	
	/**
	 * Ritorna il riferimento all'interfaccia che espone i metodi della stateful
	 * session bean.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * */
	private CartSFBI getRef(byte[] handleBytes) throws IOException, ClassNotFoundException {

		ByteArrayInputStream bais = new ByteArrayInputStream(handleBytes);
		ObjectInputStream ois = new ObjectInputStream(bais);
		Object handle = ois.readObject();
		//Object ref = handle.getEJBObject();
		CartSFBI cart = (CartSFBI)PortableRemoteObject.narrow(handle, bean.stateful.interf.CartSFBI.class);

		return cart;
	}	
}
