package negocio;

import dados.Animal;
import dados.Aquario;
import dados.Viveiro;

public class Zoologico {

    private Animal animais[] = new Animal[100];
    private Viveiro viveiros[] = new Viveiro[50];
    private int quantViveiros = 0;
    private int quantAnimais = 0;
    private int quantAquarios = 0;

    public Zoologico(int quantAnimais) {
        animais = new Animal[quantAnimais];
    }

    public Zoologico() {
    }

    public Viveiro[] getSoViveiros() {
        Viveiro[] viveirosTemp = new Viveiro[quantViveiros];
        int cont = 0;

        for (int i = 0; i < quantViveiros; i++) {
            if (!(viveiros[i] instanceof Aquario)) {
                viveirosTemp[cont] = viveiros[i];
                cont++;
            }
        }
        return viveirosTemp;
    }

    public Aquario[] getSoAquarios() {

        Aquario[] aquariosTemp = new Aquario[quantAquarios];
        int cont = 0;

        for (int i = 0; i < quantViveiros; i++) {
            if (viveiros[i] instanceof Aquario) {
                aquariosTemp[cont] = (Aquario) viveiros[i];
                cont++;
            }
        }
        return aquariosTemp;

    }

    public void cadastrarViveiro(Viveiro viveiro) {
        if (quantViveiros < viveiros.length) {
            viveiros[quantViveiros] = viveiro;
            quantViveiros++;
        } else {
            System.out.println("\nNão é possível criar um novo viveiro.");
        }
    }

    public void cadastrarAnimais(Animal animal) {
        if (quantAnimais < animais.length) {
            animais[quantAnimais] = animal;
            quantAnimais++;
        } else {
            System.out.println("\nNão é possível adicionar mais animais. ");
        }
    }

    public Animal[] getAnimais() {
        return animais;
    }

    public Viveiro[] getViveiros() {
        return viveiros;
    }

    public int getQuantiViveiros() {
        return quantViveiros;
    }

    public boolean alocarAnimal(Animal animal, Viveiro viveiro) {
        return viveiro.adicionarAnimal(animal);
    }
}
