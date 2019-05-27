package com.zabbix.sistemabancario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zabbix.sistemabancario.model.Pessoa;
import com.zabbix.sistemabancario.model.dto.PessoaDTO;
import com.zabbix.sistemabancario.repository.PessoaRepository;
/**
 * @author janaina militão
 */

@Service
public class PessoaService extends GenericService<Pessoa>{

	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa cadastrarPessoa(PessoaDTO pessoaDTO) {
		
		Pessoa pessoa = pessoaDTO.transformaParaObjeto();	
		
		return pessoaRepository.saveAndFlush(pessoa);
	}
	

}
