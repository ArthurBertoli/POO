package Dados;

public class Produto {

    private String nome;
    private float preco;
    private String validade;

    public Produto() {
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPreco() {
        return this.preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getValidade() {
        return this.validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public Produto(String nome, float preco, String validade) {
        this.nome = nome;
        this.preco = preco;
        this.validade = validade;
    }
}
