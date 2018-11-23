package pgn.proyectoFinal.GUI;

import pgn.proyectoFinal.negocio.Partida;
import pgn.proyectoFinal.negocio.PatronNoValidoException;
import pgn.utiles.PasaHtml;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JButton;
import javax.swing.JDialog;

/**
 * Ventana en la que se van a dar de baja los jugadores.
 * 
 * @author Gallardo Pérez Marcos.
 *
 */
@SuppressWarnings("serial")
public class BajaJugador extends VentanaPadreMostarJugadores {

	/**
	 * Create the dialog.
	 */
	public BajaJugador() {
		definirVentana();
	}

	private void definirVentana() {
		setTitle("Baja Jugadores");
		btnCrearPersonaje.setVisible(false);
		disenio_TextField_Nombre_Usuario();
		disenio_OkButton();
		disenio_CancelButton();
	}

	private void disenio_TextField_Nombre_Usuario() {
		textField_Nombre_Usuario.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				limpiar();
			}
		});
	}

	private void disenio_OkButton() {
		okButton.setText("Eliminar Jugador");

		JButton btnNewButton = new JButton("Baja Listando");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String contraseniaAdministracion = JOptionPane.showInputDialog(contentPanel,
						PasaHtml.dosSaltoLineaCentrado("Esta opcion solo esta disponible para el administrador",
								"Dame la contraseña de administracion."));
				if (contraseniaAdministracion == null)
					return;
				if (!contraseniaAdministracion.equals(VentanaInicial.CONTRASENIA_ADMIN)) {
					JOptionPane.showMessageDialog(contentPanel, "Contraseña erronea");
					return;
				}
				BajaJugadorListando baja = new BajaJugadorListando();
				baja.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				baja.setVisible(true);
			}
		});
		btnNewButton.setBounds(290, 208, 142, 23);
		contentPanel.add(btnNewButton);
		eventos_OkButton();
	}

	private void eventos_OkButton() {
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!buscar(textField_Nombre_Usuario.getText())
							|| JOptionPane.showConfirmDialog(contentPanel, "¿Estas seguro que desea eliminar?",
									"Eliminando... ", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION
							|| !comprobacionContraseña(Partida.getJugador(textField_Nombre_Usuario.getText())))
						return;

					if (Partida.remove(textField_Nombre_Usuario.getText())) {
						limpiar();
						VentanaInicial.actualizaPartida();
					}

				} catch (PatronNoValidoException e1) {
					JOptionPane.showMessageDialog(contentPanel, "¡El patrón no es válido!");
				}
			}
		});
	}

	private void disenio_CancelButton() {
		eventos_CancelButton();
	}

	private void eventos_CancelButton() {
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
	}

}
