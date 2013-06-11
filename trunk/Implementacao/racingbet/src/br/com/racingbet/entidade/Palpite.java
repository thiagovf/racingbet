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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Palpite implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_palpite")
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

	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date data;

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

	public GrandePremio getGrandePremio() {
		return grandePremio;
	}

	public void setGrandePremio(GrandePremio grandePremio) {
		this.grandePremio = grandePremio;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

		
}
