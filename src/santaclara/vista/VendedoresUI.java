package santaclara.vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ListCellRenderer;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import santaclara.controlador.ContVendedores;
import santaclara.modelo.Ruta;
import santaclara.modelo.Vendedor;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

import javax.swing.JComboBox;

public class VendedoresUI extends JPanel {

	private JPanel 	panelVendedor;
	private JPanel pnRutas;
	
	private List<Vendedor> vendedores = new ArrayList<Vendedor>();
	private List<Ruta> rutas = new ArrayList<Ruta>();
	private List<Ruta> rutasVendedores = new ArrayList<Ruta>();

	private JButton btnNuevo;
	private JButton btnGuardar;
	private JButton btnCancelar;
	
	private JComboBox comboRutas;
	
	private JTextField txtUsername;
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtId;
	
	private JPasswordField txtContrasena;
	private JPasswordField txtContrasenaRepetir;
	
	private JTableBinding  binVendedores;
	private JTableBinding  binRutas;
	
	private JTable table;
	private JTable tablaRutas;
	
	/**
	 * Create the panel.
	 * @param vendedores 
	 * @param contVendedores 
	 */
	
	public VendedoresUI(ContVendedores contVendedores, List<Vendedor> vendedores, List<Ruta> rutas) {
		this.vendedores = vendedores;
		this.rutas = rutas;
	
		setFont(new Font("Dialog", Font.BOLD, 13));
		setForeground(Color.WHITE);
		setBackground(Color.DARK_GRAY);
		setSize(1033,800);
		setLayout(null);
		
		JPanel pnVendedores = new JPanel();
		pnVendedores.setLocation(12, 12);
		pnVendedores.setSize(831,399);
		pnVendedores.setBackground(Color.GRAY);
		pnVendedores.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Listado de Vendedores ", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		add(pnVendedores);

		JPanel botones = new JPanel();
		botones.setBackground(Color.GRAY);
		botones.setLocation(708, 39);
		botones.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Opciones", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		botones.setBounds(698, 79, 108, 233);
		botones.setLayout(new GridLayout(0, 1, 0, 0));

		
		JScrollPane scrollPanel = new JScrollPane();
		scrollPanel.setBounds(23, 38, 646, 337);
		table = new JTable();
		table.setBackground(Color.WHITE);
		scrollPanel.setViewportView(table);

		pnVendedores.setLayout(null);
		pnVendedores.add(botones);
		

		btnNuevo = new JButton("Nuevo");
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setBackground(Color.DARK_GRAY);
		btnNuevo.addActionListener(contVendedores.nuevo());
		
		JLabel lblNewLabel = new JLabel("");
		botones.add(lblNewLabel);
		botones.add(btnNuevo);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(contVendedores.modificar());
		btnModificar.setForeground(Color.WHITE);
		btnModificar.setBackground(Color.DARK_GRAY);
		botones.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(contVendedores.cliente());
		btnEliminar.setBackground(Color.DARK_GRAY);
		btnEliminar.setForeground(Color.WHITE);
		botones.add(btnEliminar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(contVendedores.salir());
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.DARK_GRAY);
		botones.add(btnSalir);
		pnVendedores.add(scrollPanel);
		
		JPanel panel = new JPanel();
		panel.setBounds(723, 447, 94, 76);
		pnVendedores.add(panel);
		
		panelVendedor = new JPanel();
		panelVendedor.setBounds(12, 436, 831, 240);
		add(panelVendedor);
		panelVendedor.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229), 1, true), "Editar Vendedor", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		panelVendedor.setBackground(Color.GRAY);
		panelVendedor.getSize(new Dimension(800, 300));
		panelVendedor.setLayout(null);
		
		JLabel lblUsername = new JLabel("UserName");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setBounds(32, 77, 78, 15);
		panelVendedor.add(lblUsername);
		
		JLabel lblNewLabel_1 = new JLabel("cedula");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(32, 25, 70, 15);
		panelVendedor.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(32, 50, 78, 15);
		panelVendedor.add(lblNewLabel_2);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(125, 75, 173, 19);
		panelVendedor.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtCedula = new JTextField();
		txtCedula.setBounds(125, 23, 173, 19);
		panelVendedor.add(txtCedula);
		txtCedula.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(125, 50, 173, 19);
		panelVendedor.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Contrasena");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(318, 23, 94, 15);
		panelVendedor.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Repetir \nContrasena");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(310, 75, 139, 29);
		panelVendedor.add(lblNewLabel_4);
		
		txtContrasena = new JPasswordField();
		txtContrasena.setBounds(455, 23, 219, 19);
		panelVendedor.add(txtContrasena);
		
		txtContrasenaRepetir = new JPasswordField();
		txtContrasenaRepetir.setBounds(455, 75, 219, 19);
		panelVendedor.add(txtContrasenaRepetir);
		
		txtId = new JTextField();
		txtId.setBounds(22, 12, 114, 19);
		panelVendedor.add(txtId);
		txtId.setColumns(10);
		
		pnRutas = new JPanel();
		pnRutas.setBounds(32, 100, 776, 128);
		panelVendedor.add(pnRutas);
		pnRutas.setBackground(Color.GRAY);
		pnRutas.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229), 1, true), "Editar Rutas", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnRutas.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 39, 752, 78);
		pnRutas.add(scrollPane);
		
		tablaRutas = new JTable();
		scrollPane.setViewportView(tablaRutas);
		
		JPanel pnOpcionRutas = new JPanel();
		pnOpcionRutas.setBounds(433, 22, 331, 20);
		pnRutas.add(pnOpcionRutas);
		pnOpcionRutas.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean enc = false;
				for(Ruta ruta1: rutasVendedores) if (ruta1.equals(comboRutas.getSelectedItem())) enc = true;
				if (!enc) rutasVendedores.add((Ruta) comboRutas.getSelectedItem()); 
				activarBindingRutas(rutasVendedores);
			}
		});
		pnOpcionRutas.add(btnAgregar);
		btnAgregar.setForeground(Color.WHITE);
		btnAgregar.setBackground(Color.DARK_GRAY);
		
		JButton btnQuitar = new JButton("Quitar");
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rutasVendedores.remove(comboRutas.getSelectedItem());
				activarBindingRutas(rutasVendedores);
			}
		});
		pnOpcionRutas.add(btnQuitar);
		btnQuitar.setForeground(Color.WHITE);
		btnQuitar.setBackground(Color.DARK_GRAY);
		
		JButton btnNuevaRuta = new JButton("Nueva");
		btnNuevaRuta.addActionListener(contVendedores.ruta());
		pnOpcionRutas.add(btnNuevaRuta);
		btnNuevaRuta.setForeground(Color.WHITE);
		btnNuevaRuta.setBackground(Color.DARK_GRAY);
		
		comboRutas = new JComboBox();
		comboRutas.setBounds(12, 22, 422, 20);
		comboRutas.setRenderer(new ListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				// TODO Auto-generated method stub
				Ruta ruta = (Ruta)value;
				return new JLabel(ruta.getNombre());	
			}
		});
		pnRutas.add(comboRutas);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(703, 25, 100, 61);
		panelVendedor.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
				
		btnGuardar = new JButton("Guardar");
		panel_1.add(btnGuardar);
		btnGuardar.setBackground(Color.DARK_GRAY);
		btnGuardar.setForeground(Color.WHITE);
		
		btnCancelar = new JButton("Cerrar");
		panel_1.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarNuevo();
				limpiarNuevo();
			}
		});
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(Color.DARK_GRAY);
		btnGuardar.addActionListener(contVendedores.guardar());
		//txtId.setVisible(false);
		  

	}
	
	public void activarBinding(List<Vendedor> lsvendedores) {
		// TODO Auto-generated method stubactivarBinding

		binVendedores = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ,
    			lsvendedores,table);
		
		BeanProperty idVendedor = BeanProperty.create("id");
	    BeanProperty usernameVendedor = BeanProperty.create("username");
	    BeanProperty cedulaVendedor = BeanProperty.create("cedula");
	    BeanProperty nombreVendedor = BeanProperty.create("nombre");
	    
	    binVendedores.addColumnBinding(idVendedor).setColumnClass(Integer.class).setColumnName("id");;
	    binVendedores.addColumnBinding(usernameVendedor).setColumnClass(String.class).setColumnName("UserName ");
	    binVendedores.addColumnBinding(cedulaVendedor).setColumnClass(String.class).setColumnName("Cedula");;
	    binVendedores.addColumnBinding(nombreVendedor).setColumnClass(String.class).setColumnName("Nombre");;
	  

	    binVendedores.bind();
	    
	}
	
	public void activarJComboBoxBinding(){
		JComboBoxBinding jcomboRutas = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ,rutas,comboRutas);
	    jcomboRutas.bind();
	}
	
	public void activarBindingRutas(List<Ruta> rutas) {
		// TODO Auto-generated method stubactivarBinding

		binRutas = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ,
    			rutas,tablaRutas);
		
		 BeanProperty idRuta = BeanProperty.create("id");
		    BeanProperty zonaRuta = BeanProperty.create("zona.descripcion");
		    BeanProperty nombreRuta = BeanProperty.create("nombre");
		  

		    binRutas.addColumnBinding(idRuta).setColumnClass(String.class).setColumnClass(String.class).setColumnName("id");
		    binRutas.addColumnBinding(zonaRuta).setColumnClass(String.class).setColumnClass(String.class).setColumnName("zona");
		    binRutas.addColumnBinding(nombreRuta).setColumnClass(String.class).setColumnClass(String.class).setColumnName("nombre");
		    
		    binRutas.bind();    
	}


	public void activarNuevoVendedor() {
		// TODO Auto-generated method stub
		panelVendedor.setVisible(true);
		
	}
	
	public void activarNuevoRuta() {
		// TODO Auto-generated method stub
		
		panelVendedor.setVisible(true);
		txtNombre.setText("");
		//txtId.setText("");
	}
	
	public void limpiarNuevo(){
		
		txtCedula.setText("");
		txtContrasena.setText("");
		txtContrasenaRepetir.setText("");
		txtId.setText("");
		txtNombre.setText("");
		txtUsername.setText("");
		rutasVendedores.clear();
		activarBindingRutas(rutasVendedores);
	}
	
	public void quitarNuevo() {
		// TODO Auto-generated method stub
		panelVendedor.setVisible(false);
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public List<Vendedor> getVendedores() {
		return vendedores;
	}

	public void setVendedores(List<Vendedor> vendedores) {
		this.vendedores = vendedores;
	}

	public JTableBinding getBinVendedores() {
		return binVendedores;
	}

	public void setBinVendedores(JTableBinding binVendedores) {
		this.binVendedores = binVendedores;
	}

	public JPanel getPanelVendedor() {
		return panelVendedor;
	}

	public void setPanelVendedor(JPanel panelVendedor) {
		this.panelVendedor = panelVendedor;
	}

	public JButton getBtnNuevo() {
		return btnNuevo;
	}

	public void setBtnNuevo(JButton btnNuevo) {
		this.btnNuevo = btnNuevo;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public JTextField getTxtUsername() {
		return txtUsername;
	}

	public void setTxtUsername(JTextField txtUsernmae) {
		this.txtUsername = txtUsernmae;
	}

	public JTextField getTxtCedula() {
		return txtCedula;
	}

	public void setTxtCedula(JTextField txtCedula) {
		this.txtCedula = txtCedula;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JPasswordField getTxtContrasena() {
		return txtContrasena;
	}

	public void setTxtContrasena(JPasswordField txtContrasena) {
		this.txtContrasena = txtContrasena;
	}

	public JPasswordField getTxtContrasenaRepetir() {
		return txtContrasenaRepetir;
	}

	public void setTxtContrasenaRepetir(JPasswordField txtContrasenaRepetir) {
		this.txtContrasenaRepetir = txtContrasenaRepetir;
	}

	public JTextField getTxtId() {
		return txtId;
	}

	public void setTxtId(JTextField txtId) {
		this.txtId = txtId;
	}

	public JPanel getPnRutas() {
		return pnRutas;
	}

	public void setPnRutas(JPanel pnRutas) {
		this.pnRutas = pnRutas;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public JComboBox getComboRutas() {
		return comboRutas;
	}

	public void setComboRutas(JComboBox comboRutas) {
		this.comboRutas = comboRutas;
	}

	public JTableBinding getBinRutas() {
		return binRutas;
	}

	public void setBinRutas(JTableBinding binRutas) {
		this.binRutas = binRutas;
	}

	public JTable getTablaRutas() {
		return tablaRutas;
	}

	public void setTablaRutas(JTable tablaRutas) {
		this.tablaRutas = tablaRutas;
	}

	public List<Ruta> getRutas() {
		return rutas;
	}

	public void setRutas(List<Ruta> rutas) {
		this.rutas = rutas;
	}

	public List<Ruta> getRutasVendedores() {
		return rutasVendedores;
	}

	public void setRutasVendedores(List<Ruta> rutasVendedores) {
		this.rutasVendedores = rutasVendedores;
	}
}


