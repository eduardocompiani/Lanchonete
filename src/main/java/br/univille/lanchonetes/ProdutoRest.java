package br.univille.lanchonetes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
		}catch(Exception e){
			e.printStackTrace();
		}
		return produtos;
	}
}
