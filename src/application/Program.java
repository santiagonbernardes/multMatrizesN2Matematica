package application;
import javax.swing.JOptionPane;

public class Program {

	public static void main(String[] args) {

		// Declaração de variáveis
		int[][] p = new int[3][3]; // Este vetor armazena a quantidade de porções.
		double[][] c = new double[3][1]; // Este vetor armazena o preço de cada porção.
		/*
		 * A regra matemática define que em uma multiplicação de matrizes A * B só
		 * haverá uma matriz produto se o número de colunas de A for igual ao número de
		 * linhas de B.
		 * 
		 * Neste caso, a matriz p assume o papel de A(3 colunas) e a matriz c assume o
		 * papel de B (3 linhas).
		 * 
		 * A matriz produto receberá os resultados da multiplicação p * a;
		 * 
		 * A matriz produto terá o mesmo número de linhas de A (3, neste caso), e o
		 * número de colunas de B (1).
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
				do {
					opcao = JOptionPane.showInputDialog("Bem vindo, usuário. Selecione uma das opções abaixo:\n"
							+ "1 - Mostrar cardápio atual.\n" + "2 - Alterar o preço das porções.\n"
							+ "3 - Alterar a composição de um prato.\n" + "4 - Carregar cardápio padrão.\n"
							+ "5 - Fazer pedido.\n" + "6 - Encerrar o sistema.");
					if (!valideMenu(opcao)) {
						JOptionPane.showMessageDialog(null,
								"A opção informada não é válida. Tente novamente de acordo com as opções apresentadas.");
					}
				} while (!valideMenu(opcao) || opcao == null);
				opcao1Int = Character.getNumericValue(opcao.charAt(0));
				if (opcao1Int < 1 || opcao1Int > 6) {
					JOptionPane.showMessageDialog(null, "Opção inválida. Informe um número entre 1 e 6.");
				}
			} while (opcao1Int < 1 || opcao1Int > 6);

			switch (opcao1Int) {

			// Mostrar cardápio atual.
			case 1:
				JOptionPane.showMessageDialog(null, Program.mostreMatrizP(p, c, produto));
				break;

			// Alterar o preço das porções.
			case 2:
				do {
					do {
						opcao = JOptionPane
								.showInputDialog("Selecione a porção a ser alterada:\n" + Program.mostreMatrizC(c));
						if (!valideMenu(opcao)) {
							JOptionPane.showMessageDialog(null,
									"A opção informada não é válida. Tente novamente de acordo com as opções apresentadas.");
						}
					} while (!valideMenu(opcao));
					opcao1Int = Character.getNumericValue(opcao.charAt(0));
					if (opcao1Int < 1 || opcao1Int > 3) {
						JOptionPane.showMessageDialog(null, "Opção inválida. Informe um número entre 1 e 3.");
					} else {
						Program.alterePorcao(opcao1Int, c);
						produto = multipliqueMatrizes(p, c);
						JOptionPane.showMessageDialog(null, "Porções atualizadas com sucesso!\n"+Program.mostreMatrizC(c));
					}
				} while (opcao1Int < 1 || opcao1Int > 3);
				break;

			// Alterar a composição de um prato.
			case 3:
				do {
					do {
						opcao = JOptionPane.showInputDialog(
								"Escolha o prato a ser alterado:\n" + Program.mostreMatrizP(p, c, produto));
						if (!valideMenu(opcao)) {
							JOptionPane.showMessageDialog(null,
									"A opção informada não é válida. Tente novamente de acordo com as opções apresentadas.");
						}
					} while (!valideMenu(opcao));
					opcao1Int = Character.getNumericValue(opcao.charAt(0));
					if (opcao1Int < 1 || opcao1Int > 3) {
						JOptionPane.showMessageDialog(null, "Opção inválida. Informe um número entre 1 e 3.");
					}
				} while (opcao1Int < 1 || opcao1Int > 3);

				do {
					do {
						opcao = JOptionPane.showInputDialog(
								"Escolha a porção a ser alterada:\n" + Program.mostrePorcoesPrato(opcao1Int, p, c));
						if (!valideMenu(opcao)) {
							JOptionPane.showMessageDialog(null,
									"A opção informada não é válida. Tente novamente de acordo com as opções apresentadas.");
						}
					} while (!valideMenu(opcao));

					opcao2Int = Character.getNumericValue(opcao.charAt(0));
					if (opcao2Int < 1 || opcao2Int > 3) {
						JOptionPane.showMessageDialog(null, "Opção inválida. Informe um número entre 1 e 3.");
					} else {
						Program.alterePrato(opcao1Int, opcao2Int, p);
						JOptionPane.showMessageDialog(null,
								"Nova composição do prato:\n" + Program.mostrePorcoesPrato(opcao1Int, p, c));
					}
				} while (opcao2Int < 1 || opcao2Int > 3);

				produto = multipliqueMatrizes(p, c);
				break;

			// Carregar cardápio padrão.
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
				JOptionPane.showMessageDialog(null, "Acho que é nota 10 hein, fera? Tchau!");
				sistemaAtivo = false;
				break;
			}

		}

	}

	// Métodos

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
		int cont = 0;
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
					break;
				}
				if (j == 2) {

					sb.append(String.format("%.2f", produto[cont][0]) + ".\n");
					cont++;
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
		int qtd;
		String str;
		String entrada;

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
		str = "Porção de " + nome + " - Quantidade atual: " + p[linha][coluna] + "\n";

		do {
			do {
				entrada = JOptionPane.showInputDialog(str + "Informe a nova quantidade de " + nome + ":");
				if (!valideAlteracoes(entrada)) {
					JOptionPane.showMessageDialog(null, "A quantidade é inválida. Tente novamente.");
				}
			} while (!valideAlteracoes(entrada));
			qtd = Integer.parseInt(entrada);
			if (qtd < 0) {
				JOptionPane.showMessageDialog(null, "A quantidade não pode ser menor que 0. Tente novamente.");
			} else {
				p[linha][coluna] = qtd;
			}
		} while (qtd < 0);
	}

	public static void alterePorcao(int opcaoPorcao, double[][] c) {
		int linha = opcaoPorcao - 1;
		String nome = null;
		double preco = 0;
		String str;
		String entrada;

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

		str = "Porção de " + nome + " - Preço atual: R$" + String.format("%.2f", c[linha][0]) + "\n";
		do {
			do {
				entrada = JOptionPane.showInputDialog(str + "Informe o novo preço da porção de " + nome + ":");
				if (!validePreco(entrada)) {
					JOptionPane.showMessageDialog(null,
							"O preço informado é inválido ou está separado por vírgula (use ponto caso o preço tenha centavos). Tente novamente.");
				}
			} while (!validePreco(entrada));
			preco = Double.parseDouble(entrada);
			if (preco <= 0) {
				JOptionPane.showMessageDialog(null, "O preço informado é inválido. Entre com um preço superior a 0.");
			} else {
				c[linha][0] = preco;
			}
		} while (preco <= 0);

	}

	public static boolean valideMenu(String str) {
		if (str.equals("")) {
			return false;
		} else if (str.length() > 1) {
			return false;
		} else if (!Character.isDigit(str.charAt(0))) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean valideAlteracoes(String str) {
		if (str.equals("")) {
			return false;
		} else {
			for (int i = 0; i < str.length(); i++) {
				if (!Character.isDigit(str.charAt(i))) {
					return false;
				}
			}
		}
		
		return true;
	}

	public static boolean validePreco(String str) {
		if (str.equals("")) {
			return false;
		} else {
			for (int i = 0; i < str.length(); i++) {
				if (!Character.isDigit(str.charAt(i))) {
					if (str.charAt(i) != '.') {
						return false;
					}
				}
			}
		}
		
		return true;

	}

	public static String facaPedido(int[][] p, double[][] c, double[][] produto) {
		StringBuilder sb = new StringBuilder();
		double total = 0;
		String entrada;
		int opcao = 0;
		boolean finalizarPedido = false;

		sb.append("                                                       Comanda\n\n");

		do {
			do {
				do {
					entrada = JOptionPane.showInputDialog(
							"Informe qual prato deseja adicionar ao pedido:\n" + mostreMatrizP(p, c, produto));
					if (!valideMenu(entrada)) {
						JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
					} else {
						opcao = Integer.parseInt(entrada);
					}
				} while (!valideMenu(entrada));
				if (opcao < 1 || opcao > 3) {
					JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
				}
			} while (opcao < 1 || opcao > 3);
			sb.append("Prato " + opcao + ": ");
			for (int i = 0; i < p.length; i++) {
				switch (i) {
				case 0:
					sb.append(p[opcao - 1][i] + "x Arroz(R$" + String.format("%.2f", c[i][0]) + ") | ");
					break;
				case 1:
					sb.append(p[opcao - 1][i] + "x Carne(R$" + String.format("%.2f", c[i][0]) + ") | ");
					break;
				case 2:
					sb.append(p[opcao - 1][i] + "x Salada(R$" + String.format("%.2f", c[i][0]) + ") = R$"
							+ String.format("%.2f", produto[opcao - 1][0]) + "\n");
					total += produto[opcao - 1][0];
					break;
				}
			}
			do {
				do {
					entrada = JOptionPane.showInputDialog("O que deseja fazer?\n"
							+ "1 - Adicionar mais itens ao pedido.\n" + "2 - Finalizar o pedido.");
					if (!valideMenu(entrada)) {
						JOptionPane.showMessageDialog(null, "Opção inválida.");
					} else {
						opcao = Integer.parseInt(entrada);
						if (opcao < 1 || opcao > 2) {
							JOptionPane.showMessageDialog(null, "Opção inválida.");
						}
					}
				} while (!valideMenu(entrada));
			} while (opcao < 1 || opcao > 2);
			if (opcao == 2) {
				sb.append("\n\n\nTotal: R$" + String.format("%.2f", total));
				finalizarPedido = true;
			}

		} while (!finalizarPedido);

		return sb.toString();
	}
}
