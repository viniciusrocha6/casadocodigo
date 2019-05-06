package br.com.casadocodigo.loja.models;

import java.util.Calendar;
import java.util.List;

public class JSONObject {

	private Calendar dataGeracao = Calendar.getInstance();
	private int quatidade;
	private List<Produto> produtos;

	public Calendar getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(Calendar dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public int getQuatidade() {
		return quatidade;
	}

	public void setQuatidade(int quatidade) {
		this.quatidade = quatidade;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

}
