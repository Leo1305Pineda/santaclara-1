package santaclara.vista;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import net.miginfocom.swing.MigLayout;
import santaclara.controlador.ContUsuarios;
import santaclara.modelo.Usuario;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class UsuariosUI extends JPanel {
	
	private JPanel pnOpciones;
	private JPanel panel;
	private JPanel pnUsuarios;
	private JPanel panel_1;
	private JPanel pnTabla;
	private JPanel pnEditar; 
	
	private JButton btnNuevo;
	private JButton btnEditar;
	private JButton btnAtras;
	private JButton btnSalir;
	private JButton btnABuscar;
	private JButton btnEliminar;
	
	private JComboBox cmbTipoUsuario ;
	
	private JTable table;
	private JTableBinding binUsuarios; 
	
	private JScrollPane scrollPanel;
	
	private JTextField txtABuscar;
		
	private List<Usuario> camiones = new ArrayList<Usuario>();
	private JLabel lblTipoUsuario;
	private JPanel pnUsuario;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JLabel lblUserName;
	private JTextField txtUserName;
	private JTextField txtId;
	private JLabel lblCedula;
	private JLabel lblContrasena;
	private JLabel lblNombre;
	private JLabel lblReContrasena;
	private JTextField txtCedula;
	private JTextField txtContrasena;
	private JTextField txtNombre;
	private JTextField txtReContrasena;
	private JPanel pnRuta;
	private JPanel pnZona;
	private JComboBox cmbZona;
	private JLabel lblZona;
	private JButton btnAbrirZona;
	private JButton btnAbrirRuta;
	
	public UsuariosUI(ContUsuarios contUsuarios,List<Usuario> Usuarios) {
		this.camiones = Usuarios;
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		pnUsuarios = new JPanel();
		pnUsuarios.setBackground(Color.DARK_GRAY);
		pnUsuarios.setForeground(Color.DARK_GRAY);
		pnUsuarios.setBounds(12, 12, 776, 483);
		pnUsuarios.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Modulo Usuarios", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		add(pnUsuarios);
		pnUsuarios.setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(12, 45, 752, 42);
		pnUsuarios.add(panel);
		panel.setLayout(null);
		
		pnOpciones = new JPanel();
		pnOpciones.setBounds(0, 0, 752, 34);
		panel.add(pnOpciones);
		pnOpciones.setBackground(Color.DARK_GRAY);
		pnOpciones.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Opciones", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnOpciones.setLayout(null);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(contUsuarios.nuevo());
		btnNuevo.setBounds(171, 15, 120, 16);
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setBackground(Color.DARK_GRAY);
		btnNuevo.setIcon(new ImageIcon("img/gestion/add.png"));
		pnOpciones.add(btnNuevo);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(contUsuarios.modificar());
		btnEditar.setBounds(331, 15, 120, 16);
		btnEditar.setForeground(Color.WHITE);
		btnEditar.setBackground(Color.DARK_GRAY);
		btnEditar.setIcon(new ImageIcon("img/gestion/Modificara.png"));
		pnOpciones.add(btnEditar);
		
		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(contUsuarios.atras());
		btnAtras.setBounds(5, 15, 120, 16);
		btnAtras.setForeground(Color.WHITE);
		btnAtras.setBackground(Color.DARK_GRAY);
		btnAtras.setIcon(new ImageIcon("img/gestion/AtrasCurva.png"));
		pnOpciones.add(btnAtras);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(contUsuarios.salir());
		btnSalir.setBounds(625, 15, 120, 16);
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.DARK_GRAY);
		btnSalir.setIcon(new ImageIcon("img/gestion/SalirCurva.png"));
		pnOpciones.add(btnSalir);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(Color.DARK_GRAY);
		btnEliminar.setIcon(new ImageIcon("img/gestion/cancel.png"));
		btnEliminar.addActionListener(contUsuarios.eliminar());
		btnEliminar.setBounds(495, 15, 120, 16);
		pnOpciones.add(btnEliminar);
		
		pnTabla = new JPanel();
		pnTabla.setBounds(12, 85, 752, 159);
		pnUsuarios.add(pnTabla);
		pnTabla.setLayout(null);
		
		scrollPanel = new JScrollPane();
		scrollPanel.setBounds(0, 0, 752, 155);
		pnTabla.add(scrollPanel);
		
		table = new JTable();
		table.setBounds(0, 0, 1, 1);
		pnTabla.add(table);
		
		panel_1 = new JPanel();
		panel_1.setForeground(Color.GRAY);
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(324, 13, 440, 30);
		pnUsuarios.add(panel_1);
		panel_1.setLayout(new MigLayout("", "[grow]", "[]"));
		
		txtABuscar = new JTextField();
		txtABuscar.setForeground(Color.WHITE);
		txtABuscar.setBackground(new Color(64, 64, 64));
		panel_1.add(txtABuscar, "flowx,cell 0 0,growx");
		txtABuscar.setColumns(10);
		
		btnABuscar = new JButton("");
		btnABuscar.addActionListener(contUsuarios.buscar());
		btnABuscar.setVerticalAlignment(SwingConstants.TOP);
		btnABuscar.setBackground(Color.DARK_GRAY);
		btnABuscar.setIcon(new ImageIcon("img/gestion/buscar.png"));
		panel_1.add(btnABuscar, "cell 0 0");
		
		lblTipoUsuario = new JLabel("Tipo Usuario:");
		lblTipoUsuario.setForeground(Color.WHITE);
		lblTipoUsuario.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblTipoUsuario.setBounds(12, 18, 113, 25);
		pnUsuarios.add(lblTipoUsuario);
		
		cmbTipoUsuario = new JComboBox();
		cmbTipoUsuario.setModel(new DefaultComboBoxModel(new String[] {"Todos", "JefeVenta", "Vendedor", "Concesionario"}));
		cmbTipoUsuario.setBounds(114, 18, 200, 20);
		pnUsuarios.add(cmbTipoUsuario);
		
		pnEditar = new JPanel();
		pnEditar.setBackground(Color.GRAY);
		pnEditar.setBounds(12, 256, 752, 215);
		pnUsuarios.add(pnEditar);
		pnEditar.setLayout(null);
		
		pnUsuario = new JPanel();
		pnUsuario.setLayout(null);
		pnUsuario.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)),"Editar Usuario", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnUsuario.setBackground(Color.DARK_GRAY);
		pnUsuario.setBounds(0, 5, 752, 101);
		pnEditar.add(pnUsuario);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBackground(Color.DARK_GRAY);
		btnGuardar.setBounds(625, 17, 120, 16);
		pnUsuario.add(btnGuardar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(Color.DARK_GRAY);
		btnCancelar.setBounds(5, 17, 120, 16);
		pnUsuario.add(btnCancelar);
		
		lblUserName = new JLabel("UserName:");
		lblUserName.setForeground(Color.WHITE);
		lblUserName.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblUserName.setBounds(264, 17, 81, 25);
		pnUsuario.add(lblUserName);
		
		txtUserName = new JTextField();
		txtUserName.setColumns(10);
		txtUserName.setBounds(363, 20, 208, 16);
		pnUsuario.add(txtUserName);
		
		txtId = new JTextField();
		txtId.setColumns(10);
		txtId.setBounds(163, 9, 54, 19);
		pnUsuario.add(txtId);
		
		lblCedula = new JLabel("Cedula:");
		lblCedula.setForeground(Color.WHITE);
		lblCedula.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblCedula.setBounds(137, 35, 69, 25);
		pnUsuario.add(lblCedula);
		
		lblContrasena = new JLabel("Contraseña:");
		lblContrasena.setForeground(Color.WHITE);
		lblContrasena.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblContrasena.setBounds(137, 70, 103, 25);
		pnUsuario.add(lblContrasena);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblNombre.setBounds(396, 40, 69, 25);
		pnUsuario.add(lblNombre);
		
		lblReContrasena = new JLabel("Repetir:");
		lblReContrasena.setForeground(Color.WHITE);
		lblReContrasena.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblReContrasena.setBounds(396, 70, 69, 25);
		pnUsuario.add(lblReContrasena);
		
		txtCedula = new JTextField();
		txtCedula.setColumns(10);
		txtCedula.setBounds(225, 40, 168, 16);
		pnUsuario.add(txtCedula);
		
		txtContrasena = new JTextField();
		txtContrasena.setColumns(10);
		txtContrasena.setBounds(225, 72, 168, 16);
		pnUsuario.add(txtContrasena);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(454, 43, 188, 16);
		pnUsuario.add(txtNombre);
		
		txtReContrasena = new JTextField();
		txtReContrasena.setColumns(10);
		txtReContrasena.setBounds(454, 73, 188, 16);
		pnUsuario.add(txtReContrasena);
		
		pnRuta = new JPanel();
		pnRuta.setLayout(null);
		pnRuta.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Editar Rutas", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnRuta.setBackground(Color.DARK_GRAY);
		pnRuta.setBounds(0, 110, 752, 50);
		pnEditar.add(pnRuta);
		
		btnAbrirRuta = new JButton("Abrir Ruta");
		btnAbrirRuta.setForeground(Color.WHITE);
		btnAbrirRuta.setBackground(Color.DARK_GRAY);
		btnAbrirRuta.setBounds(610, 12, 130, 16);
		pnRuta.add(btnAbrirRuta);
		
		pnZona = new JPanel();
		pnZona.setLayout(null);
		pnZona.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Editar Zona", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnZona.setBackground(Color.DARK_GRAY);
		pnZona.setBounds(0, 165, 752, 48);
		pnEditar.add(pnZona);
		
		cmbZona = new JComboBox();
		cmbZona.setBounds(68, 14, 199, 24);
		pnZona.add(cmbZona);
		
		lblZona = new JLabel("Zona:");
		lblZona.setForeground(Color.WHITE);
		lblZona.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblZona.setBounds(12, 14, 69, 25);
		pnZona.add(lblZona);
		
		btnAbrirZona = new JButton("Abrir Zona");
		btnAbrirZona.setForeground(Color.WHITE);
		btnAbrirZona.setBackground(Color.DARK_GRAY);
		btnAbrirZona.setBounds(296, 14, 142, 16);
		pnZona.add(btnAbrirZona);
		
	}
	
	public void activarBinding(List<Usuario> Usuarios) {
		// TODO Auto-generated method stub
		String tipo;
		tipo = (String) cmbTipoUsuario.getSelectedItem();
		pnTabla.setVisible(true);
		table = new JTable();
		scrollPanel.setViewportView(table);
		binUsuarios = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,
    			Usuarios,table);
		
		BeanProperty idUsuario  = BeanProperty.create("id");
		BeanProperty UserNameUsuario = BeanProperty.create("placa");
		BeanProperty CedulaUsuario = BeanProperty.create("color");
		BeanProperty NombreUsuario = BeanProperty.create("capacidad");
		

		BeanProperty camionConcesionario = null ;
		BeanProperty rutaConcesionario= null;
		BeanProperty ruta1Vendedor= null;
		BeanProperty ruta2Vendedor= null;
		BeanProperty ruta3Vendedor= null;
		BeanProperty zonaJejeVenta= null;
		
		if(!tipo.equals("Todos"))
		{
		if(tipo.equals("Concesionario"))
		{
			camionConcesionario = BeanProperty.create("camion");
			rutaConcesionario = BeanProperty.create("ruta");
		}
		else if(tipo.equals("Vendedor"))
		{
			ruta1Vendedor = BeanProperty.create("ruta1");
			ruta2Vendedor = BeanProperty.create("ruta2");
			ruta3Vendedor = BeanProperty.create("ruta3");
		}
		else if(tipo.equals("JefeVenta"))
		{
			zonaJejeVenta = BeanProperty.create("zona");
		}
		}
		
	    binUsuarios.addColumnBinding(idUsuario).setColumnClass(Integer.class).setColumnName("id "+tipo);
	    binUsuarios.addColumnBinding(UserNameUsuario).setColumnClass(String.class).setColumnName("UserName");
	    binUsuarios.addColumnBinding(CedulaUsuario).setColumnClass(String.class).setColumnName("Cedula");
	    binUsuarios.addColumnBinding(NombreUsuario).setColumnClass(String.class).setColumnName("Nombre");
	   
	    if(!tipo.equals("Todos"))
	    {
	    if(tipo.equals("Concesionario"))
	    {
	    	binUsuarios.addColumnBinding(camionConcesionario).setColumnClass(String.class).setColumnName("Camion");
		    binUsuarios.addColumnBinding(rutaConcesionario).setColumnClass(String.class).setColumnName("Ruta");
	    }
	    else if(tipo.equals("Vendedor"))
	    {
	    	binUsuarios.addColumnBinding(ruta1Vendedor).setColumnClass(String.class).setColumnName("Ruta1");
		    binUsuarios.addColumnBinding(ruta2Vendedor).setColumnClass(String.class).setColumnName("Ruta2");
		    binUsuarios.addColumnBinding(ruta3Vendedor).setColumnClass(String.class).setColumnName("Ruta3");
	    }
	    else if(tipo.equals("JefeVenta"))
	    {
	    	binUsuarios.addColumnBinding(zonaJejeVenta).setColumnClass(String.class).setColumnName("Zona");
	    }
	    binUsuarios.bind();
	    }
	}
	public void activarNuevoCamion() {
		// TODO Auto-generated method stub
		txtId.setVisible(false);
		pnEditar.setBounds(12, 81, 520, 133);
		pnEditar.setVisible(true);
		getTxtCedula().setText("");
		getTxtNombre().setText("");
		getTxtReContrasena().setText("");
		getTxtContrasena().setText("");
		getTxtUserName().setText("");
		pnTabla.setVisible(false);
	}
	
	public void quitarNuevo() {
		// TODO Auto-generated method stub
		pnEditar.setVisible(false);
		
		pnTabla.setVisible(true);
		scrollPanel.setVisible(true);
		
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

	public JPanel getPnUsuarios() {
		return pnUsuarios;
	}

	public void setPnUsuarios(JPanel pnUsuarios) {
		this.pnUsuarios = pnUsuarios;
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

	public JPanel getPnEditar() {
		return pnEditar;
	}

	public void setPnEditar(JPanel pnEditar) {
		this.pnEditar = pnEditar;
	}

	public JButton getBtnNuevo() {
		return btnNuevo;
	}

	public void setBtnNuevo(JButton btnNuevo) {
		this.btnNuevo = btnNuevo;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public void setBtnEditar(JButton btnEditar) {
		this.btnEditar = btnEditar;
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

	public JComboBox getCmbTipoUsuario() {
		return cmbTipoUsuario;
	}

	public void setCmbTipoUsuario(JComboBox cmbTipoUsuario) {
		this.cmbTipoUsuario = cmbTipoUsuario;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JTableBinding getBinUsuarios() {
		return binUsuarios;
	}

	public void setBinUsuarios(JTableBinding binUsuarios) {
		this.binUsuarios = binUsuarios;
	}

	public JScrollPane getScrollPanel() {
		return scrollPanel;
	}

	public void setScrollPanel(JScrollPane scrollPanel) {
		this.scrollPanel = scrollPanel;
	}

	public JTextField getTxtABuscar() {
		return txtABuscar;
	}

	public void setTxtABuscar(JTextField txtABuscar) {
		this.txtABuscar = txtABuscar;
	}

	public List<Usuario> getCamiones() {
		return camiones;
	}

	public void setCamiones(List<Usuario> camiones) {
		this.camiones = camiones;
	}

	public JLabel getLblTipoUsuario() {
		return lblTipoUsuario;
	}

	public void setLblTipoUsuario(JLabel lblTipoUsuario) {
		this.lblTipoUsuario = lblTipoUsuario;
	}

	public JPanel getPnUsuario() {
		return pnUsuario;
	}

	public void setPnUsuario(JPanel pnUsuario) {
		this.pnUsuario = pnUsuario;
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

	public JLabel getLblUserName() {
		return lblUserName;
	}

	public void setLblUserName(JLabel lblUserName) {
		this.lblUserName = lblUserName;
	}

	public JTextField getTxtUserName() {
		return txtUserName;
	}

	public void setTxtUserName(JTextField txtUserName) {
		this.txtUserName = txtUserName;
	}

	public JTextField getTxtId() {
		return txtId;
	}

	public void setTxtId(JTextField txtID) {
		this.txtId = txtID;
	}

	public JLabel getLblCedula() {
		return lblCedula;
	}

	public void setLblCedula(JLabel lblCedula) {
		this.lblCedula = lblCedula;
	}

	public JLabel getLblContrasena() {
		return lblContrasena;
	}

	public void setLblContrasena(JLabel lblContrasena) {
		this.lblContrasena = lblContrasena;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public void setLblNombre(JLabel lblNombre) {
		this.lblNombre = lblNombre;
	}

	public JLabel getLblReContrasena() {
		return lblReContrasena;
	}

	public void setLblReContrasena(JLabel lblReContrasena) {
		this.lblReContrasena = lblReContrasena;
	}

	public JTextField getTxtCedula() {
		return txtCedula;
	}

	public void setTxtCedula(JTextField txtCedula) {
		this.txtCedula = txtCedula;
	}

	public JTextField getTxtContrasena() {
		return txtContrasena;
	}

	public void setTxtContrasena(JTextField txtContrasena) {
		this.txtContrasena = txtContrasena;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JTextField getTxtReContrasena() {
		return txtReContrasena;
	}

	public void setTxtReContrasena(JTextField txtReContrasena) {
		this.txtReContrasena = txtReContrasena;
	}

	public JPanel getPnRuta() {
		return pnRuta;
	}

	public void setPnRuta(JPanel pnRuta) {
		this.pnRuta = pnRuta;
	}

	public JPanel getPnZona() {
		return pnZona;
	}

	public void setPnZona(JPanel pnZona) {
		this.pnZona = pnZona;
	}

	public JComboBox getCmbZona() {
		return cmbZona;
	}

	public void setCmbZona(JComboBox cmbZona) {
		this.cmbZona = cmbZona;
	}

	public JLabel getLblZona() {
		return lblZona;
	}

	public void setLblZona(JLabel lblZona) {
		this.lblZona = lblZona;
	}

	public JButton getBtnAbrirZona() {
		return btnAbrirZona;
	}

	public void setBtnAbrirZona(JButton btnAbrirZona) {
		this.btnAbrirZona = btnAbrirZona;
	}

	public JButton getBtnAbrirRuta() {
		return btnAbrirRuta;
	}

	public void setBtnAbrirRuta(JButton btnAbrirRuta) {
		this.btnAbrirRuta = btnAbrirRuta;
	}
	

}
