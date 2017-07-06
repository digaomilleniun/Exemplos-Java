/**
 * 
 */
package br.com.rpires.domain.usuario;

import javax.xml.ws.WebFault;

/**
 * @author rpires
 *
 */
@WebFault(name="AutorizacaoFault")
public class AutorizacaoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 728666112012027077L;

	/**
	 * 
	 */
	public AutorizacaoException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public AutorizacaoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public AutorizacaoException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public AutorizacaoException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public AutorizacaoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Altera os detalhes da mensagem de erro.
	 * 
	 * @return
	 */
	public String getFaultInfo() {
		return "Token Inválido";
	}

}
