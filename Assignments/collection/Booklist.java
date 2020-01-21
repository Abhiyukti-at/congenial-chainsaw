package training.java.collection;

public class Booklist {

	private int id;
	private String author;
	private String name;
	public Booklist(int id,String name, String author, String publisher, int quantity) {
		super();
		this.author = author;
		this.name=name;
		this.publisher = publisher;
		this.quantity = quantity;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String publisher;
	private int quantity;

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "\nBooklist [id=" + id + ", author=" + author + ", publisher=" + publisher + ", quantity=" + quantity
				+ "]";
	}

	
}
