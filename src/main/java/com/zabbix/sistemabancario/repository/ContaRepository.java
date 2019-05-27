package com.zabbix.sistemabancario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zabbix.sistemabancario.model.Conta;

/**
 * @author janaina militão
 */

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

}
