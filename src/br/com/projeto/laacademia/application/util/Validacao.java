package br.com.projeto.laacademia.application.util;

public class Validacao {

	public static void confirmarNaoVazio(Object o) {

		if (o instanceof String) {

			String s = (String) o;
			if (StringUtils.estaVazia(s)) {

				throw new ExcecaoValidacao("O texto não pode ser nulo!");
			}
		}

		if (o == null) {

			throw new ExcecaoValidacao("O valor não pode ser nulo!");
		}
	}

}
