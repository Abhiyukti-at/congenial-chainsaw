package training.java.collection;

import java.util.Comparator;

public class sortByPublisher implements Comparator<Booklist>{

	@Override
		public int compare(Booklist b1, Booklist b2) {
			String publish1= b1.getPublisher();
			String publish2= b2.getPublisher();
			int publish=publish1.compareTo(publish2);
			return publish	;
		}
}
