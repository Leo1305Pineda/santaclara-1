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
import santaclara.controlador.ContSabores;
import santaclara.vista.herramientas.VistaGenericaUI;

import javax.swing.JLabel;

import java.awt.Font;

@SuppressWarnings("serial")
public class SaboresUI  extends VistaGenericaUI  {
	
	private JPanel pnSabor;

	private JButton btnNuevo;
	private JButton btnEliminar;
	private JButton btnGuardar;
	private JButton btnCancelar;
	
	private JLabel lblSabor;
	
	private JTextField txtSabor;
	private JTextField txtId;
	private ContSabores contSabores;
	
	public SaboresUI(ContSabores contSabores) {
		super();
		this.contSabores = contSabores;
		dibujarPanelOpciones();
		dibujarBuscar();
		getBtnABuscar().addActionListener(contSabores.buscar());
		dibujarPanelTabla();
		dibujarBotonAtras();
		getBtnAtras().addActionListener(contSabores.atras());
		dibujarPanelSabor();
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(contSabores.nuevo());
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setBackground(Color.DARK_GRAY);
		btnNuevo.setIcon(new ImageIcon("img/gestion/add.png"));
		getPnBotones().add(btnNuevo);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(Color.DARK_GRAY);
		btnEliminar.setIcon(new ImageIcon("img/gestion/cancel.png"));
		btnEliminar.addActionListener(contSabores.eliminar());
		getPnBotones().add(btnEliminar);
		
		dibujarBotonSalir();
		getBtnSalir().addActionListener(contSabores.salir());
	}

	public void dibujarPanelSabor(){
		pnSabor = new JPanel();
		pnSabor.setBackground(Color.DARK_GRAY);
		pnSabor.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)),"", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnSabor.setLayout(new MigLayout());
		
		lblSabor = new JLabel("Sabor:");
		lblSabor.setForeground(Color.WHITE);
		lblSabor.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		pnSabor.add(lblSabor,"cell 0 0");
		
		txtSabor = new JTextField();
		txtSabor.setColumns(25);
		pnSabor.add(txtSabor,"cell 1 0");
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(contSabores.guardar());
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBackground(Color.DARK_GRAY);
		btnGuardar.setIcon(new ImageIcon("img/gestion/bien.png"));
		pnSabor.add(btnGuardar,"cell 2 0");
		
		add(pnSabor,BorderLayout.SOUTH);
		
		btnCancelar = new JButton("Cancelar");
		pnSabor.add(btnCancelar, "cell 3 0");
		btnCancelar.setVisible(false);
		repaint();
	}

	public JPanel getPnSabor() {
		return pnSabor;
	}

	public void setPnSabor(JPanel pnSabor) {
		this.pnSabor = pnSabor;
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

	public JLabel getLblSabor() {
		return lblSabor;
	}

	public void setLblSabor(JLabel lblSabor) {
		this.lblSabor = lblSabor;
	}

	public JTextField getTxtSabor() {
		return txtSabor;
	}

	public void setTxtSabor(JTextField txtSabor) {
		this.txtSabor = txtSabor;
	}

	public JTextField getTxtId() {
		return txtId;
	}

	public void setTxtId(JTextField txtId) {
		this.txtId = txtId;
	}

	public ContSabores getContSabores() {
		return contSabores;
	}

	public void setContSabores(ContSabores contSabores) {
		this.contSabores = contSabores;
	}
	
 }
