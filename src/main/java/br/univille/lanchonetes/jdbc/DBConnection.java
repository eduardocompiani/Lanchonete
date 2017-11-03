package br.univille.lanchonetes.jdbc;

import java.sql.Connection;

public class DBConnection {

	private Connection connection;
	
	public Connection openConnection(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// diz a lenda que a classe de cima esta depreciada e necessita utilizar a 
			// com.mysql.cj.jdbc.Driver
			connection = java.sql.DriverManager
					.getConnection(
					"jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10202856",
					"sql10202856", "9lWWkQAYF9");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	public void closeConnection(){
		try {
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
