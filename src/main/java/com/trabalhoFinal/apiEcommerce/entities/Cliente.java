package com.trabalhoFinal.apiEcommerce.entities;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id_cliente", scope = Cliente.class)
@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private Integer id_cliente;

	@NotBlank
	@Column(name = "email", unique = true)
	private String email;

	@NotBlank
	@Column(name = "nome_completo")
	private String nome_completo;

	@NotBlank
	@Column(name = "cpf", unique = true)
	private String cpf;

	@NotBlank
	@Column(name = "telefone")
	private String telefone;

	@NotNull
	@Column(name = "data_nascimento")
	private LocalDate data_nascimento;

	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos;

	@OneToOne
	@JoinColumn(name = "id_endereco", referencedColumnName = "id_endereco", unique = true)
	private Endereco endereco;

	public Integer getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome_completo() {
		return nome_completo;
	}

	public void setNome_completo(String nome_completo) {
		this.nome_completo = nome_completo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDate getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(LocalDate data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return "Cliente [id_cliente=" + id_cliente + ", email=" + email + ", nome_completo=" + nome_completo + ", cpf="
				+ cpf + ", telefone=" + telefone + ", data_nascimento=" + data_nascimento + ", pedidos=" + pedidos
				+ "]";
	}

}
