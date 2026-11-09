package negocio;

import dados.Viveiro;
import dados.Animal;
import dados.Aquario;
import dados.Peixe;
import java.util.Scanner;

public class Teste {

    public static void main(String[] args) {

        Zoologico zoo = new Zoologico(10);
        Scanner scanner = new Scanner(System.in);

        Viveiro v = new Viveiro(5);
        System.out.print("\nDigite o nome do viveiro: ");
        String nomeViveiro = scanner.nextLine();
        v.setNome(nomeViveiro);
        System.out.print("\nDigite o comprimento do viveiro: ");
        float comprimentoViveiro = scanner.nextFloat();
        v.setComprimento(comprimentoViveiro);
        System.out.print("\nDigite a largura do viveiro: ");
        float larguraViveiro = scanner.nextFloat();
        v.setLargura(larguraViveiro);
        zoo.cadastrarViveiro(v);
        scanner.nextLine();

        Animal a = new Animal();
        System.out.println(("\nDigite o nome do animal:"));
        String nomeAnimal = scanner.nextLine();
        a.setNome(nomeAnimal);
        System.out.println("\nDigite a largura do animal: ");
        float larguraAnimal = scanner.nextFloat();
        a.setLargura(larguraAnimal);
        System.out.println("\nDigite o comprimento do animal: ");
        float comprimentoAnimal = scanner.nextFloat();
        a.setComprimento(comprimentoAnimal);
        zoo.alocarAnimal(a, v);
        zoo.cadastrarAnimais(a);
        scanner.nextLine();

        Aquario aq = new Aquario(5);
        System.out.println("\nDigite o nome do aquário: ");
        String nomeAquario = scanner.nextLine();
        aq.setNome(nomeAquario);
        System.out.println("\nDigite a altura do aquário: ");
        float alturaAquario = scanner.nextFloat();
        aq.setAltura(alturaAquario);
        System.out.println("\nDigite o comprimento do aquário: ");
        float comprimentoAquario = scanner.nextFloat();
        aq.setComprimento(comprimentoAquario);
        System.out.println("\nDigite a largura do aquário: ");
        float larguraAquario = scanner.nextFloat();
        aq.setLargura(larguraAquario);
        System.out.println("\nDigite A temperatura do aquário: ");
        float temperaturaAquario = scanner.nextFloat();
        aq.setTemperatura(temperaturaAquario);
        zoo.cadastrarViveiro(aq);
        scanner.nextLine();

        Peixe p = new Peixe();
        System.out.println("\nDigite o nome do peixe: ");
        String nomePeixe = scanner.nextLine();
        p.setNome(nomePeixe);
        System.out.println("\nDigite a largura do peixe: ");
        Float larguraPeixe = scanner.nextFloat();
        p.setLargura(larguraPeixe);
        System.out.println("\nDigite o comprimento do peixe: ");
        float comprimentoPeixe = scanner.nextFloat();
        p.setComprimento(comprimentoPeixe);
        System.out.println("\nDigite a altura do peixe: ");
        float alturaPeixe = scanner.nextFloat();
        p.setAltura(alturaPeixe);
        System.out.println("\nDigite a temperatura ideal para o peixe: ");
        float temperaturaIdealPeixe = scanner.nextFloat();
        p.setTemperaturaIdeal(temperaturaIdealPeixe);
        if (!zoo.alocarAnimal(p, v)) {
            System.out.println("\nO animal é um peixe.");
        }
        zoo.alocarAnimal(p, v);
        zoo.cadastrarAnimais(p);
        scanner.nextLine();

        Viveiro viveiros[] = zoo.getSoViveiros();
        int quantViveiros = zoo.getQuantiViveiros();

        Aquario aquarios[] = zoo.getSoAquarios();
        int quantAquarios = zoo.getQuantiViveiros();

        for (int i = 0; i < quantViveiros; i++) {
            System.out.println(viveiros[i]);
        }

        for (int i = 0; i < quantAquarios; i++) {
            System.out.println(aquarios[i]);
        }
    }

}
