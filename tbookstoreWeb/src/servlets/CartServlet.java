package servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.stateless.Book;
import bean.stateless.BookSB;
import bean.stateless.BookSBProxy;
import bean.stateless.CartManagerSB;
import bean.stateless.CartManagerSBProxy;
import bean.stateless.Customer;
import bean.stateless.CustomerSB;
import bean.stateless.CustomerSBProxy;
import bean.stateless.LineItem;



/**
 * Servlet implementation class CartServlet
 */
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//-----------test--------------
	int i = 0;
	//---------fine-test-----------
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
    }

    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession(true);
		response.setContentType("text/html");	
	
		//------------------test------------------------------
		if(session.getAttribute("customer") == null) {
			CustomerSB csb = new CustomerSBProxy();;
			Customer c = csb.findAllCustomers()[0];
			int ko = 0;
			if(c.getOrders() != null)
				ko = c.getOrders().length;
			else 
				System.out.println("Orders e' nullo porca vacca!");
			if( ko > 0)
				System.out.println("WOW gli ordini ci sono, e sono: " + ko);
			else
				System.err.println("NON c'e' manco un ordine cazzo!");

			session.setAttribute("customer", c);
		}
		//---------------------fine-test-----------------------
		
		if(session.getAttribute("customer") != null) {
			
		//------------------test-------------------------------
			if(!session.isNew()){
				BookSB bsb = new BookSBProxy();
				Book books[] = bsb.findAllBooks();
				try {
					addBookToCart(session, books[0]);
					addBookToCart(session, books[1]);
					addBookToCart(session, books[1]);
					addBookToCart(session, books[0]);
					removeBookFromCart(session, books[0]);
					if(++i > 5) {
						shipOrder(session, "cash");
						closeCart(session);
						response.getWriter().println("<strong>Ordine commissionato</strong>");
					} else {
						
						response.getWriter().println("<strong>" + (5 - i) + "</strong><br>");
						response.getWriter().println("<strong>numero di linee: " + getLineItemList(session).size() + "</strong>");
						
						
					}
				} catch (Exception e) {
					System.err.println("ERRORE nella commissione dell'ordine o nell'aggiunta di libri");
					e.printStackTrace();
				}	
			}
		//---------------------fine-test-----------------------
		} else {
			// entro qui quando l'utente non ha ancora fatto il login
			response.getWriter().println("<strong>ERRORE: nessun cliente associato a questa sessione</strong>");
		}

	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		response.setContentType("text/html");	
		
		if(session.getAttribute("customer") != null) {


		} else {
			// entro qui quando l'utente non ha ancora fatto il login
			response.getWriter().println("<strong>ERRORE: nessun cliente associato a questa sessione</strong>");
		}
	}

	//--------------------------------------------------------------------------------
	//-------METODI PRIVATI CHE FACILITANO L'INTERFACCIAMENTO CON IL WS---------------
	//--------------------------------------------------------------------------------
	
	/**
	 * Chiude la sessione e rimuove la stateful dal ejb container
	 * @throws Exception 
	 * */
	private void closeCart(HttpSession session) throws Exception {
		if(session.getAttribute("cart") != null) {
			getCartManagerSB(session).closeSession(getCartHandle(session));
		} else {
			System.err.println("WARNING: Si e'cercato di chiudere una sessione senza averla mai aperta");
		}
		session.setAttribute("customer", null);
		session.setAttribute("cart", null);
	}
	
	/**
	 * Aggiunge una(1) quantita', del libro passato come parametro, al carrello.
	 * @throws Exception 
	 * */
	private void addBookToCart(HttpSession session, Book book) throws Exception {
		CartManagerSB cartSessionBean = getCartManagerSB(session);
		cartSessionBean.addBook(getCartHandle(session), book);	
	}

	/**
	 * Rimuove una(1) quantita', del libro passato come parametro, dal carrello.
	 * @throws Exception 
	 * */
	private void removeBookFromCart(HttpSession session, Book book) throws Exception {
		CartManagerSB cartSessionBean = getCartManagerSB(session);
		cartSessionBean.removeBookFromCart(getCartHandle(session), book);	
	}
	
	/**
	 * Acquista definitivamente l'ordine.
	 * @throws Exception 
	 * */
	private void shipOrder(HttpSession session, String paymentType) throws Exception {
		CartManagerSB cartSessionBean = getCartManagerSB(session);
		cartSessionBean.shipOrder(getCartHandle(session), paymentType);
	}
	
	/**
	 * Ritorna la lista delle linee di prodotti che compongono il carrello
	 * @throws Exception 
	 * */
	private Collection<LineItem> getLineItemList(HttpSession session) throws Exception {
		CartManagerSB cartSessionBean = getCartManagerSB(session);
		return Arrays.asList(cartSessionBean.getLineItemList(getCartHandle(session)));
	}
	
	/**
	 * Ritorna un riferimento al WS client e se necessario inizializza la stateful bean
	 * @throws Exception 
	 * */
	private CartManagerSBProxy getCartManagerSB(HttpSession session) throws Exception {
		// verifica che sia gia' stato chiamato il metodo initCart altrimenti lo richiama
		if(session.getAttribute("cart") == null)
			initCart(session);
		
		return new CartManagerSBProxy();
	}
	
	/**
	 * Ritorna l'handle
	 * @throws Exception 
	 * */
	private byte[] getCartHandle(HttpSession session) throws Exception {
		if(session.getAttribute("cart") == null)
			initCart(session);
		
		return (byte[]) session.getAttribute("cart");
	}
	
	/**
	 * Crea la stateful tramite WS. Meglio non chiamare mai questo metodo direttamente. 
	 * Servirsi di getCartManagerSB(HttpSession session)
	 * @throws Exception 
	 * */
	private void initCart(HttpSession session) throws Exception {

		if(session.getAttribute("customer") == null) {
			throw new Exception();
		} else {
			CartManagerSB cartSessionBean = new CartManagerSBProxy();
			byte[] cartHandle = cartSessionBean.openSession(((Customer)session.getAttribute("customer")));
			session.setAttribute("cart", cartHandle);
		}
	}
	
	//--------------------------------------------------------------------------------
	//------------------FINE DEI METODI PRIVATI --------------------------------------
	//--------------------------------------------------------------------------------
	
}
