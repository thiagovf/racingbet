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
public class Palpite implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_palpite")
	private Long id;

	@Column(nullable = false, length = 50)
	private String respostaPrimeiroColocado;

	@Column(nullable = false, length = 50)
	private String respostaPole;

	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="id_grandepremio")
	private GrandePremio grandePremio;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date data;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getRespostaPole() {
		return respostaPole;
	}

	public void setRespostaPole(String respostaPole) {
		this.respostaPole = respostaPole;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public GrandePremio getGrandePremio() {
		return grandePremio;
	}

	public void setGrandePremio(GrandePremio grandePremio) {
		this.grandePremio = grandePremio;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public String getRespostaPrimeiroColocado() {
		return respostaPrimeiroColocado;
	}

	public void setRespostaPrimeiroColocado(String respostaPrimeiroColocado) {
		this.respostaPrimeiroColocado = respostaPrimeiroColocado;
	}
	
}
