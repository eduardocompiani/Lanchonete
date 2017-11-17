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
			String sqlProduto = "SELECT PRODUTO.CDPRODUTO, PRODUTO.NMPRODUTO, (((100 - COMBO.VLDESCONTO) / 100) * PRODUTO.VLPRODUTO) AS VLPRODUTO FROM COMBO INNER JOIN PRODUTO_DO_COMBO ON (COMBO.CDCOMBO = PRODUTO_DO_COMBO.CDCOMBO) INNER JOIN PRODUTO ON (PRODUTO.CDPRODUTO = PRODUTO_DO_COMBO.CDPRODUTO) WHERE PRODUTO_DO_COMBO.CDCOMBO = ?";
			while(rs.next()){
				Combo combo = new Combo();
				combo.setCdCombo(rs.getInt(1));
				combo.setNmCombo(rs.getString(2));
				combo.setVlDesconto(rs.getFloat(3));
				combo.setVlCombo(rs.getFloat(4));
				PreparedStatement stmtProduto = conn.prepareStatement(sqlProduto);
				stmtProduto.setInt(1, combo.getCdCombo());
				ResultSet rsProduto = stmtProduto.executeQuery();
				List<Produto> produtos = new ArrayList();
				while(rsProduto.next()){
					Produto produto = new Produto();
					produto.setCdProduto(rsProduto.getInt(1));
					produto.setNmProduto(rsProduto.getString(2));
					produto.setVlProduto(rsProduto.getFloat(3));
					produtos.add(produto);
				}
				combo.setProdutos(produtos);
				combos.add(combo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return combos;
	}
}
