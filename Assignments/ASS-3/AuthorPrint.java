package set;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.HashMap;

public class AuthorPrint {

	public void displayAll(TreeMap<String, Author> books) {
		System.out.println(books);
	}

	public void displayAuthors(TreeMap<String, Author> books) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		Set<String> keys = books.keySet();
		Author author;
		for (String s : keys) {
			author = books.get(s);
			String s1 = author.getName();

			if (!map.containsKey(s1)) {
				map.put(s1, 1);
			} else {
				int count = map.get(s1);
				map.put(s1, count + 1);
				System.out.println(s1);
			}
		}
	}

	public static void main(String[] args) {
		TreeMap<String, Author> auth = new TreeMap<String, Author>();
		auth.put("Linux", new Author(12, "Juhi", "Jodhpur"));
		auth.put("Operation", new Author(15, "Komal", "Kota"));
		auth.put("Database", new Author(18, "Sonu", "U.S.A"));
		auth.put("Newton", new Author(20, "Kapil", "Newziland"));
		auth.put("construcion", new Author(22, "Saty", "Rohtang"));
		auth.put("See the World", new Author(15, "Komal", "Kota"));
		auth.put("Datastrucure", new Author(67, "Rohan", "Jaipur"));
		System.out.println(auth);

		AuthorPrint p = new AuthorPrint();
		p.displayAll(auth);
		p.displayAuthors(auth);
	}

}
