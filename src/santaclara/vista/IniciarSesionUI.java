/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.vista;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;

import santaclara.controlador.ContIniciarSesion;
import santaclara.vista.herramientas.VistaGenericaUI;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class IniciarSesionUI extends VistaGenericaUI {
	
	private JPanel pnlInisioSecion;
	
	private JTextField txtUsuario;
	private JPasswordField password;
	private JButton btnEntrar ;
	private ContIniciarSesion contIniciarSesion;
	/**
	 * Create the panel.
	 */
	public IniciarSesionUI(ContIniciarSesion contIniciarSesion) {
		this.contIniciarSesion=contIniciarSesion;
		setBackground(Color.darkGray);
		setSize(VistaGenericaUI.getWidthPantalla(),VistaGenericaUI.getHeightPantalla());
		dibujarPanelIniciarSesion();
		setLayout(new BorderLayout());
	}
	
	public IniciarSesionUI() {
		// TODO Auto-generated constructor stub
		setBackground(Color.darkGray);
		setSize(VistaGenericaUI.getWidthPantalla(),VistaGenericaUI.getHeightPantalla());
		JPanel panel = new JPanel();
		add(panel,BorderLayout.CENTER);
		setLayout(new BorderLayout());
	}

	public void dibujarPanelIniciarSesion(){
		
		pnlInisioSecion = new JPanel();
		pnlInisioSecion.setBounds((getWidthPantalla()/2)-75, (getHeightPantalla()/2)-125,250,150);
		pnlInisioSecion.setBackground(null);
		pnlInisioSecion.setLayout(new MigLayout());
		
		btnEntrar = new JButton("Iniciar Sesion");
		btnEntrar.setIcon(new ImageIcon("img/gestion/iniciarSecion.png"));
		btnEntrar.setBackground(Color.DARK_GRAY);
		btnEntrar.setForeground(Color.white);
		btnEntrar.addActionListener(contIniciarSesion.iniciarsesion());
		pnlInisioSecion.add(btnEntrar,"cell 0 4");
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setIcon(new ImageIcon("img/gestion/user.png"));
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlInisioSecion.add(lblUsuario,"cell 0 0");
		
		txtUsuario = new JTextField();
		txtUsuario.setText("Rhonal");
		txtUsuario.setFont(new Font("Dialog", Font.BOLD, 16));
		pnlInisioSecion.add(txtUsuario,"cell 0 1");
		txtUsuario.setColumns(10);
		
		JLabel lblcontrasena = new JLabel("Contrasena");
		lblcontrasena.setForeground(Color.WHITE);
		lblcontrasena.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlInisioSecion.add(lblcontrasena,"cell 0 2");
		
		password = new JPasswordField();
		password.setText("1234");
		pnlInisioSecion.add(password,"cell 0 3");
		password.setColumns(10);
	
		add(pnlInisioSecion);
		
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

	public JPanel getPnlInisioSecion() {
		return pnlInisioSecion;
	}

	public void setPnlInisioSecion(JPanel pnlInisioSecion) {
		this.pnlInisioSecion = pnlInisioSecion;
	}

	public ContIniciarSesion getContIniciarSesion() {
		return contIniciarSesion;
	}

	public void setContIniciarSesion(ContIniciarSesion contIniciarSesion) {
		this.contIniciarSesion = contIniciarSesion;
	}
	
	
}
