package bean.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.HashCodeBuilder;

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
	private String editor;
	private String image;
	private String description;
	private Date uploadDate;
	//private List<LineItem> productItems = new LinkedList<LineItem>();
	
	@Column(name = "price", nullable = false)
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
	
	@Column(name = "title", nullable = false)
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "author", nullable = false)
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	@Column(name = "description", nullable = false)
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

/*	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.book")
	public List<LineItem> getProductItems() {
		return productItems;
	}
	
	public void setProductItems(List<LineItem> productItems) {
		this.productItems = productItems;
	}*/

	@Column(name = "editor", nullable = false)
	public String getEditor() {
		return editor;
	}


	public void setEditor(String editor) {
		this.editor = editor;
	}

	@Column(name = "image", nullable = false)
	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}

	@Column(name = "upload_date", nullable = false)
	@Temporal(value = TemporalType.TIMESTAMP)
	public Date getUploadDate() {
		return uploadDate;
	}


	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}


	@Override
	public boolean equals(Object arg0) {
		boolean flag = false;
		if(arg0 != null && arg0 instanceof Book){
			final Book b = (Book)arg0;
			if(this.isbn.equals(b.getIsbn()))
				flag = true;
		}
		return flag;
	}


	@Override
	public int hashCode() {

		final HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(this.author);
		builder.append(this.isbn);
		builder.append(this.title);
		builder.append(this.price);
		builder.append(this.editor);
		builder.append(this.description);
		builder.append(this.image);
		return builder.toHashCode();
	}


	
/*	public boolean equals(Object o) {
		
	 boolean flag = true;

	 if (this != o) {
		 
		 if (o == null || getClass() != o.getClass())
			 flag = false;
		 
		 if(flag)
			 if (getIsbn().equals(((Book)o).getIsbn())) 
				 flag = false;
	 }
	 return flag;
	}*/

}
