package dao;

import java.sql.Connection;
import java.sql.SQLException;

import oracle.jdbc.pool.OracleDataSource;

public class OracleDAOFactory extends DAOFactory {

	public static final String DBURL = 
			"jdbc:oracle:thin:bd2a14/bobas47@ikar.elka.pw.edu.pl:1521/elka.elka.pw.edu.pl";
	
	// method to create Oracle connections
	public static Connection createConnection(){
		OracleDataSource ods;
		Connection conn = null;
		try {
			ods = new OracleDataSource();
			ods.setURL(DBURL);
			conn = ods.getConnection();

		} catch (SQLException e) {
			System.err.println("Could not establish connection do database");
			e.printStackTrace();
		}
		return conn;
	}
	
	@Override
	public ReservationDAO getReservationDAO() {
		return new OracleResevationDAO();
	}

	@Override
	public CustomerDAO getCustomerDAO() {
		return new OracleCustomerDAO();
	}

	@Override
	public VisitDAO getVisitDAO() {
		return new OracleVisitDAO();
	}

	@Override
	public RoomDAO getRoomDAO() {
		return new OracleRoomDAO();
	}

	@Override
	public PaymentDAO getPaymentDAO() {
		return new OraclePaymentDAO();
	}

}
