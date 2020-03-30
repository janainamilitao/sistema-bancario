package com.zabbix.sistemabancario.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zabbix.sistemabancario.exception.ContaDisabledException;
import com.zabbix.sistemabancario.exception.ContaNotFoundException;
import com.zabbix.sistemabancario.model.Conta;
import com.zabbix.sistemabancario.model.Extrato;
import com.zabbix.sistemabancario.model.OperacaoFinanceira;
import com.zabbix.sistemabancario.model.TipoTransacaoEnum;
import com.zabbix.sistemabancario.model.Transacao;
import com.zabbix.sistemabancario.repository.ContaRepository;
import com.zabbix.sistemabancario.repository.TransacaoRepository;
import com.zabbix.sistemabancario.utils.DateUtil;
/**
 * @author janaina militão
 */

@Service
public class TransacaoService extends GenericService<Transacao>{

	@Autowired
	private TransacaoRepository transacaoRepository;

	@Autowired
	private ContaRepository contaReporitory;

	public Conta deposito(BigDecimal valor, Long idConta) throws ContaNotFoundException, ContaDisabledException {
		Optional<Conta> contaOptional = contaReporitory.findById(idConta);		

		if(contaOptional.isPresent()) {	
			Conta conta = contaOptional.get();

			if(conta.getFlagAtivo().equals(false)) {
				throw new ContaDisabledException();
			}

			Transacao transacao = new Transacao();
			transacao.setDataTransacao(new Date());
			transacao.setConta(conta);
			transacao.setTipoTransacao(TipoTransacaoEnum.DEPOSITO);
			transacao.setValor(valor);
			transacaoRepository.saveAndFlush(transacao);

			conta.setSaldo(conta.getSaldo().add(transacao.getValor()));				
			return contaReporitory.saveAndFlush(conta);
		}else {			
			throw new ContaNotFoundException();
		}
	}

	public Conta saque(BigDecimal valor, Long idConta) throws Exception {

		Optional<Conta> contaOptional = contaReporitory.findById(idConta);		

		if(contaOptional.isPresent()) {	
			Conta conta = contaOptional.get();

			if(conta.getFlagAtivo().equals(false)) {
				throw new ContaDisabledException();
			}

			if(valor.longValueExact()>conta.getSaldo().longValueExact()) {
				throw new Exception("Saldo insuficiente para o saque. Verique o saldo da conta.");
			}else {
				if(valor.longValue()>conta.getLimiteSaqueDiario().longValue()) {
					throw new Exception("O saque excede o limite de saque diário de R$"+conta.getLimiteSaqueDiario());
				}

				Transacao transacao = new Transacao();				
				transacao.setDataTransacao(new Date());
				transacao.setConta(conta);
				transacao.setTipoTransacao(TipoTransacaoEnum.SAQUE);
				transacao.setValor(valor);
				transacaoRepository.saveAndFlush(transacao);

				conta.setSaldo(conta.getSaldo().subtract(transacao.getValor()));				
				return contaReporitory.saveAndFlush(conta);
			}
		}else {			
			throw new ContaNotFoundException();
		}
	}

	public Extrato extrato(Long idConta) throws ContaNotFoundException {
		Optional<Conta> contaOptional = contaReporitory.findById(idConta);		

		if(contaOptional.isPresent()) {	
			Conta conta = contaOptional.get();	
			List<Transacao> transacoes = transacaoRepository.findByConta(conta);

			Extrato extrato = calcularExtrato(conta, transacoes);

			return extrato;
		}else {			
			throw new ContaNotFoundException();
		}
	}	

	public Extrato extratoPorPeriodo(Long idConta, int mes, int ano) throws ContaNotFoundException {
		Optional<Conta> contaOptional = contaReporitory.findById(idConta);	

		if(contaOptional.isPresent()) {	
			Conta conta = contaOptional.get();

			Date inicioPeriodo = DateUtil.retornarDataInicio(mes -1 , ano);
			Date fimPeriodo = DateUtil.retornarDataFim(mes -1, ano);

			List<Transacao> transacoes = transacaoRepository.findByContaAndDataTransacaoBetween(conta, inicioPeriodo, fimPeriodo);

			Extrato extrato = calcularExtrato(conta, transacoes);

			return extrato;
		}else {
			throw new ContaNotFoundException();
		}		
	}	
	
	private Extrato calcularExtrato(Conta conta, List<Transacao> transacoes) {
		List<OperacaoFinanceira> operacoes = new ArrayList<OperacaoFinanceira>();
		
		transacoes.stream().forEach(transacao -> calculaOperacaoFinanceira(conta, operacoes, transacao));

		return new Extrato(conta.getId(), conta.getPessoa().getNome(), operacoes);
	}

	private void calculaOperacaoFinanceira(Conta conta, List<OperacaoFinanceira> operacoes, Transacao transacao) {
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

}