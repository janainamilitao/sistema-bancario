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
@Entity
public class Transacao {
	
	@Id
    @SequenceGenerator(name = "transacao_seq", sequenceName = "transacao_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transacao_seq")
    private Long id;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "conta_id", foreignKey = @ForeignKey(name = "CONTA_ID_FK"))
	private Conta conta;
	
	@NotNull
	@Column (name = "valor")
	private BigDecimal valor;
	
	@NotNull
	@Column (name = "data_transacao")
	private Date dataTransacao;
	
	@NotNull
	@Column (name = "tipo_transacao")
	private TipoTransacaoEnum tipoTransacao;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getDataTransacao() {
		return dataTransacao;
	}

	public void setDataTransacao(Date dataTransacao) {
		this.dataTransacao = dataTransacao;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public TipoTransacaoEnum getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(TipoTransacaoEnum tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}
	
}
