package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.ControllerInterface;
import controller.ControllerListener;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTextField fromTextField;
	private JTextField toTextField;
	private JLabel stateLabel;
	private JMenuItem mntmConnect;
	private JMenuItem mntmDisconnect;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

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
		bookPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		bookPanel.add(panel, BorderLayout.SOUTH);
		
		JButton btnAdd = new JButton("Add");
		panel.add(btnAdd);
		
		JPanel panel_1 = new JPanel();
		JScrollPane scrollPane_1 = new JScrollPane(panel_1);
		panel_1.setLayout(new GridLayout(7, 2, 5, 10));
		bookPanel.add(scrollPane_1, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel_1.add(panel_3);
		
		JLabel lblFirstName = new JLabel("First Name:");
		panel_3.add(lblFirstName);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_8 = (FlowLayout) panel_4.getLayout();
		flowLayout_8.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel_4);
		
		textField = new JTextField();
		panel_4.add(textField);
		textField.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_5.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		panel_1.add(panel_5);
		
		JLabel lblLastName = new JLabel("Last Name:");
		panel_5.add(lblLastName);
		
		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_9 = (FlowLayout) panel_6.getLayout();
		flowLayout_9.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel_6);
		
		textField_1 = new JTextField();
		panel_6.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_7.getLayout();
		flowLayout_3.setAlignment(FlowLayout.RIGHT);
		panel_1.add(panel_7);
		
		JLabel lblDocumentId = new JLabel("Document ID:");
		panel_7.add(lblDocumentId);
		
		JPanel panel_8 = new JPanel();
		FlowLayout flowLayout_10 = (FlowLayout) panel_8.getLayout();
		flowLayout_10.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel_8);
		
		textField_2 = new JTextField();
		panel_8.add(textField_2);
		textField_2.setColumns(10);
		
		JPanel panel_10 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_10.getLayout();
		flowLayout_4.setAlignment(FlowLayout.RIGHT);
		panel_1.add(panel_10);
		
		JLabel lblPhoneNumber = new JLabel("Phone number:");
		panel_10.add(lblPhoneNumber);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_11 = (FlowLayout) panel_2.getLayout();
		flowLayout_11.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel_2);
		
		textField_3 = new JTextField();
		panel_2.add(textField_3);
		textField_3.setColumns(10);
		
		JPanel panel_9 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_9.getLayout();
		flowLayout_5.setAlignment(FlowLayout.RIGHT);
		panel_1.add(panel_9);
		
		JLabel lblFrom = new JLabel("From:");
		panel_9.add(lblFrom);
		
		JPanel panel_12 = new JPanel();
		FlowLayout flowLayout_12 = (FlowLayout) panel_12.getLayout();
		flowLayout_12.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel_12);
		
		textField_4 = new JTextField();
		panel_12.add(textField_4);
		textField_4.setColumns(10);
		
		JPanel panel_14 = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) panel_14.getLayout();
		flowLayout_6.setAlignment(FlowLayout.RIGHT);
		panel_1.add(panel_14);
		
		JLabel lblTo = new JLabel("To:");
		panel_14.add(lblTo);
		
		JPanel panel_11 = new JPanel();
		FlowLayout flowLayout_13 = (FlowLayout) panel_11.getLayout();
		flowLayout_13.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel_11);
		
		textField_5 = new JTextField();
		panel_11.add(textField_5);
		textField_5.setColumns(10);
		
		JPanel panel_13 = new JPanel();
		FlowLayout flowLayout_7 = (FlowLayout) panel_13.getLayout();
		flowLayout_7.setAlignment(FlowLayout.RIGHT);
		panel_1.add(panel_13);
		
		JLabel lblRoom = new JLabel("Room:");
		panel_13.add(lblRoom);
		
		JPanel panel_15 = new JPanel();
		FlowLayout flowLayout_14 = (FlowLayout) panel_15.getLayout();
		flowLayout_14.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel_15);
		
		textField_6 = new JTextField();
		panel_15.add(textField_6);
		textField_6.setColumns(10);

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
						"Booking System\n\nAuthors:\nMarek Jasiński\nMarek Lewandowski",
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
		stateLabel.setForeground(new Color(0, 87, 30));
		stateLabel.setText(ControllerInterface.State.CONNECTED.toString());
	}

}
