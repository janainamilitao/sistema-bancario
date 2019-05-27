package com.zabbix.sistemabancario.exception;

public class ContaDisabledException extends Exception {

	private static final long serialVersionUID = 1L;

	public ContaDisabledException() {
		super("Operação não permitida. Conta bloqueada!");
	}
}

