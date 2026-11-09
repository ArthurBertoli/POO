package pkg;

public class Multiplicacao implements IOperacaoInteira {
	
	@Override
	public int executar(int n, int m) {
		int resultado = 0;
		
		for(int i =0; i < m; i ++) {
			resultado = new Somas().executar(resultado, n);
		}
		
		return resultado;
	}	
}
