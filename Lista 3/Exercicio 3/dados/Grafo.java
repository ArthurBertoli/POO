package dados;

public class Grafo extends Digrafo {
    @Override
    protected String getNomeTipo() {
        return "Grafo";
    }

    public void adicionarAresta(int origem, int destino) {
        super.adicionarAresta(origem, destino);
        super.adicionarAresta(destino, origem);
    }
}