package santaclara.vista;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color;

import net.miginfocom.swing.MigLayout;
import santaclara.controlador.ContPresentaciones;
import santaclara.vista.herramientas.VistaGenericaUI;


import javax.swing.JLabel;

import java.awt.Font;

@SuppressWarnings("serial")
public class PresentacionesUI extends VistaGenericaUI {
	
	private JButton btnNuevo; 	
	private JButton btnEliminar;
	private JButton btnCancelar;
	private JButton btnGuardar;
	
	private JPanel pnPresentacion;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private ContPresentaciones contPresentaciones;
	
	public PresentacionesUI(ContPresentaciones contPresentaciones) {
		this.contPresentaciones = contPresentaciones;
		
		dibujarPanelOpciones();
		dibujarBuscar();
		getBtnABuscar().addActionListener(contPresentaciones.buscar());
		dibujarPanelTabla();
		dibujarBotonAtras();
		getBtnAtras().addActionListener(contPresentaciones.atras());
		dibujarPanelPresentaciones();
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(contPresentaciones.nuevo());
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setBackground(Color.DARK_GRAY);
		btnNuevo.setIcon(new ImageIcon("img/gestion/add.png"));
		getPnBotones().add(btnNuevo);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(Color.DARK_GRAY);
		btnEliminar.setIcon(new ImageIcon("img/gestion/cancel.png"));
		btnEliminar.addActionListener(contPresentaciones.eliminar());
		getPnBotones().add(btnEliminar);
		
		dibujarBotonSalir();
		getBtnSalir().addActionListener(contPresentaciones.salir());
	}
	
	public void dibujarPanelPresentaciones(){
		pnPresentacion = new JPanel();
		pnPresentacion.setBackground(Color.DARK_GRAY);
		pnPresentacion.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)),"", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnPresentacion.setLayout(new MigLayout());
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		pnPresentacion.add(lblNombre,"cell 0 0");
		
		txtNombre = new JTextField();
		txtNombre.setColumns(25);
		pnPresentacion.add(txtNombre,"cell 1 0");
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(contPresentaciones.guardar());
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBackground(Color.DARK_GRAY);
		btnGuardar.setIcon(new ImageIcon("img/gestion/bien.png"));
		pnPresentacion.add(btnGuardar,"cell 2 0");
		
		btnCancelar = new JButton("Cancelar");
		pnPresentacion.add(btnCancelar,"cell 3 0");
		
		add(pnPresentacion,BorderLayout.SOUTH);
		btnCancelar.setVisible(false);
		repaint();
	}

	public JButton getBtnNuevo() {
		return btnNuevo;
	}

	public void setBtnNuevo(JButton btnNuevo) {
		this.btnNuevo = btnNuevo;
	}

	public JPanel getPnPresentacion() {
		return pnPresentacion;
	}

	public void setPnPresentacion(JPanel pnPresentacion) {
		this.pnPresentacion = pnPresentacion;
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

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public void setLblNombre(JLabel lblNombre) {
		this.lblNombre = lblNombre;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public ContPresentaciones getContPresentaciones() {
		return contPresentaciones;
	}

	public void setContPresentaciones(ContPresentaciones contPresentaciones) {
		this.contPresentaciones = contPresentaciones;
	}
	
}
