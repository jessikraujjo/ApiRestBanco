package com.apirestbanco.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ForeignKey;

@Entity
public class Conta implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String num_conta;
	
	@ForeignKey(name = "agencia_id")
	@ManyToOne(optional = false)
	private Agencia agencia;
	
	@OneToMany(mappedBy = "conta", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<TipoConta> tipo_conta = new ArrayList<TipoConta>();
	
	@ForeignKey(name = "cliente_id")
	@ManyToOne(optional = false)
	private Cliente cliente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNum_conta() {
		return num_conta;
	}

	public void setNum_conta(String num_conta) {
		this.num_conta = num_conta;
	}

	public List<TipoConta> getTipo_conta() {
		return tipo_conta;
	}

	public void setTipo_conta(List<TipoConta> tipo_conta) {
		this.tipo_conta = tipo_conta;
	}

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	
}
