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
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
	public Pedido criarPedido(List<ItemVenda> itensPedido) {
		Pedido pedido = new Pedido();
		String sqlPedido = "INSERT INTO PEDIDO (VLPEDIDO) VALUES (?)";
		try {
			Connection conn = new DBConnection().openConnection();
			PreparedStatement stmt = conn.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS);
			//TO-DO
		}catch( Exception e) {
			e.printStackTrace();
		}
		return pedido;
	}
}
