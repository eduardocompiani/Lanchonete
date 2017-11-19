package br.univille.lanchonetes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.univille.lanchonetes.jdbc.DBConnection;
import br.univille.lanchonetes.model.Produto;

@Path("produto")
public class ProdutoRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Produto> getProdutos(){
		List<Produto> produtos = new ArrayList();
		Produto produto;
		Connection conn = new DBConnection().openConnection();
		String sql = "SELECT CDPRODUTO, NMPRODUTO, VLPRODUTO FROM PRODUTO";
		
		try{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				produto = new Produto();
				produto.setCdProduto(rs.getInt(1));
				produto.setNmProduto(rs.getString(2));
				produto.setVlProduto(rs.getFloat(3));
				
				produtos.add(produto);
			}
			rs.close();
			stmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return produtos;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Produto getProduto(@PathParam("id") int id){
		Produto produto = new Produto();
		Connection conn = new DBConnection().openConnection();
		String sql = "SELECT CDPRODUTO, NMPRODUTO, VLPRODUTO FROM PRODUTO WHERE CDPRODUTO = ?";
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				produto.setCdProduto(rs.getInt(1));
				produto.setNmProduto(rs.getString(2));
				produto.setVlProduto(rs.getFloat(3));
			}
			rs.close();
			stmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return produto;
	}
	
	@PUT
	@Path("/{id}")
	public void updateProduto(Produto produto, @PathParam("id") int id){
		Connection conn = new DBConnection().openConnection();
		String sql = "UPDATE PRODUTO SET NMPRODUTO = ?, VLPRODUTO = ? WHERE CDPRODUTO = ?";
		
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, produto.getNmProduto());
			stmt.setFloat(2, produto.getVlProduto());
			stmt.setInt(3, id);
			stmt.execute();
			stmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Produto cadastrarProduto(Produto produto){
		Connection conn = new DBConnection().openConnection();
		String sql = "INSERT INTO PRODUTO (NMPRODUTO, VLPRODUTO) VALUES (?,?)";
		try{
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, produto.getNmProduto());
			stmt.setFloat(2, produto.getVlProduto());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			produto.setCdProduto(rs.getInt(1));		
			rs.close();
			stmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return produto;
	}
	
	@DELETE
	@Path("/{id}")
	public void deletarProduto(@PathParam("id") int id){
		Connection conn = new DBConnection().openConnection();
		String sql = "DELETE FROM PRODUTO WHERE CDPRODUTO = ?";
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			stmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
