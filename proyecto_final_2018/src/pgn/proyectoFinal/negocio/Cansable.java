package pgn.proyectoFinal.negocio;

import java.util.ArrayList;

public interface Cansable {
	/**
	 * Comprueba que cierto personaje tiene consumible suficiente.
	 * 
	 * @param consumible
	 * @throws ConsumibleInsuficienteException,
	 *             salta cuando no tiene consumible suficiente.
	 */
	void comprobarConsumibleMinimo(double consumible) throws ConsumibleInsuficienteException;

	/**
	 * Hace un check in de los ataques que puede lanzar el jugador. Si no tiene el
	 * consumible suficiente para lanzarlo no estará en este array.
	 * 
	 * @return ArrayList<Ataque> con los ataques que el jugador puede lanzar.
	 */
	public ArrayList<Ataque> ataquesPosibles();
}
