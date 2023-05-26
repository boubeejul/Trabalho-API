package com.trabalhoFinal.apiEcommerce.dto;

public class ProdutoPedidoDTO {

	private String nome;
	private Double quantidade;
	private Double valor;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "ProdutoPedidoDTO [nome=" + nome + ", quantidade=" + quantidade + ", valor=" + valor + "]";
	}

}
