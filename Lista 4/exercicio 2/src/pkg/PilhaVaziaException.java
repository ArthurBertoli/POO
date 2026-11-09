package pkg;

public class PilhaVaziaException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PilhaVaziaException() {
        super("Pilha está vazia.");
    }
}