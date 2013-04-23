package br.com.racingbet.util;

import javax.enterprise.context.Conversation;

public class ConversacaoUtil {

	public static void iniciar(Conversation conversacao) {
		if (conversacao.isTransient()) {
			conversacao.begin();
		}
	}
	
	public static void terminar(Conversation conversacao) {
		if (!conversacao.isTransient()) {
			conversacao.end();
		}
	}
}
