package pkg;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Main {
	
	public static void main(String[] args) {
		List<IOperacaoInteira> operacoes = new ArrayList<>();
		
		operacoes.add(new Somas());
		operacoes.add(new Multiplicacao());
		operacoes.add(new Mod());
		operacoes.add(new MDC());
		
		Random random = new Random();
		
		int n = random.nextInt(100); 
		int m = random.nextInt(100);
		
		System.out.println("\n Primeiro número: " + n + "\nSegundo número: " + m);
		
		for (IOperacaoInteira operacao : operacoes) {
            String operacaoNome = operacao.getClass().getSimpleName();
            int resultado = operacao.executar(n, m);
            System.out.println("Operação: " + operacaoNome + ", n: " + n + ", m: " + m + ", Resultado: " + resultado);
        }
	}
}
