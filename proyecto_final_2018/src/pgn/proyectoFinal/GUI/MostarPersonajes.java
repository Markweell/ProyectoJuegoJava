package pgn.proyectoFinal.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ListIterator;

import pgn.proyectoFinal.negocio.Jugador;
import pgn.proyectoFinal.negocio.PatronNoValidoException;
import pgn.proyectoFinal.negocio.Personaje;

/**
 * Ventana en la que se muestran los personajes.
 * 
 * @author Gallardo Pérez Marcos.
 *
 */
@SuppressWarnings("serial")
public class MostarPersonajes extends VentanaPadreMostarPersonaje {

	protected boolean movDerecha = true;
	private Jugador jugador;
	ListIterator<Personaje> personajesIterator;

	/**
	 * Create the dialog.
	 */
	public MostarPersonajes(Jugador jugador) {
		personajesIterator = jugador.getIteratorPersonajes();
		this.jugador = jugador;
		definirVentana();
		inicioIterador();
	}

	private void definirVentana() {
		textField_NombrePJ.setEnabled(false);
		
		setTitle("Mostrar Personajes");
		disenio_OkButton();
		disenio_CancelButton();
	}

	private void disenio_OkButton() {
		okButton.setText("<");
		okButton.setMnemonic(KeyEvent.VK_LEFT);
		eventos_OkButton();
	}

	private void eventos_OkButton() {
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					moverIzquierda();
				} catch (PatronNoValidoException e1) {
				}
			}
		});
		okButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
			}
		});
	}

	private void disenio_CancelButton( ) {
		cancelButton.setText(">");
		cancelButton.setMnemonic(KeyEvent.VK_RIGHT);
		eventos_CancelButton();
	}

	private void eventos_CancelButton() {
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					moverDerecha();
				} catch (PatronNoValidoException e1) {
				}
			}
		});
		cancelButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
			}
		});
		
	}

	private void inicioIterador() {
		try {
			moverDerecha();
			comprobacionIterador();
			okButton.setEnabled(false);
		} catch (PatronNoValidoException e) {
		}

	}

	protected void comprobacionIterador() {
		okButton.setEnabled(true);
		cancelButton.setEnabled(true);
		if (!personajesIterator.hasPrevious()) {
			okButton.setEnabled(false);
		}
		if (!personajesIterator.hasNext()) {
			cancelButton.setEnabled(false);
		}
	}

	protected void moverDerecha() throws PatronNoValidoException {
		if (personajesIterator.hasNext())
			buscar(jugador, personajesIterator.next().getNombre());

		if (!movDerecha)
			if (personajesIterator.hasNext())
				buscar(jugador, personajesIterator.next().getNombre());

		movDerecha = true;
		comprobacionIterador();
	}

	protected void moverIzquierda() throws PatronNoValidoException {
		if (personajesIterator.hasPrevious())
			buscar(jugador, personajesIterator.previous().getNombre());

		if (movDerecha)
			if (personajesIterator.hasPrevious())
				buscar(jugador, personajesIterator.previous().getNombre());

		movDerecha = false;
		comprobacionIterador();
	}

}
