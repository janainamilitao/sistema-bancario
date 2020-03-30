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

import lombok.Getter;
import lombok.Setter;
/**
 * @author janaina militão
 */

@Entity(name = "conta")
@Getter
@Setter
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

	public  Conta(Pessoa pessoa, BigDecimal saldo, BigDecimal limiteSaqueDiario, TipoContaEnum tipoConta) {
		this.pessoa = pessoa;
		this.saldo = saldo;
		this.limiteSaqueDiario = limiteSaqueDiario;
		this.tipoConta = tipoConta;
	}
}
