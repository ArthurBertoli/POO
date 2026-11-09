package Dados;

public class Imovel {
    private float largura;
    private float comprimento;
    private float preco;

    public float getPreco() {
        return preco;
    }

    public Imovel(float largura, float comprimento, float preco) {
        this.largura = largura;
        this.comprimento = comprimento;
        this.preco = preco;
    }

    public float calcularArea() {
        return comprimento * largura;
    }

    @Override
    public String toString() {
        return "\nLargura: " + largura + ", Comprimento: " + comprimento + ", Preço: " + preco;
    }
}