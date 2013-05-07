package br.com.racingbet.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private String respostaPrimeiro;

	@Column(nullable = false, length = 50)
	private String respostaPole;

	@Column(nullable = false, length = 50)
	private String autorPergunta;
	
	@Column(nullable = false, length = 50)
	private String grandePremio;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date data;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRespostaPrimeiro() {
		return respostaPrimeiro;
	}

	public void setRespostaPrimeiro(String respostaPrimeiro) {
		this.respostaPrimeiro = respostaPrimeiro;
	}

	public String getRespostaPole() {
		return respostaPole;
	}

	public void setRespostaPole(String respostaPole) {
		this.respostaPole = respostaPole;
	}

	public String getAutorPergunta() {
		return autorPergunta;
	}

	public void setAutorPergunta(String autorPergunta) {
		this.autorPergunta = autorPergunta;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getGrandePremio() {
		return grandePremio;
	}

	public void setGrandePremio(String grandePremio) {
		this.grandePremio = grandePremio;
	}

}
