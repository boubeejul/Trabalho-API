package com.trabalhoFinal.apiEcommerce.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class ItemPedido {

	@EmbeddedId
	private ItemPedidoKey id_item_pedido;

	@Column(name = "quantidade")
	private Double quantidade;

	@Column(name = "preco_venda")
	private Double preco_venda;

	@Column(name = "percentual_desconto")
	private Double percentual_desconto;

	@Column(name = "valor_bruto")
	private Double valor_bruto;

	@Column(name = "valor_liquido")
	private Double valor_liquido;

	@ManyToOne
	@MapsId("id_produto")
	@JoinColumn(name = "id_produto")
	private Produto produto;

	@ManyToOne
	@MapsId("id_pedido")
	@JoinColumn(name = "id_pedido")
	private Pedido pedido;

	
	 int grade; 
	
	
	public ItemPedidoKey getId_item_pedido() {
		return id_item_pedido;
	}

	public void setId_item_pedido(ItemPedidoKey id_item_pedido) {
		this.id_item_pedido = id_item_pedido;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco_venda() {
		return preco_venda;
	}

	public void setPreco_venda(Double preco_venda) {
		this.preco_venda = preco_venda;
	}

	public Double getPercentual_desconto() {
		return percentual_desconto;
	}

	public void setPercentual_desconto(Double percentual_desconto) {
		this.percentual_desconto = percentual_desconto;
	}

	public Double getValor_bruto() {
		return valor_bruto;
	}

	public void setValor_bruto(Double valor_bruto) {
		this.valor_bruto = valor_bruto;
	}

	public Double getValor_liquido() {
		return valor_liquido;
	}

	public void setValor_liquido(Double valor_liquido) {
		this.valor_liquido = valor_liquido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	
}
