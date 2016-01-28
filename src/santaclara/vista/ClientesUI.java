package santaclara.vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import net.miginfocom.swing.MigLayout;
import santaclara.controlador.ContClientes;

import javax.swing.JCheckBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class ClientesUI extends JPanel {
	
	private JPanel pnOpciones;
	private JPanel panel;
	private JPanel pnClientes;
	private JPanel panel_1;
	private JPanel pnTabla;
	private JPanel pnlRuta;
	private JPanel pnCliente;
	private JPanel pnCheckDia;
	
	private JLabel lblTipoCliente;
	private JLabel lblRazonSocial;
	private JLabel lblTelefono;
	private JLabel lblDireccion;
	private JLabel lblRif;
	private JLabel lblDiaVisita;
	private JLabel lblRuta;
	
	private JTextField txtRazonSocial;
	private JTextField txtABuscar;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JTextField txtRif;
	private JTextField txtId;
	
	private JButton btnNuevo;
	private JButton btnAtras;
	private JButton btnSalir;
	private JButton btnABuscar;
	private JButton btnEliminar;
	private JButton btnGuardar; 
	private JButton btnAbrirRuta;
	
	private JCheckBox checkLune;
	private JCheckBox checkMarte;
	private JCheckBox checkMiercole;
	private JCheckBox checkJueve; 
	private JCheckBox checkVierne;
	private JCheckBox checkSabado;
	
	private JComboBox<String> cmbTipoCliente ;
	private JScrollPane scrollPanel;
	private JTable table;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ClientesUI(ContClientes contClientes) {
		
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		pnClientes = new JPanel();
		pnClientes.setBackground(Color.DARK_GRAY);
		pnClientes.setForeground(Color.DARK_GRAY);
		pnClientes.setBounds(12, 12, 743, 726);
		pnClientes.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Modulo Clientes", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		add(pnClientes);
		pnClientes.setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(12, 45, 725, 20);
		pnClientes.add(panel);
		panel.setLayout(null);
		
		pnOpciones = new JPanel();
		pnOpciones.setBounds(0, 2, 725, 20);
		panel.add(pnOpciones);
		pnOpciones.setBackground(Color.DARK_GRAY);
		
		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(contClientes.atras());
		pnOpciones.setLayout(new GridLayout(0, 6, 0, 0));
		btnAtras.setForeground(Color.WHITE);
		btnAtras.setBackground(Color.DARK_GRAY);
		btnAtras.setIcon(new ImageIcon("img/gestion/AtrasCurva.png"));
		pnOpciones.add(btnAtras);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(contClientes.salir());
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(Color.DARK_GRAY);
		btnEliminar.setIcon(new ImageIcon("img/gestion/cancel.png"));
		btnEliminar.addActionListener(contClientes.eliminar());
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(contClientes.nuevo());
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setBackground(Color.DARK_GRAY);
		btnNuevo.setIcon(new ImageIcon("img/gestion/add.png"));
		pnOpciones.add(btnNuevo);
		
		btnGuardar = new JButton("Guardar");
		pnOpciones.add(btnGuardar);
		btnGuardar.addActionListener(contClientes.guardar());
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBackground(Color.DARK_GRAY);
		pnOpciones.add(btnEliminar);
		
		btnAbrirRuta = new JButton("Ruta");
		pnOpciones.add(btnAbrirRuta);
		btnAbrirRuta.addActionListener(contClientes.AbrirRutas());
		btnAbrirRuta.setForeground(Color.WHITE);
		btnAbrirRuta.setBackground(Color.DARK_GRAY);
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.DARK_GRAY);
		btnSalir.setIcon(new ImageIcon("img/gestion/SalirCurva.png"));
		pnOpciones.add(btnSalir);
		
		panel_1 = new JPanel();
		panel_1.setForeground(Color.GRAY);
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(324, 13, 407, 30);
		pnClientes.add(panel_1);
		panel_1.setLayout(new MigLayout("", "[grow]", "[]"));
		
		txtABuscar = new JTextField();
		txtABuscar.setForeground(Color.WHITE);
		txtABuscar.setBackground(new Color(64, 64, 64));
		panel_1.add(txtABuscar, "flowx,cell 0 0,growx");
		txtABuscar.setColumns(10);
		
		btnABuscar = new JButton("");
		btnABuscar.addActionListener(contClientes.buscar());
		btnABuscar.setVerticalAlignment(SwingConstants.TOP);
		btnABuscar.setBackground(Color.DARK_GRAY);
		btnABuscar.setIcon(new ImageIcon("img/gestion/buscar.png"));
		panel_1.add(btnABuscar, "cell 0 0");
		
		lblTipoCliente = new JLabel("Tipo Cliente:");
		lblTipoCliente.setForeground(Color.WHITE);
		lblTipoCliente.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblTipoCliente.setBounds(12, 18, 113, 25);
		pnClientes.add(lblTipoCliente);
		
		cmbTipoCliente = new JComboBox();
		cmbTipoCliente.setModel(new DefaultComboBoxModel(new String[] {"Salp", "Domicilio", "Comercial"}));
		cmbTipoCliente.addActionListener(contClientes.ActivarTipoUsuario());
		cmbTipoCliente.setBounds(114, 18, 200, 20);
		pnClientes.add(cmbTipoCliente);
		pnCliente = new JPanel();
		pnCliente.setBounds(12, 75, 726, 126);
		pnClientes.add(pnCliente);
		pnCliente.setLayout(null);
		pnCliente.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)),"", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnCliente.setBackground(Color.DARK_GRAY);
			
		lblRazonSocial = new JLabel("Razon Social:");
		lblRazonSocial.setForeground(Color.WHITE);
		lblRazonSocial.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblRazonSocial.setBounds(200, 5, 100, 25);
		pnCliente.add(lblRazonSocial);
		
		txtRazonSocial = new JTextField();
		txtRazonSocial.setColumns(10);
		txtRazonSocial.setBounds(300, 10, 412, 16);
		pnCliente.add(txtRazonSocial);
		
		lblTelefono = new JLabel("Telefono:");
		lblTelefono.setForeground(Color.WHITE);
		lblTelefono.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblTelefono.setBounds(10, 30, 69, 25);
		pnCliente.add(lblTelefono);
		
		lblDireccion = new JLabel("Direccion:");
		lblDireccion.setForeground(Color.WHITE);
		lblDireccion.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblDireccion.setBounds(200, 30, 81, 16);
		pnCliente.add(lblDireccion);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(86, 35, 100, 16);
		pnCliente.add(txtTelefono);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(300, 35, 412, 16);
		pnCliente.add(txtDireccion);
		
		lblRif = new JLabel("Rif:");
		lblRif.setForeground(Color.WHITE);
		lblRif.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblRif.setBounds(10, 5, 39, 25);
		pnCliente.add(lblRif);
		
		txtRif = new JTextField();
		txtRif.setColumns(10);
		txtRif.setBounds(86, 9, 100, 16);
		pnCliente.add(txtRif);
		
		txtId = new JTextField();
		txtId.setColumns(10);
		txtId.setBounds(54, 9, 20, 16);
		txtId.setVisible(false);
		pnCliente.add(txtId);
		
		lblDiaVisita = new JLabel("Dia Visitas: ");
		lblDiaVisita.setForeground(Color.WHITE);
		lblDiaVisita.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblDiaVisita.setBounds(12, 90, 100, 25);
		pnCliente.add(lblDiaVisita);
		
		pnCheckDia = new JPanel();
		pnCheckDia.setBackground(Color.GRAY);
		pnCheckDia.setBounds(92, 95, 350, 25);
		pnCliente.add(pnCheckDia);
		pnCheckDia.setLayout(new GridLayout(1, 0, 0, 0));
		
		checkLune = new JCheckBox("Lun.");
		pnCheckDia.add(checkLune);
		
		checkMarte = new JCheckBox("Mar.");
		pnCheckDia.add(checkMarte);
		
		checkMiercole = new JCheckBox("Mié.");
		pnCheckDia.add(checkMiercole);
		
		checkJueve = new JCheckBox("Jue.");
		pnCheckDia.add(checkJueve);
		
		checkVierne = new JCheckBox("Vie.");
		pnCheckDia.add(checkVierne);
		
		checkSabado = new JCheckBox("Sáb.");
		pnCheckDia.add(checkSabado);
		
		pnlRuta = new JPanel();
		pnlRuta.setBounds(10, 55, 703, 37);
		pnCliente.add(pnlRuta); 
		pnlRuta.setLayout(null);
		pnlRuta.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)),"Informacion de la Ruta", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnlRuta.setBackground(Color.DARK_GRAY);
		
		lblRuta = new JLabel("");
		lblRuta.setForeground(Color.WHITE);
		lblRuta.setBounds(12, 17, 691, 15);
		pnlRuta.add(lblRuta);
		
		pnTabla = new JPanel();
		pnTabla.setBounds(12, 203, 725, 511);
		pnClientes.add(pnTabla);
		pnTabla.setLayout(new GridLayout(0, 1, 0, 0));
	
		table = new JTable();
		table.addKeyListener(contClientes.keyPressTable());
		table.addMouseListener(contClientes.mostrarCliente());
		table.setBounds(0, 0, 1, 1);
		//pnTabla.add(table);
		
		scrollPanel = new JScrollPane();
		scrollPanel.add(table);
		pnTabla.add(scrollPanel);						
	}
	
	public void LimpiarTxt(){
	 txtId.setText("");
	 txtDireccion.setText("");
	 txtRazonSocial.setText("");
	 txtTelefono.setText("");
	 txtRif.setText("");
	 checkLune.setSelected(false);
	 checkMarte.setSelected(false);
	 checkMiercole.setSelected(false);
	 checkJueve.setSelected(false);
	 checkVierne.setSelected(false);
	 checkSabado.setSelected(false);
	 lblRuta.setText("");
	}

	public JPanel getPnOpciones() {
		return pnOpciones;
	}

	public void setPnOpciones(JPanel pnOpciones) {
		this.pnOpciones = pnOpciones;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JPanel getPnClientes() {
		return pnClientes;
	}

	public void setPnClientes(JPanel pnClientes) {
		this.pnClientes = pnClientes;
	}

	public JPanel getPanel_1() {
		return panel_1;
	}

	public void setPanel_1(JPanel panel_1) {
		this.panel_1 = panel_1;
	}

	public JPanel getPnTabla() {
		return pnTabla;
	}

	public void setPnTabla(JPanel pnTabla) {
		this.pnTabla = pnTabla;
	}

	public JPanel getPnDomicilioComercial() {
		return pnlRuta;
	}

	public void setPnDomicilioComercial(JPanel pnDomicilioComercial) {
		this.pnlRuta = pnDomicilioComercial;
	}

	public JPanel getPnCliente() {
		return pnCliente;
	}

	public void setPnCliente(JPanel pnCliente) {
		this.pnCliente = pnCliente;
	}

	public JLabel getLblTipoCliente() {
		return lblTipoCliente;
	}

	public void setLblTipoCliente(JLabel lblTipoCliente) {
		this.lblTipoCliente = lblTipoCliente;
	}

	public JLabel getLblRazonSocial() {
		return lblRazonSocial;
	}

	public void setLblRazonSocial(JLabel lblRazonSocial) {
		this.lblRazonSocial = lblRazonSocial;
	}

	public JLabel getLblTelefono() {
		return lblTelefono;
	}

	public void setLblTelefono(JLabel lblTelefono) {
		this.lblTelefono = lblTelefono;
	}

	public JLabel getLblDireccion() {
		return lblDireccion;
	}

	public void setLblDireccion(JLabel lblDireccion) {
		this.lblDireccion = lblDireccion;
	}

	public JTextField getTxtRazonSocial() {
		return txtRazonSocial;
	}

	public void setTxtRazonSocial(JTextField txtRazonSocial) {
		this.txtRazonSocial = txtRazonSocial;
	}



	public JTextField getTxtABuscar() {
		return txtABuscar;
	}

	public void setTxtABuscar(JTextField txtABuscar) {
		this.txtABuscar = txtABuscar;
	}

	public JTextField getTxtTelefono() {
		return txtTelefono;
	}

	public void setTxtTelefono(JTextField txtTelefono) {
		this.txtTelefono = txtTelefono;
	}

	public JTextField getTxtDireccion() {
		return txtDireccion;
	}

	public void setTxtDireccion(JTextField txtDireccion) {
		this.txtDireccion = txtDireccion;
	}

	public JButton getBtnNuevo() {
		return btnNuevo;
	}

	public void setBtnNuevo(JButton btnNuevo) {
		this.btnNuevo = btnNuevo;
	}
	public JButton getBtnAtras() {
		return btnAtras;
	}

	public void setBtnAtras(JButton btnAtras) {
		this.btnAtras = btnAtras;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public void setBtnSalir(JButton btnSalir) {
		this.btnSalir = btnSalir;
	}

	public JButton getBtnABuscar() {
		return btnABuscar;
	}

	public void setBtnABuscar(JButton btnABuscar) {
		this.btnABuscar = btnABuscar;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public JButton getBtnAbrirRuta() {
		return btnAbrirRuta;
	}

	public void setBtnAbrirRuta(JButton btnAbrirRuta) {
		this.btnAbrirRuta = btnAbrirRuta;
	}

	public JScrollPane getScrollPanel() {
		return scrollPanel;
	}

	public void setScrollPanel(JScrollPane scrollPanel) {
		this.scrollPanel = scrollPanel;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JComboBox<String> getCmbTipoCliente() {
		return cmbTipoCliente;
	}

	public void setCmbTipoCliente(JComboBox<String> cmbTipoCliente) {
		this.cmbTipoCliente = cmbTipoCliente;
	}

	public JTextField getTxtRif() {
		return txtRif;
	}

	public void setTxtRif(String txtRif) {
		this.txtRif.setText(txtRif);
	}	

	public JTextField getTxtId() {
		return txtId;
	}

	public void setTxtId(JTextField txtId) {
		this.txtId = txtId;
	}

	public void setTxtRif(JTextField txtRif) {
		this.txtRif = txtRif;
	}
	
	public JPanel getPnCheckDia() {
		return pnCheckDia;
	}

	public void setPnCheckDia(JPanel pnCheckDia) {
		this.pnCheckDia = pnCheckDia;
	}

	public JLabel getLblRif() {
		return lblRif;
	}

	public void setLblRif(JLabel lblRif) {
		this.lblRif = lblRif;
	}

	public JLabel getLblDiaVisita() {
		return lblDiaVisita;
	}

	public void setLblDiaVisita(JLabel lblDiaVisita) {
		this.lblDiaVisita = lblDiaVisita;
	}

	public JCheckBox getCheckLune() {
		return checkLune;
	}

	public void setCheckLune(JCheckBox checkLune) {
		this.checkLune = checkLune;
	}

	public JCheckBox getCheckMarte() {
		return checkMarte;
	}

	public void setCheckMarte(JCheckBox checkMarte) {
		this.checkMarte = checkMarte;
	}

	public JCheckBox getCheckMiercole() {
		return checkMiercole;
	}

	public void setCheckMiercole(JCheckBox checkMiercole) {
		this.checkMiercole = checkMiercole;
	}

	public JCheckBox getCheckJueve() {
		return checkJueve;
	}

	public void setCheckJueve(JCheckBox checkJueve) {
		this.checkJueve = checkJueve;
	}

	public JCheckBox getCheckVierne() {
		return checkVierne;
	}

	public void setCheckVierne(JCheckBox checkVierne) {
		this.checkVierne = checkVierne;
	}

	public JCheckBox getCheckSabado() {
		return checkSabado;
	}

	public void setCheckSabado(JCheckBox checkSabado) {
		this.checkSabado = checkSabado;
	}

	public JPanel getPnlRuta() {
		return pnlRuta;
	}

	public void setPnlRuta(JPanel pnlRuta) {
		this.pnlRuta = pnlRuta;
	}

	public JLabel getLblRuta() {
		return lblRuta;
	}

	public void setLblRuta(JLabel lblRuta) {
		this.lblRuta = lblRuta;
	}
	
}
