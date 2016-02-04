package santaclara.vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import net.miginfocom.swing.MigLayout;
import santaclara.controlador.ContUsuarios;
import santaclara.modelo.Camion;
import santaclara.modelo.Concesionario;
import santaclara.modelo.JefeVenta;
import santaclara.modelo.Ruta;
import santaclara.modelo.Usuario;
import santaclara.modelo.Vendedor;
import santaclara.modelo.Zona;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;

import javax.swing.table.DefaultTableModel;

import org.jdesktop.beansbinding.ObjectProperty;

import javax.swing.ListSelectionModel;

import java.awt.event.MouseMotionAdapter;
import java.awt.FlowLayout;

@SuppressWarnings("serial")
public class UsuariosUI extends JPanel {
	
	private JPanel pnOpciones;
	private JPanel pnUsuarios;
	private JPanel panel_1;
	private JPanel pnTabla;
	private JPanel pnEditar; 
	private JPanel pnRutas;
	private JPanel pnEditarRuta;
	private JPanel pnZona;
	private JPanel pnUsuario;
	private JPanel pnCamion;
	private JPanel panelMensaje = new JPanel();
	
	private JLabel lblTipoUsuario;
	private JLabel lblUserName;
	private JLabel lblCedula;
	private JLabel lblContrasena;
	private JLabel lblNombre;
	private JLabel lblReContrasena;
	private JLabel lblZona;
	private JLabel lblCamion;
	private JLabel lblMensaje;
	private JLabel label = new JLabel();
	private JLabel label1 = new JLabel();
	
	private JTextField txtUserName;
	private JTextField txtId;
	private JTextField txtABuscar;
	private JTextField txtCedula;
	private JTextField txtNombre;
	
	private JPasswordField txtContrasena;
	private JPasswordField txtReContrasena;
	
	private JButton btnNuevo;
	private JButton btnEditar;
	private JButton btnAtras;
	private JButton btnSalir;
	private JButton btnABuscar;
	private JButton btnEliminar;
	private JButton btnQuitar;
	private JButton btnAgregar;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JButton btnAbrirRuta;
	private JButton btnZona;
	private JButton btnCamion;
	
	private JComboBox<Camion> cmbCamion;
	private JComboBox<String> cmbTipoUsuario ;
	private JComboBox<Ruta> comboRutas;
	private JComboBox<Zona> cmbZona;
	
	private JScrollPane scrollPane;
	private JScrollPane scrollPanel;
	
	private JTable tablaRutas;
	private JTable table;
	
	@SuppressWarnings("rawtypes")
	private JTableBinding binUsuarios;
	@SuppressWarnings("rawtypes")
	private JTableBinding  binRutas;
	
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	private List<Ruta> rutasVendedores = new ArrayList<Ruta>();
	private List<Ruta> rutas = new ArrayList<Ruta>();
	private List<Zona> zonas = new ArrayList<Zona>();
	private List<Camion> camiones = new ArrayList<Camion>();
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public UsuariosUI(ContUsuarios contUsuarios,List<Usuario> usuarios,List<Ruta> rutas,List<Zona> zonas,List<Camion> camiones) {
		this.usuarios = usuarios;
		this.rutas = rutas;
		this.zonas = zonas;
		this.camiones = camiones;
		
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		pnUsuarios = new JPanel();
		pnUsuarios.setBackground(Color.DARK_GRAY);
		pnUsuarios.setForeground(Color.DARK_GRAY);
		pnUsuarios.setBounds(12, 12, 876, 505);
		pnUsuarios.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Modulo Usuarios", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		add(pnUsuarios);
		pnUsuarios.setLayout(null);
		
		pnTabla = new JPanel();
		pnTabla.setBounds(12, 85, 852, 103);
		pnUsuarios.add(pnTabla);
		pnTabla.setLayout(null);
		
		table = new JTable();
		table.setBounds(0, 0, 0, 0);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		pnTabla.add(table);
		
		scrollPanel = new JScrollPane();
		scrollPanel.setBounds(0, 0, 852, 100);
		pnTabla.add(scrollPanel);
		
		panel_1 = new JPanel();
		panel_1.setForeground(Color.GRAY);
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(324, 13, 540, 30);
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
		cmbTipoUsuario.addActionListener(contUsuarios.ActivarTipoUsuario());
		cmbTipoUsuario.setBounds(114, 18, 200, 20);
		pnUsuarios.add(cmbTipoUsuario);
		
		pnEditar = new JPanel();
		pnEditar.setBackground(Color.GRAY);
		pnEditar.setBounds(12, 200, 852, 328);
		pnUsuarios.add(pnEditar);
		pnEditar.setLayout(null);
		
		pnUsuario = new JPanel();
		pnUsuario.setLayout(null);
		pnUsuario.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)),"Editar Usuario", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnUsuario.setBackground(Color.DARK_GRAY);
		pnUsuario.setBounds(0, 5, 852, 100);
		pnEditar.add(pnUsuario);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(contUsuarios.guardar());
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBackground(Color.DARK_GRAY);
		btnGuardar.setBounds(720, 17, 120, 16);
		pnUsuario.add(btnGuardar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				quitarNuevo();
			}
		});
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(Color.DARK_GRAY);
		btnCancelar.setBounds(720, 45, 120, 16);
		pnUsuario.add(btnCancelar);
		
		lblUserName = new JLabel("UserName:");
		lblUserName.setForeground(Color.WHITE);
		lblUserName.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblUserName.setBounds(520, 17, 81, 25);
		pnUsuario.add(lblUserName);
		
		txtUserName = new JTextField();
		txtUserName.setColumns(10);
		txtUserName.setBounds(612, 21, 102, 16);
		pnUsuario.add(txtUserName);
		
		txtId = new JTextField();
		txtId.setColumns(10); 
		txtId.setBounds(163, 9, 54, 19);
		txtId.setVisible(false);
		pnUsuario.add(txtId);
		
		lblCedula = new JLabel("Cedula:");
		lblCedula.setForeground(Color.WHITE);
		lblCedula.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblCedula.setBounds(22, 13, 69, 25);
		pnUsuario.add(lblCedula);
		
		lblContrasena = new JLabel("Contraseña:");
		lblContrasena.setForeground(Color.WHITE);
		lblContrasena.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblContrasena.setBounds(22, 51, 103, 25);
		pnUsuario.add(lblContrasena);
		
		lblNombre = new JLabel("Nombre Y Apellido:");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblNombre.setBounds(228, 17, 167, 25);
		pnUsuario.add(lblNombre);
		
		lblReContrasena = new JLabel("Repetir Contraseña:");
		lblReContrasena.setForeground(Color.WHITE);
		lblReContrasena.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblReContrasena.setBounds(228, 51, 157, 25);
		pnUsuario.add(lblReContrasena);
		
		txtCedula = new JTextField();
		txtCedula.setColumns(10);
		txtCedula.setBounds(110, 17, 100, 16);
		pnUsuario.add(txtCedula);
		
		txtContrasena = new JPasswordField();
		txtContrasena.setColumns(10);
		txtContrasena.setBounds(110, 55, 100, 16);
		pnUsuario.add(txtContrasena);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(382, 17, 126, 16);
		pnUsuario.add(txtNombre);
		
		txtReContrasena = new JPasswordField();
		txtReContrasena.setColumns(10);
		txtReContrasena.setBounds(382, 55, 126, 16);
		pnUsuario.add(txtReContrasena);
		
		pnEditarRuta = new JPanel();
		pnEditarRuta.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) { 
				panelMensaje.repaint();
				panelMensaje.setBounds(0, 0, 150, 30);
				panelMensaje.setLocation(e.getPoint());
				panelMensaje.setBackground(Color.DARK_GRAY);
				label.repaint();
				label.setText("  Ruta Asignada ");
				label.setForeground(Color.CYAN);
				label1.repaint();
				label1.setText(" Ruta Disponibles");
				label1.setForeground(Color.yellow);
				label.setBounds(0, 0, 150, 15);
				label1.setBounds(0, 15, 150, 15);
				panelMensaje.add(label);
				panelMensaje.add(label1);
				
				pnEditarRuta.add(panelMensaje);
			}
		});
		pnEditarRuta.setLayout(null);
		String formatString = new String("  Id Ruta                        "+
				 "Nombre Ruta              "+
                "Id Zona                       "+
				 "Nombre Zona              ");
		pnEditarRuta.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)),formatString, TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnEditarRuta.setBackground(Color.DARK_GRAY);
		pnEditarRuta.setBounds(0, 104, 852, 169);
		pnEditar.add(pnEditarRuta);
		
		pnRutas = new JPanel();
		pnRutas.setBounds(10, 35, 709, 70);
		pnEditarRuta.add(pnRutas);
		pnRutas.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 709, 70);
		pnRutas.add(scrollPane);
		tablaRutas = new JTable();
		scrollPane.setViewportView(tablaRutas);
		
		comboRutas = new JComboBox();
		
		comboRutas.setBounds(10, 12, 618, 24);
		comboRutas.setRenderer(contUsuarios.rendererComboRuta());
	
		pnEditarRuta.add(comboRutas);
		
		activarJComboBoxBindingRuta();
		
		btnAgregar = new JButton("+");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Boolean enc = false;
				for(Ruta ruta1: rutasVendedores) if (ruta1.equals(comboRutas.getSelectedItem())) enc = true;
				if (!enc) rutasVendedores.add((Ruta) comboRutas.getSelectedItem()); 
				activarBindingRutas(rutasVendedores);
			}
		});
		btnAgregar.setForeground(Color.WHITE);
		btnAgregar.setBackground(Color.DARK_GRAY);
		btnAgregar.setBounds(626, 12, 44, 24);
		pnEditarRuta.add(btnAgregar);
		
		btnQuitar = new JButton("-");
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(Ruta ruta1: rutasVendedores){
					if(ruta1.getId().equals(((Ruta)comboRutas.getSelectedItem()).getId())){
						rutasVendedores.remove(ruta1);
						break;
					}
				}
				activarBindingRutas(rutasVendedores);
			}
		});
		btnQuitar.setForeground(Color.WHITE);
		btnQuitar.setBackground(Color.DARK_GRAY);
		btnQuitar.setBounds(672, 12, 44, 24);
		pnEditarRuta.add(btnQuitar);
		
		lblMensaje = new JLabel();
		lblMensaje.setBounds(10, 110, 830, 54);
		pnEditarRuta.add(lblMensaje);
		lblMensaje.setForeground(Color.WHITE);
		
		btnAbrirRuta = new JButton("Ruta");
		btnAbrirRuta.addActionListener(contUsuarios.abrirRuta());
		btnAbrirRuta.setBounds(725, 16, 120, 16);
		pnEditarRuta.add(btnAbrirRuta);
		btnAbrirRuta.setForeground(Color.WHITE);
		btnAbrirRuta.setBackground(Color.DARK_GRAY);
		
		pnZona = new JPanel();
		pnZona.setLayout(null);
		pnZona.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Editar Zona", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnZona.setBackground(Color.DARK_GRAY);
		pnZona.setBounds(0, 253, 409, 63);
		pnEditar.add(pnZona);
		
		cmbZona = new JComboBox();
		cmbZona.setBounds(56, 22, 248, 24);
		cmbZona.setRenderer(new ListCellRenderer() {
			
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				// TODO Auto-generated method stub
				Zona zona = (Zona)value;
				return new JLabel(zona.getDescripcion());	
			}
		});
	
		pnZona.add(cmbZona);
		
		activarJComboBoxBindingZona();
		
		lblZona = new JLabel("Zona:");
		lblZona.setForeground(Color.WHITE);
		lblZona.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblZona.setBounds(12, 22, 50, 25);
		pnZona.add(lblZona);
		
		btnZona = new JButton("Zona");
		btnZona.addActionListener(contUsuarios.abrirZonas());
		btnZona.setForeground(Color.WHITE);
		btnZona.setBackground(Color.DARK_GRAY);
		btnZona.setBounds(316, 22, 93, 24);
		pnZona.add(btnZona);
		
		pnCamion = new JPanel();
		pnCamion.setLayout(null);
		pnCamion.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Editar Camion", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnCamion.setBackground(Color.DARK_GRAY);
		pnCamion.setBounds(405, 253, 435, 63);
		pnEditar.add(pnCamion);
		
		cmbCamion = new JComboBox();
		cmbCamion.setBounds(75, 22, 151, 24);
		cmbCamion.setRenderer(new ListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				// TODO Auto-generated method stub
				Camion camion = (Camion)value;
				
				JPanel pnCamion = new JPanel();
				
				pnCamion.add(new JTextField(camion.getPlaca().toString()));
				pnCamion.add(new JTextField(camion.getMarca().toString()));
				pnCamion.add(new JTextField(camion.getModelo().toString()));
				pnCamion.add(new JTextField(camion.getAno().toString()));
				pnCamion.add(new JTextField(camion.getColor()));
				pnCamion.add(new JTextField(camion.getCapacidad().toString()));
				pnCamion.setLayout(new GridLayout(1, 0, 0, 0));
				
				return pnCamion;	
			}
		});
		pnCamion.add(cmbCamion);
		
		activarJComboBoxBindingCamion();
	
		lblCamion = new JLabel("Camion:");
		lblCamion.setForeground(Color.WHITE);
		lblCamion.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblCamion.setBounds(12, 22, 68, 25);
		pnCamion.add(lblCamion);
		
		btnCamion = new JButton("Camion");
		btnCamion.addActionListener(contUsuarios.abrirCamiones());
		btnCamion.setForeground(Color.WHITE);
		btnCamion.setBackground(Color.DARK_GRAY);
		btnCamion.setBounds(312, 22, 120, 16);
		pnCamion.add(btnCamion);
		
		pnOpciones = new JPanel();
		pnOpciones.setBounds(12, 48, 630, 36);
		pnUsuarios.add(pnOpciones);
		pnOpciones.setBackground(Color.DARK_GRAY);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(cmbTipoUsuario.getSelectedItem().equals("Todos")) btnNuevo.setEnabled(false);
				else btnNuevo.setEnabled(true);
				if(btnNuevo.isEnabled()){
					btnNuevo.setToolTipText("Nuevo "+cmbTipoUsuario.getSelectedItem().toString());
				}
				else btnNuevo.setToolTipText("Seleccione el Tipo de Usuario");
			}
		});
		btnNuevo.addActionListener(contUsuarios.nuevo());
		pnOpciones.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(contUsuarios.atras());
		btnAtras.setForeground(Color.WHITE);
		btnAtras.setBackground(Color.DARK_GRAY);
		btnAtras.setIcon(new ImageIcon("img/gestion/AtrasCurva.png"));
		pnOpciones.add(btnAtras);
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setBackground(Color.DARK_GRAY);
		btnNuevo.setIcon(new ImageIcon("img/gestion/add.png"));
		btnNuevo.setEnabled(false);
		pnOpciones.add(btnNuevo);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(contUsuarios.modificar());
		btnEditar.setForeground(Color.WHITE);
		btnEditar.setBackground(Color.DARK_GRAY);
		btnEditar.setIcon(new ImageIcon("img/gestion/Modificara.png"));
		pnOpciones.add(btnEditar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(Color.DARK_GRAY);
		btnEliminar.setIcon(new ImageIcon("img/gestion/cancel.png"));
		btnEliminar.addActionListener(contUsuarios.eliminar());
		pnOpciones.add(btnEliminar);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(contUsuarios.salir());
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.DARK_GRAY);
		btnSalir.setIcon(new ImageIcon("img/gestion/SalirCurva.png"));
		pnOpciones.add(btnSalir);
		initDataBindings();
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void activarBindingVendedores(List<Vendedor> vendedores) {
		pnTabla.setVisible(true);
		table = new JTable();
		
		scrollPanel.setViewportView(table);
		binUsuarios = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,
    			vendedores,table);
		BeanProperty idUsuario  = BeanProperty.create("id");
		BeanProperty UserNameUsuario = BeanProperty.create("username");
		BeanProperty CedulaUsuario = BeanProperty.create("cedula");
		BeanProperty NombreUsuario = BeanProperty.create("nombre");
		BeanProperty Rutas = BeanProperty.create("strRutas");	
		
		binUsuarios.addColumnBinding(idUsuario).setColumnClass(Integer.class).setColumnName("idVendedor ");
		binUsuarios.addColumnBinding(UserNameUsuario).setColumnClass(String.class).setColumnName("UserName");
		binUsuarios.addColumnBinding(CedulaUsuario).setColumnClass(String.class).setColumnName("Cedula");
		binUsuarios.addColumnBinding(NombreUsuario).setColumnClass(String.class).setColumnName("Nombre");
		binUsuarios.addColumnBinding(Rutas).setColumnClass(List.class).setColumnName("Rutas");
		
	
	    binUsuarios.bind();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void activarBindingJefeVentas(List<JefeVenta> jefeVentas) {
		pnTabla.setVisible(true);
		table = new JTable();
		scrollPanel.setViewportView(table);
		pnTabla.setBounds(12, 85, 852, 408);
		scrollPanel.setBounds(0, 0, 852, 408);
		binUsuarios = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,
				jefeVentas,table);
		BeanProperty idUsuario  = BeanProperty.create("id");
		BeanProperty UserNameUsuario = BeanProperty.create("username");
		BeanProperty CedulaUsuario = BeanProperty.create("cedula");
		BeanProperty NombreUsuario = BeanProperty.create("nombre");
		
		BeanProperty zonaJefeVenta = BeanProperty.create("zona.descripcion");
		
		binUsuarios.addColumnBinding(idUsuario).setColumnClass(Integer.class).setColumnName("idJefeVenta ");
		binUsuarios.addColumnBinding(UserNameUsuario).setColumnClass(String.class).setColumnName("UserName");
		binUsuarios.addColumnBinding(CedulaUsuario).setColumnClass(String.class).setColumnName("Cedula");
		binUsuarios.addColumnBinding(NombreUsuario).setColumnClass(String.class).setColumnName("Nombre");
		
		binUsuarios.addColumnBinding(zonaJefeVenta).setColumnClass(String.class).setColumnName("Zona");
	    binUsuarios.bind();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void activarBindingConcesionarios(List<Concesionario> concesionarios) {
		pnTabla.setVisible(true);
		table = new JTable();
		pnTabla.setBounds(12, 85, 852, 408);
		scrollPanel.setBounds(0, 0, 852, 408);
		scrollPanel.setViewportView(table);
		binUsuarios = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,
				concesionarios,table);
		BeanProperty idUsuario  = BeanProperty.create("id");
		BeanProperty UserNameUsuario = BeanProperty.create("username");
		BeanProperty CedulaUsuario = BeanProperty.create("cedula");
		BeanProperty NombreUsuario = BeanProperty.create("nombre");
		
		BeanProperty camionConcesionario = BeanProperty.create("camion.placa");
		BeanProperty rutaConcesionario = BeanProperty.create("ruta.nombre");

		binUsuarios.addColumnBinding(idUsuario).setColumnClass(Integer.class).setColumnName("idConcesionario ");
		binUsuarios.addColumnBinding(UserNameUsuario).setColumnClass(String.class).setColumnName("UserName");
		binUsuarios.addColumnBinding(CedulaUsuario).setColumnClass(String.class).setColumnName("Cedula");
		binUsuarios.addColumnBinding(NombreUsuario).setColumnClass(String.class).setColumnName("Nombre");
		
		binUsuarios.addColumnBinding(camionConcesionario).setColumnClass(String.class).setColumnName("Camion");
	    binUsuarios.addColumnBinding(rutaConcesionario).setColumnClass(String.class).setColumnName("Ruta");
	    
	    binUsuarios.bind();

	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void activarBinding(List<Usuario> usuarios) {
		// TODO Auto-generated method stub
		pnTabla.setVisible(true);
		pnTabla.setBounds(12, 85, 852, 408);
		scrollPanel.setBounds(0, 0, 852, 408);
		table = new JTable();
		scrollPanel.setViewportView(table);
		binUsuarios = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,
    			usuarios,table);
		
			BeanProperty idUsuario  = BeanProperty.create("id");
			BeanProperty UserNameUsuario = BeanProperty.create("username");
			BeanProperty CedulaUsuario = BeanProperty.create("cedula");
			BeanProperty NombreUsuario = BeanProperty.create("nombre");
			
			binUsuarios.addColumnBinding(idUsuario).setColumnClass(Integer.class).setColumnName("idUsuario ");
			binUsuarios.addColumnBinding(UserNameUsuario).setColumnClass(String.class).setColumnName("UserName");
			binUsuarios.addColumnBinding(CedulaUsuario).setColumnClass(String.class).setColumnName("Cedula");
			binUsuarios.addColumnBinding(NombreUsuario).setColumnClass(String.class).setColumnName("Nombre");
			binUsuarios.bind();

	}
	
	@SuppressWarnings("rawtypes")
	public void activarJComboBoxBindingRuta(){
		
		JComboBoxBinding jcomboRutas = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ,rutas,comboRutas);
	    jcomboRutas.bind();
	}
	
	@SuppressWarnings("rawtypes")
	public void activarJComboBoxBindingCamion(){
		JComboBoxBinding jcomboCamiones = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ,camiones,cmbCamion);
	    jcomboCamiones.bind();
	}
	
	@SuppressWarnings("rawtypes")
	public void activarJComboBoxBindingZona(){
		JComboBoxBinding jcomboZonas = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ,zonas,cmbZona);
	    jcomboZonas.bind();
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void activarBindingRutas(List<Ruta> rutaVendedores) {
		// TODO Auto-generated method stubactivarBinding
		
		pnRutas.setVisible(true);
		binRutas = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ,
				rutaVendedores,tablaRutas);
		
		 BeanProperty idRuta = BeanProperty.create("id");
		    BeanProperty zonaRuta = BeanProperty.create("zona.descripcion");
		    BeanProperty nombreRuta = BeanProperty.create("nombre");
		  

		    binRutas.addColumnBinding(idRuta).setColumnClass(String.class).setColumnClass(String.class).setColumnName("id");
		    binRutas.addColumnBinding(zonaRuta).setColumnClass(String.class).setColumnClass(String.class).setColumnName("zona");
		    binRutas.addColumnBinding(nombreRuta).setColumnClass(String.class).setColumnClass(String.class).setColumnName("nombre");
		    
		    binRutas.bind();    
	}
	public void LimpiarTxt(){
		getTxtCedula().setText("");
		getTxtNombre().setText("");
		getTxtReContrasena().setText("");
		getTxtContrasena().setText("");
		getTxtUserName().setText("");
	}
	public void activarNuevo() {
		// TODO Auto-generated method stub
		pnEditar.setVisible(true);
		pnTabla.setVisible(false);
		if(cmbTipoUsuario.getSelectedItem().equals("JefeVenta")) activarNuevoJefeVenta();
		else if(cmbTipoUsuario.getSelectedItem().equals("Vendedor")) activarNuevoVendedor();
		else if(cmbTipoUsuario.getSelectedItem().equals("Concesionario"))activarNuevoConcesionario();
		else pnEditar.setBounds(12, 81, 852, 90);
	}
	
	public void activarNuevoVendedor(){
		pnEditar.setVisible(true);
		pnEditar.setBounds(12, 81, 852, 420);
		pnEditarRuta.setBounds(0, 104, 852, 250);
		btnAgregar.setVisible(true);
		btnQuitar.setVisible(true);
		getLblMensaje().setForeground(Color.GREEN);
		getLblMensaje().setBounds(10, 210, 830, 54);
		getLblMensaje().setText("solo puede Asignar tres Rutas. Se Guardaran las tres Primeras en la tabla");
	}
	public void activarNuevoJefeVenta(){
		pnEditar.setVisible(true);
		pnEditar.setBounds(12, 81, 852, 298);
		pnZona.setVisible(true);
		pnZona.setBounds(0,90, 852, 63);
		pnCamion.setVisible(false);
	}

	public void activarNuevoConcesionario(){
		pnEditar.setVisible(true);
		pnEditar.setBounds(12, 81, 852, 420);
		pnEditarRuta.setBounds(0, 104, 852, 250);
		btnAgregar.setVisible(false);
		btnQuitar.setVisible(false);
		pnCamion.setVisible(true);
		pnCamion.setBounds(0,355, 852, 67);
		btnCamion.setBounds(735, 23, 120, 16);
		cmbCamion.setBounds(75, 22, 600, 24);
		pnZona.setVisible(false);
		pnRutas.setVisible(false);
		lblMensaje.setText("");
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

	@SuppressWarnings("rawtypes")
	public JComboBox getCmbTipoUsuario() {
		return cmbTipoUsuario;
	}
	
	public void setCmbTipoUsuario(JComboBox<String> cmbTipoUsuario) {
		this.cmbTipoUsuario = cmbTipoUsuario;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	@SuppressWarnings("rawtypes")
	public JTableBinding getBinUsuarios() {
		return binUsuarios;
	}

	@SuppressWarnings("rawtypes")
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

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setCamiones(List<Usuario> camiones) {
		this.usuarios = camiones;
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

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JTextField getTxtReContrasena() {
		return txtReContrasena;
	}

	public JPanel getPnRuta() {
		return pnEditarRuta;
	}

	public void setPnRuta(JPanel pnRuta) {
		this.pnEditarRuta = pnRuta;
	}

	public JPanel getPnZona() {
		return pnZona;
	}

	public void setPnZona(JPanel pnZona) {
		this.pnZona = pnZona;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getCmbZona() {
		return cmbZona;
	}

	public void setCmbZona(JComboBox<Zona> cmbZona) {
		this.cmbZona = cmbZona;
	}

	public JLabel getLblZona() {
		return lblZona;
	}

	public void setLblZona(JLabel lblZona) {
		this.lblZona = lblZona;
	}


	public JButton getBtnAbrirRuta() {
		return btnAbrirRuta;
	}

	public void setBtnAbrirRuta(JButton btnAbrirRuta) {
		this.btnAbrirRuta = btnAbrirRuta;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}


	public JComboBox<Ruta> getComboRutas() {
		return comboRutas;
	}

	public void setComboRutas(JComboBox<Ruta> comboRutas) {
		this.comboRutas = comboRutas;
	}

	@SuppressWarnings("rawtypes")
	public JTableBinding getBinRutas() {
		return binRutas;
	}

	@SuppressWarnings("rawtypes")
	public void setBinRutas(JTableBinding binRutas) {
		this.binRutas = binRutas;
	}

	public List<Ruta> getRutasVendedores() {
		return rutasVendedores;
	}

	public void setRutasVendedores(List<Ruta> rutasVendedores) {
		this.rutasVendedores = rutasVendedores;
	}

	public List<Ruta> getRutas() {
		return rutas;
	}

	public void setRutas(List<Ruta> rutas) {
		this.rutas = rutas;
	}

	public List<Zona> getZonas() {
		return zonas;
	}

	public void setZonas(List<Zona> zonas) {
		this.zonas = zonas;
	}

	public JPanel getPnEditarRuta() {
		return pnEditarRuta;
	}

	public void setPnEditarRuta(JPanel pnEditarRuta) {
		this.pnEditarRuta = pnEditarRuta;
	}

	public JTable getTablaRutas() {
		return tablaRutas;
	}

	public void setTablaRutas(JTable tablaRutas) {
		this.tablaRutas = tablaRutas;
	}

	public void setTxtContrasena(JPasswordField txtContrasena) {
		this.txtContrasena = txtContrasena;
	}

	public void setTxtReContrasena(JPasswordField txtReContrasena) {
		this.txtReContrasena = txtReContrasena;
	}

	public JPanel getPnRutas() {
		return pnRutas;
	}

	public void setPnRutas(JPanel pnRutas) {
		this.pnRutas = pnRutas;
	}

	public JPanel getPnCamion() {
		return pnCamion;
	}

	public void setPnCamion(JPanel pnCamion) {
		this.pnCamion = pnCamion;
	}

	public JLabel getLblCamion() {
		return lblCamion;
	}

	public void setLblCamion(JLabel lblCamion) {
		this.lblCamion = lblCamion;
	}

	public JButton getBtnQuitar() {
		return btnQuitar;
	}

	public void setBtnQuitar(JButton btnQuitar) {
		this.btnQuitar = btnQuitar;
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public void setBtnAgregar(JButton btnAgregar) {
		this.btnAgregar = btnAgregar;
	}

	public JButton getBtnZona() {
		return btnZona;
	}

	public void setBtnZona(JButton btnZona) {
		this.btnZona = btnZona;
	}

	public JButton getBtnCamion() {
		return btnCamion;
	}

	public void setBtnCamion(JButton btnCamion) {
		this.btnCamion = btnCamion;
	}

	public JComboBox<Camion> getCmbCamion() {
		return cmbCamion;
	}

	public void setCmbCamion(JComboBox<Camion> cmbCamion) {
		this.cmbCamion = cmbCamion;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public JLabel getLblMensaje() {
		return lblMensaje;
	}

	public void setLblMensaje(JLabel lblMensaje) {
		this.lblMensaje = lblMensaje;
	}

	public List<Camion> getCamiones() {
		return camiones;
	}
	
	protected void initDataBindings() {
		BeanProperty<JTable, Integer> jTableBeanProperty = BeanProperty.create("selectedColumn");
		ObjectProperty<JTable> jTableObjectProperty = ObjectProperty.create();
		AutoBinding<JTable, Integer, JTable, JTable> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ, table, jTableBeanProperty, table, jTableObjectProperty);
		autoBinding.bind();
		//
		BeanProperty<JTable, Integer> jTableBeanProperty_1 = BeanProperty.create("selectedRow");
		BeanProperty<JTable, List<Object>> jTableBeanProperty_2 = BeanProperty.create("selectedElements");
		AutoBinding<JTable, Integer, JTable, List<Object>> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ, table, jTableBeanProperty_1, table, jTableBeanProperty_2);
		autoBinding_1.bind();
	}
	
	
}
