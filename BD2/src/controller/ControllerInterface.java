package controller;

import java.sql.Date;

import javax.swing.JTable;

public interface ControllerInterface {
	public enum State {
		CONNECTED, DISCONNECTED
	};

	public void connect(String login, String password);

	public void disconnect();

	public void showBookings(JTable destTable, Date from, Date to);

	public void book(String firstName, String lastName, String documentID,
			int phoneNumber, Date from, Date to, int room) throws Exception;
	public void addControllerListener(ControllerListener cl);
}
