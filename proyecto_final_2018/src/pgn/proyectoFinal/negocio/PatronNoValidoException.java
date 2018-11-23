package pgn.proyectoFinal.negocio;

/**
 * Excepcion que salta cuando no se cumple un patrón
 * 
 * @author Gallardo Pérez Marcos
 *
 */
@SuppressWarnings("serial")
public class PatronNoValidoException extends Exception {

	public PatronNoValidoException(String mj) {
		super(mj);
	}
}
