package com.apirestbanco.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apirestbanco.model.Conta;
import com.apirestbanco.repository.ClienteRepository;
import com.apirestbanco.repository.ContaRepository;


@RestController 
@RequestMapping(value = "/conta")
public class ContaController {
	
	@Autowired 
	private ContaRepository contarepository;
	
	
	@GetMapping(value = "/{id}", produces = "application/json")
	@CachePut("cacheuser")
	public ResponseEntity <Conta>init(@PathVariable(value = "id") Long id) {
		 
		Optional<Conta> conta = contarepository.findById(id);
		 
		return new ResponseEntity<Conta>(conta.get(), HttpStatus.OK);

	}
	
	@GetMapping(value = "/", produces = "application/json")
	@CachePut("cacheclientes")
	public ResponseEntity <List<Conta>>cliente() throws InterruptedException {
		List<Conta> list = (List<Conta>) contarepository.findAll();
		
		return new ResponseEntity<List<Conta>>(list, HttpStatus.OK);

	}
	
	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<Conta>cadastrar(@RequestBody Conta conta){
		Conta contaSalva = contarepository.save(conta);
		return new ResponseEntity<Conta>(contaSalva, HttpStatus.OK);
	}	
	
	
}

