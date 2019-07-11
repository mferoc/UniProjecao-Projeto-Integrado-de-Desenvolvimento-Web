package br.com.projeto.laacademia.application.util;

public class StringUtils {

	// metodo para verificar se uma string esta vazia
	public static boolean estaVazia(String s) {

		if (s == null) {

			return true;
		}

		return s.trim().length() == 0;
	}

	public static String zerosEsquerda(int valor, int tamanhoFinal) {

		return String.format("%0" + tamanhoFinal + "d", valor);
	}

	// public static void main(String[] args) {

	// String str = " a ";

	// boolean b = StringUtils.estaVazia(str);
	// System.out.println(b);

	// int v = 100;
	// System.out.println(StringUtils.zerosEsquerda(v, 8));
	// }
}
