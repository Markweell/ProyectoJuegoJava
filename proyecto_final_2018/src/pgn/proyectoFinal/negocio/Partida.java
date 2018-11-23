package pgn.proyectoFinal.negocio;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ListIterator;

/**
 * Clase que gestiona los datos de la partida, y que es lo que vamos a guardar o
 * a leer
 * 
 * @author Gallardo P�rez Marcos
 *
 */
@SuppressWarnings("serial")
public class Partida implements Serializable {

	/**
	 * ArrayList con los jugadores que participan en la partida
	 */
	private static Jugadores partida = new Jugadores();
	/**
	 * Campo que almacena si la partida se ha modificado o no.
	 */
	private static boolean modificado = false;

	/**
	 * Devuelve el campo modificado, donde se guarda infomaci�n sobre si la partida
	 * se ha guardado o no.
	 * 
	 * @return modificado
	 */
	public static boolean getModificado() {
		return modificado;
	}

	/**
	 * Cambia el campo modificado, donde se guarda infomaci�n sobre si la partida se
	 * ha guardado o no.
	 * 
	 * @param modificado
	 */
	public static void setModificado(boolean modificado) {
		Partida.modificado = modificado;
	}

	/**
	 * A�ade un jugador al ArrayList si es posible, esto modifica la partida.
	 * 
	 * @param nombre
	 * @param nombreUsuario
	 * @param contrasenia
	 * @throws JugadorYaExistenteException,
	 *             salta si existe un jugador con el mismo nombre de usuario
	 * @throws PatronNoValidoException,
	 *             Salta cuando no se cumple el patr�n del nombre, nombre de usuario
	 *             o de la contrase�a
	 */
	public static void add(String nombre, String nombreUsuario, String contrasenia)
			throws JugadorYaExistenteException, PatronNoValidoException {
		partida.add(nombre, nombreUsuario, contrasenia);
		setModificado(true);
	}

	/**
	 * Elimina un jugador del ArrayList de que se pide el nombre de usuario. Esto
	 * modifica la partida
	 * 
	 * @param usuario
	 * @return true en caso de que est� eliminado, false en caso contrario
	 * @throws PatronNoValidoException,
	 *             salta cuando el nombre de usuario que has puesto no es v�lido
	 */
	public static boolean remove(String nombreUsuario) throws PatronNoValidoException {
		if (partida.remove(nombreUsuario)) {
			setModificado(true);
			return true;
		}
		return false;
	}

	/**
	 * Devuelve un iterador con todos los jugadores de la partida.
	 * 
	 * @return iterador
	 */
	public static ListIterator<Jugador> listIterator() {
		return partida.iterator();
	}

	/**
	 * Te dice si la partida tiene jugadores o no.
	 * 
	 * @return true en caso de que no tenga jugadores.
	 */
	public static boolean isEmpty() {
		return partida.isEmpty();
	}

	/**
	 * Numero de jugadores que hay en la partida
	 * 
	 * @return numero de jugadores.
	 */
	public static int size() {
		return partida.size();
	}

	/**
	 * Obtienes un jugador de la partida sabiendo su nombre.
	 * 
	 * @param usuario
	 * @return Jugador
	 * @throws PatronNoValidoException,
	 *             salta cuando el nombre de usuario que has puesto no es v�lido
	 */
	public static Jugador getJugador(String nombreUsuario) throws PatronNoValidoException {
		return partida.getJugador(nombreUsuario);
	}

	/**
	 * Comprueba que la partida contiene a un jugador sabiendo su nombre de usuarios
	 * 
	 * @param nombreUsuario
	 * @return true en caso de que la partida contenga a dicho jugador
	 * @throws PatronNoValidoException,
	 *             en caso de que el nombre de usuario no sea v�lido
	 */
	public static boolean contains(String nombreUsuario) throws PatronNoValidoException {
		return partida.contains(nombreUsuario);
	}

	/**
	 * Comprueba que la partida contiene a un jugador
	 * 
	 * @param jugador
	 * @return true en caso de que la partida contenga a dicho jugador
	 */
	public static boolean contains(Jugador jugador) {
		return partida.contains(jugador);
	}

	/**
	 * Comprueba que la contrase�a es v�lida.
	 * 
	 * @param contrasenia
	 * @throws PatronNoValidoException
	 */
	public static void comprobarContrasenia(String contrasenia) throws PatronNoValidoException {
		Jugador.comprobarContrasenia(contrasenia);
	}

	/**
	 * Comprueba que el nombre de usuario es v�lido
	 * 
	 * @param usuario
	 * @throws PatronNoValidoException
	 */
	public static void comprobarUsuario(String usuario) throws PatronNoValidoException {
		usuario = Jugador.estandarizarNombre(usuario);
		Jugador.comprobarUsuario(usuario);
	}

	/**
	 * Comprueba que el nombre es v�lido
	 * 
	 * @param nombre
	 * @throws PatronNoValidoException
	 */
	public static void comprobarNombre(String nombre) throws PatronNoValidoException {
		nombre = Jugador.estandarizarNombre(nombre);
		Jugador.comprobarNombre(nombre);
	}

	/**
	 * Comprueba la raza que puede ser determinada clase
	 * 
	 * @param clase
	 * @param raza
	 * @return true en caso de que la clase y la raza sean compatibles.
	 */
	public static boolean compruebaRaza(Clase clase, Raza raza) {
		switch (clase) {
		case MAGO:
			if (raza == Raza.DRAENEI || raza == Raza.ORCO)
				return false;
			break;
		case BRUJO:
			if (raza == Raza.TROLL || raza == Raza.HUMANO)
				return false;
			break;
		case CABALLERO_DE_LA_MUERTE:
			if (raza == Raza.TROLL || raza == Raza.DRAENEI)
				return false;
			break;
		case GUERRERO:
			if (raza == Raza.NO_MUERTO || raza == Raza.GNOMO)
				return false;
			break;
		case PALADIN:
			if (raza == Raza.NO_MUERTO || raza == Raza.ELFO_NOCHE)
				return false;
			break;
		case PICARO:
			if (raza == Raza.TROLL || raza == Raza.DRAENEI)
				return false;
			break;
		default:
			break;
		}
		return true;
	}

	/**
	 * Te devuelve un String con informaci�n acerca de los jugadores.
	 * 
	 * @return cadena con informaci�n de los jugadores.
	 */
	public static String pasarString() {
		return partida.toString();
	}

	/**
	 * Empieza una partida
	 */
	public static void nuevaPartida() {
		partida = new Jugadores();
		setModificado(false);
		Fichero.setFile(null);
	}

	/**
	 * Intenta escribir una partida
	 * 
	 * @param file
	 * @throws IOException
	 */
	public static void escribir(File file) throws IOException {
		Fichero.escribir(file, partida);
		Fichero.setFile(file);
		setModificado(false);
	}

	/**
	 * Intenta leer una partida
	 * 
	 * @param file
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static void leer(File file) throws ClassNotFoundException, IOException {
		partida = Fichero.leer(file);
		Fichero.setFile(file);
	}

	/**
	 * Comprueba si existe una partida.
	 * 
	 * @param file
	 * @return true en caso de que exista una partida.
	 */
	public static boolean exist(File file) {
		return Fichero.exist(file);
	}

	/**
	 * Modifica el campo File
	 * 
	 * @param file
	 */
	public static void setFile(File file) {
		Fichero.setFile(file);
	}

	/**
	 * Obtienes el campo File
	 * 
	 * @return File
	 */
	public static File getFile() {
		return Fichero.getFile();
	}
}
