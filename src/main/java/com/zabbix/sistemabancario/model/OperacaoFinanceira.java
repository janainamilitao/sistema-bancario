package com.zabbix.sistemabancario.model;

import java.math.BigDecimal;
import java.util.Date;

public class OperacaoFinanceira {
	
	private BigDecimal saldoAnterior;
	
	private BigDecimal saldoAtual;
	
	private BigDecimal valorOperacao;
	
	private Date dataOperacao;
	
	private TipoTransacaoEnum tipoOperacao;


	public BigDecimal getSaldoAnterior() {
		return saldoAnterior;
	}

	public void setSaldoAnterior(BigDecimal saldoAnterior) {
		this.saldoAnterior = saldoAnterior;
	}

	public BigDecimal getSaldoAtual() {
		return saldoAtual;
	}

	public void setSaldoAtual(BigDecimal saldoAtual) {
		this.saldoAtual = saldoAtual;
	}

	public TipoTransacaoEnum getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(TipoTransacaoEnum tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public Date getDataOperacao() {
		return dataOperacao;
	}

	public void setDataOperacao(Date dataOperacao) {
		this.dataOperacao = dataOperacao;
	}

	public BigDecimal getValorOperacao() {
		return valorOperacao;
	}

	public void setValorOperacao(BigDecimal valorOperacao) {
		this.valorOperacao = valorOperacao;
	}

}
