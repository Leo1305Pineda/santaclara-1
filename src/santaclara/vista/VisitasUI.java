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
import java.awt.GridLayout;
import javax.swing.SpinnerModel;
import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class VisitasUI extends JPanel {

	private JTable table;
	
	private JScrollPane scrollPanel; 
	
	private List<Producto> productos = new ArrayList<Producto>();
	@SuppressWarnings("rawtypes")
	private JTableBinding  binProductos;

	private JTextField txtBuscar;

	private List<Presentacion> presentaciones = new ArrayList<Presentacion>();
	private List<Sabor> sabores = new ArrayList<Sabor>();
	private List<Capacidad> capacidades = new ArrayList<Capacidad>();
    private JComboBox<Presentacion> cmbCliente;
    private JButton btnGuardar;

    private JTextField 			txtNombre;
    private JTextField  txtId;
	private JSpinner			 txtValVendedor; 
	private JPanel 	  			 panelProducto;
	
	private JButton btnNuevo;
	private JButton	btnModificar;
	private JButton btnEliminar;
	
	private JTextField txtABuscar;
	private JPanel pnJefeVenta;
	private JComboBox<Presentacion> cmbJefeVenta;
	private JLabel lblValorproducto;
	private JTextField txtDescripcion;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public VisitasUI(ContProductos contProductos, List<Producto> productos,List<Capacidad> capacidades, 
		List<Sabor> sabores,List<Presentacion> presentaciones) {
		
		this.productos = productos;
		this.presentaciones = presentaciones;
		this.capacidades = capacidades;
		this.sabores = sabores;
		
		setFont(new Font("Dialog", Font.BOLD, 13));
		setForeground(Color.WHITE);
		setBackground(Color.DARK_GRAY);
		setSize(895,723);
		setLayout(null);
		
		JPanel pnProductos = new JPanel();
		pnProductos.setLocation(12, 12);
		pnProductos.setSize(875,680);
		pnProductos.setBackground(Color.DARK_GRAY);
		pnProductos.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Modulo Productos", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		add(pnProductos);

		
		scrollPanel = new JScrollPane();
		scrollPanel.setBounds(12, 96, 852, 379);
		table = new JTable();
		table.setBackground(Color.GRAY);
		scrollPanel.setViewportView(table);
		pnProductos.setLayout(null);
				
				
		pnProductos.add(scrollPanel);
		
		JPanel pnAction = new JPanel();
		pnAction.setBackground(Color.GRAY);
		pnAction.setForeground(Color.DARK_GRAY);
		pnAction.setBounds(12, 47, 852, 48);
		pnProductos.add(pnAction);
						pnAction.setLayout(null);
				
						JPanel botones = new JPanel();
						botones.setBounds(0, 0, 853, 37);
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
										
										JButton btnCliente = new JButton("Cliente");
										btnCliente.setIcon(new ImageIcon("img/gestion/Presentacion.png"));
										btnCliente.setBounds(460, 17, 150, 16);
										btnCliente.setForeground(Color.WHITE);
										btnCliente.setBackground(Color.DARK_GRAY);
										btnCliente.addActionListener(contProductos.AbrirPresentaciones());
										botones.add(btnCliente);
										
										JButton bntJefeVenta = new JButton("Capacidad");
										bntJefeVenta.addActionListener(contProductos.AbrirCapacidades());
										bntJefeVenta.setIcon(new ImageIcon("img/gestion/Capacidad.png"));
										bntJefeVenta.setBounds(615, 17, 130, 16);
										bntJefeVenta.setForeground(Color.WHITE);
										bntJefeVenta.setBackground(Color.DARK_GRAY);
										botones.add(bntJefeVenta);
										
										JButton button = new JButton("Salir");
										button.addActionListener(contProductos.salir());
										button.setIcon(new ImageIcon("img/gestion/SalirCurva.png"));
										button.setBounds(751, 17, 86, 16);
										button.setForeground(Color.WHITE);
										button.setBackground(Color.DARK_GRAY);
										botones.add(button);
		
		panelProducto = new JPanel();
		panelProducto.setBounds(12, 487, 852, 181);
		pnProductos.add(panelProducto);
		panelProducto.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229), 1, true), "Editar Visita", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		panelProducto.setBackground(Color.DARK_GRAY);
		panelProducto.getSize(new Dimension(800, 150));
		panelProducto.setLayout(null);
		
		JLabel lblMotivo = new JLabel("Motivo:");
		lblMotivo.setBounds(303, 67, 69, 25);
		lblMotivo.setForeground(Color.WHITE);
		lblMotivo.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		panelProducto.add(lblMotivo);

		txtNombre = new JTextField();
		txtNombre.setBounds(419, 71, 121, 16);
		panelProducto.add(txtNombre);
		txtNombre.setColumns(10);
				
		JLabel lblValVendedor = new JLabel("Valor Vendedor:");
		lblValVendedor.setBounds(31, 94,  116,25);
		lblValVendedor.setBackground(SystemColor.controlHighlight);
		lblValVendedor.setForeground(Color.WHITE);
		panelProducto.add(lblValVendedor);
				
		txtValVendedor = new JSpinner(new SpinnerNumberModel(0.0,0.00,Double.MAX_VALUE,0.1));
		txtValVendedor.setBounds(159, 98, 121,16);
		((JSpinner.NumberEditor)txtValVendedor.getEditor()).getFormat().setMinimumFractionDigits(2);
		panelProducto.add(txtValVendedor);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(5, 152, 844, 12);
		panelProducto.add(separator);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(713, 28, 127, 16);
		panelProducto.add(btnGuardar);
		btnGuardar.setIcon(new ImageIcon("img/gestion/bien.png"));
		btnGuardar.setBackground(Color.DARK_GRAY);
		btnGuardar.setForeground(Color.WHITE);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(17, 28, 130, 16);
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
		txtId.setBounds(15, 43, 44, 19);
		panelProducto.add(txtId);
		txtId.setColumns(10);
		
		JPanel pnCliente = new JPanel();
		pnCliente.setBackground(Color.DARK_GRAY);
		pnCliente.setBounds(159, 15, 260, 40);
		pnCliente.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229), 1, true), "idCliente  |Nombre   ", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		panelProducto.add(pnCliente);
		pnCliente.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		cmbCliente = new JComboBox<Presentacion>();
		pnCliente.add(cmbCliente);
		cmbCliente.setBackground(SystemColor.controlHighlight);
		cmbCliente.setForeground(Color.BLACK);
		cmbCliente.setRenderer(new ListCellRenderer() {
			
		
			@Override
			public Component getListCellRendererComponent(JList list, Object value,
					int index, boolean isSelected, boolean cellHasFocus) {
				// TODO Auto-generated method stub
				Presentacion presentacion = (Presentacion) value;
				return new JLabel(presentacion.getMaterial());
			}
		});
		
		pnJefeVenta = new JPanel();
		pnJefeVenta.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229), 1, true), "idJefeVenta  |Nombre   ", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnJefeVenta.setBackground(Color.DARK_GRAY);
		pnJefeVenta.setBounds(441, 15, 260, 40);
		panelProducto.add(pnJefeVenta);
		pnJefeVenta.setLayout(new GridLayout(0, 1, 0, 0));
		
		cmbJefeVenta = new JComboBox<Presentacion>();
		cmbJefeVenta.setForeground(Color.BLACK);
		cmbJefeVenta.setBackground(SystemColor.controlHighlight);
		pnJefeVenta.add(cmbJefeVenta);
		
		lblValorproducto = new JLabel("Valor Producto:");
		lblValorproducto.setForeground(Color.WHITE);
		lblValorproducto.setBackground(SystemColor.controlHighlight);
		lblValorproducto.setBounds(303, 94, 116, 25);
		panelProducto.add(lblValorproducto);
		
		JSpinner txtValProducto = new JSpinner((SpinnerModel) null);
		txtValProducto.setBounds(419, 104, 121, 16);
		panelProducto.add(txtValProducto);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setForeground(Color.WHITE);
		lblFecha.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblFecha.setBounds(31, 67, 69, 25);
		panelProducto.add(lblFecha);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setForeground(Color.WHITE);
		lblDescripcion.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblDescripcion.setBounds(558, 62, 100, 25);
		panelProducto.add(lblDescripcion);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(654, 71, 186, 16);
		panelProducto.add(txtDescripcion);
		
		JCheckBox chckbxEstado = new JCheckBox("Estado");
		chckbxEstado.setForeground(Color.WHITE);
		chckbxEstado.setBackground(Color.DARK_GRAY);
		chckbxEstado.setBounds(556, 101, 129, 23);
		panelProducto.add(chckbxEstado);
		txtId.setVisible(false);
		btnGuardar.addActionListener(contProductos.guardar());
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.GRAY);
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(12, 12, 852, 35);
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

	    JComboBoxBinding jcomboPresentacion = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ,presentaciones,cmbCliente);

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
	public JComboBox getCmbPresentacion() {
		return cmbCliente;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setCmbPresentacion(JComboBox cmbPresentacion) {
		this.cmbCliente = cmbPresentacion;
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
		return txtValVendedor;
	}

	public void setTxtPrecio(JSpinner txtPrecio) {
		this.txtValVendedor = txtPrecio;
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
		panelProducto.setBounds(12, 96, 953, 40);
		txtNombre.setText("");
		txtValVendedor.setValue(0);
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
}


