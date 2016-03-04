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

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.JButton;

import com.toedter.calendar.JDateChooser;

import santaclara.controlador.consultas.ContMontoFacturadoMesZonaTipoPago;
import santaclara.modelo.Almacen;
import santaclara.modelo.Cliente;
import santaclara.modelo.Zona;
import santaclara.vista.herramientas.VistaGenericaUI;

import java.awt.Color;
import java.io.IOException;

@SuppressWarnings("serial")
public class MontoFacturadoMesZonaTipoPagoUI extends VistaGenericaUI{

	private JComboBox<Almacen> 		cmbAlmacen;
	private JLabel lblDesde;
    private JLabel lblHasta;
    private JLabel lblAlmacen;
    private JDateChooser dateDesde;
    private JDateChooser dateHasta;
    private JButton btnActualizar;
    private JLabel lblZona;
    private JLabel lbltipoPago;
	private JComboBox<Zona> 	cmbZona;
	private JComboBox<Cliente> 		cmbTipoPago;
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public MontoFacturadoMesZonaTipoPagoUI(ContMontoFacturadoMesZonaTipoPago contMontoFacturadoMesZonaTipoPago) throws NumberFormatException, IOException {
		super();

		/**********************************************************************************************************************************************************/
		dibujarPanelOpciones();
		dibujarBotonAtras();
		getBtnAtras().addActionListener(contMontoFacturadoMesZonaTipoPago.Atras());
		dibujarPanelTabla();
		
		lblDesde = new JLabel("Desde");
		lblDesde.setForeground(Color.WHITE);
		getPnBotones().add(lblDesde);
		
		dateDesde = new JDateChooser();
		getPnBotones().add(dateDesde);
		
		lblHasta = new JLabel("Hasta");
		lblHasta.setForeground(Color.WHITE);
		getPnBotones().add(lblHasta);
		
		dateHasta = new JDateChooser();
		getPnBotones().add(dateHasta);
		
		lblAlmacen = new JLabel("Almacen");
		lblAlmacen.setForeground(Color.WHITE);
		getPnBotones().add(lblAlmacen);
	
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
	
		getPnBotones().add(cmbAlmacen);
	
		lbltipoPago = new JLabel("Tipo Cliente");
		lbltipoPago.setForeground(Color.WHITE);
		getPnBotones().add(lbltipoPago);
		
		cmbTipoPago = new JComboBox<Cliente>();
		cmbTipoPago.setModel(new DefaultComboBoxModel(new String[] {"Todos", "Credito", "Contado"}));
		cmbTipoPago.setBackground(SystemColor.controlHighlight);
			
		getPnBotones().add(cmbTipoPago);

		lblZona = new JLabel("Zona");
		lblZona.setForeground(Color.WHITE);
		getPnBotones().add(lblZona);
		
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
		getPnBotones().add(cmbZona);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.setIcon(new ImageIcon("img/gestion/actualizar.png"));
		btnActualizar.addActionListener(contMontoFacturadoMesZonaTipoPago.Actualizar());
		btnActualizar.setBackground(Color.DARK_GRAY);
		btnActualizar.setForeground(Color.WHITE);
		getPnBotones().add(btnActualizar);
		
		dibujarBotonSalir();
		getBtnSalir().addActionListener(contMontoFacturadoMesZonaTipoPago.Salir());
	}
    
	public JComboBox<Almacen> getCmbAlmacen() {
		return cmbAlmacen;
	}


	public void setCmbAlmacen(JComboBox<Almacen> cmbAlmacen) {
		this.cmbAlmacen = cmbAlmacen;
	}


	public JDateChooser getDateDesde() {
		return dateDesde;
	}


	public void setDateDesde(JDateChooser dateDesde) {
		this.dateDesde = dateDesde;
	}


	public JDateChooser getDateHasta() {
		return dateHasta;
	}


	public void setDateHasta(JDateChooser dateHasta) {
		this.dateHasta = dateHasta;
	}

	public JLabel getLblDesde() {
		return lblDesde;
	}

	public void setLblDesde(JLabel lblDesde) {
		this.lblDesde = lblDesde;
	}

	public JLabel getLblHasta() {
		return lblHasta;
	}

	public void setLblHasta(JLabel lblHasta) {
		this.lblHasta = lblHasta;
	}

	public JLabel getLblAlmacen() {
		return lblAlmacen;
	}

	public void setLblAlmacen(JLabel lblAlmacen) {
		this.lblAlmacen = lblAlmacen;
	}

	public JButton getBtnActualizar() {
		return btnActualizar;
	}

	public void setBtnActualizar(JButton btnActualizar) {
		this.btnActualizar = btnActualizar;
	}

	public JLabel getLblZona() {
		return lblZona;
	}

	public void setLblZona(JLabel lblZona) {
		this.lblZona = lblZona;
	}

	public JComboBox<Zona> getCmbZona() {
		return cmbZona;
	}

	public void setCmbZona(JComboBox<Zona> cmbZona) {
		this.cmbZona = cmbZona;
	}

	public JComboBox<Cliente> getCmbTipoCliente() {
		return cmbTipoPago;
	}

	public void setCmbTipoCliente(JComboBox<Cliente> cmbTipoCliente) {
		this.cmbTipoPago = cmbTipoCliente;
	}

    
}


