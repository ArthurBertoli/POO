package pkg;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SistemaProcessos {
	
	private List<Juiz> juizes = new ArrayList<>();
    private List<Processo> processos = new ArrayList<>();
	
	public void cadastrarProcesso(Processo processo) {
		processos.add(processo);
	}
	
	public void cadastrarJuiz(Juiz juiz) {
		juizes.add(juiz);
		
	}
	
	public void distribuirProcessos() throws ProcessoSemJuizException{
		if(juizes.isEmpty()) {
			throw new ProcessoSemJuizException();
		}
		
		Random random = new Random();
		
		for(Processo processo : processos) {
			int randJuiz = random.nextInt(juizes.size());
			Juiz juiz = juizes.get(randJuiz);
			
			try {
				juiz.cadastrarProcessos(processo);
				System.out.println("\nO Processo " + processo.getId() + " foi distribuído para o juiz: " + juiz.getNome());
			} catch (PilhaCheiaException e) {
				 System.out.println("Não foi possível distribuir o processo " + processo.getId() + " para o juiz " + juiz.getNome() + ": " + e.getMessage());
			}
		}
	}
	
	
}
