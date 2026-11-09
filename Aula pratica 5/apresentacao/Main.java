import java.util.List;
import java.util.Scanner;

import dados.Contato;
import negocio.ListaTelefonica;

public class Main {

    public static void main(String[] args) {

        ListaTelefonica listatelefonica = new ListaTelefonica();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1: Novo contato");
            System.out.println("2: Remover contato");
            System.out.println("3: Exibir contatos");
            System.out.println("4: Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("\nDigite o nome do contato: ");
                    String nomeContato = scanner.nextLine();
                    System.out.println("\nDigite o Telefone do contato: ");
                    int telefoneContato = scanner.nextInt();
                    Contato contato = new Contato(nomeContato, telefoneContato);

                    listatelefonica.adicionarContato(contato);
                    System.out.println("\nContato adicionado com sucesso!");
                    break;

                case 2:
                    System.out.println("\nDigite a inicial do contato que será removido: ");
                    String inicial = scanner.nextLine();
                    List<Contato> contatosIniciais = listatelefonica.buscarContatos(inicial);

                    if (!contatosIniciais.isEmpty()) {
                        Contato contatoExistente = contatosIniciais.get(0);
                        listatelefonica.removerContato(contatoExistente);
                        System.out.println("\nContato removido com sucesso!");

                    } else {
                        System.out.println("\nContato não encontrado!");
                    }
                    break;

                case 3:
                    List<Contato> todosContatos = listatelefonica.buscarContatos("");

                    if (!todosContatos.isEmpty()) {
                        System.out.println("\nLista dos contatos: ");

                        for (Contato c : todosContatos) {
                            System.out.println("\n");
                            System.out.println("Nome: " + c.getNome());
                            System.out.println("Telefone: " + c.getTelefone());
                        }
                    } else {
                        System.out.println("\nNenhum contato encontrado.");
                    }
                    break;

                case 4:
                    System.out.println("\nPrograma encerrando. . .");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("\nOpção inválida. Tente novamente");

            }
        }
    }
}
