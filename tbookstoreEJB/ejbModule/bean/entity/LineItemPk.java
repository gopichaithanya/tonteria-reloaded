package bean.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 * @author mattia
 * */

@Embeddable
public class LineItemPk implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2421929530602492691L;
	private Book book;
	private Order order;

	@ManyToOne
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@ManyToOne
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		LineItemPk that = (LineItemPk) o;

		if (book != null ? !book.equals(that.book) : that.book != null) return false;
		if (order != null ? !order.equals(that.order) : that.order != null) return false;

		return true;
	}

	public int hashCode() {
		int result;
		result = (book != null ? book.hashCode() : 0);
		result = 31 * result + (order != null ? order.hashCode() : 0);
		return result;
	}
}


