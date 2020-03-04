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

import com.apirestbanco.model.Cliente;
import com.apirestbanco.repository.ClienteRepository;



@RestController 
@RequestMapping(value = "/cliente")
public class ClienteController {
	
	@Autowired 
	private ClienteRepository clienterepository;
	
	
	@GetMapping(value = "/{id}", produces = "application/json")
	@CachePut("cacheuser")
	public ResponseEntity <Cliente>init(@PathVariable(value = "id") Long id) {
		 
		Optional<Cliente> cliente = clienterepository.findById(id);
		 
		return new ResponseEntity<Cliente>(cliente.get(), HttpStatus.OK);

	}
	
	@GetMapping(value = "/", produces = "application/json")
	@CachePut("cacheclientes")
	public ResponseEntity <List<Cliente>>cliente() throws InterruptedException {
		List<Cliente> list = (List<Cliente>) clienterepository.findAll();
		
		return new ResponseEntity<List<Cliente>>(list, HttpStatus.OK);

	}
	
	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<Cliente>cadastrar(@RequestBody Cliente cliente){
		
		for(int pos = 0 ; pos < cliente.getTelefones().size(); pos++) {
			cliente.getTelefones().get(pos).setCliente(cliente);
		}
		for(int pos = 0 ; pos < cliente.getContas().size(); pos++) {
			cliente.getContas().get(pos).setCliente(cliente);
		}
		String senhacriptografada = new BCryptPasswordEncoder().encode(cliente.getSenha());
		cliente.setSenha(senhacriptografada);
		Cliente clienteSalvo = clienterepository.save(cliente);
		
		return new ResponseEntity<Cliente>(clienteSalvo, HttpStatus.OK);
	}	
	
	
}

