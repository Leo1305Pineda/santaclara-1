package santaclara.controlador.consultas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import santaclara.Servicio.ServicioDetalleFactura;
import santaclara.controlador.ContGeneral;
import santaclara.controlador.ContPrincipal;
import santaclara.controlador.IContGeneral;
import santaclara.modelo.Almacen;
import santaclara.modelo.DetalleFactura;
import santaclara.vista.consultas.DetalleFacturaMesAlmacenUI;
import sun.security.action.GetLongAction;

public class ContDetalleFacturaMesAlmacen extends ContGeneral implements IContGeneral {

	private static DetalleFacturaMesAlmacenUI vista;
	private static List<DetalleFactura> detalleFacturas ;			/*Variable que permite obtener 
																	la lista original de tota los 
																	Detalle Facturados.*/ 
	private static List<DetalleFactura> detalleFacturasAux;			/*Variable que se modifica constantemente
	 																rediciendose, agrupandose y ordenandose
	 																para ser  Mostrado en la Tabla */
	
	public ContDetalleFacturaMesAlmacen() {
		// TODO Auto-generated constructor stub
		
	}
	
	public ContDetalleFacturaMesAlmacen(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		vista = new DetalleFacturaMesAlmacenUI(this);
		dibujar(vista,this);
		vista.cargarCmbAlmacen();
		setSelectedValue(vista.getCmbAlmacen(), null);
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

	public ActionListener action() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					//mostrarDato();
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		};
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
	
	public Long idValue(String condition,DetalleFactura detalleFactura){
		if(detalleFactura!=null)
		{
			switch (condition) {
			case "Date":return detalleFactura.getFactura().getFecha().getTime();
			case "Month":return new Long(detalleFactura.getFactura().getFecha().getMonth());
			case "Almacen": return  new Long(detalleFactura.getFactura().getAlmacen().getId());
			case "EmpaqueProducto": return  new Long(detalleFactura.getEmpaqueProducto().getId());
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
		List<List<DetalleFactura>> listGroup = new ArrayList<List<DetalleFactura>>();
		List<DetalleFactura> listOrder = new ArrayList<DetalleFactura>();
		/**Start****************************************************************************************************/
		//Comienzo Filtrando y Ordenando Por Fecha
		listOrder = getOrderBy("Date", getFilterByDate(getFilterByCmbAlmacen(detalleFacturas)));
		printListOrdenBy(listOrder,"List Order by Date");
			//Luego Agrupo por Mes
			listGroup = getGroupBy("Month",listOrder);
			printListGroupBy(listGroup,"List Group By Mes");
				//Entonces Ordeno la Lista Agrupada ven cada Nodo
					listGroup = getOrderListGroupBy("Almacen", listGroup);
					printListGroupBy(listGroup,"List OrderBy Almacen");
						//Asi que con la lista Agrupada y Ordenada la Agrupo por Almacen
							listGroup = getGroupListGroupBy("Almacen",listGroup);
							printListGroupBy(listGroup,"List GroupBy Almacen");
								//Posteriomente reordeno la lista por EmpaqueProducto
									listGroup = getOrderListGroupBy("EmpaqueProducto", listGroup);
									printListGroupBy(listGroup,"List OrderBy EmpaqueProducto");
										//porSiguiente agrupo segun El Empaque
											listGroup = getGroupListGroupBy("EmpaqueProducto", listGroup);
											printListGroupBy(listGroup,"List GroupBy EmpaqueProducto");
												//Por Ultimo conviento la lista Agrupada en una lista simple Para Imprimir
													listOrder = getConvertListGroupToListOrderBy(listGroup, null);
														//Filtrar las Lineas Nulas
															listOrder = getFilterByLineNull(2, listOrder);
															//Preparar para Mostrar
													printListOrdenBy(listOrder,"ListGroup Convert to ListOrder");
		/*Mostrar Tabla			*/	vista.activarBinding(listOrder);
	}	/******************************************************************************************************End**/
	
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
	
	@SuppressWarnings("rawtypes")
	public void setSelectedValue(JComboBox comboBox,Integer id)
    {	
        for (int i = 0; i < comboBox.getItemCount(); i++)
        {
        	comboBox.setSelectedIndex(i);
        	Boolean enc=false;
        	switch (comboBox.getSelectedItem().getClass().getName().toString()) {
			case "santaclara.modelo.Almacen":
				enc = (((Almacen)comboBox.getSelectedItem()).getId().equals(id)); 
					break;
			default:
				break;
			}
        	if (enc) break;
        }
    }

	public ActionListener Atras() {
		// TODO Auto-generated method stub
		return null;
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
		ContDetalleFacturaMesAlmacen.detalleFacturas = detalleFacturas;
	}

	public static List<DetalleFactura> getDetalleFacturasAux() {
	
		return detalleFacturasAux;
	}

	public static void setDetalleFacturasAux(List<DetalleFactura> detalleFacturasAux) {
		ContDetalleFacturaMesAlmacen.detalleFacturasAux = detalleFacturasAux;
	}

	public static void setVista(DetalleFacturaMesAlmacenUI vista) {
		ContDetalleFacturaMesAlmacen.vista = vista;
	}

}

