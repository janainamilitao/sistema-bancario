package com.zabbix.sistemabancario.model;

/**
 * @author janaina militão
 */

public enum TipoContaEnum {
	
	CONTA_CORRENTE(0, "Conta Corrente"),
	CONTA_POUPANCA(1, "Conta Poupança"),
	CONTA_SALARIO(2, "Conta Salário");
	 
    public int codigo;
    public String descricao;
    
    TipoContaEnum(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }


}
