package pgn.proyectoFinal.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import pgn.proyectoFinal.negocio.Jugador;
import pgn.proyectoFinal.negocio.Partida;
import pgn.proyectoFinal.negocio.PatronNoValidoException;
import pgn.utiles.PasaHtml;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Ventana padre de la que heredan todas las ventanas relacionadas con los jugadores
 * 
 * @author Gallardo Pérez Marcos.
 *
 */
@SuppressWarnings("serial")
public class VentanaPadreJugadores extends JDialog {

	protected final JPanel contentPanel = new JPanel();
	protected JLabel lblJugador;
	protected JLabel lblNombre;
	protected JLabel lblNombre_Usuario;
	protected JLabel lblContrasea;
	protected JLabel lblRepitaContrasea;
	protected JTextField textField_Nombre;
	protected JTextField textField_Nombre_Usuario;
	protected JTextField textField_Contrasenia;
	protected JTextField textField_Contrasenia_2;
	protected JPanel buttonPane;
	protected JButton okButton;
	protected JButton cancelButton;

	/**
	 * Create the dialog.
	 */
	public VentanaPadreJugadores() {
		setBounds(100, 100, 450, 300);
		setModal(true);
		setResizable(false);
		disenioContentPane();
		disenioButtonPane();
	}

	private void disenioContentPane() {
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		disenioJLabel();
		disenioTextField();
	}

	private void disenioJLabel() {
		disenioLblJugador();
		disenioLblNombre();
		disenioLblNombre_Usuario();
		disenioLblContrasenia();
		disenioLblRepitaContrasenia();
	}

	private void disenioLblJugador() {
		lblJugador = new JLabel("DATOS JUGADOR:");
		lblJugador.setBounds(25, 25, 158, 14);
		contentPanel.add(lblJugador);
	}

	private void disenioLblNombre() {
		lblNombre = new JLabel("NOMBRE:");
		lblNombre.setBounds(67, 67, 59, 14);
		contentPanel.add(lblNombre);
	}

	private void disenioLblNombre_Usuario() {
		lblNombre_Usuario = new JLabel("NOMBRE USUARIO:");
		lblNombre_Usuario.setBounds(67, 97, 108, 14);
		contentPanel.add(lblNombre_Usuario);
	}

	private void disenioLblRepitaContrasenia() {
		lblRepitaContrasea = new JLabel("REPITA CONTRASE\u00D1A:");
		lblRepitaContrasea.setBounds(67, 157, 130, 14);
		contentPanel.add(lblRepitaContrasea);
	}

	private void disenioLblContrasenia() {
		lblContrasea = new JLabel("CONTRASE\u00D1A:");
		lblContrasea.setBounds(67, 127, 108, 14);
		contentPanel.add(lblContrasea);
	}

	private void disenioTextField() {
		disenioTextField_Nombre();
		disenioTextField_Nombre_Usuario();
		disenioTextField_Contrasenia();
		disenioTextField_Contrasenia_2();
	}

	private void disenioTextField_Nombre() {
		textField_Nombre = new JTextField();
		textField_Nombre.setToolTipText(
				PasaHtml.dosSaltoLineaCentrado("El nombre debe contener un mínimo de tres letras y un máximo de diez",
						"La primera letra debe ser mayúscula"));
		textField_Nombre.setBounds(234, 61, 130, 20);
		textField_Nombre.setColumns(10);
		textField_Nombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPanel.add(textField_Nombre);
		textField_Nombre.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				try {
					textField_Nombre.setText(Jugador.estandarizarNombre(textField_Nombre.getText()));
					Partida.comprobarNombre(textField_Nombre.getText());
					textField_Nombre.setForeground(java.awt.Color.GREEN);
				} catch (PatronNoValidoException e) {
					textField_Nombre.setForeground(java.awt.Color.RED);
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				textField_Nombre.setForeground(java.awt.Color.BLACK);
			}
		});
	}

	private void disenioTextField_Nombre_Usuario() {
		textField_Nombre_Usuario = new JTextField();
		textField_Nombre_Usuario.setBounds(234, 91, 130, 20);
		textField_Nombre_Usuario.setToolTipText(PasaHtml.dosSaltoLineaCentrado(
				"El nombre de usuario debe contener un mínimo de tres letras y un máximo de diez",
				"La primera letra debe ser mayúscula"));
		textField_Nombre_Usuario.setColumns(10);
		textField_Nombre_Usuario.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPanel.add(textField_Nombre_Usuario);
		textField_Nombre_Usuario.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					textField_Nombre_Usuario.setText(Jugador.estandarizarNombre(textField_Nombre_Usuario.getText()));
					Partida.comprobarUsuario(textField_Nombre_Usuario.getText());
					textField_Nombre_Usuario.setForeground(java.awt.Color.GREEN);
				} catch (PatronNoValidoException e1) {
					textField_Nombre_Usuario.setForeground(java.awt.Color.RED);
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				textField_Nombre_Usuario.setForeground(java.awt.Color.BLACK);
			}
		});
	}

	private void disenioTextField_Contrasenia() {
		textField_Contrasenia = new JTextField();
		textField_Contrasenia.setBounds(234, 121, 130, 20);
		textField_Contrasenia.setToolTipText(PasaHtml.dosSaltoLineaCentrado(
				"La contrasenia debe contener como minimo 8 letras, entre ellas debe haber una mayuscula",
				"una minuscula, un número y un caracter especial. No puede contener espacios"));
		textField_Contrasenia.setColumns(10);
		textField_Contrasenia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPanel.add(textField_Contrasenia);
		textField_Contrasenia.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					Partida.comprobarContrasenia(textField_Contrasenia.getText());
					textField_Contrasenia.setForeground(java.awt.Color.GREEN);
				} catch (PatronNoValidoException e1) {
					textField_Contrasenia.setForeground(java.awt.Color.RED);
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				textField_Contrasenia.setForeground(java.awt.Color.BLACK);
			}
		});
	}

	private void disenioTextField_Contrasenia_2() {
		textField_Contrasenia_2 = new JTextField();
		textField_Contrasenia_2.setBounds(234, 151, 130, 20);
		textField_Contrasenia_2.setColumns(10);
		textField_Contrasenia_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPanel.add(textField_Contrasenia_2);
		textField_Contrasenia_2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					compruebaIgualdadContrasenias();
					Partida.comprobarContrasenia(textField_Contrasenia_2.getText());
					textField_Contrasenia_2.setForeground(java.awt.Color.GREEN);
				} catch (PatronNoValidoException e1) {
					textField_Contrasenia_2.setForeground(java.awt.Color.RED);
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				textField_Contrasenia_2.setForeground(java.awt.Color.BLACK);
			}
		});
	}

	void compruebaIgualdadContrasenias() throws PatronNoValidoException {
		if (!textField_Contrasenia.getText().equals(textField_Contrasenia_2.getText()))
			throw new PatronNoValidoException("Las contraseñas no coinciden.");
	}

	private void disenioButtonPane() {
		buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		disenioOkbutton();
		disenioCancelButton();
	}

	private void disenioCancelButton() {
		cancelButton = new JButton("Cancelar");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}

	private void disenioOkbutton() {
		okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
	}

	protected void limpiar() {
		textField_Nombre.setText("");
		textField_Nombre_Usuario.setText("");
		textField_Contrasenia.setText("");
		textField_Contrasenia_2.setText("");
	}

	protected boolean buscar(String nombreUsuario) throws PatronNoValidoException {
		Jugador jugador = Partida.getJugador(nombreUsuario);
		if (!Partida.contains(jugador)) {
			JOptionPane.showMessageDialog(contentPanel, "¡Este jugador no existe!");
			return false;
		}
		textField_Nombre.setText(jugador.getNombre());
		textField_Nombre_Usuario.setText(jugador.getUsuario());
		textField_Contrasenia.setText("********");
		return true;
	}
}
