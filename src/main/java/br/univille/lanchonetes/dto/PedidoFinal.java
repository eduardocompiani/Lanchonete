package br.univille.lanchonetes.dto;

import java.util.List;

import br.univille.lanchonetes.model.Pedido;

public class PedidoFinal extends Pedido{

	private List<ItensPedido> itens;

	public List<ItensPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItensPedido> itens) {
		this.itens = itens;
	}
}
