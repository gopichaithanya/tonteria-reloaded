package bean.stateless;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bean.entity.Book;
import bean.stateless.interf.BookSBI;

/**
 * @author io
 *
 */
@Remote(bean.stateless.interf.BookSBI.class)
@WebService
@Stateless(mappedName="BookWebService")
public class BookSB implements BookSBI {

	@PersistenceContext(unitName="tbookstoredb")
	private EntityManager em;

	public List<Book> findAllBooks() {
		Query query = em.createQuery("Select b from Book b");
		return (List<Book>) query.getResultList();
	}

	public Book insertBook(String title, String author, String isbn, int price, String editor, 
			String imagePath, String description) {

		Book book = new Book();
		book.setAuthor(author);
		book.setDescription(description);
		book.setIsbn(isbn);
		book.setPrice(price);
		book.setTitle(title);
		book.setImage(imagePath);
		book.setEditor(editor);
		book.setUploadDate(new Date());
		em.persist(book);

		return book;
	}

	/**
	 * Rimuove un libro
	 * */
	public boolean removeBook(String isbn) {

		boolean flag = false;
		Book book  = em.find(Book.class, isbn);
		if(book != null) {
			em.remove(book);
			flag = true;
		}

		return flag;
	}


	/**
	 * Lancia una query di ricerca per tutte le chiavi di ricerca passate come parametro e
	 * su tutti i campi indicati in fields
	 * 
	 * @param searchKey - la parola chiave da ricercare
	 * @param fields - i campi su cui ricercare
	 * @return una lista di oggetti Book che soddisfano i parametri di ricerca	
	 */
	public List<Book> searchBook(List<String> searchKeys, List<String> fields) {
		// creo "l'header" della stringa query
		String queryStr = "SELECT b from Book b WHERE ";

		// costruisco la query
		Iterator<String> keysIterator = searchKeys.iterator();
		String currentSearchKey;

		while (keysIterator.hasNext()) {
			currentSearchKey = keysIterator.next();

			Iterator<String> fieldsIterator = fields.iterator();
			String currentField;

			while (fieldsIterator.hasNext()){
				currentField = fieldsIterator.next();
				queryStr += "b."+currentField+" LIKE '%"+currentSearchKey+"%' OR ";
			}

		}
		//sego l'ultimo OR
		queryStr = queryStr.substring(0, queryStr.length()-4);

		System.out.println(queryStr);
		
		Query query = em.createQuery(queryStr);
		System.out.println(query);
		
		if(query != null)
			return (List<Book>) query.getResultList();
		
		return null;
	}

	public Book getBook(String isbn) {
		System.out.println("[Book SB] Isbn: "+isbn);
		Query query = em.createQuery("SELECT b from Book b WHERE b.isbn = '" + isbn +"'");
		System.out.println("risultato query: "+query.getSingleResult());
		return (Book) query.getSingleResult();
	}

	



}
