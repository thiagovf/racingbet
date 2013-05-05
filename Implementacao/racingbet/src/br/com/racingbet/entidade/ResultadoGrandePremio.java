package br.com.racingbet.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ResultadoGrandePremio implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_resultadograndepremio")
	private Long id;
	
	@Column(nullable=false, length=50)
	private String nomeGrandePremio;

	@Column(nullable=false, length=50)
	private String repostaPerguntaPole;
	
	@Column(nullable=false, length=50)
	private String repostaPerguntaPrimeiro;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeGrandePremio() {
		return nomeGrandePremio;
	}

	public void setNomeGrandePremio(String nomeGrandePremio) {
		this.nomeGrandePremio = nomeGrandePremio;
	}

	public String getRepostaPerguntaPole() {
		return repostaPerguntaPole;
	}

	public void setRepostaPerguntaPole(String repostaPerguntaPole) {
		this.repostaPerguntaPole = repostaPerguntaPole;
	}

	public String getRepostaPerguntaPrimeiro() {
		return repostaPerguntaPrimeiro;
	}

	public void setRepostaPerguntaPrimeiro(String repostaPerguntaPrimeiro) {
		this.repostaPerguntaPrimeiro = repostaPerguntaPrimeiro;
	}

}
