package servlets;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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

		Customer c = (Customer) session.getAttribute("customer");

		
		if(c != null && c.getEmail() != null) {

			Collection<LineItem> itemList;

			if(!session.isNew()){

				try {

					itemList = getLineItemList(session);
					request.setAttribute("cartList", itemList);

				} catch (Exception e) {
					System.out.println("[SERVLET Cart] qualcosa è andato storto!");
					e.printStackTrace();
					itemList = null;
				}

				this.forwardRequest(request, response, "/cart/index.jsp");

			}

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

		Customer c = (Customer) session.getAttribute("customer");

		if(c.getEmail() != null) {
			if(!session.isNew()){

				//				Collection<LineItem> itemList;
				BookSB bsb = new BookSBProxy();

				String addIsbn = request.getParameter("addBook");
				String removeIsbn = request.getParameter("removeBook");
				String paymentType = request.getParameter("paymentChoice");

				try {
					if(addIsbn != null){
						Book bookToAdd = bsb.getBook(addIsbn);
						System.out.println(bookToAdd);
						addBookToCart(session, bookToAdd);
						response.getWriter().print("Aggiunto il libro: "+bookToAdd.getTitle());

					} else if(removeIsbn != null){
						Book bookToRemove = bsb.getBook(removeIsbn);
						System.out.println(bookToRemove);
						removeBookFromCart(session, bookToRemove);
						response.getWriter().print("Rimosso il libro: "+bookToRemove.getTitle());
						
					} else if(paymentType != null){
						this.shipOrder(session, paymentType);
						this.closeCart(session);
					} else {
						System.out.println("Fanculoooo!");
					}
				} catch (Exception e){
					System.out.println("Qualcosa non va nella session bean stateful");
				}
				
				this.doGet(request, response);

			}

		}

	}

	/**
	 * Fa il forwarding ad una pagina jsp indicata come parametro
	 * 
	 * @param request
	 * @param response
	 * @param jspPath
	 */
	private void forwardRequest(ServletRequest request, ServletResponse response, String jspPath) {

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jspPath);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
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
	 */
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
		LineItem[] liArray = cartSessionBean.getLineItemList(getCartHandle(session));
		if(liArray != null)
			return Arrays.asList(liArray);
		
		return null;
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
		System.out.println("[CartServlet.initCart()]");
		if(((Customer)session.getAttribute("customer")).getEmail() == null) {
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
