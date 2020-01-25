package com.zabbix.sistemabancario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zabbix.sistemabancario.model.Pessoa;
import com.zabbix.sistemabancario.model.dto.PessoaRequest;
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
	@RequestMapping(value = "/cadastrar", method= RequestMethod.POST, produces = APPLICATION_JSON)
	public Pessoa cadastrarPessoa(PessoaRequest pessoa) {
		return pessoaService.cadastrarPessoa(pessoa);
	}

	@ApiOperation(value = "Listagem de Pessoas", response = Pessoa.class)
	@RequestMapping(value = "/listar", method= RequestMethod.GET, produces = APPLICATION_JSON)
	public List<Pessoa> listarPessoas() {
		return pessoaService.listarPessoas();
	}

}
