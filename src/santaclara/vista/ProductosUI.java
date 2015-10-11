package santaclara.vista;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.SystemColor;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.ListCellRenderer;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import javax.swing.JTable;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import santaclara.controlador.ContProductos;
import santaclara.modelo.Capacidad;
import santaclara.modelo.Presentacion;
import santaclara.modelo.Producto;
import santaclara.modelo.Sabor;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.JSeparator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;
import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class ProductosUI extends JPanel {

	private JPanel botones;
	private JPanel panelProducto;
	private JTable table;
	
	private JScrollPane scrollPanel; 
	
	private List<Producto> productos = new ArrayList<Producto>();
	@SuppressWarnings("rawtypes")
	private JTableBinding  binProductos;

	private JTextField txtBuscar;

	private List<Presentacion> presentaciones = new ArrayList<Presentacion>();
	private List<Sabor> sabores = new ArrayList<Sabor>();
	private List<Capacidad> capacidades = new ArrayList<Capacidad>();
	
	private JComboBox<Sabor> cmbSabor;
    private JComboBox<Presentacion> cmbPresentacion;
    private JComboBox<Capacidad> cmbCapacidad;

    private JTextField 			txtNombre;
    private JTextField  txtId;
    private JTextField txtABuscar;
    
	private JSpinner			 txtPrecio;
	private JSpinner txtDescuento;
	
	private JCheckBox chckbxExentoDelIva;
	
	private JButton btnGuardar;
	private JButton btnNuevo;
	private JButton	btnModificar;
	private JButton btnEliminar;
	private JButton btnSabor;
	private JButton btnSalir;
	private JButton btnPresentacion;
	private JButton btnCapacidades;
	
	private JLabel lblPresentacion;
	private JLabel lblCapacidad;
	private JLabel lblSabor;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ProductosUI(ContProductos contProductos, List<Producto> productos,List<Capacidad> capacidades, 
		List<Sabor> sabores,List<Presentacion> presentaciones) {
		
		this.productos = productos;
		this.presentaciones = presentaciones;
		this.capacidades = capacidades;
		this.sabores = sabores;
		
		setFont(new Font("Dialog", Font.BOLD, 13));
		setForeground(Color.WHITE);
		setBackground(Color.DARK_GRAY);
		setSize(1000,733);
		setLayout(null);
		
		JPanel pnProductos = new JPanel();
		pnProductos.setLocation(12, 12);
		pnProductos.setSize(1000,680);
		pnProductos.setBackground(Color.DARK_GRAY);
		pnProductos.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Modulo Productos", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		add(pnProductos);

		
		scrollPanel = new JScrollPane();
		scrollPanel.setBounds(12, 96, 953, 162);
		table = new JTable();
		table.setBackground(Color.GRAY);
		scrollPanel.setViewportView(table);
		pnProductos.setLayout(null);
				
				
		pnProductos.add(scrollPanel);
		
		JPanel pnAction = new JPanel();
		pnAction.setBackground(Color.GRAY);
		pnAction.setForeground(Color.DARK_GRAY);
		pnAction.setBounds(12, 47, 953, 48);
		pnProductos.add(pnAction);
		pnAction.setLayout(null);
				
		botones = new JPanel();
		botones.setBounds(0, 0, 952, 37);
		pnAction.add(botones);
		botones.setBackground(Color.DARK_GRAY);
		botones.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "opciones", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 255, 255)));
						
		btnModificar = new JButton("Editar");
		btnModificar.setBounds(228, 17, 100, 16);
		btnModificar.setIcon(new ImageIcon("img/gestion/Modificara.png"));
		btnModificar.setToolTipText("Modificar");
		btnModificar.setForeground(Color.WHITE);
		btnModificar.setBackground(Color.DARK_GRAY);
		btnModificar.addActionListener(contProductos.modificar());
										
		btnNuevo = new JButton("Nuevo");							
		btnNuevo.setBounds(124, 17, 100, 16);							
		btnNuevo.setIcon(new ImageIcon("img/gestion/add.png"));							
		btnNuevo.setForeground(Color.WHITE);						
		btnNuevo.setBackground(Color.DARK_GRAY);						
		btnNuevo.addActionListener(contProductos.nuevo());
		botones.setLayout(null);
										
										
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(contProductos.Atras());
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
	    btnEliminar.addActionListener(contProductos.eliminar());
		botones.add(btnEliminar);
										
		btnPresentacion = new JButton("Presentacion");
		btnPresentacion.setIcon(new ImageIcon("img/gestion/Presentacion.png"));
		btnPresentacion.setBounds(460, 17, 150, 16);
		btnPresentacion.setForeground(Color.WHITE);
		btnPresentacion.setBackground(Color.DARK_GRAY);
		btnPresentacion.addActionListener(contProductos.AbrirPresentaciones());
		botones.add(btnPresentacion);
										
		btnCapacidades = new JButton("Capacidad");
		btnCapacidades.addActionListener(contProductos.AbrirCapacidades());
		btnCapacidades.setIcon(new ImageIcon("img/gestion/Capacidad.png"));
		btnCapacidades.setBounds(615, 17, 130, 16);
		btnCapacidades.setForeground(Color.WHITE);
		btnCapacidades.setBackground(Color.DARK_GRAY);
		botones.add(btnCapacidades);
										
		btnSabor = new JButton("Sabor");
		btnSabor.setIcon(new ImageIcon("img/gestion/Sabor.png"));
		btnSabor.setBounds(750, 17, 100, 16);
		btnSabor.setForeground(Color.WHITE);
		btnSabor.setBackground(Color.DARK_GRAY);
		btnSabor.addActionListener(contProductos.AbrirSabor());
		botones.add(btnSabor);
										
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(contProductos.salir());
		btnSalir.setIcon(new ImageIcon("img/gestion/SalirCurva.png"));
		btnSalir.setBounds(854, 17, 86, 16);
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.DARK_GRAY);
		botones.add(btnSalir);
		
		panelProducto = new JPanel();
		panelProducto.setBounds(12, 296, 954, 150);
		pnProductos.add(panelProducto);
		panelProducto.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229), 1, true), "Editar Producto", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		panelProducto.setBackground(Color.DARK_GRAY);
		panelProducto.getSize(new Dimension(800, 150));
		panelProducto.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(12, 89, 69, 25);
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		panelProducto.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(140, 93, 150, 16);
		panelProducto.add(txtNombre);
		txtNombre.setColumns(10);
		
		
		cmbPresentacion = new JComboBox<Presentacion>();
		cmbPresentacion.setBounds(140, 61, 150,16);
		cmbPresentacion.setBackground(SystemColor.controlHighlight);
		cmbPresentacion.setForeground(Color.BLACK);
		cmbPresentacion.setRenderer(new ListCellRenderer() {
			
		
			@Override
			public Component getListCellRendererComponent(JList list, Object value,
					int index, boolean isSelected, boolean cellHasFocus) {
				// TODO Auto-generated method stub
				Presentacion presentacion = (Presentacion) value;
				return new JLabel(presentacion.getMaterial());
			}
		});
		panelProducto.add(cmbPresentacion);

		cmbCapacidad = new JComboBox<Capacidad>();
		cmbCapacidad.setBounds(410, 61, 130,16);
		cmbCapacidad.setBackground(SystemColor.controlHighlight);
		cmbCapacidad.setRenderer(new ListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			// TODO Auto-generated method stub
			Capacidad capacidad = (Capacidad) value;
			return new JLabel(capacidad.getVolumenStr());
			}
		});

		panelProducto.add(cmbCapacidad);
				
		cmbSabor = new JComboBox<Sabor>();
		cmbSabor.setBounds(670, 61, 130,16);
		cmbSabor.setBackground(SystemColor.controlHighlight);
		cmbSabor.setRenderer(new ListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
				// TODO Auto-generated method stub
				Sabor sabor = (Sabor) value;
				return new JLabel(sabor.getSabor());
			}
		});
		panelProducto.add(cmbSabor);
				
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(312, 89,  54,25);
		lblPrecio.setBackground(SystemColor.controlHighlight);
		lblPrecio.setForeground(Color.WHITE);
		panelProducto.add(lblPrecio);
				
		txtPrecio = new JSpinner(new SpinnerNumberModel(1.0,1.0,Double.MAX_VALUE,1.0));
		txtPrecio.setBounds(410, 92, 130,16);
		((JSpinner.NumberEditor)txtPrecio.getEditor()).getFormat().setMinimumFractionDigits(2);
		panelProducto.add(txtPrecio);
		
		txtDescuento = new JSpinner(new SpinnerNumberModel(0,0,100,1));
		txtDescuento.setBounds(670, 93, 130, 16);
		((JSpinner.NumberEditor)txtPrecio.getEditor()).getFormat().setMinimumFractionDigits(1);
		panelProducto.add(txtDescuento);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(5, 43, 943, 2);
		panelProducto.add(separator);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(175, 15, 130, 16);
		panelProducto.add(btnGuardar);
		btnGuardar.setIcon(new ImageIcon("img/gestion/bien.png"));
		btnGuardar.setBackground(Color.DARK_GRAY);
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.addActionListener(contProductos.guardar());
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(24, 15, 130, 16);
		panelProducto.add(btnCancelar);
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
		panelProducto.add(txtId);
		txtId.setColumns(10);
		
		lblPresentacion = new JLabel("Presentacion:");
		lblPresentacion.setForeground(Color.WHITE);
		lblPresentacion.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblPresentacion.setBounds(15, 58, 116, 25);
		panelProducto.add(lblPresentacion);
		
		lblCapacidad = new JLabel("Capacidad:");
		lblCapacidad.setForeground(Color.WHITE);
		lblCapacidad.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblCapacidad.setBounds(315, 58, 108, 25);
		panelProducto.add(lblCapacidad);
		
		lblSabor = new JLabel("Sabor:");
		lblSabor.setForeground(Color.WHITE);
		lblSabor.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblSabor.setBounds(574, 57, 69, 25);
		panelProducto.add(lblSabor);
		
		chckbxExentoDelIva = new JCheckBox("Exento del Iva");
		chckbxExentoDelIva.setForeground(Color.WHITE);
		chckbxExentoDelIva.setBackground(Color.DARK_GRAY);
		chckbxExentoDelIva.setBounds(806, 90, 129, 23);
		panelProducto.add(chckbxExentoDelIva);
		
		JLabel lblDescuento = new JLabel("% Descuento:");
		lblDescuento.setForeground(Color.WHITE);
		lblDescuento.setBackground(SystemColor.controlHighlight);
		lblDescuento.setBounds(570, 89, 97, 25);
		panelProducto.add(lblDescuento);
		
		txtId.setVisible(false);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.GRAY);
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(12, 12, 954, 35);
		pnProductos.add(panel);
		panel.setLayout(new MigLayout("", "[grow]", "[]"));
		
		txtABuscar = new JTextField();
		txtABuscar.setForeground(Color.WHITE);
		txtABuscar.setColumns(10);
		txtABuscar.setBackground(Color.DARK_GRAY);
		panel.add(txtABuscar, "flowx,cell 0 0,growx");
		
		JButton btnBuscar = new JButton("");
		btnBuscar.addActionListener(contProductos.buscar());
		btnBuscar.setVerticalAlignment(SwingConstants.TOP);
		btnBuscar.setIcon(new ImageIcon("img/gestion/buscar.png"));
		btnBuscar.setBackground(Color.DARK_GRAY);
		panel.add(btnBuscar, "cell 0 0");
		 
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void activarBinding(List<Producto> lsProductos) {
		// TODO Auto-generated method stub
		table = new JTable();
		scrollPanel.setViewportView(table);
		scrollPanel.setBounds(12, 96, 953, 670);
		binProductos = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,
    			lsProductos,table);
	    BeanProperty nombreProducto = BeanProperty.create("nombre");
	    BeanProperty idProducto  = BeanProperty.create("id");
	    BeanProperty capacidadProducto  = BeanProperty.create("capacidad.volumenStr");
	    BeanProperty presentacionProducto = BeanProperty.create("presentacion.material");
	    BeanProperty saborProducto = BeanProperty.create("sabor.sabor");
	    BeanProperty precioProducto = BeanProperty.create("precioStr");

	    binProductos.addColumnBinding(idProducto).setColumnClass(Integer.class).setColumnName("id");;
	    binProductos.addColumnBinding(nombreProducto).setColumnClass(String.class).setColumnName("Nombre");
	    binProductos.addColumnBinding(capacidadProducto).setColumnClass(String.class).setColumnName("Capacidad");
	    binProductos.addColumnBinding(presentacionProducto).setColumnClass(String.class).setColumnName("Presentacion");;
	    binProductos.addColumnBinding(saborProducto).setColumnClass(String.class).setColumnName("Sabor");;
	    binProductos.addColumnBinding(precioProducto).setColumnClass(String.class).setColumnName("Precio");;

	    binProductos.bind();

	    JComboBoxBinding jcomboSabor = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ,sabores,cmbSabor);
	    JComboBoxBinding jcomboCapacidad = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ,capacidades,cmbCapacidad);
	    JComboBoxBinding jcomboPresentacion = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ,presentaciones,cmbPresentacion);

	    jcomboSabor.bind();
	    jcomboCapacidad.bind();
	    jcomboPresentacion.bind();

	}

	
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JTextField getTxtBuscar() {
		return txtBuscar;
	}

	public void setTxtBuscar(JTextField txtBuscar) {
		this.txtBuscar = txtBuscar;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getCmbSabor() {
		return cmbSabor;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setCmbSabor(JComboBox cmbSabor) {
		this.cmbSabor = cmbSabor;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getCmbPresentacion() {
		return cmbPresentacion;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setCmbPresentacion(JComboBox cmbPresentacion) {
		this.cmbPresentacion = cmbPresentacion;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getCmbCapacidad() {
		return cmbCapacidad;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setCmbCapacidad(JComboBox cmbCapacidad) {
		this.cmbCapacidad = cmbCapacidad;
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

	public JSpinner getTxtPrecio() {
		return txtPrecio;
	}

	public void setTxtPrecio(JSpinner txtPrecio) {
		this.txtPrecio = txtPrecio;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	@SuppressWarnings("rawtypes")
	public JTableBinding getBinProductos() {
		return binProductos;
	}

	@SuppressWarnings("rawtypes")
	public void setBinProductos(JTableBinding binProductos) {
		this.binProductos = binProductos;
	}
	
	

	public List<Presentacion> getPresentaciones() {
		return presentaciones;
	}

	public void setPresentaciones(List<Presentacion> presentaciones) {
		this.presentaciones = presentaciones;
	}

	public List<Sabor> getSabores() {
		return sabores;
	}

	public void setSabores(List<Sabor> sabores) {
		this.sabores = sabores;
	}

	public List<Capacidad> getCapacidades() {
		return capacidades;
	}

	public void setCapacidades(List<Capacidad> capacidades) {
		this.capacidades = capacidades;
	}

	public JTextField getTxtId() {
		return txtId;
	}

	public void setTxtId(JTextField txtId) {
		this.txtId = txtId;
	}

	public JPanel getPanelProducto() {
		return panelProducto;
	}

	public void setPanelProducto(JPanel panelProducto) {
		this.panelProducto = panelProducto;
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


	public void activarNuevoProducto() {
		// TODO Auto-generated method stub
		panelProducto.setVisible(true);
		panelProducto.setBounds(12, 96, 953, 150);
		txtId.setText("");
		txtNombre.setText("");
		txtPrecio.setValue(1.0);
	}

	public void quitarNuevo() {
		// TODO Auto-generated method stub
		panelProducto.setVisible(false);
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

	public JPanel getBotones() {
		return botones;
	}

	public void setBotones(JPanel botones) {
		this.botones = botones;
	}

	public JSpinner getTxtDescuento() {
		return txtDescuento;
	}

	public void setTxtDescuento(JSpinner txtDescuento) {
		this.txtDescuento = txtDescuento;
	}

	public JCheckBox getChckbxExentoDelIva() {
		return chckbxExentoDelIva;
	}

	public void setChckbxExentoDelIva(JCheckBox chckbxExentoDelIva) {
		this.chckbxExentoDelIva = chckbxExentoDelIva;
	}

	public JButton getBtnSabor() {
		return btnSabor;
	}

	public void setBtnSabor(JButton btnSabor) {
		this.btnSabor = btnSabor;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public void setBtnSalir(JButton btnSalir) {
		this.btnSalir = btnSalir;
	}

	public JButton getBtnPresentacion() {
		return btnPresentacion;
	}

	public void setBtnPresentacion(JButton btnPresentacion) {
		this.btnPresentacion = btnPresentacion;
	}

	public JButton getBtnCapacidades() {
		return btnCapacidades;
	}

	public void setBtnCapacidades(JButton btnCapacidades) {
		this.btnCapacidades = btnCapacidades;
	}

	public JLabel getLblPresentacion() {
		return lblPresentacion;
	}

	public void setLblPresentacion(JLabel lblPresentacion) {
		this.lblPresentacion = lblPresentacion;
	}

	public JLabel getLblCapacidad() {
		return lblCapacidad;
	}

	public void setLblCapacidad(JLabel lblCapacidad) {
		this.lblCapacidad = lblCapacidad;
	}

	public JLabel getLblSabor() {
		return lblSabor;
	}

	public void setLblSabor(JLabel lblSabor) {
		this.lblSabor = lblSabor;
	}

	public void setTxtABuscar(JTextField txtABuscar) {
		this.txtABuscar = txtABuscar;
	}
	
	
}


