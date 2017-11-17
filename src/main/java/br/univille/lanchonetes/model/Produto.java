package br.univille.lanchonetes.model;

public class Produto {

	private int cdProduto;
	private String nmProduto;
	private float vlProduto;
	
	public int getCdProduto() {
		return cdProduto;
	}
	
	public void setCdProduto(int cdProduto) {
		this.cdProduto = cdProduto;
	}
	
	public String getNmProduto() {
		return nmProduto;
	}
	
	public void setNmProduto(String nmProduto) {
		this.nmProduto = nmProduto;
	}
	
	public float getVlProduto() {
		return vlProduto;
	}
	
	public void setVlProduto(float vlProduto) {
		this.vlProduto = vlProduto;
	}

	@Override
	public String toString() {
		return "Produto [cdProduto=" + cdProduto + ", nmProduto=" + nmProduto + ", vlProduto=" + vlProduto + "]";
	}
	
}
