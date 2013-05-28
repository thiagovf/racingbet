package br.com.racingbet.entidade;

import br.com.racingbet.entidade.GrandePremio;


public class ConsultarGrandePremio {
	
	public String recuperaGP(GrandePremio grandePremio) {
		return grandePremio.getNome();
	}
}