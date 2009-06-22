package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.stateless.Book;
import bean.stateless.BookSB;
import bean.stateless.BookSBProxy;

/**
 * Servlet implementation class Viewer
 */
public class Viewer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Viewer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String gettedIsbn = request.getParameter("isbn");
		
		if(gettedIsbn != null){
			BookSB bsb = new BookSBProxy();
			Book book = bsb.getBook(gettedIsbn);
			request.setAttribute("book", book);
		} else {
			this.forwardRequest(request, response, "/index.jsp");
			// non serve a un cazzo!
			return;
		}
		
		this.forwardRequest(request, response, "/view.jsp");

	}

	/**
	 * Se arriva da post lo redirigo all'index
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.forwardRequest(request, response, "/index.jsp");
	}

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

}
