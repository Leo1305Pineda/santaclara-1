/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.vista.reporte;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.SystemColor;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.JButton;
import javax.swing.JTable;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import santaclara.Servicio.ServicioAlmacen;
import santaclara.controlador.reportes.ContReportMontFacturadoAlmacen;
import santaclara.modelo.Almacen;
import santaclara.modelo.Factura;
import santaclara.vista.herramientas.VistaGenericaUI;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.List;

import com.toedter.calendar.JDateChooser;


@SuppressWarnings("serial")
public class MontoFacturadoAlmacenUI  extends VistaGenericaUI {

	private JComboBox<Almacen> 		cmbAlmacen;
	
	@SuppressWarnings("rawtypes")
	private JTableBinding   binFacturas;
    private JButton btnBuscar;
    private JLabel lblDesde;
    private JLabel lblHasta;
    private JLabel lblMonto;
    private JLabel lblAlmacen;
    private JDateChooser dateDesde;
    private JDateChooser dateHasta;
    private JButton btnActualizar;
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public MontoFacturadoAlmacenUI(ContReportMontFacturadoAlmacen contReportMontFacturadoAlmacen) throws NumberFormatException, IOException {
		super();

		/**********************************************************************************************************************************************************/
		dibujarPanelOpciones();
		dibujarPanelTabla();
		dibujarBotonAtras();
		getBtnAtras().addActionListener(contReportMontFacturadoAlmacen.Atras());
	
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
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(contReportMontFacturadoAlmacen.Actualizar());
		btnActualizar.setBackground(Color.DARK_GRAY);
		btnActualizar.setForeground(Color.WHITE);
		getPnBotones().add(btnActualizar);

		
		lblMonto = new JLabel("Monto:");
		lblMonto.setFont(new Font("Dialog", Font.BOLD, 16));
		lblMonto.setForeground(Color.WHITE);
		add(lblMonto, BorderLayout.SOUTH);
			
	}


    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void activarBinding(List<Factura> facturas) {
		// TODO Auto-generated method stub
		setTable(new JTable());
		getScrollPanel().setViewportView(getTable());

		binFacturas = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,facturas,getTable());
	    
	    BeanProperty idFactura  = BeanProperty.create("id");
	    BeanProperty fecha  = BeanProperty.create("fechaStr");
	    BeanProperty monto = BeanProperty.create("totalAPagar");
	    BeanProperty almacen = BeanProperty.create("almacen.ubicacion");
	    

	    binFacturas.addColumnBinding(idFactura).setColumnClass(Integer.class).setColumnName("Nro Factura");;
	    binFacturas.addColumnBinding(fecha).setColumnClass(String.class).setColumnName("Fecha");
	    binFacturas.addColumnBinding(monto).setColumnClass(String.class).setColumnName("Monto");
	    binFacturas.addColumnBinding(almacen).setColumnClass(String.class).setColumnName("almacen");;
	    

	    binFacturas.bind();
	}
    
    @SuppressWarnings("rawtypes")
	public void cargarCmbAlmacen() throws Exception{
    	List<Almacen> almacenes = new ServicioAlmacen().getAlmacenes();
    	Almacen almacen = new Almacen(0,"Todos");
    	almacenes.add(almacen);
    	JComboBoxBinding jcomboAlmacen = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ,almacenes,cmbAlmacen);
	    
	    jcomboAlmacen.bind();
    }

	public JComboBox<Almacen> getCmbAlmacen() {
		return cmbAlmacen;
	}


	public void setCmbAlmacen(JComboBox<Almacen> cmbAlmacen) {
		this.cmbAlmacen = cmbAlmacen;
	}


	@SuppressWarnings("rawtypes")
	public JTableBinding getBinFacturas() {
		return binFacturas;
	}


	@SuppressWarnings("rawtypes")
	public void setBinFacturas(JTableBinding binFacturas) {
		this.binFacturas = binFacturas;
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

	public JButton getBtnBuscar() {
		return btnBuscar;
	}


	public void setBtnBuscar(JButton btnBuscar) {
		this.btnBuscar = btnBuscar;
	}


	public JLabel getLblMonto() {
		return lblMonto;
	}


	public void setLblMonto(JLabel lblMonto) {
		this.lblMonto = lblMonto;
	}
 
	
}


