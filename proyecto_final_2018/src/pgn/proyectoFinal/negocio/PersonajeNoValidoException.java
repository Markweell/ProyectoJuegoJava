package pgn.proyectoFinal.negocio;

/**
 * Excepci�n que salta cuando un nombre de personaje no s v�lido.
 * 
 * @author Gallardo P�rez Marcos
 *
 */
@SuppressWarnings("serial")
public class PersonajeNoValidoException extends Exception {
	PersonajeNoValidoException(String mj) {
		super(mj);
	}
}
