package pgn.proyectoFinal.negocio;

/**
 * Excepción que salta cuando un nombre de personaje no s válido.
 * 
 * @author Gallardo Pérez Marcos
 *
 */
@SuppressWarnings("serial")
public class PersonajeNoValidoException extends Exception {
	PersonajeNoValidoException(String mj) {
		super(mj);
	}
}
