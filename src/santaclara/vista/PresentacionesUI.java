package santaclara.vista;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import net.miginfocom.swing.MigLayout;
import santaclara.controlador.ContPresentaciones;
import santaclara.modelo.Presentacion;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import java.awt.Font;

@SuppressWarnings("serial")
public class PresentacionesUI extends JPanel {
	
	private JPanel pnOpciones;
	private JPanel panel;
	private JPanel pnPresentaciones;
	private JPanel panel_1;
	private JPanel pnTabla;
	
	private JButton btnNuevo;
	private JButton btnEditar;
	private JButton btnAtras;
	private JButton btnSalir;
	private JButton btnABuscar;
	
	private JTable table;
	@SuppressWarnings("rawtypes")
	private JTableBinding binPresentaciones; 
	
	private JScrollPane scrollPanel;
	
	private JTextField txtABuscar;
		
	private List<Presentacion> presentaciones = new ArrayList<Presentacion>();
	private JPanel pnPresentacion;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JLabel label;
	private JTextField txtNombre;
	private JTextField txtId;
	
	public PresentacionesUI(ContPresentaciones contPresentaciones,List<Presentacion> presentaciones) {
		this.presentaciones = presentaciones;
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		pnPresentaciones = new JPanel();
		pnPresentaciones.setBackground(Color.DARK_GRAY);
		pnPresentaciones.setForeground(Color.DARK_GRAY);
		pnPresentaciones.setBounds(12, 12, 546, 315);
		pnPresentaciones.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Modulo Presentacion", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		add(pnPresentaciones);
		pnPresentaciones.setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(12, 45, 522, 42);
		pnPresentaciones.add(panel);
		panel.setLayout(null);
		
		pnOpciones = new JPanel();
		pnOpciones.setBounds(0, 0, 520, 34);
		panel.add(pnOpciones);
		pnOpciones.setBackground(Color.DARK_GRAY);
		pnOpciones.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Opciones", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnOpciones.setLayout(null);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(contPresentaciones.nuevo());
		btnNuevo.setBounds(100, 15, 103, 16);
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setBackground(Color.DARK_GRAY);
		btnNuevo.setIcon(new ImageIcon("img/gestion/add.png"));
		pnOpciones.add(btnNuevo);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(contPresentaciones.modificar());
		btnEditar.setBounds(207, 15, 102, 16);
		btnEditar.setForeground(Color.WHITE);
		btnEditar.setBackground(Color.DARK_GRAY);
		btnEditar.setIcon(new ImageIcon("img/gestion/Modificara.png"));
		pnOpciones.add(btnEditar);
		
		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(contPresentaciones.atras());
		btnAtras.setBounds(5, 15, 92, 16);
		btnAtras.setForeground(Color.WHITE);
		btnAtras.setBackground(Color.DARK_GRAY);
		btnAtras.setIcon(new ImageIcon("img/gestion/AtrasCurva.png"));
		pnOpciones.add(btnAtras);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(contPresentaciones.salir());
		btnSalir.setBounds(425, 15, 90, 16);
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.DARK_GRAY);
		btnSalir.setIcon(new ImageIcon("img/gestion/SalirCurva.png"));
		pnOpciones.add(btnSalir);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(Color.DARK_GRAY);
		btnEliminar.setIcon(new ImageIcon("img/gestion/cancel.png"));
		btnEliminar.addActionListener(contPresentaciones.eliminar());
		btnEliminar.setBounds(312, 15, 110, 16);
		pnOpciones.add(btnEliminar);
		
		pnTabla = new JPanel();
		pnTabla.setBounds(12, 85, 520, 181);
		pnPresentaciones.add(pnTabla);
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
		pnPresentaciones.add(panel_1);
		panel_1.setLayout(new MigLayout("", "[grow]", "[]"));
		
		txtABuscar = new JTextField();
		txtABuscar.setForeground(Color.WHITE);
		txtABuscar.setBackground(new Color(64, 64, 64));
		panel_1.add(txtABuscar, "flowx,cell 0 0,growx");
		txtABuscar.setColumns(10);
		
		btnABuscar = new JButton("");
		btnABuscar.addActionListener(contPresentaciones.buscar());
		btnABuscar.setVerticalAlignment(SwingConstants.TOP);
		btnABuscar.setBackground(Color.DARK_GRAY);
		btnABuscar.setIcon(new ImageIcon("img/gestion/buscar.png"));
		panel_1.add(btnABuscar, "cell 0 0");
		
		pnPresentacion = new JPanel();
		pnPresentacion.setBackground(Color.DARK_GRAY);
		pnPresentacion.setBounds(12, 270, 520, 30);
		pnPresentacion.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)),"", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnPresentaciones.add(pnPresentacion);
		pnPresentacion.setLayout(null);
		pnPresentacion.setVisible(false);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(contPresentaciones.guardar());
		btnGuardar.setBounds(395, 10, 115, 16);
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBackground(Color.DARK_GRAY);
		btnGuardar.setIcon(new ImageIcon("img/gestion/bien.png"));
		pnPresentacion.add(btnGuardar);
		
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
		pnPresentacion.add(btnCancelar);
		
		label = new JLabel("Nombre:");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		label.setBounds(130, 5, 69, 25);
		pnPresentacion.add(label);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(200, 10, 188, 16);
		pnPresentacion.add(txtNombre);
		
		txtId = new JTextField();
		txtId.setBounds(254, 12, 54, 19);
		pnPresentacion.add(txtId);
		txtId.setColumns(10);
		
		activarBinding(presentaciones);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void activarBinding(List<Presentacion> presentaciones) {
		// TODO Auto-generated method stub
		pnTabla.setVisible(true);
		table = new JTable();
		scrollPanel.setViewportView(table);
		binPresentaciones = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,presentaciones,table);
		
		BeanProperty idPresentacion  = BeanProperty.create("id");
		BeanProperty materialPresentacion = BeanProperty.create("material");

		binPresentaciones.addColumnBinding(idPresentacion).setColumnClass(Integer.class).setColumnName("id");;
	    binPresentaciones.addColumnBinding(materialPresentacion).setColumnClass(String.class).setColumnName("Material");
	    binPresentaciones.bind();

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

	public JPanel getPnPresentacion() {
		return pnPresentaciones;
	}

	public void setPnPresentacion(JPanel pnPresentacion) {
		this.pnPresentaciones = pnPresentacion;
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

	public List<Presentacion> getPresentaciones() {
		return presentaciones;
	}

	public void setPresentaciones(List<Presentacion> presentaciones) {
		this.presentaciones = presentaciones;
	}

	public void quitarNuevo() {
		// TODO Auto-generated method stub
		pnPresentacion.setVisible(false);
		
		pnTabla.setVisible(true);
		scrollPanel.setVisible(true);
	}

	public JPanel getPnPresentaciones() {
		return pnPresentaciones;
	}

	public void setPnPresentaciones(JPanel pnPresentaciones) {
		this.pnPresentaciones = pnPresentaciones;
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
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public JTextField getTextField_1() {
		return txtNombre;
	}

	public void setTxtNombre(String Nombre) {
		this.txtNombre.setText(Nombre);
	}

	public void activarNuevoPresentacion() {
		// TODO Auto-generated method stub
		txtId.setVisible(false);
		pnPresentacion.setBounds(12, 81, 520, 30);
		pnPresentacion.setVisible(true);
		setTxtNombre("");
		pnTabla.setVisible(false);
	}

	public void setTxtABuscar(JTextField txtABuscar) {
		this.txtABuscar = txtABuscar;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JTextField getTxtId() {
		return txtId;
	}

	public void setTxtId(JTextField txtId) {
		this.txtId = txtId;
	}

	@SuppressWarnings("rawtypes")
	public JTableBinding getBinPresentaciones() {
		return binPresentaciones;
	}

	@SuppressWarnings("rawtypes")
	public void setBinPresentaciones(JTableBinding binPresentaciones) {
		this.binPresentaciones = binPresentaciones;
	}
}
