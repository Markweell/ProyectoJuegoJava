package pgn.proyectoFinal.GUI;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import pgn.proyectoFinal.negocio.Jugador;
import pgn.proyectoFinal.negocio.Partida;
import pgn.proyectoFinal.negocio.PatronNoValidoException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Ventana en la que se van a seleccionar a los jugadores.
 * 
 * @author Gallardo Pérez Marcos.
 *
 */
@SuppressWarnings("serial")
public class VentanaSeleccionJugador extends MostrarJugadores {
	private JButton btnSeleccionar;
	private Jugador jugadorSeleccionado;

	/**
	 * Create the dialog.
	 */
	public VentanaSeleccionJugador(int numJugador)  {
		setTitle("Seleccion de jugadores");

		disenio_btnSeleccionar(numJugador);

	}

	private void disenio_btnSeleccionar(int numJugador) {
		btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.setBounds(324, 206, 108, 23);
		contentPanel.add(btnSeleccionar);
		eventoSeleccionar(numJugador);
	}

	private void eventoSeleccionar(int numJugador) {
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					jugadorSeleccionado = Partida.getJugador(textField_Nombre_Usuario.getText());
					if (jugadorSeleccionado.size() == 0) {
						JOptionPane.showMessageDialog(contentPanel, "Este jugador no tiene personajes");
						return;
					}
					if (numJugador == 1) {
						if (!comprobacionJugadorRepetido(VentanaInicial.getJugador2()))
							return;
						if (!comprobacionContraseña(jugadorSeleccionado))
							return;
						VentanaInicial.setJugador1(jugadorSeleccionado);
					} else if (numJugador == 2) {
						if (!comprobacionJugadorRepetido(VentanaInicial.getJugador1()))
							return;
						if (!comprobacionContraseña(jugadorSeleccionado))
							return;
						VentanaInicial.setJugador2(jugadorSeleccionado);
					}
					VentanaInicial.actualizaPartida();
					dispose();
				} catch (PatronNoValidoException e1) {

				}
			}
		});
	}

	private boolean comprobacionJugadorRepetido(Jugador jugador) {
		if (jugador == null) {
			return true;
		}
		if (jugador.equals(jugadorSeleccionado)) {
			JOptionPane.showMessageDialog(contentPanel, "No puedes luchar contigo mismo");
			return false;
		}
		return true;
	}

}
