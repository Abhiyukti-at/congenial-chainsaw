package training.java.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;


public class ListSort {

	public static void main(String[] args) {
		List<Booklist> book = new ArrayList<Booklist>();
		book.add(new Booklist(115, "HarryPotter", "J.K.rowling", "jkm", 500));
		book.add(new Booklist(120, "HarryPotter part1", "kiing", "fkm", 200));
		book.add(new Booklist(315, "Harch", "c.rowling", "jkm", 150));
		book.add(new Booklist(510, "Narnia", "J.k.Owns", "turm", 4000));
		book.add(new Booklist(215, "Satar","Dinshwa", "Tishta", 24350));

		Iterator itr= book.iterator();
		while(itr.hasNext()) {
			Object obj=itr.next();
			Booklist bo=(Booklist)obj;
			System.out.println(bo);
		}
		
		System.out.print("Enter the sorting value from 1- 5:\n 1.By Book Id\n 2.By Book Name\n 3. By Book Author\n 4.By Book Publisher\n 5.By Book Quantity\n ");
		Scanner sc=new Scanner(System.in);
		int ch=sc.nextInt();
		switch(ch) {
		case 1:
			System.out.println("\nSorted By Book id:\n");
            sorting s=new sorting();
            book.sort(s);
            System.out.println(book);
            break;
		case 2:
			System.out.println("\nSorted By Book Author:\n");
            sortByAuthor s1=new sortByAuthor();
            book.sort(s1);
            System.out.println(book);
            break;
		case 3:
			System.out.println("\nSorted By Book Publisher:\n");
            sortByPublisher s11=new sortByPublisher();
            book.sort(s11);
            System.out.println(book);
            break;
		case 4:
			System.out.println("\nSorted By Book id:\n");
            sortByQuantity s111=new sortByQuantity();
            book.sort(s111);
            System.out.println(book);
            break;
            default:
           System.out.println("\nInvalid Choice\n");
                
		}
	}

}
