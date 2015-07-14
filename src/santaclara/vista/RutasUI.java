package santaclara.vista;

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

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import santaclara.controlador.ContRutas;
import santaclara.modelo.Zona;
import santaclara.modelo.Ruta;
import net.miginfocom.swing.MigLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class RutasUI extends JPanel {

	private JPanel pnRutas;
	private JPanel pnBotones;
	private JPanel pnBuscar;
	private JPanel pnRuta;
	
	private JButton btnNuevo;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnSalir;
	private JButton btnBuscar;
	private JButton btnGuardar;
	private JButton btnCancelar;
	
	private JComboBox<Zona> cmbZona;
	
	private JTextField txtABuscar;
	private JTextField txtNombre;
	private JTextField txtId;
	
	private JLabel label;
	private JLabel label_1;
	
	private JScrollPane scrollPanel;
	
	private JTable tabla;
	
	@SuppressWarnings("rawtypes")
	private JTableBinding binRutas;
	
	private List<Zona> zonas = new ArrayList<Zona>();
	private List<Ruta> rutas = new ArrayList<Ruta>();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public RutasUI(ContRutas conRutas,List<Zona> zonas,List<Ruta> rutas) {
		this.rutas = rutas;
		this.zonas = zonas;
		
		setFont(new Font("Dialog", Font.BOLD, 13));
		setForeground(Color.WHITE);
		setBackground(Color.DARK_GRAY);
		setSize(864,537);
		setLayout(null);
		
		pnRutas = new JPanel();
		pnRutas.setLayout(null);
		pnRutas.setBorder(new TitledBorder(null, "Listado de Rutas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnRutas.setBackground(Color.GRAY);
		pnRutas.setBounds(22, 12, 817, 300);
		add(pnRutas);
		
		pnBotones = new JPanel();
		pnBotones.setBorder(new TitledBorder(null, "opciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnBotones.setBackground(Color.GRAY);
		pnBotones.setBounds(674, 38, 131, 223);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.setBounds(5, 57, 121, 40);
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setBackground(Color.DARK_GRAY);
		
		btnNuevo.addActionListener(conRutas.nuevo());
		pnBotones.setLayout(null);
		pnBotones.add(btnNuevo);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(5, 97, 121, 40);
		btnModificar.setForeground(Color.WHITE);
		btnModificar.setBackground(Color.DARK_GRAY);
		btnModificar.addActionListener(conRutas.modificar());
		pnBotones.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(5, 137, 121, 40);
		btnEliminar.setForeground(new Color(255, 255, 255));
		btnEliminar.setBackground(Color.DARK_GRAY);
		btnEliminar.addActionListener(conRutas.eliminar());
		pnBotones.add(btnEliminar);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(conRutas.salir());
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.DARK_GRAY);
		btnSalir.setBounds(5, 177, 121, 40);
		pnBotones.add(btnSalir);
		
		pnBuscar = new JPanel();
		pnBuscar.setBackground(Color.GRAY);
		pnBuscar.setForeground(Color.GRAY);
		pnBuscar.setBounds(25, 12, 635, 36);
		
		pnBuscar.setLayout(new MigLayout("", "[82px,grow][][][][][][][][][][][][][][][][][][][]", "[][][][25px]"));
		
		txtABuscar = new JTextField();
		txtABuscar.setBackground(Color.LIGHT_GRAY);
		pnBuscar.add(txtABuscar, "cell 0 0 19 1,growx");
		txtABuscar.setColumns(10);
		
		btnBuscar = new JButton("");
		btnBuscar.setIcon(new ImageIcon("/home/leo/git/santaclara/img/gestion/search.png"));
		btnBuscar.addActionListener(conRutas.buscar());
		btnBuscar.setBackground(Color.GRAY);
		
		pnBuscar.add(btnBuscar, "cell 19 0,alignx right,aligny top");
		
		pnRutas.add(pnBuscar);
		
		scrollPanel = new JScrollPane();
		scrollPanel.setBounds(25, 50, 635, 224);
		pnRutas.add(scrollPanel);
		
		tabla = new JTable();
		scrollPanel.setViewportView(tabla);
		
		pnRuta = new JPanel();
		pnRuta.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229), 1, true),
							"Editar Cliente",TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnRuta.setBackground(Color.GRAY);
		pnRuta.setBounds(22, 329, 817, 126);
	
		add(pnRuta);
		
		label = new JLabel("Zona:");
		label.setBounds(37, 87, 97, 15);
		label.setForeground(Color.WHITE);
		label.setBackground(Color.WHITE);
		
		label_1 = new JLabel("Nombre:");
		label_1.setBounds(37, 43, 94, 15);
		label_1.setForeground(Color.WHITE);
		label_1.setBackground(Color.WHITE);
		
		
		txtNombre = new JTextField();
		txtNombre.setBounds(133, 41, 672, 19);
		txtNombre.setColumns(10);
		
		cmbZona = new JComboBox();
		cmbZona.setBounds(143, 82, 258, 24);
		cmbZona.setRenderer(new ListCellRenderer() {
		@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				// TODO Auto-generated method stub
			Zona zona = (Zona)value;
			return new JLabel(zona.getDescripcion());
			}
		});
		
		pnRuta.setLayout(null);
		pnRuta.add(txtNombre);
		pnRuta.add(cmbZona);
		pnRuta.add(label);
		pnRuta.add(label_1);
		
		btnGuardar = new JButton("Guardar");  
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBackground(Color.DARK_GRAY);
		btnGuardar.addActionListener(conRutas.guardar());
		btnGuardar.setBounds(618, 82, 156, 25);
		pnRuta.add(btnGuardar);
		
		txtId = new JTextField();
		txtId.setBounds(27, 12, 114, 19);
		pnRuta.add(txtId);
		txtId.setColumns(10);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				quitarNuevo();
			}
		});
		
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(Color.DARK_GRAY);
		btnCancelar.setBounds(438, 82, 168, 25);
		pnRuta.add(btnCancelar);
		txtId.setVisible(false);
		
		pnRutas.add(pnBotones);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void activarBinding(List<Ruta> lstRutas) {
		// TODO Auto-generated method stub
		tabla = new JTable();
		scrollPanel.setViewportView(tabla);
		binRutas = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,
    			lstRutas,tabla);
				    
	    BeanProperty idRuta = BeanProperty.create("id");
	    BeanProperty zonaRuta = BeanProperty.create("zona.descripcion");
	    BeanProperty nombreRuta = BeanProperty.create("nombre");
	  
	    
	    binRutas.addColumnBinding(idRuta).setColumnClass(String.class).setColumnClass(String.class).setColumnName("id");
	    binRutas.addColumnBinding(zonaRuta).setColumnClass(String.class).setColumnClass(String.class).setColumnName("zona");
	    binRutas.addColumnBinding(nombreRuta).setColumnClass(String.class).setColumnClass(String.class).setColumnName("nombre");
	    
	    
	    binRutas.bind();
	    
		JComboBoxBinding jcomboZona = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ,zonas,cmbZona);
	    
	    jcomboZona.bind();
		
	}
	
	public void activarNuevoRuta() {
		// TODO Auto-generated method stub
		pnRuta.setVisible(true);
		txtNombre.setText("");
		txtId.setText("");
	}
	
	public void quitarNuevo() {
		// TODO Auto-generated method stub
		pnRuta.setVisible(false);
	}

	public JPanel getPnRutas() {
		return pnRutas;
	}

	public void setPnRutas(JPanel pnRutas) {
		this.pnRutas = pnRutas;
	}

	public JPanel getPnBotones() {
		return pnBotones;
	}

	public void setPnBotones(JPanel pnBotones) {
		this.pnBotones = pnBotones;
	}

	public JPanel getPnBuscar() {
		return pnBuscar;
	}

	public void setPnBuscar(JPanel pnBuscar) {
		this.pnBuscar = pnBuscar;
	}

	public JPanel getPnRuta() {
		return pnRuta;
	}

	public void setPnRuta(JPanel pnRuta) {
		this.pnRuta = pnRuta;
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

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public void setBtnSalir(JButton btnSalir) {
		this.btnSalir = btnSalir;
	}

	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public void setBtnBuscar(JButton btnBuscar) {
		this.btnBuscar = btnBuscar;
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

	public JComboBox<Zona> getCmbZona() {
		return cmbZona;
	}

	public void setCmbZona(JComboBox<Zona> cmbZona) {
		this.cmbZona = cmbZona;
	}

	public JTextField getTxtABuscar() {
		return txtABuscar;
	}

	public void setTxtABuscar(String txtABuscar) {
		this.txtABuscar.setText(txtABuscar);
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JTextField getTxtId() {
		return txtId;
	}

	public void setTxtId(JTextField txtId) {
		this.txtId = txtId;
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public JLabel getLabel_1() {
		return label_1;
	}

	public void setLabel_1(JLabel label_1) {
		this.label_1 = label_1;
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

	@SuppressWarnings("rawtypes")
	public JTableBinding getBinRutas() {
		return binRutas;
	}

	@SuppressWarnings("rawtypes")
	public void setBinRutas(JTableBinding binRutas) {
		this.binRutas = binRutas;
	}

	public List<Zona> getZonas() {
		return zonas;
	}

	public void setZonas(List<Zona> zonas) {
		this.zonas = zonas;
	}

	public List<Ruta> getRutas() {
		return rutas;
	}

	public void setRutas(List<Ruta> rutas) {
		this.rutas = rutas;
	}
}

