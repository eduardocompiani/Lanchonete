package br.univille.lanchonetes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import br.univille.lanchonetes.jdbc.DBConnection;
import br.univille.lanchonetes.model.Produto;

public class ProdutoDAO {
	private Connection conn;
	
	public void insertProduto(Produto produto){
		String sqlInsert = "INSERT INTO PRODUTO (CDPRODUTO, NMPRODUTO, VLPRODUTO) VALUES (?,?,?,?)";
		
		try{
			this.conn = new DBConnection().openConnection();
			PreparedStatement stmt = this.conn.prepareStatement(sqlInsert);
			stmt.setInt(1, produto.getCdProduto());
			stmt.setString(2, produto.getNmProduto());
			stmt.setFloat(3, produto.getVlProduto());
			stmt.executeUpdate();
			
			this.conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void updateProduto(Produto produto){
		String sqlUpdate = "UPDATE PRODUTO SET NMPRODUTO = ?, VLPRODUTO = ? WHERE CDPRODUTO = ?";
		
		try{
			this.conn = new DBConnection().openConnection();
			PreparedStatement stmt = this.conn.prepareStatement(sqlUpdate);
			stmt.setString(1, produto.getNmProduto());
			stmt.setFloat(2, produto.getVlProduto());
			stmt.setInt(3, produto.getCdProduto());
			stmt.executeUpdate();
			
			this.conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
