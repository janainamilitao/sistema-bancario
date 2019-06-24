package com.zabbix.sistemabancario.model.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.zabbix.sistemabancario.model.Conta;
import com.zabbix.sistemabancario.model.Pessoa;
import com.zabbix.sistemabancario.model.TipoContaEnum;


public class ContaRequest {

	private BigDecimal saldo;

	private BigDecimal limiteSaqueDiario;


	private TipoContaEnum tipoConta;


	
	public Conta transformaParaObjeto(Pessoa pessoa) {
		return new Conta(pessoa, saldo, limiteSaqueDiario, tipoConta);
	}


	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public BigDecimal getLimiteSaqueDiario() {
		return limiteSaqueDiario;
	}

	public void setLimiteSaqueDiario(BigDecimal limiteSaqueDiario) {
		this.limiteSaqueDiario = limiteSaqueDiario;
	}

	
	public TipoContaEnum getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoContaEnum tipoConta) {
		this.tipoConta = tipoConta;
	}



}
