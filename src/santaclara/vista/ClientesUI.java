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
import santaclara.controlador.ContClientes;
import santaclara.modelo.Cliente;
import santaclara.modelo.DomicilioComercio;
import santaclara.modelo.Ruta;
import santaclara.modelo.Salp;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ClientesUI extends JPanel {
	
	private JPanel pnOpciones;
	private JPanel panel;
	private JPanel pnClientes;
	private JPanel panel_1;
	private JPanel pnTabla;
	private JPanel pnEditar; 
	private JPanel pnDomicilioComercial;
	private JPanel pnCliente;
	
	private JLabel lblTipoCliente;
	private JLabel lblRazonSocial;
	private JLabel lblTelefono;
	private JLabel lblDireccion;
	
	private JTextField txtRazonSocial;
	private JTextField txtId;
	private JTextField txtABuscar;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	
	private JButton btnNuevo;
	private JButton btnEditar;
	private JButton btnAtras;
	private JButton btnSalir;
	private JButton btnABuscar;
	private JButton btnEliminar;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JButton btnAbrirRuta;
	
	private JComboBox<String> cmbTipoCliente ;
	private JComboBox<Ruta> comboRutas;
	private JScrollPane scrollPanel;
	private JTable table;
	
	@SuppressWarnings("rawtypes")
	private JTableBinding binClientes;
	@SuppressWarnings("rawtypes")
	private JTableBinding  binRutas;
	
	private List<Cliente> clientes = new ArrayList<Cliente>();
	private List<Ruta> rutas = new ArrayList<Ruta>();
	private JTextField txtRif;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ClientesUI(ContClientes contClientes,List<Cliente> clientes,List<Ruta> rutas) {
		this.clientes = clientes;
		this.rutas = rutas;
		
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		pnClientes = new JPanel();
		pnClientes.setBackground(Color.DARK_GRAY);
		pnClientes.setForeground(Color.DARK_GRAY);
		pnClientes.setBounds(12, 12, 746, 384);
		pnClientes.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Modulo Clientes", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		add(pnClientes);
		pnClientes.setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(12, 45, 726, 42);
		pnClientes.add(panel);
		panel.setLayout(null);
		
		pnOpciones = new JPanel();
		pnOpciones.setBounds(0, 0, 725, 34);
		panel.add(pnOpciones);
		pnOpciones.setBackground(Color.DARK_GRAY);
		pnOpciones.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Opciones", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnOpciones.setLayout(null);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(contClientes.nuevo());
		btnNuevo.setBounds(125, 15, 115, 16);
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setBackground(Color.DARK_GRAY);
		btnNuevo.setIcon(new ImageIcon("img/gestion/add.png"));
		pnOpciones.add(btnNuevo);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(contClientes.modificar());
		btnEditar.setBounds(245, 15, 115, 16);
		btnEditar.setForeground(Color.WHITE);
		btnEditar.setBackground(Color.DARK_GRAY);
		btnEditar.setIcon(new ImageIcon("img/gestion/Modificara.png"));
		pnOpciones.add(btnEditar);
		
		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(contClientes.atras());
		btnAtras.setBounds(5, 15, 115, 16);
		btnAtras.setForeground(Color.WHITE);
		btnAtras.setBackground(Color.DARK_GRAY);
		btnAtras.setIcon(new ImageIcon("img/gestion/AtrasCurva.png"));
		pnOpciones.add(btnAtras);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(contClientes.salir());
		btnSalir.setBounds(605, 15, 115, 16);
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.DARK_GRAY);
		btnSalir.setIcon(new ImageIcon("img/gestion/SalirCurva.png"));
		pnOpciones.add(btnSalir);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(Color.DARK_GRAY);
		btnEliminar.setIcon(new ImageIcon("img/gestion/cancel.png"));
		btnEliminar.addActionListener(contClientes.eliminar());
		btnEliminar.setBounds(365, 15, 115, 16);
		pnOpciones.add(btnEliminar);
		
		btnAbrirRuta = new JButton("Ruta");
		btnAbrirRuta.addActionListener(contClientes.AbrirRutas());
		btnAbrirRuta.setBounds(485, 15, 115, 16);
		pnOpciones.add(btnAbrirRuta);
		btnAbrirRuta.setForeground(Color.WHITE);
		btnAbrirRuta.setBackground(Color.DARK_GRAY);
		
		pnTabla = new JPanel();
		pnTabla.setBounds(12, 85, 726, 103);
		pnClientes.add(pnTabla);
		pnTabla.setLayout(null);
		
		scrollPanel = new JScrollPane();
		scrollPanel.setBounds(0, 0, 725, 100);
		pnTabla.add(scrollPanel);
		
		table = new JTable();
		table.setBounds(0, 0, 1, 1);
		pnTabla.add(table);
		
		panel_1 = new JPanel();
		panel_1.setForeground(Color.GRAY);
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(324, 13, 411, 30);
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
		
		pnEditar = new JPanel();
		pnEditar.setBackground(Color.GRAY);
		pnEditar.setBounds(12, 205, 726, 179);
		pnClientes.add(pnEditar);
		pnEditar.setLayout(null);
		
		pnCliente = new JPanel();
		pnCliente.setLayout(null);
		pnCliente.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)),"Editar Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnCliente.setBackground(Color.DARK_GRAY);
		pnCliente.setBounds(0, 5, 726, 63);
		pnEditar.add(pnCliente);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(contClientes.guardar());
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBackground(Color.DARK_GRAY);
		btnGuardar.setBounds(594, 17, 120, 16);
		pnCliente.add(btnGuardar);
		
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
		btnCancelar.setBounds(5, 17, 120, 16);
		pnCliente.add(btnCancelar);
		
		lblRazonSocial = new JLabel("Razon Social:");
		lblRazonSocial.setForeground(Color.WHITE);
		lblRazonSocial.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblRazonSocial.setBounds(173, 8, 100, 25);
		pnCliente.add(lblRazonSocial);
		
		txtRazonSocial = new JTextField();
		txtRazonSocial.setColumns(10);
		txtRazonSocial.setBounds(277, 12, 292, 16);
		pnCliente.add(txtRazonSocial);
		
		txtId = new JTextField();
		txtId.setColumns(10);
		txtId.setBounds(163, 9, 54, 19);
		txtId.setVisible(false);
		pnCliente.add(txtId);
		
		lblTelefono = new JLabel("Telefono:");
		lblTelefono.setForeground(Color.WHITE);
		lblTelefono.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblTelefono.setBounds(478, 38, 69, 25);
		pnCliente.add(lblTelefono);
		
		lblDireccion = new JLabel("Direccion:");
		lblDireccion.setForeground(Color.WHITE);
		lblDireccion.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblDireccion.setBounds(217, 38, 100, 25);
		pnCliente.add(lblDireccion);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(565, 42, 149, 16);
		pnCliente.add(txtTelefono);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(300, 42, 160, 16);
		pnCliente.add(txtDireccion);
		
		JLabel lblRif = new JLabel("Rif:");
		lblRif.setForeground(Color.WHITE);
		lblRif.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblRif.setBounds(5, 38, 39, 25);
		pnCliente.add(lblRif);
		
		txtRif = new JTextField();
		txtRif.setColumns(10);
		txtRif.setBounds(47, 41, 160, 16);
		pnCliente.add(txtRif);
		
		pnDomicilioComercial = new JPanel();
		pnDomicilioComercial.setLayout(null);
		String formatString = new String("  Id Ruta                        "+
				 "Nombre Ruta              "+
                "Id Zona                       "+
				 "Nombre Zona              ");
		pnDomicilioComercial.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)),formatString, TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnDomicilioComercial.setBackground(Color.DARK_GRAY);
		pnDomicilioComercial.setBounds(0, 71, 726, 102);
		pnEditar.add(pnDomicilioComercial);
		
		comboRutas = new JComboBox();
		comboRutas.setBounds(10, 12, 618, 24);
		comboRutas.setRenderer(new ListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				// TODO Auto-generated method stub
				Ruta ruta = (Ruta)value;
				
				JPanel pnRuta = new JPanel();
				pnRuta.add(new JTextField(ruta.getId().toString()));
				pnRuta.add(new JTextField(ruta.getNombre()));
				pnRuta.add(new JTextField(ruta.getZona().getId().toString()));
				pnRuta.add(new JTextField(ruta.getZona().getDescripcion()));
				
				pnRuta.setLayout(new GridLayout(1, 0, 0, 0));
				
				return pnRuta;	
			}
		});
	
		pnDomicilioComercial.add(comboRutas);
		
		activarJComboBoxBindingRuta();
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void activarBindingSalp(List<Salp> salps) {
		// TODO Auto-generated method stub
		pnTabla.setVisible(true);
		table = new JTable();
		scrollPanel.setViewportView(table);
		binClientes = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,
    			salps,table);
		
			BeanProperty idCliente  = BeanProperty.create("id");
			BeanProperty razonSocial = BeanProperty.create("razonsocial");
			BeanProperty direccion = BeanProperty.create("direccion");
			BeanProperty telefono = BeanProperty.create("telefono");
			BeanProperty rutaCliente = BeanProperty.create("ruta.nombre");
			
			binClientes.addColumnBinding(idCliente).setColumnClass(Integer.class).setColumnName("idCliente");
			binClientes.addColumnBinding(razonSocial).setColumnClass(String.class).setColumnName("Razon Social");
			binClientes.addColumnBinding(direccion).setColumnClass(String.class).setColumnName("Direccion");
			binClientes.addColumnBinding(telefono).setColumnClass(String.class).setColumnName("Telefono");
			binClientes.addColumnBinding(rutaCliente).setColumnClass(String.class).setColumnName("Ruta");
			binClientes.bind();

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void activarBindingDomicilioComercios(List<DomicilioComercio> domicilioComercios) {
		// TODO Auto-generated method stub
		pnTabla.setVisible(true);
		table = new JTable();
		scrollPanel.setViewportView(table);
		List<DomicilioComercio> aux = new ArrayList<DomicilioComercio>();
		for(DomicilioComercio domicilioComercio : domicilioComercios)
		{
			if(domicilioComercio.getTipo().equals("C")
					&& cmbTipoCliente.getSelectedItem().equals("Comercial"))aux.add(domicilioComercio);
			else if(domicilioComercio.getTipo().equals("D")
					&& cmbTipoCliente.getSelectedItem().equals("Domicilio"))aux.add(domicilioComercio);
		}
		domicilioComercios = aux;
		binClientes = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,
    			domicilioComercios,table);
		
		BeanProperty idCliente  = BeanProperty.create("id");
		BeanProperty razonSocial = BeanProperty.create("razonsocial");
		BeanProperty direccion = BeanProperty.create("direccion");
		BeanProperty telefono = BeanProperty.create("telefono");
		BeanProperty rutaCliente = BeanProperty.create("ruta.nombre");

		binClientes.addColumnBinding(idCliente).setColumnClass(Integer.class).setColumnName("idCliente");
		binClientes.addColumnBinding(razonSocial).setColumnClass(String.class).setColumnName("Razon Social");
		binClientes.addColumnBinding(direccion).setColumnClass(String.class).setColumnName("Direccion");
		binClientes.addColumnBinding(telefono).setColumnClass(String.class).setColumnName("Telefono");
		binClientes.addColumnBinding(rutaCliente).setColumnClass(String.class).setColumnName("Ruta");
		binClientes.bind();
	}

	
	@SuppressWarnings("rawtypes")
	public void activarJComboBoxBindingRuta(){
		JComboBoxBinding jcomboRutas = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ,rutas,comboRutas);
	    jcomboRutas.bind();
	}
	
	public void LimpiarTxt(){
	 txtDireccion.setText("");
	 txtRazonSocial.setText("");
	 txtTelefono.setText("");
	}
	public void activarNuevo() {
		// TODO Auto-generated method stub
		pnEditar.setVisible(true);
		pnTabla.setVisible(false);
		
	}
	
	public void quitarNuevo() {
		// TODO Auto-generated method stub
		pnEditar.setVisible(false);
		pnEditar.setBounds(12, 85, 726, 179);
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

	public JPanel getPnEditar() {
		return pnEditar;
	}

	public void setPnEditar(JPanel pnEditar) {
		this.pnEditar = pnEditar;
	}

	public JPanel getPnDomicilioComercial() {
		return pnDomicilioComercial;
	}

	public void setPnDomicilioComercial(JPanel pnDomicilioComercial) {
		this.pnDomicilioComercial = pnDomicilioComercial;
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

	public JTextField getTxtId() {
		return txtId;
	}

	public void setTxtId(JTextField txtId) {
		this.txtId = txtId;
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

	public JButton getBtnAbrirRuta() {
		return btnAbrirRuta;
	}

	public void setBtnAbrirRuta(JButton btnAbrirRuta) {
		this.btnAbrirRuta = btnAbrirRuta;
	}

	public JComboBox<String> getCmbTipoUsuario() {
		return cmbTipoCliente;
	}

	public void setCmbTipoUsuario(JComboBox<String> cmbTipoUsuario) {
		this.cmbTipoCliente = cmbTipoUsuario;
	}

	public JComboBox<Ruta> getComboRutas() {
		return comboRutas;
	}

	public void setComboRutas(JComboBox<Ruta> comboRutas) {
		this.comboRutas = comboRutas;
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

	@SuppressWarnings("rawtypes")
	public JTableBinding getBinClientes() {
		return binClientes;
	}

	@SuppressWarnings("rawtypes")
	public void setBinUsuarios(JTableBinding binUsuarios) {
		this.binClientes = binUsuarios;
	}

	@SuppressWarnings("rawtypes")
	public JTableBinding getBinRutas() {
		return binRutas;
	}

	@SuppressWarnings("rawtypes")
	public void setBinRutas(JTableBinding binRutas) {
		this.binRutas = binRutas;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Ruta> getRutas() {
		return rutas;
	}

	public void setRutas(List<Ruta> rutas) {
		this.rutas = rutas;
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

	public void setTxtRif(JTextField txtRif) {
		this.txtRif = txtRif;
	}

	@SuppressWarnings("rawtypes")
	public void setBinClientes(JTableBinding binClientes) {
		this.binClientes = binClientes;
	}
	
}
