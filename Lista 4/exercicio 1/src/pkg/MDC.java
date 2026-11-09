package pkg;

public class MDC implements IOperacaoInteira{
	
	@Override
	public int executar(int n, int m) {
		
		int maior = -1 ;
		int mdc = 0;
		
		if(n >= m) {
			maior = n;
		} else {
			maior = m;
		}
		
		for(int i =1; i < maior; i++) {
			if( n % i == 0 && m %  i == 0) {
				mdc = i;
			}
		}
		
		return mdc;
			
	}

	
}
