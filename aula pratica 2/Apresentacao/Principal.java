package Apresentacao;

import Dados.Animal;
import Dados.Dono;
import Dados.Endereco;
import Dados.Vet;
import Dados.Pet;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Pet pet = new Pet();

        while (true) {
            System.out.println("\n * PetShop *");
            System.out.println("\n1. Cadastrar Veterinário. ");
            System.out.println("\n2. Mostrar Veterinários. ");
            System.out.println("\n3. Cadastrar endereço do Veterinário. ");
            System.out.println("\n4. Cadastrar animal. ");
            System.out.println("\n5. Mostrar animais. ");
            System.out.println("\n6. Cadastrar dono. ");
            System.out.println("\n7. Cadastrar endereço do dono. ");
            System.out.println("\n8. Sair. ");
            System.out.println("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    pet.cadastrarVet();
                    break;
                case 2:
                    pet.mostrarVet();
                    break;
                case 3:
                    pet.enderecoVet();
                    break;
                case 4:
                    pet.cadastrarAnimal();
                    break;
                case 5:
                    pet.mostrarAnimais();
                    break;
                case 6:
                    pet.cadastrarDono();
                    break;
                case 7:
                    pet.cadastrarEnderecoDono();
                    break;
                case 8:
                    System.out.println("\nSaindo. . . .");
                    System.exit(0);
                default:
                    System.out.println("\nOpção inválida, tente novamente. ");
            }
        }
    }
}