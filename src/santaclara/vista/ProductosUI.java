package santaclara.vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.SystemColor;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SpringLayout;
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
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.AbstractListModel;
import javax.swing.JScrollBar;

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

    private JTextField txtNombre;
	private JTextField txtPrecio;

	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * @param contProductos 
	 * @param list 
	empresas = serEvento.listaEmpresas();
		JComboBoxBinding<Empresa, List<Empresa>, JComboBox> b =  SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ,empresas,jComboBox_empresa);
		b.bind();
		
	 *
	 */
	
	
	public ProductosUI(ContProductos contProductos, List<Producto> productos,List<Capacidad> capacidades, 
		List<Sabor> sabores,List<Presentacion> presentaciones) {
		
		this.productos = productos;
		this.presentaciones = presentaciones;
		this.capacidades = capacidades;
		this.sabores = sabores;
		
		setFont(new Font("Dialog", Font.BOLD, 13));
		setForeground(Color.WHITE);
		setBackground(Color.GRAY);
		setBounds(100, 100, 725, 510);
		setLayout(null);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(24, 208, 82, 25);
		btnBuscar.setForeground(Color.WHITE);
		btnBuscar.setBackground(Color.DARK_GRAY);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(btnBuscar);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(118, 208, 92, 25);
		btnGuardar.setBackground(Color.DARK_GRAY);
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.addActionListener(contProductos.guardar());
		add(btnGuardar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(222, 208, 99, 25);
		btnModificar.setForeground(Color.WHITE);
		btnModificar.setBackground(Color.DARK_GRAY);
		add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(346, 208, 90, 25);
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(Color.DARK_GRAY);
		add(btnEliminar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(460, 208, 66, 25);
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.DARK_GRAY);
		add(btnSalir);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(24, 245, 676, 237);
		add(panel);
		panel.setLayout(null);

		JScrollPane scrollBar = new JScrollPane();
		scrollBar.setBounds(1, 1, 520, 225);
		table = new JTable();
		table.setBounds(1, 1, 500, 220);

		scrollBar.setViewportView(table);
		panel.add(scrollBar,BorderLayout.CENTER);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(543, 211, 157, 19);
		add(txtBuscar);
		txtBuscar.setColumns(10);
		
		JPanel panelProducto = new JPanel();
		panelProducto.setBackground(Color.GRAY);
		panelProducto.setBounds(24, 23, 676, 173);
		panelProducto.setLayout(new GridLayout(6, 2, 5, 5));
		add(panelProducto);
				
		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setForeground(Color.WHITE);
		lblCodigo.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		panelProducto.add(lblCodigo);
		
		txtNombre = new JTextField();
		panelProducto.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblPresentacion = new JLabel("Presentacion:");
		lblPresentacion.setForeground(Color.WHITE);
		lblPresentacion.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		panelProducto.add(lblPresentacion);
		
		cmbPresentacion = new JComboBox();
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
		lblCapacidad.setForeground(Color.WHITE);
		lblCapacidad.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		panelProducto.add(lblCapacidad);
		cmbCapacidad = new JComboBox();
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
		lblSabor.setForeground(Color.WHITE);
		lblSabor.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		panelProducto.add(lblSabor);
		
		cmbSabor = new JComboBox();
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
		lblPrecio.setBackground(SystemColor.controlHighlight);
		lblPrecio.setForeground(Color.WHITE);
		panelProducto.add(lblPrecio);
		
		txtPrecio = new JTextField();
		panelProducto.add(txtPrecio);
		txtPrecio.setColumns(10);

		txtBuscar.addActionListener(contProductos.buscar());
		
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

	    binProductos.addColumnBinding(nombreProducto).setColumnClass(String.class).setColumnName("Capacidad");
	    binProductos.addColumnBinding(idProducto).setColumnClass(Integer.class).setColumnName("Capacidad");;
	    binProductos.addColumnBinding(capacidadProducto).setColumnClass(String.class).setColumnName("Capacidad");
	    binProductos.addColumnBinding(presentacionProducto).setColumnClass(String.class).setColumnName("Capacidad");;
	    binProductos.addColumnBinding(saborProducto).setColumnClass(String.class).setColumnName("Capacidad");;
	    binProductos.addColumnBinding(precioProducto).setColumnClass(String.class).setColumnName("Capacidad");;

	    binProductos.bind();
	    table.getTableHeader().add(new JLabel("dddd"));

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

	public JTextField getTxtPrecio() {
		return txtPrecio;
	}

	public void setTxtPrecio(JTextField txtPrecio) {
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
}


