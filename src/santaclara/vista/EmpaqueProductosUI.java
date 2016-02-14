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
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import net.miginfocom.swing.MigLayout;

import santaclara.controlador.ContEmpaqueProductos;
import santaclara.modelo.Producto;
import santaclara.vista.herramientas.VistaGenericaUI;

import java.awt.GridLayout;

@SuppressWarnings("serial")
public class EmpaqueProductosUI extends VistaGenericaUI {

	private JPanel 	  			panelEmpaqueProducto;
	
	private JTextField 			txtBuscar;
	private JTextField  		txtId;
	private JTextField 			txtABuscar;
	
	private JSpinner			txtCantidad;

    private JComboBox<Producto> cmbProducto;
    
	private JButton 			btnNuevo;
	private JButton 			btnEliminar;
	private JButton 			btnGuardar;
	private JButton 			btnProducto;
	private JButton				btnCancelar;
	private JButton 			btnBuscar;
	
	private JPanel pnEmpaque;
	
	private ContEmpaqueProductos contEmpaqueProductos;
	
	public EmpaqueProductosUI(ContEmpaqueProductos contEmpaqueProductos) {
		
		this.contEmpaqueProductos = contEmpaqueProductos;
		
		dibujarPanelOpciones();
		dibujarPanelTabla();
		dibujarPanelEmpaqueProducto();
		dibujarBotonAtras();
		
		getBtnAtras().addActionListener(contEmpaqueProductos.Atras());
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.setIcon(new ImageIcon("img/gestion/add.png"));
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setBackground(Color.DARK_GRAY);
		btnNuevo.addActionListener(contEmpaqueProductos.nuevo());
		getPnBotones().add(btnNuevo);
					
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setToolTipText("Eliminar");
		btnEliminar.setIcon(new ImageIcon("img/gestion/cancel.png"));
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(Color.DARK_GRAY);
		btnEliminar.addActionListener(contEmpaqueProductos.eliminar());
		getPnBotones().add(btnEliminar);
		
		dibujarBotonSalir();
		getBtnSalir().addActionListener(contEmpaqueProductos.salir());
	
		txtABuscar = new JTextField();
		txtABuscar.setForeground(Color.WHITE);
		txtABuscar.setColumns(10);
		txtABuscar.setBackground(Color.DARK_GRAY);
		getPanelBuscar().add(txtABuscar, "flowx,cell 0 0,growx");
		
		btnBuscar = new JButton("");
		btnBuscar.addActionListener(contEmpaqueProductos.buscar());
		btnBuscar.setVerticalAlignment(SwingConstants.TOP);
		btnBuscar.setIcon(new ImageIcon("img/gestion/buscar.png"));
		btnBuscar.setBackground(Color.DARK_GRAY);
		getPanelBuscar().add(btnBuscar, "cell 0 0");
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void dibujarPanelEmpaqueProducto(){
		
		panelEmpaqueProducto = new JPanel();
		panelEmpaqueProducto.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229), 1, true), "Editar Empaque Producto", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		panelEmpaqueProducto.setBackground(Color.DARK_GRAY);
		panelEmpaqueProducto.setLayout(new MigLayout());
		
		txtId = new JTextField();
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBackground(SystemColor.controlHighlight);
		lblCantidad.setForeground(Color.WHITE);
		panelEmpaqueProducto.add(lblCantidad,"cell 0 0");
		
		txtCantidad = new JSpinner(new SpinnerNumberModel(1,1,Integer.MAX_VALUE,1));
		((JSpinner.NumberEditor)txtCantidad.getEditor()).getFormat().setMinimumFractionDigits(0);
		panelEmpaqueProducto.add(txtCantidad,"cell 1 0");
		
		pnEmpaque = new JPanel();
		pnEmpaque.setBackground(Color.DARK_GRAY);
		pnEmpaque.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229), 1, true),"Nombre            |"
				+ "Presentacion  |"
				+ "Capacidad      |"
				+ "Sabor               |"
				+ "Precio               |", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnEmpaque.setLayout(new MigLayout());
		
		cmbProducto = new JComboBox<Producto>();
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
				
				return pnProducto;
			}
		});
		pnEmpaque.add(cmbProducto,"cell 0 0");
		panelEmpaqueProducto.add(pnEmpaque,"cell 1 1");
		
		btnProducto = new JButton("Producto");
		btnProducto.setToolTipText("Abrir");
		btnProducto.setIcon(new ImageIcon("img/gestion/Presentacion.png"));
		btnProducto.setForeground(Color.WHITE);
		btnProducto.setBackground(Color.DARK_GRAY);
		btnProducto.addActionListener(contEmpaqueProductos.AbrirProducto());
		panelEmpaqueProducto.add(btnProducto,"cell 0 1");
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setToolTipText("Guardar");
		btnGuardar.setIcon(new ImageIcon("img/gestion/bien.png"));
		btnGuardar.setBackground(Color.DARK_GRAY);
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.addActionListener(contEmpaqueProductos.guardar());
		panelEmpaqueProducto.add(btnGuardar,"cell 0 2");
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon("img/gestion/cancel.png"));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				quitarNuevo();
			}
		});
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(Color.DARK_GRAY);
		panelEmpaqueProducto.add(btnCancelar,"cell 1 2");
		
		add(panelEmpaqueProducto,BorderLayout.SOUTH);
		btnCancelar.setVisible(false);
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
	
	public JTextField getTxtId() {
		return txtId;
	}

	public void setTxtId(JTextField txtId) {
		this.txtId = txtId;
	}

	public JPanel getPanelProducto() {
		return panelEmpaqueProducto;
	}

	public void setPanelProducto(JPanel panelProducto) {
		this.panelEmpaqueProducto = panelProducto;
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

	public void quitarNuevo() {
		// TODO Auto-generated method stub
		remove(panelEmpaqueProducto);
	}
		
	public JTextField getTxtABuscar() {
		return txtABuscar;
	}

	public void setTxtABuscar(String txtABuscar) {
		this.txtABuscar.setText(txtABuscar);
	}

	public JPanel getPnAction() {
		return getPnBotones();
	}

	public JPanel getPanel() {
		return getPanelBuscar();
	}

	public JButton getBtnProducto() {
		return btnProducto;
	}

	public void setBtnProducto(JButton btnProducto) {
		this.btnProducto = btnProducto;
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
		this.txtCantidad = txtCantidad;
	}

	public void setCmbProducto(JComboBox<Producto> cmbProducto) {
		this.cmbProducto = cmbProducto;
	}

	public JPanel getPanelEmpaqueProducto() {
		return panelEmpaqueProducto;
	}

	public void setPanelEmpaqueProducto(JPanel panelEmpaqueProducto) {
		this.panelEmpaqueProducto = panelEmpaqueProducto;
	}

	public JPanel getPnEmpaque() {
		return pnEmpaque;
	}

	public void setPnEmpaque(JPanel pnEmpaque) {
		this.pnEmpaque = pnEmpaque;
	}

	public ContEmpaqueProductos getContEmpaqueProductos() {
		return contEmpaqueProductos;
	}

	public void setContEmpaqueProductos(ContEmpaqueProductos contEmpaqueProductos) {
		this.contEmpaqueProductos = contEmpaqueProductos;
	}
	
	
}


