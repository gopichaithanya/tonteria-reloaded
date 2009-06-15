package bean.stateless.interf;

import java.util.List;

import javax.ejb.Remote;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import bean.entity.Book;

@Remote
@SOAPBinding(style = Style.RPC)
public interface BookSBI {

	public List<Book> findAllBooks();

	public boolean removeBook(String isbn);

//	public List<Book> searchBook(List<String> searchKeys, List<String> fields);

	Book insertBook(String title, String author, String isbn, int price,
			String editor, String imagePath, String description);

	
}
