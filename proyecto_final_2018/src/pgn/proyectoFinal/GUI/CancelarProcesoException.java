package pgn.proyectoFinal.GUI;

/**
 * Excepci�n que salta cuando queremos que salga de un proceso sin que continue
 * @author d17gapem
 *
 */
@SuppressWarnings("serial")
public class CancelarProcesoException extends Exception {
	
	CancelarProcesoException(String mj) {
		super(mj);
	}
}
