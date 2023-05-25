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

import com.trabalhoFinal.apiEcommerce.dto.ClienteDTO;
import com.trabalhoFinal.apiEcommerce.entities.Cliente;
import com.trabalhoFinal.apiEcommerce.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	ClienteService clienteService;
	
	@GetMapping 
	public ResponseEntity<List<Cliente>> getAllClientes() {
		return new ResponseEntity<>(clienteService.getAllClientes(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
		return new ResponseEntity<>(clienteService.getClienteById(id), HttpStatus.OK);
	}
	
	// DTO
	@GetMapping ("/dto")
	public ResponseEntity<List<ClienteDTO>> getAllClientesDTO() {
		return new ResponseEntity<>(clienteService.getAllClientesDTO(), HttpStatus.OK);
	}
	
	
	@PostMapping
	public ResponseEntity<Cliente> saveCliente(@RequestBody Cliente cliente) {
		return new ResponseEntity<>(clienteService.saveCliente(cliente), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Cliente> updateCliente(@RequestBody Cliente cliente, Integer id) {
		return new ResponseEntity<>(clienteService.updateCliente(cliente, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> delCliente(@PathVariable Integer id) {
		Boolean clienteResponse = clienteService.delCliente(id);
		if (clienteResponse)
			return new ResponseEntity<>(clienteResponse, HttpStatus.OK);
		else
			return new ResponseEntity<>(clienteResponse, HttpStatus.NOT_MODIFIED);
	}
}
