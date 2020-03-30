package com.zabbix.sistemabancario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zabbix.sistemabancario.model.Pessoa;
import com.zabbix.sistemabancario.model.dto.PessoaRequest;
import com.zabbix.sistemabancario.repository.PessoaRepository;
/**
 * @author janaina militão
 */

@Service
public class PessoaService extends GenericService<Pessoa>{

	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa cadastrarPessoa(PessoaRequest pessoaRequest) {
		
		Pessoa pessoa = pessoaRequest.transformaParaObjeto();
		pessoa.setCpf(pessoa.getCpf().replace(".", "").replace("-", ""));
		pessoa.setAtivo(true);
		
		return pessoaRepository.saveAndFlush(pessoa);
	}

	public List<Pessoa> listarPessoas() {		
		return pessoaRepository.findAll();
	}

}
