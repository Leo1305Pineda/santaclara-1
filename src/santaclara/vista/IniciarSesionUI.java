package santaclara.vista;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;

import santaclara.controlador.ContIniciarSesion;
import santaclara.vista.herramientas.VistaGenericaUI;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class IniciarSesionUI extends JPanel {
	
	
	private JTextField txtUsuario;
	private JPasswordField password;
	private JButton btnEntrar ;

	/**
	 * Create the panel.
	 */
	public IniciarSesionUI(ContIniciarSesion controlador) {
		setBackground(Color.WHITE);
		setLayout(null);
		setSize(VistaGenericaUI.getWidthPantalla(),VistaGenericaUI.getHeightPantalla());
		btnEntrar = new JButton("Iniciar Sesion");
		btnEntrar.setBounds(528, 388, 161, 44);
		btnEntrar.addActionListener(controlador.iniciarsesion());
		add(btnEntrar);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setIcon(new ImageIcon("img/gestion/persona.png"));
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuario.setBounds(333, 259, 94, 15);
		add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setText("Rhonal");
		txtUsuario.setFont(new Font("Dialog", Font.BOLD, 16));
		txtUsuario.setBounds(483, 243, 250, 31);
		add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblcontrasena = new JLabel("Contrasena");
		lblcontrasena.setHorizontalAlignment(SwingConstants.RIGHT);
		lblcontrasena.setBounds(319, 305, 121, 20);
		add(lblcontrasena);
		
		password = new JPasswordField();
		password.setText("1234");
		password.setBounds(483, 300, 250, 31);
		add(password);
		password.setColumns(10);

	}

	public JTextField getTxtUsuario() {
		return txtUsuario;
	}

	public void setTxtUsuario(JTextField txtUsuario) {
		this.txtUsuario = txtUsuario;
	}

	public JTextField getPassword() {
		return password;
	}

	public void setPassword(JPasswordField password) {
		this.password = password;
	}

	public JButton getBtnEntrar() {
		return btnEntrar;
	}

	public void setBtnEntrar(JButton btnEntrar) {
		this.btnEntrar = btnEntrar;
	}
}
