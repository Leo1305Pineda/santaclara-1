package santaclara.vista;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import santaclara.controlador.ContRutas;
import santaclara.modelo.Zona;
import santaclara.modelo.Ruta;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class RutasUI extends JPanel {

	private JTable tabla;
	
	private JScrollPane scrollPanel; 
	
	private List<Ruta> rutas = new ArrayList<Ruta>();
	@SuppressWarnings("rawtypes")
	private JTableBinding  binRutas;

	private JTextField txtBuscar;

	private List<Zona> zonas = new ArrayList<Zona>();
	
    private JComboBox<Zona> cmbZona;
    private JButton btnGuardar;

    private JTextField 			txtNombre;
    private JTextField  txtId; 
	private JPanel 	  			 panelRuta;
	
	private JButton btnNuevo;
	private JButton	btnModificar;
	private JButton btnEliminar;
	private JButton button;
	private JButton Zona;
	private JButton btnAtras;
	
	private JTextField txtABuscar;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public RutasUI(ContRutas contRutas, List<Ruta> rutas,List<Zona> zonas) {
		
		this.rutas = rutas;
		this.zonas = zonas;
		
		setFont(new Font("Dialog", Font.BOLD, 13));
		setForeground(Color.WHITE);
		setBackground(Color.DARK_GRAY);
		setSize(763,427);
		setLayout(null);
		
		JPanel pnRutas = new JPanel();
		pnRutas.setLocation(12, 12);
		pnRutas.setSize(735,339);
		pnRutas.setBackground(Color.DARK_GRAY);
		pnRutas.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Modulo Rutas", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		add(pnRutas);

		
		scrollPanel = new JScrollPane();
		scrollPanel.setBounds(12, 96, 717, 182);
		tabla = new JTable();
		tabla.setBackground(Color.GRAY);
		scrollPanel.setViewportView(tabla);
		pnRutas.setLayout(null);
				
				
		pnRutas.add(scrollPanel);
		
		JPanel pnAction = new JPanel();
		pnAction.setBackground(Color.GRAY);
		pnAction.setForeground(Color.DARK_GRAY);
		pnAction.setBounds(12, 47, 717, 48);
		pnRutas.add(pnAction);
		pnAction.setLayout(null);
				
		JPanel botones = new JPanel();
		botones.setBounds(0, 0, 715, 37);
		pnAction.add(botones);
		botones.setBackground(Color.DARK_GRAY);
		botones.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "opciones", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 255, 255)));
						
		btnModificar = new JButton("Editar");
		btnModificar.setBounds(228, 17, 100, 16);
		btnModificar.setIcon(new ImageIcon("img/gestion/Modificara.png"));
						
		btnModificar.setToolTipText("Modificar");
										
		btnModificar.setForeground(Color.WHITE);
		btnModificar.setBackground(Color.DARK_GRAY);
		btnModificar.addActionListener(contRutas.modificar());
										

		btnNuevo = new JButton("Nuevo");
		btnNuevo.setBounds(124, 17, 100, 16);
		btnNuevo.setIcon(new ImageIcon("img/gestion/add.png"));
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setBackground(Color.DARK_GRAY);
		btnNuevo.addActionListener(contRutas.nuevo());
		botones.setLayout(null);
										
		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(contRutas.Atras());
		btnAtras.setIcon(new ImageIcon("img/gestion/AtrasCurva.png"));
		btnAtras.setBounds(12, 17, 92, 16);
		botones.add(btnAtras);
		btnAtras.setForeground(Color.WHITE);
		btnAtras.setBackground(Color.DARK_GRAY);
		botones.add(btnNuevo);
		botones.add(btnModificar);
										
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(331, 17, 110, 16);
		btnEliminar.setIcon(new ImageIcon("img/gestion/cancel.png"));
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(Color.DARK_GRAY);
		btnEliminar.addActionListener(contRutas.eliminar());
		botones.add(btnEliminar);
		Zona = new JButton("Zona");
		Zona.setIcon(new ImageIcon("img/gestion/Presentacion.png"));
		Zona.setBounds(460, 17, 150, 16);
		Zona.setForeground(Color.WHITE);
		Zona.setBackground(Color.DARK_GRAY);
		Zona.addActionListener(contRutas.AbrirZona());
		botones.add(Zona);
									
		button = new JButton("Salir");
		button.addActionListener(contRutas.salir());
		button.setIcon(new ImageIcon("img/gestion/SalirCurva.png"));
		button.setBounds(622, 17, 86, 16);
		button.setForeground(Color.WHITE);
		button.setBackground(Color.DARK_GRAY);
		botones.add(button);
	
		panelRuta = new JPanel();
		panelRuta.setBounds(12, 286, 717, 40);
		pnRutas.add(panelRuta);
		panelRuta.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229), 1, true), "Editar Ruta", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		panelRuta.setBackground(Color.DARK_GRAY);
		panelRuta.getSize(new Dimension(800, 150));
		panelRuta.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(152, 11, 69, 25);
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		panelRuta.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(223, 15, 121, 16);
		panelRuta.add(txtNombre);
		txtNombre.setColumns(10);
		
		
		cmbZona = new JComboBox<Zona>();
		cmbZona.setBounds(413, 15, 150,16);
		cmbZona.setBackground(SystemColor.controlHighlight);
		cmbZona.setForeground(Color.BLACK);
		cmbZona.setRenderer(new ListCellRenderer() {
			
		
			@Override
			public Component getListCellRendererComponent(JList list, Object value,
					int index, boolean isSelected, boolean cellHasFocus) {
				// TODO Auto-generated method stub
				Zona zona = (Zona) value;
				return new JLabel(zona.getDescripcion());
			}
		});
		panelRuta.add(cmbZona);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(575, 15, 121, 16);
		panelRuta.add(btnGuardar);
		btnGuardar.setIcon(new ImageIcon("img/gestion/bien.png"));
		btnGuardar.setBackground(Color.DARK_GRAY);
		btnGuardar.setForeground(Color.WHITE);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(5, 15, 116, 16);
		panelRuta.add(btnCancelar);
		btnCancelar.setIcon(new ImageIcon("img/gestion/cancel.png"));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				quitarNuevo();
				scrollPanel.setVisible(true);
			}
		});
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(Color.DARK_GRAY);
		
		txtId = new JTextField();
		txtId.setBounds(460, 12, 44, 19);
		panelRuta.add(txtId);
		txtId.setColumns(10);
		
		JLabel lblZona = new JLabel("Zona:");
		lblZona.setForeground(Color.WHITE);
		lblZona.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblZona.setBounds(359, 11, 69, 25);
		panelRuta.add(lblZona);
		txtId.setVisible(false);
		btnGuardar.addActionListener(contRutas.guardar());
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.GRAY);
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(12, 12, 723, 35);
		pnRutas.add(panel);
		panel.setLayout(new MigLayout("", "[grow]", "[]"));
		
		txtABuscar = new JTextField();
		txtABuscar.setForeground(Color.WHITE);
		txtABuscar.setColumns(10);
		txtABuscar.setBackground(Color.DARK_GRAY);
		panel.add(txtABuscar, "flowx,cell 0 0,growx");
		
		JButton btnBuscar = new JButton("");
		btnBuscar.addActionListener(contRutas.buscar());
		btnBuscar.setVerticalAlignment(SwingConstants.TOP);
		btnBuscar.setIcon(new ImageIcon("img/gestion/buscar.png"));
		btnBuscar.setBackground(Color.DARK_GRAY);
		panel.add(btnBuscar, "cell 0 0");
		 
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void activarBinding(List<Ruta> rutas) {
		// TODO Auto-generated method stub
		tabla = new JTable();
		scrollPanel.setViewportView(tabla);
		binRutas = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,
    			rutas,tabla);
	    BeanProperty nombreRuta = BeanProperty.create("nombre");
	    BeanProperty idRuta  = BeanProperty.create("id");
	    BeanProperty zonaDescripcion  = BeanProperty.create("zona.descripcion");
	    

	    binRutas.addColumnBinding(idRuta).setColumnClass(Integer.class).setColumnName("id");;
	    binRutas.addColumnBinding(nombreRuta).setColumnClass(String.class).setColumnName("Nombre");
	    binRutas.addColumnBinding(zonaDescripcion).setColumnClass(String.class).setColumnName("Zona");
	    
	    binRutas.bind();

	    JComboBoxBinding jcomboZona = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ,zonas,cmbZona);
	    jcomboZona.bind();

	}

	
	public JTable getTabla() {
		return tabla;
	}

	public void setTabla(JTable table) {
		this.tabla = table;
	}

	public JTextField getTxtBuscar() {
		return txtBuscar;
	}

	public void setTxtBuscar(JTextField txtBuscar) {
		this.txtBuscar = txtBuscar;
	}


	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public List<Ruta> getProductos() {
		return rutas;
	}

	public void setProductos(List<Ruta> productos) {
		this.rutas = productos;
	}

	@SuppressWarnings("rawtypes")
	public JTableBinding getBinRutas() {
		return binRutas;
	}

	@SuppressWarnings("rawtypes")
	public void setBinProductos(JTableBinding binProductos) {
		this.binRutas = binProductos;
	}
	
		public JTextField getTxtId() {
		return txtId;
	}

	public void setTxtId(JTextField txtId) {
		this.txtId = txtId;
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


	public void activarNuevoRuta() {
		// TODO Auto-generated method stub
		panelRuta.setVisible(true);
		scrollPanel.setVisible(false);
		panelRuta.setBounds(12, 96, 717, 40);
		txtNombre.setText("");
	}

	public void quitarNuevo() {
		// TODO Auto-generated method stub
		panelRuta.setVisible(false);
		scrollPanel.setVisible(true);
	}

	public JScrollPane getScrollPanel() {
		return scrollPanel;
	}

	public void setScrollPanel(JScrollPane scrollPanel) {
		this.scrollPanel = scrollPanel;
	}

	public JTextField getTxtABuscar() {
		return txtABuscar;
	}

	public void setTxtABuscar(String txtABuscar) {
		this.txtABuscar.setText(txtABuscar);
	}

	public List<Ruta> getRutas() {
		return rutas;
	}

	public void setRutas(List<Ruta> rutas) {
		this.rutas = rutas;
	}

	public List<Zona> getZonas() {
		return zonas;
	}

	public void setZonas(List<Zona> zonas) {
		this.zonas = zonas;
	}

	public JComboBox<Zona> getCmbZona() {
		return cmbZona;
	}

	public void setCmbZona(JComboBox<Zona> cmbZona) {
		this.cmbZona = cmbZona;
	}

	public JPanel getPanelRuta() {
		return panelRuta;
	}

	public void setPanelRuta(JPanel panelRuta) {
		this.panelRuta = panelRuta;
	}

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}

	public JButton getZona() {
		return Zona;
	}

	public void setZona(JButton zona) {
		Zona = zona;
	}

	public JButton getBtnAtras() {
		return btnAtras;
	}

	public void setBtnAtras(JButton btnAtras) {
		this.btnAtras = btnAtras;
	}

	public void setTxtABuscar(JTextField txtABuscar) {
		this.txtABuscar = txtABuscar;
	}
}


