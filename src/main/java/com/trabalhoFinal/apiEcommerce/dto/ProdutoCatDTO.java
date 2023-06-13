package com.trabalhoFinal.apiEcommerce.dto;

public class ProdutoCatDTO {

	private Integer id_produto;
	private Integer id_imagem;
	private String nome;
	private String descricao;
	
	
	public Integer getId_produto() {
		return id_produto;
	}
	public void setId_produto(Integer id_produto) {
		this.id_produto = id_produto;
	}
	public Integer getId_imagem() {
		return id_imagem;
	}
	public void setId_imagem(Integer id_imagem) {
		this.id_imagem = id_imagem;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
