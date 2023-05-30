package com.trabalhoFinal.apiEcommerce.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id_endereco", scope = Endereco.class)
@Entity
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_endereco")
	private Integer id_endereco;

	@NotBlank(message = "O CEP deve ser preenchido!" )
	@Column(name = "cep")
	private String cep;

	
	@Column(name = "rua")
	private String logradouro;

	
	@Column(name = "bairro")
	private String bairro;

	
	@Column(name = "cidade")
	private String localidade;

	
	@Column(name = "numero", unique = true)
	private Integer numero;

	@Column(name = "complemento")
	private String complemento;

	@Column(name = "uf")
	private String uf;

	@OneToOne(mappedBy = "endereco")
	private Cliente cliente;

	
	/**
	 * @return the id_endereco
	 */
	public Integer getId_endereco() {
		return id_endereco;
	}


	/**
	 * @param id_endereco the id_endereco to set
	 */
	public void setId_endereco(Integer id_endereco) {
		this.id_endereco = id_endereco;
	}


	/**
	 * @return the cep
	 */
	public String getCep() {
		return cep;
	}


	/**
	 * @param cep the cep to set
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}


	/**
	 * @return the logradouro
	 */
	public String getLogradouro() {
		return logradouro;
	}


	/**
	 * @param logradouro the logradouro to set
	 */
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}


	/**
	 * @return the bairro
	 */
	public String getBairro() {
		return bairro;
	}


	/**
	 * @param bairro the bairro to set
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}


	/**
	 * @return the localidade
	 */
	public String getLocalidade() {
		return localidade;
	}


	/**
	 * @param localidade the localidade to set
	 */
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}


	/**
	 * @return the numero
	 */
	public Integer getNumero() {
		return numero;
	}


	/**
	 * @param numero the numero to set
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
	}


	/**
	 * @return the complemento
	 */
	public String getComplemento() {
		return complemento;
	}


	/**
	 * @param complemento the complemento to set
	 */
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}


	/**
	 * @return the uf
	 */
	public String getUf() {
		return uf;
	}


	/**
	 * @param uf the uf to set
	 */
	public void setUf(String uf) {
		this.uf = uf;
	}


	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}


	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	@Override
	public String toString() {
		return "Endereco [id_endereco=" + id_endereco + ", cep=" + cep + ", rua=" + "logradouro" + ", bairro=" + bairro
				+ ", cidade=" + "localidade" + ", numero=" + numero + ", complemento=" + complemento + ", uf=" + uf
				+ ", cliente=" + cliente + "]";
	}

}
