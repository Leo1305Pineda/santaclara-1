/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.controlador.consultas;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import santaclara.Servicio.ServicioAlmacen;
import santaclara.Servicio.ServicioFactura;
import santaclara.Servicio.ServicioZona;
import santaclara.controlador.ContGeneral;
import santaclara.controlador.ContPrincipal;
import santaclara.controlador.IContGeneral;
import santaclara.modelo.Almacen;
import santaclara.modelo.Factura;
import santaclara.modelo.Zona;
import santaclara.vista.consultas.MontoFacturadoMesZonaTipoPagoUI;

public class ContMontoFacturadoMesZonaTipoPago extends ContGeneral implements IContGeneral {

	private static MontoFacturadoMesZonaTipoPagoUI vista;
	private static List<Factura> facturas ;
	Double valueAcum = new Double(0.0);
	
	private List<Zona> zonas = new ArrayList<Zona>();
	
	public ContMontoFacturadoMesZonaTipoPago() {
		// TODO Auto-generated constructor stub
		
	}
	
	public ContMontoFacturadoMesZonaTipoPago(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		vista = new MontoFacturadoMesZonaTipoPagoUI(this);
		facturas = new ArrayList<Factura>();
		
		dibujar(vista,this);
		cargarCmbAlmacen();
		setSelectedValue(vista.getCmbAlmacen(), null);
		vista.getDateHasta().setMaxSelectableDate(new Date());
		vista.getDateHasta().setDate(new Date());
		vista.getDateDesde().setDate(new Date());
		
		for(Factura factura :new ServicioFactura().getPedidoFacturados())
		{
			facturas.add(factura);
		}
		for(Factura factura :new ServicioFactura().getPedidoPendientes())
		{
			facturas.add(factura);
		}
		
		zonas = new ServicioZona().getZonas();
		cargarCmbZona();
		setSelectedValue(vista.getCmbZona(), null);
	}
	@Override
	public JPanel getVista() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Factura> getFilterByCmbAlmacen(List<Factura> facturas){
		List<Factura> facturasAux = new ArrayList<Factura>();
		if(!((Almacen)vista.getCmbAlmacen().getSelectedItem()).getId().equals(0))
		{
			for(Factura factura: facturas)
			{
				if (factura.getAlmacen().getId().equals(((Almacen)vista.getCmbAlmacen().getSelectedItem()).getId()))
				{
					facturasAux.add(factura);
				}			
			}
			return facturasAux;
		}
		return facturas;
	}
	
	public List<Factura> getFilterByCmbTipoPago(List<Factura> facturas){
		List<Factura> facturasAux = new ArrayList<Factura>();
		if(!vista.getCmbTipoCliente().getSelectedItem().equals("Todos"))
		{
			for(Factura factura: facturas)
			{	
				if (factura.getTipoPago().equals(vista.getCmbTipoCliente().getSelectedItem()))
				{
					facturasAux.add(factura);
				}
				else if (factura.getTipoPago().equals(vista.getCmbTipoCliente().getSelectedItem()))
				{
					facturasAux.add(factura);
				}
			}
			return facturasAux;
		}
		return facturas;
	}
	
	
	public List<Factura> getFilterByCmbZona(List<Factura> facturas){
		List<Factura> detalleFacturasAux = new ArrayList<Factura>();
		if(!((Zona)vista.getCmbZona().getSelectedItem()).getId().equals(0))
		{
			for(Factura factura: facturas)
			{
				if (factura.getCliente().getRuta().getZona().getId().equals(((Zona)vista.getCmbZona().getSelectedItem()).getId()))
				{
					detalleFacturasAux.add(factura);
				}			
			}
			return detalleFacturasAux;
		}
		return facturas;
	}

	
	public List<Factura> getFilterByDate(List<Factura> facturas){
		List<Factura> facturasAux = new ArrayList<Factura>();
		//Filtra fecha Respecto Al dateDesde Y dateHasta
		for(Factura factura: facturas)
		{
			
			if (factura.getFecha().getTime() >= vista.getDateDesde().getDate().getTime() &&
					factura.getFecha().getTime() <= vista.getDateHasta().getDate().getTime())
			{
					facturasAux.add(factura);
			}
		}
		return facturasAux;
	}
	
	public List<List<Factura>> getOrderListGroupBy(String condition,List<List<Factura>> listGroup){
		List<List<Factura>> listGroupByAux = new ArrayList<List<Factura>>();
		for(List<Factura> list : listGroup) listGroupByAux.add(getOrderBy(condition, list));
		return listGroupByAux;
	}
	
	public List<Factura> getOrderBy(String condition,List<Factura> facturasNotOrder){
		List<Factura> facturasAux = new ArrayList<Factura>();
		Factura menorFactura = new Factura();
		while(!facturasNotOrder.isEmpty())
		{
			menorFactura = facturasNotOrder.get(0);
			
			for(Factura factura: facturasNotOrder)
			{	
				if(factura!=null && menorFactura !=null)
				{
					if(idValue(condition, factura) <= idValue(condition, menorFactura) )
					{	
						menorFactura = factura;	
					}
				}
			}
			facturasAux.add(menorFactura);
			
			facturasNotOrder.remove(menorFactura);
		
		}
		return facturasAux;
	}
	
	@SuppressWarnings("deprecation")
	public Long idValue(String condition,Factura factura){
		if(factura!=null)
		{
			switch (condition) {
			case "Date":return factura.getFecha().getTime();
			case "Month":return new Long(factura.getFecha().getMonth());
			case "Almacen": return  new Long(factura.getAlmacen().getId());
			case "Zona": return  new Long(factura.getCliente().getRuta().getZona().getId());
			case "TipoPago": 
				if (factura.getEstado()==null)return  new Long(0);
				else if (factura.getEstado()==true)return  new Long(1);
				else return  new Long(2);
			default:	return null;
			}
		}
		return null; 
	}
	
	public List<List<Factura>> getGroupListGroupBy(String condition,List<List<Factura>> listGroup){
		List<List<Factura>> facturasAux = new ArrayList<List<Factura>>();
		
		for(List<Factura> facturas : listGroup)
		{
			for(List<Factura> facturas2 : getGroupBy(condition, getOrderBy(condition, facturas)))
			{
				facturasAux.add(facturas2);
				
			}
			facturasAux.add(new ArrayList<Factura>());
		}
	return facturasAux;
	}
	
	public List<List<Factura>> getGroupBy(String condition,List<Factura> facturas){
		List<Factura> facturasAux = new ArrayList<Factura>();
		List<List<Factura>> listGroupBy = new ArrayList<List<Factura>>(); 	
		
		if(!facturas.isEmpty())
		{
			
		Long id =  idValue(condition, facturas.get(0)) ;
		for(Factura factura: facturas)
		{
			if(factura !=null)
			{
				if(!id.equals(idValue(condition, factura)))
				{
					listGroupBy.add(facturasAux);
					
					listGroupBy.add(new ArrayList<Factura>());//new Line
					
					facturasAux = new ArrayList<Factura>();//Lo instancio nuevamente
				}
				facturasAux.add(factura);
				id = idValue(condition, factura);
			}
			else listGroupBy.add(new ArrayList<Factura>());
			
		}
		}
		listGroupBy.add(facturasAux);		
		return listGroupBy;
	}
	
	public List<Factura> getConvertListGroupToListOrderBy(List<List<Factura>> listGroup,Factura indicat){
		List<Factura> facturasAux = new ArrayList<Factura>();
		for(List<Factura> facturas : listGroup)
		{
			if(facturas == null || facturas.size()==0)
			{
				facturasAux.add(new Factura());
			}
			else for(Factura factura : facturas) facturasAux.add(factura);
		} 
		return facturasAux;
	}

	public List<Factura> getAcumCantidad(List<Factura> facturas){
		
		for(Factura factura : facturas)
		{
			if(factura.getTotalAPagar() != null) 
			{
				valueAcum = valueAcum + factura.getTotalAPagar();
			}
				else
				{
					if(!valueAcum.equals(0.0))
					{
						factura.setTotalAPagar(valueAcum);
					}
					valueAcum = new Double(0.0);
				}
		}
		return facturas;
	}
	
	
	public List<Factura> getFilterByLineNull(Integer maxLine,List<Factura> facturas){
		Integer valueLine = new Integer(0);
		
		List<Factura> facturasAux = new ArrayList<Factura>();
		
		for(Factura factura : facturas)
		{
			if(factura.getId() == null)
			{
				valueLine=valueLine+1;
			}
			else
			{
				valueLine = 0;
			}
			
			if(valueLine<=maxLine)
			{
				facturasAux.add(factura);
			}
		}
		return facturasAux;
	}
	
	public void actualizarTabla() throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		/**7- Obtener informacion mediante ventana de consultas acerca de:
		 * Monto total facturado mensualmente 
		 * por zona y tipo de pago 
		 * */
		
		List<List<Factura>> listGroup = new ArrayList<List<Factura>>();
		List<Factura> listFactura = new ArrayList<Factura>();
	
		listFactura = getFilterByCmbAlmacen(facturas);
		
		listFactura = getFilterByCmbTipoPago(listFactura);
		
		listFactura = getFilterByCmbZona(listFactura);
		
		listFactura = getFilterByDate(listFactura);
		
		listFactura = getOrderBy("Date", listFactura);
	
		listGroup = getGroupBy("Month",listFactura);
	
		listGroup = getOrderListGroupBy("Zona", listGroup);
	
		listGroup = getGroupListGroupBy("Zona",listGroup);
	
		listGroup = getOrderListGroupBy("TipoPago", listGroup);
	
		listGroup = getGroupListGroupBy("TipoPago", listGroup);
	
		listFactura = getConvertListGroupToListOrderBy(listGroup, null);

		listFactura = getFilterByLineNull(2, listFactura);

		listFactura = getAcumCantidad(listFactura);
		
		activarBinding(listFactura);
	}	
		
	public ActionListener Atras() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Atras();
			}
		};
	}
	
	public ActionListener Salir(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				quitarVista();
			}
		};
	}

	public ActionListener buscar() {
		// TODO Auto-generated method stub
		return null;
	}

	public ActionListener Actualizar() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					actualizarTabla();
					vista.getTable().setDefaultRenderer(String.class, getTableCellRenderer());
				} catch (NumberFormatException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}

	public static List<Factura> getDetalleFacturas() {
		return facturas;
	}

	public static void setDetalleFacturas(List<Factura> facturas) {
		ContMontoFacturadoMesZonaTipoPago.facturas = facturas;
	}

	public static void setVista(MontoFacturadoMesZonaTipoPagoUI vista) {
		ContMontoFacturadoMesZonaTipoPago.vista = vista;
	}

	public TableCellRenderer getTableCellRenderer(){
		return new TableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable tabla, Object value,
					boolean arg2, boolean arg3, int row, int column) {
				// TODO Auto-generated method stub
				tabla.setShowGrid(false);
				tabla.setBackground(Color.lightGray);
				
				JPanel celda = new JPanel();
				celda.setBackground(Color.WHITE);
				JLabel label = new JLabel();	
				String linea = new String("");
				String columnName = new String(vista.getTable().getColumnName(column)); 
				
				if(value == null) label.setText(linea);
				else label.setText(value.toString());
				
				if(label.getText().equals(linea)) celda.setBackground(Color.lightGray);
				
				if(columnName.equals("Monto"))
				{
					
					if(vista.getTable().getValueAt(row,0)==null)
					{
						if(!label.getText().equals(""))label.setText("Total: ".concat(label.getText()).concat(" Monto.Facturado"));
						label.setForeground(Color.blue);
					}
				}
				else label.setForeground(Color.BLACK);
				
				celda.add(label);
				celda.setLayout(new GridLayout(1, 0, 0, 0));
				return celda;
			}
		};
	}

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void activarBinding(List<Factura> Facturas) {
		// TODO Auto-generated method stub
		vista.setTable(new JTable());
		vista.getScrollPanel().setViewportView(vista.getTable());
		JTableBinding binFacturas = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,Facturas,vista.getTable());
	    
	    BeanProperty idFactura  = BeanProperty.create("id");
	    BeanProperty fecha  = BeanProperty.create("fechaCadenaStr");
	    BeanProperty almacen = BeanProperty.create("almacen.ubicacion");
	    BeanProperty zonaDescripcion = BeanProperty.create("cliente.ruta.zona.descripcion");
	    BeanProperty tipoPago = BeanProperty.create("tipoPagoCreditoContado");
	    BeanProperty monto = BeanProperty.create("totalAPagar");
	    

	    binFacturas.addColumnBinding(idFactura).setColumnClass(String.class).setColumnName("Nro Factura");
	    binFacturas.addColumnBinding(fecha).setColumnClass(String.class).setColumnName("Fecha");
	    binFacturas.addColumnBinding(almacen).setColumnClass(String.class).setColumnName("Almacen");
	    binFacturas.addColumnBinding(zonaDescripcion).setColumnClass(String.class).setColumnName("Zona");
	    binFacturas.addColumnBinding(tipoPago).setColumnClass(String.class).setColumnName("Tipo Pago");
	    
	    binFacturas.addColumnBinding(monto).setColumnClass(String.class).setColumnName("Monto");
	    
	    binFacturas.bind();
	}
   
    @SuppressWarnings("rawtypes")
   	public void cargarCmbAlmacen() throws Exception{
       	List<Almacen> almacenes = new ServicioAlmacen().getAlmacenes();
       	Almacen almacen = new Almacen(0,"Todos");
       	almacenes.add(almacen);
       	JComboBoxBinding jcomboAlmacen = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ,almacenes,vista.getCmbAlmacen());
   	    
   	    jcomboAlmacen.bind();
       }

    @SuppressWarnings("rawtypes")
	public void cargarCmbZona() throws NumberFormatException, IOException{
    	Zona zona = new Zona();
    	zona.setId(0);
    	zonas.add(zona);
    	JComboBoxBinding jcomboZona = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ,zonas,vista.getCmbZona());
	    jcomboZona.bind();
    }
}

