package com.robomq;

import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.robomq.config.AppConfig;
import com.robomq.dao.MobileDAO;
import com.robomq.pojo.Mobile;

public class MobileJDBCMain {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		MobileDAO mobileDAO = context.getBean(MobileDAO.class);
		Scanner sc = new Scanner(System.in);

// Display All Mobiles
		System.out.println("List of person is:");

		for (Mobile m : mobileDAO.getAllMobile()) {
			System.out.println(m);
		}
///Display with specific Id
		System.out.println("\nGet Mobile with ID.\nEnter the id:");
		int id = sc.nextInt();
		System.out.println("\nGet person with ID " + id);

		Mobile mobileById = mobileDAO.getMobileById(id);

		System.out.println(mobileById);

		// Create Mobile

		System.out.println("\nCreating Mobile Record: ");
		System.out.println("Enter mobile id:");
		int id1 = sc.nextInt();
		System.out.println("Enter mobile name:");
		String name = sc.next();
		System.out.println("Enter mobile price:");
		int price = sc.nextInt();
		System.out.println("Enter mobile quantity:");
		int qty = sc.nextInt();
		System.out.println("Enter mobile description:");
		String desc = sc.next();
		Mobile mobile = new Mobile(id1, name, price, qty, desc);
		System.out.println(mobile);
		mobileDAO.createMobile(mobile);

		System.out.println("\nList of person is:");

		for (Mobile c : mobileDAO.getAllMobile()) {
			System.out.println(c);
		}
		// Delete Mobile

		System.out.println("\nEnter the Mobile id to be deleted:");
		int id2 = sc.nextInt();
		System.out.println("\nDeleting person with ID" + id2);
		Mobile mobileById1 = mobileDAO.getMobileById(id2);
        mobileDAO.deleteMobile(mobileById1);
        System.out.println("Record Deleted.");
        
		// Update Specific Mobile
		System.out.println("Enter mobile id to be updated:");
		int id3 = sc.nextInt();
		System.out.println("Enter mobile name:");
		String name_new = sc.next();
		System.out.println("Enter mobile price:");
		int price_new = sc.nextInt();
		System.out.println("Enter mobile quantity:");
		int qty_new = sc.nextInt();
		System.out.println("Enter mobile description:");
		String desc_new = sc.next();

		System.out.println("\nUpdate person with ID " + id3);

		Mobile mob = mobileDAO.getMobileById(id3);
		mob.setName(name_new);
		mob.setPrice(price_new);
		mob.setQty(qty_new);
		mob.setDesc(desc_new);
		mobileDAO.updateMobile(mob);

		System.out.println("\nList of person is:");
		for (Mobile c : mobileDAO.getAllMobile()) {
			System.out.println(c);
		}

		context.close();
	}

}
