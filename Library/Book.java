package Library;

public class Book extends writtenItem {

	public Book(int identity_no, String title, int noC, String author) {
		super(identity_no, title, noC, author);
		// TODO Auto-generated constructor stub
		
	}

	public String Author(String author) {
//		System.out.println("Author is"+author);
		return author;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Item i = new Book(12, "Don't break", 15);
//		Book b1 = new Book(15, "Demo", 5);
//		b1.Author("Nikita");
//		System.out.println("Object instancs" + b1.getNoC());
//		System.out.println("Object instancs" + i.getTitle());
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}

}
