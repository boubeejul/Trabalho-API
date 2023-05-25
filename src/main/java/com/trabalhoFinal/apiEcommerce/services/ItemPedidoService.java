package com.trabalhoFinal.apiEcommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabalhoFinal.apiEcommerce.entities.ItemPedido;
import com.trabalhoFinal.apiEcommerce.repositories.ItemPedidoRepository;

@Service
public class ItemPedidoService {

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public List<ItemPedido> getAllItemPedidos() { 
		return itemPedidoRepository.findAll();
	}
	
	public ItemPedido getItemPedidoById(Integer id) {
		return itemPedidoRepository.findById(id).orElse(null);
	}
	
	public ItemPedido saveItemPedido(ItemPedido itemPedido) { 
		return itemPedidoRepository.save(itemPedido); 
	}
	
	public ItemPedido updateItemPedido(ItemPedido itemPedido, Integer id) { 
		return itemPedidoRepository.save(itemPedido);
	}

	public Boolean delItemPedido(Integer id) {
		ItemPedido itemPedido = itemPedidoRepository.findById(id).orElse(null);
		
		if (itemPedido != null) {
			itemPedidoRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}
}
