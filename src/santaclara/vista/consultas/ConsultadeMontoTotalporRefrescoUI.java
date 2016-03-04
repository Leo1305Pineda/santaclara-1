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

import com.toedter.calendar.JDateChooser;

import santaclara.controlador.consultas.ContConsultadeMontoTotalporRefresco;
import santaclara.modelo.Almacen;
import santaclara.modelo.Sabor;
import santaclara.vista.herramientas.VistaGenericaUI;

import java.awt.Color;
import java.io.IOException;

@SuppressWarnings("serial")
public class ConsultadeMontoTotalporRefrescoUI extends VistaGenericaUI{

	private JComboBox<Almacen> 		cmbAlmacen;
	private JComboBox<Sabor> 		cmbSabor;
    private JButton btnBuscar;
    private JLabel lblDesde;
    private JLabel lblHasta;
    private JLabel lblAlmacen;
    private JLabel lblSabor;
    private JDateChooser dateDesde;
    private JDateChooser dateHasta;
    private JButton btnActualizar;
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public ConsultadeMontoTotalporRefrescoUI(ContConsultadeMontoTotalporRefresco contConsultadeMontoTotalporRefresco) throws NumberFormatException, IOException {
		super();

		/**********************************************************************************************************************************************************/
		dibujarPanelOpciones();
		dibujarBotonAtras();
		getBtnAtras().addActionListener(contConsultadeMontoTotalporRefresco.Atras());
		dibujarPanelTabla();
	
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
		cmbSabor = new JComboBox<Sabor>();
		cmbSabor.setBackground(SystemColor.controlHighlight);
		cmbSabor.setRenderer(new ListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
				// TODO Auto-generated method stub
				Sabor sabor = (Sabor) value;
				return new JLabel(sabor.getSabor());
			}
		});
		
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
		
		getPnBotones().add(cmbAlmacen);
		
		lblSabor = new JLabel("Sabor");
		lblSabor.setForeground(Color.WHITE);
		getPnBotones().add(lblSabor);
		
		getPnBotones().add(cmbSabor);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setIcon(new ImageIcon("img/gestion/actualizar.png"));
		btnActualizar.addActionListener(contConsultadeMontoTotalporRefresco.Actualizar());
		btnActualizar.setBackground(Color.DARK_GRAY);
		btnActualizar.setForeground(Color.WHITE);
		getPnBotones().add(btnActualizar);
		
		dibujarBotonSalir();
		getBtnSalir().addActionListener(contConsultadeMontoTotalporRefresco.Salir());
	}

	public JComboBox<Almacen> getCmbAlmacen() {
		return cmbAlmacen;
	}

	public void setCmbAlmacen(JComboBox<Almacen> cmbAlmacen) {
		this.cmbAlmacen = cmbAlmacen;
	}

	public JComboBox<Sabor> getCmbSabor() {
		return cmbSabor;
	}

	public void setCmbSabor(JComboBox<Sabor> cmbSabor) {
		this.cmbSabor = cmbSabor;
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
	
	public JLabel getLblSabor() {
		return lblSabor;
	}

	public void setLblSabor(JLabel lblSabor) {
		this.lblSabor = lblSabor;
	}

	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public void setBtnBuscar(JButton btnBuscar) {
		this.btnBuscar = btnBuscar;
	}

	public JButton getBtnActualizar() {
		return btnActualizar;
	}

	public void setBtnActualizar(JButton btnActualizar) {
		this.btnActualizar = btnActualizar;
	}
}


