package dados;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class CalculadoraEstatistica implements ISequencia {
    private static CalculadoraEstatistica instance = null;

    private CalculadoraEstatistica() {
    }

    public static CalculadoraEstatistica getInstance() {
        if (instance == null) {
            instance = new CalculadoraEstatistica();
        }
        return instance;
    }

    private List<Integer> sequencia = new LinkedList<>();

    public void adicionarValor(int valor) {
        this.sequencia.add(valor);
    }

    public List<Integer> getValores() {
        return sequencia;
    }

    public void setValores(List<Integer> valores) {
        this.sequencia = valores;
    }

    public void limparValores() {
        sequencia.clear();
    }

    public int sortear() {
        final Random r = new Random();
        return sequencia.get(r.nextInt(sequencia.size()));
    }

    public long somatorio() {
        long soma = 0;
        for (final int x : sequencia) {
            soma += x;
        }
        return soma;
    }

    public long produtoTotal() {
        long produto = 1;
        for (final int x : sequencia) {
            produto *= x;
        }
        return produto;
    }

    /**
     * Média geométrica calculada por logaritmos (exp da média dos logaritmos), o que evita
     * o estouro do produto em {@code long} quando há muitos valores ou valores grandes.
     * Retorna 0 se algum valor for 0 e {@code Double.NaN} se houver valor negativo
     * (a média geométrica não é definida nesse caso).
     */
    public double mediaGeometrica() {
        double somaLogaritmos = 0;
        for (final int x : sequencia) {
            if (x < 0) {
                return Double.NaN;
            }
            if (x == 0) {
                return 0;
            }
            somaLogaritmos += Math.log(x);
        }
        return Math.exp(somaLogaritmos / sequencia.size());
    }

    public double mediaAritmetica() {
        return somatorio() / (double) sequencia.size();
    }

    /** Variância amostral (divide por n - 1). Com menos de 2 valores, retorna 0. */
    public double variancia() {
        if (sequencia.size() < 2) {
            return 0;
        }
        final double media = mediaAritmetica();
        double soma = 0;
        for (final int x : sequencia) {
            soma += Math.pow(x - media, 2);
        }
        return soma / ((double) sequencia.size() - 1);
    }

    public double desvioPadrao() {
        return Math.sqrt(variancia());
    }

    public int amplitude() {
        int maior = sequencia.get(0);
        int menor = sequencia.get(0);

        for (final int x : sequencia) {
            if (x > maior) {
                maior = x;
            }
            if (x < menor) {
                menor = x;
            }
        }

        return maior - menor;
    }

}
