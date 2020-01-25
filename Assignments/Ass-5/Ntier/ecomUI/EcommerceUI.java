package Ntier.ecomUI;

import Ntier.pojo.Customer;
import Ntier.service.CustomerService;
import Ntier.service.CustomerServiceImpl;
import java.util.Scanner;

public class EcommerceUI {

	Customer c;
	Scanner sc;
	CustomerService service;

	public EcommerceUI() {
		sc = new Scanner(System.in);
		c = new Customer();
		service = new CustomerServiceImpl();
	}

	public void display() {
		int id;
		System.out.println("Enter Customer id.");
		id = sc.nextInt();
		service.displayCustomer(id);
	}

	public void login() {
		int cid;
		int mobile;
		System.out.println("Enter Customer id.");
		cid = sc.nextInt();

		System.out.println("Enter Customer Mobileno.");
		mobile = sc.nextInt();
		service.customerLogin(cid, mobile);

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String ch = null;
		EcommerceUI e = new EcommerceUI();

		while (true) {

			System.out.println("Enter Your Choice");
			System.out.println("1. Registring New Customer");
			System.out.println("2. Login as Existing Customer");
			System.out.println("3. Update an Existing Customer");
			System.out.println("4. Delete an Existing Customer");
			System.out.println("5. Display details of Customer");
			System.out.println("6. Exit");
			ch = sc.next();
			switch (ch) {
			case "1": {
				e.registerCustomer();
				break;
			}
			case "2": {
				e.login();
				break;
			}
			case "3": {
				e.updateCustom();
				break;
			}
			case "4": {
				e.deleteCustom();
				break;
			}
			case "5": {
				e.display();
				break;
			}
			case "6": {
				System.exit(0);
			}
			}
		}
	}

	public void registerCustomer() {
		System.out.println("Enter Customer id.");
		c.setId(sc.nextInt());
		System.out.println("Enter Customer Name.");
		c.setName(sc.next());
		System.out.println("Enter Customer email.");
		c.setEmail(sc.next());
		System.out.println("Enter Customer address.");
		c.setAddress(sc.next());
		System.out.println("Enter Customer Mobileno.");
		c.setMobileno(sc.next());
		if (service.createCustomer(c))
			System.out.println("Customer registered successfully...");
		else
			System.out.println("Customer Not registered ...");

	}

	public void updateCustom() {
		System.out.println("Enter Customer id to update Adress and Mobile No");
		System.out.println("Enter Customer id.");
		int cid = sc.nextInt();
		System.out.println("Enter Customer address.");
		String add = sc.next();
		System.out.println("Enter Customer Mobileno.");
		int mob = sc.nextInt();
		if (service.updateCustomer(cid, add, mob))
			System.out.println("Customer updated successfully...");
		else
			System.out.println("Customer Not updated ...");
	}

	public void deleteCustom() {
		int id;
		System.out.println("Enter Customer id.");
		id = sc.nextInt();
		service.customerDelete(id);
	}
}
