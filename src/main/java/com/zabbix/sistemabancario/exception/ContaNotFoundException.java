package com.zabbix.sistemabancario.exception;

/**
 * @author janaina militão
 */
public class ContaNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public ContaNotFoundException() {
		super("Conta não encontrada");
	}
}
