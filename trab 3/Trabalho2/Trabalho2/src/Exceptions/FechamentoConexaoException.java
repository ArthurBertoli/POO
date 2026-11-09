package Exceptions;

public class FechamentoConexaoException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FechamentoConexaoException(String mensagem) {
        super(mensagem);
    }

    public FechamentoConexaoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}

