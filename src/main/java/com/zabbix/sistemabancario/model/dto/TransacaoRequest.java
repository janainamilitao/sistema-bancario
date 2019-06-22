package com.zabbix.sistemabancario.model.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class TransacaoRequest {

	@NotNull
	private BigDecimal valor;

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	
}
