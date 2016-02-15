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
import santaclara.controlador.ContConcesionarios;
import santaclara.controlador.ContJefeVentas;
import santaclara.modelo.Camion;
import santaclara.modelo.Concesionario;
import santaclara.modelo.Ruta;
import santaclara.modelo.Zona;
import santaclara.vista.herramientas.VistaGenericaUI;


@SuppressWarnings("serial")
public class ConcesionarioUI  extends VistaGenericaUI  {
	
	
	private JPanel pnUsuario;
	private JLabel lblUserName;
	private JLabel lblCedula;
	private JLabel lblContrasena;
	private JLabel lblNombre;
	private JLabel lblReContrasena;
	private JLabel lblZona;
	private JLabel lblCamion;
	
	private JTextField txtUserName;
	private JTextField txtId;
	private JTextField txtABuscar;
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JLabel 	   lblCamionInformacion;
	
	private JPasswordField txtContrasena;
	private JPasswordField txtReContrasena;
	
	private JButton btnNuevo; 
	private JButton btnSalir;
	private JButton btnABuscar;
	private JButton btnEliminar;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JButton bntAgregarCamion;
	private ContConcesionarios controlador;
	private JComboBox<Zona> cmbRuta;

	
	private Camion camion;
	private List concecionarios = new ArrayList<Concesionario>();
	private List<Ruta> rutas = new ArrayList<Ruta>();

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ConcesionarioUI(ContConcesionarios controlador,List  concecionarios,List<Ruta> rutas ) {
		super();
		this.controlador = controlador;
		this.setConcecionarios(concecionarios);
		this.rutas = rutas;
		dibujarPanelOpciones();
		dibujarPanelTabla();
	 
		txtABuscar = new JTextField();
		txtABuscar.setForeground(Color.WHITE);
		txtABuscar.setBackground(new Color(64, 64, 64));
		txtABuscar.setColumns(10);
		getPanelBuscar().add(txtABuscar);
		
		
		btnABuscar = new JButton("");
		btnABuscar.addActionListener(controlador.buscar());
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
		//txtCedula.setEnabled(false);
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

		cmbRuta = new JComboBox();
		//cmbZona.setBounds(56, 22, 248, 24);
		cmbRuta.setRenderer(new ListCellRenderer() {
			
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				// TODO Auto-generated method stub
				Ruta ruta = (Ruta)value;
				return new JLabel(ruta.getNombre()+" -- "+ ruta.getZona().getDescripcion());	
			}
		});
		pnUsuario.add(cmbRuta,"cell 3 2");
		activarJComboBoxBindingZona();
		
   	    lblCamion = new JLabel("Camion:");
		lblCamion.setForeground(Color.WHITE);
		lblCamion.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		pnUsuario.add(lblCamion,"cell 0 3");
		
		lblCamionInformacion = new JLabel("");
		lblCamionInformacion.setForeground(Color.WHITE);
		lblCamionInformacion.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		pnUsuario.add(lblCamionInformacion,"cell 1 3");
		
		bntAgregarCamion = new JButton();
		bntAgregarCamion.addActionListener(controlador.verCamiones());
		bntAgregarCamion.setSize(200,200);
		bntAgregarCamion.setForeground(Color.WHITE);
		bntAgregarCamion.setBackground(Color.DARK_GRAY);
		bntAgregarCamion.setIcon(new ImageIcon("img/gestion/add.png"));
		pnUsuario.add(bntAgregarCamion,"cell 2 3");

		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(controlador.guardar());
		btnGuardar.setSize(200,200);
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBackground(Color.DARK_GRAY);
		pnUsuario.add(btnGuardar,"cell 2 4");

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(Color.DARK_GRAY);
		pnUsuario.add(btnCancelar,"cell 2 4");
  
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(controlador.nuevo());
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setBackground(Color.DARK_GRAY);
		btnNuevo.setIcon(new ImageIcon("img/gestion/add.png"));
		getPnBotones().add(btnNuevo);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(Color.DARK_GRAY);
		btnEliminar.setIcon(new ImageIcon("img/gestion/cancel.png"));
		btnEliminar.addActionListener(controlador.eliminar());
		getPnBotones().add(btnEliminar);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(controlador.salir());
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.DARK_GRAY);
		btnSalir.setIcon(new ImageIcon("img/gestion/SalirCurva.png"));
		getPnBotones().add(btnSalir);
		activarBinding(concecionarios);
		//initDataBindings();
		
	}
 
 
	
 
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void activarBinding(List concecionarios) {
		// TODO Auto-generated method stub
		this.setConcecionarios(concecionarios);
		getPnTabla().setVisible(true);
		setTable(new JTable());
		getScrollPanel().setViewportView(getTable());

		JTableBinding binUsuarios = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,concecionarios,getTable());
		BeanProperty idUsuario  = BeanProperty.create("id");
		BeanProperty UserNameUsuario = BeanProperty.create("username");
		BeanProperty CedulaUsuario = BeanProperty.create("cedula");
		BeanProperty NombreUsuario = BeanProperty.create("nombre");
		BeanProperty ruta = BeanProperty.create("ruta.nombre");
		BeanProperty zona = BeanProperty.create("ruta.zona.descripcion");
		binUsuarios.addColumnBinding(idUsuario).setColumnClass(Integer.class).setColumnName("idJefeVenta ");
		binUsuarios.addColumnBinding(UserNameUsuario).setColumnClass(String.class).setColumnName("UserName");
		binUsuarios.addColumnBinding(CedulaUsuario).setColumnClass(String.class).setColumnName("Cedula");
		binUsuarios.addColumnBinding(NombreUsuario).setColumnClass(String.class).setColumnName("Nombre");
		binUsuarios.addColumnBinding(ruta).setColumnClass(String.class).setColumnName("Ruta");
		binUsuarios.addColumnBinding(zona).setColumnClass(String.class).setColumnName("Zona");
	    binUsuarios.bind();
	    
	    
		getTable().addMouseListener(controlador.mostrar());

	}
	
  
	
	@SuppressWarnings("rawtypes")
	public void activarJComboBoxBindingZona(){
		JComboBoxBinding jcomboZonas = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ,rutas,cmbRuta);
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
	

	public void cargar(Concesionario concecionario) {
		// TODO Auto-generated method stub

		txtUserName.setText(concecionario.getUsername());
		if(concecionario.getId() != null)
		{
			txtId.setText(concecionario.getId().toString());
		}
		txtCedula.setText(concecionario.getCedula());
		txtNombre.setText(concecionario.getNombre());
		txtContrasena.setText(concecionario.getContrasena());
		txtReContrasena.setText(concecionario.getContrasena());
		if(concecionario.getRuta() != null )
		{
			for(int i =0 ; i < rutas.size(); i++)
			{
				Ruta ruta = rutas.get(i);
				if (ruta.getId().equals(concecionario.getRuta().getId()))
				{
					cmbRuta.setSelectedIndex(i);
					break;
				}
			} 
		}
		camion = concecionario.getCamion();
		if(concecionario.getCamion() != null)
		{
			String datos = concecionario.getCamion().getPlaca()+" "+concecionario.getCamion().getModelo()+" "+concecionario.getCamion().getMarca();
			lblCamionInformacion.setText(datos);
		}
		else
		{
			lblCamionInformacion.setText("No Definido ");
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
 

	public JLabel getLblZona() {
		return lblZona;
	}

	public void setLblZona(JLabel lblZona) {
		this.lblZona = lblZona;
	}
   

	public void setTxtContrasena(JPasswordField txtContrasena) {
		this.txtContrasena = txtContrasena;
	}

	public void setTxtReContrasena(JPasswordField txtReContrasena) {
		this.txtReContrasena = txtReContrasena;
	}
  

	public JComboBox<Zona> getCmbRuta() {
		return cmbRuta;
	}

	
	public void setCmbRuta(JComboBox<Zona> cmbRuta) {
		this.cmbRuta = cmbRuta;
	}


	public List<Ruta> getRutas() {
		return rutas;
	}


	public void setRutas(List<Ruta> rutas) {
		this.rutas = rutas;
	}





	public List getConcecionarios() {
		return concecionarios;
	}





	public void setConcecionarios(List concecionarios) {
		this.concecionarios = concecionarios;
	}





	public Camion getCamion() {
		return camion;
	}





	public void setCamion(Camion camion) {
		this.camion = camion;
	}
 
}
