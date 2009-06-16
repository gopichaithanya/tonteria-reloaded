package bean.entity;


import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

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




	@EmbeddedId
	public LineItemPk getPk() {
		return pk;
	}

	private void setPk(LineItemPk pk) {
		this.pk = pk;
	}

	@Column(name = "quantity")
	public long getQuantity() {
		return quantity;
	}
	
	public void setQuantity(long quantity) {
		this.quantity = quantity;
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
