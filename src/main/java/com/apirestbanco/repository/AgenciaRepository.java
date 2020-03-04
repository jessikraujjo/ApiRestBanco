package com.apirestbanco.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.apirestbanco.model.Agencia;
import com.apirestbanco.model.Cliente;

@Repository
public interface AgenciaRepository extends CrudRepository<Agencia, Long>{

}
