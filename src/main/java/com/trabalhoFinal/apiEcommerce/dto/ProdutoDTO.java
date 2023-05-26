package com.trabalhoFinal.apiEcommerce.dto;

import java.util.Date;

public class ProdutoDTO {

	private String nome;
	private String descricao;
	private Integer qtd_estoque;
	private Date data_cadastro;
	private Double valor_unitario;
	private CategoriaProdDTO categoriaProdDto;

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

	public Integer getQtd_estoque() {
		return qtd_estoque;
	}

	public void setQtd_estoque(Integer qtd_estoque) {
		this.qtd_estoque = qtd_estoque;
	}

	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public Double getValor_unitario() {
		return valor_unitario;
	}

	public void setValor_unitario(Double valor_unitario) {
		this.valor_unitario = valor_unitario;
	}

	public CategoriaProdDTO getCategoriaProdDto() {
		return categoriaProdDto;
	}

	public void setCategoriaProdDto(CategoriaProdDTO categoriaProdDto) {
		this.categoriaProdDto = categoriaProdDto;
	}

}
