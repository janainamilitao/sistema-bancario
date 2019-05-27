package com.zabbix.sistemabancario.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
/**
 * @author janaina militão
 */

@Entity(name = "conta")

public class Conta {

	@Id
	@SequenceGenerator(name = "conta_seq", sequenceName = "conta_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conta_seq")
	private Long id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "pessoa_id", foreignKey = @ForeignKey(name = "PESSOA_ID_FK"))
	private Pessoa pessoa;

	@NotNull
	@Column (name = "saldo")
	private BigDecimal saldo;

	@NotNull
	@Column (name = "limite_saque_diario")
	private BigDecimal limiteSaqueDiario;

	@NotNull
	@Column (name = "flag_ativo")
	private Boolean flagAtivo;

	@NotNull
	@Column (name = "tipo_conta")
	private TipoContaEnum tipoConta;

	@NotNull
	@Column (name = "data_criacao")
	private Date dataCriacao;	

	public Conta() {

	}

	public  Conta(Pessoa pessoa, BigDecimal saldo, BigDecimal limiteSaqueDiario, Boolean flagAtivo, TipoContaEnum tipoConta, Date dataCriacao) {
		this.pessoa = pessoa;
		this.saldo = saldo;
		this.limiteSaqueDiario = limiteSaqueDiario;
		this.flagAtivo = this.flagAtivo;
		this.tipoConta = tipoConta;
		this.dataCriacao = dataCriacao;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
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



	public void setLimiteSaqueDiario(BigDecimal limiteSaqueDiario) {
		this.limiteSaqueDiario = limiteSaqueDiario;
	}

	public BigDecimal getLimiteSaqueDiario() {
		return limiteSaqueDiario;
	}

}
