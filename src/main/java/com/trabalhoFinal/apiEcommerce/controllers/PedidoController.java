package com.trabalhoFinal.apiEcommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trabalhoFinal.apiEcommerce.dto.PedidoDTO;
import com.trabalhoFinal.apiEcommerce.entities.Pedido;
import com.trabalhoFinal.apiEcommerce.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	PedidoService pedidoService;
	
	@GetMapping 
	public ResponseEntity<List<Pedido>> getAllPedidos() {
		return new ResponseEntity<>(pedidoService.getAllPedidos(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> getPedidoById(@PathVariable Integer id) {
		return new ResponseEntity<>(pedidoService.getPedidoById(id), HttpStatus.OK);
	}
	
	//DTO
	@GetMapping("/dto") 
	public ResponseEntity<List<PedidoDTO>> getAllPedidosDTO() {
		return new ResponseEntity<>(pedidoService.getAllPedidosDTO(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Pedido> savePedido(@RequestBody Pedido pedido) {
		return new ResponseEntity<>(pedidoService.savePedido(pedido), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Pedido> updatePedido(@RequestBody Pedido pedido, Integer id) {
		return new ResponseEntity<>(pedidoService.updatePedido(pedido), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> delPedido(@PathVariable Integer id) {
		Boolean pedidoResponse = pedidoService.delPedido(id);
		if (pedidoResponse)
			return new ResponseEntity<>(pedidoResponse, HttpStatus.OK);
		else
			return new ResponseEntity<>(pedidoResponse, HttpStatus.NOT_MODIFIED);
	}
}
