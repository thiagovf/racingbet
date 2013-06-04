package br.com.racingbet.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class GrandePremio implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_grandepremio")
	private Long id;

	@Column(nullable=false)
	private Long id_categoria;

	@Column(nullable=false, length=50)
	private String nome;
	
	@Column(nullable=false, length=50)
	private String pais;
	
	@Column(nullable=false, length=50)
	private String cidade;

	@Column(nullable=false, length=50)
	private String autodromo;

	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="id_categoria", nullable=false, insertable=true, updatable=true)
	private Categoria categoria;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdCategoria() {
		return id_categoria;
	}

	public void setIdCategoria(Long id) {
		this.id_categoria = id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getAutodromo() {
		return autodromo;
	}
	public void setAutodromo(String autodromo) {
		this.autodromo = autodromo;
	}

	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public GrandePremio() {
		nome = null;
		data = null;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}	
}
