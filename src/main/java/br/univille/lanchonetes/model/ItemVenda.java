package br.univille.lanchonetes.model;

public class ItemVenda {
	private boolean isProduto;
	private int cdProdutoVenda;
	private String nmProdutoVenda;
	private float vlProdutoVenda;
	
	public boolean isProduto() {
		return isProduto;
	}
	public void setProduto(boolean isProduto) {
		this.isProduto = isProduto;
	}
	public int getCdProdutoVenda() {
		return cdProdutoVenda;
	}
	public void setCdProdutoVenda(int cdProdutoVenda) {
		this.cdProdutoVenda = cdProdutoVenda;
	}
	public String getNmProdutoVenda() {
		return nmProdutoVenda;
	}
	public void setNmProdutoVenda(String nmProdutoVenda) {
		this.nmProdutoVenda = nmProdutoVenda;
	}
	public float getVlProdutoVenda() {
		return vlProdutoVenda;
	}
	public void setVlProdutoVenda(float vlProdutoVenda) {
		this.vlProdutoVenda = vlProdutoVenda;
	}
}
