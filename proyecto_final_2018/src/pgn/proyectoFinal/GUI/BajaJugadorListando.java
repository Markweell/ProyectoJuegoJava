package pgn.proyectoFinal.GUI;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import pgn.proyectoFinal.negocio.Partida;
import pgn.proyectoFinal.negocio.PatronNoValidoException;
import pgn.utiles.PasaHtml;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Ventana en la que se van a dar de baja jugadores de una forma más rápida.
 * 
 * @author Gallardo Pérez Marcos.
 *
 */
@SuppressWarnings("serial")
public class BajaJugadorListando extends MostrarJugadores {

	/**
	 * Create the dialog.
	 */
	public BajaJugadorListando() { 
		textField_Contrasenia.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					textField_Contrasenia
							.setText(Partida.getJugador(textField_Nombre_Usuario.getText()).getContrasenia());
				} catch (PatronNoValidoException e1) {
				}
			}
		});

		disenioVentana();
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jugadoresIterator.remove();
				VentanaInicial.actualizaPartida();
				try {
					if (jugadoresIterator.nextIndex() == 0 || jugadoresIterator.previousIndex() == 0) {
						if (jugadoresIterator.hasNext()) {
							buscar(jugadoresIterator.next().getUsuario());
							movDerecha = true;
						}
					}
					if (jugadoresIterator.hasPrevious()) {
						buscar(jugadoresIterator.previous().getUsuario());
						movDerecha = false;
					} else if (jugadoresIterator.hasNext()) {
						buscar(jugadoresIterator.next().getUsuario());
						movDerecha = true;
					}
				} catch (PatronNoValidoException e) {
				}
				comprobacionIterador();
				if (Partida.size() == 1) {
					okButton.setEnabled(false);
					cancelButton.setEnabled(false);
				}
				if (Partida.size() == 0) {
					JOptionPane.showMessageDialog(contentPanel,
							PasaHtml.dosSaltoLineaCentrado("Has eliminado el último jugador.", "No quedan jugadores."));
					dispose();
					VentanaInicial.bajaJugador.dispose();
				}
			}
		});
		btnBorrar.setBounds(331, 212, 101, 23);
		contentPanel.add(btnBorrar);
	}

	private void disenioVentana() {
		setTitle("Baja jugador");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		btnCrearPersonaje.setVisible(false);
	}
}
