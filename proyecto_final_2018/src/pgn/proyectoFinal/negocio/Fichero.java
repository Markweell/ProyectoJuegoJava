package pgn.proyectoFinal.negocio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Clase que gestiona el tema de los ficheros. La usamos para guardar o abrir el
 * arrayList de jugadores
 * 
 * @author Gallardo Pérez Marcos
 *
 */
public class Fichero {
	private static File file = null;

	static File getFile() {
		return file;
	}

	static void setFile(File file) {
		Fichero.file = file;
	}

	public static Jugadores leer(File file) throws ClassNotFoundException, IOException {
		file = comprobarExtension(file);
		try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
			return (Jugadores) in.readObject();
		}
	}

	public static void escribir(File file, Jugadores jugadores) throws IOException {
		file = comprobarExtension(file);
		try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
			out.writeObject(jugadores);
		}
	}

	public static boolean exist(File file) {
		file = comprobarExtension(file);
		return file.exists();
	}

	static File comprobarExtension(File file) {
		String nombreFichero = file.getPath();// probar getName()
		if (!nombreFichero.endsWith(".obj"))
			return new File(nombreFichero + ".obj");
		return file;
	}

}
