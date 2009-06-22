package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.stateless.Customer;
import bean.stateless.CustomerSB;
import bean.stateless.CustomerSBProxy;

/**
 * Servlet implementation class validateuser
 */
public class ValidateUser extends HttpServlet {

	public ValidateUser(){
		super();

	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);

		/*
		 * nel caso l'utente sia gia loggato verifico il caso in cui abbia
		 * fatto logout o che abbia fatto refresh nella servlet
		 * 
		 */
		if(((Customer) session.getAttribute("customer")).getName() != null) {
			System.out.println("Un utente è gia loggato! -> "+ ((Customer)session.getAttribute("customer")).getName());

			if(request.getParameter("logout") != null)
				session.removeAttribute("customer");

			forwardRequest(request, response);
		} else { 

			/*
			 * Nel caso non ci sia una sessione utente, verifico i dati
			 */
			String username = request.getParameter("username");
			String password = request.getParameter("password");	

			System.out.println(username + " - "+ password);


			if (username == null || password == null || username.equals("") || password.equals("")){

				showError("One or more fields null!", request, response);

			} else {
				CustomerSB csb = new CustomerSBProxy();
				Customer loggedCustomer = csb.login(username, password);

				System.out.println("");

				if (loggedCustomer != null){

					showUserPage(loggedCustomer, request, response);
					System.out.println(loggedCustomer.getName() +" loggato!");

				} else {

					showError("Wrong username or password!", request, response);
					System.out.println("username o password errati");

				}
			}
		}
	}


	private void showUserPage(Customer customer, HttpServletRequest request,
			HttpServletResponse response) {

		//		Customer loggedCustomer = customer;
		//		HttpSession session = request.getSession(true);
		request.getSession(true).setAttribute("customer", customer);


		forwardRequest(request, response);
		//		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
		//		try {
		//			dispatcher.forward(request, response);
		//
		//		} catch (ServletException e) {
		//
		//			e.printStackTrace();
		//		} catch (IOException e) {
		//
		//			e.printStackTrace();
		//		}
	}

	private void forwardRequest(ServletRequest request, ServletResponse response) {

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private void showError(String errorMessage, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		request.setAttribute("failedMessage", errorMessage);
		session.setAttribute("customer", null);

		forwardRequest(request, response);

	}

}