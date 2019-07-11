package br.com.projeto.laacademia.application.util;

import javax.ejb.ApplicationException;

@SuppressWarnings("serial")
@ApplicationException
public class ExcecaoValidacao extends RuntimeException {

	public ExcecaoValidacao() {
		//Auto-generated constructor stub
	}

	public ExcecaoValidacao(String message) {
		super(message);
		//Auto-generated constructor stub
	}

	public ExcecaoValidacao(Throwable cause) {
		super(cause);
		//Auto-generated constructor stub
	}

	public ExcecaoValidacao(String message, Throwable cause) {
		super(message, cause);
		//Auto-generated constructor stub
	}

	public ExcecaoValidacao(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		//Auto-generated constructor stub
	}

}
