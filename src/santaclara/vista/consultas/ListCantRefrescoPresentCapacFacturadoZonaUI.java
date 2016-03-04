/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.vista.consultas;

import java.awt.Component;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.JButton;

import net.miginfocom.swing.MigLayout;

import com.toedter.calendar.JDateChooser;

import santaclara.controlador.consultas.ContListCantRefrecoPresentCapacFacturadoZona;
import santaclara.modelo.Almacen;
import santaclara.modelo.Capacidad;
import santaclara.modelo.Presentacion;
import santaclara.modelo.Zona;
import santaclara.vista.herramientas.VistaGenericaUI;

import java.awt.Color;
import java.io.IOException;

@SuppressWarnings("serial")
public class ListCantRefrescoPresentCapacFacturadoZonaUI extends VistaGenericaUI{

	private JComboBox<Almacen> 		cmbAlmacen;
	private JComboBox<Presentacion> cmbPresentacion;
	private JComboBox<Capacidad> 	cmbCapacidad;
	private JComboBox<Zona> 	cmbZona;
    private JLabel lblDesde;
    private JLabel lblHasta;
    private JLabel lblAlmacen;
    private JLabel lblPresentacion;
    private JLabel lblCapacidad;
    private JLabel lblZona;
    private JDateChooser dateDesde;
    private JDateChooser dateHasta;
    private JButton btnActualizar;
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public ListCantRefrescoPresentCapacFacturadoZonaUI(ContListCantRefrecoPresentCapacFacturadoZona contListCantRefrecoPresentCapacFacturadoZona) throws NumberFormatException, IOException {
		super();

		/**********************************************************************************************************************************************************/
		dibujarPanelOpciones();
		dibujarBotonAtras();
		getBtnAtras().addActionListener(contListCantRefrecoPresentCapacFacturadoZona.Atras());
		dibujarPanelTabla();
		
		getPnBotones().setLayout(new MigLayout());
		
		lblDesde = new JLabel("Desde");
		lblDesde.setForeground(Color.WHITE);
		getPnBotones().add(lblDesde,"cell 1 0");
		
		dateDesde = new JDateChooser();
		getPnBotones().add(dateDesde,"cell 2 0");
		
		lblHasta = new JLabel("Hasta");
		lblHasta.setForeground(Color.WHITE);
		getPnBotones().add(lblHasta,"cell 3 0");
		
		dateHasta = new JDateChooser();
		getPnBotones().add(dateHasta,"cell 4 0");
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setIcon(new ImageIcon("img/gestion/actualizar.png"));
		btnActualizar.addActionListener(contListCantRefrecoPresentCapacFacturadoZona.Actualizar());
		btnActualizar.setBackground(Color.DARK_GRAY);
		btnActualizar.setForeground(Color.WHITE);
		getPnBotones().add(btnActualizar,"cell 5 0");
		
		dibujarBotonSalir();
		getBtnSalir().addActionListener(contListCantRefrecoPresentCapacFacturadoZona.Salir());
		
		lblZona = new JLabel("Zona");
		lblZona.setForeground(Color.WHITE);
		getPnBotones().add(lblZona,"cell 1 1");
		
		cmbZona = new JComboBox<Zona>();
		cmbZona.setBackground(SystemColor.controlHighlight);
		cmbZona.setRenderer(new ListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
				// TODO Auto-generated method stub
				Zona zona = (Zona) value;
				if (zona.getId().equals(0)) return new JLabel("Todos");
				return new JLabel(zona.getDescripcion());
			}
		});
		getPnBotones().add(cmbZona,"cell 2 1");
		
		lblAlmacen = new JLabel("Almacen");
		lblAlmacen.setForeground(Color.WHITE);
		getPnBotones().add(lblAlmacen,"cell 3 1");
		
		cmbAlmacen = new JComboBox<Almacen>();
		cmbAlmacen.setBackground(SystemColor.controlHighlight);
		cmbAlmacen.setRenderer(new ListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
				// TODO Auto-generated method stub
				Almacen almacen = (Almacen) value;
				return new JLabel(almacen.getUbicacion());
			}
		});
		getPnBotones().add(cmbAlmacen,"cell 4 1");
		
		lblPresentacion = new JLabel("Presentacion");
		lblPresentacion.setForeground(Color.WHITE);
		getPnBotones().add(lblPresentacion,"cell 5 1");
		
		cmbPresentacion = new JComboBox<Presentacion>();
		cmbPresentacion.setBackground(SystemColor.controlHighlight);
		cmbPresentacion.setRenderer(new ListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
				// TODO Auto-generated method stub
				Presentacion presentacion = (Presentacion) value;
				return new JLabel(presentacion.getMaterial());
			}
		});
		getPnBotones().add(cmbPresentacion,"cell 6 1");
		
		lblCapacidad = new JLabel("Capacidad");
		lblCapacidad.setForeground(Color.WHITE);
		getPnBotones().add(lblCapacidad,"cell 7 1");
		
		cmbCapacidad = new JComboBox<Capacidad>();
		cmbCapacidad.setBackground(SystemColor.controlHighlight);
		cmbCapacidad.setRenderer(new ListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
				// TODO Auto-generated method stub
				Capacidad capacidad = (Capacidad) value;
				if (capacidad.getId().equals(0))return new JLabel("Todos");
				return new JLabel(capacidad.getVolumen().toString());
				
			}
		}); 
	
		getPnBotones().add(cmbCapacidad,"cell 8 1");
	
	}
    
	public JComboBox<Almacen> getCmbAlmacen() {
		return cmbAlmacen;
	}


	public void setCmbAlmacen(JComboBox<Almacen> cmbAlmacen) {
		this.cmbAlmacen = cmbAlmacen;
	}
	
	public JComboBox<Presentacion> getCmbPresentacion() {
		return cmbPresentacion;
	}

	public void setCmbPresentacion(JComboBox<Presentacion> cmbPresentacion) {
		this.cmbPresentacion = cmbPresentacion;
	}

	public JComboBox<Capacidad> getCmbCapacidad() {
		return cmbCapacidad;
	}

	public void setCmbCapacidad(JComboBox<Capacidad> cmbCapacidad) {
		this.cmbCapacidad = cmbCapacidad;
	
	} 

	public JLabel getLblDesde() {
		return lblDesde;
	}

	public void setLblDesde(JLabel lblDesde) {
		this.lblDesde = lblDesde;
	}

	public JDateChooser getDateDesde() {
		return dateDesde;
	}

	public void setDateDesde(JDateChooser dateDesde) {
		this.dateDesde = dateDesde;
	}

	public JLabel getLblHasta() {
		return lblHasta;
	}

	public void setLblHasta(JLabel lblHasta) {
		this.lblHasta = lblHasta;
	}

	public JDateChooser getDateHasta() {
		return dateHasta;
	}

	public void setDateHasta(JDateChooser dateHasta) {
		this.dateHasta = dateHasta;
	}

	public JLabel getLblAlmacen() {
		return lblAlmacen;
	}

	public void setLblAlmacen(JLabel lblAlmacen) {
		this.lblAlmacen = lblAlmacen;
	}
	
	public JLabel getLblPresentacion() {
		return lblPresentacion;
	}

	public void setLblPresentacion(JLabel lblPresentacion) {
		this.lblPresentacion = lblPresentacion;
	}
	
	public JLabel getLblCapacidad() {
		return lblCapacidad;
	}

	public void setLblCapacidad(JLabel lblCapacidad) {
		this.lblCapacidad = lblCapacidad;
	}

	public JComboBox<Zona> getCmbZona() {
		return cmbZona;
	}

	public void setCmbZona(JComboBox<Zona> cmbZona) {
		this.cmbZona = cmbZona;
	}

	public JLabel getLblZona() {
		return lblZona;
	}

	public void setLblZona(JLabel lblZona) {
		this.lblZona = lblZona;
	}

	public JButton getBtnActualizar() {
		return btnActualizar;
	}

	public void setBtnActualizar(JButton btnActualizar) {
		this.btnActualizar = btnActualizar;
	}
	
	
}


