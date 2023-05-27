package com.trabalhoFinal.apiEcommerce.exceptions;

public class PedidoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PedidoNotFoundException(Integer id) {
		super("Não foi encontrada Pedido com o id = " + id);
	}
}
