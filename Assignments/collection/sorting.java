package training.java.collection;

import java.util.Comparator;

public class sorting implements Comparator<Booklist>{

	@Override
		public int compare(Booklist b1, Booklist b2) {
		int id=(int)b1.getId()- b2.getId();

			return id	;
		}
	
}
