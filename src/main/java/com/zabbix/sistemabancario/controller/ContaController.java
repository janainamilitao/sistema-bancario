package com.zabbix.sistemabancario.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zabbix.sistemabancario.model.Conta;
import com.zabbix.sistemabancario.model.dto.ContaRequest;
import com.zabbix.sistemabancario.service.ContaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/***
 * @author janaina
 */

@RestController
@RequestMapping("/contas")
@Api(tags = "Contas", description = "Operações relacionas as contas")
public class ContaController {
	
	@Autowired
	private ContaService contaService;
	
	private static final String APPLICATION_JSON  = "application/json";
	
	/****
	 * Cria uma conta recebendo como paramentro ID de pessoa já criada e caso não exista lança uma exceção
	 */
	@ApiOperation(value = "Criação de conta bancaria", response = Conta.class)
	@RequestMapping(value = "/criar/{idPessoa}", method= RequestMethod.POST, produces = APPLICATION_JSON )
	public Conta criarConta(ContaRequest conta, @PathVariable("idPessoa") Long idPessoa) throws Exception {
		return contaService.criarConta(conta, idPessoa);	
	}
	
	
	/*****
	 * Consulta o saldo recebendo como paramentro ID de uma conta e caso não exista lança uma exceção.
	 */
	@ApiOperation(value = "Consulta de conta bancaria", response = String.class)
	@RequestMapping(value = "/saldo/{idConta}", method= RequestMethod.GET, produces = APPLICATION_JSON)
	public BigDecimal consultarSaldo(@PathVariable("idConta") Long idConta) throws Exception {	
		return contaService.consultarSaldo(idConta);
	}
	
	/**
	 * Bloqueio de conta recebendo como paramentro ID  da conta e caso não exista lança exceção 
	 */	
	@ApiOperation(value = "Bloqueio de conta bancaria", response = Conta.class)
	@RequestMapping(value = "/bloquear/{idConta}", method= RequestMethod.POST, produces = APPLICATION_JSON)
	public Conta bloquearConta(@PathVariable("idConta") Long idConta) throws Exception {
		return contaService.bloquearConta(idConta);
	}
	
	/***
	 *  Desbloqueia conta recebendo como paramentro ID da conta e caso não exista lança exceção.
	 */
	@ApiOperation(value = "Desbloqueio de conta bancaria", response = Conta.class)
	@RequestMapping(value = "/desbloquear/{idConta}", method= RequestMethod.POST, produces = APPLICATION_JSON)
	public Conta desbloquearConta(@PathVariable("idConta") Long idConta) throws Exception {
		return contaService.desbloquearConta(idConta);
	}
	
	@ApiOperation(value = "Listagem de contas ativas", response = Conta.class)
	@RequestMapping(value = "/listar", method= RequestMethod.GET, produces = APPLICATION_JSON)
	public List<Conta> listarContas() throws Exception {
		return contaService.listarContarAtivas();
	}	
}
