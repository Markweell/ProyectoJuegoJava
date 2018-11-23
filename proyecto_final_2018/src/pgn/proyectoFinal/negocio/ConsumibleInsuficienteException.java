package pgn.proyectoFinal.negocio;

/**
 * Excepcion que salta cuando no hay consumible suficiente para hacer un ataque
 * 
 * @author Gallardo Pérez Marcos
 *
 */
@SuppressWarnings("serial")
public class ConsumibleInsuficienteException extends Exception {
	ConsumibleInsuficienteException(String mj) {
		super(mj);
	}
}
