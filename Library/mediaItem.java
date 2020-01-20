package Library;

public abstract class mediaItem extends Item{
	private String genre;
	public mediaItem(int identity_no, String title, int noC, String genre) {
		super(identity_no, title, noC);
		// TODO Auto-generated constructor stub
		this.setGenre(genre);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

}
