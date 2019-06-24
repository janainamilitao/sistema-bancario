package com.zabbix.sistemabancario.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.zabbix.sistemabancario.model.Pessoa;

public class PessoaRequest {
	
		
	@NotBlank
	private String nome;
	
	@NotNull
	private String cpf;
	
	@NotNull
	private String telefone;
	
	public  Pessoa transformaParaObjeto() {
		return new Pessoa(nome, cpf, telefone);
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}	
}
