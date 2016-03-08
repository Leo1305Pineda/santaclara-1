/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.vista;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import java.awt.BorderLayout;
import java.awt.Color;

import net.miginfocom.swing.MigLayout;
import santaclara.Servicio.ServicioCamion;
import santaclara.controlador.ContCamiones;
import santaclara.modelo.Camion;
import santaclara.vista.herramientas.VistaGenericaUI;
import javax.swing.JLabel;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JSpinner;

import beans.JDibujarTabla;
import beans.jCampoBuscar;

@SuppressWarnings("serial")
public class CamionesUI extends VistaGenericaUI {

	private JButton btnNuevo;
	private JButton btnEliminar;
	  
	private JSpinner txtCapacidad;
	private JPanel pnCamion;
	private JButton btnGuardar;
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

	private ContCamiones contCamiones;
	
	public CamionesUI(ContCamiones contCamiones) throws Exception {
		super();
		this.contCamiones = contCamiones;
		dibujarPanelOpciones();
		
		List<Camion> catalogo = new ServicioCamion().getCamiones();
		List<jCampoBuscar> campos = new ArrayList<jCampoBuscar>();
		campos.add(jCampoBuscar.crearCampoBusquedad("id","getId"));
		campos.add(jCampoBuscar.crearCampoBusquedad("placa","getPlaca"));
		campos.add(jCampoBuscar.crearCampoBusquedad("Color","getColor"));
		campos.add(jCampoBuscar.crearCampoBusquedad("Capacidad","getCapacidad"));
		campos.add(jCampoBuscar.crearCampoBusquedad("Modelo","getModelo"));
		campos.add(jCampoBuscar.crearCampoBusquedad("Marca","getMarca"));
		campos.add(jCampoBuscar.crearCampoBusquedad("Año","getAno"));
		dibujarBuscar(campos,catalogo,new JDibujarTabla() {
			
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void dibujarTabla(List resultados) {
				// TODO Auto-generated method stub
				CamionesUI.this.contCamiones.activarBinding(resultados);
			}
		});

		dibujarBotonAtras();
		getBtnAtras().addActionListener(contCamiones.atras());
		dibujarPanelTabla();
		dibujarPanelCamion();
	
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(contCamiones.nuevo());
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setBackground(Color.DARK_GRAY);
		btnNuevo.setIcon(new ImageIcon("img/gestion/add.png"));
		getPnBotones().add(btnNuevo);
			
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(Color.DARK_GRAY);
		btnEliminar.setIcon(new ImageIcon("img/gestion/cancel.png"));
		btnEliminar.addActionListener(contCamiones.eliminar());
		getPnBotones().add(btnEliminar);
		
		dibujarBotonSalir();
		getBtnSalir().addActionListener(contCamiones.salir());
	}
	
	public void dibujarPanelCamion(){
		pnCamion = new JPanel();
		pnCamion.setBackground(Color.DARK_GRAY);
		pnCamion.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)),"EDITAR CAMION ", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnCamion.setLayout(new MigLayout());
		
		lblPlaca = new JLabel("Placa:");
		lblPlaca.setForeground(Color.WHITE);
		pnCamion.add(lblPlaca);
		
		txtPlaca = new JTextField();
		txtPlaca.setColumns(10);
		pnCamion.add(txtPlaca);
		
		txtId = new JTextField();
		txtId.setColumns(10);
		
		lblMarca = new JLabel("Marca:");
		lblMarca.setForeground(Color.WHITE);
		pnCamion.add(lblMarca);
		
		txtMarca = new JTextField();
		txtMarca.setColumns(10);
		pnCamion.add(txtMarca,"wrap");
		
		lblColor = new JLabel("Color:");
		lblColor.setForeground(Color.WHITE);
		lblColor.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		pnCamion.add(lblColor);
		
		txtColor = new JTextField();
		txtColor.setColumns(10);
		pnCamion.add(txtColor);
		
		lblModelo = new JLabel("Modelo:");
		lblModelo.setForeground(Color.WHITE);
		lblModelo.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		pnCamion.add(lblModelo);
		
		txtModelo = new JTextField();
		txtModelo.setColumns(10);
		pnCamion.add(txtModelo,"wrap");
		
		lblAnno = new JLabel("Año:");
		lblAnno.setForeground(Color.WHITE);
		lblAnno.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		pnCamion.add(lblAnno);
		
		txtYear = new JTextField();
		txtYear.setColumns(10);
		pnCamion.add(txtYear);
		
		lblCapacidad = new JLabel("Capacidad:");
		lblCapacidad.setForeground(Color.WHITE);
		lblCapacidad.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		pnCamion.add(lblCapacidad);
		
		txtCapacidad = new JSpinner(new SpinnerNumberModel(0.0,0.00,Double.MAX_VALUE,0.1));
		((JSpinner.NumberEditor)txtCapacidad.getEditor()).getFormat().setMinimumFractionDigits(2);
		pnCamion.add(txtCapacidad,"wrap, width 80:80:80 ");
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(contCamiones.guardar());
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBackground(Color.DARK_GRAY);
		btnGuardar.setIcon(new ImageIcon("img/gestion/bien.png"));
		pnCamion.add(btnGuardar);
		
		add(pnCamion,BorderLayout.SOUTH);
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

	public JSpinner getTxtCapacidad() {
		return txtCapacidad;
	}

	public void setTxtCapacidad(JSpinner txtCapacidad) {
		this.txtCapacidad = txtCapacidad;
	}

	public JPanel getPnCamion() {
		return pnCamion;
	}

	public void setPnCamion(JPanel pnCamion) {
		this.pnCamion = pnCamion;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
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

	public void setTxtPlaca(JTextField txtPlaca) {
		this.txtPlaca = txtPlaca;
	}

	public JTextField getTxtId() {
		return txtId;
	}

	public void setTxtId(JTextField txtId) {
		this.txtId = txtId;
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

	public ContCamiones getContCamiones() {
		return contCamiones;
	}

	public void setContCamiones(ContCamiones contCamiones) {
		this.contCamiones = contCamiones;
	}
 	
}
