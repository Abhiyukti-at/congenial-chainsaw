package Library;

public class journalPaper extends writtenItem {
	private int yearPublish;

	public journalPaper(int identity_no, String title, int noC,String author, int yearPublish) {
		super(identity_no, title, noC, author);
		// TODO Auto-generated constructor stub
		this.setYearPublish(yearPublish);
	}

	public String Author(String author) {
		// System.out.println("Author is"+author);
		return author;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		journalPaper j1 = new journalPaper(11, "Data Anlytics", 13);
//		j1.Author("Nivedita");

	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}

	public int getYearPublish() {
		return yearPublish;
	}

	public void setYearPublish(int yearPublish) {
		this.yearPublish = yearPublish;
	}

}
