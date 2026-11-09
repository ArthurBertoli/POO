package dados;

public class Quadrados extends Gerador{

    private int n;

    public void gerar(int quantidade) {
        for(int i = 0; i <=n; i++){
            sequencia.add(i * i);
        }
    }
    
}