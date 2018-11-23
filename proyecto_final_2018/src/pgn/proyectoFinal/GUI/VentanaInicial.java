package pgn.proyectoFinal.GUI;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

import pgn.proyectoFinal.negocio.Brujo;
import pgn.proyectoFinal.negocio.CaballeroDeLaMuerte;
import pgn.proyectoFinal.negocio.Guerrero;
import pgn.proyectoFinal.negocio.Jugador;
import pgn.proyectoFinal.negocio.JugadorYaExistenteException;
import pgn.proyectoFinal.negocio.Mago;
import pgn.proyectoFinal.negocio.Paladin;
import pgn.proyectoFinal.negocio.Partida;
import pgn.proyectoFinal.negocio.PatronNoValidoException;
import pgn.proyectoFinal.negocio.PersonajeYaExistenteException;
import pgn.proyectoFinal.negocio.Picaro;
import pgn.proyectoFinal.negocio.Raza;
import pgn.proyectoFinal.negocio.RazaNoValidaException;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.Toolkit;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import java.awt.SystemColor;

/**
 * Ventana inicial en la que damos las diferentes opciones y vemos a los
 * jugadores que se van a enfrentar
 * 
 * @author Gallardo Pérez Marcos.
 *
 */
public class VentanaInicial {
	private JFrame frmWowFighter;
	static JFileChooser jFileChooser = new JFileChooser();
	private static final String TITULO = "Wow Fighter";
	static final String CONTRASENIA_ADMIN = "0000";
	static Jugador jugador1;
	static Jugador jugador2;

	static JLabel nombre_Usuario2;
	static JLabel nombre_Usuario1;
	static JLabel personajePrincipal_Jugador2;
	static JLabel personajePrincipal_Jugador1;
	static JButton cambiarJugador1;
	static JButton cambiarJugador2;
	static JMenuItem mntmAltaPersonaje1;
	static JMenuItem mntmBajaPersonaje1;
	static JMenuItem mntmMostrarPersonajes1;
	static JMenuItem mntmAltaPersonaje2;
	static JMenuItem mntmBajaPersonaje2;
	static JMenuItem mntmMostrarPersonajes2;

	static BajaJugador bajaJugador;
	static BajaPersonaje bajaPersonaje;
	private JButton btnCambiarPj;
	private JButton btnCombate;

	static {
		jFileChooser.addChoosableFileFilter(new FileNameExtensionFilter(".obj", "obj"));
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				VentanaInicial ventanaInicial = new VentanaInicial();
				ventanaInicial.frmWowFighter.setVisible(true);
				ventanaInicial.frmWowFighter.setResizable(false);
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaInicial() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		disenioVentana();

		disenioMenu();

		disenio_Cuerpo();

	}

	private void disenioVentana() {
		frmWowFighter = new JFrame();
		frmWowFighter.getContentPane().setForeground(UIManager.getColor("Button.background"));
		frmWowFighter.getContentPane().setBackground(UIManager.getColor("Button.background"));
		frmWowFighter.setIconImage(Toolkit.getDefaultToolkit().getImage("imagenes\\icon.png"));
		frmWowFighter.setTitle(TITULO);

		frmWowFighter.setBounds(100, 100, 641, 419);
		frmWowFighter.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmWowFighter.getContentPane().setLayout(null);
		frmWowFighter.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				salir();
			}
		});
	}

	private void disenioMenu() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(UIManager.getColor("Button.background"));
		menuBar.setBounds(0, 0, 643, 21);
		frmWowFighter.getContentPane().add(menuBar);

		disenio_JMenu_Archivo(menuBar);

		disenio_JMenu_Jugadores(menuBar);

		disenio_JMenu_Ayuda(menuBar);
	}

	private void disenio_JMenu_Archivo(JMenuBar menuBar) {
		JMenu mnArchivo = new JMenu("Archivo");
		mnArchivo.setMnemonic('A');
		menuBar.add(mnArchivo);

		JMenuItem mntmNuevaPartida = new JMenuItem("Nueva Partida");
		mntmNuevaPartida.setMnemonic('N');
		mntmNuevaPartida.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mntmNuevaPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nuevaPartida();
			}
		});
		mnArchivo.add(mntmNuevaPartida);

		JMenuItem mntmAbrirPartida = new JMenuItem("Abrir...");
		mntmAbrirPartida.setMnemonic('b');
		mntmAbrirPartida.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mntmAbrirPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirPartida();
			}
		});
		mnArchivo.add(mntmAbrirPartida);

		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		mntmGuardar.setMnemonic('G');
		mntmGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
		mntmGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					guardar(Partida.getFile());
				} catch (CancelarProcesoException e1) {
				}
			}
		});
		mnArchivo.add(mntmGuardar);

		JMenuItem mntmGuardarComo = new JMenuItem("Guardar Como...");
		mntmGuardarComo.setMnemonic('C');
		mntmGuardarComo
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mntmGuardarComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					guardarComo();
				} catch (CancelarProcesoException e1) {
				}
			}
		});
		mnArchivo.add(mntmGuardarComo);

		JSeparator separator = new JSeparator();
		mnArchivo.add(separator);

		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.setMnemonic('S');
		mntmSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salir();
			}
		});
		mnArchivo.add(mntmSalir);
	}

	private void nuevaPartida() {
		try {
			evitaPerdidaInformacion();
			Partida.nuevaPartida();
			cambioPartida();
		} catch (CancelarProcesoException e) {

		}

	}

	private void evitaPerdidaInformacion() throws CancelarProcesoException {
		if (preguntaGuardarCambios())
			guardar(Partida.getFile());
	}

	private boolean preguntaGuardarCambios() throws CancelarProcesoException {
		if (Partida.getModificado()) {
			switch (JOptionPane.showOptionDialog(frmWowFighter, "¿Desea guardar los cambios efectuados?",
					"¿Quiere guardar el documento?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null,
					null)) {
			case JOptionPane.YES_OPTION:
				return true;
			case JOptionPane.CLOSED_OPTION:
			case JOptionPane.CANCEL_OPTION:
				throw new CancelarProcesoException("Accion Interrumpida");
			}
		}
		return false;
	}

	private void cambioPartida() {
		jugador1 = null;
		jugador2 = null;
		actualizar_Usuario1();
		actualizar_Usuario2();
	}

	private void abrirPartida() {
		try {
			evitaPerdidaInformacion();
			if (jFileChooser.showDialog(frmWowFighter, "Abrir Fichero") == JFileChooser.APPROVE_OPTION) {
				try {
					Partida.leer(jFileChooser.getSelectedFile());
					cambioPartida();
				} catch (ClassNotFoundException | IOException e) {
					JOptionPane.showMessageDialog(frmWowFighter, "Error a abrir el archivo");
				}
			}
		} catch (CancelarProcesoException e1) {
		}
	}

	private void guardar(File file) throws CancelarProcesoException {
		try {
			if (file == null)
				guardarComo();
			else {
				Partida.escribir(file);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(frmWowFighter, "Error al guardar el archivo");
		}
	}

	private void guardarComo() throws CancelarProcesoException {
		if (jFileChooser.showDialog(frmWowFighter, "Guardar como...") != JFileChooser.APPROVE_OPTION)
			throw new CancelarProcesoException("Accion Interrumpida");

		File file = jFileChooser.getSelectedFile();
		if (Partida.exist(file)) {
			int opcion = JOptionPane.showOptionDialog(frmWowFighter,
					"Hay un archivo con el mismo nombre, ¿Desea sobreescribirlo?", "Guardar", JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE, null, null, null);
			if (opcion != JOptionPane.YES_OPTION)
				throw new CancelarProcesoException("Accion Interrumpida");
		}
		guardar(file);
	}

	private void salir() {
		try {
			evitaPerdidaInformacion();
			System.exit(0);
		} catch (CancelarProcesoException e) {
		}

	}

	private void disenio_JMenu_Jugadores(JMenuBar menuBar) {
		JMenu mnJugadores = new JMenu("Jugadores");
		mnJugadores.setMnemonic('J');
		menuBar.add(mnJugadores);

		disenio_anadirJugador(mnJugadores);

		disenio_subMenuJugador1(mnJugadores);

		disenio_subMenuJugador2(mnJugadores);

		disenio_eliminarJugador(mnJugadores);

		disenio_mostarJugadores(mnJugadores);

	}

	private void disenio_anadirJugador(JMenu mnJugadores) {
		JMenuItem mntmAadirJugador = new JMenuItem("A\u00F1adir Jugador");
		mntmAadirJugador.setMnemonic('A');
		mntmAadirJugador.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, InputEvent.CTRL_MASK));
		mntmAadirJugador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaJugador();
			}
		});
		mnJugadores.add(mntmAadirJugador);
	}

	private void disenio_subMenuJugador1(JMenu mnJugadores) {
		JMenu mnJugador_1 = new JMenu("Jugador1");
		mnJugador_1.setMnemonic(KeyEvent.VK_1);
		mnJugadores.add(mnJugador_1);

		JMenuItem mntmSeleccionarJugador1 = new JMenuItem("Seleccionar Jugador 1");
		mntmSeleccionarJugador1.setMnemonic('S');
		mntmSeleccionarJugador1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_MASK));
		mntmSeleccionarJugador1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionarJugador(1);
			}
		});
		mnJugador_1.add(mntmSeleccionarJugador1);

		mntmAltaPersonaje1 = new JMenuItem("Alta Personaje");
		mntmAltaPersonaje1.setMnemonic('N');
		mntmAltaPersonaje1
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mntmAltaPersonaje1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				altaPersonaje(jugador1);
			}
		});
		mnJugador_1.add(mntmAltaPersonaje1);

		mntmBajaPersonaje1 = new JMenuItem("Baja Personaje");
		mntmBajaPersonaje1.setMnemonic('B');
		mntmBajaPersonaje1
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mntmBajaPersonaje1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bajaPersonaje(jugador1);
			}
		});
		mnJugador_1.add(mntmBajaPersonaje1);

		mntmMostrarPersonajes1 = new JMenuItem("Mostrar Personajes");
		mntmMostrarPersonajes1.setMnemonic('P');
		mntmMostrarPersonajes1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,
				InputEvent.CTRL_MASK | InputEvent.ALT_MASK | InputEvent.SHIFT_MASK));
		mntmMostrarPersonajes1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mostrarPersonajes(jugador1);
			}
		});
		mnJugador_1.add(mntmMostrarPersonajes1);
	}

	private void disenio_subMenuJugador2(JMenu mnJugadores) {
		JMenu mnJugador_2 = new JMenu("Jugador2");
		mnJugador_2.setMnemonic('P');
		mnJugador_2.setMnemonic(KeyEvent.VK_2);
		mnJugadores.add(mnJugador_2);

		JMenuItem mntmSeleccionarJugador2 = new JMenuItem("Seleccionar Jugador 2");
		mntmSeleccionarJugador2.setMnemonic('S');
		mntmSeleccionarJugador2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.CTRL_MASK));
		mntmSeleccionarJugador2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionarJugador(2);
			}
		});
		mnJugador_2.add(mntmSeleccionarJugador2);

		mntmAltaPersonaje2 = new JMenuItem("Alta Personaje");
		mntmAltaPersonaje2.setMnemonic('N');
		mntmAltaPersonaje2
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mntmAltaPersonaje2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaPersonaje(jugador2);
			}
		});
		mntmAltaPersonaje2.setEnabled(true);
		mnJugador_2.add(mntmAltaPersonaje2);

		mntmBajaPersonaje2 = new JMenuItem("Baja Personaje");
		mntmBajaPersonaje2.setMnemonic('B');
		mntmBajaPersonaje2
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mntmBajaPersonaje2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bajaPersonaje(jugador2);
			}
		});
		mntmBajaPersonaje2.setEnabled(true);
		mnJugador_2.add(mntmBajaPersonaje2);

		mntmMostrarPersonajes2 = new JMenuItem("Mostrar Personajes");
		mntmMostrarPersonajes2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2,
				InputEvent.CTRL_MASK | InputEvent.ALT_MASK | InputEvent.SHIFT_MASK));
		mntmMostrarPersonajes2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarPersonajes(jugador2);
			}
		});
		mntmMostrarPersonajes2.setEnabled(true);
		mnJugador_2.add(mntmMostrarPersonajes2);
	}

	private void disenio_eliminarJugador(JMenu mnJugadores) {
		JMenuItem mntmEliminarJugador = new JMenuItem("Eliminar Jugador");
		mntmEliminarJugador.setMnemonic('E');
		mntmEliminarJugador.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_MASK));
		mntmEliminarJugador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				bajaJugador();
			}
		});
		mnJugadores.add(mntmEliminarJugador);
	}

	private void disenio_mostarJugadores(JMenu mnJugadores) {
		JMenuItem mntmVerTodosLos = new JMenuItem("Mostrar jugadores");
		mntmVerTodosLos.setMnemonic('M');
		mntmVerTodosLos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK));
		mntmVerTodosLos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				mostarJugadores();
			}
		});
		mnJugadores.add(mntmVerTodosLos);
	}

	static void setJugador1(Jugador jugador1) {
		VentanaInicial.jugador1 = jugador1;
	}

	static Jugador getJugador1() {
		return jugador1;
	}

	static void setJugador2(Jugador jugador2) {
		VentanaInicial.jugador2 = jugador2;
	}

	static Jugador getJugador2() {
		return jugador2;
	}

	protected void altaJugador() {
		JDialog dialog = new AltaJugador();
		dialog.setVisible(true);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	private void bajaJugador() {
		if (empty())
			return;
		bajaJugador = new BajaJugador();
		bajaJugador.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		bajaJugador.setVisible(true);
	}

	private void mostarJugadores() {
		if (empty())
			return;
		MostrarJugadores dialog = new MostrarJugadores();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}

	private void seleccionarJugador(int jugador) {
		if (empty())
			return;
		VentanaSeleccionJugador dialog = new VentanaSeleccionJugador(jugador);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}

	private boolean empty() {
		if (Partida.isEmpty()) {
			JOptionPane.showMessageDialog(frmWowFighter, "No hay jugadores.");
			return true;
		}
		return false;
	}

	private void altaPersonaje(Jugador jugador) {
		if (!comprobacionExistencia(jugador))
			return;
		AltaPersonaje altaPersonaje = new AltaPersonaje(jugador);
		altaPersonaje.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		altaPersonaje.setVisible(true);
	}

	private void bajaPersonaje(Jugador jugador) {
		if (!comprobacionExistencia(jugador))
			return;
		bajaPersonaje = new BajaPersonaje(jugador);
		bajaPersonaje.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		bajaPersonaje.setVisible(true);
	}

	private void mostrarPersonajes(Jugador jugador) {
		MostarPersonajes mostarPersonajes = new MostarPersonajes(jugador);
		mostarPersonajes.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		mostarPersonajes.setVisible(true);
	}

	private boolean comprobacionExistencia(Jugador jugador) {
		if (jugador == null) {
			JOptionPane.showMessageDialog(frmWowFighter, "Selecciona un jugador");
			return false;
		}
		return true;
	}

	static void actualizarJugador(Jugador jugador) {
		if (jugador.equals(jugador1))
			actualizar_Usuario1();
		if (jugador.equals(jugador2))
			actualizar_Usuario2();
	}

	private void disenio_JMenu_Ayuda(JMenuBar menuBar) {
		JMenu mnAyuda = new JMenu("Ayuda");
		mnAyuda.setBackground(UIManager.getColor("Button.background"));
		mnAyuda.setMnemonic('H');
		menuBar.add(mnAyuda);

		JMenuItem mntmAyuda = new JMenuItem("Ver la Ayuda");
		mntmAyuda.setMnemonic('H');
		mntmAyuda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK));
		mntmAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ayuda ayuda = Ayuda.ayudaSingleton();
				ayuda.setVisible(true);
			}
		});
		mnAyuda.add(mntmAyuda);

		JSeparator separator_1 = new JSeparator();
		mnAyuda.add(separator_1);

		JMenuItem mntmAcercade = new JMenuItem("AcercaDe...");
		mntmAcercade.setMnemonic('A');
		mntmAcercade.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mntmAcercade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AcercaDe dialog = new AcercaDe();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		mnAyuda.add(mntmAcercade);
	}

	private void disenio_Cuerpo() {
		disenio_Jugador1();

		btnCombate = new JButton("Combate");
		btnCombate.isDefaultButton();
		btnCombate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!comprobacionExistencia(getJugador2()))
					return;
				if (!comprobacionExistencia(getJugador1()))
					return;
				combate(getJugador1(), getJugador2());
			}
		});
		btnCombate.setBounds(215, 317, 202, 50);

		frmWowFighter.getContentPane().add(btnCombate);

		disenio_Jugador2();
	}

	private void disenio_Jugador1() {
		JLabel lblJugador = new JLabel("Jugador 1 :");
		lblJugador.setForeground(SystemColor.activeCaptionText);
		lblJugador.setBounds(24, 70, 79, 14);
		frmWowFighter.getContentPane().add(lblJugador);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setForeground(SystemColor.activeCaptionText);
		lblUsuario.setBounds(34, 95, 169, 14);
		frmWowFighter.getContentPane().add(lblUsuario);

		nombre_Usuario1 = new JLabel("-");
		nombre_Usuario1.setForeground(SystemColor.activeCaptionText);
		nombre_Usuario1.setBounds(44, 120, 159, 14);
		frmWowFighter.getContentPane().add(nombre_Usuario1);

		JLabel lblUsuario_1 = new JLabel("Personaje principal:");
		lblUsuario_1.setForeground(SystemColor.activeCaptionText);
		lblUsuario_1.setBounds(34, 159, 170, 14);
		frmWowFighter.getContentPane().add(lblUsuario_1);

		personajePrincipal_Jugador1 = new JLabel("-");
		personajePrincipal_Jugador1.setForeground(SystemColor.activeCaptionText);
		personajePrincipal_Jugador1.setBounds(44, 189, 160, 14);
		frmWowFighter.getContentPane().add(personajePrincipal_Jugador1);

		btnCambiarPj = new JButton("Cambiar PJ1");
		btnCambiarPj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seleccionarJugador(1);
			}
		});

		btnCambiarPj.setBounds(34, 234, 169, 23);
		frmWowFighter.getContentPane().add(btnCambiarPj);

		cambiarJugador1 = new JButton("Cambiar");
		cambiarJugador1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seleccionarPersonaje(jugador1);
			}
		});
		cambiarJugador1.setBounds(171, 185, 88, 23);
		frmWowFighter.getContentPane().add(cambiarJugador1);

		actualizar_Usuario1();
	}

	static void actualizar_Usuario1() {
		if (jugador1 == null || jugador1.size() == 0) {
			jugador1 = null;
			nombre_Usuario1.setText("-");
			personajePrincipal_Jugador1.setText("-");
			cambiarJugador1.setEnabled(false);
			mntmAltaPersonaje1.setEnabled(false);
			mntmBajaPersonaje1.setEnabled(false);
			mntmMostrarPersonajes1.setEnabled(false);
			return;
		}
		nombre_Usuario1.setText("-" + jugador1.getUsuario() + "-");
		personajePrincipal_Jugador1.setText("-" + jugador1.getPersonajeActual().getNombre() + "-");
		mntmAltaPersonaje1.setEnabled(true);
		mntmMostrarPersonajes1.setEnabled(true);
		if (jugador1.size() < 2) {
			cambiarJugador1.setEnabled(false);
			mntmBajaPersonaje1.setEnabled(false);
			return;
		}
		cambiarJugador1.setEnabled(true);
		mntmBajaPersonaje1.setEnabled(true);

	}

	private void disenio_Jugador2() {
		JLabel lblJugador_1 = new JLabel("Jugador 2 :");
		lblJugador_1.setBackground(UIManager.getColor("Button.background"));
		lblJugador_1.setForeground(SystemColor.activeCaptionText);
		lblJugador_1.setBounds(444, 70, 79, 14);
		frmWowFighter.getContentPane().add(lblJugador_1);

		JLabel label_3 = new JLabel("Usuario:");
		label_3.setForeground(SystemColor.activeCaptionText);
		label_3.setBounds(454, 95, 79, 14);
		frmWowFighter.getContentPane().add(label_3);

		nombre_Usuario2 = new JLabel("-");
		nombre_Usuario2.setForeground(SystemColor.activeCaptionText);
		nombre_Usuario2.setBounds(464, 120, 159, 14);
		frmWowFighter.getContentPane().add(nombre_Usuario2);

		JLabel label_5 = new JLabel("Personaje principal:");
		label_5.setForeground(SystemColor.activeCaptionText);
		label_5.setBounds(453, 159, 140, 14);
		frmWowFighter.getContentPane().add(label_5);

		personajePrincipal_Jugador2 = new JLabel("-");
		personajePrincipal_Jugador2.setForeground(SystemColor.activeCaptionText);
		personajePrincipal_Jugador2.setBounds(463, 189, 160, 14);
		frmWowFighter.getContentPane().add(personajePrincipal_Jugador2);

		JButton btnCambiarPj_1 = new JButton("Cambiar PJ2");
		btnCambiarPj_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seleccionarJugador(2);
			}
		});
		btnCambiarPj_1.setBounds(388, 234, 169, 23);
		frmWowFighter.getContentPane().add(btnCambiarPj_1);

		cambiarJugador2 = new JButton("Cambiar");
		cambiarJugador2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionarPersonaje(jugador2);
			}
		});
		cambiarJugador2.setBounds(348, 185, 90, 23);
		frmWowFighter.getContentPane().add(cambiarJugador2);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(SystemColor.windowText);
		lblNewLabel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("imagenes\\fondo2.jpg")));
		lblNewLabel.setBounds(10, 32, 613, 349);
		frmWowFighter.getContentPane().add(lblNewLabel);

		actualizar_Usuario2();
	}

	static void actualizar_Usuario2() {
		if (jugador2 == null || jugador2.size() == 0) {
			jugador2 = null;
			nombre_Usuario2.setText("-");
			personajePrincipal_Jugador2.setText("-");
			cambiarJugador2.setEnabled(false);
			mntmAltaPersonaje2.setEnabled(false);
			mntmBajaPersonaje2.setEnabled(false);
			mntmMostrarPersonajes2.setEnabled(false);
			return;
		}
		nombre_Usuario2.setText("-" + jugador2.getUsuario() + "-");
		personajePrincipal_Jugador2.setText("-" + jugador2.getPersonajeActual().getNombre() + "-");
		mntmAltaPersonaje2.setEnabled(true);
		mntmMostrarPersonajes2.setEnabled(true);
		if (jugador2.size() < 2) {
			cambiarJugador2.setEnabled(false);
			mntmBajaPersonaje2.setEnabled(false);
			return;
		}
		cambiarJugador2.setEnabled(true);
		mntmBajaPersonaje2.setEnabled(true);

	}

	public static void actualizaPartida() {
		if (!Partida.contains(getJugador1())) {
			setJugador1(null);
		}
		if (!Partida.contains(getJugador2())) {
			setJugador2(null);
		}
		actualizar_Usuario1();
		actualizar_Usuario2();

	}

	private void seleccionarPersonaje(Jugador jugador) {
		VentanaSeleccionPersonaje seleccionarPersonaje = new VentanaSeleccionPersonaje(jugador);
		seleccionarPersonaje.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		seleccionarPersonaje.setVisible(true);
	}

	private void combate(Jugador jugador1, Jugador jugador2) {
		try {
			CombateGUI combate = new CombateGUI(jugador1, jugador2);
			combate.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			combate.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frmWowFighter, e.getMessage());
		}
	}
}
