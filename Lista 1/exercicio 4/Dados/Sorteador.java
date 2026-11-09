package Dados;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sorteador {

    private List<Pessoa> pessoas;

    public Sorteador(List<Pessoa> pessoas) {
        this.pessoas = new ArrayList<>(pessoas);
    }

    public Pessoa sortearProximo() {
        if (pessoas.isEmpty()) {
            return null;
        }

        Random random = new Random();
        int indiceSorteado = random.nextInt(pessoas.size());
        Pessoa pessoaSorteada = pessoas.remove(indiceSorteado);
        return pessoaSorteada;
    }
}