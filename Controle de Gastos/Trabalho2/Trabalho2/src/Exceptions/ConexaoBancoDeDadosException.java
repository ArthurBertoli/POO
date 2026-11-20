package Exceptions;

public class ConexaoBancoDeDadosException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConexaoBancoDeDadosException(String mensagem) {
        super(mensagem);
    }

    public ConexaoBancoDeDadosException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}


