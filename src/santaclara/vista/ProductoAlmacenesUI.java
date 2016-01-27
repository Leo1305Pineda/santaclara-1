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
import java.awt.GridLayout;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import santaclara.controlador.ContProductoAlmacenes;
import santaclara.modelo.Almacen;
import santaclara.modelo.EmpaqueProducto;
import santaclara.modelo.ProductoAlmacen;
import net.miginfocom.swing.MigLayout;


@SuppressWarnings("serial")
public class ProductoAlmacenesUI extends JPanel {

	private JPanel 	  			panelProductoAlmacen;
	private JPanel 				pnAction;
	private JPanel 				botones;
	private JPanel 				panel;
	private JPanel 				pnTabla;
	private JPanel  			pnPreoductoAlmacenes;
	
	private JTextField 			txtBuscar;
	private JTextField 			txtABuscar;
	
	private JLabel              lblStock;
	private JLabel              lblStockmin;
	private JLabel              lblExistencia;
	
	private JSpinner			txtStock;
	private JSpinner 			txtStockMin;
	private JSpinner 			txtExistencia;

    @SuppressWarnings("rawtypes")
	private JComboBox cmbEmpaqueProducto;
    @SuppressWarnings("rawtypes")
	private JComboBox cmbAlmacenes;
    
	private JButton 			btnNuevo;
	private JButton				btnModificar;
	private JButton 			btnEliminar;
	private JButton 			btnGuardar;
	private JButton 			btnAtras;
	private JButton 			btnEmpaqueProducto;
	private JButton 			btnSalir;
	private JButton				btnCancelar;
	private JButton 			btnBuscar;
	private JButton 			btnAlmacen;
	
	private JTable 				table;
	
	private JScrollPane 		scrollPanel;
	
	@SuppressWarnings("rawtypes")
	private JTableBinding 		binProductoAlmacenes;
	
	private List<ProductoAlmacen> productoAlmacenes = new ArrayList<ProductoAlmacen>();
	private List<Almacen> 		almacenes = new ArrayList<Almacen>();
	private List<EmpaqueProducto> empaqueProductos = new ArrayList<EmpaqueProducto>();
	private JPanel pnAlmacen;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ProductoAlmacenesUI(ContProductoAlmacenes contProductoAlmacenes,List<ProductoAlmacen> productoAlmacenes,List<EmpaqueProducto> empaqueProductos,List<Almacen> almacenes) {
		
		this.productoAlmacenes = productoAlmacenes;
		this.almacenes = almacenes;
		this.empaqueProductos = empaqueProductos;
				
		setFont(new Font("Dialog", Font.BOLD, 13));
		setForeground(Color.WHITE);
		setBackground(Color.DARK_GRAY);
		setSize(1000,585);
		setLayout(null);
		
		pnPreoductoAlmacenes = new JPanel();
		pnPreoductoAlmacenes.setLocation(12, 12);
		pnPreoductoAlmacenes.setSize(976,561);
		pnPreoductoAlmacenes.setBackground(Color.DARK_GRAY);
		pnPreoductoAlmacenes.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Modulo Producto por Almacenes", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		add(pnPreoductoAlmacenes);
		pnPreoductoAlmacenes.setLayout(null);
		
		pnAction = new JPanel();
		pnAction.setBackground(Color.GRAY);
		pnAction.setForeground(Color.DARK_GRAY);
		pnAction.setBounds(14, 47, 950, 48);
		pnPreoductoAlmacenes.add(pnAction);
		pnAction.setLayout(null);
		
		botones = new JPanel();
		botones.setBounds(1, 0, 949, 37);
		pnAction.add(botones);
		botones.setBackground(Color.DARK_GRAY);
		botones.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "opciones", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 255, 255)));
		
		btnModificar = new JButton("Editar");
		btnModificar.setBounds(265, 17, 125, 16);
		btnModificar.setIcon(new ImageIcon("img/gestion/Modificara.png"));
		btnModificar.setToolTipText("Modificar");					
		btnModificar.setForeground(Color.WHITE);
		btnModificar.setBackground(Color.DARK_GRAY);
		btnModificar.addActionListener(contProductoAlmacenes.modificar());
		botones.add(btnModificar);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.setBounds(130, 17, 130, 16);
		btnNuevo.setIcon(new ImageIcon("img/gestion/add.png"));
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setBackground(Color.DARK_GRAY);
		btnNuevo.addActionListener(contProductoAlmacenes.nuevo());
		botones.setLayout(null);
				
		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(contProductoAlmacenes.Atras());
		btnAtras.setIcon(new ImageIcon("img/gestion/AtrasCurva.png"));
		btnAtras.setBounds(5, 17, 115, 16);
		botones.add(btnAtras);
		btnAtras.setForeground(Color.WHITE);
		btnAtras.setBackground(Color.DARK_GRAY);		
		botones.add(btnNuevo);
				
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setToolTipText("Eliminar");
		btnEliminar.setBounds(395, 17, 125, 16);
		btnEliminar.setIcon(new ImageIcon("img/gestion/cancel.png"));
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(Color.DARK_GRAY);
		btnEliminar.addActionListener(contProductoAlmacenes.eliminar());
		botones.add(btnEliminar);
		
		btnSalir = new JButton("Salir");
		btnSalir.setToolTipText("Cerrar");
		btnSalir.addActionListener(contProductoAlmacenes.salir());
		btnSalir.setIcon(new ImageIcon("img/gestion/SalirCurva.png"));
		btnSalir.setBounds(525, 17, 125, 16);
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.DARK_GRAY);
		botones.add(btnSalir);
		
		panel = new JPanel();
		panel.setForeground(Color.GRAY);
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(14, 12, 950, 35);
		pnPreoductoAlmacenes.add(panel);
		panel.setLayout(new MigLayout("", "[grow]", "[]"));
		
		txtABuscar = new JTextField();
		txtABuscar.setForeground(Color.WHITE);
		txtABuscar.setColumns(10);
		txtABuscar.setBackground(Color.DARK_GRAY);
		panel.add(txtABuscar, "flowx,cell 0 0,growx");
		
		btnBuscar = new JButton("");
		btnBuscar.addActionListener(contProductoAlmacenes.buscar());
		btnBuscar.setVerticalAlignment(SwingConstants.TOP);
		btnBuscar.setIcon(new ImageIcon("img/gestion/buscar.png"));
		btnBuscar.setBackground(Color.DARK_GRAY);
		panel.add(btnBuscar, "cell 0 0");
		
		
		pnTabla = new JPanel();
		pnTabla.setBackground(Color.DARK_GRAY);
		pnTabla.setBounds(14, 85, 950, 289);
		pnPreoductoAlmacenes.add(pnTabla);
		pnTabla.setLayout(null);
		
		scrollPanel = new JScrollPane();
		scrollPanel.setBounds(0, 12, 950, 265);
		scrollPanel.setBackground(Color.DARK_GRAY);
		pnTabla.add(scrollPanel);
		
		table = new JTable();
		table.setBounds(0, 0, 1, 1);
		pnTabla.add(table);
		
		
		panelProductoAlmacen = new JPanel();
		panelProductoAlmacen.setBounds(14, 386, 950, 163);
		pnPreoductoAlmacenes.add(panelProductoAlmacen);
		panelProductoAlmacen.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229), 1, true), "", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		panelProductoAlmacen.setBackground(Color.DARK_GRAY);
		panelProductoAlmacen.getSize(new Dimension(800, 150));
		panelProductoAlmacen.setLayout(null);
		
		lblStock = new JLabel("Stock");
		lblStock.setBounds(10, 12,  56,25);
		lblStock.setBackground(SystemColor.controlHighlight);
		lblStock.setForeground(Color.WHITE);
		panelProductoAlmacen.add(lblStock);
		
		txtStock = new JSpinner(new SpinnerNumberModel(1,1,Integer.MAX_VALUE,1));
		txtStock.setBounds(50, 14, 70,16);
		((JSpinner.NumberEditor)txtStock.getEditor()).getFormat().setMinimumFractionDigits(0);
		panelProductoAlmacen.add(txtStock);
		
		txtStockMin = new JSpinner(new SpinnerNumberModel(1,1,Integer.MAX_VALUE,1));
		txtStockMin.setBounds(210, 14, 70, 16);
		((JSpinner.NumberEditor)txtStockMin.getEditor()).getFormat().setMinimumFractionDigits(0);
		panelProductoAlmacen.add(txtStockMin);
		
		txtExistencia = new JSpinner(new SpinnerNumberModel(1,1,Integer.MAX_VALUE,1));
		txtExistencia.setBounds(390, 16, 70, 16);
		((JSpinner.NumberEditor)txtExistencia.getEditor()).getFormat().setMinimumFractionDigits(0);
		panelProductoAlmacen.add(txtExistencia);
		
		lblStockmin = new JLabel("StockMin");
		lblStockmin.setForeground(Color.WHITE);
		lblStockmin.setBackground(SystemColor.controlHighlight);
		lblStockmin.setBounds(145, 12, 75, 25);
		panelProductoAlmacen.add(lblStockmin);
		
		lblExistencia = new JLabel("Existencia");
		lblExistencia.setForeground(Color.WHITE);
		lblExistencia.setBackground(SystemColor.controlHighlight);
		lblExistencia.setBounds(315, 12, 82, 25);
		panelProductoAlmacen.add(lblExistencia);
		
		JPanel pnEmpaqueProducto = new JPanel();
		pnEmpaqueProducto.setBackground(Color.DARK_GRAY);
		pnEmpaqueProducto.setBounds(2, 45, 945, 46);
		panelProductoAlmacen.add(pnEmpaqueProducto);
		pnEmpaqueProducto.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), " EmpaqueProducto|"
				+ "Presentacion        |"
				+ "Capacidad             |"
				+ "Sabor                      |"
				+ "Precio                    |"
				+ "Cantidad                |", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 255, 255)));
		pnEmpaqueProducto.setLayout(null);	
		cmbEmpaqueProducto = new JComboBox();
		cmbEmpaqueProducto.setBounds(10, 15, 810, 24);
		pnEmpaqueProducto.add(cmbEmpaqueProducto);
		cmbEmpaqueProducto.setBackground(SystemColor.controlHighlight);
		cmbEmpaqueProducto.setForeground(Color.BLACK);		
		cmbEmpaqueProducto.setRenderer(new ListCellRenderer() {
											
		@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
			// TODO Auto-generated method stub
			EmpaqueProducto empaqueProducto = (EmpaqueProducto) value;
			JPanel pnEmpaqueProducto = new JPanel();
			pnEmpaqueProducto.add(new JTextField(empaqueProducto.getProducto().getNombre()));
			pnEmpaqueProducto.add(new JTextField(empaqueProducto.getProducto().getPresentacion().getMaterial()));
			pnEmpaqueProducto.add(new JTextField(empaqueProducto.getProducto().getCapacidad().getVolumenStr()));
			pnEmpaqueProducto.add(new JTextField(empaqueProducto.getProducto().getSabor().getSabor()));
			pnEmpaqueProducto.add(new JTextField(empaqueProducto.getProducto().getPrecioStr()));
			pnEmpaqueProducto.add(new JTextField(empaqueProducto.getUnidadesStr()));
			pnEmpaqueProducto.setLayout(new GridLayout(1, 0, 0, 0));
												
			return pnEmpaqueProducto;
			}
	});
		btnEmpaqueProducto = new JButton("Empaque");
		btnEmpaqueProducto.setBounds(820, 14, 120, 25);
		pnEmpaqueProducto.add(btnEmpaqueProducto);
		btnEmpaqueProducto.setToolTipText("Abrir");
		btnEmpaqueProducto.setIcon(new ImageIcon("img/gestion/Presentacion.png"));
		btnEmpaqueProducto.setForeground(Color.WHITE);
		btnEmpaqueProducto.setBackground(Color.DARK_GRAY);
						
		pnAlmacen = new JPanel();
		pnAlmacen.setBackground(Color.DARK_GRAY);
		pnAlmacen.setBounds(2, 103, 945, 46);
		pnAlmacen.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), " Almacen ", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panelProductoAlmacen.add(pnAlmacen);
		pnAlmacen.setLayout(null);
						
		cmbAlmacenes = new JComboBox();
		cmbAlmacenes.setBounds(10, 15, 810, 24);
		pnAlmacen.add(cmbAlmacenes);
		cmbAlmacenes.setForeground(Color.BLACK);
		cmbAlmacenes.setBackground(SystemColor.controlHighlight);
		cmbAlmacenes.setRenderer(new ListCellRenderer() {
							
			@Override
			public Component getListCellRendererComponent(JList list, Object value,
					int index, boolean isSelected, boolean cellHasFocus) {
					// TODO Auto-generated method stub
					Almacen almacen = (Almacen) value;			
								
					JPanel pnalmacen = new JPanel();
					pnalmacen.add(new JTextField(almacen.getUbicacion()));
					pnalmacen.setLayout(new GridLayout(1, 0, 0, 0));
					return pnalmacen;
				}
			});
						
		btnAlmacen = new JButton("Almacen");
		btnAlmacen.setBounds(820, 14, 120, 25);
		pnAlmacen.add(btnAlmacen);
		btnAlmacen.addActionListener(contProductoAlmacenes.AbrirAlmacen());
		btnAlmacen.setToolTipText("Abrir");
		btnAlmacen.setForeground(Color.WHITE);
		btnAlmacen.setBackground(Color.DARK_GRAY);
						
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(481, 15, 120, 16);
		panelProductoAlmacen.add(btnCancelar);
		btnCancelar.setIcon(new ImageIcon("img/gestion/cancel.png"));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					quitarNuevo();
					}
			});
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(Color.DARK_GRAY);
					
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(605, 15, 112, 16);
		panelProductoAlmacen.add(btnGuardar);
		btnGuardar.setToolTipText("Guardar");
		btnGuardar.setIcon(new ImageIcon("img/gestion/bien.png"));
		btnGuardar.setBackground(Color.DARK_GRAY);
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.addActionListener(contProductoAlmacenes.guardar());
		btnEmpaqueProducto.addActionListener(contProductoAlmacenes.AbrirEmpaqueProducto());
		 
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void activarBinding(List<ProductoAlmacen> productoAlmacenes) {
		// TODO Auto-generated method stub
		pnTabla.setVisible(true);
		table = new JTable();
		scrollPanel.setViewportView(table);
		
		binProductoAlmacenes = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,
    			productoAlmacenes,table);
		
		BeanProperty idAlmacen = BeanProperty.create("idAlmacen");
		BeanProperty nombreAlmacen = BeanProperty.create("ubicacion");
		
		BeanProperty idEmpaqueProducto = BeanProperty.create("empaqueProducto.id");
	    BeanProperty nombreProducto = BeanProperty.create("empaqueProducto.producto.nombre");
	    BeanProperty presentacionProducto = BeanProperty.create("empaqueProducto.producto.presentacion.material");
	    BeanProperty capacidadProducto = BeanProperty.create("empaqueProducto.producto.capacidad.volumen");
	    BeanProperty saborProducto = BeanProperty.create("empaqueProducto.producto.sabor.sabor");
	    
	    BeanProperty stockProductAlmacen = BeanProperty.create("stock");
	    BeanProperty stockMinProductoAlmacen = BeanProperty.create("stockMin");
	    BeanProperty existenciaProductoAlmacen = BeanProperty.create("existencia");

	    binProductoAlmacenes.addColumnBinding(idAlmacen).setColumnClass(String.class).setColumnName("Id");
	    binProductoAlmacenes.addColumnBinding(nombreAlmacen).setColumnClass(String.class).setColumnName("Ubicacion del Almacen");
	    
	    binProductoAlmacenes.addColumnBinding(idEmpaqueProducto).setColumnClass(String.class).setColumnName("Id");
	    binProductoAlmacenes.addColumnBinding(nombreProducto).setColumnClass(String.class).setColumnName("Producto");
	    binProductoAlmacenes.addColumnBinding(presentacionProducto).setColumnClass(String.class).setColumnName("Presentacion");
	    binProductoAlmacenes.addColumnBinding(capacidadProducto).setColumnClass(String.class).setColumnName("Capacidad");
	    binProductoAlmacenes.addColumnBinding(saborProducto).setColumnClass(String.class).setColumnName("Sabor");
	    
	    binProductoAlmacenes.addColumnBinding(stockProductAlmacen).setColumnClass(String.class).setColumnName("Stock");
	    binProductoAlmacenes.addColumnBinding(stockMinProductoAlmacen).setColumnClass(String.class).setColumnName("Stock Minimo");
	    binProductoAlmacenes.addColumnBinding(existenciaProductoAlmacen).setColumnClass(String.class).setColumnName("Existencia");

	    binProductoAlmacenes.bind();

	    JComboBoxBinding jcomboEmpaqueProductos = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ,empaqueProductos,cmbEmpaqueProducto);
	    
	    jcomboEmpaqueProductos.bind();
	    
	    JComboBoxBinding jcomboAlmacenes = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ,almacenes,cmbAlmacenes);
	    
	    jcomboAlmacenes.bind();
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

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public JSpinner getTxtStock() {
		return txtStock;
	}

	public List<ProductoAlmacen> getProductoAlmacenes() {
		return productoAlmacenes;
	}

	public void setAlmacenes(List<Almacen> almacenes) {
		this.almacenes = almacenes;
	}

	@SuppressWarnings("rawtypes")
	public JTableBinding getBinProductoAlmacenes() {
		return binProductoAlmacenes;
	}

	@SuppressWarnings("rawtypes")
	public void setBinProductoAlmacenes(JTableBinding binProductoAlmacenes) {
		this.binProductoAlmacenes = binProductoAlmacenes;
	}
	
	public List<Almacen> getAlmacenes() {
		return almacenes;
	}

	public JPanel getPanelProductoAlmacen() {
		return panelProductoAlmacen;
	}

	public void setPanelProductoAlmacen(JPanel panelProductoAlmacen) {
		this.panelProductoAlmacen = panelProductoAlmacen;
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


	public void activarNuevoProductoAlmacen() {
		// TODO Auto-generated method stub
	
		panelProductoAlmacen.setVisible(true);
		panelProductoAlmacen.setBounds(12, 95, 950, 280);
		
		pnTabla.setVisible(false);
		scrollPanel.setVisible(false);
	}

	public void quitarNuevo() {
		// TODO Auto-generated method stub
		panelProductoAlmacen.setVisible(false);
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

	public void setTxtABuscar(JTextField txtABuscar) {
		this.txtABuscar = txtABuscar;
	}

	public void setTxtCantidad(JSpinner txtCantidad) {
		this.txtStock = txtCantidad;
	}

	public JPanel getPnTabla() {
		return pnTabla;
	}

	public void setPnTabla(JPanel pnTabla) {
		this.pnTabla = pnTabla;
	}

	public JPanel getPnPreoductoAlmacenes() {
		return pnPreoductoAlmacenes;
	}

	public void setPnPreoductoAlmacenes(JPanel pnPreoductoAlmacenes) {
		this.pnPreoductoAlmacenes = pnPreoductoAlmacenes;
	}

	@SuppressWarnings("unchecked")
	public JComboBox<Almacen> getCmbAlmacenes() {
		return cmbAlmacenes;
	}

	public void setCmbAlmacenes(JComboBox<Almacen> cmbAlmacenes) {
		this.cmbAlmacenes = cmbAlmacenes;
	}

	public JButton getBtnEmpaqueProducto() {
		return btnEmpaqueProducto;
	}

	public void setBtnEmpaqueProducto(JButton btnEmpaqueProducto) {
		this.btnEmpaqueProducto = btnEmpaqueProducto;
	}

	public JButton getBtnAlmacen() {
		return btnAlmacen;
	}

	public void setBtnAlmacen(JButton btnAlmacen) {
		this.btnAlmacen = btnAlmacen;
	}

	public void setEmpaqueProductos(List<ProductoAlmacen> empaqueProductos) {
		this.productoAlmacenes = empaqueProductos;
	}

	public void setProductoAlmacenes(List<ProductoAlmacen> productoAlmacenes) {
		this.productoAlmacenes = productoAlmacenes;
	}

	public JSpinner getTxtStockMin() {
		return txtStockMin;
	}

	public void setTxtStockMin(JSpinner txtStockMin) {
		this.txtStockMin = txtStockMin;
	}

	public JSpinner getTxtExistencia() {
		return txtExistencia;
	}

	public void setTxtExistencia(JSpinner txtExistencia) {
		this.txtExistencia = txtExistencia;
	}

	public void setTxtStock(JSpinner txtStock) {
		this.txtStock = txtStock;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getCmbEmpaqueProducto() {
		return cmbEmpaqueProducto;
	}

	@SuppressWarnings("rawtypes")
	public void setCmbEmpaqueProducto(JComboBox cmbEmpaqueProducto) {
		this.cmbEmpaqueProducto = cmbEmpaqueProducto;
	}
}


