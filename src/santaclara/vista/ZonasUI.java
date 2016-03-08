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

import java.awt.BorderLayout;
import java.awt.Color;

import net.miginfocom.swing.MigLayout;
import santaclara.Servicio.ServicioZona;
import santaclara.controlador.ContZonas;
import santaclara.modelo.Zona;
import santaclara.vista.herramientas.VistaGenericaUI;

import javax.swing.JLabel;

import beans.JDibujarTabla;
import beans.jCampoBuscar;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class ZonasUI extends VistaGenericaUI {
	
	private JButton btnNuevo; 	
	private JButton btnEliminar;
	private JButton btnCancelar;
	private JButton btnGuardar;
	
	private JPanel pnZona;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private ContZonas contZonas;
	
	public ZonasUI(ContZonas contZonas) throws Exception {
		this.contZonas = contZonas;
		
		dibujarPanelOpciones();
		List<Zona> auxiliar = new ServicioZona().getZonas(); 
		List<jCampoBuscar> campos = new ArrayList<jCampoBuscar>();
		campos.add(jCampoBuscar.crearCampoBusquedad("Id", "getId"));
		campos.add(jCampoBuscar.crearCampoBusquedad("Nombre", "getDescripcion"));
		dibujarBuscar(campos, auxiliar, new JDibujarTabla() {
			
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void dibujarTabla(List arg0) {
				// TODO Auto-generated method stub
				ZonasUI.this.contZonas.activarBinding(arg0);
			}
		});
		
		dibujarPanelTabla();
		dibujarBotonAtras();
		getBtnAtras().addActionListener(contZonas.atras());
		dibujarPanelZonas();
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(contZonas.nuevo());
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setBackground(Color.DARK_GRAY);
		btnNuevo.setIcon(new ImageIcon("img/gestion/add.png"));
		getPnBotones().add(btnNuevo);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(Color.DARK_GRAY);
		btnEliminar.setIcon(new ImageIcon("img/gestion/cancel.png"));
		btnEliminar.addActionListener(contZonas.eliminar());
		getPnBotones().add(btnEliminar);
		
		dibujarBotonSalir();
		getBtnSalir().addActionListener(contZonas.salir());
	}
	
	public void dibujarPanelZonas(){
		pnZona = new JPanel();
		pnZona.setBackground(Color.DARK_GRAY);
		pnZona.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)),"", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnZona.setLayout(new MigLayout());
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		pnZona.add(lblNombre,"cell 0 0");
		
		txtNombre = new JTextField();
		txtNombre.setColumns(25);
		pnZona.add(txtNombre,"cell 1 0");
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(contZonas.guardar());
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBackground(Color.DARK_GRAY);
		btnGuardar.setIcon(new ImageIcon("img/gestion/bien.png"));
		pnZona.add(btnGuardar,"cell 2 0");
		
		btnCancelar = new JButton("Cancelar");
		pnZona.add(btnCancelar,"cell 3 0");
		
		add(pnZona,BorderLayout.SOUTH);
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

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public JPanel getPnZona() {
		return pnZona;
	}

	public void setPnZona(JPanel pnZona) {
		this.pnZona = pnZona;
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

	public ContZonas getContZonas() {
		return contZonas;
	}

	public void setContZonas(ContZonas contZonas) {
		this.contZonas = contZonas;
	}
}
