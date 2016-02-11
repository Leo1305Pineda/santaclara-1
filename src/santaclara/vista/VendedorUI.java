package santaclara.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField; 
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty; 
import org.jdesktop.beansbinding.ELProperty;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.JListBinding;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import net.miginfocom.swing.MigLayout; 
import santaclara.controlador.ContVendedores;
import santaclara.modelo.Ruta;
import santaclara.modelo.Vendedor;
import santaclara.modelo.Zona;
import santaclara.vista.herramientas.VistaGenericaUI;

@SuppressWarnings("serial")
public class VendedorUI  extends VistaGenericaUI  {
	
	private JPanel pnUsuario;
	
	private JLabel lblUserName;
	private JLabel lblCedula;
	private JLabel lblContrasena;
	private JLabel lblNombre;
	private JLabel lblReContrasena;
 
	private JTextField txtUserName;
	private JTextField txtId;
	private JTextField txtABuscar;
	private JTextField txtCedula;
	private JTextField txtNombre;
	
	private JPasswordField txtContrasena;
	private JPasswordField txtReContrasena;

	private JList	 jTblRutas;
	private JScrollPane scPaneRuta;
 
	private JComboBox cmbRuta;
	private JComboBox cmbZona;
	
	
	private JButton btnNuevo; 
	private JButton btnSalir;
	private JButton btnABuscar;
	private JButton btnEliminar;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JButton btnAgregar;
	private JButton btnQuitar;
	
	private ContVendedores contVendedores;

	
	@SuppressWarnings("rawtypes")
	private List vendedores = new ArrayList<Vendedor>();
	private List<Zona> zonas = new ArrayList<Zona>();
	private List<Ruta> rutasVendedor = new ArrayList<Ruta>();

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public VendedorUI(ContVendedores contVendedores,List  vendedores,List<Zona> zonas ) {
		super();
		this.contVendedores = contVendedores;
		this.vendedores = vendedores;
		this.zonas = zonas;
		
		dibujarPanelOpciones();
		dibujarPanelTabla();
	 
		txtABuscar = new JTextField();
		txtABuscar.setForeground(Color.WHITE);
		txtABuscar.setBackground(new Color(64, 64, 64));
		txtABuscar.setColumns(10);
		getPanelBuscar().add(txtABuscar);
		
		
		btnABuscar = new JButton("");
		//btnABuscar.addActionListener(contVendedores.buscar());
		btnABuscar.setVerticalAlignment(SwingConstants.TOP);
		btnABuscar.setBackground(Color.DARK_GRAY);
		btnABuscar.setIcon(new ImageIcon("img/gestion/buscar.png"));
		getPanelBuscar().add(btnABuscar);
		
		
		pnUsuario = new JPanel();
		pnUsuario.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)),"Editar Usuario", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnUsuario.setBackground(Color.DARK_GRAY);
		pnUsuario.setLayout(new MigLayout());
		add(pnUsuario,BorderLayout.SOUTH);
				
		
		lblUserName = new JLabel("UserName:");
		lblUserName.setForeground(Color.WHITE);
		lblUserName.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		pnUsuario.add(lblUserName,"cell 0 0");
		
		txtUserName = new JTextField();
		txtUserName.setColumns(10);
		txtUserName.setEnabled(false);
		pnUsuario.add(txtUserName,"cell 1 0");
		
		txtId = new JTextField();
		txtId.setColumns(10); 
		txtId.setBounds(163, 9, 54, 19);
		txtId.setVisible(false);
		pnUsuario.add(txtId);
		
		lblCedula = new JLabel("Cedula:");
		lblCedula.setForeground(Color.WHITE);
		lblCedula.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		pnUsuario.add(lblCedula,"cell 2 0");
		
		txtCedula = new JTextField();
		txtCedula.setColumns(10);
		txtCedula.setEnabled(false);
		pnUsuario.add(txtCedula,"cell 3 0");

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Rutas", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		panel.setFont(new Font("Dialog", Font.BOLD, 13));
		panel.setForeground(Color.WHITE);
		panel.setBackground(Color.DARK_GRAY);
		panel.setLayout(new MigLayout());

		JLabel lblzona = new JLabel("Zona");
		lblzona.setForeground(Color.WHITE);
		lblzona.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		panel.add(lblzona,"cell 0 0");
 
		cmbZona = new JComboBox();
		cmbZona.setModel(new DefaultComboBoxModel(zonas.toArray()));
		cmbZona.addActionListener(contVendedores.cargarRuta());
		cmbZona.setRenderer(new ListCellRenderer() {

			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				// TODO Auto-generated method stub
				Zona zona = (Zona) value;
				return new JLabel(zona.getDescripcion());
			}
		});
		
		panel.add(cmbZona,"cell 1 0");

		JLabel lblRuta = new JLabel("Ruta");
		lblRuta.setForeground(Color.WHITE);
		lblRuta.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		panel.add(lblRuta,"cell 2 0");
		
		cmbRuta = new JComboBox();
		
		panel.add(cmbRuta,"wrap");
	
		jTblRutas = new JList();
		scPaneRuta = new JScrollPane();
		scPaneRuta.setViewportView(jTblRutas);
		panel.add(scPaneRuta,"span 4");

		btnAgregar = new JButton("+");
		btnAgregar.setForeground(Color.WHITE);
		btnAgregar.setBackground(Color.DARK_GRAY);
		btnAgregar.addActionListener(contVendedores.agregarRuta());
		panel.add(btnAgregar,"cell 6 1");

		btnQuitar = new JButton("-");
		btnQuitar.setForeground(Color.WHITE);
		btnQuitar.setBackground(Color.DARK_GRAY);
		btnQuitar.addActionListener(contVendedores.quitarRuta());
		panel.add(btnQuitar,"cell 6 1");

		
		
		pnUsuario.add(panel,"cell 5 0 5 4");
 

		lblContrasena = new JLabel("Contraseña:");
		lblContrasena.setForeground(Color.WHITE);
		lblContrasena.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		pnUsuario.add(lblContrasena,"cell 0 1");
		
		txtContrasena = new JPasswordField();
		txtContrasena.setColumns(10);
		pnUsuario.add(txtContrasena,"cell 1 1");

		lblReContrasena = new JLabel("Repetir Contraseña:");
		lblReContrasena.setForeground(Color.WHITE);
		lblReContrasena.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		pnUsuario.add(lblReContrasena,"cell 2 1");

		txtReContrasena = new JPasswordField();
		txtReContrasena.setColumns(10);
		pnUsuario.add(txtReContrasena,"cell 3 1");

		lblNombre = new JLabel("Nombre Y Apellido:");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		pnUsuario.add(lblNombre,"cell 0 2");

		txtNombre = new JTextField();
		txtNombre.setColumns(30);
		//txtNombre.setBounds(382, 17, 126, 16);
		pnUsuario.add(txtNombre,"span 3");


		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(contVendedores.guardar());
		btnGuardar.setSize(200,200);
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBackground(Color.DARK_GRAY);
		pnUsuario.add(btnGuardar,"cell 1 3 2 3");

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(Color.DARK_GRAY);
		pnUsuario.add(btnCancelar,"cell 1 3 2 3");
  
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(contVendedores.nuevo());
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setBackground(Color.DARK_GRAY);
		btnNuevo.setIcon(new ImageIcon("img/gestion/add.png"));
		getPnBotones().add(btnNuevo);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(Color.DARK_GRAY);
		btnEliminar.setIcon(new ImageIcon("img/gestion/cancel.png"));
		//btnEliminar.addActionListener(contJefeVentas.eliminar());
		getPnBotones().add(btnEliminar);
		
		btnSalir = new JButton("Salir");
		//btnSalir.addActionListener(contJefeVentas.salir());
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.DARK_GRAY);
		btnSalir.setIcon(new ImageIcon("img/gestion/SalirCurva.png"));
		getPnBotones().add(btnSalir);
		activarBinding(vendedores);
		//activarJComboBoxBindingZona();
		//initDataBindings();
		
	}
 
	
    @SuppressWarnings("rawtypes")
	public void activarJComboBoxBindingZona(){
		JComboBoxBinding jcomboZonas = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ,zonas,cmbZona);
 	    jcomboZonas.bind();
	}
	
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void activarBinding(List vendedores) {
		// TODO Auto-generated method stub
		this.vendedores = vendedores;
		getPnTabla().setVisible(true);
		setTable(new JTable());
		getScrollPanel().setViewportView(getTable());

		JTableBinding binUsuarios = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,vendedores,getTable());
		BeanProperty idUsuario  = BeanProperty.create("id");
		BeanProperty UserNameUsuario = BeanProperty.create("username");
		BeanProperty CedulaUsuario = BeanProperty.create("cedula");
		BeanProperty NombreUsuario = BeanProperty.create("nombre");
		binUsuarios.addColumnBinding(idUsuario).setColumnClass(Integer.class).setColumnName("idJefeVenta ");
		binUsuarios.addColumnBinding(UserNameUsuario).setColumnClass(String.class).setColumnName("UserName");
		binUsuarios.addColumnBinding(CedulaUsuario).setColumnClass(String.class).setColumnName("Cedula");
		binUsuarios.addColumnBinding(NombreUsuario).setColumnClass(String.class).setColumnName("Nombre");
	    binUsuarios.bind();
	    
		getTable().addMouseListener(contVendedores.mostrar());

	}
 
	
	public void LimpiarTxt(){
		getTxtId().setText("");
		getTxtCedula().setText("");
		getTxtNombre().setText("");
		getTxtReContrasena().setText("");
		getTxtContrasena().setText("");
		getTxtUserName().setText("");
		rutasVendedor.clear();
		activarBindingRuta(rutasVendedor);
		activarBinding(vendedores);
	}
	

	public void cargar(Vendedor usuario) {
		// TODO Auto-generated method stub
		txtUserName.setText(usuario.getUsername());
		txtUserName.setEnabled(false);
		txtId.setText(usuario.getId().toString());
		txtCedula.setText(usuario.getCedula());
		txtCedula.setEnabled(false);
		txtNombre.setText(usuario.getNombre());
		txtContrasena.setText(usuario.getContrasena());
		txtReContrasena.setText(usuario.getContrasena());
		activarBindingVendedorRuta(usuario.getRutas());
 
	}   
	
	public void activarBindingVendedorRuta(List<Ruta> rutas) {
		// TODO Auto-generated method stub
		this.rutasVendedor = rutas;
 		jTblRutas = new JList();
		jTblRutas.setSize(200,20);
		scPaneRuta.setViewportView(jTblRutas);
		JListBinding binRuta = SwingBindings.createJListBinding(AutoBinding.UpdateStrategy.READ_WRITE,rutas,jTblRutas);
		ELProperty ruta = ELProperty.create("${nombre} ${zona.descripcion}");
		binRuta.setDetailBinding(ruta);
		binRuta.bind(); 
		
	}
	

	public void activarBindingRuta(List<Ruta> rutas) {
		// TODO Auto-generated method stub
		if(rutas.size() != 0 )
		{
			cmbRuta.setRenderer(new ListCellRenderer() {
				@Override
				public Component getListCellRendererComponent(JList list,
						Object value, int index, boolean isSelected,
						boolean cellHasFocus) {
					// TODO Auto-generated method stub
					Ruta ruta = (Ruta) value;
					return new JLabel(ruta.getNombre());
				}
			});
			cmbRuta.setModel(new DefaultComboBoxModel(rutas.toArray()));
			if(rutas.size() == 1)
			{
				cmbRuta.setSelectedIndex(0);
			}
		}
		else
		{
			cmbRuta.removeAllItems();
		}
		
	}
	
	

	public JButton getBtnNuevo() {
		return btnNuevo;
	}

	public void setBtnNuevo(JButton btnNuevo) {
		this.btnNuevo = btnNuevo;
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
 


	public JTextField getTxtABuscar() {
		return txtABuscar;
	}

	public void setTxtABuscar(JTextField txtABuscar) {
		this.txtABuscar = txtABuscar;
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

	public JLabel getLblUserName(JLabel lblUserName) {
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
 
	public List<Zona> getZonas() {
		return zonas;
	}

	public void setZonas(List<Zona> zonas) {
		this.zonas = zonas;
	}
 

	public void setTxtContrasena(JPasswordField txtContrasena) {
		this.txtContrasena = txtContrasena;
	}

	public void setTxtReContrasena(JPasswordField txtReContrasena) {
		this.txtReContrasena = txtReContrasena;
	}

	public JScrollPane getScPaneRuta() {
		return scPaneRuta;
	}

	public void setScPaneRuta(JScrollPane scPaneRuta) {
		this.scPaneRuta = scPaneRuta;
	}

	public List getVendedores() {
		return vendedores;
	}

	public void setVendedores(List vendedores) {
		this.vendedores = vendedores;
	}

 
	public JComboBox getCmbZona() {
		return cmbZona;
	}


	public void setCmbZona(JComboBox cmbZona) {
		this.cmbZona = cmbZona;
	}


	public JButton getBtnAgregar() {
		return btnAgregar;
	}


	public void setBtnAgregar(JButton btnAgregar) {
		this.btnAgregar = btnAgregar;
	}


	public JButton getBtnQuitar() {
		return btnQuitar;
	}


	public void setBtnQuitar(JButton btnQuitar) {
		this.btnQuitar = btnQuitar;
	}


	public JList getjTblRutas() {
		return jTblRutas;
	}


	public void setjTblRutas(JList jTblRutas) {
		this.jTblRutas = jTblRutas;
	}


	public List<Ruta> getRutasVendedor() {
		return rutasVendedor;
	}


	public void setRutasVendedor(List<Ruta> rutasVendedor) {
		this.rutasVendedor = rutasVendedor;
	}

	

	public JComboBox getCmbRuta() {
		return cmbRuta;
	}


	public void setCmbRuta(JComboBox cmbRuta) {
		this.cmbRuta = cmbRuta;
	}


	public void quitarRuta(Ruta ruta) {
		// TODO Auto-generated method stub
		rutasVendedor.remove(ruta);
		activarBindingVendedorRuta(rutasVendedor);
	}


	public void agregarRuta(Ruta ruta) {
		// TODO Auto-generated method stub
		for(Ruta r : rutasVendedor)
		{
			if(r.getId().equals(ruta.getId()))
			{
				return;
			}
		}
		rutasVendedor.add(ruta);
		activarBindingVendedorRuta(rutasVendedor);
	}

 
}
