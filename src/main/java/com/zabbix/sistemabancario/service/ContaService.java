package com.zabbix.sistemabancario.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zabbix.sistemabancario.exception.ContaNotFoundException;
import com.zabbix.sistemabancario.exception.PessoaNotFoundException;
import com.zabbix.sistemabancario.model.Conta;
import com.zabbix.sistemabancario.model.Pessoa;
import com.zabbix.sistemabancario.model.dto.ContaRequest;
import com.zabbix.sistemabancario.repository.ContaRepository;
import com.zabbix.sistemabancario.repository.PessoaRepository;

/**
 * @author janaina militão
 */

@Service
public class ContaService extends GenericService<Conta>{


	@Autowired 
	private ContaRepository contaRepository;	
	
	@Autowired 
	private PessoaRepository pessoaRepository;
	
	public Conta criarConta(ContaRequest contaRequest, Long idPessoa) throws PessoaNotFoundException {
		Optional<Pessoa> pessoa = pessoaRepository.findById(idPessoa);
		
		if(pessoa.isPresent()) {
			Conta conta  = contaRequest.transformaParaObjeto(pessoa.get());
			conta.setFlagAtivo(Boolean.TRUE);
			conta.setDataCriacao(new Date());
			return contaRepository.saveAndFlush(conta);
		}else {			
			throw new PessoaNotFoundException();
		}	
	}

	public BigDecimal consultarSaldo(Long idConta) throws ContaNotFoundException {
		Optional<Conta> conta = contaRepository.findById(idConta);		

		if(conta.isPresent()) {	
			return conta.get().getSaldo();
		}
		else {
			throw new ContaNotFoundException();
		}
	}

	public Conta bloquearConta(Long idConta) throws ContaNotFoundException {
		Optional<Conta> contaOptional = contaRepository.findById(idConta);		

		if(contaOptional.isPresent()) {	
			Conta conta = contaOptional.get();
			conta.setFlagAtivo(false);
			contaRepository.saveAndFlush(conta);
			return conta;
		}else {
			throw new ContaNotFoundException();
		}
	}

	public Conta desbloquearConta(Long idConta) throws ContaNotFoundException {
		Optional<Conta> contaOptional = contaRepository.findById(idConta);		

		if(contaOptional.isPresent()) {	
			Conta conta = contaOptional.get();
			conta.setFlagAtivo(true);
			contaRepository.saveAndFlush(conta);
			return conta;
		}else {
			throw new ContaNotFoundException();
		}		
	}
	
	public List<Conta> listarContarAtivas() {
		return contaRepository.findByFlagAtivo(true);		
	}

}
