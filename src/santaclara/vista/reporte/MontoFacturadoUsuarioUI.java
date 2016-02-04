package santaclara.vista.reporte;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.SystemColor;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.JButton;
import javax.swing.JTable;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import santaclara.Servicio.ServicioAlmacen;
import santaclara.controlador.consultas.ContMontoFacturadoMesZonaTipoPago;
import santaclara.modelo.Almacen;
import santaclara.modelo.DetalleFactura;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.List;

import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class MontoFacturadoUsuarioUI extends JPanel {
	
	private JComboBox<Almacen> 		cmbAlmacen;
	@SuppressWarnings("rawtypes")
	private JTableBinding   binFacturas;
    private JPanel pnOpciones;
    private JPanel pnCatalogo;
    private JButton btnAtras;
    private JButton btnBuscar;
    private JLabel lblDesde;
    private JLabel lblHasta;
    private JLabel lblMonto;
    private JLabel lblAlmacen;
    private JDateChooser dateDesde;
    private JDateChooser dateHasta;
    private JScrollPane scrollPane;
    
    
    
    private JTable table;
    private JButton btnActualizar;
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public MontoFacturadoUsuarioUI(ContMontoFacturadoMesZonaTipoPago contMontoFacturadoMesZonaTipoPago) throws NumberFormatException, IOException {
		super();
		setBackground(Color.DARK_GRAY);

		/**********************************************************************************************************************************************************/
	
	
		cmbAlmacen = new JComboBox<Almacen>();
		setBounds(162, 115, 695,426);
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
		setLayout(new BorderLayout(0, 0));
		
		pnOpciones = new JPanel();
		pnOpciones.setBackground(Color.DARK_GRAY);
		add(pnOpciones, BorderLayout.NORTH);
		
		btnAtras = new JButton("Atras");
		btnAtras.setForeground(Color.WHITE);
		btnAtras.setFont(new Font("Dialog", Font.BOLD, 10));
		btnAtras.setBackground(Color.DARK_GRAY);
		pnOpciones.add(btnAtras);
		
		lblDesde = new JLabel("Desde");
		lblDesde.setForeground(Color.WHITE);
		pnOpciones.add(lblDesde);
		
		dateDesde = new JDateChooser();
		pnOpciones.add(dateDesde);
		
		lblHasta = new JLabel("Hasta");
		lblHasta.setForeground(Color.WHITE);
		pnOpciones.add(lblHasta);
		
		dateHasta = new JDateChooser();
		pnOpciones.add(dateHasta);
		
		lblAlmacen = new JLabel("Almacen");
		lblAlmacen.setForeground(Color.WHITE);
		pnOpciones.add(lblAlmacen);
		
		pnOpciones.add(cmbAlmacen);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(contMontoFacturadoMesZonaTipoPago.Actualizar());
		btnActualizar.setBackground(Color.DARK_GRAY);
		btnActualizar.setForeground(Color.WHITE);
		pnOpciones.add(btnActualizar);
		
		pnCatalogo = new JPanel();
		add(pnCatalogo, BorderLayout.CENTER);
		pnCatalogo.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		pnCatalogo.add(scrollPane, BorderLayout.NORTH);
		
		table = new JTable();
		pnCatalogo.add(table);
		
		lblMonto = new JLabel("Monto:");
		lblMonto.setFont(new Font("Dialog", Font.BOLD, 16));
		lblMonto.setForeground(Color.WHITE);
		add(lblMonto, BorderLayout.SOUTH);
			
	}


    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void activarBinding(List<DetalleFactura> detalleFacturas) {
		// TODO Auto-generated method stub
		setTable(new JTable());
		getScrollPane().setViewportView(getTable());
		binFacturas = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,detalleFacturas,getTable());
	    
	    BeanProperty idFactura  = BeanProperty.create("id");
	    BeanProperty fecha  = BeanProperty.create("getFechaStr");
	    BeanProperty monto = BeanProperty.create("totalAPagar");
	    BeanProperty almacen = BeanProperty.create("almacen.ubicacion");
	    

	    binFacturas.addColumnBinding(idFactura).setColumnClass(Integer.class).setColumnName("Nro Factura");;
	    binFacturas.addColumnBinding(fecha).setColumnClass(String.class).setColumnName("Fecha");
	    binFacturas.addColumnBinding(monto).setColumnClass(String.class).setColumnName("Monto");
	    binFacturas.addColumnBinding(almacen).setColumnClass(String.class).setColumnName("almacen");;
	    

	    binFacturas.bind();
	}
    
    @SuppressWarnings("rawtypes")
	public void cargarCmbAlmacen() throws NumberFormatException, IOException{
   
    	JComboBoxBinding jcomboAlmacen = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ,new ServicioAlmacen().getAlmacenes(),cmbAlmacen);
	    
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


	public JPanel getPnOpciones() {
		return pnOpciones;
	}


	public void setPnOpciones(JPanel pnOpciones) {
		this.pnOpciones = pnOpciones;
	}


	public JPanel getPnCatalogo() {
		return pnCatalogo;
	}


	public void setPnCatalogo(JPanel pnCatalogo) {
		this.pnCatalogo = pnCatalogo;
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


	public JScrollPane getScrollPane() {
		return scrollPane;
	}


	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}


	public JTable getTable() {
		return table;
	}


	public void setTable(JTable table) {
		this.table = table;
	}
}


