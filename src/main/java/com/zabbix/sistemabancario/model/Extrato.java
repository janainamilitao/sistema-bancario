package com.zabbix.sistemabancario.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Extrato {
	
	private Long idConta;
	
	private String usuarioConta;
	
	List<OperacaoFinanceira> operacaoFinanceiraList;

}