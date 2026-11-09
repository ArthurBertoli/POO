package dados;

public class NumeroPrimos extends Gerador{

    public boolean primos(int v){
        for(int i = 2; i < v/2; i++){
            if(v % i == 0){
                return false;
            }
        }
        return true;
    }

    public void gerar(int quantidade) {
        int cont = 0;
        int num = 2;
        do{
            if(primos(num)){
                sequencia.add(num);
                cont++;
            }
            num++;
        } while (cont < quantidade);
    }
    
}