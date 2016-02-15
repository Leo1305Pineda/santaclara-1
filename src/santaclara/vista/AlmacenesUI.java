package santaclara.vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;
import santaclara.controlador.ContAlmacenes;
import santaclara.vista.herramientas.VistaGenericaUI;
 
@SuppressWarnings("serial")
public class AlmacenesUI extends VistaGenericaUI {

	private JButton btnNuevo;
	private JButton btnEliminar; 
	private JPanel pnAlmacen;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JLabel label;
	private JTextField txtUbicacion;

	private ContAlmacenes contAlmacenes;
	
	public AlmacenesUI(ContAlmacenes contAlmacenes) {
		super();
		this.contAlmacenes = contAlmacenes;
		
		dibujarPanelOpciones();
		dibujarBuscar();
		getBtnABuscar().addActionListener(contAlmacenes.buscar());
		dibujarBotonAtras();
		getBtnAtras().addActionListener(contAlmacenes.atras());
		dibujarPanelTabla();
		dibujarPanelAlmacen();
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(contAlmacenes.nuevo());
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setBackground(Color.DARK_GRAY);
		btnNuevo.setIcon(new ImageIcon("img/gestion/add.png"));
		btnNuevo.setFont(new Font("Dialog", Font.BOLD, 10));
		getPnBotones().add(btnNuevo);
	
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(Color.DARK_GRAY);
		btnEliminar.setIcon(new ImageIcon("img/gestion/cancel.png"));
		btnEliminar.addActionListener(contAlmacenes.eliminar());
		btnEliminar.setFont(new Font("Dialog", Font.BOLD, 10));
		getPnBotones().add(btnEliminar);
 
		dibujarBotonSalir();
		getBtnSalir().addActionListener(contAlmacenes.salir());
		
	}
	
	public void dibujarPanelAlmacen(){
		pnAlmacen = new JPanel();
		pnAlmacen.setBackground(Color.DARK_GRAY);
		pnAlmacen.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)),"EDITAR ALMACEN", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnAlmacen.setLayout(new MigLayout());

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
		pnAlmacen.add(btnGuardar, "cell 0 1");
		
		btnCancelar = new JButton("Cancelar");
		pnAlmacen.add(btnCancelar, "cell 1 1");

		add(pnAlmacen,BorderLayout.SOUTH);
		btnCancelar.setVisible(false);
		repaint();
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

	public JPanel getPnAlmacen() {
		return pnAlmacen;
	}

	public void setPnAlmacen(JPanel pnAlmacen) {
		this.pnAlmacen = pnAlmacen;
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

	public JTextField getTxtUbicacion() {
		return txtUbicacion;
	}

	public void setTxtUbicacion(JTextField txtUbicacion) {
		this.txtUbicacion = txtUbicacion;
	}

	public ContAlmacenes getContAlmacenes() {
		return contAlmacenes;
	}

	public void setContAlmacenes(ContAlmacenes contAlmacenes) {
		this.contAlmacenes = contAlmacenes;
	}

	
}
