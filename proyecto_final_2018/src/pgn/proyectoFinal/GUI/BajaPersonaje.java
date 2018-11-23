package pgn.proyectoFinal.GUI;

import pgn.proyectoFinal.negocio.Jugador;
import pgn.proyectoFinal.negocio.PatronNoValidoException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JDialog;

/**
 * Ventana en la que se van a dar de baja personajes.
 * 
 * @author Gallardo Pérez Marcos.
 *
 */
@SuppressWarnings("serial")
public class BajaPersonaje extends VentanaPadreMostarPersonaje {

	/**
	 * Create the dialog.
	 */
	public BajaPersonaje(Jugador jugador) {
		setTitle("Baja Personaje");

		JButton btnNewButton = new JButton("Baja Listando");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BajaPersonajeListando dialog = new BajaPersonajeListando(jugador);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		btnNewButton.setBounds(562, 340, 180, 23);
		contentPanel.add(btnNewButton);
		disenio_TextField_NombrePJ();
		disenio_okButton(jugador);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	private void disenio_TextField_NombrePJ() {
		textField_NombrePJ.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				limpiar();
			}
		});
	}

	private void disenio_okButton(Jugador jugador) {
		okButton.setText("Eliminar Personaje");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!buscar(jugador, textField_NombrePJ.getText()))
						return;
					if (jugador.size() == 1) {
						JOptionPane.showMessageDialog(contentPanel, "¡No puedes eliminar tu último personaje!");
						return;
					}
					if (JOptionPane.showConfirmDialog(contentPanel, "¿Estas seguro que desea eliminar?",
							"Eliminando... ", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						if (jugador.eliminarPersonaje(textField_NombrePJ.getText())) {
							limpiar();
							VentanaInicial.actualizarJugador(jugador);
						}
					}

				} catch (PatronNoValidoException e1) {
					JOptionPane.showMessageDialog(contentPanel, "¡El patrón no es válido!");
				}
			}
		});
	}

}
