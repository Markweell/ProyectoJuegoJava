package pgn.proyectoFinal.negocio;

/**
 * Excepci�n que salta cuando determinada raza no es compatible con determinada
 * clase
 * 
 * @author Gallardo P�rez Marcos
 *
 */
@SuppressWarnings("serial")
public class RazaNoValidaException extends Exception {

	protected RazaNoValidaException(String mj) {
		super(mj);
	}
}
