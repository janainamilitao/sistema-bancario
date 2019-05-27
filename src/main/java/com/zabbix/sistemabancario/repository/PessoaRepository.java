package com.zabbix.sistemabancario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zabbix.sistemabancario.model.Pessoa;

/**
 * @author janaina militão
 */

@Repository
public interface PessoaRepository  extends JpaRepository<Pessoa, Long>{


}
