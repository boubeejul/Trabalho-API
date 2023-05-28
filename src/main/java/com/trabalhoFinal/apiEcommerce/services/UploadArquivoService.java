package com.trabalhoFinal.apiEcommerce.services;

import java.io.IOException;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.trabalhoFinal.apiEcommerce.dto.ClienteDTO;
import com.trabalhoFinal.apiEcommerce.dto.UploadArquivoDTO;
import com.trabalhoFinal.apiEcommerce.entities.UploadArquivo;
import com.trabalhoFinal.apiEcommerce.exceptions.ClienteNotFoundException;
import com.trabalhoFinal.apiEcommerce.exceptions.NoSuchElementException;
import com.trabalhoFinal.apiEcommerce.exceptions.UploadArquivoException;
import com.trabalhoFinal.apiEcommerce.repositories.UploadArquivoRepository;

@Service
public class UploadArquivoService {
	
	@Autowired
	UploadArquivoRepository uploadRepository;
	
	public UploadArquivoDTO armazenaArquivo(MultipartFile file) {
		String clearFileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
			
			if(clearFileName.contains("..")) {
				throw new UploadArquivoException("Nome de arquivo inválido: " + clearFileName);
			}
			
			UploadArquivo arquivo = new UploadArquivo(clearFileName, file.getContentType(), file.getBytes());
			
			uploadRepository.save(arquivo);
			
			return new UploadArquivoDTO(clearFileName, arquivo.getId_imagem(), file.getContentType(), file.getSize());
			
		} catch(IOException ex) {
			throw new UploadArquivoException("Ocorreu um erro ao armazenar o arquivo" + clearFileName, ex);
		}
	}
	
	public UploadArquivo getFile(Integer id) {	
		Optional<UploadArquivo> arquivoGet = uploadRepository.findById(id);
		
		if (arquivoGet != null) {
			ModelMapper modelMapper = new ModelMapper();
			return modelMapper.map(arquivoGet, UploadArquivo.class);
		} else {
			return null;
		}
	}
	
	public Boolean delFile(Integer id) {
		UploadArquivo arquivo = uploadRepository.findById(id).orElse(null);

		if (arquivo != null) {
			uploadRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	public UploadArquivoService() {
	}
}
