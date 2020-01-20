package Library;

public class Cd extends mediaItem{

	private String artist;
	public Cd(int identity_no, String title, int noC,String genre,String artist) {
		super(identity_no, title, noC, genre);
		// TODO Auto-generated constructor stub
		this.artist=artist;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

}
