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
import com.apirestbanco.model.Cliente;
import com.apirestbanco.repository.AgenciaRepository;
import com.apirestbanco.repository.ClienteRepository;

@RestController 
@RequestMapping(value = "/agencia")
public class AgenciaController {
	
	@Autowired 
	private AgenciaRepository agenciarepository;
	
	
	@GetMapping(value = "/{id}", produces = "application/json")
	@CachePut("cacheuser")
	public ResponseEntity <Agencia>init(@PathVariable(value = "id") Long id) {
		 
		Optional<Agencia> agencia = agenciarepository.findById(id);
		 
		return new ResponseEntity<Agencia>(agencia.get(), HttpStatus.OK);

	}
	
	@GetMapping(value = "/", produces = "application/json")
	@CachePut("cacheclientes")
	public ResponseEntity <List<Agencia>>cliente() throws InterruptedException {
		List<Agencia> list = (List<Agencia>) agenciarepository.findAll();
		
		return new ResponseEntity<List<Agencia>>(list, HttpStatus.OK);

	}
	
	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<Agencia>cadastrar(@RequestBody Agencia agencia){
		Agencia agenciaSalva = agenciarepository.save(agencia);
		return new ResponseEntity<Agencia>(agenciaSalva, HttpStatus.OK);
	}
	
	
}

