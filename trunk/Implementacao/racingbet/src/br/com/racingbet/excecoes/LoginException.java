package br.com.racingbet.excecoes;

public class LoginException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LoginException() {
		super("Email ou senha incorretos!");
	}

}
