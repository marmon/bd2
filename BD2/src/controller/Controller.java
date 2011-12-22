package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import oracle.jdbc.pool.OracleDataSource;

public class Controller implements ControllerInterface {
	public static final String URL = "jdbc:oracle:thin:bd2a14/bobas47@ikar.elka.pw.edu.pl:1521/elka.elka.pw.edu.pl";

	private List<ControllerListener> controllerListeners = new LinkedList<ControllerListener>();
	private Connection conn;
	private Statement stmt;
	private PreparedStatement prepStatement;
	
	private ControllerInterface.State state = ControllerInterface.State.DISCONNECTED;
	
	public Controller() {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				ControllerInterface.State currentState = ControllerInterface.State.DISCONNECTED;
				while (true) {
					try {
						if (conn != null) {
							if (conn.isValid(100))
								currentState = ControllerInterface.State.CONNECTED;
							else
								currentState = ControllerInterface.State.DISCONNECTED;
							if (!state.equals(currentState)) {
								state = currentState;
								fireSessionStateChanged(state);
							}
						}
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (Exception e) {
						e.printStackTrace();
						continue;
					}
				}
			}
		});
		t.start();
	}

	@Override
	public void connect() {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					OracleDataSource ods = new OracleDataSource();
					ods.setURL(URL);
					conn = ods.getConnection();
					stmt = conn.createStatement();
					prepStatement = conn
							.prepareStatement("SELECT P.NUMER_POKOJU, PR.OD, PR.DO "
									+ "FROM POKOJE P, POZYCJE_REZERWACJI PR "
									+ "WHERE P.NUMER_POKOJU = PR.NUMER_POKOJU "
									+ "AND PR.DO >= ? "
									+ "AND PR.OD <= ? "
									+ "ORDER BY P.NUMER_POKOJU");
					fireSessionStateChanged(ControllerInterface.State.CONNECTED);
				} catch (SQLException e) {
					fireError(e.getMessage());
					return;
				}

			}

		});
		t.start();
	}

	@Override
	public void disconnect() {
		try {
			prepStatement.close();
			stmt.close();
			conn.close();
			fireSessionStateChanged(ControllerInterface.State.DISCONNECTED);
		} catch (Exception e) {
			return;
		}
	}

	@Override
	public void showBookings(final JTable destTable, final Date from, final Date to) {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					System.out.println("showBookings");
					prepStatement.setDate(1, from);
					prepStatement.setDate(2, to);
					ResultSet rset = prepStatement.executeQuery();
					DefaultTableModel model = new DefaultTableModel();
					destTable.setModel(model);
					model.addColumn("Room");
					model.addColumn("From");
					model.addColumn("To");
					while (rset.next()) {
						System.out.println("addRow");
						model.addRow(new Object[] { rset.getInt(1), rset.getDate(2),
								rset.getDate(3) });
					}
					rset.close();
				} catch (Exception e) {
					fireError(ControllerInterface.State.DISCONNECTED.toString());
					return;
				}
			}
			
		});
		t.start();
	}

	@Override
	public void book(final String firstName,final String lastName,final String documentID,
			final int phoneNumber,final java.sql.Date from,final java.sql.Date to,final int room) throws Exception{
		 
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				OracleDataSource ods;
				Savepoint save = null;
				PreparedStatement addCustomerStmt = null;
				PreparedStatement selectCustomerIDStmt = null;
				PreparedStatement insertReservationStmt = null;
				PreparedStatement selectReservationIDStmt = null;
				PreparedStatement insertPositionStmt = null;
				
				try {
					try {
						ods = new OracleDataSource();
						ods.setURL(URL);
						conn = ods.getConnection();
						fireSessionStateChanged(ControllerInterface.State.CONNECTED);
						
						String insertBasicCustomer = "INSERT INTO BASIC_CUSTOMERS VALUES(?,?,?,?)";
						String selectCustomerID = "SELECT ID_KLIENTA FROM KLIENCI WHERE NUMER_DOKUMENTU = ?";
						String insertReservation = "INSERT INTO REZERWACJE VALUES (null, ?, ?, 0)";
						String selectReservationID = "SELECT ID_REZERWACJI FROM REZERWACJE WHERE ID_KLIENTA = ? AND DATA_ZALOZENIA = ?";
						String insertPosition = "INSERT INTO POZYCJE_REZERWACJI VALUES (?,?,?,?)";
						
						
						
						// first we check if there is a customer with documentID provided
						// if customer credentials are different we raise error
						// 
						
						
						conn.setAutoCommit(false); // begin transaction
						save = conn.setSavepoint();
						// insert customer
						addCustomerStmt = conn.prepareStatement(insertBasicCustomer);
						addCustomerStmt.setString(1, documentID);
						addCustomerStmt.setString(2, firstName);
						addCustomerStmt.setString(3, lastName);
						addCustomerStmt.setInt(4, phoneNumber);
						
						addCustomerStmt.execute();
						
						// get his key
						selectCustomerIDStmt = conn.prepareStatement(selectCustomerID);
						selectCustomerIDStmt.setString(1, documentID);
						
						ResultSet res = selectCustomerIDStmt.executeQuery();
						int customerID = -1;
						while(res.next()){ // .next is needed to actually executeQuery
							customerID = res.getInt(1);
						}
						
						// insert reservation
						insertReservationStmt = conn.prepareStatement(insertReservation);
						insertReservationStmt.setInt(1, customerID);
						insertReservationStmt.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
						
						insertReservationStmt.execute();
						
						// get reservation id
						selectReservationIDStmt = conn.prepareStatement(selectReservationID);
						selectReservationIDStmt.setInt(1, customerID);
						selectReservationIDStmt.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
						
						ResultSet res2 = selectReservationIDStmt.executeQuery();
						int reservationID = -1;
						while(res2.next()){
							reservationID = res2.getInt(1);
						}
						
						// insert reservation's position
						insertPositionStmt = conn.prepareStatement(insertPosition);
						insertPositionStmt.setInt(1, room);
						insertPositionStmt.setInt(2, reservationID);
						insertPositionStmt.setDate(3, from);
						insertPositionStmt.setDate(4, to);
						
						insertPositionStmt.execute();
						
						conn.commit();
						
					} catch (SQLException e) {
						fireError(e.getMessage());
						e.printStackTrace();
						if (conn != null)
							conn.rollback(save);
					} finally {
						conn.setAutoCommit(true);
						addCustomerStmt.close();
						selectCustomerIDStmt.close();
						insertReservationStmt.close();
						selectReservationIDStmt.close();
						insertPositionStmt.close();
					}
				} catch (SQLException e) {
					// TODO 
					e.printStackTrace();
				}
				
				
			}
		});
		t.start();
	}

	public void addControllerListener(ControllerListener cl) {
		controllerListeners.add(cl);
	}

	protected void fireSessionStateChanged(ControllerInterface.State s) {
		for(ControllerListener cl : controllerListeners){
			cl.sessionStateChanged(s);
		}
	}

	protected void fireError(String err) {
		for(ControllerListener cl : controllerListeners){
			cl.error(err);
		}
	}

}
