package training.java.collection;

import java.util.Comparator;

public class sortByAuthor implements Comparator<Booklist>{

	@Override
		public int compare(Booklist b1, Booklist b2) {
			String author1= b1.getAuthor();
			String author2= b2.getAuthor();
			int author=author1.compareTo(author2);
			return author	;
		}

}
