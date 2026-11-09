package pkg;

public class Mod implements IOperacaoInteira{
	
	@Override
	public int executar(int n, int m) {
		while(n >= m){
			n -= m;
		}
		return n;
	}
}
