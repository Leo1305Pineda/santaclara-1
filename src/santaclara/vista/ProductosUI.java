package santaclara.vista;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Component;
import java.awt.SystemColor;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
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
import santaclara.vista.herramientas.VistaGenericaUI;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class ProductosUI extends VistaGenericaUI {

	private List<Producto> 		productos = new ArrayList<Producto>();
	private List<Presentacion> 	presentaciones = new ArrayList<Presentacion>();
	private List<Sabor> 		sabores = new ArrayList<Sabor>();
	private List<Capacidad> 	capacidades = new ArrayList<Capacidad>();
	
	private JComboBox<Sabor> 		cmbSabor;
    private JComboBox<Presentacion> cmbPresentacion;
    private JComboBox<Capacidad> 	cmbCapacidad;

    private JTextField 				txtABuscar;
    private JTextField 				txtNombre;
    private JTextField 	 			txtId;
	private JTextField 				txtBuscar;
	
    private JSpinner				txtPrecio;
    
    private Checkbox checkIva; 

	private JPanel 	  		pnlProducto;
	@SuppressWarnings("rawtypes")

	private JTableBinding   binProductos;
	
	private JButton btnNuevo;
	private JButton	btnModificar;
	private JButton btnEliminar;
	private JButton btnSabor;
	private JButton btnSalir;
	private JButton btnPresentacion;
	private JButton btnCapacidades;
    private JButton btnGuardar;
	
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public ProductosUI(ContProductos contProductos, List<Producto> productos,List<Capacidad> capacidades, List<Sabor> sabores,List<Presentacion> presentaciones) {
		super();
		//getPnTabla().setBounds(0, 55, 1216, 681);
		//getPnOpciones().setBounds(0, 0, 1216, 55);

		
		this.productos = productos;
		this.presentaciones = presentaciones;
		this.capacidades = capacidades;
		this.sabores = sabores;

		dibujarPanelOpciones();
		dibujarPanelTabla();

		btnNuevo = new JButton("Nuevo");							
		btnNuevo.setIcon(new ImageIcon("img/gestion/add.png"));							
		btnNuevo.setForeground(Color.WHITE);						
		btnNuevo.setBackground(Color.DARK_GRAY);						
		btnNuevo.addActionListener(contProductos.nuevo());
		btnNuevo.setFont(new Font("Dialog", Font.BOLD, 10));		
		getPnBotones().add(btnNuevo);							

		
		btnModificar = new JButton("Editar");
		btnModificar.setIcon(new ImageIcon("img/gestion/Modificara.png"));
		btnModificar.setToolTipText("Modificar");
		btnModificar.setForeground(Color.WHITE);
		btnModificar.setBackground(Color.DARK_GRAY);
		btnModificar.addActionListener(contProductos.modificar());
		btnModificar.setFont(new Font("Dialog", Font.BOLD, 10));		
		getPnBotones().add(btnModificar);							
 
										
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(contProductos.Atras());
		btnAtras.setIcon(new ImageIcon("img/gestion/AtrasCurva.png"));
		btnAtras.setForeground(Color.WHITE);
		btnAtras.setBackground(Color.DARK_GRAY);
		btnAtras.setFont(new Font("Dialog", Font.BOLD, 10));	
		getPnBotones().add(btnAtras);							

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon("img/gestion/cancel.png"));
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(Color.DARK_GRAY);
		btnEliminar.addActionListener(contProductos.eliminar());
		btnEliminar.setFont(new Font("Dialog", Font.BOLD, 10));	
	    getPnBotones().add(btnEliminar);							
	
	    
		btnPresentacion = new JButton("Presentacion");
		btnPresentacion.setIcon(new ImageIcon("img/gestion/Presentacion.png"));
		btnPresentacion.setForeground(Color.WHITE);
		btnPresentacion.setBackground(Color.DARK_GRAY);
		btnPresentacion.addActionListener(contProductos.AbrirPresentaciones());
		btnPresentacion.setFont(new Font("Dialog", Font.BOLD, 10));	
		getPnBotones().add(btnPresentacion);							

		
		btnCapacidades = new JButton("Capacidad");
		btnCapacidades.addActionListener(contProductos.AbrirCapacidades());
		btnCapacidades.setIcon(new ImageIcon("img/gestion/Capacidad.png"));
		btnCapacidades.setForeground(Color.WHITE);
		btnCapacidades.setBackground(Color.DARK_GRAY);
		btnCapacidades.setFont(new Font("Dialog", Font.BOLD, 10));	
		getPnBotones().add(btnCapacidades);							

		
		btnSabor = new JButton("Sabor");
		btnSabor.setIcon(new ImageIcon("img/gestion/Sabor.png"));
		btnSabor.setForeground(Color.WHITE);
		btnSabor.setBackground(Color.DARK_GRAY);
		btnSabor.addActionListener(contProductos.AbrirSabor());
		btnSabor.setFont(new Font("Dialog", Font.BOLD, 10));	
		getPnBotones().add(btnSabor);							

		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(contProductos.salir());
		btnSalir.setIcon(new ImageIcon("img/gestion/SalirCurva.png"));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.DARK_GRAY);
		btnSalir.setFont(new Font("Dialog", Font.BOLD, 10));	
		getPnBotones().add(btnSalir);							

		
		/**********************************************************************************************************************************************************/
		pnlProducto = new JPanel();
		pnlProducto.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229), 1, true), "Editar Producto", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnlProducto.setBackground(Color.DARK_GRAY);
		pnlProducto.setLayout(new MigLayout());
		
		JLabel lblNombre = new JLabel("Nombre:");
		//lblNombre.setBounds(92, 35, 69, 16);
		lblNombre.setForeground(Color.WHITE);
		pnlProducto.add(lblNombre,"align right");

		txtNombre = new JTextField();
		//txtNombre.setBounds(158, 35, 121, 16);
		pnlProducto.add(txtNombre);
		txtNombre.setColumns(20);
		
		JLabel lblPresentacion = new JLabel("Presentacion:");
		//lblPresentacion.setBounds(92, 75, 110, 16);
		lblPresentacion.setForeground(Color.WHITE);
		lblPresentacion.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		pnlProducto.add(lblPresentacion);

		cmbPresentacion = new JComboBox<Presentacion>();
		//cmbPresentacion.setBounds(205, 75, 150,16);
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
		pnlProducto.add(cmbPresentacion,"wrap");

		JLabel lblCapactidad = new JLabel("Capacidad:");
		//lblCapactidad.setBounds(420, 75, 100, 16);
		lblCapactidad.setForeground(Color.WHITE);
		lblCapactidad.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		pnlProducto.add(lblCapactidad,"align right");

		cmbCapacidad = new JComboBox<Capacidad>();
		//cmbCapacidad.setBounds(510, 75, 130,16);
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
		pnlProducto.add(cmbCapacidad);
		
		JLabel lblSabor = new JLabel("Sabor:");
		//lblSabor.setBounds(92, 115, 69, 16);
		lblSabor.setForeground(Color.WHITE);
		lblSabor.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		pnlProducto.add(lblSabor);

		cmbSabor = new JComboBox<Sabor>();
		//cmbSabor.setBounds(162, 115, 100,16);
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
		pnlProducto.add(cmbSabor,"wrap");
				
		JLabel lblPrecio = new JLabel("Precio:");
		//lblPrecio.setBounds(360, 35,  54,16);
		lblPrecio.setBackground(SystemColor.controlHighlight);
		lblPrecio.setForeground(Color.WHITE);
		pnlProducto.add(lblPrecio,"align right");
		
		txtPrecio = new JSpinner(new SpinnerNumberModel(1.0,0.0,Double.MAX_VALUE,1.0));
		((JSpinner.NumberEditor)txtPrecio.getEditor()).getFormat().setMinimumFractionDigits(2);
		pnlProducto.add(txtPrecio,"wrap,growx,width 40:40:60");

		checkIva = new Checkbox("Iva Exento");
		checkIva.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		checkIva.setForeground(Color.WHITE);
		pnlProducto.add(checkIva,"wrap,growx,width 40:40:60");
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setIcon(new ImageIcon("img/gestion/bien.png"));
		btnGuardar.setBackground(Color.DARK_GRAY);
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.addActionListener(contProductos.guardar());
		pnlProducto.add(btnGuardar,"cell 1 3 2 3,split 2");
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon("img/gestion/cancel.png"));
		btnCancelar.addActionListener(contProductos.quitarNuevo());
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(Color.DARK_GRAY);
		pnlProducto.add(btnCancelar);

		txtId = new JTextField();
		txtId.setColumns(10);
		txtId.setVisible(false);
		pnlProducto.add(txtId);
		
		 
		txtABuscar = new JTextField();
		txtABuscar.setForeground(Color.WHITE);
		txtABuscar.setColumns(10);
		txtABuscar.setBackground(Color.DARK_GRAY);
		getPanelBuscar().add(txtABuscar);
		
		JButton btnBuscar = new JButton("");
		btnBuscar.addActionListener(contProductos.buscar());
		btnBuscar.setVerticalAlignment(SwingConstants.TOP);
		btnBuscar.setIcon(new ImageIcon("img/gestion/buscar.png"));
		btnBuscar.setBackground(Color.DARK_GRAY);
		getPanelBuscar().add(btnBuscar);

		activarBinding(productos);
			
	}


    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void activarBinding(List<Producto> lsProductos) {
		// TODO Auto-generated method stub
		setTable(new JTable());
		getScrollPanel().setViewportView(getTable());
		binProductos = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,lsProductos,getTable());
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
		pnlProducto.setVisible(true);
		//txtId.setText("");
		txtNombre.setText("");
		txtPrecio.setValue(1.0);
		getPnTabla().setVisible(false);
		add(pnlProducto,BorderLayout.CENTER);
	}

	public void quitarNuevo() {
		// TODO Auto-generated method stub
		pnlProducto.setVisible(false);
		getPnTabla().setVisible(true);
		getScrollPanel().setVisible(true);

	}

	public JTextField getTxtABuscar() {
		return txtABuscar;
	}

	public void setTxtABuscar(String txtABuscar) {
		this.txtABuscar.setText(txtABuscar);
	}


	public Checkbox getCheckIva() {
		return checkIva;
	}


	public void setCheckIva(Checkbox checkIva) {
		this.checkIva = checkIva;
	}
	
	
}


