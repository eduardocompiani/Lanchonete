package br.univille.lanchonetes.model;

import java.util.ArrayList;
import java.util.List;

public class Combo {
	private int cdCombo;
	private String nmCombo;
	private float vlCombo;
	private float vlDesconto;
	private List<Produto> produtos = new ArrayList();
	
	public float getVlDesconto() {
		return vlDesconto;
	}

	public void setVlDesconto(float vlDesconto) {
		this.vlDesconto = vlDesconto;
	}
	
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
