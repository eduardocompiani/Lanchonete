package br.univille.lanchonetes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.univille.lanchonetes.jdbc.DBConnection;
import br.univille.lanchonetes.model.ItemVenda;

@Path("/venda")
public class ItemVendaRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ItemVenda> listarProdutosVenda() {
		List<ItemVenda> produtos = new ArrayList();
		String sql = "SELECT IS_PRODUTO, CDPRODUTO_VENDA, NMPRODUTO_VENDA, VLPRODUTO_VENDA FROM PRODUTO_VENDA";
		Connection conn = new DBConnection().openConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				ItemVenda item = new ItemVenda();
				item.setProduto(rs.getBoolean(1));
				item.setCdProdutoVenda(rs.getInt(2));
				item.setNmProdutoVenda(rs.getString(3));
				item.setVlProdutoVenda(rs.getFloat(4));
				produtos.add(item);
			}
			rs.close();
			stmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return produtos;
	}
}
