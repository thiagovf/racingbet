package br.com.racingbet.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class ResultadoGrandePremio implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_ResultadoGrandePremio")
	private Long id;

    @OneToOne(optional=false)
    @JoinColumn(name="idPilotoPerguntaPole", nullable=false, insertable=true, updatable=true)
	private Piloto repostaPerguntaPole;
	
    @OneToOne(optional=false)
    @JoinColumn(name="idPilotoPerguntaPrimeiro", nullable=false, insertable=true, updatable=true)
	private Piloto repostaPerguntaPrimeiro;
	
	@OneToOne(optional=false)
	@JoinColumn(name="idGrandePremio",  nullable=false, insertable=true, updatable=true)
	private GrandePremio grandePremio;	
	
	public GrandePremio getGrandePremio() {
		return grandePremio;
	}

	public void setGrandePremio(GrandePremio grandePremio) {
		this.grandePremio = grandePremio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Piloto getRepostaPerguntaPole() {
		return repostaPerguntaPole;
	}

	public void setRepostaPerguntaPole(Piloto repostaPerguntaPole) {
		this.repostaPerguntaPole = repostaPerguntaPole;
	}

	public Piloto getRepostaPerguntaPrimeiro() {
		return repostaPerguntaPrimeiro;
	}

	public void setRepostaPerguntaPrimeiro(Piloto repostaPerguntaPrimeiro) {
		this.repostaPerguntaPrimeiro = repostaPerguntaPrimeiro;
	}



}
