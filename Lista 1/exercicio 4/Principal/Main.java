package Principal;

import Dados.Pessoa;
import Dados.Sorteador;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o número de pessoas para o sorteio: ");
        int n = scanner.nextInt();

        List<Pessoa> listaPessoas = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.print("Digite o nome da pessoa " + (i + 1) + ": ");
            String nome = scanner.next();
            Pessoa pessoa = new Pessoa(nome);
            listaPessoas.add(pessoa);
        }

        Sorteador sorteador = new Sorteador(listaPessoas);

        System.out.println("Realizando sorteios:");
        for (int i = 0; i < n; i++) {
            Pessoa pessoaSorteada = sorteador.sortearProximo();
            if (pessoaSorteada != null) {
                System.out.println("Sorteio #" + (i + 1) + ": Pessoa sorteada: " + pessoaSorteada.getNome());
            } else {
                System.out.println("Sorteio #" + (i + 1) + ": Não há mais pessoas para sortear.");
            }
        }
    }
}
