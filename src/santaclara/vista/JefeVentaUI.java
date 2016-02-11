package santaclara.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import net.miginfocom.swing.MigLayout;
import santaclara.controlador.ContJefeVentas;
import santaclara.modelo.JefeVenta;
import santaclara.modelo.Zona;
import santaclara.vista.herramientas.VistaGenericaUI;

@SuppressWarnings("serial")
public class JefeVentaUI  extends VistaGenericaUI  {
	
	private JPanel pnUsuario;
	
	private JLabel lblUserName;
	private JLabel lblCedula;
	private JLabel lblContrasena;
	private JLabel lblNombre;
	private JLabel lblReContrasena;
	private JLabel lblZona;

	private JTextField txtUserName;
	private JTextField txtId;
	private JTextField txtABuscar;
	private JTextField txtCedula;
	private JTextField txtNombre;
	
	private JPasswordField txtContrasena;
	private JPasswordField txtReContrasena;
	
	private JButton btnNuevo; 
	private JButton btnSalir;
	private JButton btnABuscar;
	private JButton btnEliminar;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private ContJefeVentas contJefeVentas;
	private JComboBox<Zona> cmbZona;
		
	@SuppressWarnings("rawtypes")
	private List jefeVentas = new ArrayList<JefeVenta>();
	private List<Zona> zonas = new ArrayList<Zona>();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JefeVentaUI(ContJefeVentas contJefeVentas,List  jefeVentas,List<Zona> zonas ) {
		super();
		this.contJefeVentas = contJefeVentas;
		this.jefeVentas = jefeVentas;
		this.zonas = zonas;
		dibujarPanelOpciones();
		dibujarPanelTabla();
	 
		txtABuscar = new JTextField();
		txtABuscar.setForeground(Color.WHITE);
		txtABuscar.setBackground(new Color(64, 64, 64));
		txtABuscar.setColumns(10);
		getPanelBuscar().add(txtABuscar);
		
		
		btnABuscar = new JButton("");
		btnABuscar.addActionListener(contJefeVentas.buscar());
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
		txtNombre.setColumns(10);
		//txtNombre.setBounds(382, 17, 126, 16);
		pnUsuario.add(txtNombre,"cell 1 2");

		lblZona = new JLabel("Zona:");
		lblZona.setForeground(Color.WHITE);
		lblZona.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		pnUsuario.add(lblZona,"cell 2 2");

		cmbZona = new JComboBox();
		//cmbZona.setBounds(56, 22, 248, 24);
		cmbZona.setRenderer(new ListCellRenderer() {
			
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				// TODO Auto-generated method stub
				Zona zona = (Zona)value;
				return new JLabel(zona.getDescripcion());	
			}
		});
		pnUsuario.add(cmbZona,"cell 3 2");
		activarJComboBoxBindingZona();
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(contJefeVentas.guardar());
		btnGuardar.setSize(200,200);
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBackground(Color.DARK_GRAY);
		pnUsuario.add(btnGuardar,"cell 2 3");

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(Color.DARK_GRAY);
		pnUsuario.add(btnCancelar,"cell 2 3");
  
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(contJefeVentas.nuevo());
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setBackground(Color.DARK_GRAY);
		btnNuevo.setIcon(new ImageIcon("img/gestion/add.png"));
		getPnBotones().add(btnNuevo);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(Color.DARK_GRAY);
		btnEliminar.setIcon(new ImageIcon("img/gestion/cancel.png"));
		btnEliminar.addActionListener(contJefeVentas.eliminar());
		getPnBotones().add(btnEliminar);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(contJefeVentas.salir());
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.DARK_GRAY);
		btnSalir.setIcon(new ImageIcon("img/gestion/SalirCurva.png"));
		getPnBotones().add(btnSalir);
		activarBinding(jefeVentas);
		//initDataBindings();
		
	}
 
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void activarBinding(List jefeventas) {
		// TODO Auto-generated method stub
		this.jefeVentas = jefeventas;
		getPnTabla().setVisible(true);
		setTable(new JTable());
		getScrollPanel().setViewportView(getTable());

		JTableBinding binUsuarios = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,jefeVentas,getTable());
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
	    
		getTable().addMouseListener(contJefeVentas.mostrar());

	}
	
    @SuppressWarnings("rawtypes")
	public void activarJComboBoxBindingZona(){
		JComboBoxBinding jcomboZonas = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ,zonas,cmbZona);
	    jcomboZonas.bind();
	}
	
	public void LimpiarTxt(){
		getTxtId().setText("");
		getTxtCedula().setText("");
		getTxtNombre().setText("");
		getTxtReContrasena().setText("");
		getTxtContrasena().setText("");
		getTxtUserName().setText("");
	}
	

	public void cargarJefeVenta(JefeVenta jefeVenta) {
		// TODO Auto-generated method stub
		txtUserName.setText(jefeVenta.getUsername());
		txtUserName.setEnabled(false);
		txtId.setText(jefeVenta.getId().toString());
		txtCedula.setText(jefeVenta.getCedula());
		txtCedula.setEnabled(false);
		txtNombre.setText(jefeVenta.getNombre());
		txtContrasena.setText(jefeVenta.getContrasena());
		txtReContrasena.setText(jefeVenta.getContrasena());
		for(int i =0 ; i < zonas.size(); i++)
		{
			Zona zona = zonas.get(i);
			if (zona.getId().equals(jefeVenta.getZona().getId()))
			{
				cmbZona.setSelectedIndex(i);
				break;
			}
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

	public ContJefeVentas getContJefeVentas() {
		return contJefeVentas;
	}

	public void setContJefeVentas(ContJefeVentas contJefeVentas) {
		this.contJefeVentas = contJefeVentas;
	}
	
	@SuppressWarnings("rawtypes")
	public List getJefeVentas() {
		return jefeVentas;
	}

	@SuppressWarnings("rawtypes")
	public void setJefeVentas(List jefeVentas) {
		this.jefeVentas = jefeVentas;
	}


 
}
