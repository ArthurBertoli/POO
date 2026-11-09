package Dados;

import java.util.List;
import java.util.ArrayList;

public class Carrinho {

    private List<Produto> produtos;
    private int quantidade;
    private float valortotal;

    public Carrinho() {
        produtos = new ArrayList<>();
    }

    public List<Produto> getProdutos() {
        return this.produtos;
    }

    public void adicionarproduto(Produto produto) {
        produtos.add(produto);
        quantidade++;
        valortotal += produto.getPreco();
    }

    public void removerproduto(Produto produto) {
        produtos.remove(produto);
        quantidade--;
        valortotal -= produto.getPreco();
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public float getValortotal() {
        return this.valortotal;
    }

    public Carrinho(float valortotal, List<Produto> produtos, int quantidade) {
        this.valortotal = valortotal;
        this.quantidade = quantidade;
        this.produtos = produtos;
    }
}
