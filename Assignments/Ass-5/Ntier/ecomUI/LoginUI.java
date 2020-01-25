package Ntier.ecomUI;

import Ntier.pojo.Customer;
import Ntier.service.CustomerService;
import Ntier.service.CustomerServiceImpl;
import java.util.Scanner;

public class LoginUI {
	Customer c;
	Scanner sc;
	CustomerService service;
	
	public LoginUI() {
		sc = new Scanner(System.in);
		c = new Customer();
		service = new CustomerServiceImpl();
		
	}
	public void login() {
		int cid;
		int mobile;
		System.out.println("Enter Customer id.");
        cid = sc.nextInt();
       
        System.out.println("Enter Customer Mobileno.");
        mobile = sc.nextInt();
        service.customerLogin(cid,mobile);
			
	}

	public static void main(String[] args) {
		LoginUI l=new LoginUI();
		Scanner sc = new Scanner(System.in);
		int ch;
		
		System.out.println("Enter Your Choice");
        System.out.println("1. Registring New Customer");
        System.out.println("2. Login as Existing Customer");
        System.out.println("3. Exit");
        ch=sc.nextInt();
        switch (ch) {
		case 2: {
			l.login();
			break;
		}
		
        }
		
	}

}
