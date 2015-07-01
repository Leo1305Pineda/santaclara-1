package santaclara.vista;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import santaclara.modelo.Cliente;
import santaclara.modelo.Ruta;
import santaclara.controlador.ContClientes;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import net.miginfocom.swing.MigLayout;

import java.awt.event.ActionEvent;

public class ClientesUI extends JPanel {
	
	private JPanel pnCliente;
	private JPanel pnClientes;
	private JPanel pnBotones;
	private JTable table;
	private JTableBinding  binProductos;
	private List<Ruta> rutas = new ArrayList<Ruta>();
	private List<Cliente> clientes = new ArrayList<Cliente>();
	private JTableBinding  binClientes;
	private JScrollPane scrollPanel;
	private JTable tabla;
	private JButton btnNuevo;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnGuardar;
	private JButton btnBuscar;
	private JButton btnSalir;
	private JTextField txtRif;
	private JTextField txtRazonSocial;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JTextField txtABuscar; 
	private JComboBox cmbRuta;
	private JLabel label;
	private JLabel label_5;
	private JTextField txtId;
	
	public ClientesUI(ContClientes contCliente,List<Ruta> rutas,List<Cliente> clientes) {
		this.clientes = clientes;
		this.rutas = rutas;
		
		setFont(new Font("Dialog", Font.BOLD, 13));
		setForeground(Color.WHITE);
		setBackground(Color.DARK_GRAY);
		setSize(864,537);
		setLayout(null);
		
		pnClientes = new JPanel();
		pnClientes.setLayout(null);
		pnClientes.setBorder(new TitledBorder(null, "Listado de Clientes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnClientes.setBackground(Color.GRAY);
		pnClientes.setBounds(22, 12, 817, 300);
		add(pnClientes);
		
		pnBotones = new JPanel();
		pnBotones.setBorder(new TitledBorder(null, "opciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnBotones.setBackground(Color.GRAY);
		pnBotones.setBounds(674, 38, 131, 223);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.setBounds(5, 57, 121, 40);
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setBackground(Color.DARK_GRAY);
		btnNuevo.addActionListener(contCliente.nuevo());
		pnBotones.setLayout(null);
		pnBotones.add(btnNuevo);
		
		label_5 = new JLabel("");
		label_5.setBounds(5, 57, 121, 40);
		pnBotones.add(label_5);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(5, 97, 121, 40);
		btnModificar.setForeground(Color.WHITE);
		btnModificar.setBackground(Color.DARK_GRAY);
		btnModificar.addActionListener(contCliente.modificar());
		pnBotones.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(5, 137, 121, 40);
		btnEliminar.setForeground(new Color(255, 255, 255));
		btnEliminar.setBackground(Color.DARK_GRAY);
		btnEliminar.addActionListener(contCliente.eliminar());
		pnBotones.add(btnEliminar);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(contCliente.salir());
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.DARK_GRAY);
		btnSalir.setBounds(5, 177, 121, 40);
		pnBotones.add(btnSalir);
		
		JPanel pnBuscar = new JPanel();
		pnBuscar.setBackground(Color.GRAY);
		pnBuscar.setForeground(Color.GRAY);
		pnBuscar.setBounds(25, 12, 635, 36);
		
		pnBuscar.setLayout(new MigLayout("", "[82px,grow][][][][][][][][][][][][][][][][][][][]", "[][][][25px]"));
		
		txtABuscar = new JTextField();
		txtABuscar.setBackground(Color.LIGHT_GRAY);
		pnBuscar.add(txtABuscar, "cell 0 0 19 1,growx");
		txtABuscar.setColumns(10);
		
		btnBuscar = new JButton("b");
		btnBuscar.addActionListener(contCliente.buscar());
		btnBuscar.setBackground(Color.GRAY);
		
		pnBuscar.add(btnBuscar, "cell 19 0,alignx right,aligny top");
		
		pnClientes.add(pnBuscar);
		
		scrollPanel = new JScrollPane();
		scrollPanel.setBounds(25, 50, 635, 224);
		pnClientes.add(scrollPanel);
		
		tabla = new JTable();
		scrollPanel.setViewportView(tabla);
		
		pnCliente = new JPanel();
		pnCliente.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229), 1, true),
							"Editar Cliente",TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnCliente.setBackground(Color.GRAY);
		pnCliente.setBounds(22, 329, 817, 167);
		//quitarNuevo();
	
		add(pnCliente);
		
		label = new JLabel("Rif:");
		label.setBounds(28, 42, 23, 15);
		label.setForeground(Color.WHITE);
		label.setBackground(Color.WHITE);
		
		JLabel label_1 = new JLabel("Razon social:");
		label_1.setBounds(28, 89, 94, 15);
		label_1.setForeground(Color.WHITE);
		label_1.setBackground(Color.WHITE);
		
		JLabel label_3 = new JLabel("Telefono:");
		label_3.setBounds(28, 138, 67, 15);
		label_3.setForeground(Color.WHITE);
		label_3.setBackground(Color.WHITE);
		
		JLabel label_2 = new JLabel("Direccion:");
		label_2.setBounds(408, 39, 70, 15);
		label_2.setForeground(Color.WHITE);
		label_2.setBackground(Color.WHITE);
		
		JLabel label_4 = new JLabel("Ruta:");
		label_4.setBounds(411, 87, 38, 15);
		label_4.setForeground(Color.WHITE);
		label_4.setBackground(Color.WHITE);
		
		txtRif = new JTextField();
		txtRif.setBounds(128, 38, 258, 19);
		txtRif.setColumns(10);
		
		txtRazonSocial = new JTextField();
		txtRazonSocial.setBounds(128, 85, 258, 19);
		txtRazonSocial.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(128, 134, 258, 19);
		txtTelefono.setColumns(10);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(496, 38, 258, 19);
		txtDireccion.setColumns(10);
		
		cmbRuta = new JComboBox();
		cmbRuta.setBounds(496, 82, 258, 24);
		cmbRuta.setRenderer(new ListCellRenderer() {

			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				// TODO Auto-generated method stub
				Ruta ruta = (Ruta)value;
				return new JLabel(ruta.getNombre());	
			}
		});
		
		pnCliente.setLayout(null);
		pnCliente.add(txtRif);
		pnCliente.add(txtRazonSocial);
		pnCliente.add(txtTelefono);
		pnCliente.add(txtDireccion);
		pnCliente.add(cmbRuta);
		pnCliente.add(label_4);
		pnCliente.add(label_2);
		pnCliente.add(label_3);
		pnCliente.add(label_1);
		pnCliente.add(label);
		
		btnGuardar = new JButton("Guardar");  
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBackground(Color.DARK_GRAY);
		btnGuardar.addActionListener(contCliente.guardar());
		btnGuardar.setBounds(598, 131, 156, 25);
		pnCliente.add(btnGuardar);
		
		txtId = new JTextField();
		txtId.setBounds(27, 12, 114, 19);
		pnCliente.add(txtId);
		txtId.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				quitarNuevo();
			}
		});
		
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(Color.DARK_GRAY);
		btnCancelar.setBounds(418, 131, 168, 25);
		pnCliente.add(btnCancelar);
		txtId.setVisible(false);
		
		pnClientes.add(pnBotones);
		
	}
	    
	public void activarBinding() {
		// TODO Auto-generated method stub
		tabla = new JTable();
		scrollPanel.setViewportView(tabla);
		binClientes = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,
    			clientes,tabla);
				    
	    BeanProperty idCliente = BeanProperty.create("id");
	    BeanProperty rifCliente = BeanProperty.create("rif");
	    BeanProperty razonSocialCliente = BeanProperty.create("razonsocial");
	    BeanProperty direccionCliente = BeanProperty.create("direccion");
	    BeanProperty telefonoCliente = BeanProperty.create("telefono");
	    BeanProperty rutaCliente = BeanProperty.create("ruta.nombre");
	    
	    binClientes.addColumnBinding(idCliente).setColumnClass(String.class).setColumnClass(String.class).setColumnName("id");
	    binClientes.addColumnBinding(rifCliente).setColumnClass(String.class).setColumnClass(String.class).setColumnName("rif");
	    binClientes.addColumnBinding(razonSocialCliente).setColumnClass(String.class).setColumnClass(String.class).setColumnName("razonSocial");
	    binClientes.addColumnBinding(direccionCliente).setColumnClass(String.class).setColumnClass(String.class).setColumnName("direccion");
	    binClientes.addColumnBinding(telefonoCliente).setColumnClass(String.class).setColumnClass(String.class).setColumnName("telefono");
	    binClientes.addColumnBinding(rutaCliente).setColumnClass(String.class).setColumnClass(String.class).setColumnName("Ruta");
	    
	    binClientes.bind();
	    
	    JComboBoxBinding jcomboRuta = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ,rutas,cmbRuta);
	    
	    jcomboRuta.bind();
		
	}
	
	public JTableBinding getBinProductos() {
		return binProductos;
	}

	public void setBinProductos(JTableBinding binProductos) {
		this.binProductos = binProductos;
	}

	public void activarNuevoCliente() {
		// TODO Auto-generated method stub
		pnCliente.setVisible(true);
		
		txtRazonSocial.setText("");
		txtDireccion.setText("");
		txtTelefono.setText("");
		txtRif.setText("");
		txtId.setText("");
 	
	}
	
	public void quitarNuevo() {
		// TODO Auto-generated method stub
		pnCliente.setVisible(false);
	}
	public JPanel getPnClientes() {
		return pnCliente;
	}
	public void setPnClientes(JPanel pnClientes) {
		this.pnCliente = pnClientes;
	}
	public JTable getTable() {
		return table;
	}
	public void setTable(JTable table) {
		this.table = table;
	}
	public List<Ruta> getRutas() {
		return rutas;
	}
	public void setRutas(List<Ruta> rutas) {
		this.rutas = rutas;
	}
	public List<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	public JTableBinding getBinClientes() {
		return binClientes;
	}
	public void setBinClientes(JTableBinding binClientes) {
		this.binClientes = binClientes;
	}
	public JScrollPane getScrollPanel() {
		return scrollPanel;
	}
	public void setScrollPanel(JScrollPane scrollPanel) {
		this.scrollPanel = scrollPanel;
	}
	public JTable getTabla() {
		return tabla;
	}
	public void setTabla(JTable tabla) {
		this.tabla = tabla;
	}
	public JButton getBtnNuevo() {
		return btnNuevo;
	}
	public void setBtnNuevo(JButton btnNuevo) {
		this.btnNuevo = btnNuevo;
	}
	public JButton getBtnModificar() {
		return btnModificar;
	}
	public void setBtnModificar(JButton btnModificar) {
		this.btnModificar = btnModificar;
	}
	public JButton getBtnEliminar() {
		return btnEliminar;
	}
	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}
	public JTextField getTxtRif() {
		return txtRif;
	}
	public void setTxtRif(JTextField txtRif) {
		this.txtRif = txtRif;
	}
	public JTextField getTxtRazonSocial() {
		return txtRazonSocial;
	}
	public void setTxtRazonSocial(JTextField txtRazonSocial) {
		this.txtRazonSocial = txtRazonSocial;
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
	@SuppressWarnings("rawtypes")
	public JComboBox getCmbRuta() {
		return cmbRuta;
	}
	@SuppressWarnings("rawtypes")
	public void setCmbRuta(JComboBox cmbRuta) {
		this.cmbRuta = cmbRuta;
	}
	public JPanel getPnCliente() {
		return pnCliente;
	}
	public void setPnCliente(JPanel pnCliente) {
		this.pnCliente = pnCliente;
	}
	public JPanel getPnBotones() {
		return pnBotones;
	}
	public void setPnBotones(JPanel pnBotones) {
		this.pnBotones = pnBotones;
	}
	public JButton getBtnGuardar() {
		return btnGuardar;
	}
	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}
	public JLabel getLabel() {
		return label;
	}
	public void setLabel(JLabel label) {
		this.label = label;
	}
	public JLabel getLabel_5() {
		return label_5;
	}
	public void setLabel_5(JLabel label_5) {
		this.label_5 = label_5;
	}
	public JTextField getTxtABuscar() {
		return txtABuscar;
	}
	public void setTxtABuscar(String txtABuscar) {
		this.txtABuscar.setText(txtABuscar);
	}

	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public void setBtnBuscar(JButton btnBuscar) {
		this.btnBuscar = btnBuscar;
	}

	public void setTxtABuscar(JTextField txtABuscar) {
		this.txtABuscar = txtABuscar;
	}

	public JTextField getTxtId() {
		return txtId;
	}

	public void setTxtId(JTextField txtId) {
		this.txtId = txtId;
	}
}
