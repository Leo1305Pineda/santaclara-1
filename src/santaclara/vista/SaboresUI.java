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
import santaclara.controlador.ContSabores;
import santaclara.dao.impl.PresentacionDAO;
import santaclara.modelo.Sabor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import java.awt.Font;

public class SaboresUI extends JPanel {
	
	private JPanel pnOpciones;
	private JPanel panel;
	private JPanel pnSabor;
	private JPanel panel_1;
	private JPanel pnTabla;
	
	private JButton btnNuevo;
	private JButton btnEditar;
	private JButton btnAtras;
	private JButton btnSalir;
	private JButton btnABuscar;
	private JButton btnEliminar;
	
	private JTable table;
	private JTableBinding binSabores; 
	
	private JScrollPane scrollPanel;
	
	private JTextField txtABuscar;
		
	private List<Sabor> sabores = new ArrayList<Sabor>();
	private JPanel pnSabores;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JLabel lblSabor;
	private JTextField txtSabor;
	private JTextField txtId;
	
	public SaboresUI(ContSabores contSabores,List<Sabor> sabores) {
		this.sabores = sabores;
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		pnSabores = new JPanel();
		pnSabores.setBackground(Color.DARK_GRAY);
		pnSabores.setForeground(Color.DARK_GRAY);
		pnSabores.setBounds(12, 12, 546, 315);
		pnSabores.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Modulo Sabor", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		add(pnSabores);
		pnSabores.setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(12, 45, 522, 42);
		pnSabores.add(panel);
		panel.setLayout(null);
		
		pnOpciones = new JPanel();
		pnOpciones.setBounds(0, 0, 520, 34);
		panel.add(pnOpciones);
		pnOpciones.setBackground(Color.DARK_GRAY);
		pnOpciones.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Opciones", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnOpciones.setLayout(null);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(contSabores.nuevo());
		btnNuevo.setBounds(100, 15, 103, 16);
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setBackground(Color.DARK_GRAY);
		btnNuevo.setIcon(new ImageIcon("img/gestion/add.png"));
		pnOpciones.add(btnNuevo);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(contSabores.modificar());
		btnEditar.setBounds(207, 15, 102, 16);
		btnEditar.setForeground(Color.WHITE);
		btnEditar.setBackground(Color.DARK_GRAY);
		btnEditar.setIcon(new ImageIcon("img/gestion/Modificara.png"));
		pnOpciones.add(btnEditar);
		
		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(contSabores.atras());
		btnAtras.setBounds(5, 15, 92, 16);
		btnAtras.setForeground(Color.WHITE);
		btnAtras.setBackground(Color.DARK_GRAY);
		btnAtras.setIcon(new ImageIcon("img/gestion/AtrasCurva.png"));
		pnOpciones.add(btnAtras);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(contSabores.salir());
		btnSalir.setBounds(425, 15, 90, 16);
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.DARK_GRAY);
		btnSalir.setIcon(new ImageIcon("img/gestion/SalirCurva.png"));
		pnOpciones.add(btnSalir);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(Color.DARK_GRAY);
		btnEliminar.setIcon(new ImageIcon("img/gestion/cancel.png"));
		btnEliminar.addActionListener(contSabores.eliminar());
		btnEliminar.setBounds(312, 15, 110, 16);
		pnOpciones.add(btnEliminar);
		
		pnTabla = new JPanel();
		pnTabla.setBounds(12, 85, 520, 181);
		pnSabores.add(pnTabla);
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
		pnSabores.add(panel_1);
		panel_1.setLayout(new MigLayout("", "[grow]", "[]"));
		
		txtABuscar = new JTextField();
		txtABuscar.setForeground(Color.WHITE);
		txtABuscar.setBackground(new Color(64, 64, 64));
		panel_1.add(txtABuscar, "flowx,cell 0 0,growx");
		txtABuscar.setColumns(10);
		
		btnABuscar = new JButton("");
		btnABuscar.addActionListener(contSabores.buscar());
		btnABuscar.setVerticalAlignment(SwingConstants.TOP);
		btnABuscar.setBackground(Color.DARK_GRAY);
		btnABuscar.setIcon(new ImageIcon("img/gestion/buscar.png"));
		panel_1.add(btnABuscar, "cell 0 0");
		
		pnSabor = new JPanel();
		pnSabor.setBackground(Color.DARK_GRAY);
		pnSabor.setBounds(12, 270, 520, 30);
		pnSabor.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)),"", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnSabores.add(pnSabor);
		pnSabor.setLayout(null);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(contSabores.guardar());
		btnGuardar.setBounds(395, 10, 115, 16);
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBackground(Color.DARK_GRAY);
		btnGuardar.setIcon(new ImageIcon("img/gestion/bien.png"));
		pnSabor.add(btnGuardar);
		
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
		pnSabor.add(btnCancelar);
		
		lblSabor = new JLabel("Sabor:");
		lblSabor.setForeground(Color.WHITE);
		lblSabor.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblSabor.setBounds(130, 5, 69, 25);
		pnSabor.add(lblSabor);
		
		txtSabor = new JTextField();
		txtSabor.setColumns(10);
		txtSabor.setBounds(200, 10, 188, 16);
		pnSabor.add(txtSabor);
		
		txtId = new JTextField();
		txtId.setBounds(254, 12, 54, 19);
		pnSabor.add(txtId);
		txtId.setColumns(10);
	}
	
	public void activarBinding(List<Sabor> sabores) {
		// TODO Auto-generated method stub
		pnTabla.setVisible(true);
		table = new JTable();
		scrollPanel.setViewportView(table);
		binSabores = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,
    			sabores,table);
		
		BeanProperty idSabor  = BeanProperty.create("id");
		BeanProperty Sabor = BeanProperty.create("sabor");
	    
	    
	    binSabores.addColumnBinding(idSabor).setColumnClass(Integer.class).setColumnName("id");;
	    binSabores.addColumnBinding(Sabor).setColumnClass(String.class).setColumnName("Sabor");
	    
	    binSabores.bind();

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

	public JPanel getPnSabor() {
		return pnSabores;
	}

	public void setPnPresentacion(JPanel pnSabores) {
		this.pnSabores = pnSabores;
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

	public List<Sabor> getSabores() {
		return sabores;
	}

	public void setPresentaciones(List<Sabor> sabores) {
		this.sabores = sabores;
	}

	public void quitarNuevo() {
		// TODO Auto-generated method stub
		pnSabor.setVisible(false);
		
		pnTabla.setVisible(true);
		scrollPanel.setVisible(true);
		
	}

	public JPanel getPnSabores() {
		return pnSabores;
	}

	public void setPnSabores(JPanel pnSabores) {
		this.pnSabores = pnSabores;
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
		return lblSabor;
	}

	public void setLabel(JLabel label) {
		this.lblSabor = label;
	}

	public JTextField getTextField_1() {
		return txtSabor;
	}

	public void setTxtSabor(String sabor) {
		this.txtSabor.setText(sabor);
	}

	public void activarNuevoSabor() {
		// TODO Auto-generated method stub
		txtId.setVisible(false);
		pnSabor.setBounds(12, 81, 520, 30);
		pnSabor.setVisible(true);
		setTxtSabor("");
		pnTabla.setVisible(false);
	}

	public void setTxtABuscar(JTextField txtABuscar) {
		this.txtABuscar = txtABuscar;
	}

	public JTextField getTxtSabor() {
		return txtSabor;
	}

	public JTextField getTxtId() {
		return txtId;
	}

	public void setTxtId(JTextField txtId) {
		this.txtId = txtId;
	}

	public JTableBinding getBinPresentaciones() {
		return binSabores;
	}

	public void setBinPresentaciones(JTableBinding binPresentaciones) {
		this.binSabores = binPresentaciones;
	}
}
