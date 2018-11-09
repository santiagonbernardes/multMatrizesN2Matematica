package application;

import javax.swing.JOptionPane;

public class Program {

	public static void main(String[] args) {

		// Declara��o de vari�veis
		int[][] p = new int[3][3]; // Este vetor armazena a quantidade de por��es.
		double[][] c = new double[3][1]; // Este vetor armazena o pre�o de cada por��o.
		/*
		 * A regra matem�tica define que em uma multiplica��o de matrizes A * B s�
		 * haver� uma matriz produto se o n�mero de colunas de A for igual ao n�mero de
		 * linhas de B.
		 * 
		 * Neste caso, a matriz p assume o papel de A(3 colunas) e a matriz c assume o
		 * papel de B (3 linhas).
		 * 
		 * A matriz produto receber� os resultados da multiplica��o p * a;
		 * 
		 * A matriz produto ter� o mesmo n�mero de linhas de A (3, neste caso), e o
		 * n�mero de colunas de B (1).
		 */
		double[][] produto = new double[3][1];
		boolean sistemaAtivo = true;
		String opcao = null;
		int opcao1Int = 0, opcao2Int = 0;
		p = carregueMatrizPPadrao();
		c = carregueMatrizCPadrao();
		produto = multipliqueMatrizes(p, c);

		while (sistemaAtivo) {
			do {
				opcao = JOptionPane.showInputDialog("Bem vindo, usu�rio. Selecione uma das op��es abaixo:\n"
						+ "1 - Mostrar card�pio atual.\n" + "2 - Alterar o pre�o das por��es.\n"
						+ "3 - Alterar a composi��o de um prato.\n" + "4 - Carregar card�pio padr�o.\n"
						+ "5 - Fazer pedido.\n" + "6 - Encerrar o sistema.");
				if (!opcao.matches("^[1-6]{1}$")) {
					JOptionPane.showMessageDialog(null,
							"Op��o inv�lida. Tente novamente informando uma op��o entre 1 e 6.");
				}
			} while (!opcao.matches("^[1-6]{1}$"));

			switch (Integer.parseInt(opcao)) {

			// Mostrar card�pio atual.
			case 1:
				JOptionPane.showMessageDialog(null, Program.mostreMatrizP(p, c, produto));
				break;

			// Alterar o pre�o das por��es.
			case 2:
				do {
					opcao = JOptionPane
							.showInputDialog("Selecione a por��o a ser alterada:\n" + Program.mostreMatrizC(c));
					if (!opcao.matches("^[1-3]{1}$")) {
						JOptionPane.showMessageDialog(null,
								"Op��o inv�lida. Tente novamente informando uma op��o entre 1 e 3.");
					}
				} while (!opcao.matches("^[1-3]{1}$"));

				Program.alterePorcao(Integer.parseInt(opcao), c);
				produto = multipliqueMatrizes(p, c);
				JOptionPane.showMessageDialog(null, "Por��es atualizadas com sucesso!\n" + Program.mostreMatrizC(c));

				break;

			// Alterar a composi��o de um prato.
			case 3:
				do {
					opcao = JOptionPane.showInputDialog(
							"Escolha o prato a ser alterado:\n" + Program.mostreMatrizP(p, c, produto));
					if (!opcao.matches("^[1-3]{1}$")) {
						JOptionPane.showMessageDialog(null,
								"Op��o inv�lida. Tente novamente informando uma op��o entre 1 e 3.");
					}
				} while (!opcao.matches("^[1-3]{1}$"));

				opcao1Int = Integer.parseInt(opcao);

				do {
					opcao = JOptionPane.showInputDialog(
							"Escolha a por��o a ser alterada:\n" + Program.mostrePorcoesPrato(opcao1Int, p, c));
					if (!opcao.matches("^[1-3]{1}$")) {
						JOptionPane.showMessageDialog(null,
								"Op��o inv�lida. Tente novamente informando uma op��o entre 1 e 3.");
					}

				} while (!opcao.matches("^[1-3]{1}$"));

				opcao2Int = Integer.parseInt(opcao);

				Program.alterePrato(opcao1Int, opcao2Int, p);
				JOptionPane.showMessageDialog(null,
						"Nova composi��o do prato:\n" + Program.mostrePorcoesPrato(opcao1Int, p, c));

				produto = multipliqueMatrizes(p, c);
				break;

			// Carregar card�pio padr�o.
			case 4:
				p = carregueMatrizPPadrao();
				c = carregueMatrizCPadrao();
				produto = multipliqueMatrizes(p, c);
				break;

			// Fazer pedido
			case 5:
				JOptionPane.showMessageDialog(null, Program.facaPedido(p, c, produto));
				break;

			// Encerrar
			case 6:
				JOptionPane.showMessageDialog(null, "Acho que � nota 10 hein, fera? Tchau!");
				sistemaAtivo = false;
				break;
			}

		}

	}

	// M�todos

	public static double[][] multipliqueMatrizes(int[][] p, double[][] c) {
		double[][] produto = new double[3][1];
		for (int linha = 0; linha < p.length; linha++) {
			for (int coluna = 0; coluna < p[linha].length; coluna++) {
				produto[linha][0] += p[linha][coluna] * c[coluna][0];
			}
		}
		return produto;
	}

	public static int[][] carregueMatrizPPadrao() {
		int[][] p = { { 2, 1, 1 }, { 1, 2, 1 }, { 2, 2, 0 } };
		return p;
	}

	public static double[][] carregueMatrizCPadrao() {
		double[][] c = { { 1 }, { 3 }, { 2 } };
		return c;
	}

	public static String mostreMatrizC(double[][] c) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < c.length; i++) {
			for (int j = 0; j < c[i].length; j++) {
				switch (i) {
				case 0:
					sb.append((i + 1) + " - Arroz: R$" + String.format("%.2f", c[i][j]) + "\n");
					break;
				case 1:
					sb.append((i + 1) + " - Carne: R$" + String.format("%.2f", c[i][j]) + "\n");
					break;
				case 2:
					sb.append((i + 1) + " - Salada: R$" + String.format("%.2f", c[i][j]) + "\n");
					break;
				}
			}
		}

		return sb.toString();
	}

	public static String mostreMatrizP(int[][] p, double[][] c, double[][] produto) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < p.length; i++) {
			sb.append((i + 1) + " - Prato " + (i + 1) + ": ");
			for (int j = 0; j < p[i].length; j++) {
				switch (j) {
				case 0:
					sb.append(p[i][j] + "x Arroz(R$" + String.format("%.2f", c[j][0]) + ") | ");
					break;
				case 1:
					sb.append(p[i][j] + "x Carne(R$" + String.format("%.2f", c[j][0]) + ") | ");
					break;
				case 2:
					sb.append(p[i][j] + "x Salada(R$" + String.format("%.2f", c[j][0]) + ") = R$");
					sb.append(String.format("%.2f", produto[i][0]) + ".\n");
					break;
				}
			}
		}
		return sb.toString();
	}

	public static String mostrePorcoesPrato(int opcaoPrato, int p[][], double[][] c) {
		StringBuilder sb = new StringBuilder();
		int index = opcaoPrato - 1;

		sb.append("Prato " + opcaoPrato + ": \n");

		for (int i = 0; i < p[index].length; i++) {
			switch (i) {
			case 0:
				sb.append((i + 1) + " - " + p[index][i] + "x Arroz: R$" + String.format("%.2f", c[i][0]) + "\n");
				break;
			case 1:
				sb.append((i + 1) + " - " + p[index][i] + "x Carne: R$" + String.format("%.2f", c[i][0]) + "\n");
				break;
			case 2:
				sb.append((i + 1) + " - " + p[index][i] + "x Salada: R$" + String.format("%.2f", c[i][0]) + "\n");
				break;
			}

		}
		return sb.toString();
	}

	public static void alterePrato(int opcaoPrato, int opcaoPorcao, int[][] p) {
		int linha = opcaoPrato - 1;
		int coluna = opcaoPorcao - 1;
		String nome = null;
		String opcao;

		switch (coluna) {
		case 0:
			nome = "arroz";
			break;
		case 1:
			nome = "carne";
			break;
		case 2:
			nome = "salada";
			break;
		}
		do {
			opcao = JOptionPane.showInputDialog("Por��o de " + nome + " - Quantidade atual: " + p[linha][coluna] + "\n"
					+ "Informe a nova quantidade de " + nome + ":");
			if (!opcao.matches("\\d+")) {
				JOptionPane.showMessageDialog(null, "A quantidade � inv�lida. Tente novamente.");
			}
		} while (!opcao.matches("\\d+"));
		p[linha][coluna] = Integer.parseInt(opcao);
	}

	public static void alterePorcao(int opcaoPorcao, double[][] c) {
		int linha = opcaoPorcao - 1;
		String nome = null;
		String opcao;
		switch (linha) {
		case 0:
			nome = "arroz";
			break;
		case 1:
			nome = "carne";
			break;
		case 2:
			nome = "salada";
			break;
		}

		do {
			opcao = JOptionPane.showInputDialog("Por��o de " + nome + " - Pre�o atual: R$"
					+ String.format("%.2f", c[linha][0]) + "\n" + "Informe o novo pre�o da por��o de " + nome + ":");
			if (!validePreco(opcao)) {
				JOptionPane.showMessageDialog(null,
						"O pre�o informado � inv�lido. Tente novamente informando um pre�o composto apenas por n�meros e com no m�ximo 2 d�gitos ap�s a v�rgula/ponto.");
			}
		} while (!validePreco(opcao));

		c[linha][0] = Double.parseDouble(opcao.replace(",", "."));
	}

	public static boolean validePreco(String str) {
		if (!str.matches("^(\\d+)(\\.|,)?(\\d){0,2}$")) {
			return false;
		} else if (Double.parseDouble(str.replace(",", ".")) <= 0) {
			return false;
		}

		return true;

	}

	public static String facaPedido(int[][] p, double[][] c, double[][] produto) {
		StringBuilder sb = new StringBuilder();
		double total = 0;
		String opcao;
		int index;
		boolean finalizarPedido = false;

		sb.append("                                                       Comanda\n\n");

		while (!finalizarPedido) {
			do {
				opcao = JOptionPane.showInputDialog(
						"Informe qual prato deseja adicionar ao pedido:\n" + mostreMatrizP(p, c, produto));
				if (!opcao.matches("[1-3]{1}")) {
					JOptionPane.showMessageDialog(null, "Op��o inv�lida. Tente novamente.");
				}
			} while (!opcao.matches("[1-3]{1}"));
			index = Integer.parseInt(opcao) - 1;

			sb.append("Prato " + index + ": ");
			for (int i = 0; i < p.length; i++) {
				switch (i) {
				case 0:
					sb.append(p[index][i] + "x Arroz(R$" + String.format("%.2f", c[i][0]) + ") | ");
					break;
				case 1:
					sb.append(p[index][i] + "x Carne(R$" + String.format("%.2f", c[i][0]) + ") | ");
					break;
				case 2:
					sb.append(p[index][i] + "x Salada(R$" + String.format("%.2f", c[i][0]) + ") = R$"
							+ String.format("%.2f", produto[index][0]) + "\n");
					total += produto[index][0];
					break;
				}
			}
			do {
				opcao = JOptionPane.showInputDialog(
						"O que deseja fazer?\n" + "1 - Adicionar mais itens ao pedido.\n" + "2 - Finalizar o pedido.");
				if (!opcao.matches("1|2{1}")) {
					JOptionPane.showMessageDialog(null, "Op��o inv�lida. Tente novamente.");
				}
			} while (!opcao.matches("^1|2{1}$"));

			if (Integer.parseInt(opcao) == 2) {
				sb.append("\n\n\nTotal: R$" + String.format("%.2f", total));
				finalizarPedido = true;
			}
		}
		return sb.toString();
	}
}
