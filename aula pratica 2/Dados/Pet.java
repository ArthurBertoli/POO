package Dados;

import java.util.Scanner;

public class Pet {

    private Veterinario[] veterinarios = new Veterinario[50];
    private int qntdvet = 0;
    private Scanner scan = new Scanner(System.in);

    public void cadastrarVet() {

        String nome;
        float salario;
        Veterinario novVet = new Veterinario();

        System.out.println("\nQual o nome do veterinário(a)?");
        scan.nextLine();
        nome = scan.nextLine();

        System.out.println("\nQual é o salário do veterinário(a)?");
        salario = scan.nextFloat();

        novVet.setNome(nome);
        novVet.setSalario(salario);

        veterinarios[qntdvet] = novVet;
        qntdvet++;
        System.out.println("\nVeterinário cadastrado com sucesso!");
    }

    public void mostrarVet() {

        System.out.println("\nLista de veterinários: ");

        for (int i = 0; i < qntdvet; i++) {
            System.out.println("\nVeterinário : " + i + ";");
            System.out.println("\nNome: " + veterinarios[i].getNome());
            System.out.println("\nSalário : " + veterinarios[i].getSalario());
        }
    }

    public void enderecoVet() {

        if (qntdvet == 0) {
            System.out.println("\nNenhum veterinário foi cadastrado. ");
            return;
        }

        System.out.println("\nVeterinários cadastrados: ");
        for (int i = 0; i < qntdvet; i++) {
            System.out.println(i + ": " + veterinarios[i].getNome());
        }

        System.out.println("\nEscolha o número do veterinário para cadastrar: ");
        int escolha = scan.nextInt();

        if (escolha < 0 || escolha >= qntdvet) {
            System.out.println("\nEscolha inválida! ");
            return;
        }

        scan.nextLine();

        Endereco novEndereco = new Endereco();

        String rua, bairro, cidade, estado;
        int numero, cep;

        System.out.println("\nInforme os dados do endereço: ");
        System.out.println("\nRua: ");
        rua = scan.nextLine();

        System.out.println("\nNúmero: ");
        numero = scan.nextInt();

        scan.nextLine();

        System.out.println("\nBairro: ");
        bairro = scan.nextLine();

        System.out.println("\nCidade: ");
        cidade = scan.nextLine();

        System.out.println("\nEstado: ");
        estado = scan.nextLine();

        System.out.println("\nCep: ");
        cep = scan.nextInt();

        novEndereco.setRua(rua);
        novEndereco.setNumero(numero);
        novEndereco.setBairro(bairro);
        novEndereco.setCidade(cidade);
        novEndereco.setEstado(estado);
        novEndereco.setCep(cep);

        veterinarios[escolha].setEndereco(novEndereco);

        System.out.println("\nEndereço cadastrado com sucesso! ");

    }

    public void cadastrarAnimal() {

        if (qntdvet == 0) {
            System.out.println("\nNenhum veterinário foi cadastrado. ");
            return;
        }

        System.out.println("\nVeterinários cadastrados: ");
        for (int i = 0; i < qntdvet; i++) {
            System.out.println(i + ": " + veterinarios[i].getNome());
        }

        System.out.println("\nEscolha o número do veterinário para cadastrar o animal: ");
        int escolha = scan.nextInt();

        if (escolha < 0 || escolha >= qntdvet) {
            System.out.println("\nEscolha inválida! ");
            return;
        }

        scan.nextLine();

        Animal novoAnimal = new Animal();

        String nomeAnimal, especie, descricao;

        System.out.println("\nQual é o nome do animal? ");
        nomeAnimal = scan.nextLine();

        System.out.println("\nQual é a espécie desse animal? ");
        especie = scan.nextLine();

        System.out.println("\nFaça uma breve descrição sobre esse animal: ");
        descricao = scan.nextLine();

        novoAnimal.setNomeAnimal(nomeAnimal);
        novoAnimal.setEspecie(especie);
        novoAnimal.setDescricao(descricao);

        veterinarios[escolha].adicionarAnimal(novoAnimal);

        System.out.println("\nAnimal cadastrado com sucesso! ");
    }

    public void mostrarAnimais() {

        System.out.println("\nAnimais cadastrados:");
        for (int i = 0; i < qntdvet; i++) {
            if (veterinarios[i] != null) {
                Animal[] animaisAtendidos = veterinarios[i].getAnimais();
                for (int j = 0; j < animaisAtendidos.length; j++) {
                    if (animaisAtendidos[j] != null) {
                        System.out.println(i + "-" + j + ":" + animaisAtendidos[j].getNomeAnimal());
                    }
                }
            }
        }

    }

    public void cadastrarDono() {

        mostrarAnimais();

        System.out.println("\nEscolha o número do animal para cadastrar o dono: ");
        int escolhaAnimal = scan.nextInt();

        if (escolhaAnimal < 0) {
            System.out.println("\nEscolha inválida!");
            return;
        }

        boolean animalEncontrado = false;

        Dono novDono = new Dono();

        String nomeDono;
        int cpfDono;

        for (int i = 0; i < qntdvet; i++) {
            Animal[] animaisAtendidos = veterinarios[i].getAnimais();
            if (escolhaAnimal < animaisAtendidos.length) {
                animalEncontrado = true;
                scan.nextLine();

                System.out.println("\nQual é o nome do dono? ");
                nomeDono = scan.nextLine();

                System.out.println("\nQual é o cpf do dono? ");
                cpfDono = scan.nextInt();

                novDono.setNome(nomeDono);
                novDono.setCpf(cpfDono);

                animaisAtendidos[escolhaAnimal].setDono(novDono);

                System.out.println("\nDono cadastrado com sucesso!");
                break;
            } else {
                escolhaAnimal -= animaisAtendidos.length;
            }
        }

        if (!animalEncontrado) {
            System.out.println("\nAnimal não encontrado.");
        }
    }

    public void cadastrarEnderecoDono() {

        if (qntdvet == 0) {
            System.out.println("\nNenhum veterinário foi cadastrado. ");
            return;
        }

        System.out.println("\nAnimais cadastrados:");
        for (int i = 0; i < qntdvet; i++) {
            Animal[] animalsAtendidos = veterinarios[i].getAnimais();
            for (int j = 0; j < animalsAtendidos.length; j++) {
                System.out.println(i + "-" + j + ":" + animaisAtendidos[j].getNome());
            }
        }

        int escolhaAnimal;

        System.out.println("\nEscolha o número do animal para cadastrar o endereço do dono: ");
        scan.nextLine();
        escolhaAnimal = scan.nextInt();

        if (escolhaAnimal < 0) {
            System.out.println("\nEscolha inválida!");
            return;
        }

        boolean animalEncontrado = false;
        String ruaDono, bairroDono, cidadeDono, estadoDono;
        int numeroDono, cepDono;

        for (int i = 0; i < qntdvet; i++) {
            Animal[] animaisAtendidos = veterinarios[i].getAnimais();
            if (escolhaAnimal < animaisAtendidos.length) {
                animalEncontrado = true;
                scan.nextLine();

                System.out.println("\nQual é a rua do dono? ");
                ruaDono = scan.nextLine();

                System.out.println("\nQual é o número do dono? ");
                numeroDono = scan.nextInt();

                scan.nextLine();

                System.out.println("\nQual é o bairro do dono? ");
                bairroDono = scan.nextLine();

                System.out.println("\nQual é a cidade do dono? ");
                cidadeDono = scan.nextLine();

                System.out.println("\nQual é o estado do dono? ");
                estadoDono = scan.nextLine();

                System.out.println("\nQual é o cep do dono? ");
                cepDono = scan.nextLine();

                Dono donoAnimal = animaisAtendidos[escolhaAnimal].geDono();

                if (donoAnimal != null) {
                    Endereco enderecoDono = new Endereco();

                    enderecoDono.setRua(ruaDono);
                    enderecoDono.setNumero(numeroDono);
                    enderecoDono.setBairro(bairroDono);
                    enderecoDono.setCidade(cidadeDono);
                    enderecoDono.setEstado(estadoDono);
                    enderecoDono.setCep(cepDono);

                    donoAnimal.setEndereco(enderecoDono);

                    System.out.println("\nEndereço do dono cadastrado com sucesso!");
                } else {
                    System.out.println("\nAnimal não possui dono cadastrado.");
                }

                break;

            } else {
                escolhaAnimal -= animaisAtendidos.length;
            }
        }

        if (!animalEncontrado) {
            System.out.println("\nAnimal não encontrado.");
        }
    }
}
