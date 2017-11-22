package br.univille.lanchonetes.dto;

import br.univille.lanchonetes.model.ItemVenda;

public class ItensPedido extends ItemVenda{

	private int cdPedido;
	private int qtdItem;
	
	public int getCdPedido() {
		return cdPedido;
	}
	public void setCdPedido(int cdPedido) {
		this.cdPedido = cdPedido;
	}
	public int getQtdItem() {
		return qtdItem;
	}
	public void setQtdItem(int qtdItem) {
		this.qtdItem = qtdItem;
	}
	
	
}
