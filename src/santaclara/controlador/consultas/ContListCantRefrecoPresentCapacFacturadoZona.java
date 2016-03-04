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
import santaclara.Servicio.ServicioCapacidad;
import santaclara.Servicio.ServicioDetalleFactura;
import santaclara.Servicio.ServicioPresentacion;
import santaclara.Servicio.ServicioZona;
import santaclara.controlador.ContGeneral;
import santaclara.controlador.ContPrincipal;
import santaclara.controlador.IContGeneral;
import santaclara.modelo.Almacen;
import santaclara.modelo.Capacidad;
import santaclara.modelo.DetalleFactura;
import santaclara.modelo.Presentacion;
import santaclara.modelo.Zona;
import santaclara.vista.consultas.ListCantRefrescoPresentCapacFacturadoZonaUI;

public class ContListCantRefrecoPresentCapacFacturadoZona extends ContGeneral implements IContGeneral {

	private static ListCantRefrescoPresentCapacFacturadoZonaUI vista;
	private static List<DetalleFactura> detalleFacturas ;
	Integer acumCantidad = new Integer(0);
	
	private List<Zona> zonas = new ArrayList<Zona>();
	private List<Almacen> almacenes = new ArrayList<Almacen>();
	private List<Presentacion> presentaciones = new ArrayList<Presentacion>();
	private List<Capacidad> capacidades = new ArrayList<Capacidad>();
	
	public ContListCantRefrecoPresentCapacFacturadoZona() {
		// TODO Auto-generated constructor stub
		
	}
	
	public ContListCantRefrecoPresentCapacFacturadoZona(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		vista = new  ListCantRefrescoPresentCapacFacturadoZonaUI(this);
		dibujar(vista,this);
		zonas = new ServicioZona().getZonas();
		almacenes = new ServicioAlmacen().getAlmacenes();
		capacidades = new ServicioCapacidad().getCapacidades();
		presentaciones = new ServicioPresentacion().getPresentaciones();
		
		cargarCmbAlmacen();
		setSelectedValue(vista.getCmbAlmacen(), null);
		cargarCmbPresentacion();
		setSelectedValue(vista.getCmbPresentacion(), null);
		cargarCmbCapacidad();
		setSelectedValue(vista.getCmbCapacidad(), null);
		cargarCmbZona();
		setSelectedValue(vista.getCmbZona(), null);
		
		vista.getDateHasta().setMaxSelectableDate(new Date());
		vista.getDateHasta().setDate(new Date());
		vista.getDateDesde().setDate(new Date());
		
		detalleFacturas = new ServicioDetalleFactura().getDetalleFacturas();
	}
	@Override
	public JPanel getVista() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<DetalleFactura> getFilterByCmbAlmacen(List<DetalleFactura> detalleFacturas){
		List<DetalleFactura> detalleFacturasAux = new ArrayList<DetalleFactura>();
		if(!((Almacen)vista.getCmbAlmacen().getSelectedItem()).getId().equals(0))
		{
			for(DetalleFactura detalleFactura: detalleFacturas)
			{
				if (detalleFactura.getFactura().getAlmacen().getId().equals(((Almacen)vista.getCmbAlmacen().getSelectedItem()).getId()))
				{
					detalleFacturasAux.add(detalleFactura);
				}			
			}
			return detalleFacturasAux;
		}
		return detalleFacturas;
	}
	
	public List<DetalleFactura> getFilterByCmbZona(List<DetalleFactura> detalleFacturas){
		List<DetalleFactura> detalleFacturasAux = new ArrayList<DetalleFactura>();
		if(!((Zona)vista.getCmbZona().getSelectedItem()).getId().equals(0))
		{
			for(DetalleFactura detalleFactura: detalleFacturas)
			{
				if (detalleFactura.getFactura().getCliente().getRuta().getZona().getId().equals(((Zona)vista.getCmbZona().getSelectedItem()).getId()))
				{
					detalleFacturasAux.add(detalleFactura);
				}			
			}
			return detalleFacturasAux;
		}
		return detalleFacturas;
	}
	
	public List<DetalleFactura> getFilterByCmbPresentacion(List<DetalleFactura> detalleFacturas){
		List<DetalleFactura> detalleFacturasAux = new ArrayList<DetalleFactura>();
		if(!((Presentacion)vista.getCmbPresentacion().getSelectedItem()).getId().equals(0))
		{
			for(DetalleFactura detalleFactura: detalleFacturas)
			{
				if (detalleFactura.getEmpaqueProducto().getProducto().getPresentacion().getId().equals(((Presentacion)vista.getCmbPresentacion().getSelectedItem()).getId()))
				{
					detalleFacturasAux.add(detalleFactura);
				}			
			}
			return detalleFacturasAux;
		}
		return detalleFacturas;
	}
	
	
	public List<DetalleFactura> getFilterByCmbCapacidad(List<DetalleFactura> detalleFacturas){
		List<DetalleFactura> detalleFacturasAux = new ArrayList<DetalleFactura>();
		if(!((Capacidad)vista.getCmbCapacidad().getSelectedItem()).getId().equals(0))
		{
			for(DetalleFactura detalleFactura: detalleFacturas)
			{
				if (detalleFactura.getEmpaqueProducto().getProducto().getCapacidad().getId().equals(((Capacidad)vista.getCmbCapacidad().getSelectedItem()).getId()))
				{
					detalleFacturasAux.add(detalleFactura);
				}			
			}
			return detalleFacturasAux;
		}
		return detalleFacturas;
	}
	
	
	public List<DetalleFactura> getFilterByDate(List<DetalleFactura> detalleFacturas){
		List<DetalleFactura> detalleFacturasAux = new ArrayList<DetalleFactura>();
		//Filtra fecha Respecto Al dateDesde Y dateHasta
		for(DetalleFactura detalleFactura: detalleFacturas)
		{
			if (detalleFactura.getFactura().getFecha().getTime() >= vista.getDateDesde().getDate().getTime() &&
					detalleFactura.getFactura().getFecha().getTime() <= vista.getDateHasta().getDate().getTime())
			{
					detalleFacturasAux.add(detalleFactura);
			}
		}
		return detalleFacturasAux;
	}
	
	public List<List<DetalleFactura>> getOrderListGroupBy(String condition,List<List<DetalleFactura>> listGroup){
		List<List<DetalleFactura>> listGroupByAux = new ArrayList<List<DetalleFactura>>();
		for(List<DetalleFactura> list : listGroup) listGroupByAux.add(getOrderBy(condition, list));
		return listGroupByAux;
	}
	
	public List<DetalleFactura> getOrderBy(String condition,List<DetalleFactura> detalleFacturasNotOrder){
		List<DetalleFactura> detalleFacturasAux = new ArrayList<DetalleFactura>();
		DetalleFactura menorDetalleFactura = new DetalleFactura();
		while(!detalleFacturasNotOrder.isEmpty())
		{
			menorDetalleFactura = detalleFacturasNotOrder.get(0);
			
			for(DetalleFactura detalleFactura: detalleFacturasNotOrder)
			{	
				if(detalleFactura!=null && menorDetalleFactura !=null)
				{
					if(idValue(condition, detalleFactura) <= idValue(condition, menorDetalleFactura) )
					{	
						menorDetalleFactura = detalleFactura;	
					}
				}
			}
			detalleFacturasAux.add(menorDetalleFactura);
			
			detalleFacturasNotOrder.remove(menorDetalleFactura);
		
		}
		return detalleFacturasAux;
	}
	
	@SuppressWarnings("deprecation")
	public Long idValue(String condition,DetalleFactura detalleFactura){
		if(detalleFactura!=null)
		{
			switch (condition) {
			case "Date":return detalleFactura.getFactura().getFecha().getTime();
			case "Month":return new Long(detalleFactura.getFactura().getFecha().getMonth());
			case "Almacen": return  new Long(detalleFactura.getFactura().getAlmacen().getId());
			case "EmpaqueProducto": return  new Long(detalleFactura.getEmpaqueProducto().getId());
			case "Capacidad": return  new Long(detalleFactura.getEmpaqueProducto().getProducto().getCapacidad().getId());
			case "Presentacion": return  new Long(detalleFactura.getEmpaqueProducto().getProducto().getPresentacion().getId());
			case "Sabor": return  new Long(detalleFactura.getEmpaqueProducto().getProducto().getSabor().getId());
			case "Zona": return  new Long(detalleFactura.getFactura().getCliente().getRuta().getZona().getId());
			default:	return null;
			}
		}
		return null;
	}
	
	public List<List<DetalleFactura>> getGroupListGroupBy(String condition,List<List<DetalleFactura>> listGroup){
		List<List<DetalleFactura>> detalleFacturasAux = new ArrayList<List<DetalleFactura>>();
		
		for(List<DetalleFactura> detalleFacturas : listGroup)
		{
			for(List<DetalleFactura> detalleFacturas2 : getGroupBy(condition, getOrderBy(condition, detalleFacturas)))
			{
				detalleFacturasAux.add(detalleFacturas2);
				
			}
			detalleFacturasAux.add(new ArrayList<DetalleFactura>());
		}
	return detalleFacturasAux;
	}
	
	public List<List<DetalleFactura>> getGroupBy(String condition,List<DetalleFactura> detalleFacturas){
		List<DetalleFactura> detalleFacturasAux = new ArrayList<DetalleFactura>();
		List<List<DetalleFactura>> listGroupBy = new ArrayList<List<DetalleFactura>>(); 	
		
		if(!detalleFacturas.isEmpty())
		{
			
		Long id =  idValue(condition, detalleFacturas.get(0)) ;
		for(DetalleFactura detalleFactura: detalleFacturas)
		{
			if(detalleFactura !=null)
			{
				if(!id.equals(idValue(condition, detalleFactura)))
				{
					listGroupBy.add(detalleFacturasAux);
					
					listGroupBy.add(new ArrayList<DetalleFactura>());//new Line
					
					detalleFacturasAux = new ArrayList<DetalleFactura>();//Lo instancio nuevamente
				}
				detalleFacturasAux.add(detalleFactura);
				id = idValue(condition, detalleFactura);
			}
			else listGroupBy.add(new ArrayList<DetalleFactura>());
			
		}
		}
		listGroupBy.add(detalleFacturasAux);		
		return listGroupBy;
	}
	
	public List<DetalleFactura> getConvertListGroupToListOrderBy(List<List<DetalleFactura>> listGroup,DetalleFactura indicat){
		List<DetalleFactura> detalleFacturasAux = new ArrayList<DetalleFactura>();
		for(List<DetalleFactura> detalleFacturas : listGroup)
		{
			if(detalleFacturas == null || detalleFacturas.size()==0)
			{
				detalleFacturasAux.add(new DetalleFactura());
			}
			else for(DetalleFactura detalleFactura : detalleFacturas) detalleFacturasAux.add(detalleFactura);
		} 
		return detalleFacturasAux;
	}

	public List<DetalleFactura> getAcumCantidad(List<DetalleFactura> detalleFacturas){
		Integer valueAcum = new Integer(0);
		for(DetalleFactura detalleFactura : detalleFacturas)
		{
			if(detalleFactura.getFactura() != null) 
			{
				valueAcum = valueAcum + detalleFactura.getCantidad();
			}
				else
				{
					if(valueAcum != 0)
					{
						detalleFactura.setCantidad(valueAcum);
					}
					valueAcum = 0;
				}
		}
		return detalleFacturas;
	}
	
	
	public List<DetalleFactura> getFilterByLineNull(Integer maxLine,List<DetalleFactura> detalleFacturas){
		Integer valueLine = new Integer(0);
		List<DetalleFactura> detalleFacturasAux = new ArrayList<DetalleFactura>();
		
		for(DetalleFactura detalleFactura : detalleFacturas)
		{
			if(detalleFactura.getFactura()==null)
			{
				valueLine=valueLine+1;
			}
			else
			{
				valueLine = 0;
			}
			
			if(valueLine<=maxLine)
			{
				detalleFacturasAux.add(detalleFactura);
			}
		}
		return detalleFacturasAux;
	}
	
	public void actualizarTabla() throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		/**7- Obtener informacion mediante ventana de consultas acerca de:
		 * Listado de laq cantidad de refresco 
		 * por presentacion y capacidad Facturado Zona 
		 * en un determinado periodo de tiempo determinado
		 * */
		
		List<List<DetalleFactura>> listGroup = new ArrayList<List<DetalleFactura>>();
		List<DetalleFactura> listOrder = new ArrayList<DetalleFactura>();
		
		listOrder = getFilterByCmbAlmacen(detalleFacturas);
		
		listOrder = getFilterByDate(listOrder);
		
		listOrder = getFilterByCmbZona(listOrder);
		
		listOrder = getFilterByCmbCapacidad(listOrder);
		
		listOrder = getFilterByCmbPresentacion(listOrder);
		
		listOrder = getOrderBy("Zona", listOrder);
		
		listGroup = getGroupBy("Zona",listOrder);
		
		listGroup = getOrderListGroupBy("Presentacion", listGroup);
		
		listGroup = getGroupListGroupBy("Presentacion", listGroup);
				
		listGroup = getOrderListGroupBy("Capacidad", listGroup);
		
		listGroup = getGroupListGroupBy("Capacidad", listGroup);
		
		listOrder = getConvertListGroupToListOrderBy(listGroup, null);
		
		listOrder = getFilterByLineNull(2, listOrder);
		
		listOrder = getAcumCantidad(listOrder);
		
		activarBinding(listOrder);
	}	
	
	void printListOrdenBy(List<DetalleFactura> listOrder,String title){
		System.out.println("/***************Start Print  ".concat(title).concat("********************"));
		for(DetalleFactura detalleFactura : listOrder){
			if (detalleFactura.getFactura()==null)
			{
				System.out.println("new line");
			}
			else
			{
				System.out.println("data: "+ detalleFactura.getFactura().getId());
			}
		}
		System.out.println("/***************End Print Size List "+listOrder.size()+"********************");
	}
	
	void printListGroupBy(List<List<DetalleFactura>> listGroup,String title){
		System.out.println("/***************Start Print  ".concat(title).concat("********************"));
		for(List<DetalleFactura> detalleFacturas :listGroup){
			
			if (detalleFacturas==null)
			{
				System.out.println("new line");
			}
			else
			{
				printListOrdenBy(detalleFacturas,title);
			}
			
		}
		System.out.println("/***************End Print  Size List "+listGroup.size()+" ".concat(title).concat("********************\n"));
	}
	

	public ActionListener Atras() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ActivarAtras(null);
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

	public static List<DetalleFactura> getDetalleFacturas() {
		return detalleFacturas;
	}

	public static void setDetalleFacturas(List<DetalleFactura> detalleFacturas) {
		ContListCantRefrecoPresentCapacFacturadoZona.detalleFacturas = detalleFacturas;
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
				
				if(columnName.equals("Cantidad"))
				{
					
					if(vista.getTable().getValueAt(row,0)==null)
					{
						if(!label.getText().equals(""))label.setText("Total: ".concat(label.getText()).concat(" Un.Facturado"));
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
		public void activarBinding(List<DetalleFactura> detalleFacturas) {
			// TODO Auto-generated method stub
		
		   	vista.setTable(new JTable());
			vista.getScrollPanel().setViewportView(vista.getTable());
		   
		   	JTableBinding binFacturas = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,detalleFacturas,vista.getTable());
		    
		   	BeanProperty idFactura  = BeanProperty.create("factura.id");
		    BeanProperty fecha  = BeanProperty.create("factura.fechaCadenaStr");
		    BeanProperty almacen = BeanProperty.create("factura.almacen.ubicacion");
		    BeanProperty presentacion = BeanProperty.create("empaqueProducto.producto.presentacion.material");
		    BeanProperty capacidad = BeanProperty.create("empaqueProducto.producto.capacidad.volumenStr");
		    BeanProperty zonaDescripcion = BeanProperty.create("factura.clienteZona");
		    BeanProperty cantidad = BeanProperty.create("cantidad");
		    

		    binFacturas.addColumnBinding(idFactura).setColumnClass(String.class).setColumnName("Nro Factura");
		    binFacturas.addColumnBinding(fecha).setColumnClass(String.class).setColumnName("Fecha");
		    binFacturas.addColumnBinding(zonaDescripcion).setColumnClass(String.class).setColumnName("Zona");
		    
		    binFacturas.addColumnBinding(almacen).setColumnClass(String.class).setColumnName("Almacen");
		    binFacturas.addColumnBinding(presentacion).setColumnClass(String.class).setColumnName("Presentacion");
		    
		    binFacturas.addColumnBinding(capacidad).setColumnClass(String.class).setColumnName("Capacidad");
		   
		    binFacturas.addColumnBinding(cantidad).setColumnClass(String.class).setColumnName("Cantidad");
		    

		    binFacturas.bind();
		}
	    
	    @SuppressWarnings("rawtypes")
		public void cargarCmbAlmacen() throws NumberFormatException, IOException{
	    	Almacen almacen = new Almacen(0,"Todos");
	    	almacenes.add(almacen);
	    	JComboBoxBinding jcomboAlmacen = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ,almacenes,vista.getCmbAlmacen());
		    
		    jcomboAlmacen.bind();
	    }
	    
		@SuppressWarnings("rawtypes")
		public void cargarCmbPresentacion() throws NumberFormatException, IOException{
	    	Presentacion presentacion = new Presentacion(0,"Todos");
	    	presentaciones.add(presentacion);
	    	JComboBoxBinding jcomboPresentacion = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ,presentaciones,vista.getCmbPresentacion());
		    
		    jcomboPresentacion.bind();
	    }
		
		@SuppressWarnings("rawtypes")
		public void cargarCmbCapacidad() throws NumberFormatException, IOException{
	    	Capacidad capacidad = new Capacidad();
	    	capacidad.setId(0);
	    	capacidades.add(capacidad);
	    	JComboBoxBinding jcomboCapacidad = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ,capacidades,vista.getCmbCapacidad());
		    
		    jcomboCapacidad.bind();
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

