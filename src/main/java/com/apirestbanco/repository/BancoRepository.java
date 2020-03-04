package com.apirestbanco.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.apirestbanco.model.Banco;
import com.apirestbanco.model.Cliente;

@Repository
public interface BancoRepository extends CrudRepository<Banco, Long>{

}
