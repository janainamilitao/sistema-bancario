package com.zabbix.sistemabancario.exception;

/**
 * @author janaina militão
 */
public class PessoaNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;

	public PessoaNotFoundException() {
		super("Pessoa não encontrada");
	}
}
