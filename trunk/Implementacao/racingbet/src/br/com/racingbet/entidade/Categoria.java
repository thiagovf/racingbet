package br.com.racingbet.entidade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Categoria implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_categoria")
	private Long id;

	@Column(nullable = false, length = 50)
	private String nome;

	@Column(nullable = false, length = 100)
	private String descricao;

	@Column(length = 50)
	private String tipoveiculo;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "categoria", orphanRemoval = true)
	private List<Equipe> equipes;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "categoria", orphanRemoval = true)
	private List<GrandePremio> grandespremios;
	
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipoveiculo() {
		return tipoveiculo;
	}

	public void setTipoveiculo(String tipoveiculo) {
		this.tipoveiculo = tipoveiculo;
	}

	public List<Equipe> getEquipes() {
		return equipes;
	}

	public void setEquipes(List<Equipe> equipes) {
		this.equipes = equipes;
	}

	public List<GrandePremio> getGrandespremios() {
		return grandespremios;
	}

	public void setGrandespremios(List<GrandePremio> grandespremios) {
		this.grandespremios = grandespremios;
	}

	public Categoria() {
		nome = null;
		descricao = null;
		tipoveiculo = null;
	}

	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
