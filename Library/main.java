package Library;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Item[] i = new Item[4];
		i[0] = new Book(3, "Oracle Advance", 5, "Archya din");
		i[1] = new journalPaper(5, "Intra life", 6, "Nirmal", 2018);
		i[2] = new Video(6, "Liar", 7, "Setff", "ermet", 1010);
		i[3] = new Cd(5, "Let me do it", 6, "Soft", "Ed sheeran");
		for (int l = 0; l < i.length; l++) {
			System.out.println("book" + i[l].getTitle());
		}

	}

}
