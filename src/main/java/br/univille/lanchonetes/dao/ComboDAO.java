package br.univille.lanchonetes.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.univille.lanchonetes.jdbc.DBConnection;
import br.univille.lanchonetes.model.Combo;

public class ComboDAO {
	
	private DBConnection dbConn = new DBConnection();
	private Connection conn;
	private Combo combo;
	
	public ComboDAO() {
		conn = dbConn.openConnection();
	}
	
	public List<Combo> getCombos(){
		List<Combo> combos = new ArrayList();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT CDCOMBO, NMCOMBO, VLDESCONTO FROM COMBO");
			
			while(rs.next()){
				combo = new Combo();
				combo.setCdCombo(rs.getInt(0));
				combo.setNmCombo(rs.getString(1));
				combo.setVlDesconto(rs.getFloat(2));
				
				combos.add(combo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
