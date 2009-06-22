package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import bean.stateful.SearchSFBI;
import bean.stateless.Book;
import bean.stateless.BookSB;
import bean.stateless.BookSBProxy;

/**
 * Servlet implementation class Search
 */
public class Search extends HttpServlet {

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Search() {
		super();
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ottengo e memorizzo le chiavi di ricerca da ricerca semplice e da quella avanzata
		String simpleSearchParam = request.getParameter("searchsubmit");
		String advancedSearchParam = request.getParameter("advancedsearchsubmit");
		String searchKeys = request.getParameter("s");
		

	
		// setto i campi in cui cercare
		List<String> fields = new ArrayList<String>();
		
		// DA OTTIMIZZARE!
		if(simpleSearchParam != null && !simpleSearchParam.isEmpty()){
			// ricerca semplice
			fields.add("author");
			fields.add("title");
			
		}else if (advancedSearchParam != null && !advancedSearchParam.isEmpty()){
			// ricerca avanzata
			String choiceStr = request.getParameter("choice");
			System.out.println(choiceStr);
			if(!choiceStr.equals("both")){
				fields.add(choiceStr);
			}else{
				fields.add("author");
				fields.add("title");
			}
				
			
		}else{
			// qualcuno sta rompendo il cazzo (non viene dalle pagine index.jsp o search.jsp)
			System.out.println("rompicazzo?");
			request.setAttribute("errormessage", "Non puoi accedere a questa pagina direttamente!");
			forwardRequest(request, response, "/error/index.jsp");
		}
		
		System.out.println("Cercherò \""+searchKeys+"\" in "+fields);
		
		Book[] books;
		BookSB bsb = new BookSBProxy();
		if(searchKeys.isEmpty()){
			books = bsb.findAllBooks();
		} else {
			// l'input text ha il nome sempre uguale in entrambe le tipologie di ricerca
			List<String> keys = popolateKeysList(searchKeys);
			books = bsb.searchBook(keys.toArray(new String[0]), fields.toArray(new String[0]));
		}
		
		ArrayList<String> booksList = null;
		if(books != null)
			booksList =  new ArrayList(Arrays.asList(books));
		
		System.out.println(booksList);
		
//		HttpSession session = request.getSession(true);
		// lo metto in request, non in session
		request.setAttribute("searchResults", booksList);
		
//		System.out.println("Path da cui viene: "+request.getRequestURL()+ "Uri: "+request.getRequestURI());
		

		this.forwardRequest(request, response, "/search/index.jsp");
		
//		if(books == null)
//			response.getWriter().println("niente libbbri!");
//		else
//			response.getWriter().println(books);

	}


	/**
	 * Popola una lista di parole chiave, separando la stringa ricevuta in input.
	 * Il separatore utilizzato per definire le diverse parole chiave è lo spazio.
	 * 
	 * @param searchKeys
	 * @return ogni parola chiave in una lista
	 */
	private List<String> popolateKeysList(String searchKeys) {
		
		StringTokenizer st = new StringTokenizer(searchKeys);
		
		List<String> keysList = new ArrayList<String>();
		
		while (st.hasMoreTokens()) {
			keysList.add(st.nextToken());
			// Debug
			System.out.print(keysList.get(keysList.size()-1)+" - ");
		}
		return keysList;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("errormessage", "Non puoi accedere a questa pagina con Post!");
		forwardRequest(request, response, "/error/index.jsp");
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

}
