package pgn.proyectoFinal.GUI;

import javax.swing.JDialog;
import javax.swing.JTextArea;
import java.awt.Font;

/**
 * Ventana que se va a usar para dar una ayuda básica a los jugadores
 * 
 * @author Gallardo Pérez Marcos.
 *
 */
@SuppressWarnings("serial")
public class Ayuda extends JDialog {
	private static Ayuda ayuda;

	
	/**
	 * Create the dialog.
	 */
	public Ayuda() {
		setTitle("Ayuda");
		setResizable(false);
		setModal(false);
		setBounds(100, 100, 499, 592);
		getContentPane().setLayout(null);

		JTextArea ayudaText = new JTextArea();
		ayudaText.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ayudaText.setBounds(10, 11, 473, 550);
		getContentPane().add(ayudaText);
		ayudaText.setText(
				"\tBienvenido al menu de ayuda de WOW FIGHTER"
				+"\n\n Barra de Menu: "
					+"\n  Archivo: "
					+ "\n   Crear Partida: Empieza una partida desde cero."
					+ "\n   Abrir Partida: Continua con una partida guardada."
					+ "\n   Guardar Partida: Guarda la partida."
					+ "\n   Guardar Como...: Guarda la partida dando la opción a cambiar el nombre."
					+ "\n   Salir: Sale de la ventana"
					+"\n  Jugador: "
					+ "\n   Añadir Jugador: Añade un jugador a la partida."
					+ "\n   Personaje:"
					+ "\n     Seleccionar Personaje: Selecciona un jugador para que sea uno de "
					+ "\n                            los participantes de la batalla."
					+ "\n     Alta Personaje: Añade un peronaje al jugador seleccionado."
					+ "\n     Baja Personaje: Borra un personaje al jugador seleccionado."
					+ "\n     Mostar Personajes: Muestra los personajes que posee el jugador."
					
					+ "\n   Eliminar Jugador: Borra un jugador de la partida."
					+ "\n   Mostrar Jugadores: Muestra todos los jugadores de la partida."
				+"\n\n Cuerpo: "
				+ "\n  Jugador1: Da una descripcion básica del jugador. El personaje actual "
				+ "\n          es el personaje con el que el jugador va a enfrentarse en el combate. "
				+ "\n           El boton cambiar permite cambiar el personaje actual, y el Cambiar Pj "
				+ "\n           permite elegir otro personaje"
				+"\n\n Combate: "
				+ "\n  El combate se desarrolla entre los dos personajes principales de cada uno de  "
				+ "\n  los jugadores.  Ataca el personaje que se le ilumina el icono. Se puede elegir "
				+ "\n  entre 4 ataques dependiendo de el consumible que poseas y gana el que mata a su"
				+ "\n  adversario.");
		ayudaText.setEditable(false);
	}
	public static Ayuda ayudaSingleton() {
		if (ayuda == null)
			ayuda = new Ayuda();
		return ayuda;

	}
}
