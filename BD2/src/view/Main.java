package view;

import javax.swing.SwingUtilities;

import controller.Controller;
import controller.ControllerInterface;

public class Main {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		final ControllerInterface controller = new Controller();
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				MainWindow frame = new MainWindow(controller);
				frame.setVisible(true);
			}
		});
	}
}
