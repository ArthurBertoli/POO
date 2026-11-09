package main;

import dados.Pessoa;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Principal {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            Map<String, Pessoa> map = new HashMap<>();
            List<Pessoa> criancas = new ArrayList<>();
            List<Pessoa> adolescentes = new ArrayList<>();
            List<Pessoa> jovens = new ArrayList<>();
            List<Pessoa> adultos = new ArrayList<>();
            List<Pessoa> idosos = new ArrayList<>();

            while (true) {

                System.out.println("\nDigite os dados da(s) pessoas: (digite -1 para parar)");
                int idade = scanner.nextInt();

                if (idade == -1) {
                    break;
                }

                scanner.nextLine();

                System.out.println("\nDigite o nome da pessoa: ");
                String nome = scanner.nextLine();
                System.out.println("\nDigite o Cpf da pessoa: ");
                String cpf = scanner.nextLine();
                System.out.println("\nDigite a cidade da pessoa: ");
                String cidade = scanner.nextLine();

                Pessoa pessoa = new Pessoa(nome, idade, cpf, cidade);
                map.put(pessoa.getNome(), pessoa);

                if (idade >= 1 && idade <= 12) {
                    criancas.add(pessoa);
                } else if (idade >= 13 && idade <= 18) {
                    adolescentes.add(pessoa);
                } else if (idade >= 19 && idade <= 25) {
                    jovens.add(pessoa);
                } else if (idade >= 25 && idade <= 59) {
                    adultos.add(pessoa);
                } else if (idade > 59) {
                    idosos.add(pessoa);
                }
            }

            System.out.println("\nCrianças:");
            for (Pessoa crianca : criancas) {
                System.out.println(crianca.getNome() + ", " + crianca.getIdade() + ", " + crianca.getCpf() + ", "
                        + crianca.getCidade());
            }

            System.out.println("\nAdolescentes:");
            for (Pessoa adolescente : adolescentes) {
                System.out.println(adolescente.getNome() + ", " + adolescente.getIdade() + ", " + adolescente.getCpf()
                        + ", " + adolescente.getCidade());
            }

            System.out.println("\nJovens:");
            for (Pessoa jovem : jovens) {
                System.out.println(
                        jovem.getNome() + ", " + jovem.getIdade() + ", " + jovem.getCpf() + ", " + jovem.getCidade());
            }

            System.out.println("\nAdultos:");
            for (Pessoa adulto : adultos) {
                System.out.println(adulto.getNome() + ", " + adulto.getIdade() + ", " + adulto.getCpf() + ", "
                        + adulto.getCidade());
            }

            System.out.println("\nIdosos:");
            for (Pessoa idoso : idosos) {
                System.out.println(
                        idoso.getNome() + ", " + idoso.getIdade() + ", " + idoso.getCpf() + ", " + idoso.getCidade());
            }

            scanner.close();
        }
    }

}
