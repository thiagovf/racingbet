package br.com.racingbet.entidade;



import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
public class Equipe implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_equipe")
	private Long id;
	
	@Column(nullable=false, length=50)
	private String nome;
	
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private Date data_fundacao;
	
	@Column(nullable=false, length=50)
	private String nacionalidade;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "equipe", orphanRemoval = true)
    private List<Piloto> pilotos;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="id_categoria", nullable=false, insertable=true, updatable=true)
	private Categoria categoria;
	
	public List<Piloto> getPilotos() {
		return pilotos;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public void setPilotos(List<Piloto> pilotos) {
		this.pilotos = pilotos;
	}

	public Long getId() {
		return id;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
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

	public Date getData_fundacao() {
		return data_fundacao;
	}

	public void setData_fundacao(Date data_fundacao) {
		this.data_fundacao = data_fundacao;
	}
	
	public Equipe() {
		nome = null;
		data_fundacao = null;
	}

}
