package pkg;

import java.util.ArrayList;
import java.util.List;

public class Pilha<T> {
	
	private int limite;
	private List<T> elementos = new ArrayList<>();
	
	public Pilha(int limite) {
		this.limite = limite;
	}
	
	public void inserir(T elemento) throws PilhaCheiaException{
		if(elementos.size() >= limite) {
			throw new PilhaCheiaException();
		}
		elementos.add(elemento);
	}
	
	public T remover() throws PilhaVaziaException{
		if(elementos.isEmpty()) {
			throw new PilhaVaziaException();
		}
		return elementos.remove(elementos.size() - 1);
	}
	
	
}
