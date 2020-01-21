package training.java.collection;

import java.util.Comparator;

public class sortByName implements Comparator<Booklist>{

	@Override
		public int compare(Booklist b1, Booklist b2) {
		String name1 = b1.getName();
			String name2= b2.getName();
			int name=name1.compareTo(name2);
			return name	;
		}

}
