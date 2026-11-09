package Dados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Imobiliaria {
    private String nome;
    private List<Imovel> imoveis;

    public String getNome() {
        return nome;
    }

    public Imobiliaria(String nome) {
        this.nome = nome;
        this.imoveis = new ArrayList<>();
    }

    public void adicionarImovel(Imovel imovel) {
        imoveis.add(imovel);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Imobiliária: ").append(nome).append("\n");
        for (Imovel imovel : imoveis) {
            builder.append(imovel.toString()).append("\n");
        }
        return builder.toString();
    }
}