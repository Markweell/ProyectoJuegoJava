package pgn.proyectoFinal.negocio;

/**
 * Excepción que salta cuando el personaje esta repetido.
 * 
 * @author Gallardo Pérez Marcos.
 *
 */
@SuppressWarnings("serial")
public class PersonajeYaExistenteException extends Exception {

	PersonajeYaExistenteException(String mj) {
		super(mj);
	}
}
