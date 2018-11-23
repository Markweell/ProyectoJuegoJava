package pgn.proyectoFinal.GUI;

import pgn.proyectoFinal.negocio.Jugador;
import pgn.proyectoFinal.negocio.PatronNoValidoException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Ventana en la que se van a seleccionar a los personajes
 * 
 * @author Gallardo Pérez Marcos.
 *
 */
@SuppressWarnings("serial")
public class VentanaSeleccionPersonaje extends MostarPersonajes {

	/**
	 * Create the dialog.
	 */
	public VentanaSeleccionPersonaje(Jugador jugador) {
		super(jugador);
		JButton btnNewButton = new JButton("Seleccionar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (jugador.equals(VentanaInicial.getJugador1())) {
					try {
						VentanaInicial.getJugador1()
								.setPersonajeActual(jugador.getPersonaje(textField_NombrePJ.getText()));
					} catch (PatronNoValidoException e1) {
					}
				} else if (jugador.equals(VentanaInicial.getJugador2())) {
					try {
						VentanaInicial.getJugador2()
								.setPersonajeActual(jugador.getPersonaje(textField_NombrePJ.getText()));
					} catch (PatronNoValidoException e1) {

					}
				}
				VentanaInicial.actualizaPartida();
				dispose();
			}
		});
		btnNewButton.setBounds(568, 340, 174, 23);
		contentPanel.add(btnNewButton);

	}
}
