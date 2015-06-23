package santaclara.vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JDesktopPane;
import javax.swing.JTabbedPane;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import santaclara.controlador.ContVendedores;
import santaclara.modelo.Vendedor;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class VendedoresUI extends JPanel {

	
	private JTable table;
	private List<Vendedor> vendedores = new ArrayList<Vendedor>();
	private JTableBinding  binVendedores;

    private JPanel 	  		panelVendedor;
	private JButton    		btnNuevo;
	private JButton 		btnGuardar;
	private JTextField txtUsernmae;
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JPasswordField txtContrasena;
	private JPasswordField txtContrasenaRepetir;

	
	/**
	 * Create the panel.
	 * @param vendedores 
	 * @param controlador 
	 */
	
	public VendedoresUI(ContVendedores controlador, List<Vendedor> vendedores) {
		this.vendedores = vendedores;
	
		setFont(new Font("Dialog", Font.BOLD, 13));
		setForeground(Color.WHITE);
		setBackground(Color.GRAY);
		setSize(900,600);
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

		JPanel pnVendedores = new JPanel();
		pnVendedores.setSize(800,300);
		pnVendedores.setBackground(Color.GRAY);
		pnVendedores.setBorder(new TitledBorder(null, "Listado de Vendedores ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(pnVendedores);

		JPanel botones = new JPanel();
		botones.setBackground(Color.GRAY);
		botones.setLocation(708, 39);
		botones.setBorder(new TitledBorder(null, "Opciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		botones.setSize(131,223);
		botones.setLayout(null);
		

		btnNuevo = new JButton("Nuevo");
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setBackground(Color.DARK_GRAY);
		btnNuevo.setBounds(12,50, 100, 25);
		btnNuevo.addActionListener(controlador.nuevo());
		botones.add(btnNuevo);

		
		JScrollPane scrollPanel = new JScrollPane();
		scrollPanel.setBounds(23, 38, 673, 224);
		table = new JTable();
		table.setBackground(Color.WHITE);
		scrollPanel.setViewportView(table);

		pnVendedores.setLayout(null);
		pnVendedores.add(botones);
		
		JButton btnRutas = new JButton("Rutas");
		btnRutas.setBounds(12, 87, 100, 25);
		botones.add(btnRutas);
		
		JButton btnClientes = new JButton("Clientes");
		btnClientes.setBounds(12, 131, 100, 25);
		botones.add(btnClientes);
		pnVendedores.add(scrollPanel);
		
		panelVendedor = new JPanel();
		add(panelVendedor);
		panelVendedor.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229), 1, true), "Editar Vendedor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelVendedor.setBackground(Color.GRAY);
		panelVendedor.getSize(new Dimension(800, 300));
		panelVendedor.setLayout(null);
		panelVendedor.setVisible(false);
		
				
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(651, 22, 150,25);
		btnGuardar.setBackground(Color.DARK_GRAY);
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.addActionListener(controlador.guardar());
		panelVendedor.add(btnGuardar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(5, 152, 844, 12);
		panelVendedor.add(separator);
		
		JLabel lblUsername = new JLabel("UserName");
		lblUsername.setBounds(46, 27, 78, 15);
		panelVendedor.add(lblUsername);
		
		JLabel lblNewLabel_1 = new JLabel("cedula");
		lblNewLabel_1.setBounds(46, 54, 70, 15);
		panelVendedor.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre");
		lblNewLabel_2.setBounds(46, 81, 70, 15);
		panelVendedor.add(lblNewLabel_2);
		
		txtUsernmae = new JTextField();
		txtUsernmae.setBounds(134, 25, 186, 19);
		panelVendedor.add(txtUsernmae);
		txtUsernmae.setColumns(10);
		
		txtCedula = new JTextField();
		txtCedula.setBounds(134, 54, 188, 19);
		panelVendedor.add(txtCedula);
		txtCedula.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(134, 79, 186, 19);
		panelVendedor.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Contrasena");
		lblNewLabel_3.setBounds(388, 27, 94, 15);
		panelVendedor.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Repetir \nContrasena");
		lblNewLabel_4.setBounds(354, 54, 139, 59);
		panelVendedor.add(lblNewLabel_4);
		
		txtContrasena = new JPasswordField();
		txtContrasena.setBounds(500, 25, 139, 19);
		panelVendedor.add(txtContrasena);
		
		txtContrasenaRepetir = new JPasswordField();
		txtContrasenaRepetir.setBounds(500, 66, 143, 19);
		panelVendedor.add(txtContrasenaRepetir);
		  

	}
	
	public void activarBinding() {
		// TODO Auto-generated method stub

		binVendedores = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ,
    			vendedores,table);
		
	    BeanProperty idVendedor = BeanProperty.create("id");
	    BeanProperty usernameVendedor = BeanProperty.create("username");
	    BeanProperty cedulaVendedor = BeanProperty.create("cedula");
	    BeanProperty nombreVendedor = BeanProperty.create("nombre");
	    

	    binVendedores.addColumnBinding(idVendedor).setColumnClass(Integer.class).setColumnName("id");;
	    binVendedores.addColumnBinding(usernameVendedor).setColumnClass(String.class).setColumnName("UserName ");
	    binVendedores.addColumnBinding(cedulaVendedor).setColumnClass(String.class).setColumnName("Cedula");
	    binVendedores.addColumnBinding(nombreVendedor).setColumnClass(String.class).setColumnName("Nombre");;

	    binVendedores.bind();

	    
	    
	}

	public void activarNuevoVendedor() {
		// TODO Auto-generated method stub
		panelVendedor.setVisible(true);
		
	}
}


