package dados;

import java.util.List;

public abstract class Gerador {

    protected List<Integer> sequencia;

    public abstract void gerar(int quantidade);

    public List<Integer> getSequencia(){
        return sequencia;
    }

    public void setSequencia(List<Integer> sequencia){
        this.sequencia = sequencia; 
    }


}