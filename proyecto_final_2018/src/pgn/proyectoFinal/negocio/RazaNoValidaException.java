package pgn.proyectoFinal.negocio;

/**
 * Excepción que salta cuando determinada raza no es compatible con determinada
 * clase
 * 
 * @author Gallardo Pérez Marcos
 *
 */
@SuppressWarnings("serial")
public class RazaNoValidaException extends Exception {

	protected RazaNoValidaException(String mj) {
		super(mj);
	}
}
