package pgn.proyectoFinal.GUI;

import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import pgn.proyectoFinal.negocio.Brujo;
import pgn.proyectoFinal.negocio.CaballeroDeLaMuerte;
import pgn.proyectoFinal.negocio.Clase;
import pgn.proyectoFinal.negocio.Guerrero;
import pgn.proyectoFinal.negocio.Jugador;
import pgn.proyectoFinal.negocio.Mago;
import pgn.proyectoFinal.negocio.Paladin;
import pgn.proyectoFinal.negocio.PatronNoValidoException;
import pgn.proyectoFinal.negocio.PersonajeYaExistenteException;
import pgn.proyectoFinal.negocio.Picaro;
import pgn.proyectoFinal.negocio.Raza;
import pgn.proyectoFinal.negocio.RazaNoValidaException;

import java.awt.event.ActionEvent;

/**
 * Ventana que se va a usar para dar de alta los personajes
 * 
 * @author Gallardo Pérez Marcos.
 *
 */
@SuppressWarnings("serial")
public class AltaPersonaje extends VentanaPadrePersonajes {

	String nombrePJ;
	Clase clase;
	Raza raza;

	/**
	 * Create the dialog.
	 */
	public AltaPersonaje(Jugador jugador) {
		disenioVentana();
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_NombrePJ.getText() == null || comboBox_Clase.getSelectedItem() == null
						|| (Raza) comboBox_Raza.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(contentPanel, "Rellena todos los campos", "¡ Error ! ",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				nombrePJ = textField_NombrePJ.getText();
				clase = (Clase) comboBox_Clase.getSelectedItem();
				raza = (Raza) comboBox_Raza.getSelectedItem();

				try {
					switch (clase) {
					case MAGO:
						Mago mago = new Mago(nombrePJ, raza);
						jugador.anadirPersonaje(mago);
						break;
					case BRUJO:
						jugador.anadirPersonaje(new Brujo(nombrePJ, raza));
						break;
					case CABALLERO_DE_LA_MUERTE:
						jugador.anadirPersonaje(new CaballeroDeLaMuerte(nombrePJ, raza));
						break;
					case GUERRERO:
						jugador.anadirPersonaje(new Guerrero(nombrePJ, raza));
						break;
					case PALADIN:
						jugador.anadirPersonaje(new Paladin(nombrePJ, raza));
						break;
					case PICARO:
						jugador.anadirPersonaje(new Picaro(nombrePJ, raza));
						break;
					}
					limpiar();
					VentanaPadreMostarJugadores.lbl_NPersonajes.setText(String.valueOf(jugador.size()));
					VentanaInicial.actualizaPartida();

				} catch (PersonajeYaExistenteException | RazaNoValidaException | PatronNoValidoException e1) {
					JOptionPane.showMessageDialog(contentPanel, e1.getMessage(), "¡ Error ! ",
							JOptionPane.ERROR_MESSAGE);
				} catch (NullPointerException e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(contentPanel, "Selecciona un Jugador", "¡ Error ! ",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	private void disenioVentana() {
		setTitle("Alta Personaje");
		okButton.setText("Añadir Personaje");
	}
}
