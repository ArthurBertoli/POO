package Principal;

import java.util.Scanner;
import Dados.Imovel;
import Dados.Imobiliaria;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Imobiliaria imobiliaria = new Imobiliaria(" * Imobiliária Kokhai * ");

        System.out.println("Bem-vindo à " + imobiliaria.getNome());

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("\n1. Registrar imóvel");
            System.out.println("\n2. Listar imóveis");
            System.out.println("\n3. Sair");
            System.out.print("\nEscolha uma opção: \n");
            int opcao = scanner.nextInt();

            if (opcao == 1) {
                System.out.print("\nInforme a largura do imóvel em metros: ");
                float largura = scanner.nextFloat();
                System.out.print("\nInforme o comprimento do imóvel em metros1: ");
                float comprimento = scanner.nextFloat();
                System.out.print("\nInforme o preço do imóvel: ");
                float preco = scanner.nextFloat();

                Imovel novoImovel = new Imovel(largura, comprimento, preco);
                imobiliaria.adicionarImovel(novoImovel);
                System.out.println("\nImóvel registrado com sucesso!");
            } else if (opcao == 2) {
                System.out.println(imobiliaria);
            } else if (opcao == 3) {
                System.out.println("\nEncerrando o programa. . . Bip");
                break;
            } else {
                System.out.println("\nOpção inválida.");
            }
        }
    }
}