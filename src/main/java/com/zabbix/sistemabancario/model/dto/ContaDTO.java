package com.zabbix.sistemabancario.model.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.zabbix.sistemabancario.model.Conta;
import com.zabbix.sistemabancario.model.Pessoa;
import com.zabbix.sistemabancario.model.TipoContaEnum;


public class ContaDTO {


	private Long id;

	private Long idPessoa;

	private BigDecimal saldo;

	private BigDecimal limiteSaqueDiario;

	private Boolean flagAtivo;

	private TipoContaEnum tipoConta;

	private Date dataCriacao;

	
	public Conta transformaParaObjeto(Pessoa pessoa) {
		return new Conta(pessoa, saldo, limiteSaqueDiario, flagAtivo, tipoConta, dataCriacao);
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
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

	public Boolean getFlagAtivo() {
		return flagAtivo;
	}

	public void setFlagAtivo(Boolean flagAtivo) {
		this.flagAtivo = flagAtivo;
	}

	public TipoContaEnum getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoContaEnum tipoConta) {
		this.tipoConta = tipoConta;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}


}
