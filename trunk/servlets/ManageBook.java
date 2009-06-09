package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.stateless.BookSB;
import bean.stateless.BookSBProxy;

/**
 * Servlet implementation class ManageBook
 */
public class ManageBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	BookSB bsb;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageBook() {
        super();
         bsb = new BookSBProxy();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String description = request.getParameter("description");
		String isbn = request.getParameter("isbn");
		int price = Integer.parseInt(request.getParameter("price"));
		String choiceStr = request.getParameter("choice");
		
		
		int choice = Integer.parseInt(choiceStr);
	//	boolean flag = false;
		
		switch(choice) {
		
		case 1:
			 bsb.insertBook(title, author, isbn, price, description);
			response.getWriter().write("Libro inserito con successo");
			break;
		case 2:
			//flag = csb.removeCustomer(email, password);
			response.getWriter().write("Non ancora implementata");
			break;
		case 3:
		//	csb.login(email, password);
			response.getWriter().write("Non ancora implementata");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
