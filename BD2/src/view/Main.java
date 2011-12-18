package view;

import java.awt.EventQueue;

import controller.Controller;
import controller.ControllerInterface;

public class Main {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				ControllerInterface controller = new Controller();
				MainWindow frame = new MainWindow(controller);
				frame.setVisible(true);
			}
		});
	}
}
