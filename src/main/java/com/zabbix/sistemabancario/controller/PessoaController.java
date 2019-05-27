package com.zabbix.sistemabancario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zabbix.sistemabancario.model.Pessoa;
import com.zabbix.sistemabancario.model.dto.PessoaDTO;
import com.zabbix.sistemabancario.service.PessoaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/***
 * @author janaina militão
 */

@RestController
@RequestMapping("/pessoas")
@Api(tags = "Pessoas", description = "Operações relacionas a pessoas")
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;
	
	private static final String APPLICATION_JSON  = "application/json";
	
	@ApiOperation(value = "Cadastro de Pessoa", response = Pessoa.class)
	@RequestMapping(value = "/cadastrarPessoa", 
	                method= RequestMethod.POST, 
	                produces = APPLICATION_JSON)
	public Pessoa cadastrarPessoa(PessoaDTO pessoa) {
		return pessoaService.cadastrarPessoa(pessoa);
	}

}
