package Library;

public abstract class writtenItem extends Item {
	private String author;
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public writtenItem(int identity_no, String title, int noC,String author) {
		super(identity_no, title, noC);
		// TODO Auto-generated constructor stub
		this.author=author;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
