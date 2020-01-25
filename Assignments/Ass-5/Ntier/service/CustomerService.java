package Ntier.service;
import java.sql.ResultSet;

import Ntier.pojo.Customer;

public interface CustomerService {
	public boolean createCustomer(Customer c);
	public ResultSet displayCustomer(int cid);
	public ResultSet customerLogin(int cid, int mobile);
	public boolean updateCustomer(int cid, String add, int mob);
	public boolean customerDelete(int id);

}
