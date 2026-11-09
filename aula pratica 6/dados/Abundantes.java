package dados;

import java.util.List;

public class Abundantes extends Gerador{

    private List<Integer> listaDiv;

    public void divisores(int n){
        for(int i = 2; i < n; i++){
            if((n % i) == 0){
                listaDiv.add(i);
            }
        }
    }

    int soma = 0;
    public void gerar(int quantidade) {
        for(Integer n: listaDiv){
            soma += n.intValue();
            if(n < soma){
                sequencia.add(n);
            }
        }
    }

    
}