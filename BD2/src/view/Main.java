package view;

import javax.swing.SwingUtilities;

import controller.Controller;
import controller.ControllerInterface;

public class Main {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				ControllerInterface controller = new Controller();
				MainWindow frame = new MainWindow(controller);
				frame.setVisible(true);
			}
		});
	}
}
