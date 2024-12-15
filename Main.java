package apresentacao;

import negocio.Banco;
import negocio.Conta;
import acesso_a_dado.ContaDAO;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Banco banco = new Banco();
        ContaDAO contaDAO = new ContaDAO();

        while (true) {
            System.out.println("\nMenu Principal");
            System.out.println("1. Criar Conta");
            System.out.println("2. Alterar Saldo");
            System.out.println("3. Listar Contas");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir newline

            switch (opcao) {
                case 1 -> {
                    System.out.print("Número da conta: ");
                    String numero = scanner.nextLine();
                    System.out.print("Saldo inicial: ");
                    double saldo = scanner.nextDouble();
                    scanner.nextLine(); // Consumir newline

                    Conta conta = new Conta(numero, saldo);
                    if (contaDAO.criarConta(conta)) {
                        banco.adicionarConta(conta);
                        System.out.println("Conta criada com sucesso!");
                    } else {
                        System.out.println("Erro ao criar conta no banco de dados.");
                    }
                }
                case 2 -> {
                    System.out.print("Número da conta: ");
                    String numero = scanner.nextLine();
                    System.out.print("Novo saldo: ");
                    double saldo = scanner.nextDouble();
                    scanner.nextLine(); // Consumir newline

                    if (contaDAO.atualizarSaldo(numero, saldo)) {
                        banco.atualizarSaldo(numero, saldo);
                        System.out.println("Saldo atualizado com sucesso!");
                    } else {
                        System.out.println("Erro ao atualizar saldo no banco de dados.");
                    }
                }
                case 3 -> banco.listarContas();
                case 4 -> {
                    System.out.println("Saindo...");
                    return;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }
}
