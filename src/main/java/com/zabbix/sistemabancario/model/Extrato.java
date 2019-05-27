package com.zabbix.sistemabancario.model;

import java.util.List;

public class Extrato {
	
	private Long idConta;
	
	private String usuarioConta;
	
	List<OperacaoFinanceira> operacaoFinanceiraList;

	public Long getIdConta() {
		return idConta;
	}

	public void setIdConta(Long idConta) {
		this.idConta = idConta;
	}

	public String getUsuarioConta() {
		return usuarioConta;
	}

	public void setUsuarioConta(String usuarioConta) {
		this.usuarioConta = usuarioConta;
	}

	public List<OperacaoFinanceira> getOperacaoFinanceiraList() {
		return operacaoFinanceiraList;
	}

	public void setOperacaoFinanceiraList(List<OperacaoFinanceira> operacaoFinanceiraList) {
		this.operacaoFinanceiraList = operacaoFinanceiraList;
	}

}
