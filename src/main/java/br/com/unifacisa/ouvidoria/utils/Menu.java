package br.com.unifacisa.ouvidoria.utils;

public class Menu {
	public static String menuUser() {

		String menu = "1 | Adicionar um novo Feedback\n"
					+ "2 | Consultar Feedbacks\n"
					+ "3 | Sair";

		return menu;

	}

	public static String menuAdmin() {

		String menu = "1 | Consultar Feedbacks por Email\n"
					+ "2 | Consultar Feedbacks totais\n"
					+ "3 | Resolver Solicitação / Deletar\n"
					+ "4 | Deletar um usuário\n"
					+ "5 | Sair";

		return menu;

	}

}
