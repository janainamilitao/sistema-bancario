package com.zabbix.sistemabancario.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.zabbix.sistemabancario.model.Pessoa;

public class PessoaDTO {
	
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotNull
	private String cpf;
	
	public  Pessoa transformaParaObjeto() {
		return new Pessoa(nome, cpf);
	}

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}	

}
