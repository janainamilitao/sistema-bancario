package com.zabbix.sistemabancario.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zabbix.sistemabancario.model.Conta;
import com.zabbix.sistemabancario.model.Transacao;
import com.zabbix.sistemabancario.repository.TransacaoRepository;
/**
 * @author janaina militão
 */

@Service
public class TransacaoService extends GenericService<Transacao>{
	
	@Autowired
    private TransacaoRepository repository;	  

	public List<Transacao> findByConta(Conta conta) {
        return repository.findByConta(conta);
    }
	
	public List<Transacao> findByContaAndDataTransacaoBetween(Conta conta, Date inicio, Date fim) {
        return repository.findByContaAndDataTransacaoBetween(conta, inicio, fim);
    }

}
