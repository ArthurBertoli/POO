package dados;

public class Fatoriais extends Gerador{

    private int n;
    
    private int sequencia(int i) {
        return 0;
    }
    public void gerar(int quantidade) {
        sequencia.add(1);
        for(int i = 2; i <= n; i++){
            int novoFat = sequencia(i) * sequencia(i -1);
            sequencia.add(novoFat);
        }
    
    }
}