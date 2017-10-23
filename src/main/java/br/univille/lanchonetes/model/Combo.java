package br.univille.lanchonetes.model;

import java.util.List;

public class Combo {
	private int cdCombo;
	private String nmCombo;
	private float vlCombo;
	private List<Produto> produtos;
	
	public int getCdCombo() {
		return cdCombo;
	}
	
	public void setCdCombo(int cdCombo) {
		this.cdCombo = cdCombo;
	}
	
	public String getNmCombo() {
		return nmCombo;
	}
	
	public void setNmCombo(String nmCombo) {
		this.nmCombo = nmCombo;
	}
	
	public float getVlCombo() {
		return vlCombo;
	}
	
	public void setVlCombo(float vlCombo) {
		this.vlCombo = vlCombo;
	}
	
	public List<Produto> getProdutos() {
		return produtos;
	}
	
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
}
