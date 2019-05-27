package com.zabbix.sistemabancario.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zabbix.sistemabancario.exception.ContaDisabledException;
import com.zabbix.sistemabancario.exception.ContaNotFoundException;
import com.zabbix.sistemabancario.model.Conta;
import com.zabbix.sistemabancario.model.Extrato;
import com.zabbix.sistemabancario.model.OperacaoFinanceira;
import com.zabbix.sistemabancario.model.TipoTransacaoEnum;
import com.zabbix.sistemabancario.model.Transacao;
import com.zabbix.sistemabancario.service.ContaService;
import com.zabbix.sistemabancario.service.TransacaoService;
import com.zabbix.sistemabancario.utils.DateUtil;

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

	@Autowired
	private ContaService contaService;
	
	/***
	 * Deposita valor em conta recebendo como paramentro ID da conta e caso não exista lança exceção.
	 * @param transacao
	 * @param idConta
	 * @return Conta
	 * @throws Exception
	 */

	@PostMapping("/deposito/{idConta}")
	public Conta deposito(@RequestBody Transacao transacao, @PathVariable("idConta") Long idConta) throws Exception {

		Optional<Conta> contaOptional = contaService.findById(idConta);		

		if(contaOptional.isPresent()) {	
			Conta conta = contaOptional.get();
			
			if(conta.getFlagAtivo().equals(false)) {
				throw new ContaDisabledException();
			}
			
			transacao.setDataTransacao(new Date());
			transacao.setConta(conta);
			transacao.setTipoTransacao(TipoTransacaoEnum.DEPOSITO);
			transacaoService.save(transacao);

			conta.setSaldo(conta.getSaldo().add(transacao.getValor()));				
			return contaService.save(conta);
		}else {			
			throw new ContaNotFoundException();
		}

	}

	/***
	 * Realiza saque  em conta recebendo como paramentro ID da conta e caso não exista lança exceção.
	 * @param transacao
	 * @param idConta
	 * @return Conta
	 * @throws Exception
	 */
	@PostMapping("/saque/{idConta}")
	public Conta saque(@RequestBody Transacao transacao, @PathVariable("idConta") Long idConta) throws Exception {

		Optional<Conta> contaOptional = contaService.findById(idConta);		

		if(contaOptional.isPresent()) {	
			Conta conta = contaOptional.get();
			
			if(conta.getFlagAtivo().equals(false)) {
				throw new ContaDisabledException();
			}
			if(transacao.getValor().longValueExact()>conta.getSaldo().longValueExact()) {
				throw new Exception("Saldo insuficiente para o saque. Verique o saldo da conta.");
			}else {
				if(transacao.getValor().longValue()>conta.getLimiteSaqueDiario().longValue()) {
					throw new Exception("O saque excede o limite de saque diário de R$"+conta.getLimiteSaqueDiario());
				}
				transacao.setDataTransacao(new Date());
				transacao.setConta(conta);
				transacao.setTipoTransacao(TipoTransacaoEnum.SAQUE);
				transacaoService.save(transacao);
				
				conta.setSaldo(conta.getSaldo().subtract(transacao.getValor()));				
				return contaService.save(conta);
			}	

		}else {			
			throw new ContaNotFoundException();
		}
	}
	/**
	 * Realiza extrato da conta recebendo como paramentro ID da conta e caso não exista lança exceção.
	 * @param idConta
	 * @return List
	 * @throws Exception
	 */
	
	@GetMapping("/extrato/{idConta}")
	public Extrato transacoesPorConta(@PathVariable("idConta") Long idConta) throws Exception{
		
		Optional<Conta> contaOptional = contaService.findById(idConta);		

		if(contaOptional.isPresent()) {	
			Conta conta = contaOptional.get();	
			List<Transacao> transacoes = transacaoService.findByConta(conta);
			
			Extrato extrato = calcularExtrato(conta, transacoes);
			
			return extrato;
			
		}else {			
			throw new ContaNotFoundException();
		}
	}
	
	/**
	 * Extrato por perído recebendo como parâmetro mês e ano
	 * @param idConta
	 * @param mes
	 * @param ano
	 * @return List
	 * @throws Exception
	 */
	@GetMapping("/extratoPeriodo/{idConta}/{mes}/{ano}")
	public Extrato extratoPorPeriodo(@PathVariable("idConta") Long idConta,
			                                 @PathVariable("mes") int mes, 
			                                 @PathVariable("ano") int ano ) throws Exception{
		
		Optional<Conta> contaOptional = contaService.findById(idConta);	
		
		if(contaOptional.isPresent()) {	
			Conta conta = contaOptional.get();
						
			Date inicioPeriodo = DateUtil.retornarDataInicio(mes -1 , ano);
			Date fimPeriodo = DateUtil.retornarDataFim(mes -1, ano);
			
			List<Transacao> transacoes = transacaoService.findByContaAndDataTransacaoBetween(conta, inicioPeriodo, fimPeriodo);
			
			Extrato extrato = calcularExtrato(conta, transacoes);
			
			return extrato;
		}else {
			throw new ContaNotFoundException();
		}		
	}

	private Extrato calcularExtrato(Conta conta, List<Transacao> transacoes) {
		Extrato extrato = new Extrato();
		extrato.setIdConta(conta.getId());
		extrato.setUsuarioConta(conta.getPessoa().getNome());
		
		
		List<OperacaoFinanceira> operacoes = new ArrayList<OperacaoFinanceira>();
		for(Transacao transacao: transacoes) {
			OperacaoFinanceira operacao = new OperacaoFinanceira();
			operacao.setDataOperacao(transacao.getDataTransacao());
			operacao.setTipoOperacao(transacao.getTipoTransacao());	
			operacao.setValorOperacao(transacao.getValor());

			BigDecimal saldoAnterior = conta.getSaldo();
			
			if(operacao.getTipoOperacao().equals(TipoTransacaoEnum.DEPOSITO)) {
				saldoAnterior = saldoAnterior.subtract(transacao.getValor());
				operacao.setSaldoAnterior(saldoAnterior);
				operacao.setSaldoAtual(saldoAnterior.add(transacao.getValor()));
				
			}else if(operacao.getTipoOperacao().equals(TipoTransacaoEnum.SAQUE)) {
				saldoAnterior = saldoAnterior.add(transacao.getValor());
				operacao.setSaldoAnterior(saldoAnterior);
				operacao.setSaldoAtual(saldoAnterior.subtract(transacao.getValor()));				
			}
			
			operacoes.add(operacao);
		}
		
		extrato.setOperacaoFinanceiraList(operacoes);
		
		return extrato;
	}	

}
