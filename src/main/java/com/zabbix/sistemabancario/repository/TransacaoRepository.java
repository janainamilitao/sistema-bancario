package com.zabbix.sistemabancario.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zabbix.sistemabancario.model.Conta;
import com.zabbix.sistemabancario.model.Transacao;

/**
 * @author janaina militão
 */

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long>{	
	
	public List<Transacao> findByConta(Conta conta);
	
	public List<Transacao> findByContaAndDataTransacaoBetween(Conta conta, Date inicio, Date fim);
	
}
