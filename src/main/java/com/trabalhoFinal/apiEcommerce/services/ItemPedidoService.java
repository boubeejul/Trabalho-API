package com.trabalhoFinal.apiEcommerce.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabalhoFinal.apiEcommerce.dto.PedidoDTO;
import com.trabalhoFinal.apiEcommerce.entities.ItemPedido;
import com.trabalhoFinal.apiEcommerce.entities.Pedido;
import com.trabalhoFinal.apiEcommerce.repositories.ItemPedidoRepository;
import com.trabalhoFinal.apiEcommerce.repositories.PedidoRepository;

@Service
public class ItemPedidoService {

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public List<ItemPedido> getAllItemPedidos() { 
		return itemPedidoRepository.findAll();
	}
	
	public ItemPedido getItemPedidoById(Integer id) {
		return itemPedidoRepository.findById(id).orElse(null);
	}
	
	public ItemPedido saveItemPedido(ItemPedido itemPedido) { 
		atualizaValorTotal(itemPedido, 0);		
		return itemPedidoRepository.save(itemPedido); 
	}
	
	public ItemPedido updateItemPedido(ItemPedido itemPedido, Integer id) { 
		return itemPedidoRepository.save(itemPedido);
	}

	public Boolean delItemPedido(Integer id) {
		ItemPedido itemPedido = itemPedidoRepository.findById(id).orElse(null);
		
		if (itemPedido != null) {
			itemPedidoRepository.deleteById(id);
			atualizaValorTotal(itemPedido, 1);
			return true;
		} else {
			return false;
		}
	}
	
	// atualiza o valor total do pedido toda vez que um novo ItemPedido é salvo ou deletado
	public void atualizaValorTotal(ItemPedido itemPedido, Integer r) {
		// r = 0 | saveItemPedido
		// r = 1 | delItemPedido
		
		Pedido pedidoAtualizado = pedidoService.getPedidoById(itemPedido.getPedido().getId_pedido());
		pedidoAtualizado.setValor_total(itemPedido.getValor_liquido(), r);
	
		pedidoService.updatePedido(pedidoAtualizado);
	}
}
