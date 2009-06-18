package bean.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import org.apache.commons.lang.builder.HashCodeBuilder;

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

	public boolean equals(Object arg0) {
		
		boolean flag = false;
		if(arg0 != null && arg0 instanceof LineItemPk){
			final LineItemPk lipk = (LineItemPk)arg0;
			if( this.book.equals(lipk.getBook()) && this.order.equals(lipk.getOrder()) )
				flag = true;
		}
		return flag;
	}

	@Override
	public int hashCode() {

		final HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(this.getBook());
		builder.append(this.getOrder());
		
		return builder.toHashCode();
	}

}


