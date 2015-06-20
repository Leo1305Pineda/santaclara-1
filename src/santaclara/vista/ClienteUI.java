package santaclara.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JList;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import java.util.List;

import santaclara.modelo.Ruta;
import santaclara.controlador.ContClientes;
import santaclara.modelo.Cliente;

public class ClienteUI extends JPanel {

	private JPanel contentPane;
	private List<Ruta> rutas = new ArrayList<Ruta>();
	private List<Cliente> clientes = new ArrayList<Cliente>();
	private JTable table;
	private JTextField txtTelefono;
	private JPasswordField txtDireccion;
	private JPasswordField txtRazonSocial;
	private JPasswordField txtRif;
	
	private JTableBinding  binClientes;

	
	public ClienteUI(ContClientes contCliente,List<Ruta> rutas){
		this.rutas = rutas;
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.DARK_GRAY);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBackground(Color.GRAY);
		contentPane.add(panel, BorderLayout.CENTER);
		
		JButton btnBuscar = new JButton("Guardar");
		btnBuscar.setBounds(126, 172, 103, 25);
		btnBuscar.setForeground(Color.WHITE);
		btnBuscar.setBackground(Color.DARK_GRAY);
		panel.setLayout(null);
		panel.add(btnBuscar);
		
		JButton button = new JButton("Buscar");
		button.setForeground(Color.WHITE);
		button.setBackground(Color.DARK_GRAY);
		button.setBounds(22, 172, 103, 25);
		panel.add(button);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setForeground(Color.WHITE);
		btnModificar.setBackground(Color.DARK_GRAY);
		btnModificar.setBounds(221, 172, 103, 25);
		panel.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(Color.DARK_GRAY);
		btnEliminar.setBounds(325, 172, 92, 25);
		panel.add(btnEliminar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.DARK_GRAY);
		btnSalir.setBounds(419, 172, 92, 25);
		panel.add(btnSalir);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(25, 209, 486, 205);
		panel.add(panel_1);
		
		table = new JTable();
		panel_1.add(table);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(22, 12, 489, 148);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel label = new JLabel("Rif:");
		label.setBounds(15, 7, 23, 15);
		label.setForeground(Color.WHITE);
		label.setBackground(Color.WHITE);
		panel_2.add(label);
		
		JLabel label_3 = new JLabel("Telefono:");
		label_3.setBounds(15, 94, 67, 15);
		label_3.setForeground(Color.WHITE);
		label_3.setBackground(Color.WHITE);
		panel_2.add(label_3);
		
		JLabel label_4 = new JLabel("Ruta:");
		label_4.setBounds(15, 121, 38, 15);
		label_4.setForeground(Color.WHITE);
		label_4.setBackground(Color.WHITE);
		panel_2.add(label_4);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(224, 90, 265, 19);
		txtTelefono.setColumns(10);
		panel_2.add(txtTelefono);
		
		txtDireccion = new JPasswordField();
		txtDireccion.setBounds(224, 63, 265, 19);
		panel_2.add(txtDireccion);
		
		txtRazonSocial = new JPasswordField();
		txtRazonSocial.setBounds(224, 32, 265, 19);
		panel_2.add(txtRazonSocial);
		
		JLabel label_1 = new JLabel("Razon social:");
		label_1.setBounds(15, 34, 94, 15);
		label_1.setForeground(Color.WHITE);
		panel_2.add(label_1);
		
		JLabel label_2 = new JLabel("Direccion:");
		label_2.setBounds(12, 69, 70, 15);
		label_2.setForeground(Color.WHITE);
		label_2.setBackground(Color.WHITE);
		panel_2.add(label_2);
		
		JComboBox comboRuta = new JComboBox();
		comboRuta.setBounds(224, 112, 265, 24);
		panel_2.add(comboRuta);
		
		txtRif = new JPasswordField();
		txtRif.setBounds(224, 5, 265, 19);
		panel_2.add(txtRif);
}		

	public void activarBinding() {
			// TODO Auto-generated method stub
		binClientes = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,
	    			clientes,table);
			
		table.getTableHeader().add(new JLabel("hola soy una prueba"));
		    
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
		    
		}

	
}
