package santaclara.vista;

import java.util.ArrayList;
import java.util.List;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;
 
import net.miginfocom.swing.MigLayout;
import santaclara.controlador.ContAlmacenes;
import santaclara.modelo.Almacen;
import santaclara.vista.herramientas.VistaGenericaUI;
 
@SuppressWarnings("serial")
public class AlmacenesUI extends VistaGenericaUI {

	private JButton btnNuevo;
	private JButton btnEditar;
	private JButton btnEliminar;
	@SuppressWarnings("rawtypes")
	private JTableBinding binAlmacenes; 
	private JPanel pnAlmacen;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JLabel label;
	private JTextField txtUbicacion;
	private JTextField txtId;

	private List<Almacen> almacenes = new ArrayList<Almacen>();

	public AlmacenesUI(ContAlmacenes contAlmacenes,List<Almacen> almacenes) {
		super();
		this.almacenes = almacenes;
		dibujarPanelOpciones();
		dibujarBuscar();
		getBtnABuscar().addActionListener(contAlmacenes.buscar());
		dibujarBotonAtras();
		getBtnAtras().addActionListener(contAlmacenes.atras());
		dibujarPanelTabla();
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(contAlmacenes.nuevo());
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setBackground(Color.DARK_GRAY);
		btnNuevo.setIcon(new ImageIcon("img/gestion/add.png"));
		btnNuevo.setFont(new Font("Dialog", Font.BOLD, 10));
		getPnBotones().add(btnNuevo);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(contAlmacenes.modificar());
		btnEditar.setForeground(Color.WHITE);
		btnEditar.setBackground(Color.DARK_GRAY);
		btnEditar.setIcon(new ImageIcon("img/gestion/Modificara.png"));
		btnEditar.setFont(new Font("Dialog", Font.BOLD, 10));

		getPnBotones().add(btnEditar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(Color.DARK_GRAY);
		btnEliminar.setIcon(new ImageIcon("img/gestion/cancel.png"));
		btnEliminar.addActionListener(contAlmacenes.eliminar());
		btnEliminar.setFont(new Font("Dialog", Font.BOLD, 10));
		getPnBotones().add(btnEliminar);
 
		dibujarBotonSalir();
		getBtnSalir().addActionListener(contAlmacenes.salir());
		
		/********          QUITAR  NUEVO 		*****************/
		pnAlmacen = new JPanel();
		pnAlmacen.setBackground(Color.DARK_GRAY);
		pnAlmacen.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)),"EDITAR ALMACEN", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnAlmacen.setLayout(new MigLayout("", "[][grow][]", "[][][]"));

		label = new JLabel("Ubicacion:");
		label.setForeground(Color.WHITE);
		pnAlmacen.add(label, "cell 0 0");
		
		txtUbicacion = new JTextField();
		txtUbicacion.setColumns(20);
		pnAlmacen.add(txtUbicacion, "cell 1 0");
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(contAlmacenes.guardar());
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBackground(Color.DARK_GRAY);
		btnGuardar.setIcon(new ImageIcon("img/gestion/bien.png"));
		pnAlmacen.add(btnGuardar, "cell 1 1");
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(contAlmacenes.quitarNuevo());
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(Color.DARK_GRAY);
		btnCancelar.setIcon(new ImageIcon("img/gestion/cancel.png"));
		pnAlmacen.add(btnCancelar, "cell 1 1");
		
		txtId = new JTextField();
		
		/*******************       Enlazador ******************************************************/
		activarBinding(almacenes);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void activarBinding(List<Almacen> almacenes) {
		// TODO Auto-generated method stub
	 
		getPnTabla().setVisible(true);
		setTable(new JTable());
		getScrollPanel().setViewportView(getTable());
		binAlmacenes = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,almacenes,getTable());
		BeanProperty idAlmacen  = BeanProperty.create("id");
		BeanProperty ubicacionAlmacen = BeanProperty.create("ubicacion");
		binAlmacenes.addColumnBinding(idAlmacen).setColumnClass(Integer.class).setColumnName("id");;
	    binAlmacenes.addColumnBinding(ubicacionAlmacen).setColumnClass(String.class).setColumnName("Ubicacion");
	    binAlmacenes.bind();
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

	public JScrollPane getScrollPane() {
		return getScrollPanel();
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.setScrollPanel(scrollPane);
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
		getPnTabla().setVisible(true);
		getScrollPanel().setVisible(true);
		
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
		pnAlmacen.setVisible(true);
		setTxtUbicacion("");
		getPnTabla().setVisible(false);
		add(pnAlmacen,BorderLayout.CENTER);
	 
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

	@SuppressWarnings("rawtypes")
	public JTableBinding getBinAlmacenes() {
		return binAlmacenes;
	}

	@SuppressWarnings("rawtypes")
	public void setBinAlmacenes(JTableBinding binAlmacenes) {
		this.binAlmacenes = binAlmacenes;
	}
}
