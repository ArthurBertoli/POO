package pkg;

public class Main {
	public static void main(String[] args) {
		
		SistemaProcessos sistema = new SistemaProcessos();
		
		Juiz juiz1 = new Juiz("Juiz 1", 5);
		Juiz juiz2 = new Juiz("Juiz 2", 8);
		Juiz juiz3 = new Juiz("Juiz 3", 13);
		
		sistema.cadastrarJuiz(juiz1);
		sistema.cadastrarJuiz(juiz2);
		sistema.cadastrarJuiz(juiz3);
		
		for(int i = 1; i < 28; i++) {
			Processo processo = new Processo(i, "Processo " + i);
			sistema.cadastrarProcesso(processo);
		}
		
		try {
			sistema.distribuirProcessos();
		} catch (ProcessoSemJuizException e) {
			System.out.println("\nErro ao distribuir processos: " + e.getMessage());
		}
	}

}
