package pkg;

public class ProcessoSemJuizException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProcessoSemJuizException() {
        super("Não há juízes disponíveis para distribuir processos.");
    }
}
