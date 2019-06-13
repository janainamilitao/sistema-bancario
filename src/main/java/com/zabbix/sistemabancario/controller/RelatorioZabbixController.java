package com.zabbix.sistemabancario.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/zabbix")
@Api(tags = "Relatórios", description = "Operações relacionadas ao relatória a ser exibido no zabbix")
public class RelatorioZabbixController {

}
