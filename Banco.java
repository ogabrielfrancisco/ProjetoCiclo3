package negocio;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    private final List<Conta> contas;

    public Banco() {
        this.contas = new ArrayList<>();
    }

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public void atualizarSaldo(String numero, double saldo) {
        for (Conta conta : contas) {
            if (conta.getNumero().equals(numero)) {
                conta.setSaldo(saldo);
                break;
            }
        }
    }

    public void listarContas() {
        System.out.println("\nContas cadastradas:");
        for (Conta conta : contas) {
            System.out.println("Número: " + conta.getNumero() + ", Saldo: " + conta.getSaldo());
        }
    }
}
