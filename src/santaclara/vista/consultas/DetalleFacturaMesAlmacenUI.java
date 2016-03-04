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

import santaclara.controlador.consultas.ContDetalleFacturaMesAlmacen;
import santaclara.modelo.Almacen;
import santaclara.modelo.Producto;
import santaclara.vista.herramientas.VistaGenericaUI;

import java.awt.Color;
import java.io.IOException;

@SuppressWarnings("serial")
public class DetalleFacturaMesAlmacenUI extends VistaGenericaUI{

	private JComboBox<Almacen> 		cmbAlmacen;
	private JComboBox<Producto> 		cmbProducto;
    private JButton btnBuscar;
    private JLabel lblDesde;
    private JLabel lblHasta;
    private JLabel lblAlmacen;
    private JLabel lblProducto;
    private JDateChooser dateDesde;
    private JDateChooser dateHasta;
    private JButton btnActualizar;
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public DetalleFacturaMesAlmacenUI(ContDetalleFacturaMesAlmacen contDetalleFacturaMesAlmacen) throws NumberFormatException, IOException {
		super();

		/**********************************************************************************************************************************************************/
		dibujarPanelOpciones();
		dibujarBotonAtras();
		getBtnAtras().addActionListener(contDetalleFacturaMesAlmacen.Atras());
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
		
		cmbProducto = new JComboBox<Producto>();
		cmbProducto.setBackground(SystemColor.controlHighlight);
		cmbProducto.setRenderer(new ListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
				// TODO Auto-generated method stub
				Producto producto = (Producto) value;
				if(producto.getId().equals(0))return new JLabel("Todos");
					return new JLabel(producto.getDescripcion());
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
		
		lblProducto = new JLabel("Producto");
		lblProducto.setForeground(Color.WHITE);
		getPnBotones().add(lblProducto);
		
		getPnBotones().add(cmbProducto);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setIcon(new ImageIcon("img/gestion/actualizar.png"));
		btnActualizar.addActionListener(contDetalleFacturaMesAlmacen.Actualizar());
		btnActualizar.setBackground(Color.DARK_GRAY);
		btnActualizar.setForeground(Color.WHITE);
		getPnBotones().add(btnActualizar);
		
		dibujarBotonSalir();
		getBtnSalir().addActionListener(contDetalleFacturaMesAlmacen.Salir());
	}
	
	public JComboBox<Almacen> getCmbAlmacen() {
		return cmbAlmacen;
	}


	public void setCmbAlmacen(JComboBox<Almacen> cmbAlmacen) {
		this.cmbAlmacen = cmbAlmacen;
	}


	public JComboBox<Producto> getCmbProducto() {
		return cmbProducto;
	}


	public void setCmbProducto(JComboBox<Producto> cmbProducto) {
		this.cmbProducto = cmbProducto;
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
	
	public JLabel getLblProducto() {
		return lblProducto;
	}


	public void setLblProducto(JLabel lblProducto) {
		this.lblProducto = lblProducto;
	}

	public JButton getBtnBuscar() {
		return btnBuscar;
	}


	public void setBtnBuscar(JButton btnBuscar) {
		this.btnBuscar = btnBuscar;
	}
}


