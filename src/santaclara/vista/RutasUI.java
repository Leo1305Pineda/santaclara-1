/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.vista;

import java.awt.Component;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import beans.JDibujarTabla;
import beans.jCampoBuscar;
import net.miginfocom.swing.MigLayout;
import santaclara.Servicio.ServicioRuta;
import santaclara.controlador.ContRutas;
import santaclara.modelo.Ruta;
import santaclara.modelo.Zona;
import santaclara.vista.herramientas.VistaGenericaUI;

@SuppressWarnings("serial")
public class RutasUI extends VistaGenericaUI {
    private JComboBox<Zona> cmbZona;
   
    private JPanel 	 panelRuta;
  
    private JTextField 	txtNombre;
	
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JButton btnNuevo;
	private JButton btnEliminar;
	private JButton Zona;
	
	private ContRutas contRutas;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public RutasUI(ContRutas contRutas) throws Exception {
		super();
		this.contRutas = contRutas;
		dibujarPanelOpciones();
		List<Ruta> catalogo = new ServicioRuta().getRutas();
		List<jCampoBuscar> campos = new ArrayList<jCampoBuscar>();
		campos.add(jCampoBuscar.crearCampoBusquedad("Id","getId"));
		campos.add(jCampoBuscar.crearCampoBusquedad("Nombre","getNombre"));
		dibujarBuscar(campos,catalogo,new JDibujarTabla() {
			
			@Override
			public void dibujarTabla(List resultados) {
				// TODO Auto-generated method stub
				RutasUI.this.contRutas.activarBinding(resultados);
			}
		});
		dibujarPanelTabla();
		dibujarBotonAtras();
		getBtnAtras().addActionListener(contRutas.Atras());
						
		btnNuevo = new JButton("Nuevo");
		btnNuevo.setIcon(new ImageIcon("img/gestion/add.png"));
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setBackground(Color.DARK_GRAY);
		btnNuevo.addActionListener(contRutas.nuevo());
		getPnBotones().add(btnNuevo);
									
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon("img/gestion/cancel.png"));
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(Color.DARK_GRAY);
		btnEliminar.addActionListener(contRutas.eliminar());
		getPnBotones().add(btnEliminar);
		
		dibujarBotonSalir();
		getBtnSalir().addActionListener(contRutas.salir());
	
		panelRuta = new JPanel();
		panelRuta.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)),"Editar Ruta", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		panelRuta.setBackground(Color.DARK_GRAY);
		panelRuta.setLayout(new MigLayout());
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		panelRuta.add(lblNombre,"cell 0 0");

		txtNombre = new JTextField();
		panelRuta.add(txtNombre,"cell 1 0");
		txtNombre.setColumns(75);
		
		JLabel lblZona = new JLabel("Zona:");
		lblZona.setForeground(Color.WHITE);
		lblZona.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		panelRuta.add(lblZona,"cell 2 0");
		
		cmbZona = new JComboBox<Zona>();
		cmbZona.setBackground(SystemColor.controlHighlight);
		cmbZona.setForeground(Color.BLACK);
		cmbZona.setRenderer(new ListCellRenderer() {
			
			@Override
			public Component getListCellRendererComponent(JList list, Object value,
					int index, boolean isSelected, boolean cellHasFocus) {
				// TODO Auto-generated method stub
				Zona zona = (Zona) value;
				return new JLabel(zona.getDescripcion());
			}
		});
		cmbZona.setSize(150, 16);
		panelRuta.add(cmbZona,"cell 3 0");
		
		btnCancelar = new JButton("Guardar");
		panelRuta.add(btnCancelar,"cell 4 1");
		
		Zona = new JButton("Zona");
		Zona.setIcon(new ImageIcon("img/gestion/Presentacion.png"));
		Zona.setForeground(Color.WHITE);
		Zona.setBackground(Color.DARK_GRAY);
		Zona.addActionListener(contRutas.AbrirZona());
		panelRuta.add(Zona,"cell 4 0");
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setIcon(new ImageIcon("img/gestion/bien.png"));
		btnGuardar.addActionListener(contRutas.guardar());
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBackground(Color.DARK_GRAY);
		panelRuta.add(btnGuardar,"cell 5 0");
		
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
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

	public JComboBox<Zona> getCmbZona() {
		return cmbZona;
	}

	public void setCmbZona(JComboBox<Zona> cmbZona) {
		this.cmbZona = cmbZona;
	}

	public JPanel getPanelRuta() {
		return panelRuta;
	}

	public void setPanelRuta(JPanel panelRuta) {
		this.panelRuta = panelRuta;
	}

	public JButton getZona() {
		return Zona;
	}

	public void setZona(JButton zona) {
		Zona = zona;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}
}
