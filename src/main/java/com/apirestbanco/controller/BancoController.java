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

import com.apirestbanco.model.Agencia;
import com.apirestbanco.model.Banco;
import com.apirestbanco.model.Cliente;
import com.apirestbanco.repository.BancoRepository;
import com.apirestbanco.repository.ClienteRepository;


@RestController 
@RequestMapping(value = "/banco")
public class BancoController {
	
	@Autowired 
	private BancoRepository bancorepository;
	
	
	@GetMapping(value = "/{id}", produces = "application/json")
	@CachePut("cacheuser")
	public ResponseEntity <Banco>init(@PathVariable(value = "id") Long id) {
		 
		Optional<Banco> banco = bancorepository.findById(id);
		 
		return new ResponseEntity<Banco>(banco.get(), HttpStatus.OK);

	}
	
	@GetMapping(value = "/", produces = "application/json")
	@CachePut("cachebancos")
	public ResponseEntity <List<Banco>>cliente() throws InterruptedException {
		List<Banco> list = (List<Banco>) bancorepository.findAll();
		
		return new ResponseEntity<List<Banco>>(list, HttpStatus.OK);

	}
	
		
	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<Banco>cadastrar(@RequestBody Banco banco){
		Banco bancoSalvo = bancorepository.save(banco);
		return new ResponseEntity<Banco>(bancoSalvo, HttpStatus.OK);
	}
	
}

