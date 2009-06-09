package bean.stateless;

import java.util.List;

import javax.ejb.Remote;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import bean.entity.Book;

@Remote
@SOAPBinding(style = Style.RPC)
public interface BookSBI {

	public List<Book> findAllBooks();

	public Book insertBook(String title, String author, String isbn, int price, String description);

	boolean removeBook(String isbn);

	
	
}
