package santaclara.vista;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import net.miginfocom.swing.MigLayout;
import santaclara.controlador.ContCamiones;
import santaclara.modelo.Camion;
import santaclara.vista.herramientas.VistaGenericaUI;
 

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JSpinner;

@SuppressWarnings("serial")
public class CamionesUI extends VistaGenericaUI {
	

	private JButton btnNuevo;
	private JButton btnEditar;
	private JButton btnAtras;
	private JButton btnSalir;
	private JButton btnABuscar;
	private JButton btnEliminar;
	 
	@SuppressWarnings("rawtypes")
	private JTableBinding binCamioness; 
	
 
	private JTextField txtABuscar;
	private JSpinner txtCapacidad;
	
	private List<Camion> camiones = new ArrayList<Camion>();
	private JPanel pnCamion;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JLabel lblPlaca;
	private JTextField txtPlaca;
	private JTextField txtId;
	private JLabel lblMarca;
	private JLabel lblColor;
	private JLabel lblModelo;
	private JLabel lblAnno;
	private JLabel lblCapacidad;
	private JTextField txtMarca;
	private JTextField txtColor;
	private JTextField txtModelo;
	private JTextField txtYear;
	
	public CamionesUI(ContCamiones contCamiones,List<Camion> camiones) {
		super();
		this.camiones = camiones;
		dibujarPanelOpciones();
		dibujarPanelTabla();
		

		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(contCamiones.nuevo());
		//btnNuevo.setBounds(100, 15, 103, 16);
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setBackground(Color.DARK_GRAY);
		btnNuevo.setIcon(new ImageIcon("img/gestion/add.png"));
		getPnBotones().add(btnNuevo);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(contCamiones.modificar());
		//btnEditar.setBounds(207, 15, 102, 16);
		btnEditar.setForeground(Color.WHITE);
		btnEditar.setBackground(Color.DARK_GRAY);
		btnEditar.setIcon(new ImageIcon("img/gestion/Modificara.png"));
		getPnBotones().add(btnEditar);
		
		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(contCamiones.atras());
		//btnAtras.setBounds(5, 15, 92, 16);
		btnAtras.setForeground(Color.WHITE);
		btnAtras.setBackground(Color.DARK_GRAY);
		btnAtras.setIcon(new ImageIcon("img/gestion/AtrasCurva.png"));
		getPnBotones().add(btnAtras);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(contCamiones.salir());
		//btnSalir.setBounds(425, 15, 90, 16);
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.DARK_GRAY);
		btnSalir.setIcon(new ImageIcon("img/gestion/SalirCurva.png"));
		getPnBotones().add(btnSalir);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(Color.DARK_GRAY);
		btnEliminar.setIcon(new ImageIcon("img/gestion/cancel.png"));
		btnEliminar.addActionListener(contCamiones.eliminar());
		//btnEliminar.setBounds(312, 15, 110, 16);
		getPnBotones().add(btnEliminar);
		

		txtABuscar = new JTextField();
		txtABuscar.setForeground(Color.WHITE);
		//txtABuscar.setBackground(new Color(64, 64, 64));
		txtABuscar.setColumns(10);
		getPanelBuscar().add(txtABuscar);
		
		btnABuscar = new JButton("");
		btnABuscar.addActionListener(contCamiones.buscar());
		btnABuscar.setVerticalAlignment(SwingConstants.TOP);
		btnABuscar.setBackground(Color.DARK_GRAY);
		btnABuscar.setIcon(new ImageIcon("img/gestion/buscar.png"));
		getPanelBuscar().add(btnABuscar);
		
		pnCamion = new JPanel();
		pnCamion.setBackground(Color.DARK_GRAY);
		//pnCamion.setBounds(12, 270, 520, 133);
		pnCamion.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)),"EDITAR CAMION ", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnCamion.setLayout(new MigLayout());
		//pnCamiones.add(pnCamion);
		//pnCamion.setLayout(null);
		
		lblPlaca = new JLabel("Placa:");
		lblPlaca.setForeground(Color.WHITE);
		//lblPlaca.setBounds(130, 5, 69, 25);
		pnCamion.add(lblPlaca);
		
		txtPlaca = new JTextField();
		txtPlaca.setColumns(10);
		//txtPlaca.setBounds(200, 10, 188, 16);
		pnCamion.add(txtPlaca);
		
		txtId = new JTextField();
		//txtId.setBounds(254, 12, 54, 19);
		//pnCamion.add(txtId);
		txtId.setColumns(10);
		
		lblMarca = new JLabel("Marca:");
		lblMarca.setForeground(Color.WHITE);
		//lblMarca.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		//lblMarca.setBounds(5, 33, 69, 25);
		pnCamion.add(lblMarca);
		
		txtMarca = new JTextField();
		txtMarca.setColumns(10);
		//txtMarca.setBounds(73, 38, 188, 16);
		pnCamion.add(txtMarca,"wrap");

		
		lblColor = new JLabel("Color:");
		lblColor.setForeground(Color.WHITE);
		lblColor.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		//lblColor.setBounds(5, 68, 69, 25);
		pnCamion.add(lblColor);
		
		txtColor = new JTextField();
		txtColor.setColumns(10);
		//txtColor.setBounds(73, 70, 188, 16);
		pnCamion.add(txtColor);
		
		lblModelo = new JLabel("Modelo:");
		lblModelo.setForeground(Color.WHITE);
		lblModelo.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		//lblModelo.setBounds(264, 38, 69, 25);
		pnCamion.add(lblModelo);
		
		txtModelo = new JTextField();
		txtModelo.setColumns(10);
		//txtModelo.setBounds(322, 41, 188, 16);
		pnCamion.add(txtModelo,"wrap");
		
		lblAnno = new JLabel("Año:");
		lblAnno.setForeground(Color.WHITE);
		lblAnno.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		//lblAnno.setBounds(264, 68, 69, 25);
		pnCamion.add(lblAnno);
		
		txtYear = new JTextField();
		txtYear.setColumns(10);
		//txtYear.setBounds(322, 71, 188, 16);
		pnCamion.add(txtYear);
		
		lblCapacidad = new JLabel("Capacidad:");
		lblCapacidad.setForeground(Color.WHITE);
		lblCapacidad.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		//lblCapacidad.setBounds(5, 96, 89, 25);
		pnCamion.add(lblCapacidad);
		
		txtCapacidad = new JSpinner(new SpinnerNumberModel(0.0,0.00,Double.MAX_VALUE,0.1));
		//txtCapacidad.setBounds(100, 99, 161, 16);
		((JSpinner.NumberEditor)txtCapacidad.getEditor()).getFormat().setMinimumFractionDigits(2);
		pnCamion.add(txtCapacidad,"wrap, width 80:80:80 ");
		
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(contCamiones.guardar());
		//btnGuardar.setBounds(395, 10, 115, 16);
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBackground(Color.DARK_GRAY);
		btnGuardar.setIcon(new ImageIcon("img/gestion/bien.png"));
		pnCamion.add(btnGuardar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(contCamiones.quitarNuevo());
		//btnCancelar.setBounds(5, 10, 120, 16);
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(Color.DARK_GRAY);
		btnCancelar.setIcon(new ImageIcon("img/gestion/cancel.png"));
		pnCamion.add(btnCancelar);
 
		
		activarBinding(camiones);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void activarBinding(List<Camion> camiones) {
		// TODO Auto-generated method stub
		getPnTabla().setVisible(true);
		setTable(new JTable());
		getScrollPanel().setViewportView(getTable());
		binCamioness = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,camiones,getTable());
		
		BeanProperty idCamiones  = BeanProperty.create("id");
		BeanProperty placaCamion = BeanProperty.create("placa");
		BeanProperty colorCamion = BeanProperty.create("color");
		BeanProperty capacidadCamion = BeanProperty.create("capacidad");
		BeanProperty anoCamion = BeanProperty.create("ano");
		BeanProperty marcaCamion = BeanProperty.create("marca");
		BeanProperty modeloCamion = BeanProperty.create("modelo");
	    
	    binCamioness.addColumnBinding(idCamiones).setColumnClass(Integer.class).setColumnName("id");
	    binCamioness.addColumnBinding(placaCamion).setColumnClass(String.class).setColumnName("Placa");
	    binCamioness.addColumnBinding(marcaCamion).setColumnClass(String.class).setColumnName("Marca");
	    binCamioness.addColumnBinding(modeloCamion).setColumnClass(String.class).setColumnName("Modelo");
	    binCamioness.addColumnBinding(anoCamion).setColumnClass(String.class).setColumnName("Año");
	    binCamioness.addColumnBinding(colorCamion).setColumnClass(String.class).setColumnName("Color");
	    binCamioness.addColumnBinding(capacidadCamion).setColumnClass(String.class).setColumnName("Capacidad");
	    binCamioness.bind();

	}

  
	public JButton getBtnNuevo() {
		return btnNuevo;
	}

	public void setBtnNuevo(JButton btnNuevo) {
		this.btnNuevo = btnNuevo;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public void setBtnEditar(JButton btnEditar) {
		this.btnEditar = btnEditar;
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

	public JButton getBtnABuscar() {
		return btnABuscar;
	}

	public void setBtnABuscar(JButton btnABuscar) {
		this.btnABuscar = btnABuscar;
	}
 

	public JTextField getTextField() {
		return txtABuscar;
	}

	public void setTextField(JTextField textField) {
		this.txtABuscar = textField;
	}
 
	public List<Camion> getCamiones() {
		return camiones;
	}

	public void setCamiones(List<Camion> camiones) {
		this.camiones = camiones;
	}

	public void quitarNuevo() {
		// TODO Auto-generated method stub
		pnCamion.setVisible(false);
		getPnTabla().setVisible(true);
		getScrollPanel().setVisible(true);
		
	}
	
	public JTextField getTxtABuscar() {
		return txtABuscar;
	}

	public void setTxtABuscar(String txtABuscar) {
		this.txtABuscar.setText(txtABuscar);
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public JLabel getLabel() {
		return lblPlaca;
	}

	public void setLabel(JLabel label) {
		this.lblPlaca = label;
	}

	public JTextField getTextField_1() {
		return txtPlaca;
	}

	public void activarNuevoCamion() {
		// TODO Auto-generated method stub
		txtId.setVisible(false);
		getTxtPlaca().setText("");;
		getTxtMarca().setText("");;
		getTxtModelo().setText("");;
		getTxtColor().setText("");;
		getTxtCapacidad().setValue(0);;
		getTxtYear().setText("");;
		getPnTabla().setVisible(false);
		pnCamion.setVisible(true);
		add(pnCamion,BorderLayout.CENTER);
		
	}

	public void setTxtABuscar(JTextField txtABuscar) {
		this.txtABuscar = txtABuscar;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtPlaca = txtNombre;
	}

	public JTextField getTxtId() {
		return txtId;
	}

	public void setTxtId(JTextField txtId) {
		this.txtId = txtId;
	}

	@SuppressWarnings("rawtypes")
	public JTableBinding getBinCamiones() {
		return binCamioness;
	}

	@SuppressWarnings("rawtypes")
	public void setBinCamiones(JTableBinding binCamiones) {
		this.binCamioness = binCamiones;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}

	@SuppressWarnings("rawtypes")
	public JTableBinding getBinCamioness() {
		return binCamioness;
	}

	@SuppressWarnings("rawtypes")
	public void setBinCamioness(JTableBinding binCamioness) {
		this.binCamioness = binCamioness;
	}

	public JPanel getPnCamion() {
		return pnCamion;
	}

	public void setPnCamion(JPanel pnCamion) {
		this.pnCamion = pnCamion;
	}

	public JLabel getLblPlaca() {
		return lblPlaca;
	}

	public void setLblPlaca(JLabel lblPlaca) {
		this.lblPlaca = lblPlaca;
	}

	public JTextField getTxtPlaca() {
		return txtPlaca;
	}

	public void setTxtPlaca(String txtPlaca) {
		this.txtPlaca.setText(txtPlaca);
	}

	public JLabel getLblMarca() {
		return lblMarca;
	}

	public void setLblMarca(JLabel lblMarca) {
		this.lblMarca = lblMarca;
	}

	public JLabel getLblColor() {
		return lblColor;
	}

	public void setLblColor(JLabel lblColor) {
		this.lblColor = lblColor;
	}

	public JLabel getLblModelo() {
		return lblModelo;
	}

	public void setLblModelo(JLabel lblModelo) {
		this.lblModelo = lblModelo;
	}

	public JLabel getLblAnno() {
		return lblAnno;
	}

	public void setLblAnno(JLabel lblAnno) {
		this.lblAnno = lblAnno;
	}

	public JLabel getLblCapacidad() {
		return lblCapacidad;
	}

	public void setLblCapacidad(JLabel lblCapacidad) {
		this.lblCapacidad = lblCapacidad;
	}

	public JTextField getTxtMarca() {
		return txtMarca;
	}

	public void setTxtMarca(JTextField txtMarca) {
		this.txtMarca = txtMarca;
	}

	public JTextField getTxtColor() {
		return txtColor;
	}

	public void setTxtColor(JTextField txtColor) {
		this.txtColor = txtColor;
	}

	public JTextField getTxtModelo() {
		return txtModelo;
	}

	public void setTxtModelo(JTextField txtModelo) {
		this.txtModelo = txtModelo;
	}

	public JTextField getTxtYear() {
		return txtYear;
	}

	public void setTxtYear(JTextField txtYear) {
		this.txtYear = txtYear;
	}

	public JSpinner getTxtCapacidad() {
		return txtCapacidad;
	}

	public void setTxtCapacidad(JSpinner txtCapacidad) {
		this.txtCapacidad = txtCapacidad;
	}

	public void setTxtPlaca(JTextField txtPlaca) {
		this.txtPlaca = txtPlaca;
	}
	
	
}
