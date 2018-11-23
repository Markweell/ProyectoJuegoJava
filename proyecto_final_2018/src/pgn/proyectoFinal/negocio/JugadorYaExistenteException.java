package pgn.proyectoFinal.negocio;

/**
 * Excepci�n que salta cuando un jugador esta repetido en un Array
 * 
 * @author Gallardo P�rez Marcos
 *
 */
@SuppressWarnings("serial")
public class JugadorYaExistenteException extends Exception {
	JugadorYaExistenteException(String mj) {
		super(mj);
	}
}
