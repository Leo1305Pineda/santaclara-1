package santaclara.vista;

import java.awt.BorderLayout;
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
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.GridLayout;

import santaclara.controlador.ContProductoAlmacenes;
import santaclara.modelo.Almacen;
import santaclara.modelo.EmpaqueProducto;
import santaclara.vista.herramientas.VistaGenericaUI;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class ProductoAlmacenesUI extends VistaGenericaUI {

	private JPanel 	  			panelProductoAlmacen;
	
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
	private JButton 			btnEliminar;
	private JButton 			btnGuardar;
	private JButton 			btnEmpaqueProducto;
	private JButton				btnCancelar;
	private JButton 			btnAlmacen;

	private ContProductoAlmacenes contProductoAlmacenes;
	
	private JTextField campo = new JTextField();
	
	public ProductoAlmacenesUI(ContProductoAlmacenes contProductoAlmacenes) {
		this.contProductoAlmacenes = contProductoAlmacenes;			
		
		dibujarPanelOpciones();
		dibujarBuscar();
		getBtnABuscar().addActionListener(contProductoAlmacenes.buscar());
		dibujarPanelTabla();
		dibujarBotonAtras();
		getBtnAtras().addActionListener(contProductoAlmacenes.Atras());
		dibujarProductoAlmacen();
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.setBounds(130, 17, 130, 16);
		btnNuevo.setIcon(new ImageIcon("img/gestion/add.png"));
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setBackground(Color.DARK_GRAY);
		btnNuevo.addActionListener(contProductoAlmacenes.nuevo());		
		getPnBotones().add(btnNuevo);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setToolTipText("Eliminar");
		btnEliminar.setBounds(395, 17, 125, 16);
		btnEliminar.setIcon(new ImageIcon("img/gestion/cancel.png"));
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(Color.DARK_GRAY);
		btnEliminar.addActionListener(contProductoAlmacenes.eliminar());
		getPnBotones().add(btnEliminar);
		
		dibujarBotonSalir();
		getBtnSalir().addActionListener(contProductoAlmacenes.salir());
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void dibujarProductoAlmacen(){
		panelProductoAlmacen = new JPanel();
		panelProductoAlmacen.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229), 1, true), "", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		panelProductoAlmacen.setBackground(Color.DARK_GRAY);
		panelProductoAlmacen.setLayout(new MigLayout());
		
		lblStock = new JLabel("Stock");
		lblStock.setBackground(SystemColor.controlHighlight);
		lblStock.setForeground(Color.WHITE);
		panelProductoAlmacen.add(lblStock,"cell 0 0");
		
		txtStock = new JSpinner(new SpinnerNumberModel(1,1,Integer.MAX_VALUE,1));
		((JSpinner.NumberEditor)txtStock.getEditor()).getFormat().setMinimumFractionDigits(0);
		panelProductoAlmacen.add(txtStock ,"cell 1 0");
		
		JPanel pnEmpaqueProducto_1 = new JPanel();
		pnEmpaqueProducto_1.setBackground(Color.DARK_GRAY);
		pnEmpaqueProducto_1.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), " EmpaqueProducto|"
				+ "Presentacion        |"
				+ "Capacidad             |"
				+ "Sabor                      |"
				+ "Precio                    |"
				+ "Cantidad                |", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 255, 255)));
		pnEmpaqueProducto_1.setLayout(new MigLayout());	
		
		cmbEmpaqueProducto = new JComboBox();
		cmbEmpaqueProducto.setBackground(Color.DARK_GRAY);
		pnEmpaqueProducto_1.add(cmbEmpaqueProducto);
		cmbEmpaqueProducto.setBackground(SystemColor.controlHighlight);
		cmbEmpaqueProducto.setForeground(Color.BLACK);		
		cmbEmpaqueProducto.setRenderer(new ListCellRenderer() {
											
		@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
			// TODO Auto-generated method stub
			EmpaqueProducto empaqueProducto = (EmpaqueProducto) value;
			JPanel pnEmpaqueProducto = new JPanel();
			campo = new JTextField(empaqueProducto.getProducto().getNombre());
			campo.setColumns(10);
			pnEmpaqueProducto.add(campo,"cell 0 0");
			campo = new JTextField(empaqueProducto.getProducto().getPresentacion().getMaterial());
			campo.setColumns(10);
			pnEmpaqueProducto.add(campo,"cell 1 0");
			campo = new JTextField(empaqueProducto.getProducto().getCapacidad().getVolumenStr());
			campo.setColumns(10);
			pnEmpaqueProducto.add(campo,"cell 2 0");
			campo = new JTextField(empaqueProducto.getProducto().getSabor().getSabor());
			campo.setColumns(10);
			pnEmpaqueProducto.add(campo,"cell 3 0");
			campo = new JTextField(empaqueProducto.getProducto().getPrecioStr());
			campo.setColumns(10);
			pnEmpaqueProducto.add(campo,"cell 4 0");
			campo = new JTextField(empaqueProducto.getUnidadesStr());
			campo.setColumns(10);
			pnEmpaqueProducto.add(campo,"cell 5 0");
			pnEmpaqueProducto.setLayout(new GridLayout());
												
			return pnEmpaqueProducto;
			}
	});
		btnEmpaqueProducto = new JButton("Empaque");
		pnEmpaqueProducto_1.add(btnEmpaqueProducto);
		btnEmpaqueProducto.setToolTipText("Abrir");
		btnEmpaqueProducto.setIcon(new ImageIcon("img/gestion/Presentacion.png"));
		btnEmpaqueProducto.setForeground(Color.WHITE);
		btnEmpaqueProducto.setBackground(Color.DARK_GRAY);
		
		panelProductoAlmacen.add(pnEmpaqueProducto_1,"cell 2 0");
		btnEmpaqueProducto.addActionListener(contProductoAlmacenes.AbrirEmpaqueProducto());
		
		lblStockmin = new JLabel("StockMin");
		lblStockmin.setForeground(Color.WHITE);
		lblStockmin.setBackground(SystemColor.controlHighlight);
		panelProductoAlmacen.add(lblStockmin,"cell 0 1");
		
		txtStockMin = new JSpinner(new SpinnerNumberModel(1,1,Integer.MAX_VALUE,1));
		((JSpinner.NumberEditor)txtStockMin.getEditor()).getFormat().setMinimumFractionDigits(0);
		panelProductoAlmacen.add(txtStockMin,"cell 1 1");
		
		JPanel pnAlmacen = new JPanel();
		pnAlmacen.setBackground(Color.DARK_GRAY);
		pnAlmacen.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), " Almacen ", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 255, 255)));
		pnAlmacen.setLayout(new MigLayout());
		
		cmbAlmacenes = new JComboBox();
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
		pnAlmacen.add(btnAlmacen);
		btnAlmacen.addActionListener(contProductoAlmacenes.AbrirAlmacen());
		btnAlmacen.setToolTipText("Abrir");
		btnAlmacen.setForeground(Color.WHITE);
		btnAlmacen.setBackground(Color.DARK_GRAY);
		
		panelProductoAlmacen.add(pnAlmacen,"cell 2 1");
		
		lblExistencia = new JLabel("Existencia");
		lblExistencia.setForeground(Color.WHITE);
		lblExistencia.setBackground(SystemColor.controlHighlight);
		panelProductoAlmacen.add(lblExistencia,"cell 0 2");
		
		txtExistencia = new JSpinner(new SpinnerNumberModel(1,1,Integer.MAX_VALUE,1));
		((JSpinner.NumberEditor)txtExistencia.getEditor()).getFormat().setMinimumFractionDigits(0);
		panelProductoAlmacen.add(txtExistencia,"cell 1 2");

		add(panelProductoAlmacen,BorderLayout.SOUTH);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setToolTipText("Guardar");
		btnGuardar.setIcon(new ImageIcon("img/gestion/bien.png"));
		btnGuardar.setBackground(Color.DARK_GRAY);
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.addActionListener(contProductoAlmacenes.guardar());
		panelProductoAlmacen.add(btnGuardar,"cell 2 2");
		
		btnCancelar = new JButton("Cancelar");
		panelProductoAlmacen.add(btnCancelar,"cell 0 3");
		btnCancelar.setVisible(false);
	}

	public JPanel getPanelProductoAlmacen() {
		return panelProductoAlmacen;
	}

	public void setPanelProductoAlmacen(JPanel panelProductoAlmacen) {
		this.panelProductoAlmacen = panelProductoAlmacen;
	}

	public JLabel getLblStock() {
		return lblStock;
	}

	public void setLblStock(JLabel lblStock) {
		this.lblStock = lblStock;
	}

	public JLabel getLblStockmin() {
		return lblStockmin;
	}

	public void setLblStockmin(JLabel lblStockmin) {
		this.lblStockmin = lblStockmin;
	}

	public JLabel getLblExistencia() {
		return lblExistencia;
	}

	public void setLblExistencia(JLabel lblExistencia) {
		this.lblExistencia = lblExistencia;
	}

	public JSpinner getTxtStock() {
		return txtStock;
	}

	public void setTxtStock(JSpinner txtStock) {
		this.txtStock = txtStock;
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

	@SuppressWarnings("rawtypes")
	public JComboBox getCmbEmpaqueProducto() {
		return cmbEmpaqueProducto;
	}

	@SuppressWarnings("rawtypes")
	public void setCmbEmpaqueProducto(JComboBox cmbEmpaqueProducto) {
		this.cmbEmpaqueProducto = cmbEmpaqueProducto;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getCmbAlmacenes() {
		return cmbAlmacenes;
	}

	@SuppressWarnings("rawtypes")
	public void setCmbAlmacenes(JComboBox cmbAlmacenes) {
		this.cmbAlmacenes = cmbAlmacenes;
	}

	public JButton getBtnNuevo() {
		return btnNuevo;
	}

	public void setBtnNuevo(JButton btnNuevo) {
		this.btnNuevo = btnNuevo;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public JButton getBtnEmpaqueProducto() {
		return btnEmpaqueProducto;
	}

	public void setBtnEmpaqueProducto(JButton btnEmpaqueProducto) {
		this.btnEmpaqueProducto = btnEmpaqueProducto;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public JButton getBtnAlmacen() {
		return btnAlmacen;
	}

	public void setBtnAlmacen(JButton btnAlmacen) {
		this.btnAlmacen = btnAlmacen;
	}

	public ContProductoAlmacenes getContProductoAlmacenes() {
		return contProductoAlmacenes;
	}

	public void setContProductoAlmacenes(ContProductoAlmacenes contProductoAlmacenes) {
		this.contProductoAlmacenes = contProductoAlmacenes;
	}
}


