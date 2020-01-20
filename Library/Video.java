package Library;

public class Video extends mediaItem {
	private String director;
	private int year;

	public Video(int identity_no, String title, int noC, String genre,String director,int year) {
		super(identity_no, title, noC, genre);
		// TODO Auto-generated constructor stub
		this.director=director;
		this.year= year;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
