package br.univille.lanchonetes.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {

	private Connection connection;
	
	public Connection openConnection(){
		
		try {
			connection = java.sql.DriverManager
					.getConnection(
					"jdbc:mysql://localhost:3306/LANCHONETEDB",
					"root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	public void closeConnection(){
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
