package Dados;

import java.lang.reflect.Array;

public class Veterinario {

    private String nome;
    private float salario;
    private Endereco endereco;
    private Animal[] animais = new Animal[5];
    private int qntdanimais = 0;

    public Veterinario(int tam) {

        qntdanimais = 0;
        animais = new Animal[tam];
    }

    public void adicionarAnimal(Animal animal) {
        if (qntdanimais < animais.length) {
            animais[qntdanimais] = animal;
            qntdanimais++;
        }
    }

    public Veterinario() {
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getSalario() {
        return this.salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public Endereco getEndereco() {
        return this.endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public int getQntdanimais() {
        return this.qntdanimais;
    }

    public Animal[] getAnimais() {
        return animais;
    }

    public void setAnimal(Animal animal) {
        if (qntdanimais < animais.length) {
            animais[qntdanimais] = animal;
            qntdanimais++;
        }
    }

}
