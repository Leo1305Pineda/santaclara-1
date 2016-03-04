/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.vista;

import java.awt.BorderLayout;
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
import santaclara.vista.herramientas.VistaGenericaUI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;

import javax.swing.table.DefaultTableModel;

import org.jdesktop.beansbinding.ObjectProperty;

import com.sun.xml.internal.ws.org.objectweb.asm.Label;

import javax.swing.ListSelectionModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.MouseMotionAdapter;
import java.awt.FlowLayout;

@SuppressWarnings("serial")
public class UsuariosUI  extends VistaGenericaUI  {
	

	private JPanel pnUsuario;
	private JButton btnAtras;
	private JButton btnSalir;
	private JLabel lblTipoUsuario;
	private JComboBox<String> cmbTipoUsuario ;

	@SuppressWarnings("rawtypes")
	private JTableBinding binUsuarios;
	private List usuarios = new ArrayList<Usuario>();
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public UsuariosUI(ContUsuarios contUsuarios,List<Usuario> usuarios) {
		super();
		this.usuarios = usuarios;
		dibujarPanelOpciones();
		dibujarPanelTabla();

		lblTipoUsuario = new JLabel("Tipo Usuario:");
		lblTipoUsuario.setForeground(Color.WHITE);
		lblTipoUsuario.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblTipoUsuario.setBounds(12, 18, 113, 25);
		getPanelBuscar().add(lblTipoUsuario);

		
		//pnUsuarios.add(lblTipoUsuario);
		
		cmbTipoUsuario = new JComboBox();
		cmbTipoUsuario.setModel(new DefaultComboBoxModel(new String[] {"Todos", "JefeVenta", "Vendedor", "Concesionario"}));
		cmbTipoUsuario.addActionListener(contUsuarios.ActivarTipoUsuario());
		cmbTipoUsuario.setBounds(114, 18, 200, 20);
		getPanelBuscar().add(cmbTipoUsuario);
		

		pnUsuario = new JPanel();
		pnUsuario.setLayout(null);
		pnUsuario.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)),"Editar Usuario", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnUsuario.setBackground(Color.DARK_GRAY);
		add(pnUsuario,BorderLayout.SOUTH);
		
		
		getPnBotones().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(contUsuarios.atras());
		btnAtras.setForeground(Color.WHITE);
		btnAtras.setBackground(Color.DARK_GRAY);
		btnAtras.setIcon(new ImageIcon("img/gestion/AtrasCurva.png"));
		getPnBotones().add(btnAtras);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(contUsuarios.salir());
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.DARK_GRAY);
		btnSalir.setIcon(new ImageIcon("img/gestion/SalirCurva.png"));
		getPnBotones().add(btnSalir);
		System.out.println(usuarios.size());
		activarBinding(usuarios);
		//initDataBindings();
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void activarBindingVendedores(List<Vendedor> vendedores) {
		this.usuarios = vendedores;
		getPnTabla().setVisible(true);
		setTable(new JTable());
		getScrollPanel().setViewportView(getTable());
		binUsuarios = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,vendedores,getTable());
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
		this.usuarios = jefeVentas;
		getPnTabla().setVisible(true);
		setTable(new JTable());
		getScrollPanel().setViewportView(getTable());
		binUsuarios = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,jefeVentas,getTable());
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
		this.usuarios =concesionarios;
		getPnTabla().setVisible(true);
		setTable(new JTable());
		getScrollPanel().setViewportView(getTable());
		binUsuarios = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,concesionarios,getTable());
		
		binUsuarios = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,concesionarios,getTable());
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
		getPnTabla().setVisible(true);
		setTable(new JTable());
		getScrollPanel().setViewportView(getTable());

		binUsuarios = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,usuarios,getTable());
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

	@SuppressWarnings("rawtypes")
	public JComboBox getCmbTipoUsuario() {
		return cmbTipoUsuario;
	}
	
	public void setCmbTipoUsuario(JComboBox<String> cmbTipoUsuario) {
		this.cmbTipoUsuario = cmbTipoUsuario;
	}

	@SuppressWarnings("rawtypes")
	public JTableBinding getBinUsuarios() {
		return binUsuarios;
	}

	@SuppressWarnings("rawtypes")
	public void setBinUsuarios(JTableBinding binUsuarios) {
		this.binUsuarios = binUsuarios;
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
      
  
 
	
}
