package apresentacao;

import dados.Digrafo;
import dados.Grafo;

public class Main {
    public static void main(String[] args) {
        Digrafo digrafo = new Digrafo();
        Grafo grafo = new Grafo();

        digrafo.adicionarVertice();
        digrafo.adicionarVertice();
        digrafo.adicionarAresta(0, 1);
        digrafo.adicionarAresta(1, 0);

        grafo.adicionarVertice();
        grafo.adicionarVertice();
        grafo.adicionarAresta(0, 1);

        System.out.println(digrafo.toString());
        System.out.println(grafo.toString());
    }
}
