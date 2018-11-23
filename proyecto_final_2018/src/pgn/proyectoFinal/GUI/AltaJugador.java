package pgn.proyectoFinal.GUI;

import javax.swing.JOptionPane;
import pgn.proyectoFinal.negocio.JugadorYaExistenteException;
import pgn.proyectoFinal.negocio.Partida;
import pgn.proyectoFinal.negocio.PatronNoValidoException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Ventana que vamos a usar para dar de alta a los jugadores.
 * 
 * @author Gallardo Pérez Marcos.
 *
 */
@SuppressWarnings("serial")
public class AltaJugador extends VentanaPadreJugadores {

	/**
	 * Create the dialog.
	 */
	public AltaJugador() {
		definirVentana();

		disenioOkButton();

		disenioCancelButton();
	}

	/**
	 * Cambia el titulo a Alta de Jugadores.
	 */
	private void definirVentana() {
		setTitle("Alta de Jugadores.");
	}

	/**
	 * Cambia el nombre del OkButton y le añade un evento al pulsar el boton que
	 * añade un jugador a la partida si es posible
	 */
	private void disenioOkButton() {
		okButton.setText("Añadir Jugador");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					compruebaIgualdadContrasenias();
					Partida.add(textField_Nombre.getText(), textField_Nombre_Usuario.getText(),
							textField_Contrasenia_2.getText());
					limpiar();
				} catch (JugadorYaExistenteException | PatronNoValidoException e) {
					JOptionPane.showMessageDialog(contentPanel, e.getMessage(), "¡ Error ! ",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	private void disenioCancelButton() {
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
	}

}
