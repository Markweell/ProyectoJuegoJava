package pgn.proyectoFinal.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import pgn.proyectoFinal.negocio.Jugador;
import pgn.proyectoFinal.negocio.PatronNoValidoException;
import pgn.utiles.PasaHtml;

/**
 * Ventana en la que se van a dar de baja personajes de una forma más rápida.
 * 
 * @author Gallardo Pérez Marcos.
 *
 */
@SuppressWarnings("serial")
public class BajaPersonajeListando extends MostarPersonajes {

	/**
	 * Create the dialog.
	 */
	public BajaPersonajeListando(Jugador jugador) {
		super(jugador);
		setTitle("Baja Personaje");

		JButton btnEliminarPersonaje = new JButton("Eliminar Personaje");
		btnEliminarPersonaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				personajesIterator.remove();
				VentanaInicial.actualizarJugador(jugador);
				try {
					if (personajesIterator.nextIndex() == 0 || personajesIterator.previousIndex() == 0) {
						if (personajesIterator.hasNext()) {
							buscar(jugador, personajesIterator.next().getNombre());
							movDerecha = true;
						}
					}
					if (personajesIterator.hasPrevious()) {
						buscar(jugador, personajesIterator.previous().getNombre());
						movDerecha = false;
					} else if (personajesIterator.hasNext()) {
						buscar(jugador, personajesIterator.next().getNombre());
						movDerecha = true;
					}
				} catch (PatronNoValidoException e) {
				}
				comprobacionIterador();
				if (jugador.size() == 1) {
					okButton.setEnabled(false);
					cancelButton.setEnabled(false);
					JOptionPane.showMessageDialog(contentPanel, PasaHtml.dosSaltoLineaCentrado(
							"Solo te queda un solo personaje.", "No te puedes quedar sin personajes."));
					dispose();
					VentanaInicial.bajaPersonaje.dispose();
				}
			}
		});
		btnEliminarPersonaje.setBounds(567, 340, 175, 23);
		contentPanel.add(btnEliminarPersonaje);
	}

}
