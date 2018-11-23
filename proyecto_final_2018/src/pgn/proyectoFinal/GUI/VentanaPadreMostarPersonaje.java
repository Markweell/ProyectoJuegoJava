package pgn.proyectoFinal.GUI;

import pgn.proyectoFinal.negocio.Brujo;
import pgn.proyectoFinal.negocio.CaballeroDeLaMuerte;
import pgn.proyectoFinal.negocio.Clase;
import pgn.proyectoFinal.negocio.Guerrero;
import pgn.proyectoFinal.negocio.Jugador;
import pgn.proyectoFinal.negocio.Mago;
import pgn.proyectoFinal.negocio.Paladin;
import pgn.proyectoFinal.negocio.PatronNoValidoException;
import pgn.proyectoFinal.negocio.Personaje;
import pgn.proyectoFinal.negocio.Picaro;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

/**
 * Ventana que hereda de la ventana padre de personajes y de la que van a heredar
 * las bajas y los mostrar personajes
 * 
 * @author Gallardo Pérez Marcos.
 *
 */
@SuppressWarnings("serial")
public class VentanaPadreMostarPersonaje extends VentanaPadrePersonajes {

	protected JLabel lblNivel;
	protected JLabel lbl_MuestraNivel;
	protected JLabel lblVida;
	protected JLabel lbl_MuestraVida;
	protected JLabel lblDefensa;
	protected JLabel lbl_MuestraDefensa;
	protected JLabel lblGolpeCritico;
	private JLabel lbl_MuestraGolpCrit;
	protected JLabel lblVelocidad;
	protected JLabel lbl_MuestraVelocidad;
	protected JLabel lblExperiencia;
	protected JLabel lblConsumible;
	protected JLabel label_MuestraConsumible;
	protected JLabel lbl_PoderPasivo;
	protected JLabel label_MuestraPoderPasivo;
	protected JProgressBar progressBar_Exp;
	protected JLabel lbl_Extra;
	protected JLabel lbl_MuestraExtra;


	/**
	 * Create the dialog.
	 */
	public VentanaPadreMostarPersonaje() {
		disenio_Ventana();

		disenio_Nombre();

		disenio_Raza();

		disenio_Faccion();

		disenio_Clase();

		disenio_Nivel();

		disenio_Vida();

		disenio_Defensa();

		disenio_GolpeCritico();

		disenio_Velocidad();

		disenio_Experiencia();

		disenio_Consumible();

		disenio_PoderPasivo();
		
		disenio_lblExtra();
	}

	private void disenio_Ventana() {
		setBounds(100, 100, 758, 432);
	}

	private void disenio_Clase() {
		comboBox_Clase.setLocation(105, 107);
		comboBox_Clase.setEnabled(false);
	}

	private void disenio_Nombre() {
		lblNombre.setLocation(37, 60);
		textField_NombrePJ.setLocation(105, 57);
	}

	private void disenio_Faccion() {
		lblFacccion.setLocation(345, 60);
		comboBox_Faccion.setLocation(475, 57);
		comboBox_Faccion.setEnabled(false);
	}

	private void disenio_Raza() {
		lblRaza.setLocation(345, 110);
		comboBox_Raza.setLocation(452, 107);
		comboBox_Raza.setEnabled(false);
	}

	private void disenio_Nivel() {
		lblNivel = new JLabel("NIVEL:");
		lblNivel.setBounds(37, 160, 46, 14);
		contentPanel.add(lblNivel);

		lbl_MuestraNivel = new JLabel("-");
		lbl_MuestraNivel.setBounds(213, 160, 67, 14);
		contentPanel.add(lbl_MuestraNivel);
	}

	private void disenio_Vida() {
		lblVida = new JLabel("VIDA");
		lblVida.setBounds(37, 199, 46, 14);
		contentPanel.add(lblVida);

		lbl_MuestraVida = new JLabel("-");
		lbl_MuestraVida.setBounds(213, 199, 67, 14);
		contentPanel.add(lbl_MuestraVida);
	}

	private void disenio_Defensa() {
		lblDefensa = new JLabel("DEFENSA");
		lblDefensa.setBounds(37, 240, 105, 14);
		contentPanel.add(lblDefensa);

		lbl_MuestraDefensa = new JLabel("-");
		lbl_MuestraDefensa.setBounds(213, 240, 67, 14);
		contentPanel.add(lbl_MuestraDefensa);
	}

	private void disenio_GolpeCritico() {
		lblGolpeCritico = new JLabel("GOLPE CRITICO");
		lblGolpeCritico.setBounds(37, 280, 105, 14);
		contentPanel.add(lblGolpeCritico);

		lbl_MuestraGolpCrit = new JLabel("-");
		lbl_MuestraGolpCrit.setBounds(213, 280, 67, 14);
		contentPanel.add(lbl_MuestraGolpCrit);
	}

	private void disenio_Velocidad() {
		lblVelocidad = new JLabel("VELOCIDAD");
		lblVelocidad.setBounds(37, 320, 105, 14);
		contentPanel.add(lblVelocidad);

		lbl_MuestraVelocidad = new JLabel("-");
		lbl_MuestraVelocidad.setBounds(213, 320, 67, 14);
		contentPanel.add(lbl_MuestraVelocidad);
	}

	private void disenio_Experiencia() {
		lblExperiencia = new JLabel("EXPERIENCIA:");
		lblExperiencia.setBounds(345, 160, 129, 14);
		contentPanel.add(lblExperiencia);

		progressBar_Exp = new JProgressBar();
		progressBar_Exp.setStringPainted(true);
		progressBar_Exp.setBounds(484, 160, 199, 16);
		contentPanel.add(progressBar_Exp);
	}

	private void disenio_Consumible() {
		lblConsumible = new JLabel("MANA");
		lblConsumible.setBounds(345, 240, 129, 14);
		contentPanel.add(lblConsumible);

		label_MuestraConsumible = new JLabel("-");
		label_MuestraConsumible.setBounds(521, 240, 67, 14);
		contentPanel.add(label_MuestraConsumible);
	}

	private void disenio_PoderPasivo() {
		lbl_PoderPasivo = new JLabel("INTELECTO");
		lbl_PoderPasivo.setBounds(345, 199, 129, 14);
		contentPanel.add(lbl_PoderPasivo);

		label_MuestraPoderPasivo = new JLabel("-");
		label_MuestraPoderPasivo.setBounds(521, 199, 67, 14);
		contentPanel.add(label_MuestraPoderPasivo);
	}

	private void disenio_lblExtra() {
		lbl_Extra = new JLabel("");
		lbl_Extra.setBounds(345, 280, 166, 14);
		contentPanel.add(lbl_Extra);
		
		lbl_MuestraExtra = new JLabel("");
		lbl_MuestraExtra.setBounds(521, 280, 67, 14);
		contentPanel.add(lbl_MuestraExtra);
	}

	protected boolean buscar(Jugador jugador, String nombrePersonaje) throws PatronNoValidoException {
		if (!jugador.contains(nombrePersonaje)) {
			JOptionPane.showMessageDialog(contentPanel, "¡Este jugador no existe!");
			return false;
		}
		Personaje pj = jugador.getPersonaje(nombrePersonaje);
		textField_NombrePJ.setText(pj.getNombre());
		lbl_MuestraNivel.setText(String.valueOf((int)pj.getLvl()));
		lbl_MuestraVida.setText(String.valueOf(Math.round(pj.getVidaMaxima())));
		lbl_MuestraDefensa.setText(String.valueOf((int)pj.getDefensa()));
		lbl_MuestraVelocidad.setText(String.valueOf((int)pj.getVelocidad()));
		lbl_MuestraGolpCrit.setText(String.valueOf((int)pj.getProbGolpeCritico()));
		progressBar_Exp.setMaximum((int)pj.getExpMax());
		progressBar_Exp.setValue((int)pj.getExp());
		lbl_Extra.setText("");
		lbl_MuestraExtra.setText("");
		if (pj instanceof Mago) { 
			comboBox_Clase.setSelectedItem(Clase.MAGO);
			lbl_PoderPasivo.setText("INTELECTO");
			label_MuestraPoderPasivo.setText(String.valueOf((int)((Mago) pj).getIntelecto()));
			lblConsumible.setText("MANA");
			label_MuestraConsumible.setText(String.valueOf((int)((Mago)pj).getManaMaximo()));		
		} else if (pj instanceof Guerrero) {
			comboBox_Clase.setSelectedItem(Clase.GUERRERO);
			lbl_PoderPasivo.setText("FUERZA");
			label_MuestraPoderPasivo.setText(String.valueOf((int)((Guerrero) pj).getFuerza()));
			lblConsumible.setText("IRA");
			label_MuestraConsumible.setText(String.valueOf((int)((Guerrero)pj).getIraMaxima()));
		} else if (pj instanceof CaballeroDeLaMuerte) {
			comboBox_Clase.setSelectedItem(Clase.CABALLERO_DE_LA_MUERTE);
			lbl_PoderPasivo.setText("FUERZA");
			label_MuestraPoderPasivo.setText(String.valueOf((int)((CaballeroDeLaMuerte) pj).getFuerza()));
			lblConsumible.setText("RUNAS");
			label_MuestraConsumible.setText(String.valueOf((int)((CaballeroDeLaMuerte)pj).getRunasMaximas()));
			lbl_Extra.setText("% GENERACION RUNAS");
			lbl_MuestraExtra.setText(String.valueOf(Math.round(((CaballeroDeLaMuerte)pj).getProb_GeneracionRunas())));
		} else if (pj instanceof Picaro) {
			comboBox_Clase.setSelectedItem(Clase.PICARO);
			lbl_PoderPasivo.setText("AGILIDAD");
			label_MuestraPoderPasivo.setText(String.valueOf((int)((Picaro) pj).getAgilidad()));
			lblConsumible.setText("ENERGIA");
			label_MuestraConsumible.setText(String.valueOf((int)((Picaro)pj).getEnergiaMaxima()));
		} else if (pj instanceof Brujo) {
			comboBox_Clase.setSelectedItem(Clase.BRUJO);
			lbl_PoderPasivo.setText("ENERGIA VIL");
			label_MuestraPoderPasivo.setText(String.valueOf((int)((Brujo) pj).getEnergiaVil()));
			lblConsumible.setText("");
			label_MuestraConsumible.setText("");
		} else if (pj instanceof Paladin) {
			comboBox_Clase.setSelectedItem(Clase.PALADIN);
			lbl_PoderPasivo.setText("FUERZA");
			label_MuestraPoderPasivo.setText(String.valueOf((int)((Paladin) pj).getFuerza()));
			lblConsumible.setText("MANA");
			label_MuestraConsumible.setText(String.valueOf((int)((Paladin)pj).getManaMaximo()));
		}
		comboBox_Faccion.setSelectedItem(pj.getFaccion());
		comboBox_Raza.setSelectedItem(pj.getRaza());
		comboBox_Raza.setEnabled(false);
		return true;
	}
	protected void limpiar() {
		comboBox_Faccion.setSelectedItem(null);
		comboBox_Raza.setSelectedItem(null);
		comboBox_Raza.setEnabled(false);
		textField_NombrePJ.setText("");
		lbl_MuestraNivel.setText("");
		lbl_MuestraVida.setText("");
		lbl_MuestraDefensa.setText("");
		lbl_MuestraVelocidad.setText("");
		lbl_MuestraGolpCrit.setText("");
		progressBar_Exp.setValue(0);
		lbl_Extra.setText("");
		lbl_MuestraExtra.setText("");
		comboBox_Clase.setSelectedItem(null);
		lbl_PoderPasivo.setText("");
		label_MuestraPoderPasivo.setText("");
		lblConsumible.setText("");
		label_MuestraConsumible.setText("");
	}
	
}
