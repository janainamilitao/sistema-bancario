package com.zabbix.sistemabancario.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zabbix.sistemabancario.model.Conta;
import com.zabbix.sistemabancario.model.Extrato;
import com.zabbix.sistemabancario.service.TransacaoService;

import io.swagger.annotations.Api;
/***
 * @author janaina
 */
@RestController
@RequestMapping("/transacoes")
@Api(tags = "Transações", description = "Operações relacionadas a trasações")
public class TransacaoController {

	@Autowired
	private TransacaoService transacaoService;;
	
	/***
	 * Deposita valor em conta recebendo como paramentro ID da conta e caso não exista lança exceção.
	 */
	@PostMapping("/deposito/{idConta}")
	public Conta deposito(BigDecimal valor, @PathVariable("idConta") Long idConta) throws Exception {		
		return transacaoService.deposito(valor, idConta);
	}

	/***
	 * Realiza saque  em conta recebendo como paramentro ID da conta e caso não exista lança exceção.
	 */
	@PostMapping("/saque/{idConta}")
	public Conta saque(BigDecimal valor, @PathVariable("idConta") Long idConta) throws Exception {		
		return transacaoService.saque(valor, idConta);
	}
	/**
	 * Realiza extrato da conta recebendo como paramentro ID da conta e caso não exista lança exceção.
	 */
	
	@GetMapping("/extrato/{idConta}")
	public Extrato transacoesPorConta(@PathVariable("idConta") Long idConta) throws Exception{		
		return transacaoService.extrato(idConta);
	}
	
	/**
	 * Extrato por perído recebendo como parâmetro mês e ano
	 */
	@GetMapping("/extrato-periodo/{idConta}/{mes}/{ano}")
	public Extrato extratoPorPeriodo(@PathVariable("idConta") Long idConta,  @PathVariable("mes") int mes,  @PathVariable("ano") int ano ) throws Exception{		
		return transacaoService.extratoPorPeriodo(idConta, mes, ano);
	}

}
