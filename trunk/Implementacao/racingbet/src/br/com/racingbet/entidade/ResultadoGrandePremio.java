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
	
	@Column
	private Long idGrandePremio;
	
	@Column
	private Long idCategoria;
	
	public Long getIdCategoria() {
		return idCategoria;
	}


	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}


	public Long getIdGrandePremio() {
		return idGrandePremio;
	}
	
	
	public void setIdGrandePremio(Long idGrandePremio) {
		this.idGrandePremio = idGrandePremio;
	}

	public Long getIdPilotoPergunta1() {
		return idPilotoPergunta1;
	}

	public void setIdPilotoPergunta1(Long idPilotoPergunta1) {
		this.idPilotoPergunta1 = idPilotoPergunta1;
	}

	public Long getIdPilotoPergunta2() {
		return idPilotoPergunta2;
	}

	public void setIdPilotoPergunta2(Long idPilotoPergunta2) {
		this.idPilotoPergunta2 = idPilotoPergunta2;
	}

	@Column
	private Long idPilotoPergunta1;
	
	@Column
	private Long idPilotoPergunta2;

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
