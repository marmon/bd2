package dao;

import java.util.Collection;

import javax.sql.RowSet;

public interface CustomerDAO {
	  public int insertCustomer(Customer customer);
	  public boolean deleteCustomer(Customer customer);
	  public Customer findCustomer(int id);
	  public boolean updateCustomer(Customer customer);
	  public RowSet selectCustomersRS();
	  public Collection selectCustomersTO(); // what criteria?

}
