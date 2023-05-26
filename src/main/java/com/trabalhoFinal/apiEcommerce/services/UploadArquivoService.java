package com.trabalhoFinal.apiEcommerce.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.trabalhoFinal.apiEcommerce.dto.UploadArquivoDTO;
import com.trabalhoFinal.apiEcommerce.entities.Produto;
import com.trabalhoFinal.apiEcommerce.entities.UploadArquivo;
import com.trabalhoFinal.apiEcommerce.exceptions.UploadArquivoException;
import com.trabalhoFinal.apiEcommerce.repositories.UploadArquivoRepository;

@Service
public class UploadArquivoService {
	
	@Autowired
	UploadArquivoRepository uploadRepository;

	@Value("${pasta.upload.arquivos}")
	private String pastaUploadArquivos;
	
	private Path localArmazenamentoArquivo;
	
	public UploadArquivoDTO armazenaArquivo(MultipartFile file) {
		String clearFileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
			
			if(clearFileName.contains("..")) {
				throw new UploadArquivoException("Nome de arquivo inválido: " + clearFileName);
			}
			
			this.localArmazenamentoArquivo = Paths.get(pastaUploadArquivos).toAbsolutePath().normalize();
			
			Path pastaDestino = this.localArmazenamentoArquivo.resolve(clearFileName);
			
			Files.copy(file.getInputStream(), pastaDestino);
			
			UploadArquivo arquivo = new UploadArquivo(clearFileName, file.getContentType(), file.getBytes());
			
			uploadRepository.save(arquivo);
			
			return new UploadArquivoDTO(clearFileName, pastaUploadArquivos, file.getContentType(), file.getSize());
			
		} catch(IOException ex) {
			throw new UploadArquivoException("Ocorreu um erro ao armazenar o arquivo" + clearFileName, ex);
		}
	}
	
	public UploadArquivo getFile(String id) {
		return uploadRepository.findById(id).get();
	}

	public UploadArquivoService() {
	}
}
