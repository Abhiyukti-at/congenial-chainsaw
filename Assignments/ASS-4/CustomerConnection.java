package threading;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class CustomerConnection {
	int id;
	String fname;
	String lname;
	String address;
	String email;
	Connection con;
	Statement stmt;
	ResultSet res;
	PreparedStatement pre;

	public void addConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "root");
			System.out.println("Database Connected....");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addCustomer() {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter customer id");
			id = sc.nextInt();
			System.out.println("Enter customer First name");
			fname = sc.next();
			System.out.println("Enter customer Last name");
			lname = sc.next();
			System.out.println("Enter customer Address");
			address = sc.next();
			System.out.println("Enter customer Email");
			email = sc.next();

			pre = con.prepareStatement("insert into customer values (?,?,?,?,?)");
			pre.setInt(1, id);
			pre.setString(2, fname);
			pre.setString(3, lname);
			pre.setString(4, address);

			pre.setString(5, email);
			int res = pre.executeUpdate();
			if (res > 0)
				System.out.println("Customer Added SuccessFully");
			else
				System.out.println("Customer is not Added");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ShowCustomers() {
		try {
			stmt = con.createStatement();
			res = stmt.executeQuery("select * from customer");
			while (res.next()) {
				System.out.println("id : " + res.getInt("id"));
				System.out.println("First Name : " + res.getString("fname"));
				System.out.println("Last Name : " + res.getString("lname"));

				System.out.println("Address : " + res.getString("address"));
				System.out.println("Email : " + res.getString("email"));
				System.out.println("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateCustomers() {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter customer id");
			id = sc.nextInt();
			System.out.println("Enter customer changed First name");
			fname = sc.next();
			pre = con.prepareStatement("update customer set fname=? where id=?");
			pre.setString(1, fname);
			pre.setInt(2, id);
			int ra = pre.executeUpdate();
			if (ra > 0)
				System.out.println("Name updated for id " + id);
			else
				System.out.println("Name is not updated..");
			pre.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteCustomers() {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter customer id to Delete");
			id = sc.nextInt();

			pre = con.prepareStatement("delete from customer where id=?");
			pre.setInt(1, id);

			int ra = pre.executeUpdate();
			if (ra > 0)
				System.out.println("Record Deleted...");
			else
				System.out.println("Record has not Deleted...");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		CustomerConnection cust = new CustomerConnection();
		cust.addConnection();
		Scanner sc = new Scanner(System.in);
		String re;
		while (true) {
			System.out.println(
					"\n 1. Add a Customer \n2. Update a customer Name  \n3. Delete a Customer \n4. Show Details of Customer \n 5. Exit");
			System.out.println("Enter Your Choice");
			int ch = sc.nextInt();
			switch (ch) {
			case 1:
				cust.addCustomer();
				break;

			case 2:
				cust.updateCustomers();
				break;
			case 3:
				cust.deleteCustomers();
				break;
			case 4:
				cust.ShowCustomers();
				break;
			case 5:
				System.exit(0);
				break;

			default:
				System.out.println("Invalid Choice");
			}

		}

	}

}
