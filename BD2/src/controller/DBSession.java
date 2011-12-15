package controller;

import java.sql.*;
import oracle.jdbc.pool.OracleDataSource;

public class DBSession {

	private Connection conn;
	
	public DBSession(){
		OracleDataSource ods;
		try {
			ods = new OracleDataSource();
			ods.setURL("jdbc:oracle:thin:bd2a14/bobas47@ikar.elka.pw.edu.pl:1521/elka.elka.pw.edu.pl");
			conn = ods.getConnection();

		} catch (SQLException e) {
			System.err.println("Nie udalo sie polaczyc z baza danych");
			e.printStackTrace();
		}
	}
}
