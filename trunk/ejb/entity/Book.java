package bean.entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Book")
public class Book implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7734237603034279293L;
	private int price;
	private String isbn;
	private String title;
	private String author;
	private String description;
	private List<LineItem> productItems = new LinkedList<LineItem>();
	
	@Column(name = "price")
	public int getPrice() {
		return price;
	}
	
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	@Id 
	@Column(name = "isbn", nullable = false)
	public String getIsbn() {
		return isbn;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	@Column(name = "title")
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "author")
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.book")
	public List<LineItem> getProductItems() {
		return productItems;
	}
	
	public void setProductItems(List<LineItem> productItems) {
		this.productItems = productItems;
	}

}
