package santaclara.vista.reporte;

import java.awt.BorderLayout;
import java.awt.SystemColor;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTable;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import santaclara.controlador.reportes.ContReportMontFacturadoVendedor;

import santaclara.modelo.Factura;
import santaclara.vista.herramientas.VistaGenericaUI;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.List;

import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class MontoFacturadoVendedorUI extends VistaGenericaUI{
	
	private JComboBox<String> 		cmbVendedor;
	@SuppressWarnings("rawtypes")
	private JTableBinding   binFacturas;
    private JButton btnAtras;
    private JButton btnBuscar;
    private JLabel lblDesde;
    private JLabel lblHasta;
    private JLabel lblMonto;
    private JLabel lblVendedor;
    private JDateChooser dateDesde;
    private JDateChooser dateHasta;
    private JButton btnActualizar;
    
	public MontoFacturadoVendedorUI(ContReportMontFacturadoVendedor contReportMontFacturadoVendedor) throws NumberFormatException, IOException {
		super();

		/**********************************************************************************************************************************************************/

		dibujarPanelOpciones();
		dibujarPanelTabla();
		
		cmbVendedor = new JComboBox<String>();
		cmbVendedor.setBackground(SystemColor.controlHighlight);

		btnAtras = new JButton("Atras");
		btnAtras.setForeground(Color.WHITE);
		btnAtras.setFont(new Font("Dialog", Font.BOLD, 10));
		btnAtras.setBackground(Color.DARK_GRAY);
		getPnBotones().add(btnAtras);
		
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
		
		lblVendedor = new JLabel("Almacen");
		lblVendedor.setForeground(Color.WHITE);
		getPnBotones().add(lblVendedor);
		
		getPnBotones().add(cmbVendedor);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(contReportMontFacturadoVendedor.Actualizar());
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
	    BeanProperty vendedor = BeanProperty.create("vendedor.username");
	    

	    binFacturas.addColumnBinding(idFactura).setColumnClass(Integer.class).setColumnName("Nro Factura");;
	    binFacturas.addColumnBinding(fecha).setColumnClass(String.class).setColumnName("Fecha");
	    binFacturas.addColumnBinding(monto).setColumnClass(String.class).setColumnName("Monto");
	    binFacturas.addColumnBinding(vendedor).setColumnClass(String.class).setColumnName("Vendedor");;
	    
	    binFacturas.bind();
	}
   
	public JComboBox<String> getCmbVendedor() {
		return cmbVendedor;
	}


	public void setCmbAlmacen(JComboBox<String> cmbAlmacen) {
		this.cmbVendedor = cmbAlmacen;
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


	public JLabel getLblVendedor() {
		return lblVendedor;
	}


	public void setLblVendedor(JLabel lblAlmacen) {
		this.lblVendedor = lblAlmacen;
	}


	public JPanel getPnOpciones() {
		return getPnBotones();
	}
	
	public JButton getBtnAtras() {
		return btnAtras;
	}


	public void setBtnAtras(JButton btnAtras) {
		this.btnAtras = btnAtras;
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


