package application;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import entities.Produto;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(new Locale.Builder().setLanguage("pt").setRegion("BR").build());
		Scanner sc = new Scanner(System.in);
		ArrayList<Produto> produtos = new ArrayList<>();
		int opcao = 0;
		int contadorId = 1;

		do {
			System.out.println("\n=== CRUD Produtos ===");
			System.out.println("1 - Adicionar produto");
			System.out.println("2 - Listar produtos");
			System.out.println("3 - Atualizar produto");
			System.out.println("4 - Deletar produto");
			System.out.println("0 - Sair");
			opcao = lerOpcao(sc);

			switch (opcao) {
			case 1:
				System.out.print("Nome: ");
				String nome = sc.nextLine();
				BigDecimal preco = BigDecimal.ZERO;
				while (true) {
					System.out.print("Preço: ");
					preco = lerBigDecimal(sc);
					if (preco.compareTo(BigDecimal.ZERO) > 0)
						break;
					System.out.println("Preço inválido! Deve ser maior que 0.");
				}
				try {
					produtos.add(new Produto(contadorId++, nome, preco));
					System.out.println("Produto adicionado!");
				} catch (IllegalArgumentException e) {
					System.out.println("Erro: " + e.getMessage());
				}
				break;

			case 2:
				if (produtos.isEmpty()) {
					System.out.println("Nenhum produto cadastrado.");
				} else {
					NumberFormat formatadorMoeda = NumberFormat.getCurrencyInstance();

					System.out.println("ID  | Nome             | Preço ");
					System.out.println("-------------------------------------------");
					for (Produto p : produtos) {
						String precoFormatado = formatadorMoeda.format(p.getPreco());
						System.out.printf(" %-2d | %-16s | %s%n", p.getId(), p.getNome(), precoFormatado);
					}
				}
				break;

			case 3:
				System.out.print("ID do produto para atualizar: ");
				int idUpdate = sc.nextInt();
				sc.nextLine();

				produtos.stream().filter(p -> p.getId() == idUpdate).findFirst().ifPresentOrElse(prodUpdate -> {
					System.out.print("Novo nome: ");
					prodUpdate.setNome(sc.nextLine());

					BigDecimal novoPreco = BigDecimal.ZERO;
					while (true) {
						System.out.print("Novo preço: ");
						try {
							novoPreco = sc.nextBigDecimal();
							sc.nextLine();
							if (novoPreco.compareTo(BigDecimal.ZERO) > 0)
								break;
							System.out.println("Preço inválido! Deve ser maior que 0.");
						} catch (java.util.InputMismatchException e) {
							System.out.println("Formato de preço inválido. Por favor, digite um número.");
							sc.nextLine();
						}
					}
					prodUpdate.setPreco(novoPreco);
					System.out.println("Produto atualizado!");

				}, () -> {
					System.out.println("Produto não encontrado!");
				});
				break;

			case 4:
				System.out.print("ID do produto para deletar: ");
				int idDelete = sc.nextInt();
				sc.nextLine();
				produtos.removeIf(p -> p.getId() == idDelete);
				System.out.println("Produto deletado, se existia.");
				break;

			case 0:
				System.out.println("Saindo...");
				break;

			default:
				System.out.println("Opção inválida!");
			}

		} while (opcao != 0);

		sc.close();
	}

	public static BigDecimal lerBigDecimal(Scanner sc) {
		while (true) {
			try {
				return sc.nextBigDecimal();
			} catch (java.util.InputMismatchException e) {
				System.out.println("Formato inválido. Digite um número.");
				sc.nextLine(); // Consome a linha inválida
			}
		}
	}

	public static int lerOpcao(Scanner sc) {
		while (true) {
			try {
				System.out.print("Escolha uma opção: ");
				int opcao = sc.nextInt();
				sc.nextLine();
				return opcao;
			} catch (java.util.InputMismatchException e) {
				System.out.println("Opção inválida! Por favor, digite um número.");
				sc.nextLine();
			}
		}
	}
}