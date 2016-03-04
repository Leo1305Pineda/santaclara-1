package santaclara.dbPostgresql.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import santaclara.dbPostgresql.controlador.ContPostgreSql;
import santaclara.vista.herramientas.VistaGenericaUI;

@SuppressWarnings("serial")
public class PostgreSqlUI extends VistaGenericaUI{

	private JPanel pnConfPostgreSql;
	
	private JButton btnGuardar;
	private JButton btnGedit;  
	private JButton btnPgAdmin3;
	private JButton btnCancelar;
	
	private JLabel lblUser;
	private JLabel lblPassword;
	private JLabel lblUrl;
	private JLabel lblClassName;
	private JLabel lblUbicacionDB;
	private JLabel lblPuerto;
	private JLabel lblNombreDB;
	
	private JTextField txtUser;
	private JTextField txtPassword;
	private JTextField txtUrl;
	private JTextField txtClassName;
	private JTextField txtUbicacionDB;
	private JTextField txtPuerto;
	private JTextField txtNombreDB;
	
	private ContPostgreSql contPostgreSql;
	
	public PostgreSqlUI(ContPostgreSql contPostgreSql) {
		super();
		// TODO Auto-generated constructor stub
		
		this.contPostgreSql = contPostgreSql;
		
		dibujarPanelOpciones();
		dibujarBotonAtras();
		getBtnAtras().addActionListener(contPostgreSql.Atras());
		dibujarPanelConfPostgreSql();
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(contPostgreSql.guardar());
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBackground(Color.DARK_GRAY);
		btnGuardar.setIcon(new ImageIcon("img/gestion/bien.png"));
		getPnBotones().add(btnGuardar);
		
		btnPgAdmin3 = new JButton("pgAdmin III");
		btnPgAdmin3.addActionListener(contPostgreSql.Abrir("pgadmin3"));
		btnPgAdmin3.setForeground(Color.WHITE);
		btnPgAdmin3.setBackground(Color.DARK_GRAY);
		btnPgAdmin3.setIcon(new ImageIcon("img/gestion/pgAdmin3.png"));
		getPnBotones().add(btnPgAdmin3);
		
		btnGedit = new JButton();
		btnGedit.addActionListener(contPostgreSql.Abrir("gedit " + new File("archivos/confPostgreSQL.txt")));
		btnGedit.setForeground(Color.WHITE);
		btnGedit.setBackground(Color.WHITE);
		btnGedit.setIcon(new ImageIcon("img/gestion/gedit.png"));
		getPnBotones().add(btnGedit);
		
		dibujarBotonSalir();
		getBtnSalir().addActionListener(contPostgreSql.salir());
		
	}

	public void dibujarPanelConfPostgreSql(){
		pnConfPostgreSql = new JPanel();
		pnConfPostgreSql.setBackground(Color.DARK_GRAY);
		pnConfPostgreSql.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)),"EDITAR ALMACEN", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnConfPostgreSql.setLayout(new MigLayout());

		Integer numColumn = new Integer(30);
		
		lblUser = new JLabel("Usuario:");
		lblUser.setForeground(Color.WHITE);
		pnConfPostgreSql.add(lblUser, "cell 0 0");
		
		txtUser = new JTextField();
		txtUser.setColumns(numColumn);
		pnConfPostgreSql.add(txtUser, "cell 1 0");
		
		lblPassword = new JLabel("Contraseña:");
		lblPassword.setForeground(Color.WHITE);
		pnConfPostgreSql.add(lblPassword, "cell 0 1");
		
		txtPassword = new JTextField();
		txtPassword.setColumns(numColumn);
		pnConfPostgreSql.add(txtPassword, "cell 1 1");
		
		lblUbicacionDB = new JLabel("Ubicacion Dase de Dato:");
		lblUbicacionDB.setForeground(Color.WHITE);
		pnConfPostgreSql.add(lblUbicacionDB, "cell 0 2");
		
		txtUbicacionDB = new JTextField();
		txtUbicacionDB.setColumns(numColumn);
		txtUbicacionDB.setEditable(false);
		pnConfPostgreSql.add(txtUbicacionDB, "cell 1 2");
		
		lblPuerto = new JLabel("Puerto:");
		lblPuerto.setForeground(Color.WHITE);
		pnConfPostgreSql.add(lblPuerto, "cell 0 3");
		
		txtPuerto = new JTextField();
		txtPuerto.setColumns(numColumn);
		txtPuerto.setEditable(false);
		pnConfPostgreSql.add(txtPuerto, "cell 1 3");
		
		lblNombreDB = new JLabel("Base Dato:");
		lblNombreDB.setForeground(Color.WHITE);
		pnConfPostgreSql.add(lblNombreDB, "cell 0 4");
		
		txtNombreDB = new JTextField();
		txtNombreDB.setColumns(numColumn);
		txtNombreDB.setEditable(false);
		pnConfPostgreSql.add(txtNombreDB, "cell 1 4");
		
		lblUrl = new JLabel("Url:");
		lblUrl.setForeground(Color.WHITE);
		pnConfPostgreSql.add(lblUrl, "cell 0 5");
		
		txtUrl = new JTextField();
		txtUrl.setColumns(numColumn);
		txtUrl.addKeyListener(contPostgreSql.activarCambioTxtUrl());
		pnConfPostgreSql.add(txtUrl, "cell 1 5");
		
		lblClassName = new JLabel("Nombre del Driver:");
		lblClassName.setForeground(Color.WHITE);
		pnConfPostgreSql.add(lblClassName, "cell 0 6");
		
		txtClassName = new JTextField();
		txtClassName.setColumns(numColumn);
		pnConfPostgreSql.add(txtClassName, "cell 1 6");
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setVisible(false);

		add(pnConfPostgreSql,BorderLayout.CENTER);
		
		repaint();
	}

	public JPanel getPnConfPostgreSql() {
		return pnConfPostgreSql;
	}

	public void setPnConfPostgreSql(JPanel pnConfPostgreSql) {
		this.pnConfPostgreSql = pnConfPostgreSql;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public JLabel getLblUser() {
		return lblUser;
	}

	public void setLblUser(JLabel lblUser) {
		this.lblUser = lblUser;
	}

	public JLabel getLblPassword() {
		return lblPassword;
	}

	public void setLblPassword(JLabel lblPassword) {
		this.lblPassword = lblPassword;
	}

	public JLabel getLblUrl() {
		return lblUrl;
	}

	public void setLblUrl(JLabel lblUrl) {
		this.lblUrl = lblUrl;
	}

	public JLabel getLblClassName() {
		return lblClassName;
	}

	public void setLblClassName(JLabel lblClassName) {
		this.lblClassName = lblClassName;
	}

	public JTextField getTxtUser() {
		return txtUser;
	}

	public void setTxtUser(JTextField txtUser) {
		this.txtUser = txtUser;
	}

	public JTextField getTxtPassword() {
		return txtPassword;
	}

	public void setTxtPassword(JTextField txtPassword) {
		this.txtPassword = txtPassword;
	}

	public JTextField getTxtUrl() {
		return txtUrl;
	}

	public void setTxtUrl(JTextField txtUrl) {
		this.txtUrl = txtUrl;
	}

	public JTextField getTxtClassName() {
		return txtClassName;
	}

	public void setTxtClassName(JTextField txtClassName) {
		this.txtClassName = txtClassName;
	}

	public ContPostgreSql getContPostgreSql() {
		return contPostgreSql;
	}

	public void setContPostgreSql(ContPostgreSql contPostgreSql) {
		this.contPostgreSql = contPostgreSql;
	}

	public JLabel getLblUbicacionDB() {
		return lblUbicacionDB;
	}

	public void setLblUbicacionDB(JLabel lblUbicacionDB) {
		this.lblUbicacionDB = lblUbicacionDB;
	}

	public JLabel getLblPuerto() {
		return lblPuerto;
	}

	public void setLblPuerto(JLabel lblPuerto) {
		this.lblPuerto = lblPuerto;
	}

	public JLabel getLblNombreDB() {
		return lblNombreDB;
	}

	public void setLblNombreDB(JLabel lblNombreDB) {
		this.lblNombreDB = lblNombreDB;
	}

	public JTextField getTxtUbicacionDB() {
		return txtUbicacionDB;
	}

	public void setTxtUbicacionDB(JTextField txtUbicacionDB) {
		this.txtUbicacionDB = txtUbicacionDB;
	}

	public JTextField getTxtPuerto() {
		return txtPuerto;
	}

	public void setTxtPuerto(JTextField txtPuerto) {
		this.txtPuerto = txtPuerto;
	}

	public JTextField getTxtNombreDB() {
		return txtNombreDB;
	}

	public void setTxtNombreDB(JTextField txtNombreDB) {
		this.txtNombreDB = txtNombreDB;
	}
	
}
