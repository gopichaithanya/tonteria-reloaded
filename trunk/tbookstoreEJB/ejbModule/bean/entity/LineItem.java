package bean.entity;


import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "LineItem")
@AssociationOverrides({
	@AssociationOverride(name = "pk.order", joinColumns = @JoinColumn(name = "torder")),
	@AssociationOverride(name = "pk.book", joinColumns = @JoinColumn(name = "book")) 
})
public class LineItem implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4756154397300618782L;
	private long quantity;
	private LineItemPk pk = new LineItemPk();
	private Book book;


	@Transient
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		getPk().setBook(book);
		this.book = book;
	}

	@EmbeddedId
	public LineItemPk getPk() {
		return pk;
	}

	public void setPk(LineItemPk pk) {
		this.pk = pk;
	}

	@Column(name = "quantity")
	public long getQuantity() {
		return quantity;
	}
	
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}


	@Override
	public boolean equals(Object arg0) {
		boolean flag = false;
		if(arg0 != null && arg0 instanceof LineItem){
			final LineItem li = (LineItem)arg0;
			if(this.pk.equals(li.getPk()))
				flag = true;
		}
		return flag;
	}


	@Override
	public int hashCode() {

		final HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(this.quantity);
		builder.append(this.getPk());
		
		return builder.toHashCode();
	}

}
