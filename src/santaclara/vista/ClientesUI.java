package santaclara.vista;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
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
	private JTextField txtRif;
	private JTextField txtRazonSocial;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JComboBox cmbRuta;
	private JLabel label;
	private JLabel label_5;
	
	public ClientesUI(ContClientes contCliente,List<Ruta> rutas,List<Cliente> clientes) {
		this.clientes = clientes;
		this.rutas = rutas;
		
		setFont(new Font("Dialog", Font.BOLD, 13));
		setForeground(Color.WHITE);
		setBackground(Color.GRAY);
		setSize(800,800);
		setLayout(null);
		
		pnClientes = new JPanel();
		pnClientes.setLayout(null);
		pnClientes.setBorder(new TitledBorder(null, "Listado de Clientes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnClientes.setBackground(Color.GRAY);
		pnClientes.setBounds(22, 12, 710, 300);
		add(pnClientes);
		
		pnBotones = new JPanel();
		pnBotones.setBorder(new TitledBorder(null, "opciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnBotones.setBackground(Color.GRAY);
		pnBotones.setBounds(555, 38, 131, 223);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.setBounds(5, 17, 121, 40);
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
		//btnNuevo.addActionListener(contCliente.modificar());
		pnBotones.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(5, 137, 121, 40);
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(Color.DARK_GRAY);
		//btnNuevo.addActionListener(contCliente.eliminar());
		pnBotones.add(btnEliminar);
		
		JButton btnNewButton = new JButton("Salir");
		btnNewButton.setBounds(5, 177, 121, 40);
		pnBotones.add(btnNewButton);
		
		scrollPanel = new JScrollPane();
		scrollPanel.setBounds(27, 37, 514, 224);
		pnClientes.add(scrollPanel);
		
		tabla = new JTable();
		scrollPanel.setViewportView(tabla);
		
		pnCliente = new JPanel();
		pnCliente.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229), 1, true),
							"Editar Cliente",TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnCliente.setBackground(Color.GRAY);
		pnCliente.setBounds(22, 329, 700, 275);
		pnCliente.setVisible(false);
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
		label_2.setBounds(28, 186, 70, 15);
		label_2.setForeground(Color.WHITE);
		label_2.setBackground(Color.WHITE);
		
		JLabel label_4 = new JLabel("Ruta:");
		label_4.setBounds(28, 221, 38, 15);
		label_4.setForeground(Color.WHITE);
		label_4.setBackground(Color.WHITE);
		
		txtRif = new JTextField();
		txtRif.setBounds(212, 40, 258, 19);
		txtRif.setColumns(10);
		
		txtRazonSocial = new JTextField();
		txtRazonSocial.setBounds(212, 87, 258, 19);
		txtRazonSocial.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(212, 136, 258, 19);
		txtTelefono.setColumns(10);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(212, 184, 258, 19);
		txtDireccion.setColumns(10);
		
		cmbRuta = new JComboBox();
		cmbRuta.setBounds(212, 221, 258, 24);
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
		
		pnClientes.add(pnBotones);
		
		
		
		/*pnClientes = new JPanel();
		pnClientes.setBounds(0, 0, 800, 723);
		pnClientes.setBackground(Color.GRAY);
		pnClientes.setBorder(new TitledBorder(null, "Listado de Clientes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(pnClientes);
*/
		
	}
	public void activarBinding() {
		// TODO Auto-generated method stub
	binClientes = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,
    			clientes,table);
				    
	    BeanProperty idCliente = BeanProperty.create("id");
	    BeanProperty rifCliente = BeanProperty.create("rif");
	    BeanProperty razonSocialCliente = BeanProperty.create("razonSocial");
	    BeanProperty direccionCliente = BeanProperty.create("direccion");
	    BeanProperty telefonoCliente = BeanProperty.create("telefono");
	    BeanProperty rutaCliente = BeanProperty.create("Ruta.nombre");

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
	public JComboBox getCmbRuta() {
		return cmbRuta;
	}
	public void setCmbRuta(JComboBox cmbRuta) {
		this.cmbRuta = cmbRuta;
	}
}
