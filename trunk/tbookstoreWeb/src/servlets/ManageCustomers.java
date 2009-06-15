package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.stateless.CustomerSB;
import bean.stateless.CustomerSBProxy;

/**
 * Servlet implementation class manage_customers
 */
public class ManageCustomers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	CustomerSB csb;
	
    /**
     * @see HttpServlet#HttpServlet() 
     */
    public ManageCustomers() {
        super();
        csb = new CustomerSBProxy();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String surname = request.getParameter("surname");
		String choiceStr = request.getParameter("choice");
		
		
		int choice = Integer.parseInt(choiceStr);
		boolean flag = false;
		
		switch(choice) {
		
		case 1:
			flag = csb.createCustomer(name, surname, email, password);
			response.getWriter().write("Account creato con successo");
			break;
		case 2:
			flag = csb.removeCustomer(email, password);
			response.getWriter().write("Account rimosso con successo");
			break;
		case 3:
			csb.login(email, password);
			response.getWriter().write("login avvenuto con successo");
		}
		
	//	response.getWriter().write("i tuoi dati sono: " + name + ", " + password + ", " + email);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
