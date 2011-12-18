package controller;

import java.sql.Date;

import javax.swing.JTable;

public interface ControllerInterface {
	public enum State {
		CONNECTED, DISCONNECTED
	};

	public void connect();

	public void disconnect();

	public void showBookings(JTable destTable, Date from, Date to);

	public void addControllerListener(ControllerListener cl);
}
