package dados;

import java.util.ArrayList;
import java.util.List;

public class Digrafo {
    private List<List<Integer>> matrizAdjacente;

    public Digrafo() {
        matrizAdjacente = new ArrayList<>();
    }

    public void adicionarVertice() {
        for (List<Integer> row : matrizAdjacente) {
            row.add(0);
        }
        List<Integer> newRow = new ArrayList<>();
        for (int i = 0; i < matrizAdjacente.size(); i++) {
            newRow.add(0);
        }
        matrizAdjacente.add(newRow);
    }

    public void adicionarAresta(int origem, int destino) {
        if (origem >= 0 && origem < matrizAdjacente.size() && destino >= 0 && destino < matrizAdjacente.size()) {
            matrizAdjacente.get(origem).set(destino, 1);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Matriz de Adjacência do Dígrafo:\n");
        for (int i = 0; i < matrizAdjacente.size(); i++) {
            for (int j = 0; j < matrizAdjacente.get(i).size(); j++) {
                sb.append(matrizAdjacente.get(i).get(j)).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}