package santaclara.vista;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import net.miginfocom.swing.MigLayout;
import santaclara.controlador.ContCamiones;
import santaclara.modelo.Camion;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JSpinner;

@SuppressWarnings("serial")
public class CamionesUI extends JPanel {
	
	private JPanel pnOpciones;
	private JPanel panel;
	private JPanel pnCamiones;
	private JPanel panel_1;
	private JPanel pnTabla;
	
	private JButton btnNuevo;
	private JButton btnEditar;
	private JButton btnAtras;
	private JButton btnSalir;
	private JButton btnABuscar;
	private JButton btnEliminar;
	
	private JTable table;
	@SuppressWarnings("rawtypes")
	private JTableBinding binCamioness; 
	
	private JScrollPane scrollPanel;
	
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
		this.camiones = camiones;
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		pnCamiones = new JPanel();
		pnCamiones.setBackground(Color.DARK_GRAY);
		pnCamiones.setForeground(Color.DARK_GRAY);
		pnCamiones.setBounds(12, 12, 546, 415);
		pnCamiones.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Modulo Camion", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		add(pnCamiones);
		pnCamiones.setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(12, 45, 522, 42);
		pnCamiones.add(panel);
		panel.setLayout(null);
		
		pnOpciones = new JPanel();
		pnOpciones.setBounds(0, 0, 520, 34);
		panel.add(pnOpciones);
		pnOpciones.setBackground(Color.DARK_GRAY);
		pnOpciones.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Opciones", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnOpciones.setLayout(null);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(contCamiones.nuevo());
		btnNuevo.setBounds(100, 15, 103, 16);
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setBackground(Color.DARK_GRAY);
		btnNuevo.setIcon(new ImageIcon("img/gestion/add.png"));
		pnOpciones.add(btnNuevo);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(contCamiones.modificar());
		btnEditar.setBounds(207, 15, 102, 16);
		btnEditar.setForeground(Color.WHITE);
		btnEditar.setBackground(Color.DARK_GRAY);
		btnEditar.setIcon(new ImageIcon("img/gestion/Modificara.png"));
		pnOpciones.add(btnEditar);
		
		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(contCamiones.atras());
		btnAtras.setBounds(5, 15, 92, 16);
		btnAtras.setForeground(Color.WHITE);
		btnAtras.setBackground(Color.DARK_GRAY);
		btnAtras.setIcon(new ImageIcon("img/gestion/AtrasCurva.png"));
		pnOpciones.add(btnAtras);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(contCamiones.salir());
		btnSalir.setBounds(425, 15, 90, 16);
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.DARK_GRAY);
		btnSalir.setIcon(new ImageIcon("img/gestion/SalirCurva.png"));
		pnOpciones.add(btnSalir);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(Color.DARK_GRAY);
		btnEliminar.setIcon(new ImageIcon("img/gestion/cancel.png"));
		btnEliminar.addActionListener(contCamiones.eliminar());
		btnEliminar.setBounds(312, 15, 110, 16);
		pnOpciones.add(btnEliminar);
		
		pnTabla = new JPanel();
		pnTabla.setBounds(12, 85, 520, 181);
		pnCamiones.add(pnTabla);
		pnTabla.setLayout(null);
		
		scrollPanel = new JScrollPane();
		scrollPanel.setBounds(0, 0, 520, 182);
		pnTabla.add(scrollPanel);
		
		table = new JTable();
		table.setBounds(0, 0, 1, 1);
		pnTabla.add(table);
		
		panel_1 = new JPanel();
		panel_1.setForeground(Color.GRAY);
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(12, 13, 522, 30);
		pnCamiones.add(panel_1);
		panel_1.setLayout(new MigLayout("", "[grow]", "[]"));
		
		txtABuscar = new JTextField();
		txtABuscar.setForeground(Color.WHITE);
		txtABuscar.setBackground(new Color(64, 64, 64));
		panel_1.add(txtABuscar, "flowx,cell 0 0,growx");
		txtABuscar.setColumns(10);
		
		btnABuscar = new JButton("");
		btnABuscar.addActionListener(contCamiones.buscar());
		btnABuscar.setVerticalAlignment(SwingConstants.TOP);
		btnABuscar.setBackground(Color.DARK_GRAY);
		btnABuscar.setIcon(new ImageIcon("img/gestion/buscar.png"));
		panel_1.add(btnABuscar, "cell 0 0");
		
		pnCamion = new JPanel();
		pnCamion.setBackground(Color.DARK_GRAY);
		pnCamion.setBounds(12, 270, 520, 133);
		pnCamion.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)),"", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnCamiones.add(pnCamion);
		pnCamion.setLayout(null);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(contCamiones.guardar());
		btnGuardar.setBounds(395, 10, 115, 16);
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBackground(Color.DARK_GRAY);
		btnGuardar.setIcon(new ImageIcon("img/gestion/bien.png"));
		pnCamion.add(btnGuardar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarNuevo();
			}
		});
		btnCancelar.setBounds(5, 10, 120, 16);
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(Color.DARK_GRAY);
		btnCancelar.setIcon(new ImageIcon("img/gestion/cancel.png"));
		pnCamion.add(btnCancelar);
		
		lblPlaca = new JLabel("Placa:");
		lblPlaca.setForeground(Color.WHITE);
		lblPlaca.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblPlaca.setBounds(130, 5, 69, 25);
		pnCamion.add(lblPlaca);
		
		txtPlaca = new JTextField();
		txtPlaca.setColumns(10);
		txtPlaca.setBounds(200, 10, 188, 16);
		pnCamion.add(txtPlaca);
		
		txtId = new JTextField();
		txtId.setBounds(254, 12, 54, 19);
		pnCamion.add(txtId);
		txtId.setColumns(10);
		
		lblMarca = new JLabel("Marca:");
		lblMarca.setForeground(Color.WHITE);
		lblMarca.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblMarca.setBounds(5, 33, 69, 25);
		pnCamion.add(lblMarca);
		
		lblColor = new JLabel("Color:");
		lblColor.setForeground(Color.WHITE);
		lblColor.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblColor.setBounds(5, 68, 69, 25);
		pnCamion.add(lblColor);
		
		lblModelo = new JLabel("Modelo:");
		lblModelo.setForeground(Color.WHITE);
		lblModelo.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblModelo.setBounds(264, 38, 69, 25);
		pnCamion.add(lblModelo);
		
		lblAnno = new JLabel("Año:");
		lblAnno.setForeground(Color.WHITE);
		lblAnno.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblAnno.setBounds(264, 68, 69, 25);
		pnCamion.add(lblAnno);
		
		lblCapacidad = new JLabel("Capacidad:");
		lblCapacidad.setForeground(Color.WHITE);
		lblCapacidad.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblCapacidad.setBounds(5, 96, 89, 25);
		pnCamion.add(lblCapacidad);
		
		txtMarca = new JTextField();
		txtMarca.setColumns(10);
		txtMarca.setBounds(73, 38, 188, 16);
		pnCamion.add(txtMarca);
		
		txtColor = new JTextField();
		txtColor.setColumns(10);
		txtColor.setBounds(73, 70, 188, 16);
		pnCamion.add(txtColor);
		
		txtModelo = new JTextField();
		txtModelo.setColumns(10);
		txtModelo.setBounds(322, 41, 188, 16);
		pnCamion.add(txtModelo);
		
		txtYear = new JTextField();
		txtYear.setColumns(10);
		txtYear.setBounds(322, 71, 188, 16);
		pnCamion.add(txtYear);
		
		txtCapacidad = new JSpinner(new SpinnerNumberModel(0.0,0.00,Double.MAX_VALUE,0.1));
		txtCapacidad.setBounds(100, 99, 161, 16);
		((JSpinner.NumberEditor)txtCapacidad.getEditor()).getFormat().setMinimumFractionDigits(2);
		pnCamion.add(txtCapacidad);
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void activarBinding(List<Camion> camiones) {
		// TODO Auto-generated method stub
		pnTabla.setVisible(true);
		table = new JTable();
		scrollPanel.setViewportView(table);
		binCamioness = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,
    			camiones,table);
		
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


	public JPanel getPnOpciones() {
		return pnOpciones;
	}

	public void setPnOpciones(JPanel pnOpciones) {
		this.pnOpciones = pnOpciones;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JPanel getPnCamiones() {
		return pnCamiones;
	}

	public void setPnCamiones(JPanel pnCamiones) {
		this.pnCamiones = pnCamiones;
	}

	public JPanel getPanel_1() {
		return panel_1;
	}

	public void setPanel_1(JPanel panel_1) {
		this.panel_1 = panel_1;
	}

	public JPanel getPnTabla() {
		return pnTabla;
	}

	public void setPnTabla(JPanel pnTabla) {
		this.pnTabla = pnTabla;
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

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JScrollPane getScrollPane() {
		return scrollPanel;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPanel = scrollPane;
	}

	public JTextField getTextField() {
		return txtABuscar;
	}

	public void setTextField(JTextField textField) {
		this.txtABuscar = textField;
	}

	public JScrollPane getScrollPanel() {
		return scrollPanel;
	}

	public void setScrollPanel(JScrollPane scrollPanel) {
		this.scrollPanel = scrollPanel;
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
		
		pnTabla.setVisible(true);
		scrollPanel.setVisible(true);
		
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
		pnCamion.setBounds(12, 81, 520, 133);
		pnCamion.setVisible(true);
		getTxtPlaca().setText("");;
		getTxtMarca().setText("");;
		getTxtModelo().setText("");;
		getTxtColor().setText("");;
		getTxtCapacidad().setValue(0);;
		getTxtYear().setText("");;
		pnTabla.setVisible(false);
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
