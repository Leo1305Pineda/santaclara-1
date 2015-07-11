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
import santaclara.controlador.ContAlmacenes;
import santaclara.modelo.Almacen;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import java.awt.Font;

public class AlmacenesUI extends JPanel {
	
	private JPanel pnOpciones;
	private JPanel panel;
	private JPanel pnAlmacenes;
	private JPanel panel_1;
	private JPanel pnTabla;
	
	private JButton btnNuevo;
	private JButton btnEditar;
	private JButton btnAtras;
	private JButton btnSalir;
	private JButton btnABuscar;
	private JButton btnEliminar;
	
	private JTable table;
	private JTableBinding binAlmacenes; 
	
	private JScrollPane scrollPanel;
	
	private JTextField txtABuscar;
		
	private List<Almacen> almacenes = new ArrayList<Almacen>();
	private JPanel pnAlmacen;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JLabel label;
	private JTextField txtUbicacion;
	private JTextField txtId;
	
	public AlmacenesUI(ContAlmacenes contAlmacenes,List<Almacen> almacenes) {
		this.almacenes = almacenes;
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		pnAlmacenes = new JPanel();
		pnAlmacenes.setBackground(Color.DARK_GRAY);
		pnAlmacenes.setForeground(Color.DARK_GRAY);
		pnAlmacenes.setBounds(12, 12, 546, 315);
		pnAlmacenes.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Modulo Almacen", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		add(pnAlmacenes);
		pnAlmacenes.setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(12, 45, 522, 42);
		pnAlmacenes.add(panel);
		panel.setLayout(null);
		
		pnOpciones = new JPanel();
		pnOpciones.setBounds(0, 0, 520, 34);
		panel.add(pnOpciones);
		pnOpciones.setBackground(Color.DARK_GRAY);
		pnOpciones.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Opciones", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnOpciones.setLayout(null);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(contAlmacenes.nuevo());
		btnNuevo.setBounds(100, 15, 103, 16);
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setBackground(Color.DARK_GRAY);
		btnNuevo.setIcon(new ImageIcon("img/gestion/add.png"));
		pnOpciones.add(btnNuevo);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(contAlmacenes.modificar());
		btnEditar.setBounds(207, 15, 102, 16);
		btnEditar.setForeground(Color.WHITE);
		btnEditar.setBackground(Color.DARK_GRAY);
		btnEditar.setIcon(new ImageIcon("img/gestion/Modificara.png"));
		pnOpciones.add(btnEditar);
		
		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(contAlmacenes.atras());
		btnAtras.setBounds(5, 15, 92, 16);
		btnAtras.setForeground(Color.WHITE);
		btnAtras.setBackground(Color.DARK_GRAY);
		btnAtras.setIcon(new ImageIcon("img/gestion/AtrasCurva.png"));
		pnOpciones.add(btnAtras);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(contAlmacenes.salir());
		btnSalir.setBounds(425, 15, 90, 16);
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.DARK_GRAY);
		btnSalir.setIcon(new ImageIcon("img/gestion/SalirCurva.png"));
		pnOpciones.add(btnSalir);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(Color.DARK_GRAY);
		btnEliminar.setIcon(new ImageIcon("img/gestion/cancel.png"));
		btnEliminar.addActionListener(contAlmacenes.eliminar());
		btnEliminar.setBounds(312, 15, 110, 16);
		pnOpciones.add(btnEliminar);
		
		pnTabla = new JPanel();
		pnTabla.setBounds(12, 85, 520, 181);
		pnAlmacenes.add(pnTabla);
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
		pnAlmacenes.add(panel_1);
		panel_1.setLayout(new MigLayout("", "[grow]", "[]"));
		
		txtABuscar = new JTextField();
		txtABuscar.setForeground(Color.WHITE);
		txtABuscar.setBackground(new Color(64, 64, 64));
		panel_1.add(txtABuscar, "flowx,cell 0 0,growx");
		txtABuscar.setColumns(10);
		
		btnABuscar = new JButton("");
		btnABuscar.addActionListener(contAlmacenes.buscar());
		btnABuscar.setVerticalAlignment(SwingConstants.TOP);
		btnABuscar.setBackground(Color.DARK_GRAY);
		btnABuscar.setIcon(new ImageIcon("img/gestion/buscar.png"));
		panel_1.add(btnABuscar, "cell 0 0");
		
		pnAlmacen = new JPanel();
		pnAlmacen.setBackground(Color.DARK_GRAY);
		pnAlmacen.setBounds(12, 270, 520, 30);
		pnAlmacen.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)),"", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnAlmacenes.add(pnAlmacen);
		pnAlmacen.setLayout(null);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(contAlmacenes.guardar());
		btnGuardar.setBounds(395, 10, 115, 16);
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBackground(Color.DARK_GRAY);
		btnGuardar.setIcon(new ImageIcon("img/gestion/bien.png"));
		pnAlmacen.add(btnGuardar);
		
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
		pnAlmacen.add(btnCancelar);
		
		label = new JLabel("Ubicacion:");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		label.setBounds(127, 5, 80, 25);
		pnAlmacen.add(label);
		
		txtUbicacion = new JTextField();
		txtUbicacion.setColumns(10);
		txtUbicacion.setBounds(205, 10, 188, 16);
		pnAlmacen.add(txtUbicacion);
		
		txtId = new JTextField();
		txtId.setBounds(254, 12, 54, 19);
		pnAlmacen.add(txtId);
		txtId.setColumns(10);
	}
	
	public void activarBinding(List<Almacen> almacenes) {
		// TODO Auto-generated method stub
		pnTabla.setVisible(true);
		table = new JTable();
		scrollPanel.setViewportView(table);
		binAlmacenes = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,
    			almacenes,table);
		
		BeanProperty idAlmacen  = BeanProperty.create("id");
		BeanProperty ubicacionAlmacen = BeanProperty.create("ubicacion");
	    
	    
	    binAlmacenes.addColumnBinding(idAlmacen).setColumnClass(Integer.class).setColumnName("id");;
	    binAlmacenes.addColumnBinding(ubicacionAlmacen).setColumnClass(String.class).setColumnName("Ubicacion");
	    
	    binAlmacenes.bind();

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

	public JPanel getPnAlmacen() {
		return pnAlmacenes;
	}

	public void setPnAlmacen(JPanel pnAlmacen) {
		this.pnAlmacenes = pnAlmacen;
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

	public List<Almacen> getAlmacenes() {
		return almacenes;
	}

	public void setAlmacenes(List<Almacen> presentaciones) {
		this.almacenes = presentaciones;
	}

	public void quitarNuevo() {
		// TODO Auto-generated method stub
		pnAlmacen.setVisible(false);
		
		pnTabla.setVisible(true);
		scrollPanel.setVisible(true);
		
	}

	public JPanel getPnAlmacenes() {
		return pnAlmacenes;
	}

	public void setPnAlmacenes(JPanel pnAlmacenes) {
		this.pnAlmacenes = pnAlmacenes;
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
		return txtUbicacion;
	}

	public void setTxtUbicacion(String ubicacion) {
		this.txtUbicacion.setText(ubicacion);
	}

	public void activarNuevoAlmacen() {
		// TODO Auto-generated method stub
		txtId.setVisible(false);
		pnAlmacen.setBounds(12, 81, 520, 30);
		pnAlmacen.setVisible(true);
		setTxtUbicacion("");
		pnTabla.setVisible(false);
	}

	public void setTxtABuscar(JTextField txtABuscar) {
		this.txtABuscar = txtABuscar;
	}

	public JTextField getTxtUbicacion() {
		return txtUbicacion;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtUbicacion = txtNombre;
	}

	public JTextField getTxtId() {
		return txtId;
	}

	public void setTxtId(JTextField txtId) {
		this.txtId = txtId;
	}

	public JTableBinding getBinAlmacenes() {
		return binAlmacenes;
	}

	public void setBinAlmacenes(JTableBinding binAlmacenes) {
		this.binAlmacenes = binAlmacenes;
	}
}
