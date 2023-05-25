package com.trabalhoFinal.apiEcommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabalhoFinal.apiEcommerce.entities.Endereco;
import com.trabalhoFinal.apiEcommerce.repositories.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public List<Endereco> getAllEnderecos() { 
		return enderecoRepository.findAll();
	}
	
	public Endereco getEnderecoById(Integer id) {
		return enderecoRepository.findById(id).orElse(null);
	}
	
	public Endereco saveEndereco(Endereco endereco) { 
		return enderecoRepository.save(endereco); 
	}
	
	public Endereco updateEndereco(Endereco endereco, Integer id) { 
		return enderecoRepository.save(endereco);
	}

	public Boolean delEndereco(Integer id) {
		Endereco endereco = enderecoRepository.findById(id).orElse(null);
		
		if (endereco != null) {
			enderecoRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}
}
