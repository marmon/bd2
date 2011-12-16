package dao;

// Abstract class DAO Factory
public abstract class DAOFactory {

	// List of DAO types supported by the factory
	public static final int ORACLE = 1;
	
	// There will be a method for each DAO that can be created.
	// The concrete factories will have to implement these methods.
	public abstract ReservationDAO getReservationDAO();
	public abstract CustomerDAO getCustomerDAO();
	public abstract VisitDAO getVisitDAO();
	public abstract RoomDAO getRoomDAO(); // not sure if this is even needed
	public abstract PaymentDAO getPaymentDAO();
	
	public static DAOFactory getDAOFactory(int whichFactory){
		
		switch(whichFactory){
			case ORACLE :
				return new OracleDAOFactory();
			default :
				return null;
		}
	}
}
