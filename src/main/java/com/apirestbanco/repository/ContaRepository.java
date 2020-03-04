package com.apirestbanco.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.apirestbanco.model.Cliente;
import com.apirestbanco.model.Conta;

@Repository
public interface ContaRepository extends CrudRepository<Conta, Long>{

}
