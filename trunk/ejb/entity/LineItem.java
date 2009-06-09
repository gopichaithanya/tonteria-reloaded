package bean.entity;


import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "LineItem")
@AssociationOverrides({
	@AssociationOverride(name = "pk.order", joinColumns = @JoinColumn(name = "order_id")),
	@AssociationOverride(name = "pk.book", joinColumns = @JoinColumn(name = "isbn")) 
})
public class LineItem implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4756154397300618782L;
	private Book book;
	private Order order;
	private LineItemPk pk = new LineItemPk();




	@EmbeddedId
	private LineItemPk getPk() {
		return pk;
	}

	private void setPk(LineItemPk pk) {
		this.pk = pk;
	}


	@Transient
	public Book getBook()
	{
		return book;
	}

	public void setBook(Book book)
	{
		this.book = book;
	}


	@Transient
	public Order getOrder()
	{
		return order;
	}

	public void setOrder(Order order)
	{
		this.order = order;
	}

	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		LineItem that = (LineItem) o;

		if (getPk() != null ? !getPk().equals(that.getPk()) : that.getPk() != null) return false;

		return true;
	}

	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}
}
