package set;

import java.util.ArrayList;
import java.util.HashSet;

public class ElectDetail {

	public static void main(String[] args) {

		HashSet<ElectionPojo> elect = new HashSet<ElectionPojo>();
		elect.add(new ElectionPojo(1, "Aap", "15-2020", "Jaipur", 5, 12));
		elect.add(new ElectionPojo(2, "BJP", "18-2020", "Raipur", 8, 23));
		elect.add(new ElectionPojo(1, "Aap", "15-2020", "Jaipur", 5, 12));
		elect.add(new ElectionPojo(3, "Congress", "13-2020", "Up", 12, 30));
		elect.add(new ElectionPojo(4, "Nitara", "27-2020", "Mijorum", 23, 8));

		System.out.println(elect);

		ArrayList<Integer> elect1 = new ArrayList<Integer>();
		ArrayList<Integer> commonDate = new ArrayList<Integer>();
		for (ElectionPojo e : elect) {
		    if (elect1.contains(e.getCountDate())) {
		        System.out.println("Parties having same date:");
		        commonDate.add(e.getCountDate());
		        }
		    elect1.add(e.getCountDate());
		}
		
		
		
		for (ElectionPojo e1 : elect) {
		    if (commonDate.contains(e1.getCountDate())) {
		        System.out.println(e1);
		    }
		}
		}
	 

		
		

	}




