package bean.stateless;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bean.entity.Book;

@Remote(bean.stateless.BookSBI.class)
@WebService
@Stateless
public class BookSB implements BookSBI {

	@PersistenceContext(unitName="tbookstoredb")
	private EntityManager em;
	
	@Override
	public List<Book> findAllBooks() {
		  Query query = em.createQuery("Select b from Book b");
		  return (List<Book>) query.getResultList();
		 }
	
	@Override
	public Book insertBook(String title, String author, String isbn, int price,
			String description) {
		
		Book book = new Book();
		book.setAuthor(author);
		book.setDescription(description);
		book.setIsbn(isbn);
		book.setPrice(price);
		book.setTitle(title);
		em.persist(book);
		
		return book;
	}
	
	/**
	 * Rimuove un libro
	 * */
	@Override
	public boolean removeBook(String isbn) {
		
		boolean flag = false;
		Book book  = em.find(Book.class, isbn);
		if(book != null) {
			em.remove(book);
			flag = true;
		}
		
		return flag;
	}
	
}
