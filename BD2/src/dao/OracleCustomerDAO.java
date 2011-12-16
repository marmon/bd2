package dao;

import java.util.Collection;

import javax.sql.RowSet;

public class OracleCustomerDAO implements CustomerDAO {

	@Override
	public int insertCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean deleteCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Customer findCustomer(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public RowSet selectCustomersRS() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection selectCustomersTO() {
		// TODO Auto-generated method stub
		return null;
	}

}
