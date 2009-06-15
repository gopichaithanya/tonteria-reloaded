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
	 * */
	@Override
	public byte[] openSession(Customer customer){

		InitialContext ctx;
		//Handle h = null;
		byte[] handleBytes = {};
		ObjectOutput out = null;
		ByteArrayOutputStream bos = null;
		try {
			ctx = new InitialContext();

			CartSFBI cart = (CartSFBI)ctx.lookup("CartSFSB");
			//CartSFBI cart = cartHome.create();
			//cart.setURI(id);
			cart.createOrder(customer);
		//	h = cart.getHandle();
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
			
			try { out.close(); } catch (Exception e) { /*e.printStackTrace();*/ }
			try { bos.close(); } catch (Exception e) { /*e.printStackTrace();*/ }
		}
		return handleBytes;
	}

	/**
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
	 * 
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
	 * 
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
	 * 
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
	 * 
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
	 * 
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
	
	/*	@Override
	public	void setURI(byte[] handleBytes, String uri) throws RemoteException {
		try {
			getRef(handleBytes).setURI(uri);
		} catch (IOException e) {
			System.err.println("ERRORE in setUri");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("ERRORE in setUri");
			e.printStackTrace();
		}
	}

	*//**
	 * *//*
	@Override
	public String getURI(byte[] handleBytes) throws RemoteException {
		String uri = "";
		try {
			uri = getRef(handleBytes).getURI();
		} catch (IOException e) {
			System.err.println("ERRORE in getUri");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("ERRORE in getUri");
			e.printStackTrace();
		}
		return uri;
	}*/

	/**
	 * 
	 * */
/*	@Override
	public void setPaymentType(byte[] handleBytes, String paymentType) {
		
		try {
			getRef(handleBytes).setPaymentType(paymentType);
		} catch (IOException e) {
			System.err.println("ERRORE in setPaymentType");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("ERRORE in setPaymentType");
			e.printStackTrace();
		}
		
	}*/
	
}
