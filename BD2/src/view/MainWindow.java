package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;

import java.sql.*;

import oracle.jdbc.pool.OracleDataSource;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTextField fromTextField;
	private JTextField toTextField;
	private JTable table;
	

    private Connection conn;
    private Statement stmt;
    private PreparedStatement prepStatement;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 799, 632);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, "name_16188434970045");
		
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
		
		table = new JTable();
		bookingsPanel.add(table, BorderLayout.CENTER);
		
		JPanel bookPanel = new JPanel();
		tabbedPane.addTab("Book", null, bookPanel, null);

		try {
			OracleDataSource ods = new OracleDataSource();
			ods.setURL("jdbc:oracle:thin:bd2a14/bobas47@ikar.elka.pw.edu.pl:1521/elka.elka.pw.edu.pl");
			conn = ods.getConnection();
			stmt = conn.createStatement();
			prepStatement = conn.prepareStatement("SELECT P.NUMER_POKOJU, PR.OD, PR.DO " +
					"FROM POKOJE P, POZYCJE_REZERWACJI PR " +
					"WHERE P.NUMER_POKOJU = PR.NUMER_POKOJU " +
					"AND PR.DO >= ? " +
					"AND PR.OD <= ?");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                try {
                    stmt.close();
                    conn.close();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
		AbstractAction showAction = new AbstractAction() {
			@Override
            public void actionPerformed(ActionEvent evt) {
                try {
                	System.out.println("showAction");
                	// TODO read date from text fields and parse it
                	// TODO fix problem with Date type
                	Date fromDate = new Date(2012, 12, 01);
                	Date toDate = new Date(2012, 12, 30);
                    prepStatement.setDate(1, fromDate);
                    prepStatement.setDate(2, toDate);
                    ResultSet rset = prepStatement.executeQuery();
                    DefaultTableModel model = new DefaultTableModel();
                    table.setModel(model);
                    model.addColumn("Room number");
                    model.addColumn("From");
                    model.addColumn("To");
                    while (rset.next()) {
                    	System.out.println("addRow");
                        model.addRow(new Object[]{rset.getInt(1), rset.getDate(2), rset.getDate(3)});
                    }
                    rset.close();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        
        fromTextField.addActionListener(showAction);
        toTextField.addActionListener(showAction);
        showButton.addActionListener(showAction);
	}

}
