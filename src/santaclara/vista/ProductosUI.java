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
import javax.swing.BoxLayout;
import javax.swing.JSeparator;

public class ProductosUI extends JPanel {

	private JTable table;
	private List<Producto> productos = new ArrayList<Producto>();
	private JTableBinding  binProductos;

	private JTextField txtBuscar;

	private List<Presentacion> presentaciones = new ArrayList<Presentacion>();
	private List<Sabor> sabores = new ArrayList<Sabor>();
	private List<Capacidad> capacidades = new ArrayList<Capacidad>();
	
	private JComboBox cmbSabor;
    private JComboBox cmbPresentacion;
    private JComboBox cmbCapacidad;
    private JButton btnGuardar;

    private JTextField 			txtNombre;
	private JSpinner			 txtPrecio; 
	private JPanel 	  			 panelProducto;
	private JButton    			btnNuevo;
	
	public ProductosUI(ContProductos contProductos, List<Producto> productos,List<Capacidad> capacidades, 
		List<Sabor> sabores,List<Presentacion> presentaciones) {
		
		this.productos = productos;
		this.presentaciones = presentaciones;
		this.capacidades = capacidades;
		this.sabores = sabores;
		
		setFont(new Font("Dialog", Font.BOLD, 13));
		setForeground(Color.WHITE);
		setBackground(Color.GRAY);
		setSize(800,600);
		setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		
		JPanel pnProductos = new JPanel();
		pnProductos.setSize(800,300);
		pnProductos.setBackground(Color.GRAY);
		pnProductos.setBorder(new TitledBorder(null, "Listado de Productos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(pnProductos);

		JPanel botones = new JPanel();
		botones.setBackground(Color.GRAY);
		botones.setLocation(708, 39);
		botones.setBorder(new TitledBorder(null, "opciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		botones.setSize(131,223);
		botones.setLayout(null);
		

		btnNuevo = new JButton("Nuevo");
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setBackground(Color.DARK_GRAY);
		btnNuevo.setBounds(22,50, 100, 25);
		btnNuevo.addActionListener(contProductos.nuevo());
		botones.add(btnNuevo);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(22,80,100, 25);
		btnModificar.setForeground(Color.WHITE);
		btnModificar.setBackground(Color.DARK_GRAY);
		botones.add(btnModificar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(22,105, 100, 25);
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(Color.DARK_GRAY);
		botones.add(btnEliminar);

		
		JScrollPane scrollPanel = new JScrollPane();
		scrollPanel.setBounds(23, 38, 673, 224);
		table = new JTable();
		table.setBackground(Color.WHITE);
		scrollPanel.setViewportView(table);
		pnProductos.setLayout(null);
		pnProductos.add(botones);
		pnProductos.add(scrollPanel);
		
		panelProducto = new JPanel();
		panelProducto.setSize(800,300);
		panelProducto.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229), 1, true), "Editar Producto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelProducto.setBackground(Color.GRAY);
		panelProducto.getSize(new Dimension(800, 150));
		panelProducto.setLayout(null);
		panelProducto.setVisible(false);
		
		JLabel lblNombre = new JLabel("Nombre :");
		lblNombre.setBounds(5, 15, 150, 25);
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		panelProducto.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(155, 15, 150, 25);
		panelProducto.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblPresentacion = new JLabel("Presentacion ");
		lblPresentacion.setForeground(Color.WHITE);
		lblPresentacion.setBounds(349, 15, 150, 25);
		panelProducto.add(lblPresentacion);
		
		
		cmbPresentacion = new JComboBox();
		cmbPresentacion.setBounds(523, 15, 150,25);
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
		 
		JLabel lblCapacidad = new JLabel("Capacidad:");
		lblCapacidad.setBounds(5, 65, 150,25);
		lblCapacidad.setForeground(Color.WHITE);
		panelProducto.add(lblCapacidad);

		cmbCapacidad = new JComboBox();
		cmbCapacidad.setBounds(155, 65, 150,25);
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

		JLabel lblSabor = new JLabel("Sabor:");
		lblSabor.setBounds(349, 65, 150,25);
		lblSabor.setForeground(Color.WHITE);
		lblSabor.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		panelProducto.add(lblSabor);
				
		cmbSabor = new JComboBox();
		cmbSabor.setBounds(523, 65, 150,25);
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
				
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(5, 115,  150,25);
		lblPrecio.setBackground(SystemColor.controlHighlight);
		lblPrecio.setForeground(Color.WHITE);
		panelProducto.add(lblPrecio);
				
		txtPrecio = new JSpinner(new SpinnerNumberModel(0.0,0.00,Double.MAX_VALUE,0.1));
		txtPrecio.setBounds(155, 115, 150,25);
		((JSpinner.NumberEditor)txtPrecio.getEditor()).getFormat().setMinimumFractionDigits(2);
		panelProducto.add(txtPrecio);
				
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(455, 160, 150,25);
		btnGuardar.setBackground(Color.DARK_GRAY);
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.addActionListener(contProductos.guardar());
		panelProducto.add(btnGuardar);
		
		add(panelProducto);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(5, 152, 844, 12);
		panelProducto.add(separator);
		  
		 
	}

	public void activarBinding() {
		// TODO Auto-generated method stub

		binProductos = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,
    			productos,table);
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

	public JComboBox getCmbSabor() {
		return cmbSabor;
	}

	public void setCmbSabor(JComboBox cmbSabor) {
		this.cmbSabor = cmbSabor;
	}

	public JComboBox getCmbPresentacion() {
		return cmbPresentacion;
	}

	public void setCmbPresentacion(JComboBox cmbPresentacion) {
		this.cmbPresentacion = cmbPresentacion;
	}

	public JComboBox getCmbCapacidad() {
		return cmbCapacidad;
	}

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

	public JTableBinding getBinProductos() {
		return binProductos;
	}

	public void setBinProductos(JTableBinding binProductos) {
		this.binProductos = binProductos;
	}

	public void activarNuevoProducto() {
		// TODO Auto-generated method stub
		panelProducto.setVisible(true);
		txtNombre.setText("");
		txtPrecio.setValue(0);
 
		
	}

	public void quitarNuevo() {
		// TODO Auto-generated method stub

		panelProducto.setVisible(false);
	}
}


