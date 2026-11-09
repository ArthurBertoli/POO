package pkg;

import java.util.ArrayList;
import java.util.List;

public class Juiz {
	
	private String nome;
	private int limite;
	private List<Processo> pilha = new ArrayList<>();
	
	public Juiz(String nome, int limite) {
        this.setNome(nome);
        this.limite = limite;
    }
	
	public void cadastrarProcessos(Processo processo) throws PilhaCheiaException {
		if(pilha.size() >= limite) {
			throw new PilhaCheiaException();
		}
		pilha.add(processo);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
