package pgn.proyectoFinal.GUI;

import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

/**
 * Ventana en la que se da información sobre quien ha realizado el proyecto
 * 
 * @author Gallardo Pérez Marcos.
 *
 */
@SuppressWarnings("serial")
public class AcercaDe extends JDialog {

	private final JPanel contentPane = new JPanel();
	JLabel lblea;
	GroupLayout gl_contentPane;

	/**
	 * Create the dialog.
	 */
	public AcercaDe() {
		setBounds(100, 100, 450, 300);
		setResizable(false);
		setModal(true);
		setTitle("Acerca De");
		setBounds(100, 100, 450, 300);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		lblea = new JLabel(
				"<html>\r\n<body>\r\n<p>Trabajo Realizado en:</p>\r\n\r\n<p> &nbsp;&nbsp;  Instituto Gran Capit\u00E1n&nbsp; &nbsp; &nbsp; C\u00F3rdoba</p>\r\n<p></p>\r\n<p>Trabajo Realizado por : </p>\r\n<p>  &nbsp; &nbsp; Marcos Gallardo P\u00E9rez</p>\r\n</body>\r\n</html>");
		lblea.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblea.setAutoscrolls(true);
		gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(
				Alignment.TRAILING, gl_contentPane.createSequentialGroup().addContainerGap(74, Short.MAX_VALUE)
						.addComponent(lblea, GroupLayout.PREFERRED_SIZE, 296, GroupLayout.PREFERRED_SIZE).addGap(64)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(23)
						.addComponent(lblea, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(48, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);
	}

}
