package pgn.proyectoFinal.negocio;

/**
 * Excepcion que salta cuando no se cumple un patr�n
 * 
 * @author Gallardo P�rez Marcos
 *
 */
@SuppressWarnings("serial")
public class PatronNoValidoException extends Exception {

	public PatronNoValidoException(String mj) {
		super(mj);
	}
}
