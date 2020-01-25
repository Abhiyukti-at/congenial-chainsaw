package Ntier.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Ntier.dao.DBConnection;
import Ntier.pojo.Customer;

public class CustomerServiceImpl implements CustomerService {
	Connection con;
	PreparedStatement pre;
	int ra;
	ResultSet res;
	boolean flag = false;

	public CustomerServiceImpl() {
		con = DBConnection.getConnection();
	}

	// Register a New Customer
	public boolean createCustomer(Customer c) {
		try {

			pre = con.prepareStatement("insert into EcomCustomer values(?,?,?,?,?)");
			pre.setInt(1, c.getId());
			pre.setString(2, c.getName());
			pre.setString(3, c.getEmail());
			pre.setString(4, c.getAddress());
			pre.setString(5, c.getMobileno());

			ra = pre.executeUpdate();
			if (ra > 0)
				flag = true;
			else
				flag = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public ResultSet displayCustomer(int id) {
		try {
			pre = con.prepareStatement("select * from EcomCustomer where id=?");
			pre.setInt(1,id);
			res = pre.executeQuery();
			  while (res.next()) {
	                System.out.println("Customer Id:" + res.getInt("id"));
	                System.out.println("Customer Name:" + res.getString("name"));
	                System.out.println("Customer Email:" + res.getString("email"));
	                System.out.println("Customer Address:" + res.getString("address"));
	                System.out.println("Customer Mobile:" + res.getString("mobile"));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return res;
	}

	public boolean updateCustomer(int id, String add, int mob) {
		try {

//			if (id > 0 && add != null && mob > 0) {
			pre = con.prepareStatement("Update EcomCustomer set address=?, mobile=? where id=?");
			pre.setString(1, add);
			pre.setInt(2, mob);
			pre.setInt(3, id);

			ra = pre.executeUpdate();
			if (ra > 0) {
				flag=true;
			}
				
			else
			{
				flag=false;
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean customerDelete(int cid) {
		try {
			pre = con.prepareStatement("delete from EcomCustomer where id=?");
			pre.setInt(1, cid);
			int ra = pre.executeUpdate();
			if (ra > 0)
				System.out.println("Record Deleted...");
			else
				System.out.println("Record has not Deleted...");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public ResultSet customerLogin(int cid, int mobile) {
		try {
			pre = con.prepareStatement("Select * from EcomCustomer where id=? and mobile=?");
			pre.setInt(1, cid);
			pre.setInt(2, mobile);
			res = pre.executeQuery();
			if (res.next())
				System.out.println("You are Successfully Logged in");
			else
				System.out.println("You are not successfully Logged in");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
}