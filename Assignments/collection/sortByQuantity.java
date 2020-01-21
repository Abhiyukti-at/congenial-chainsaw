package training.java.collection;

import java.util.Comparator;

public class sortByQuantity implements Comparator<Booklist>{

	@Override
		public int compare(Booklist b1, Booklist b2) {
			int quan=(int)b1.getQuantity()- b2.getQuantity();
			return quan	;
		}
}
