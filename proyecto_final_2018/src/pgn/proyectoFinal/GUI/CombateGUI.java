package pgn.proyectoFinal.GUI;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import pgn.proyectoFinal.negocio.Ataque;
import pgn.proyectoFinal.negocio.Brujo;
import pgn.proyectoFinal.negocio.CaballeroDeLaMuerte;
import pgn.proyectoFinal.negocio.Clase;
import pgn.proyectoFinal.negocio.Combate;
import pgn.proyectoFinal.negocio.ConsumibleInsuficienteException;
import pgn.proyectoFinal.negocio.Guerrero;
import pgn.proyectoFinal.negocio.Jugador;
import pgn.proyectoFinal.negocio.Mago;
import pgn.proyectoFinal.negocio.Paladin;
import pgn.proyectoFinal.negocio.Personaje;
import pgn.proyectoFinal.negocio.Picaro;
import pgn.utiles.PasaHtml;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Ventana en la que se va a llevar a cabo el combate.
 * 
 * @author Gallardo Pérez Marcos.
 *
 */
@SuppressWarnings("serial")
public class CombateGUI extends JDialog {

	private final JPanel contentPanel = new JPanel();

	Personaje personaje1;
	Personaje personaje2;
	Combate combate;

	private JProgressBar progressBar_Consumible2;

	private JProgressBar progressBar_Vida2;

	private JProgressBar progressBar_Consumible1;

	private JProgressBar progressBar_Vida1;

	private JLabel lbl_MuestraAtacante;

	private JButton btnAtaBasico;

	private JButton btnAtaSecundario;

	private JButton btnAtaRestauratorio;

	private JButton btnAtaFinal;

	private JLabel lblNewLabel;
	private JLabel lblLucha1;
	private JLabel lblLucha2;

	/**
	 * Create the dialog.
	 */
	public CombateGUI(Jugador jugador1, Jugador jugador2) {
	
		combate = new Combate(jugador1.getPersonajeActual(), jugador2.getPersonajeActual());

		setPersonaje1(combate.getPj1());
		setPersonaje2(combate.getPj2());

		disenio_Ventana();

		disenio_Jugador1();

		disenio_Jugador2();

		disenio_Ataques();
		actualizacionPasoTurno();

	}

	public Personaje getPersonaje1() {
		return personaje1;
	}

	public void setPersonaje1(Personaje personaje1) {
		this.personaje1 = personaje1;
	}

	public Personaje getPersonaje2() {
		return personaje2;
	}

	public void setPersonaje2(Personaje personaje2) {
		this.personaje2 = personaje2;
	}

	private void disenio_Ventana() {
		setTitle("Combate\r\n");
		setBounds(100, 100, 758, 441);
		setModal(true);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				salir();
			}
		});
		contentPanel.setLayout(null);
	}

	private void disenio_Jugador1() {
		JLabel lblJugador1 = new JLabel("JUGADOR 1:");
		lblJugador1.setBounds(114, 38, 101, 14);
		contentPanel.add(lblJugador1);

		JLabel lbl_MuestraJugador1 = new JLabel("-");
		lbl_MuestraJugador1.setBounds(124, 63, 110, 14);
		contentPanel.add(lbl_MuestraJugador1);
		lbl_MuestraJugador1.setText(getPersonaje1().getNombre());

		JLabel lblVida1 = new JLabel("VIDA");
		lblVida1.setBounds(30, 11, 46, 14);
		contentPanel.add(lblVida1);

		progressBar_Vida1 = new JProgressBar();
		progressBar_Vida1.setForeground(Color.GREEN);
		progressBar_Vida1.setStringPainted(true);
		progressBar_Vida1.setBounds(80, 11, 150, 16);
		contentPanel.add(progressBar_Vida1);
		progressBar_Vida1.setMaximum((int) getPersonaje1().getVidaMaxima());
		progressBar_Vida1.setValue((int) getPersonaje1().getVidaMaxima());

		JLabel lblConsumible1 = new JLabel("Consumible");
		lblConsumible1.setBounds(10, 38, 70, 14);
		contentPanel.add(lblConsumible1);

		progressBar_Consumible1 = new JProgressBar();
		progressBar_Consumible1.setStringPainted(true);
		progressBar_Consumible1.setOrientation(SwingConstants.VERTICAL);
		progressBar_Consumible1.setBounds(10, 63, 47, 100);
		contentPanel.add(progressBar_Consumible1);
		asignarConsumible(lblConsumible1, progressBar_Consumible1, getPersonaje1());
		progressBar_Consumible1
				.setString(progressBar_Consumible1.getValue() + "/" + progressBar_Consumible1.getMaximum());
	}

	private void disenio_Jugador2() {
		JLabel lblJugador2 = new JLabel("JUGADOR 2:");
		lblJugador2.setBounds(503, 38, 101, 14);
		contentPanel.add(lblJugador2);

		JLabel label_MuestraJugador2 = new JLabel("-");
		label_MuestraJugador2.setBounds(513, 63, 110, 14);
		contentPanel.add(label_MuestraJugador2);
		label_MuestraJugador2.setText(getPersonaje2().getNombre());

		JLabel lblVida2 = new JLabel("VIDA");
		lblVida2.setBounds(509, 11, 46, 14);
		contentPanel.add(lblVida2);

		progressBar_Vida2 = new JProgressBar();
		progressBar_Vida2.setForeground(Color.GREEN);
		progressBar_Vida2.setStringPainted(true);
		progressBar_Vida2.setBounds(559, 11, 150, 16);
		contentPanel.add(progressBar_Vida2);
		progressBar_Vida2.setMaximum((int) getPersonaje2().getVidaMaxima());
		progressBar_Vida2.setValue((int) getPersonaje2().getVidaMaxima());

		JLabel labelConsumible2 = new JLabel("CONSUMIBLE");
		labelConsumible2.setBounds(645, 38, 95, 14);
		contentPanel.add(labelConsumible2);

		progressBar_Consumible2 = new JProgressBar();
		progressBar_Consumible2.setStringPainted(true);
		progressBar_Consumible2.setOrientation(SwingConstants.VERTICAL);
		progressBar_Consumible2.setBounds(645, 63, 47, 100);
		contentPanel.add(progressBar_Consumible2);
		asignarConsumible(labelConsumible2, progressBar_Consumible2, getPersonaje2());
		progressBar_Consumible2
				.setString(progressBar_Consumible2.getValue() + "/" + progressBar_Consumible2.getMaximum());
	}

	private void disenio_Ataques() {
		{
			JLabel lblPersonajeAtacante = new JLabel("ATACA ");
			lblPersonajeAtacante.setBounds(10, 228, 47, 14);
			contentPanel.add(lblPersonajeAtacante);
		}
		{
			lbl_MuestraAtacante = new JLabel("-");
			lbl_MuestraAtacante.setBounds(80, 228, 186, 14);
			contentPanel.add(lbl_MuestraAtacante);
			lbl_MuestraAtacante.setText(combate.getAtacante().getNombre());
		}

		JPanel panel = new JPanel();
		panel.setBounds(10, 253, 730, 155);
		contentPanel.add(panel);
		panel.setLayout(null);

		btnAtaBasico = new JButton("New button");
		btnAtaBasico.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
			}
		});
		btnAtaBasico.setMnemonic('Q');
		btnAtaBasico.setToolTipText("");
		btnAtaBasico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ataque(Ataque.ATAQUE_BASICO);
				} catch (ConsumibleInsuficienteException e) {
					JOptionPane.showMessageDialog(contentPanel, "No tienes consumible suficiente");
				}
			}
		});
		btnAtaBasico.setBounds(0, 0, 365, 75);
		btnAtaBasico.setText("ATAQUE BÁSICO");
		panel.add(btnAtaBasico);

		btnAtaSecundario = new JButton("New button");
		btnAtaSecundario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		btnAtaSecundario.setMnemonic('A');
		btnAtaSecundario.setToolTipText("");
		btnAtaSecundario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					ataque(Ataque.ATAQUE_SECUNDARIO);
				} catch (ConsumibleInsuficienteException e) {
					JOptionPane.showMessageDialog(contentPanel, "No tienes consumible suficiente");
				}
			}
		});
		btnAtaSecundario.setBounds(0, 76, 365, 75);
		btnAtaSecundario.setText("ATAQUE SECUNDARIO");
		panel.add(btnAtaSecundario);

		btnAtaRestauratorio = new JButton("New button");
		btnAtaRestauratorio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		btnAtaRestauratorio.setMnemonic('W');
		btnAtaRestauratorio.setToolTipText("");
		btnAtaRestauratorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ataque(Ataque.ATAQUE_RESTAURATORIO);
				} catch (ConsumibleInsuficienteException e) {
					JOptionPane.showMessageDialog(contentPanel, "No tienes consumible suficiente");
				}
			}
		});
		btnAtaRestauratorio.setBounds(365, 0, 365, 75);
		btnAtaRestauratorio.setText("ATAQUE RESTAURATORIO");
		panel.add(btnAtaRestauratorio);

		btnAtaFinal = new JButton("New button");
		btnAtaFinal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		btnAtaFinal.setMnemonic(KeyEvent.VK_S);
		btnAtaFinal.setToolTipText("");
		btnAtaFinal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					ataque(Ataque.ATAQUE_FINAL);
				} catch (ConsumibleInsuficienteException e) {
					JOptionPane.showMessageDialog(contentPanel, "No tienes consumible suficiente");
				}
			}
		});
		btnAtaFinal.setBounds(365, 76, 365, 75);
		btnAtaFinal.setText("ATAQUE FINAL");
		panel.add(btnAtaFinal);

		lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(222, 100, 301, 129);
		contentPanel.add(lblNewLabel);

		lblLucha1 = new JLabel("");
		lblLucha1.setBounds(80, 91, 86, 89);

		contentPanel.add(lblLucha1);

		lblLucha2 = new JLabel("");
		lblLucha2.setBounds(533, 91, 86, 89);
		contentPanel.add(lblLucha2);
		compruebaAtaquesDisponibles();
	}

	void asignarConsumible(JLabel lbl_Consumible, JProgressBar progressBar, Personaje pj) {
		switch (getClase(pj)) {
		case GUERRERO:
			progressBar.setMaximum((int) ((Guerrero) pj).getIraMaxima());
			progressBar.setValue((int) ((Guerrero) pj).getIra());
			progressBar.setForeground(Color.RED);
			lbl_Consumible.setText("IRA");
			break;
		case CABALLERO_DE_LA_MUERTE:
			progressBar.setMaximum((int) ((CaballeroDeLaMuerte) pj).getRunasMaximas());
			progressBar.setValue((int) ((CaballeroDeLaMuerte) pj).getRunas());
			progressBar.setForeground(Color.CYAN);
			lbl_Consumible.setText("RUNAS");
			break;
		case PICARO:
			progressBar.setMaximum((int) ((Picaro) pj).getEnergiaMaxima());
			progressBar.setValue((int) ((Picaro) pj).getEnergia());
			progressBar.setForeground(Color.YELLOW);
			lbl_Consumible.setText("ENERGIA");
			break;
		case BRUJO:
			progressBar.setVisible(false);
			lbl_Consumible.setText("");
			break;
		case PALADIN:
			progressBar.setMaximum((int) ((Paladin) pj).getManaMaximo());
			progressBar.setValue((int) ((Paladin) pj).getMana());
			progressBar.setForeground(Color.BLUE);
			lbl_Consumible.setText("MANA");
			break;
		default:
			progressBar.setMaximum((int) ((Mago) pj).getManaMaximo());
			progressBar.setValue((int) ((Mago) pj).getMana());
			progressBar.setForeground(Color.BLUE);
			lbl_Consumible.setText("MANA");
			break;
		}
	}

	Clase getClase(Personaje pj) {
		if (pj instanceof Mago)
			return Clase.MAGO;
		else if (pj instanceof Guerrero)
			return Clase.GUERRERO;
		else if (pj instanceof CaballeroDeLaMuerte)
			return Clase.CABALLERO_DE_LA_MUERTE;
		else if (pj instanceof Picaro)
			return Clase.PICARO;
		else if (pj instanceof Brujo)
			return Clase.BRUJO;
		else
			return Clase.PALADIN;

	}

	private void actualizacionPasoTurno() {
		progressBar_Vida1.setValue((int) combate.getPj1().getVida());
		progressBar_Vida2.setValue((int) combate.getPj2().getVida());
		actualizarConsumible(getPersonaje1(), progressBar_Consumible1);
		actualizarConsumible(getPersonaje2(), progressBar_Consumible2);
		progressBar_Consumible2
				.setString(progressBar_Consumible2.getValue() + "/" + progressBar_Consumible2.getMaximum());
		progressBar_Consumible1
				.setString(progressBar_Consumible1.getValue() + "/" + progressBar_Consumible1.getMaximum());
		lblNewLabel.setText(combate.getDescripcionAtaque());
		Personaje atacante = combate.getAtacante();

		if (getPersonaje1().equals(atacante)) {
			lblLucha1.setIcon(new ImageIcon("imagenes\\lucha1.png"));
			lblLucha2.setIcon(null);
			btnAtaBasico.setMnemonic('Q');
			btnAtaRestauratorio.setMnemonic('W');
			btnAtaSecundario.setMnemonic('A');
			btnAtaFinal.setMnemonic('S');
		} else {
			lblLucha2.setIcon(new ImageIcon("imagenes\\lucha2.png"));
			lblLucha1.setIcon(null);
			btnAtaBasico.setMnemonic('I');
			btnAtaRestauratorio.setMnemonic('O');
			btnAtaSecundario.setMnemonic('K');
			btnAtaFinal.setMnemonic('L');
		}

		lbl_MuestraAtacante.setText(atacante.getNombre());
		compruebaAtaquesDisponibles();
		btnAtaBasico.setToolTipText(atacante.descripcionAtaqueBasico());
		btnAtaSecundario.setToolTipText(atacante.descripcionAtaquesecundario());
		btnAtaRestauratorio.setToolTipText(atacante.descripcionAtaqueRestauratorio());
		btnAtaFinal.setToolTipText(atacante.descripcionAtaqueFinal());

	}

	private void actualizarConsumible(Personaje pj, JProgressBar progressBar) {
		switch (getClase(pj)) {
		case GUERRERO:
			progressBar.setValue((int) ((Guerrero) pj).getIra());
			break;
		case CABALLERO_DE_LA_MUERTE:
			progressBar.setValue((int) ((CaballeroDeLaMuerte) pj).getRunas());
			break;
		case PICARO:
			progressBar.setValue((int) ((Picaro) pj).getEnergia());
			break;
		case BRUJO:
			break;
		case PALADIN:
			progressBar.setValue((int) ((Paladin) pj).getMana());
			break;
		case MAGO:
			progressBar.setValue((int) ((Mago) pj).getMana());
			break;
		}
	}

	private void compruebaAtaquesDisponibles() {
		btnAtaBasico.setEnabled(false);
		btnAtaSecundario.setEnabled(false);
		btnAtaRestauratorio.setEnabled(false);
		btnAtaFinal.setEnabled(false);
		for (Ataque ataque : combate.getAtacante().ataquesPosibles()) {
			switch (ataque) {
			case ATAQUE_BASICO:
				btnAtaBasico.setEnabled(true);
				break;
			case ATAQUE_SECUNDARIO:
				btnAtaSecundario.setEnabled(true);
				break;
			case ATAQUE_RESTAURATORIO:
				btnAtaRestauratorio.setEnabled(true);
				break;
			case ATAQUE_FINAL:
				btnAtaFinal.setEnabled(true);
				break;
			}
		}

	}

	private void ataque(Ataque ataque) throws ConsumibleInsuficienteException {
		if (combate.Ataque(ataque)) {
			actualizacionPasoTurno();
			JOptionPane.showMessageDialog(contentPanel, combate.getDefensor().getNombre() + " ha muerto. "
					+ combate.getAtacante().getNombre() + " gana y consigue dos puntos de experiencia.");
			combate.getAtacante().ganarExperiencia(2);
			dispose();
		}
		actualizacionPasoTurno();
	}

	private void salir() {
		if (JOptionPane.showOptionDialog(contentPanel,
				PasaHtml.dosSaltoLineaCentrado("¿Estais seguros que quereis acabar?", "(no se otorgará experiencia)"),
				"Saliendo...", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null,
				null) == JOptionPane.YES_OPTION)
			dispose();
		return;
	}
}
