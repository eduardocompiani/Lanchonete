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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.univille.lanchonetes.dto.ItensPedido;
import br.univille.lanchonetes.dto.PedidoFinal;
import br.univille.lanchonetes.jdbc.DBConnection;
import br.univille.lanchonetes.model.ItemVenda;
import br.univille.lanchonetes.model.Pedido;

@Path("/venda/pedido")
public class PedidoRest {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pedido> pegarPedidos() {
		List<Pedido> pedidos = new ArrayList();
		String sql = "SELECT CDPEDIDO, VLTOTAL FROM PEDIDO";
		try {
			Connection conn = new DBConnection().openConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Pedido pedido = new Pedido();
				pedido.setCdPedido(rs.getInt(1));
				pedido.setVlPedido(rs.getFloat(2));
				pedidos.add(pedido);
			}
			rs.close();
			stmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return pedidos;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public PedidoFinal criarPedido(List<ItensPedido> itensPedido) {
		PedidoFinal pedidoFinal = new PedidoFinal();
		String sqlPedido = "INSERT INTO PEDIDO (VLTOTAL) VALUES (?)";
		try {
			Connection conn = new DBConnection().openConnection();
			PreparedStatement stmt = conn.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS);
			stmt.setFloat(1, 0.00f);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()){
				pedidoFinal.setCdPedido(rs.getInt(1));
			}
			
			for (ItensPedido itemPedido : itensPedido) {
				StringBuilder builder = new StringBuilder();
				builder.append("INSERT INTO PEDIDO_ITEM (CDPEDIDO, ");
				
				if (itemPedido.isProduto()) {
					builder.append("CDPRODUTO, ");
				} else {
					builder.append("CDCOMBO, ");
				}
				
				builder.append("QTDPEDIDO) VALUES (?, ?, ?)");
				PreparedStatement stmtmItem = conn.prepareStatement(builder.toString());
				stmtmItem.setInt(1, pedidoFinal.getCdPedido());
				stmtmItem.setInt(2, itemPedido.getCdProdutoVenda());
				stmtmItem.setInt(3, itemPedido.getQtdItem());
				stmtmItem.executeUpdate();
				stmtmItem.close();
			}
			rs.close();
			stmt.close();
			conn.close();
		}catch( Exception e) {
			e.printStackTrace();
		}
		return pedidoFinal;
	}
	
	@DELETE
	@Path("/{id}")
	public void deletarPedido(@PathParam("id") int id){
		Connection conn = new DBConnection().openConnection();
		String sql = "DELETE FROM PEDIDO_ITEM WHERE CDPEDIDO = ?";
		String sqlPedido = "DELETE FROM PEDIDO WHERE CDPEDIDO = ?";
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			
			stmt = conn.prepareStatement(sqlPedido);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			stmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
}
