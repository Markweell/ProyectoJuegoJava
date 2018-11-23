package pgn.proyectoFinal.negocio;

/**
 * Excepción que salta cuando un jugador esta repetido en un Array
 * 
 * @author Gallardo Pérez Marcos
 *
 */
@SuppressWarnings("serial")
public class JugadorYaExistenteException extends Exception {
	JugadorYaExistenteException(String mj) {
		super(mj);
	}
}
