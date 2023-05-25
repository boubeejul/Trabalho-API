package com.trabalhoFinal.apiEcommerce.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabalhoFinal.apiEcommerce.dto.ClienteDTO;
import com.trabalhoFinal.apiEcommerce.dto.EnderecoClienteDTO;
import com.trabalhoFinal.apiEcommerce.dto.PedidoClienteDTO;
import com.trabalhoFinal.apiEcommerce.entities.Cliente;
import com.trabalhoFinal.apiEcommerce.entities.Pedido;
import com.trabalhoFinal.apiEcommerce.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Cliente> getAllClientes() { 
		return clienteRepository.findAll();
	}
	
	public List<ClienteDTO> getAllClientesDTO() { 
		ModelMapper modelMapper = new ModelMapper();
		
		List<ClienteDTO> clientesDto = new ArrayList<>();
		
		// percorre cada cliente no banco
		for (Cliente cliente : clienteRepository.findAll()) {
			ClienteDTO novoClienteDto = modelMapper.map(cliente, ClienteDTO.class);
			
			// pega o endereço do cliente e converte pra dto
			novoClienteDto.setEndereco(modelMapper.map(cliente.getEndereco(), EnderecoClienteDTO.class));
			
			// percore a lista de pedidos do cliente e converte pra dto
			List<PedidoClienteDTO> pedidosDTO = new ArrayList<>();
			for(Pedido pedido: cliente.getPedidos()) {
				PedidoClienteDTO novoPedidoDto = modelMapper.map(pedido, PedidoClienteDTO.class);
				pedidosDTO.add(novoPedidoDto);
			}
			
			novoClienteDto.setPedidos(pedidosDTO);
			clientesDto.add(novoClienteDto);
		}
		return clientesDto;
	}
	
	public Cliente getClienteById(Integer id) {
		return clienteRepository.findById(id).orElse(null);
	}
	
	public Cliente saveCliente(Cliente cliente) { 
		return clienteRepository.save(cliente); 
	}
	
	public Cliente updateCliente(Cliente cliente, Integer id) { 
		return clienteRepository.save(cliente);
	}

	public Boolean delCliente(Integer id) {
		Cliente cliente = clienteRepository.findById(id).orElse(null);
		
		if (cliente != null) {
			clienteRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}
}
