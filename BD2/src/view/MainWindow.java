package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.ControllerInterface;
import controller.ControllerListener;

import java.awt.*;
import java.awt.event.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTextField fromTextField;
	private JTextField toTextField;
	private JLabel stateLabel;
	private JMenuItem mntmConnect;
	private JMenuItem mntmDisconnect;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public MainWindow(final ControllerInterface controller) {
		setTitle("Booking System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 799, 632);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		mntmConnect = new JMenuItem("Connect");
		mnFile.add(mntmConnect);

		mntmDisconnect = new JMenuItem("Disconnect");
		mnFile.add(mntmDisconnect);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);

		JPanel bookingsPanel = new JPanel();
		tabbedPane.addTab("Bookings", null, bookingsPanel, null);
		bookingsPanel.setLayout(new BorderLayout(0, 0));

		JPanel northPanel = new JPanel();
		FlowLayout fl_northPanel = (FlowLayout) northPanel.getLayout();
		fl_northPanel.setAlignment(FlowLayout.LEFT);
		bookingsPanel.add(northPanel, BorderLayout.NORTH);

		JLabel fromLabel = new JLabel("From:");
		northPanel.add(fromLabel);

		fromTextField = new JTextField();
		northPanel.add(fromTextField);
		fromTextField.setColumns(10);

		JLabel toLabel = new JLabel("To:");
		northPanel.add(toLabel);

		toTextField = new JTextField();
		northPanel.add(toTextField);
		toTextField.setColumns(10);

		JButton showButton = new JButton("Show");
		northPanel.add(showButton);

		JLabel dateInfoLabel = new JLabel("Type date in \"yyyy-MM-dd\" format.");
		northPanel.add(dateInfoLabel);

		table = new JTable();
		JScrollPane scrollPane = new JScrollPane(table);
		bookingsPanel.add(scrollPane, BorderLayout.CENTER);

		JPanel bookPanel = new JPanel();
		tabbedPane.addTab("Book", null, bookPanel, null);

		JPanel southPanel = new JPanel();
		FlowLayout fl_southPanel = (FlowLayout) southPanel.getLayout();
		fl_southPanel.setAlignment(FlowLayout.RIGHT);
		contentPane.add(southPanel, BorderLayout.SOUTH);

		stateLabel = new JLabel(
				ControllerInterface.State.DISCONNECTED.toString());
		southPanel.add(stateLabel);

		controller.addControllerListener(new ControllerListener() {

			@Override
			public void error(String err) {
				System.out.println("error");
				JOptionPane.showMessageDialog(MainWindow.this, err, "Error",
						JOptionPane.ERROR_MESSAGE);
			}

			@Override
			public void sessionStateChanged(ControllerInterface.State s) {
				switch (s) {
				case CONNECTED:
					connected();
					break;
				case DISCONNECTED:
					disconnected();
					break;
				}
			}

		});

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent evt) {
				controller.disconnect();
			}
		});

		AbstractAction connectAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				controller.connect();
			}
		};

		mntmConnect.addActionListener(connectAction);

		AbstractAction disconnectAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				controller.disconnect();
			}
		};

		mntmDisconnect.addActionListener(disconnectAction);

		AbstractAction exitAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				controller.disconnect();
				System.exit(0);
			}
		};

		mntmExit.addActionListener(exitAction);

		AbstractAction aboutAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				JOptionPane
						.showMessageDialog(
								MainWindow.this,
								"Booking System\n\nAutors:\nMarek Jasiński\nMarek Lewandowski",
								"About", JOptionPane.INFORMATION_MESSAGE);
			}
		};

		mntmAbout.addActionListener(aboutAction);

		AbstractAction showAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				try {
					System.out.println("showAction");

					DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");

					java.util.Date parsedFromDate = formater
							.parse(fromTextField.getText());
					java.sql.Date fromDate = new java.sql.Date(
							parsedFromDate.getTime());

					java.util.Date parsedToDate = formater.parse(toTextField
							.getText());
					java.sql.Date toDate = new java.sql.Date(
							parsedToDate.getTime());

					table.setModel(new DefaultTableModel());
					controller.showBookings(table, fromDate, toDate);
				} catch (Exception e) {
					System.err.println(e.getMessage());
					JOptionPane.showMessageDialog(MainWindow.this,
							e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		};

		fromTextField.addActionListener(showAction);
		toTextField.addActionListener(showAction);
		showButton.addActionListener(showAction);

		// Initialization
		disconnected();
	}

	protected void disconnected() {
		mntmDisconnect.setEnabled(false);
		mntmConnect.setEnabled(true);
		stateLabel.setForeground(Color.RED);
		stateLabel.setText(ControllerInterface.State.DISCONNECTED.toString());
	}

	protected void connected() {
		mntmConnect.setEnabled(false);
		mntmDisconnect.setEnabled(true);
		stateLabel.setForeground(Color.GREEN);
		stateLabel.setText(ControllerInterface.State.CONNECTED.toString());
	}

}
