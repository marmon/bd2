package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import dao.Customer;
import dao.CustomerDAO;
import dao.DAOFactory;

public class CustomerDAOTest {


	// if you want to run this test first thing you need to do is check manually how many
	// entries there are in database and then assign proper values to asserts
	@Test
	public void testInsertCustomer(){
		DAOFactory oracleDAOFactory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
		CustomerDAO customerDAO = oracleDAOFactory.getCustomerDAO();
		
		Customer c = new Customer("dokument2223", "mietek33231", "iksinsk11i", 423232244);
		int id = customerDAO.insertCustomer(c);
		
		assertEquals(id, 2);
		
		List lista = customerDAO.selectCustomersTO();
		assertEquals(lista.size(), 2);
	}

//	@Test
//	public void testSelectCustomersTO() {
//		DAOFactory oracleDAOFactory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
//		CustomerDAO customerDAO = oracleDAOFactory.getCustomerDAO();
//		
//		
//		//for(Customer c : customerDAO.selectCustomersTO()){	}
//	}
	
}
