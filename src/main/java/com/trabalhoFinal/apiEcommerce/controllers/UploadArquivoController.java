package com.trabalhoFinal.apiEcommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.trabalhoFinal.apiEcommerce.dto.UploadArquivoDTO;
import com.trabalhoFinal.apiEcommerce.entities.UploadArquivo;
import com.trabalhoFinal.apiEcommerce.services.ProdutoService;
import com.trabalhoFinal.apiEcommerce.services.UploadArquivoService;

@RestController
@RequestMapping("/upload")
public class UploadArquivoController {

	@Autowired
	UploadArquivoService uploadArquivoService;
	
	@Autowired
	ProdutoService produtoService;
	
	@PostMapping
	public ResponseEntity<UploadArquivoDTO> uploadArquivo(@RequestParam("file") MultipartFile file){

		return new ResponseEntity<>(uploadArquivoService.armazenaArquivo(file), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<byte[]> getArquivo(@PathVariable String id){
		
		UploadArquivo arquivo = uploadArquivoService.getFile(id);
		
		return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + arquivo.getNome() + "\"")
		        .contentType(MediaType.valueOf(arquivo.getTipoArquivo()))
		        .body(arquivo.getData());
	}
}
