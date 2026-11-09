package dados;

import java.util.List;

public class Perfeitos extends Gerador{

    private List<Integer> listaDivP;
    
    public void divisoresP(int n){
        for(int i = 2; i < n; i++){
            if((n % i) == 0){
                listaDivP.add(i);
            }
        }
    }

    int soma = 0;

    public void gerar(int quantidade) {
        for(Integer n: listaDivP){
            soma += n.intValue();
            if(n == soma){
                sequencia.add(n);
            }
        }
    }

    
}
