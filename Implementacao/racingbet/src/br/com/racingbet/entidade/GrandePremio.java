package br.com.racingbet.entidade;

import java.io.Serializable;
import java.util.Date;

public class GrandePremio implements Serializable {
	
	private String nome;
	
	private Date data;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

}
