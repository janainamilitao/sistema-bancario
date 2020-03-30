package com.zabbix.sistemabancario.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperacaoFinanceira {
	
	private BigDecimal saldoAnterior;
	
	private BigDecimal saldoAtual;
	
	private BigDecimal valorOperacao;
	
	private Date dataOperacao;
	
	private TipoTransacaoEnum tipoOperacao;
	
}
