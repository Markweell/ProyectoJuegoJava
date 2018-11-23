package pgn.proyectoFinal.negocio;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ListIterator;
import java.util.regex.Pattern;

import pgn.utiles.PasaHtml;

/**
 * Clase que gestiona y guarda los campos del jugador
 * 
 * @author Gallardo Pérez Marcos
 *
 */
@SuppressWarnings("serial")
public class Jugador implements Serializable {

	/**
	 * Patrón que debe cumplir el nombre
	 */
	private static final String PATRON_NOMBRE = "[(A-Z)]{1}[(a-z)]{2,9}";
	/**
	 * Patrón que debe cumplir el usuario
	 */
	private static final String PATRON_USUARIO = "[(A-Z)]{1}[(a-z)]{2,9}";
	/**
	 * Patrón que debe cumplir la contraseña
	 */
	private static final String PATRON_CONTRASENIA = "^.*(?=.{8,})(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$)(?=.*[@#!$%^&+=]).*$";
	/**
	 * Nombre del jugador
	 */
	private String Nombre;
	/**
	 * Fecha en la que el jugador se inscribe
	 */
	private LocalDate fechaInscripcion;
	/**
	 * Nombre del usuario
	 */
	private String Usuario;
	/**
	 * Contraseña del jugador
	 */
	private String Contrasenia;
	/**
	 * Personaje seleccionado. Cuando este jugador se enfrente a otro, se enfrentará
	 * con este personaje
	 */
	private Personaje personajeActual;

	/**
	 * Personajes que posee el jugador.
	 */
	Personajes personajes = new Personajes();

	/**
	 * Patrón que debe cumplir la contraseña
	 */
	private static Pattern patronContrasenia = Pattern.compile(PATRON_CONTRASENIA);

	/**
	 * Patrón que debe cumplir el nombre
	 */
	private static Pattern patronNombre = Pattern.compile(PATRON_NOMBRE);

	/**
	 * Patrón que debe cumplir el usuario
	 */
	private static Pattern patronUsuario = Pattern.compile(PATRON_USUARIO);

	/**
	 * Constructor del jugador
	 * 
	 * @param nombre
	 *            oficial del jugador, debe contener al menos tres letras, y un
	 *            máximo de 10.
	 * @param nombreUsuario,
	 *            sirve para identificarte dentro del juego, debe contener al menos
	 *            tres letras, y un máximo de 10
	 * @param contrasenia,
	 *            para que solo tu puedas manejar tus personajes, debe contener al
	 *            menos 8 cifras, una minuscula, una mayuscula, un digito y un
	 *            caracter especial
	 * @throws PatronNoValidoException,
	 *             Salta cuando no se cumple el patrón del nombre, nombre de usuario
	 *             o de la contraseña
	 */
	public Jugador(String nombre, String nombreUsuario, String contrasenia) throws PatronNoValidoException {
		setContrasenia(contrasenia);
		setNombre(nombre);
		setUsuario(nombreUsuario);
		setFechaInscripcion(LocalDate.now());
	}

	/**
	 * Devuelve el nombre del jugador
	 * 
	 * @return
	 */
	public String getNombre() {
		return Nombre;
	}

	/**
	 * Permite cambiar el nombre del jugador
	 * 
	 * @param nombre
	 * @throws PatronNoValidoException,
	 *             salta cuando el nombre no cumple el patrón
	 */
	void setNombre(String nombre) throws PatronNoValidoException {
		nombre = estandarizarNombre(nombre);
		comprobarNombre(nombre);
		Nombre = nombre;
	}

	/**
	 * Comprueba que el nombre es válido
	 * 
	 * @param nombre
	 * @throws PatronNoValidoException,salta
	 *             cuando el nombre no cumple el patrón
	 */
	static void comprobarNombre(String nombre) throws PatronNoValidoException {
		if (!patronNombre.matcher(nombre).matches())
			throw new PatronNoValidoException("El nombre no cumple el patrón indicado");

	}

	/**
	 * Estandariza el nombre, poniendo la primera letra en mayuscula y las demás en
	 * minuscula
	 * 
	 * @param nombre
	 * @return nombre estandarizado.
	 */
	public static String estandarizarNombre(String nombre) {
		nombre = nombre.trim();
		try {
			return nombre.toUpperCase().charAt(0) + "" + nombre.toLowerCase().subSequence(1, nombre.length());
		} catch (StringIndexOutOfBoundsException e) {
			return "";
		}
	}

	/**
	 * Devuelve la fecha de inscripcion del jugador
	 * 
	 * @return fechaInscripcion
	 */
	public LocalDate getFechaInscripcion() {
		return fechaInscripcion;
	}

	/**
	 * Cambia la fecha de inscripcion del jugador
	 * 
	 * @param fechaInscripcion
	 */
	void setFechaInscripcion(LocalDate fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	/**
	 * Devuelve el campo usuario, que guarda una cadena con el nombre del usuario
	 * 
	 * @return Usuario
	 */
	public String getUsuario() {
		return Usuario;
	}

	/**
	 * Cambia el campo usuario, que guarda una cadena con el nombre del usuario
	 * 
	 * @param usuario
	 * @throws PatronNoValidoException,
	 *             salta si el nombre de usuario no es válido
	 */
	void setUsuario(String usuario) throws PatronNoValidoException {
		usuario = estandarizarNombre(usuario);
		comprobarUsuario(usuario);
		Usuario = usuario;
	}

	/**
	 * Comprueba que el nombre de usuario es válido
	 * 
	 * @param usuario
	 * @throws PatronNoValidoException,
	 *             salta si el nombre de usuario no es válido
	 */
	static void comprobarUsuario(String usuario) throws PatronNoValidoException {
		if (!patronUsuario.matcher(usuario).matches())
			throw new PatronNoValidoException("El nombre de usuario no cumple el patrón indicado");

	}

	/**
	 * Obtienes la contraseña del jugador
	 * 
	 * @return contrasenia
	 */
	public String getContrasenia() {
		return Contrasenia;
	}

	/**
	 * Permite cambiar la contraseña del jugador
	 * 
	 * @param contrasenia
	 * @throws PatronNoValidoException,
	 *             salta si la contraseña no es válida
	 */
	public void setContrasenia(String contrasenia) throws PatronNoValidoException {
		comprobarContrasenia(contrasenia);
		Contrasenia = contrasenia;
	}

	/**
	 * Comprueba que la contraseña sea válida
	 * 
	 * @param contrasenia
	 * @throws PatronNoValidoException,
	 *             salta si la contraseña no es valida.
	 */
	static void comprobarContrasenia(String contrasenia) throws PatronNoValidoException {
		if (!patronContrasenia.matcher(contrasenia).matches())
			throw new PatronNoValidoException(PasaHtml.tresSaltoLineaCentrado("La contraseña no es válida",
					"Debe contener al menos 8 digitos, al menos una minuscula, una mayuscula, un dígito,",
					" un caracter especial, y no puede tener espacios en blanco."));
	}

	/**
	 * Devuelve el campo personajeActual, que guarda al personaje con el que este
	 * jugador desearía luchar en caso de que hubiese un combate
	 * 
	 * @return personajeActual
	 */
	public Personaje getPersonajeActual() {
		if (!personajes.contains(personajeActual)) {
			if (personajes.isEmpty())
				return null;
			personajeActual = personajes.getPersonaje(0);
		}
		return personajeActual;
	}

	/**
	 * Cambia el personaje actual del jugador
	 * 
	 * @param personajeActual
	 */
	public void setPersonajeActual(Personaje personajeActual) {
		if (personajes.contains(personajeActual))
			this.personajeActual = personajeActual;
	}

	/**
	 * Añade un nuevo personaje a los personajes que ya tiene el jugador
	 * 
	 * @param personaje
	 * @throws PersonajeYaExistenteException,
	 *             salta si ya existe un personaje con el mismo nombre
	 */
	public void anadirPersonaje(Personaje personaje) throws PersonajeYaExistenteException {
		personajes.add(personaje);
		setPersonajeActual(personaje);
		Partida.setModificado(true);
	}

	/**
	 * Intenta eliminar un personaje de de los que tiene el jugador.
	 * 
	 * @param nombre
	 * @return true en caso de que lo halla podido eliminar, false en caso contrario
	 * @throws PatronNoValidoException,
	 *             salta si el nombre introducido no cumple el patrón del nombre de
	 *             personaje
	 */
	public boolean eliminarPersonaje(String nombre) throws PatronNoValidoException {
		if (personajes.remove(nombre)) {
			Partida.setModificado(true);
			return true;
		}
		return false;
	}

	/**
	 * Envoltorio del método contains de arrayList, que devuelve si el personaje
	 * está o no dentro del arrayList
	 * 
	 * @param nombrePersonaje
	 * @return true en caso de que esté, false en caso contrario
	 * @throws PatronNoValidoException,
	 *             salta si el nombre introducido no cumple el patrón del nombre de
	 *             personaje
	 */
	public boolean contains(String nombrePersonaje) throws PatronNoValidoException {
		return personajes.contains(nombrePersonaje);
	}

	/**
	 * Devuelve un iterador con todos los personajes que posee el jugador
	 * 
	 * @return
	 */
	public ListIterator<Personaje> getIteratorPersonajes() {
		return personajes.iterator();
	}

	/**
	 * Te dice si el jugador tiene personajes o no.
	 * 
	 * @return true en caso de que no posea personajes, false en caso de que si.
	 */
	public boolean personajesIsEmpty() {
		return personajes.isEmpty();
	}

	/**
	 * Te dice el numero de personajes que tiene el jugador
	 * 
	 * @return numero de personajes
	 */
	public int size() {
		return personajes.size();
	}

	/**
	 * Devuelve un personaje del jugador
	 * 
	 * @param nombre
	 * @return Personaje
	 * @throws PatronNoValidoException,
	 *             salta si el nombre no cumple con el patrón de personaje
	 */
	public Personaje getPersonaje(String nombre) throws PatronNoValidoException {
		return personajes.getPersonaje(nombre);
	}

	/**
	 * Devuelve una cadena con los campos del jugador
	 * 
	 * @return cadena con los campos de jugador
	 */
	public String toString() {
		return "El personaje: " + getNombre() + " con nombre de usuario: " + getUsuario() + " con contraseña : "
				+ getContrasenia() + " se incribió el dia: " + getFechaInscripcion() + " y su "
				+ "personaje actual es : " + getPersonajeActual();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Usuario == null) ? 0 : Usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jugador other = (Jugador) obj;
		if (Usuario == null) {
			if (other.Usuario != null)
				return false;
		} else if (!Usuario.equals(other.Usuario))
			return false;
		return true;
	}

}
