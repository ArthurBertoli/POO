package principal;

import java.util.List;
import java.util.Scanner;

import dados.Aluno;
import dados.Equipe;
import dados.Turma;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Turma turma = new Turma();

        System.out.println(
                "Digite os dados dos alunos (nome, idade, notas separadas por espaços, digite 'fim' para encerrar):");

        while (true) {
            String nome = scanner.next();
            if (nome.equals("fim")) {
                break;
            }
            int idade = scanner.nextInt();
            double[] notas = new double[5];
            for (int i = 0; i < 5; i++) {
                notas[i] = scanner.nextDouble();
            }
            Aluno aluno = new Aluno(nome, idade, notas);
            turma.adicionarAluno(aluno);
        }

        List<Equipe> equipes = turma.separarEmEquipes();

        for (Equipe equipe : equipes) {
            System.out.println(equipe.getNome());
            for (Aluno aluno : equipe.getMembros()) {
                System.out.println("Nome: " + aluno.getNome() + ", Média: " + aluno.calcularMedia());
            }
        }

        scanner.close();
    }
}
