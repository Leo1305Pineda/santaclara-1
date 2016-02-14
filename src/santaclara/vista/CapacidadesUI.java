package santaclara.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;
import santaclara.controlador.ContCapacidades;
import santaclara.vista.herramientas.VistaGenericaUI;

@SuppressWarnings("serial")
public class CapacidadesUI extends VistaGenericaUI {

	private JButton btnNuevo;
	private JButton btnEliminar; 
		
	private JPanel pnCapacidad;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JLabel label;
	private JTextField txtVolumen;
	private JTextField txtId;
	
	private ContCapacidades contCapacidades;
	
	public CapacidadesUI(ContCapacidades contCapacidades) {
		this.contCapacidades = contCapacidades;
		
		dibujarPanelOpciones();
		dibujarBuscar();
		getBtnABuscar().addActionListener(contCapacidades.buscar());
		dibujarPanelTabla();
		dibujarBotonAtras();
		getBtnAtras().addActionListener(contCapacidades.atras());
		dibujarPanelCapacidades();
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(contCapacidades.nuevo());
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setBackground(Color.DARK_GRAY);
		btnNuevo.setIcon(new ImageIcon("img/gestion/add.png"));
		getPnBotones().add(btnNuevo);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(Color.DARK_GRAY);
		btnEliminar.setIcon(new ImageIcon("img/gestion/cancel.png"));
		btnEliminar.addActionListener(contCapacidades.eliminar());
		getPnBotones().add(btnEliminar);
		
		dibujarBotonSalir();
		getBtnSalir().addActionListener(contCapacidades.salir());
		
	}
	
	public void dibujarPanelCapacidades(){
		pnCapacidad = new JPanel();
		pnCapacidad.setBackground(Color.DARK_GRAY);
		pnCapacidad.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)),"", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnCapacidad.setLayout(new MigLayout());;
		
		label = new JLabel("Volumen:");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		pnCapacidad.add(label,"cell 0 0");
		
		txtVolumen = new JTextField();
		txtVolumen.setColumns(10);
		pnCapacidad.add(txtVolumen,"cell 1 0");
		
		txtId = new JTextField();
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(contCapacidades.guardar());
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBackground(Color.DARK_GRAY);
		btnGuardar.setIcon(new ImageIcon("img/gestion/bien.png"));
		pnCapacidad.add(btnGuardar,"cell 2 0");
		
		btnCancelar = new JButton("Cancelar");
		pnCapacidad.add(btnCancelar,"cell 3 0");
		
		add(pnCapacidad,BorderLayout.SOUTH);
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

	public JPanel getPnCapacidad() {
		return pnCapacidad;
	}

	public void setPnCapacidad(JPanel pnCapacidad) {
		this.pnCapacidad = pnCapacidad;
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

	public JTextField getTxtVolumen() {
		return txtVolumen;
	}

	public void setTxtVolumen(JTextField txtVolumen) {
		this.txtVolumen = txtVolumen;
	}

	public JTextField getTxtId() {
		return txtId;
	}

	public void setTxtId(JTextField txtId) {
		this.txtId = txtId;
	}

	public ContCapacidades getContCapacidades() {
		return contCapacidades;
	}

	public void setContCapacidades(ContCapacidades contCapacidades) {
		this.contCapacidades = contCapacidades;
	}
}
