package dados;

public class Fibonacci extends Gerador{

    private int n;
    
    private int sequencia(int i) {
        return 0;
    }
    
    public void gerar(int quantidade) {
        sequencia.add(0);
        sequencia.add(1);
        for(int i = 2; i <= n; i++){
            int novoFib = sequencia(i-1) + sequencia(i - 2);
            sequencia.add(novoFib);
        }
    }


}