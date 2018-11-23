package pgn.proyectoFinal.negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Envoltorio del arrayList para almacenar Jugadores.
 * 
 * @author Gallardo Pérez Marcos
 *
 */
@SuppressWarnings("serial")
public class Jugadores implements Iterable<Jugador>, Serializable {

	/**
	 * ArrayList donde se almacenan los jugadores
	 */
	ArrayList<Jugador> jugadores = new ArrayList<Jugador>();

	/**
	 * Devuelve el ArrayList con los jugadores
	 * 
	 * @return jugadores
	 */
	ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

	/**
	 * Añade un jugador al ArrayList si es posible
	 * 
	 * @param nombre
	 * @param nombreUsuario
	 * @param contrasenia
	 * @throws JugadorYaExistenteException,
	 *             salta si existe un jugador con el mismo nombre de usuario
	 * @throws PatronNoValidoException,
	 *             Salta cuando no se cumple el patrón del nombre, nombre de usuario
	 *             o de la contraseña
	 */
	void add(String nombre, String nombreUsuario, String contrasenia)
			throws JugadorYaExistenteException, PatronNoValidoException {
		Jugador jugador = new Jugador(nombre, nombreUsuario, contrasenia);
		if (getJugadores().contains(jugador))
			throw new JugadorYaExistenteException("Ya existe un jugador con ese nombre de usuario");
		getJugadores().add(jugador);
	}

	/**
	 * Elimina un jugador del ArrayList de que se pide el nombre de usuario
	 * 
	 * @param usuario
	 * @return true en caso de que esté eliminado, false en caso contrario
	 * @throws PatronNoValidoException,
	 *             salta cuando el nombre de usuario que has puesto no es válido
	 */
	boolean remove(String usuario) throws PatronNoValidoException {
		return getJugadores().remove(new Jugador("Marcos", usuario, "1pP!arfpp"));
	}

	/**
	 * Obtienes un jugador del ArrayList sabiendo su nombre.
	 * 
	 * @param usuario
	 * @return Jugador
	 * @throws PatronNoValidoException,
	 *             salta cuando el nombre de usuario que has puesto no es válido
	 */
	Jugador getJugador(String usuario) throws PatronNoValidoException {
		Jugador jugador = new Jugador("Marcos", usuario, "1pP!arfpp");
		if (!contains(jugador))
			return null;
		return getJugadores().get(getJugadores().indexOf(jugador));
	}

	/**
	 * Te dice si el ArrayList contiene o no un Jugador con un determinado nombre de
	 * usuario
	 * 
	 * @param nombreUsuario
	 * @return true en caso de que lo contenga, false en caso contrario
	 * @throws PatronNoValidoException,
	 *             salta cuando el nombre de usuario que has puesto no es válido
	 */
	boolean contains(String nombreUsuario) throws PatronNoValidoException {
		Jugador jugador = new Jugador("Marcos", nombreUsuario, "1pP!arfpp");
		return jugadores.contains(jugador);
	}

	/**
	 * Te dice si el ArrayList contiene o no un Jugador
	 * 
	 * @param jugador
	 * @return true en caso de que lo contenga, false en caso contrario.
	 */
	boolean contains(Jugador jugador) {
		return jugadores.contains(jugador);
	}

	/**
	 * Te dice el numero de jugadores que hay en el ArrayList
	 * 
	 * @return numero de jugadores
	 */
	int size() {
		return getJugadores().size();
	}

	/**
	 * Cadena con todos los jugadores.
	 * @return cadena con la información de todos los jugadores
	 */
	public String toString() {
		String mj = "";
		for (Jugador jugador : getJugadores()) {
			mj += "\n" + jugador.toString();
		}
		return mj;
	}

	/**
	 * Te dice si el ArrayList de los jugadores está o no vacio
	 * @return true en caso de que esté vacío, false en caso contario
	 */
	boolean isEmpty() {
		return getJugadores().isEmpty();
	}

	/**
	 * Devuelve un iterador con todos los jugadores del ArrayList jugadores
	 * @return iterador
	 */
	@Override
	public ListIterator<Jugador> iterator() {
		return getJugadores().listIterator();
	}
}
