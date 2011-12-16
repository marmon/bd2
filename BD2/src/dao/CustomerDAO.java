package dao;

import java.sql.ResultSet;
import java.util.List;


public interface CustomerDAO {
	  public int insertCustomer(Customer customer);
	  public boolean deleteCustomer(Customer customer);
	  public Customer findCustomer(int id);
	  public boolean updateCustomer(Customer customer);
	  public ResultSet selectCustomersRS();
	  public List<Customer> selectCustomersTO(); // what criteria?

}
