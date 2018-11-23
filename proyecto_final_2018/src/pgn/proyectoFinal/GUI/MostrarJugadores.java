package pgn.proyectoFinal.GUI;

import pgn.proyectoFinal.negocio.Jugador;
import pgn.proyectoFinal.negocio.Partida;
import pgn.proyectoFinal.negocio.PatronNoValidoException;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ListIterator;
import java.awt.event.ActionEvent;

/**
 * Ventana en la que se muestran los jugadores
 * 
 * @author Gallardo Pérez Marcos.
 *
 */
@SuppressWarnings("serial")
public class MostrarJugadores extends VentanaPadreMostarJugadores {

	protected ListIterator<Jugador> jugadoresIterator = Partida.listIterator();
	protected boolean movDerecha = true;

	/**
	 * Create the dialog.
	 */
	public MostrarJugadores() {

		definirVentana();
		inicioIterador();
	}

	private void definirVentana() {
		textField_Nombre_Usuario.setEnabled(false);
		setTitle("Mostrar Jugadores");
		disenio_OkButton();
		disenio_CancelButton();
		okButton.setMnemonic(KeyEvent.VK_LEFT);
		okButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

			}
		});
		cancelButton.setMnemonic(KeyEvent.VK_RIGHT);
		cancelButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
			}
		});

	}

	private void disenio_OkButton() {
		okButton.setText("<");
		eventos_OkButton();
	}

	private void eventos_OkButton() {
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moverIzquierda();
			}
		});
	}

	private void disenio_CancelButton() {
		cancelButton.setText(">");
		eventos_CancelButton();
	}

	private void eventos_CancelButton() {
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moverDerecha();
			}
		});
	}

	protected void inicioIterador() {
		moverDerecha();
		comprobacionIterador();
		okButton.setEnabled(false);
	}

	protected void comprobacionIterador() {
		okButton.setEnabled(true);
		cancelButton.setEnabled(true);
		if (!jugadoresIterator.hasPrevious()) {
			okButton.setEnabled(false);
		}
		if (!jugadoresIterator.hasNext()) {
			cancelButton.setEnabled(false);
		}
	}

	protected void moverDerecha() {
		if (jugadoresIterator.hasNext())
			try {
				buscar(jugadoresIterator.next().getUsuario());
			} catch (PatronNoValidoException e) {
				e.printStackTrace();
			}
		if (!movDerecha)
			if (jugadoresIterator.hasNext())
				try {
					buscar(jugadoresIterator.next().getUsuario());
				} catch (PatronNoValidoException e) {
					e.printStackTrace();
				}
		movDerecha = true;
		comprobacionIterador();
	}

	protected void moverIzquierda() {
		if (jugadoresIterator.hasPrevious())
			try {
				buscar(jugadoresIterator.previous().getUsuario());
			} catch (PatronNoValidoException e) {
				e.printStackTrace();
			}
		if (movDerecha)
			if (jugadoresIterator.hasPrevious())
				try {
					buscar(jugadoresIterator.previous().getUsuario());
				} catch (PatronNoValidoException e) {
					e.printStackTrace();
				}
		movDerecha = false;
		comprobacionIterador();
	}

}
