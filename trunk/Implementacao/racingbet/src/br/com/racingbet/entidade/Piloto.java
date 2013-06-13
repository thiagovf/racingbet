package br.com.racingbet.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Piloto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_piloto")
	private Long id;
	
	@Column(nullable=false, length=50)
	private String nome;
	
	@Column(nullable=true, length=25)
	private String nacionalidade;
	
	@Column(nullable=true, length=20)
	private String numeroDoCarro;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="id_equipe", nullable=false, insertable=true, updatable=true)
	private Equipe equipe;
	
	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getNumeroDoCarro() {
		return numeroDoCarro;
	}

	public void setNumeroDoCarro(String numeroDoCarro) {
		this.numeroDoCarro = numeroDoCarro;
	}


}
