package Dados;

public class Caixa {

    private String pagamento;
    private double totalVendas;
    private float totalDinheiro;

    public Caixa() {
    }

    public String getPagamento() {
        return this.pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public void registrarVenda(int quantidade) {
        totalVendas += quantidade;
    }

    public double getTotalVendas() {
        return totalVendas;
    }

    public void registrarDinheiro(float valor) {
        totalDinheiro += valor;
    }

    public float getTotalDinheiro() {
        return totalDinheiro;
    }

    public Caixa(String pagamento, double totalVendas, float totalDinheiro) {
        this.pagamento = pagamento;
        this.totalVendas = totalVendas;
        this.totalDinheiro = totalDinheiro;
    }

}
