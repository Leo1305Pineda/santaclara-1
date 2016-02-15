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

import santaclara.controlador.ContProductos;
import santaclara.modelo.Capacidad;
import santaclara.modelo.Presentacion;
import santaclara.modelo.Sabor;
import santaclara.vista.herramientas.VistaGenericaUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class ProductosUI extends VistaGenericaUI {

	private JComboBox<Sabor> 		cmbSabor;
    private JComboBox<Presentacion> cmbPresentacion;
    private JComboBox<Capacidad> 	cmbCapacidad;

    private JTextField 				txtNombre;
    private JTextField 	 			txtId;
	
    private JSpinner				txtPrecio;
    
    private Checkbox checkIva; 

	private JPanel 	pnlProducto;
	
	private JButton btnNuevo;
	private JButton btnEliminar;
	private JButton btnCancelar;
	private JButton btnSabor;
	private JButton btnPresentacion;
	private JButton btnCapacidades;
    private JButton btnGuardar;
    
    private ContProductos contProductos;
	
	public ProductosUI(ContProductos contProductos) {
		super();
		
		this.contProductos = contProductos;
		
		dibujarPanelOpciones();
		dibujarBuscar();
		getBtnABuscar().addActionListener(contProductos.buscar());
		dibujarPanelTabla();
		dibujarBotonAtras();
		getBtnAtras().addActionListener(contProductos.Atras());
		dibujarPnlProducto();

		btnNuevo = new JButton("Nuevo");							
		btnNuevo.setIcon(new ImageIcon("img/gestion/add.png"));							
		btnNuevo.setForeground(Color.WHITE);						
		btnNuevo.setBackground(Color.DARK_GRAY);						
		btnNuevo.addActionListener(contProductos.nuevo());
		btnNuevo.setFont(new Font("Dialog", Font.BOLD, 10));		
		getPnBotones().add(btnNuevo);														

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
		
		dibujarBotonSalir();
		getBtnSalir().addActionListener(contProductos.salir());
	}
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void dibujarPnlProducto(){
		pnlProducto = new JPanel();
		pnlProducto.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229), 1, true), "Editar Producto", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnlProducto.setBackground(Color.DARK_GRAY);
		pnlProducto.setLayout(new MigLayout());
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(Color.WHITE);
		pnlProducto.add(lblNombre,"cell 0 0");

		txtNombre = new JTextField();
		txtNombre.setColumns(20);
		pnlProducto.add(txtNombre,"cell 1 0");
		
		JLabel lblPresentacion = new JLabel("Presentacion:");
		lblPresentacion.setForeground(Color.WHITE);
		lblPresentacion.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		pnlProducto.add(lblPresentacion,"cell 0 1");

		cmbPresentacion = new JComboBox<Presentacion>();
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
		pnlProducto.add(cmbPresentacion,"cell 1 1");

		JLabel lblCapactidad = new JLabel("Capacidad:");
		lblCapactidad.setForeground(Color.WHITE);
		lblCapactidad.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		pnlProducto.add(lblCapactidad,"cell 0 2");

		cmbCapacidad = new JComboBox<Capacidad>();
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
		pnlProducto.add(cmbCapacidad,"cell 1 2");
		
		JLabel lblSabor = new JLabel("Sabor:");
		lblSabor.setForeground(Color.WHITE);
		lblSabor.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		pnlProducto.add(lblSabor,"cell 0 3");

		cmbSabor = new JComboBox<Sabor>();
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
		pnlProducto.add(cmbSabor,"cell 1 3");
				
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBackground(SystemColor.controlHighlight);
		lblPrecio.setForeground(Color.WHITE);
		pnlProducto.add(lblPrecio,"cell 0 4");
		
		txtPrecio = new JSpinner(new SpinnerNumberModel(1.0,0.0,Double.MAX_VALUE,1.0));
		((JSpinner.NumberEditor)txtPrecio.getEditor()).getFormat().setMinimumFractionDigits(2);
		pnlProducto.add(txtPrecio,"cell 1 4");

		checkIva = new Checkbox("Iva Exento");
		checkIva.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		checkIva.setForeground(Color.WHITE);
		pnlProducto.add(checkIva,"cell 0 5");
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setIcon(new ImageIcon("img/gestion/bien.png"));
		btnGuardar.setBackground(Color.DARK_GRAY);
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.addActionListener(contProductos.guardar());
		pnlProducto.add(btnGuardar,"cell 0 6");
		
		btnCancelar = new JButton("Cancelar");
		pnlProducto.add(btnCancelar,"cell 1 7");

		txtId = new JTextField();
    	
		add(pnlProducto,BorderLayout.SOUTH);
		btnCancelar.setVisible(false);
		repaint();
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

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}

	public Checkbox getCheckIva() {
		return checkIva;
	}


	public void setCheckIva(Checkbox checkIva) {
		this.checkIva = checkIva;
	}

	public JPanel getPnlProducto() {
		return pnlProducto;
	}

	public void setPnlProducto(JPanel pnlProducto) {
		this.pnlProducto = pnlProducto;
	}

	public JButton getBtnSabor() {
		return btnSabor;
	}

	public void setBtnSabor(JButton btnSabor) {
		this.btnSabor = btnSabor;
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

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}
	
	
}


