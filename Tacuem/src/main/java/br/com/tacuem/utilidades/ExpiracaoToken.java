package br.com.tacuem.utilidades;

import java.util.Calendar;
import java.util.Date;

public class ExpiracaoToken {

	private static final int EXPIRATION_TIME = 30;

	public static Date tempoExpiracao() {

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(new Date().getTime());
		calendar.add(Calendar.MINUTE, EXPIRATION_TIME);
		return new Date(calendar.getTime().getTime());
	}

}
