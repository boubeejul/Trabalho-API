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

import com.trabalhoFinal.apiEcommerce.dto.ProdutoDTO;
import com.trabalhoFinal.apiEcommerce.entities.Produto;
import com.trabalhoFinal.apiEcommerce.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	ProdutoService produtoService;
	
	@GetMapping 
	public ResponseEntity<List<Produto>> getAllProdutos() {
		return new ResponseEntity<>(produtoService.getAllProdutos(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getProdutoById(@PathVariable Integer id) {
		return new ResponseEntity<>(produtoService.getProdutoById(id), HttpStatus.OK);
	}
	
	//DTO
	
	@GetMapping("/dto") 
	public ResponseEntity<List<ProdutoDTO>> getAllProdutosDTO() {
		return new ResponseEntity<>(produtoService.getAllProdutosDTO(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Produto> saveProduto(@RequestBody Produto produto) {
		return new ResponseEntity<>(produtoService.saveProduto(produto), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Produto> updateProduto(@RequestBody Produto produto, Integer id) {
		return new ResponseEntity<>(produtoService.updateProduto(produto, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> delProduto(@PathVariable Integer id) {
		Boolean produtoResponse = produtoService.delProduto(id);
		if (produtoResponse)
			return new ResponseEntity<>(produtoResponse, HttpStatus.OK);
		else
			return new ResponseEntity<>(produtoResponse, HttpStatus.NOT_MODIFIED);
	}
}
