package com.trabalhoFinal.apiEcommerce.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabalhoFinal.apiEcommerce.dto.PedidoDTO;
import com.trabalhoFinal.apiEcommerce.dto.ProdutoPedidoDTO;
import com.trabalhoFinal.apiEcommerce.entities.ItemPedido;
import com.trabalhoFinal.apiEcommerce.entities.Pedido;
import com.trabalhoFinal.apiEcommerce.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public List<Pedido> getAllPedidos() { 
		return pedidoRepository.findAll();
	}
	
	public List<PedidoDTO> getAllPedidosDTO() { 
		ModelMapper modelMapper = new ModelMapper();
		
		List<PedidoDTO> pedidosDto = new ArrayList<>();
		
		for(Pedido pedido : pedidoRepository.findAll()) {
			PedidoDTO novoPedidoDto = modelMapper.map(pedido, PedidoDTO.class);
			novoPedidoDto.setId_cliente(pedido.getCliente().getId_cliente());
			
			List<ProdutoPedidoDTO> prodPedDto = new ArrayList<>();
			
			for(ItemPedido itemPedido : pedido.getItemPedidos()) {
				ProdutoPedidoDTO novoProdDto = modelMapper.map(itemPedido.getProduto(), ProdutoPedidoDTO.class);
				novoProdDto.setQuantidade(itemPedido.getQuantidade());
				prodPedDto.add(novoProdDto);
			}
			
			novoPedidoDto.setProdutos(prodPedDto);
			pedidosDto.add(novoPedidoDto);
		}
		
		return pedidosDto;
	}
	
	public Pedido getPedidoById(Integer id) {
		return pedidoRepository.findById(id).orElse(null);
	}
	
	public Pedido savePedido(Pedido pedido) { 
		return pedidoRepository.save(pedido); 
	}
	
	public Pedido updatePedido(Pedido pedido) { 
		return pedidoRepository.save(pedido);
	}

	public Boolean delPedido(Integer id) {
		Pedido pedido = pedidoRepository.findById(id).orElse(null);
		
		if (pedido != null) {
			pedidoRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}
}
