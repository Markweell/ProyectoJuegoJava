package pgn.proyectoFinal.GUI;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import pgn.proyectoFinal.negocio.Jugador;
import pgn.proyectoFinal.negocio.Partida;
import pgn.proyectoFinal.negocio.PatronNoValidoException;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Ventana que hereda de la ventana padre de jugadores y de la que van a heredar
 * las bajas y los mostrar jugadores
 * 
 * @author Gallardo Pérez Marcos.
 *
 */
@SuppressWarnings("serial")
public class VentanaPadreMostarJugadores extends VentanaPadreJugadores {

	JLabel lblPersonajesDeEste;
	static JLabel lbl_NPersonajes;
	JLabel lblPersonajePrincipal;
	JLabel lbl_PersonajePrincipal;
	JLabel lbl_FechaInicio;
	protected JButton btnCrearPersonaje;

	/**
	 * Create the dialog.
	 */
	public VentanaPadreMostarJugadores() {
		definirVentana();
	}

	private void definirVentana() {
		lblRepitaContrasea.setVisible(false);
		textField_Contrasenia_2.setVisible(false);

		lblNombre_Usuario.setLocation(67, 67);
		lblNombre.setLocation(67, 97);
		textField_Nombre_Usuario.setLocation(234, 61);
		textField_Nombre.setLocation(234, 91);

		textField_Nombre.setEnabled(false);
		textField_Contrasenia.setEnabled(false);

		okButton.setText("Eliminar Jugador");
		disenioLbl_NPersonajes();
		disenioLbl_PersonajePrincipal();
	}

	private void disenioLbl_NPersonajes() {
		lblPersonajesDeEste = new JLabel("Personajes de este jugador: ");
		lblPersonajesDeEste.setBounds(49, 182, 186, 14);
		contentPanel.add(lblPersonajesDeEste);

		lbl_NPersonajes = new JLabel("-");
		lbl_NPersonajes.setBounds(234, 182, 46, 14);
		contentPanel.add(lbl_NPersonajes);
	}

	private void disenioLbl_PersonajePrincipal() {
		lblPersonajePrincipal = new JLabel("Personaje principal: ");
		lblPersonajePrincipal.setBounds(49, 207, 134, 14);
		contentPanel.add(lblPersonajePrincipal);

		lbl_PersonajePrincipal = new JLabel("-");
		lbl_PersonajePrincipal.setBounds(234, 207, 149, 14);
		contentPanel.add(lbl_PersonajePrincipal);

		JLabel lblFechaDeInicio = new JLabel("Fecha de inicio: ");
		lblFechaDeInicio.setBounds(49, 157, 186, 14);
		contentPanel.add(lblFechaDeInicio);

		lbl_FechaInicio = new JLabel("-");
		lbl_FechaInicio.setBounds(234, 157, 149, 14);
		contentPanel.add(lbl_FechaInicio);

		btnCrearPersonaje = new JButton("Crear Personaje");
		btnCrearPersonaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					AltaPersonaje dialog = new AltaPersonaje(Partida.getJugador(textField_Nombre_Usuario.getText()));
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnCrearPersonaje.setBounds(290, 178, 142, 23);
		contentPanel.add(btnCrearPersonaje);

	}

	protected boolean buscar(String nombreUsuario) throws PatronNoValidoException {
		if (!super.buscar(nombreUsuario)) {
			return false;
		}
		Jugador jugador = Partida.getJugador(nombreUsuario);
		lbl_NPersonajes.setText(String.valueOf(jugador.size()));
		lbl_FechaInicio.setText(jugador.getFechaInscripcion().toString());
		if (jugador.getPersonajeActual() == null) {
			lbl_PersonajePrincipal.setText("-");
			return true;
		}
		lbl_PersonajePrincipal.setText(jugador.getPersonajeActual().getNombre());
		return true;
	}

	protected void limpiar() {
		super.limpiar();
		lbl_NPersonajes.setText("-");
		lbl_PersonajePrincipal.setText("-");
		lbl_FechaInicio.setText("-");
	}

	protected boolean comprobacionContraseña(Jugador jugador) {
		String contrasenia = JOptionPane.showInputDialog(contentPanel, "Comprobación contraseña",
				"¿Cual es tu contraseña?", JOptionPane.QUESTION_MESSAGE);
		if (contrasenia == null)
			return false;
		else if (!contrasenia.equals(jugador.getContrasenia())) {
			JOptionPane.showMessageDialog(contentPanel, "Contraseña erronea");
			return false;
		}
		return true;

	}
}
