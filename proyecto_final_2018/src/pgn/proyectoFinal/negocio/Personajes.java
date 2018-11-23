package pgn.proyectoFinal.negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Clase envoltorio de ArrayList que gestiona a los personajes.
 * 
 * @author Gallardo Pérez Marcos
 *
 */
@SuppressWarnings("serial")
public class Personajes implements Serializable, Iterable<Personaje> {

	ArrayList<Personaje> personajes = new ArrayList<Personaje>();

	ArrayList<Personaje> getPersonajes() {
		return personajes;
	}

	void add(Personaje personaje) throws PersonajeYaExistenteException {
		if (getPersonajes().contains(personaje))
			throw new PersonajeYaExistenteException("Ya existe un personaje con ese nombre");
		getPersonajes().add(personaje);
	}

	boolean remove(String nombre) throws PatronNoValidoException {
		try {
			return getPersonajes().remove(new Mago(nombre, Raza.HUMANO));
		} catch (RazaNoValidaException e) {
		}
		return false;
	}

	Personaje getPersonaje(String nombre) throws PatronNoValidoException {
		try {
			return getPersonajes().get(getPersonajes().indexOf(new Mago(nombre, Raza.HUMANO)));
		} catch (RazaNoValidaException e) {

		}
		return null;
	}

	Personaje getPersonaje(int index) {
		return getPersonajes().get(index);
	}

	boolean contains(String nombrePersonaje) throws PatronNoValidoException {
		try {
			return personajes.contains(new Mago(nombrePersonaje, Raza.HUMANO));
		} catch (RazaNoValidaException e) {
			e.printStackTrace();
		}
		return false;
	}

	boolean contains(Personaje pj) {
		return personajes.contains(pj);
	}

	int size() {
		return getPersonajes().size();
	}

	public String toString() {
		String mj = "";
		for (Personaje personaje : getPersonajes()) {
			mj += "\n" + personaje.toString();
		}
		return mj;
	}

	boolean isEmpty() {
		return getPersonajes().isEmpty();
	}

	@Override
	public ListIterator<Personaje> iterator() {
		return getPersonajes().listIterator();
	}
}
