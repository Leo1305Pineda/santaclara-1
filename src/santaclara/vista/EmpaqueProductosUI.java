package santaclara.vista;

import java.util.ArrayList;
import java.util.List;
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
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import santaclara.controlador.ContEmpaqueProductos;
import santaclara.modelo.Producto;
import santaclara.modelo.EmpaqueProducto;
import net.miginfocom.swing.MigLayout;

import java.awt.GridLayout;

@SuppressWarnings("serial")
public class EmpaqueProductosUI extends JPanel {

	private JPanel 	  			panelProducto;
	private JPanel 				pnAction;
	private JPanel 				botones;
	private JPanel 				panel;
	private JPanel 				pnTabla;
	private JPanel  			pnEmpaqueProductos;
	
	private JTextField 			txtBuscar;
	private JTextField  		txtId;
	private JTextField 			txtABuscar;
	
	private JSpinner			txtCantidad;

    private JComboBox<Producto> cmbProducto;
    
	private JButton 			btnNuevo;
	private JButton				btnModificar;
	private JButton 			btnEliminar;
	private JButton 			btnGuardar;
	private JButton 			btnAtras;
	private JButton 			btnProducto;
	private JButton 			btnSalir;
	private JButton				btnCancelar;
	private JButton 			btnBuscar;
	
	private JTable 				table;
	
	private JScrollPane 		scrollPanel;
	
	@SuppressWarnings("rawtypes")
	private JTableBinding 		binEmpaqueProductos;
	
	private List<EmpaqueProducto> empaqueProductos = new ArrayList<EmpaqueProducto>();
	private List<Producto> 		productos = new ArrayList<Producto>();
	private JPanel pnEmpaque;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public EmpaqueProductosUI(ContEmpaqueProductos contEmpaqueProductos, List<EmpaqueProducto> empaqueProductos,List<Producto> productos) {
		
		this.empaqueProductos = empaqueProductos;
		this.productos = productos;
				
		setFont(new Font("Dialog", Font.BOLD, 13));
		setForeground(Color.WHITE);
		setBackground(Color.DARK_GRAY);
		setSize(1000,733);
		setLayout(null);
		
		pnEmpaqueProductos = new JPanel();
		pnEmpaqueProductos.setLocation(12, 12);
		pnEmpaqueProductos.setSize(740,628);
		pnEmpaqueProductos.setBackground(Color.DARK_GRAY);
		pnEmpaqueProductos.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Modulo Empaque por Producto", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		add(pnEmpaqueProductos);
		pnEmpaqueProductos.setLayout(null);
		
		pnAction = new JPanel();
		pnAction.setBackground(Color.GRAY);
		pnAction.setForeground(Color.DARK_GRAY);
		pnAction.setBounds(14, 47, 714, 48);
		pnEmpaqueProductos.add(pnAction);
		pnAction.setLayout(null);
		
		botones = new JPanel();
		botones.setBounds(1, 0, 712, 37);
		pnAction.add(botones);
		botones.setBackground(Color.DARK_GRAY);
		botones.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "opciones", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 255, 255)));
		
		btnModificar = new JButton("Editar");
		btnModificar.setBounds(228, 17, 100, 16);
		btnModificar.setIcon(new ImageIcon("img/gestion/Modificara.png"));
		btnModificar.setToolTipText("Modificar");					
		btnModificar.setForeground(Color.WHITE);
		btnModificar.setBackground(Color.DARK_GRAY);
		btnModificar.addActionListener(contEmpaqueProductos.modificar());
		botones.add(btnModificar);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.setBounds(124, 17, 100, 16);
		btnNuevo.setIcon(new ImageIcon("img/gestion/add.png"));
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setBackground(Color.DARK_GRAY);
		btnNuevo.addActionListener(contEmpaqueProductos.nuevo());
		botones.setLayout(null);
				
		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(contEmpaqueProductos.Atras());
		btnAtras.setIcon(new ImageIcon("img/gestion/AtrasCurva.png"));
		btnAtras.setBounds(12, 17, 92, 16);
		botones.add(btnAtras);
		btnAtras.setForeground(Color.WHITE);
		btnAtras.setBackground(Color.DARK_GRAY);		
		botones.add(btnNuevo);
				
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setToolTipText("Eliminar");
		btnEliminar.setBounds(331, 17, 110, 16);
		btnEliminar.setIcon(new ImageIcon("img/gestion/cancel.png"));
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(Color.DARK_GRAY);
		btnEliminar.addActionListener(contEmpaqueProductos.eliminar());
		botones.add(btnEliminar);
		
		btnSalir = new JButton("Salir");
		btnSalir.setToolTipText("Cerrar");
		btnSalir.addActionListener(contEmpaqueProductos.salir());
		btnSalir.setIcon(new ImageIcon("img/gestion/SalirCurva.png"));
		btnSalir.setBounds(615, 17, 86, 16);
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.DARK_GRAY);
		botones.add(btnSalir);
		
		panel = new JPanel();
		panel.setForeground(Color.GRAY);
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(14, 12, 715, 35);
		pnEmpaqueProductos.add(panel);
		panel.setLayout(new MigLayout("", "[grow]", "[]"));
		
		txtABuscar = new JTextField();
		txtABuscar.setForeground(Color.WHITE);
		txtABuscar.setColumns(10);
		txtABuscar.setBackground(Color.DARK_GRAY);
		panel.add(txtABuscar, "flowx,cell 0 0,growx");
		
		btnBuscar = new JButton("");
		btnBuscar.addActionListener(contEmpaqueProductos.buscar());
		btnBuscar.setVerticalAlignment(SwingConstants.TOP);
		btnBuscar.setIcon(new ImageIcon("img/gestion/buscar.png"));
		btnBuscar.setBackground(Color.DARK_GRAY);
		panel.add(btnBuscar, "cell 0 0");
		
		
		pnTabla = new JPanel();
		pnTabla.setBackground(Color.DARK_GRAY);
		pnTabla.setBounds(14, 91, 716, 392);
		pnEmpaqueProductos.add(pnTabla);
		pnTabla.setLayout(null);
		
		scrollPanel = new JScrollPane();
		scrollPanel.setBounds(0, 0, 716, 396);
		scrollPanel.setBackground(Color.DARK_GRAY);
		pnTabla.add(scrollPanel);
		
		table = new JTable();
		table.setBounds(0, 0, 1, 1);
		pnTabla.add(table);
		
		
		panelProducto = new JPanel();
		panelProducto.setBounds(14, 495, 714, 121);
		pnEmpaqueProductos.add(panelProducto);
		panelProducto.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229), 1, true), "Editar Empaque Producto", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		panelProducto.setBackground(Color.DARK_GRAY);
		panelProducto.getSize(new Dimension(800, 150));
		panelProducto.setLayout(null);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(12, 10,  75,25);
		lblCantidad.setBackground(SystemColor.controlHighlight);
		lblCantidad.setForeground(Color.WHITE);
		panelProducto.add(lblCantidad);
		
		txtCantidad = new JSpinner(new SpinnerNumberModel(1,1,Integer.MAX_VALUE,1));
		txtCantidad.setBounds(89, 14, 100,16);
		((JSpinner.NumberEditor)txtCantidad.getEditor()).getFormat().setMinimumFractionDigits(0);
		panelProducto.add(txtCantidad);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setToolTipText("Guardar");
		btnGuardar.setBounds(325, 15, 112, 16);
		btnGuardar.setIcon(new ImageIcon("img/gestion/bien.png"));
		btnGuardar.setBackground(Color.DARK_GRAY);
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.addActionListener(contEmpaqueProductos.guardar());
		panelProducto.add(btnGuardar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(201, 15, 120, 16);
		btnCancelar.setIcon(new ImageIcon("img/gestion/cancel.png"));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				quitarNuevo();
			}
		});
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(Color.DARK_GRAY);
		panelProducto.add(btnCancelar);
		
		txtId = new JTextField();
		txtId.setBounds(658, 13, 44, 19);
		panelProducto.add(txtId);
		txtId.setColumns(10);
		
		pnEmpaque = new JPanel();
		pnEmpaque.setBackground(Color.DARK_GRAY);
		pnEmpaque.setBounds(5, 36, 702, 56);
		pnEmpaque.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229), 1, true),"Nombre            |"
				+ "Presentacion  |"
				+ "Capacidad      |"
				+ "Sabor               |"
				+ "Precio               |", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		panelProducto.add(pnEmpaque);
		pnEmpaque.setLayout(null);
		
		cmbProducto = new JComboBox<Producto>();
		cmbProducto.setBounds(5, 20, 550, 24);
		pnEmpaque.add(cmbProducto);
		cmbProducto.setBackground(SystemColor.controlHighlight);
		cmbProducto.setForeground(Color.BLACK);
		cmbProducto.setRenderer(new ListCellRenderer() {
			
			@Override
			public Component getListCellRendererComponent(JList list, Object value,
					int index, boolean isSelected, boolean cellHasFocus) {
				// TODO Auto-generated method stub
				Producto producto = (Producto) value;
				
				JPanel pnProducto = new JPanel();
				pnProducto.add(new JTextField(producto.getNombre()));
				pnProducto.add(new JTextField(producto.getPresentacion().getMaterial()));
				pnProducto.add(new JTextField(producto.getCapacidad().getVolumenStr()));
				pnProducto.add(new JTextField(producto.getSabor().getSabor()));
				pnProducto.add(new JTextField(producto.getPrecioStr()));
				pnProducto.setLayout(new GridLayout(1, 0, 0, 0));
				
				return pnProducto;//new JPanel().add(new JTextField("dd"));
			}
		});
		
		btnProducto = new JButton("Producto");
		btnProducto.setBounds(565, 20, 127, 25);
		pnEmpaque.add(btnProducto);
		btnProducto.setToolTipText("Abrir");
		btnProducto.setIcon(new ImageIcon("img/gestion/Presentacion.png"));
		btnProducto.setForeground(Color.WHITE);
		btnProducto.setBackground(Color.DARK_GRAY);
		btnProducto.addActionListener(contEmpaqueProductos.AbrirProducto());
	
		txtId.setVisible(false);
		 
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void activarBinding(List<EmpaqueProducto> EmpaqueProductos) {
		// TODO Auto-generated method stub
		pnTabla.setVisible(true);
		table = new JTable();
		scrollPanel.setViewportView(table);
		
		binEmpaqueProductos = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,
    			EmpaqueProductos,table);
		BeanProperty idEmpaqueProducto  = BeanProperty.create("id");
	    
		BeanProperty nombreProducto = BeanProperty.create("producto.nombre");
	    BeanProperty presentacionProducto = BeanProperty.create("producto.presentacion.material");
	    BeanProperty capacidadProducto = BeanProperty.create("producto.capacidad.volumen");
	    BeanProperty saborProducto = BeanProperty.create("producto.sabor.sabor");
	    
	    BeanProperty cantidadProducto = BeanProperty.create("cantidadStr");

	    binEmpaqueProductos.addColumnBinding(idEmpaqueProducto).setColumnClass(Integer.class).setColumnName("id Empaque");;
	    
	    binEmpaqueProductos.addColumnBinding(nombreProducto).setColumnClass(String.class).setColumnName("Producto");
	    binEmpaqueProductos.addColumnBinding(presentacionProducto).setColumnClass(String.class).setColumnName("Presentacion");
	    binEmpaqueProductos.addColumnBinding(capacidadProducto).setColumnClass(String.class).setColumnName("Capacidad");
	    binEmpaqueProductos.addColumnBinding(saborProducto).setColumnClass(String.class).setColumnName("Sabor");
	    
	    binEmpaqueProductos.addColumnBinding(cantidadProducto).setColumnClass(String.class).setColumnName("Unidades por Empaque");

	    binEmpaqueProductos.bind();

	    JComboBoxBinding jcomboProductos = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ,productos,cmbProducto);
	    
	    jcomboProductos.bind();
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
	public JComboBox getCmbProducto() {
		return cmbProducto;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setCmbPresentacion(JComboBox cmbPresentacion) {
		this.cmbProducto = cmbPresentacion;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public JSpinner getTxtCantidad() {
		return txtCantidad;
	}

	public List<EmpaqueProducto> getEmpaqueProductos() {
		return empaqueProductos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	@SuppressWarnings("rawtypes")
	public JTableBinding getBinEmpaqueProductos() {
		return binEmpaqueProductos;
	}

	@SuppressWarnings("rawtypes")
	public void setBinProductos(JTableBinding binProductos) {
		this.binEmpaqueProductos = binProductos;
	}
	
	public List<Producto> getPresentaciones() {
		return productos;
	}

	public void setPresentaciones(List<Producto> presentaciones) {
		this.productos = presentaciones;
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


	public void activarNuevoEmpaqueProducto() {
		// TODO Auto-generated method stub
		panelProducto.setVisible(true);
		panelProducto.setBounds(12, 95, 714, 100);
		scrollPanel.setVisible(false);
		txtCantidad.setValue(0);
	}

	public void quitarNuevo() {
		// TODO Auto-generated method stub
		panelProducto.setVisible(false);
		pnTabla.setVisible(true);
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

	public JPanel getPnAction() {
		return pnAction;
	}

	public void setPnAction(JPanel pnAction) {
		this.pnAction = pnAction;
	}

	public JPanel getBotones() {
		return botones;
	}

	public void setBotones(JPanel botones) {
		this.botones = botones;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JButton getBtnAtras() {
		return btnAtras;
	}

	public void setBtnAtras(JButton btnAtras) {
		this.btnAtras = btnAtras;
	}

	public JButton getBtnProducto() {
		return btnProducto;
	}

	public void setBtnProducto(JButton btnProducto) {
		this.btnProducto = btnProducto;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public void setBtnSalir(JButton btnSalir) {
		this.btnSalir = btnSalir;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public void setBtnBuscar(JButton btnBuscar) {
		this.btnBuscar = btnBuscar;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setTxtABuscar(JTextField txtABuscar) {
		this.txtABuscar = txtABuscar;
	}

	public void setTxtCantidad(JSpinner txtCantidad) {
		this.txtCantidad = txtCantidad;
	}

	public void setCmbProducto(JComboBox<Producto> cmbProducto) {
		this.cmbProducto = cmbProducto;
	}

	@SuppressWarnings("rawtypes")
	public void setBinEmpaqueProductos(JTableBinding binEmpaqueProductos) {
		this.binEmpaqueProductos = binEmpaqueProductos;
	}

	public void setEmpaqueProductos(List<EmpaqueProducto> empaqueProductos) {
		this.empaqueProductos = empaqueProductos;
	}

	public JPanel getPnTabla() {
		return pnTabla;
	}

	public void setPnTabla(JPanel pnTabla) {
		this.pnTabla = pnTabla;
	}

	public JPanel getPnEmpaqueProductos() {
		return pnEmpaqueProductos;
	}

	public void setPnEmpaqueProductos(JPanel pnEmpaqueProductos) {
		this.pnEmpaqueProductos = pnEmpaqueProductos;
	}

	
}


