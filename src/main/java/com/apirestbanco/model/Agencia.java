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
public class Agencia implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String num_agencia;
	
	@ForeignKey(name = "banco_id")
	@ManyToOne(optional = false)
	private Banco banco; 
	
	@OneToMany(mappedBy = "agencia", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Conta> contas = new ArrayList<Conta>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNum_agencia() {
		return num_agencia;
	}

	public void setNum_agencia(String num_agencia) {
		this.num_agencia = num_agencia;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	

}
