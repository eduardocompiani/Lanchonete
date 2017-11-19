package br.univille.lanchonetes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.univille.lanchonetes.jdbc.DBConnection;
import br.univille.lanchonetes.model.Combo;
import br.univille.lanchonetes.model.Produto;

@Path("combo")
public class ComboRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Combo> getAllCombos(){
		List<Combo> combos = new ArrayList();
		Connection conn = new DBConnection().openConnection();
		String sqlCombo = "SELECT CDCOMBO, NMCOMBO, VLDESCONTO, VLCOMBO FROM COMBO";
		try{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlCombo);
			while(rs.next()){
				Combo combo = new Combo();
				combo.setCdCombo(rs.getInt(1));
				combo.setNmCombo(rs.getString(2));
				combo.setVlDesconto(rs.getFloat(3));
				combo.setVlCombo(rs.getFloat(4));
				combo.setProdutos(this.pegarProdutoDoCombo(combo.getCdCombo()));
				combos.add(combo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return combos;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Combo getCombo(@PathParam("id") int id){
		Combo combo = new Combo();
		Connection conn = new DBConnection().openConnection();
		String sqlCombo = "SELECT CDCOMBO, NMCOMBO, VLDESCONTO, VLCOMBO FROM COMBO WHERE CDCOMBO = ?";
		try{
			PreparedStatement stmt = conn.prepareStatement(sqlCombo);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				combo.setCdCombo(rs.getInt(1));
				combo.setNmCombo(rs.getString(2));
				combo.setVlDesconto(rs.getFloat(3));
				combo.setVlCombo(rs.getFloat(4));
				combo.setProdutos(this.pegarProdutoDoCombo(combo.getCdCombo()));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return combo;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Combo adicionarCombo(Combo combo){
		String sql = "INSERT INTO COMBO(NMCOMBO, VLDESCONTO, VLCOMBO) (?, ?, ?)";
		Connection conn = new DBConnection().openConnection();
		try{
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, combo.getNmCombo());
			stmt.setFloat(2, combo.getVlDesconto());
			stmt.setFloat(3, combo.getVlCombo());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()){
				combo.setCdCombo(rs.getInt(1));
			}
			//TO-DO
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return combo;
	}
	
	private List<Produto> pegarProdutoDoCombo(int cdCombo){
		List<Produto> produtos = new ArrayList();
		String sqlProduto = "SELECT PRODUTO.CDPRODUTO, PRODUTO.NMPRODUTO, (((100 - COMBO.VLDESCONTO) / 100) * PRODUTO.VLPRODUTO) AS VLPRODUTO FROM COMBO INNER JOIN PRODUTO_DO_COMBO ON (COMBO.CDCOMBO = PRODUTO_DO_COMBO.CDCOMBO) INNER JOIN PRODUTO ON (PRODUTO.CDPRODUTO = PRODUTO_DO_COMBO.CDPRODUTO) WHERE PRODUTO_DO_COMBO.CDCOMBO = ?";
		Connection conn = new DBConnection().openConnection();
		try{
			PreparedStatement stmt = conn.prepareStatement(sqlProduto);
			stmt.setInt(1, cdCombo);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Produto produto = new Produto();
				produto.setCdProduto(rs.getInt(1));
				produto.setNmProduto(rs.getString(2));
				produto.setVlProduto(rs.getFloat(3));
				produtos.add(produto);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return produtos;
	}
}
