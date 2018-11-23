package pgn.proyectoFinal.negocio;

/**
 * Excepci�n que salta cuando el personaje esta repetido.
 * 
 * @author Gallardo P�rez Marcos.
 *
 */
@SuppressWarnings("serial")
public class PersonajeYaExistenteException extends Exception {

	PersonajeYaExistenteException(String mj) {
		super(mj);
	}
}
