package br.univille.lanchonetes.jdbc;

import java.sql.Connection;

public class DBConnection {

	private Connection connection;
	
	public Connection openConnection(){
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// diz a lenda que a classe de cima esta depreciada e necessita utilizar a 
			// com.mysql.cj.jdbc.Driver
			connection = java.sql.DriverManager
					.getConnection(
					"jdbc:mysql://b4e9xxkxnpu2v96i.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/guv626gv1gdmd2i0?autoReconnect=true&useSSL=false",
					"y73ah4b7f2ydien1", "md9jrm6ibomi1c9d");
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
