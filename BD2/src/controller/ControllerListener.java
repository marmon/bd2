package controller;

import java.util.EventListener;

public interface ControllerListener extends EventListener {
	public void error(String err);

	public void sessionStateChanged(Controller.State state);
	
	public void newReservationAdded();
}
