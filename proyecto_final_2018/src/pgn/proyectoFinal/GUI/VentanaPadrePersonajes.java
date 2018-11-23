package pgn.proyectoFinal.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import pgn.proyectoFinal.negocio.Raza;
import pgn.proyectoFinal.negocio.Clase;
import pgn.proyectoFinal.negocio.Faccion;
import pgn.proyectoFinal.negocio.Jugador;
import pgn.proyectoFinal.negocio.Partida;
import pgn.proyectoFinal.negocio.PatronNoValidoException;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * Ventana padre de la que heredan todas las ventanas relacionadas con los personajes
 * 
 * @author Gallardo Pérez Marcos.
 *
 */
@SuppressWarnings("serial")
public class VentanaPadrePersonajes extends JDialog {

	protected final JPanel contentPanel = new JPanel();
	protected JLabel lblNombre;
	protected JTextField textField_NombrePJ;
	protected JLabel lblClase;
	protected JLabel lblFacccion;
	protected JComboBox<Object> comboBox_Faccion;
	protected JLabel lblRaza;
	protected JComboBox<Object> comboBox_Raza;
	protected JComboBox<Object> comboBox_Clase;
	protected JPanel buttonPane;
	protected JButton okButton;
	protected JButton cancelButton;


	/**
	 * Create the dialog.
	 */
	public VentanaPadrePersonajes() {
		disenioVentana();
		disenio_LblPersonaje();
		disenio_LblNombre();
		disenio_textField_NombrePJ();
		disenio_ComboBox_Raza();
		disenio_ComboBoxFaccion();
		disenio_LblFaccion();
		disenio_LblRaza();
		disenio_ButtonPane();
	}

	private void disenioVentana() {
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
	}

	private void disenio_LblPersonaje() {
		JLabel lblPersonaje = new JLabel("PERSONAJE:");
		lblPersonaje.setBounds(27, 24, 115, 14);
		contentPanel.add(lblPersonaje);
	}

	private void disenio_LblNombre() {
		lblNombre = new JLabel("NOMBRE: ");
		lblNombre.setBounds(37, 59, 85, 14);
		contentPanel.add(lblNombre);
	}

	private void disenio_textField_NombrePJ() {
		textField_NombrePJ = new JTextField();
		textField_NombrePJ.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				try {
					textField_NombrePJ.setText(Jugador.estandarizarNombre(textField_NombrePJ.getText()));
					Partida.comprobarUsuario(textField_NombrePJ.getText());
					textField_NombrePJ.setForeground(java.awt.Color.GREEN);
				} catch (PatronNoValidoException e) {
					textField_NombrePJ.setForeground(java.awt.Color.RED);
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				textField_NombrePJ.setForeground(java.awt.Color.BLACK);
			}
		});
		textField_NombrePJ.setBounds(179, 56, 192, 20);
		contentPanel.add(textField_NombrePJ);
		textField_NombrePJ.setColumns(10);
	}
	
	private void disenio_LblClase() {
		lblClase = new JLabel("CLASE:");
		lblClase.setBounds(37, 110, 105, 14);
		contentPanel.add(lblClase);
	}
	
	private void disenio_ComboxClase() {
		comboBox_Clase = new JComboBox<Object>();
		comboBox_Clase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox_Faccion.getSelectedItem()==null)
					return;
				comboBox_Faccion.setSelectedItem(comboBox_Faccion.getSelectedItem());
			}
		});
		comboBox_Clase.setModel(new DefaultComboBoxModel<Object>(Clase.values()));
		comboBox_Clase.setBounds(179, 106, 192, 22);
		contentPanel.add(comboBox_Clase);
		comboBox_Clase.setSelectedItem(null);
	}

	private void disenio_ComboBoxFaccion() {
		comboBox_Faccion = new JComboBox<Object>();
		comboBox_Faccion.setModel(new DefaultComboBoxModel<Object>(Faccion.values()));
		comboBox_Faccion.setBounds(37, 185, 137, 22);
		contentPanel.add(comboBox_Faccion);
		comboBox_Faccion.setSelectedItem(null);
		comboBox_Faccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox_Clase.getSelectedItem()==null)
					return;
				comboBox_Raza.setModel(new DefaultComboBoxModel<Object>(getModelo(comboBox_Faccion)));
				comboBox_Raza.setEnabled(true);
			}
		});
	}

	private void disenio_ComboBox_Raza() {
		comboBox_Raza = new JComboBox<Object>();
		comboBox_Raza.setModel(new DefaultComboBoxModel<Object>(Raza.values()));
		comboBox_Raza.setBounds(246, 185, 186, 22);
		contentPanel.add(comboBox_Raza);
		comboBox_Raza.setSelectedItem(null);
		comboBox_Raza.setEnabled(false);
	}

	private void disenio_LblFaccion() {
		lblFacccion = new JLabel("FACCCION:");
		lblFacccion.setBounds(37, 160, 105, 14);
		contentPanel.add(lblFacccion);
	}

	private void disenio_LblRaza() {
		lblRaza = new JLabel("RAZA");
		lblRaza.setBounds(246, 160, 105, 14);
		contentPanel.add(lblRaza);
		disenio_ComboxClase();
		disenio_LblClase();
	}

	
	private void disenio_ButtonPane() {
		buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		cancelButton = new JButton("Cancelar");
		cancelButton.setActionCommand("Cancelar");
		buttonPane.add(cancelButton);
	}

	protected void limpiar() {
		textField_NombrePJ.setText("");
		comboBox_Clase.setSelectedItem(null);
		comboBox_Faccion.setSelectedItem(null);
		comboBox_Raza.setSelectedItem(null);
		comboBox_Raza.setEnabled(false);
	}
	
	protected Object[] getModelo(JComboBox<Object> comboBoxFaccion) {
		Faccion faccion = (Faccion) comboBoxFaccion.getSelectedItem();
		Clase clase = (Clase) comboBox_Clase.getSelectedItem();
		ArrayList<Raza> razas = new ArrayList<Raza>();
		for (Raza raza : Raza.values())
			if (raza.getFaccion() == faccion && Partida.compruebaRaza(clase, raza)) {
				razas.add(raza);
			}
		return razas.toArray();
	}
}
